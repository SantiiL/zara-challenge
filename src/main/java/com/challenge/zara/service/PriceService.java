package com.challenge.zara.service;

import com.challenge.zara.controller.PriceController;
import com.challenge.zara.model.Price;
import com.challenge.zara.repository.PriceRepository;
import com.challenge.zara.utils.PriceNotFoundException;
import com.challenge.zara.utils.PriceServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(PriceService.class);

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
            Optional<Price> selectedPrice = prices.stream().max(Comparator.comparingInt(Price::getPriority));

            return selectedPrice;
        } catch (Exception e) {
            // Registramos un error pero no lanzamos una excepción
            logger.error("An unexpected error occurred:", e);
            return Optional.empty();
        }
    }
}
