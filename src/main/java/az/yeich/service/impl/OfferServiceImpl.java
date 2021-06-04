package az.yeich.service.impl;

import az.yeich.dto.OfferDto;
import az.yeich.mapper.CustomMapper;
import az.yeich.model.Offer;
import az.yeich.respository.OfferRepository;
import az.yeich.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;

    private CustomMapper customMapper;
    
    @Override
    public List<OfferDto> getAllOffers(String language) {
        List<Offer> offers = offerRepository.findAll();
        return customMapper.offerListToOfferDtoList(offers, language);
    }
}
