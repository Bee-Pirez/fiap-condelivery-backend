package br.com.condelivery.delivery.service;

import br.com.condelivery.delivery.dto.DeliveryDto;
import br.com.condelivery.delivery.model.Delivery;
import br.com.condelivery.delivery.model.Status;
import br.com.condelivery.delivery.repository.DeliveryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public DeliveryDto createOrder(@Valid DeliveryDto deliveryDto) {

        Delivery order = modelMapper.map(deliveryDto, Delivery.class);
        Delivery savedOrder = repository.save(order);

        return modelMapper.map(savedOrder, DeliveryDto.class);
    }

    public List<DeliveryDto> getDeliveriesByResident(Long residentId) {
        List<Delivery> orders = repository.findByResidentId(residentId);

        return orders.stream()
                .map(order -> modelMapper.map(order, DeliveryDto.class))
                .collect(Collectors.toList());
    }

    public DeliveryDto updateDeliveryStatus(Long deliveryId, Status newStatus) {
        Delivery delivery = repository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found"));

        delivery.setStatus(newStatus);

        Delivery updatedDelivery = repository.save(delivery);

        return modelMapper.map(updatedDelivery, DeliveryDto.class);
    }

    //listar todos os as entregas de todos os residents de um condominio
    //pegar pedidido pelo id no serviço de pedido

    //conectar com pedido
}
