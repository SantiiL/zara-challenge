package com.challenge.zara.controller;

import com.challenge.zara.model.Price;
import com.challenge.zara.service.PriceService;
import com.challenge.zara.utils.PriceNotFoundException;
import org.antlr.v4.runtime.misc.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class PriceController {

    private final PriceService priceService;

    private static final Logger logger = LoggerFactory.getLogger(PriceController.class);

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/prices")
    public ResponseEntity<Price> getPrice(
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam(value = "productId", required = false) Long productId,
            @RequestParam(value = "brandId", required = false) Long brandId) {
        logger.info("Received request for price with parameters: date={}, productId={}, brandId={}", date, productId, brandId);

        if (date == null || productId == null || brandId == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Price> price = priceService.getPriceByDateAndProductIdAndChainId(date, productId, brandId);

        return price.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
