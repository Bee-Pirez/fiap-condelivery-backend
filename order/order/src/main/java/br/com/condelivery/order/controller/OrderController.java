package br.com.condelivery.order.controller;

import br.com.condelivery.order.dto.ItemDto;
import br.com.condelivery.order.dto.OrderDto;
import br.com.condelivery.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public ResponseEntity<OrderDto> registerOrder(@Valid @RequestBody OrderDto orderDto) {
        OrderDto newOrder = orderService.createOrder(orderDto);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/resident/{residentId}")
    public ResponseEntity<List<OrderDto>> getAllOrdersWithItemsByResident(@PathVariable Long residentId) {
        List<OrderDto> orderDtos = orderService.getAllOrdersWithItemsByResident(residentId);
        return ResponseEntity.ok(orderDtos);
    }


    /*@GetMapping("/resident/{residentId}")
    public ResponseEntity<List<OrderDto>> getAllOrdersWithItemsByResident(@PathVariable Long residentId) {
        List<OrderDto> orderDtos = orderService.getAllOrdersWithItemsByResident(residentId);
        return ResponseEntity.ok(orderDtos);
    }

     */
}
