package br.com.condelivery.delivery.controller;

import br.com.condelivery.delivery.dto.DeliveryDto;
import br.com.condelivery.delivery.model.Status;
import br.com.condelivery.delivery.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;


    @PostMapping("/create")
    public ResponseEntity<DeliveryDto> registerOrder(@Valid @RequestBody DeliveryDto deliveryDto) {
        DeliveryDto newOrder = deliveryService.createOrder(deliveryDto);
        return ResponseEntity.ok(newOrder);
    }


    @GetMapping("/deliveryman/{residentId}")
    public ResponseEntity<List<DeliveryDto>> getDeliveriesByResident(@PathVariable Long residentId) {
        List<DeliveryDto> orders = deliveryService.getDeliveriesByResident(residentId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{deliveryId}/status")
    public ResponseEntity<DeliveryDto> updateDeliveryStatus(@PathVariable Long deliveryId, @RequestBody Status newStatus) {
        DeliveryDto updatedDelivery = deliveryService.updateDeliveryStatus(deliveryId, newStatus);
        return ResponseEntity.ok(updatedDelivery);
    }
}
