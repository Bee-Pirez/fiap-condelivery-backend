package br.com.condelivery.auth.feignClients;

import br.com.condelivery.auth.model.Condominium;
import br.com.condelivery.auth.model.Resident;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "user")
public interface UserFeignClient {

    @GetMapping(value = "/residents/search")
    ResponseEntity<Resident> findResidentByEmail(@RequestParam String email);

    @GetMapping(value = "/condominiums/search")
    ResponseEntity<Condominium> findCondominiumByEmail(@RequestParam String email);
}
