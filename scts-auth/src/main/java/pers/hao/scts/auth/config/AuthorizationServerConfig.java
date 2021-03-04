package pers.hao.scts.auth.config;

import com.xkcoding.justauth.AuthRequestFactory;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.JdkSerializationStrategy;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import pers.hao.scts.auth.granter.CaptchaTokenGranter;
import pers.hao.scts.auth.granter.SocialTokenGranter;
import pers.hao.scts.common.constant.AuthConstants;
import pers.hao.scts.core.redis.util.RedisUtil;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 授权中心服务
 * @author hujiahao
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 客户端信息JDBC储存
     */
    private final DataSource dataSource;

    /**
     * 认证管理器
     */
    private final AuthenticationManager authenticationManager;

    /**
     * 用户信息检验
     */
    private final UserDetailsService userDetailsService;

    /**
     * Redis 操作类
     */
    private final RedisUtil redisUtil;

    /**
     * Redis 数据源
     */
    private final RedisConnectionFactory redisConnectionFactory;

    /**
     * AuthRequestFactory JustAuth 第三方登录工具
     */
    private final AuthRequestFactory authRequestFactory;

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix(AuthConstants.RESOURCE_TOKEN_PREFIX);
        redisTokenStore.setSerializationStrategy(new JdkSerializationStrategy());
        return redisTokenStore;
    }

    @Bean
    public DefaultTokenServices tokenService() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 配置token存储
        tokenServices.setTokenStore(tokenStore());
        // 开启支持refresh_token，此处如果之前没有配置，启动服务后再配置重启服务，可能会导致不返回token的问题，解决方式：清除redis对应token存储
        tokenServices.setSupportRefreshToken(true);
        // 复用refresh_token
        tokenServices.setReuseRefreshToken(true);
        // token有效期，设置2小时
        tokenServices.setAccessTokenValiditySeconds(2 * 60 * 60);
        // refresh_token有效期，设置一周
        tokenServices.setRefreshTokenValiditySeconds(15 * 24 * 60 * 60);
        return tokenServices;
    }

    /**
     * 重点
     * 先添加已有的五种授权，然后添加我们自定义授权
     */
    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));
        granters.add(new CaptchaTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory(), redisUtil, authenticationManager));
        granters.add(new SocialTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory(), authRequestFactory,authenticationManager));
        return new CompositeTokenGranter(granters);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        //主要是让/oauth/token支持client_id以及client_secret作登录认证
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                // 配置用户服务
                .userDetailsService(userDetailsService)
                // 配置认证管理器
                .authenticationManager(authenticationManager)
                // 添加自定义授权模式
                .tokenGranter(tokenGranter(endpoints))
                // token储存服务与位置
                .tokenServices(tokenService());
    }

    /**
     * 使用非对称加密算法对token签名
     */
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setKeyPair(keyPair());
//        return converter;
//    }

    /**
     * 从classpath下的密钥库中获取密钥对(公钥+私钥)
     */
//    @Bean
//    public KeyPair keyPair() {
//        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "jiahao81".toCharArray());
//        return factory.getKeyPair("jwt", "jiahao81".toCharArray());
//    }

}
