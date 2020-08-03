package cc.caker.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author cakeralter
 * @date 2020/8/3
 */
@FeignClient("provider-serv")
public interface ApiService {

    @GetMapping("/api/{msg}")
    String say(@PathVariable("msg") String msg);
}
