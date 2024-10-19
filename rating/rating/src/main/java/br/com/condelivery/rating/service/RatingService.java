package br.com.condelivery.rating.service;

import br.com.condelivery.rating.dto.RatingDto;
import br.com.condelivery.rating.model.Rating;
import br.com.condelivery.rating.repository.RatingRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    private RatingRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public RatingDto createRating(@Valid RatingDto ratingDto) {

        Rating rating = modelMapper.map(ratingDto, Rating.class);
        Rating savedRating = repository.save(rating);

        return modelMapper.map(savedRating, RatingDto.class);
    }

    public List<RatingDto> getRatingByResident(Long residentId) {
        List<Rating> ratings = repository.findByResidentId(residentId);

        return ratings.stream()
                .map(rating -> modelMapper.map(rating, RatingDto.class))
                .collect(Collectors.toList());
    }

    //listar todos as avalicaoes de todos os residents de um condominio
    //pegar pedido pelo id no serviço de pedido

    //conectar com pedido ou user
}
