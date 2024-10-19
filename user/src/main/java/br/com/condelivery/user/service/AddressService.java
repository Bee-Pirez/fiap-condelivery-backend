package br.com.condelivery.user.service;

import br.com.condelivery.user.dto.AddressDto;
import br.com.condelivery.user.dto.CondominiumDto;
import br.com.condelivery.user.dto.CondominiumRegisterDto;
import br.com.condelivery.user.exception.ResourceNotFoundException;
import br.com.condelivery.user.model.Address;
import br.com.condelivery.user.model.Condominium;
import br.com.condelivery.user.repository.AddressRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    // Método para criar e salvar um endereço
    public AddressDto createAddress(AddressDto addressDto) {
        // Verificar se já existe um endereço com o mesmo zipCode
        Optional<Address> existingAddress = addressRepository.findByZipCode(addressDto.getZipCode());

        if (existingAddress.isPresent()) {
            throw new IllegalArgumentException("Endereço com o CEP " + addressDto.getZipCode() + " já existe.");
        }

        // Converter AddressDto para a entidade Address
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setZipCode(addressDto.getZipCode());
        address.setNumber(addressDto.getNumber());

        // Salvar o endereço no banco de dados
        Address savedAddress = addressRepository.save(address);

        // Converter a entidade Address de volta para AddressDto
        AddressDto savedAddressDto = new AddressDto();
        savedAddressDto.setId(savedAddress.getId());
        savedAddressDto.setStreet(savedAddress.getStreet());
        savedAddressDto.setCity(savedAddress.getCity());
        savedAddressDto.setState(savedAddress.getState());
        savedAddressDto.setZipCode(savedAddress.getZipCode());
        savedAddressDto.setNumber(savedAddress.getNumber());

        return savedAddressDto; // Retornar o DTO com o ID gerado
    }

    public AddressDto updateAddress(Long id, AddressDto addressDto) {
        // Busca o endereço pelo ID
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));

        // Atualiza os campos que não são nulos
        if (addressDto.getStreet() != null) {
            existingAddress.setStreet(addressDto.getStreet());
        }
        if (addressDto.getCity() != null) {
            existingAddress.setCity(addressDto.getCity());
        }
        if (addressDto.getState() != null) {
            existingAddress.setState(addressDto.getState());
        }
        if (addressDto.getZipCode() != null) {
            existingAddress.setZipCode(addressDto.getZipCode());
        }
        if (addressDto.getNumber() != null) {
            existingAddress.setNumber(addressDto.getNumber());
        }

        // Salva o endereço atualizado no banco de dados
        Address updatedAddress = addressRepository.save(existingAddress);

        // Mapeia a entidade Address de volta para AddressDto
        return new AddressDto(
                updatedAddress.getId(),
                updatedAddress.getStreet(),
                updatedAddress.getCity(),
                updatedAddress.getState(),
                updatedAddress.getZipCode(),
                updatedAddress.getNumber()
        );
    }

}
