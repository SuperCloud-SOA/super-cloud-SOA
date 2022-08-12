package superCloud.config;

import com.alibaba.fastjson.JSON;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ys
 * @version 1.0
 * @date 2022/8/2 15:44
 */
@Component
public class UserGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //如果解析到令牌就会封装到 OAuth2Authentication 中
        if (!(authentication instanceof OAuth2Authentication)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //用户名 没有其他信息
        Object principal = authentication.getPrincipal();
        //获取用户所拥有的权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Set<String> authoritySet = AuthorityUtils.authorityListToSet(authorities);
        //请求详情
        Object details = authentication.getDetails();
        Map<String,Object> result = new HashMap<>();
        result.put("principal",principal);
        result.put("authorities",authoritySet);
        result.put("details",details);

        //获取当前请求上下文
        ServerHttpRequest request=exchange.getRequest();
        //将用户信息与权限转成json 再通过base64编码
        String resultBase64 = Base64Utils.encodeToString(JSON.toJSONString(result).getBytes());
        //添加到请求头
        ServerHttpRequest shr=request.mutate().header("auth-token", resultBase64).build();
        //继续往下执行
        return chain.filter(exchange.mutate().request(shr).build());
    }
    //数字越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
