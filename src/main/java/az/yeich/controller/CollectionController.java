package az.yeich.controller;


import az.yeich.dto.CollectionDto;
import az.yeich.dto.RestaurantCardDto;
import az.yeich.security.JwtUser;
import az.yeich.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("collection")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCollection(@RequestBody CollectionDto collectionDto, JwtUser jwtUser) {
        collectionService.createCollection(jwtUser, collectionDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletCollection(@RequestBody CollectionDto collectionDto) {
        collectionService.deleteCollection(collectionDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCollection(@RequestBody CollectionDto collectionDto) {
        collectionService.updateCollection(collectionDto);
    }


    @GetMapping
    public List<CollectionDto> getAllCollection(JwtUser jwtUser) {
        return collectionService.getAllCollection(jwtUser);
    }

    @PostMapping("{collectionId}/add/{restaurantId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRestaurantToCollection(@PathVariable("collectionId") String collectionId, @PathVariable("restaurantId") String restaurantId) {
        collectionService.addRestaurantToCollection(restaurantId, collectionId);
    }

    @DeleteMapping("{collectionId}/remove/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRetaurantFromCollection(@PathVariable("restaurantId") String restaurantId, @PathVariable("collectionId") String collectionId) {
        collectionService.removeRetaurantFromCollection(restaurantId, collectionId);
    }

    @GetMapping("/{id}")
    public List<RestaurantCardDto> getAllrestaurantFromCollection(@PathVariable("id") String collectionId) {
        return collectionService.getAllrestaurantFromCollection(collectionId);
    }
}
