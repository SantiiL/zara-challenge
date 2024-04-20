package com.challenge.zara.service;

import com.challenge.zara.model.Price;
import com.challenge.zara.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPriceByDateAndProductIdAndChainId_Test1() {
        // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        Long productId = 35455L;
        Long chainId = 1L;
        Price price = new Price(1L, chainId, LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"), 1, productId, 0, BigDecimal.valueOf(35.50), "EUR");
        List<Price> prices = new ArrayList<>();
        prices.add(price);
        when(priceRepository.findByProductIdAndBrandId(anyLong(), anyLong())).thenReturn(prices);

        Optional<Price> result = priceService.getPriceByDateAndProductIdAndChainId(date, productId, chainId);

        assertEquals(Optional.of(price), result);
    }

    @Test
    public void testGetPriceByDateAndProductIdAndChainId_Test2() {
        // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        LocalDateTime date = LocalDateTime.parse("2020-06-14T16:00:00");
        Long productId = 35455L;
        Long chainId = 1L;
        Price price = new Price(2L, chainId, LocalDateTime.parse("2020-06-14T15:00:00"),
                LocalDateTime.parse("2020-06-14T18:30:00"), 2, productId, 1, BigDecimal.valueOf(25.45), "EUR");
        List<Price> prices = new ArrayList<>();
        prices.add(price);
        when(priceRepository.findByProductIdAndBrandId(anyLong(), anyLong())).thenReturn(prices);

        Optional<Price> result = priceService.getPriceByDateAndProductIdAndChainId(date, productId, chainId);

        assertEquals(Optional.of(price), result);
    }

    @Test
    public void testGetPriceByDateAndProductIdAndChainId_Test3() {
        // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        LocalDateTime date = LocalDateTime.parse("2020-06-14T21:00:00");
        Long productId = 35455L;
        Long chainId = 1L;
        Price price = new Price(3L, chainId, LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"), 3, productId, 1, BigDecimal.valueOf(30.50), "EUR");
        List<Price> prices = new ArrayList<>();
        prices.add(price);
        when(priceRepository.findByProductIdAndBrandId(anyLong(), anyLong())).thenReturn(prices);

        Optional<Price> result = priceService.getPriceByDateAndProductIdAndChainId(date, productId, chainId);

        assertEquals(Optional.of(price), result);
    }

    @Test
    public void testGetPriceByDateAndProductIdAndChainId_Test4() {
        // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
        LocalDateTime date = LocalDateTime.parse("2020-06-15T10:00:00");
        Long productId = 35455L;
        Long chainId = 1L;
        Price price = new Price(4L, chainId, LocalDateTime.parse("2020-06-15T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"), 4, productId, 1, BigDecimal.valueOf(38.95), "EUR");
        List<Price> prices = new ArrayList<>();
        prices.add(price);
        when(priceRepository.findByProductIdAndBrandId(anyLong(), anyLong())).thenReturn(prices);

        Optional<Price> result = priceService.getPriceByDateAndProductIdAndChainId(date, productId, chainId);

        assertEquals(Optional.of(price), result);
    }

    @Test
    public void testGetPriceByDateAndProductIdAndChainId_Test5() {
        // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
        LocalDateTime date = LocalDateTime.parse("2020-06-16T21:00:00");
        Long productId = 35455L;
        Long chainId = 1L;
        Price price1 = new Price(1L, chainId, LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"), 1, productId, 0, BigDecimal.valueOf(35.50), "EUR");
        Price price2 = new Price(4L, chainId, LocalDateTime.parse("2020-06-15T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"), 4, productId, 1, BigDecimal.valueOf(38.95), "EUR");
        List<Price> prices = new ArrayList<>();
        prices.add(price1);
        prices.add(price2);
        when(priceRepository.findByProductIdAndBrandId(anyLong(), anyLong())).thenReturn(prices);

        Optional<Price> result = priceService.getPriceByDateAndProductIdAndChainId(date, productId, chainId);

        assertEquals(Optional.of(price2), result);
    }

}
