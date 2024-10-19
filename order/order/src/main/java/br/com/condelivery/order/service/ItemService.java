package br.com.condelivery.order.service;

import br.com.condelivery.order.dto.ItemDto;
import br.com.condelivery.order.dto.OrderDto;
import br.com.condelivery.order.model.Item;
import br.com.condelivery.order.model.Order;
import br.com.condelivery.order.repository.ItemRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public ItemDto createItem(@Valid ItemDto itemDto) {

        Item item = modelMapper.map(itemDto, Item.class);
        Item savedItem = repository.save(item);

        return modelMapper.map(savedItem, ItemDto.class);
    }
}
