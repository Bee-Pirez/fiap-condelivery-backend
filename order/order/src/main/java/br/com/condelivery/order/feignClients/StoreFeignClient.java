package br.com.condelivery.order.feignClients;

import br.com.condelivery.order.dto.StoreDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "products", path = "/stores" )
public interface StoreFeignClient {

    @GetMapping("/{storeId}")
    StoreDto getStoreById(@PathVariable("storeId") Long storeId);
}
