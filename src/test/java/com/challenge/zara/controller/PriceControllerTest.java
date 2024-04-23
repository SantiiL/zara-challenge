package com.challenge.zara.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import com.challenge.zara.controller.PriceController;
import com.challenge.zara.model.Price;
import com.challenge.zara.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PriceControllerTest {

    private PriceService priceService;
    private PriceController priceController;

    @BeforeEach
    public void setUp() {
        priceService = mock(PriceService.class);
        priceController = new PriceController(priceService);
    }

    @Test
    public void testGetPriceScenario1() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        Price mockPrice = new Price(1L, 1L, LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), 1, 35455L, 0, new BigDecimal("35.50"), "EUR");

        when(priceService.getPriceByDateAndProductIdAndChainId(date, productId, brandId)).thenReturn(Optional.of(mockPrice));

        ResponseEntity<Price> response = priceController.getPrice(date, productId, brandId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPrice, response.getBody());
    }


    @Test
    public void testGetPriceScenario2() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        // MockPrice para el escenario 2: precio con prioridad 1
        Price mockPrice = new Price(1L, 1L, LocalDateTime.of(2020, 6, 14, 15, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30), 2, 35455L, 1, new BigDecimal("25.45"), "EUR");

        when(priceService.getPriceByDateAndProductIdAndChainId(date, productId, brandId)).thenReturn(Optional.of(mockPrice));

        ResponseEntity<Price> response = priceController.getPrice(date, productId, brandId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPrice, response.getBody());
    }

    @Test
    public void testGetPriceScenario3() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        // MockPrice para el escenario 3: precio con prioridad 1
        Price mockPrice = new Price(2L, 1L, LocalDateTime.of(2020, 6, 15, 16, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), 4, 35455L, 1, new BigDecimal("38.95"), "EUR");

        when(priceService.getPriceByDateAndProductIdAndChainId(date, productId, brandId)).thenReturn(Optional.of(mockPrice));

        ResponseEntity<Price> response = priceController.getPrice(date, productId, brandId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPrice, response.getBody());
    }

    @Test
    public void testGetPriceScenario4() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        // MockPrice para el escenario 4: precio con prioridad 3
        Price mockPrice = new Price(3L, 1L, LocalDateTime.of(2020, 6, 15, 0, 0),
                LocalDateTime.of(2020, 6, 15, 11, 0), 3, 35455L, 1, new BigDecimal("30.50"), "EUR");

        when(priceService.getPriceByDateAndProductIdAndChainId(date, productId, brandId)).thenReturn(Optional.of(mockPrice));

        ResponseEntity<Price> response = priceController.getPrice(date, productId, brandId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPrice, response.getBody());
    }

    @Test
    public void testGetPriceScenario5() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        // MockPrice para el escenario 5: precio con prioridad 4
        Price mockPrice = new Price(4L, 1L, LocalDateTime.of(2020, 6, 15, 16, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), 4, 35455L, 1, new BigDecimal("38.95"), "EUR");

        when(priceService.getPriceByDateAndProductIdAndChainId(date, productId, brandId)).thenReturn(Optional.of(mockPrice));

        ResponseEntity<Price> response = priceController.getPrice(date, productId, brandId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPrice, response.getBody());
    }
}

