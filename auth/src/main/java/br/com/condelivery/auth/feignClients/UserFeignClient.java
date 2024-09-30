package br.com.condelivery.auth.feignClients;

import br.com.condelivery.auth.model.Resident;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "user", path = "/residents")
public interface UserFeignClient {

    @GetMapping(value = "/search")
    ResponseEntity<Resident> findByEmail(@RequestParam String email);
}
