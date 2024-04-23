package com.challenge.zara.controller;

import com.challenge.zara.model.Price;
import com.challenge.zara.service.PriceService;
import com.challenge.zara.utils.PriceNotFoundException;
import org.antlr.v4.runtime.misc.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class PriceController {

    private static final Logger logger = LoggerFactory.getLogger(PriceController.class);

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/prices")
    public ResponseEntity<Price> getPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("productId") @NotNull Long productId,
            @RequestParam("brandId") @NotNull Long brandId) {
        try {
            logger.info("Received request for price with parameters: date={}, productId={}, brandId={}", date, productId, brandId);
            Optional<Price> price = priceService.getPriceByDateAndProductIdAndChainId(date, productId, brandId);

            return price.map(ResponseEntity::ok)
                    .orElseThrow(() -> new PriceNotFoundException("Price not found for the given parameters"));
        } catch (PriceNotFoundException ex) {
            logger.warn("Price not found for the given parameters: {}", ex.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            logger.error("An unexpected error occurred while fetching price:", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
