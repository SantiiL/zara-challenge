package com.challenge.zara.service;

import com.challenge.zara.model.Price;
import com.challenge.zara.repository.PriceRepository;
import com.challenge.zara.utils.PriceNotFoundException;
import com.challenge.zara.utils.PriceServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
            // Se filtra por fecha, producto e identificador de cadena, y se ordena por prioridad
            List<Price> prices = priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                    productId, chainId, date, LocalDateTime.now());

            // Se selecciona el precio con mayor prioridad
            return prices.stream()
                    .filter(price -> date.isAfter(price.getStartDate()) || date.isEqual(price.getStartDate()))
                    .filter(price -> date.isBefore(price.getEndDate()) || date.isEqual(price.getEndDate()))
                    .max(Comparator.comparingInt(Price::getPriority));
        } catch (DataIntegrityViolationException e) {
            throw new PriceNotFoundException("Price not found for the given parameters");
        } catch (Exception e) {
            throw new PriceServiceException("An error occurred while retrieving the price", e);
        }
    }
}
