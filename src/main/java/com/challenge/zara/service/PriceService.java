package com.challenge.zara.service;

import com.challenge.zara.model.Price;
import com.challenge.zara.repository.PriceRepository;
import com.challenge.zara.utils.PriceServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {
    private final PriceRepository priceRepository;
    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> getPriceByDateAndProductIdAndChainId(LocalDateTime date, Long productId, Long chainId) {
        try {
            List<Price> prices = priceRepository.findByProductIdAndBrandId(productId, chainId);
            return prices.stream()
                    .filter(price -> date.isAfter(price.getStartDate()) || date.isEqual(price.getStartDate()))
                    .filter(price -> date.isBefore(price.getEndDate()) || date.isEqual(price.getEndDate()))
                    .max(Comparator.comparingInt(Price::getPriority));
        } catch (Exception ex) {
            throw new PriceServiceException("An error occurred while retrieving the price", ex);
        }
    }
}
