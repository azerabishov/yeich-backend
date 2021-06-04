package az.yeich.controller;

import az.yeich.dto.OfferDto;
import az.yeich.service.OfferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("offer")
public class OfferController {
    private OfferService offerService;

    @GetMapping
    public List<OfferDto> getAllOffers(@RequestHeader("accept-language") String language) {
        return offerService.getAllOffers(language);
    }


}
