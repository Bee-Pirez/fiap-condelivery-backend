package br.com.condelivery.rating.controller;

import br.com.condelivery.rating.dto.RatingDto;
import br.com.condelivery.rating.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;


    @PostMapping("/create")
    public ResponseEntity<RatingDto> registerOrder(@Valid @RequestBody RatingDto ratingDtoDto) {
        RatingDto newRating = ratingService.createRating(ratingDtoDto);
        return ResponseEntity.ok(newRating);
    }


    @GetMapping("/resident/{residentId}")
    public ResponseEntity<List<RatingDto>> getOrdersByResident(@PathVariable Long residentId) {
        List<RatingDto> rating = ratingService.getRatingByResident(residentId);
        return ResponseEntity.ok(rating);
    }
}
