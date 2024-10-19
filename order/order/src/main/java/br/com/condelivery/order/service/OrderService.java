package br.com.condelivery.order.service;

import br.com.condelivery.order.dto.ItemDto;
import br.com.condelivery.order.dto.OrderDto;
import br.com.condelivery.order.dto.StoreDto;
import br.com.condelivery.order.feignClients.StoreFeignClient;
import br.com.condelivery.order.model.Order;
import br.com.condelivery.order.repository.OrderRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StoreFeignClient storeFeignClient;


    public OrderDto createOrder(@Valid OrderDto orderDto) {

        Order order = modelMapper.map(orderDto, Order.class);
        Order savedOrder = repository.save(order);

        return modelMapper.map(savedOrder, OrderDto.class);
    }

    public List<OrderDto> getOrdersByResident(Long residentId) {
        List<Order> orders = repository.findByResidentId(residentId);

        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    public List<OrderDto> getAllOrdersWithItemsByResident(Long residentId) {
        List<Order> orders = repository.findAllByResidentIdWithItems(residentId);

        return orders.stream().map(order -> {
            List<ItemDto> itemDtos = order.getItems().stream()
                    .map(item -> new ItemDto(item.getId(), item.getQuantity(), item.getCreatedAt(), item.getProductId(), order.getId()))
                    .collect(Collectors.toList());

            StoreDto storeDto = storeFeignClient.getStoreById(order.getStoreId());

            OrderDto orderDto = new OrderDto(order.getId(), order.getPrice(), order.getCreatedAt(), order.getResidentId(), order.getStoreId(), itemDtos);
            orderDto.setStore(storeDto);

            return orderDto;
        }).collect(Collectors.toList());
    }

    /*public List<OrderDto> getAllOrdersWithItemsByResident(Long residentId) {
        // Busca todos os pedidos do residente com os itens
        List<Order> orders = repository.findAllByResidentIdWithItems(residentId);

        // Mapeia cada pedido e seus itens para OrderDto
        return orders.stream().map(order -> {
            List<ItemDto> itemDtos = order.getItems().stream()
                    .map(item -> new ItemDto(item.getId(), item.getQuantity(), item.getCreatedAt(), item.getProductId(), order.getId()))
                    .collect(Collectors.toList());

            return new OrderDto(order.getId(), order.getPrice(), order.getCreatedAt(), order.getResidentId(), order.getStoreId(), itemDtos);
        }).collect(Collectors.toList());
    }

     */

    //listar todos os pedidos de todos os residents de um condominio
    //pegar produto pelo id no serviço de products

    //conectar com produto
}
