package br.com.condelivery.order.controller;

import br.com.condelivery.order.dto.ItemDto;
import br.com.condelivery.order.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;


    @PostMapping("/create")
    public ResponseEntity<ItemDto> registerItem(@Valid @RequestBody ItemDto itemDto) { // Corrigido para itemDto
        ItemDto newItem = itemService.createItem(itemDto);
        return ResponseEntity.ok(newItem);
    }


}
