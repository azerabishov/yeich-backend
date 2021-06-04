package az.yeich.service;

import az.yeich.dto.OfferDto;

import java.util.List;

public interface OfferService {
    List<OfferDto> getAllOffers(String language);
 }
