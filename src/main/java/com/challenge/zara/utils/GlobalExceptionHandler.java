package com.challenge.zara.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Manejo de excepciones genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        // Log del error
        logger.error("An unexpected error occurred:", ex);

        // Mensaje de error genérico
        String errorMessage = "An unexpected error occurred. Please try again later.";

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    // Manejo de PriceNotFoundException
    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<Object> handlePriceNotFoundException(PriceNotFoundException ex) {
        // Log del error
        logger.warn("Price not found:", ex);

        // Mensaje de error específico
        String errorMessage = "Price not found for the given parameters.";

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    // Manejo de PriceServiceException
    @ExceptionHandler(PriceServiceException.class)
    public ResponseEntity<Object> handlePriceServiceException(PriceServiceException ex) {
        // Log del error
        logger.error("An error occurred while retrieving the price:", ex);

        // Mensaje de error específico
        String errorMessage = "An error occurred while retrieving the price.";

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
    // Manejo de excepciones de base de datos (DataAccessException)

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException ex) {
        // Log del error
        logger.error("A database access error occurred:", ex);

        // Mensaje de error genérico
        String errorMessage = "A database access error occurred. Please try again later.";

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    // Manejo de excepciones de violación de integridad de datos (DataIntegrityViolationException)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Log del error
        logger.error("A data integrity violation occurred:", ex);

        // Mensaje de error específico
        String errorMessage = "A data integrity violation occurred. Please check your request parameters.";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    // Manejo de excepciones de falla de recurso de acceso a datos (DataAccessResourceFailureException)
    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<Object> handleDataAccessResourceFailureException(DataAccessResourceFailureException ex) {
        // Log del error
        logger.error("A data access resource failure occurred:", ex);

        // Mensaje de error específico
        String errorMessage = "A data access resource failure occurred. Please check your database connection.";

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    // Manejo de NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
        // Log del error
        logger.error("A NullPointerException occurred:", ex);

        // Mensaje de error específico
        String errorMessage = "A NullPointerException occurred. Please check your code for null references.";

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

}
