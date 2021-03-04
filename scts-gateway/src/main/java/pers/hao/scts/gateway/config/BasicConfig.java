package pers.hao.scts.gateway.config;


import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import pers.hao.scts.gateway.props.SystemProperties;

/**
 * @author hujiahao
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({SystemProperties.class})
public class BasicConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

}
