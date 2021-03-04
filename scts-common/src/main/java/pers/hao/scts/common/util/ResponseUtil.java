package pers.hao.scts.common.util;

import cn.hutool.json.JSONUtil;
import org.apache.http.entity.ContentType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 响应工具
 *
 * @author hujiahao
 */
public class ResponseUtil {

    /**
     * 设置响应
     *
     * @param response    HttpServletResponse
     * @param contentType content-type
     * @param status      http状态码
     * @param value       响应内容
     * @throws IOException IOException
     */
    public static void responseWriter(HttpServletResponse response, String contentType,
                                      int status, Object value) throws IOException {
        response.setContentType(contentType);
        response.setStatus(status);
        response.getOutputStream().write(JSONUtil.parse(value).toString().getBytes());
    }

    /**
     * 设置webflux模型响应
     *
     * @param response    ServerHttpResponse
     * @param resultStatus     业务码
     * @return Mono<Void>
     */
    public static Mono<Void> unauthorized(ServerHttpResponse response, ResultStatus resultStatus) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        Result<?> error = Result.failure(resultStatus);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONUtil.parse(error).toString().getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}
