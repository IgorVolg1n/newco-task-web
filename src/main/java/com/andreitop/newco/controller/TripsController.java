package com.andreitop.newco.controller;

import com.andreitop.newco.common.ApiConstant;
import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.dto.ValidationErrorDto;
import com.andreitop.newco.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Validated
@RestController
@RequestMapping(ApiConstant.API_V_1 + "/trips")
public class TripsController implements AbstractController<TripDto> {

    private final TripService tripService;

    @Autowired
    Validator validator;
    private MessageSource messageSource;

    @Autowired
    public TripsController(TripService tripService, MessageSource messageSource) {
        this.tripService = tripService;
        this.messageSource = messageSource;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TripDto> findAll() {
        return tripService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TripDto findById(@PathVariable("id") final Long id) {
        return tripService.findById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody final TripDto trip) {
        tripService.save(trip);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        tripService.delete(id);
    }

    @Override
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody final TripDto newTrip) {
        tripService.update(newTrip);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDto processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private ValidationErrorDto processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDto dto = new ValidationErrorDto();
        for (FieldError fieldError : fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
        return dto;
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }
        return localizedErrorMessage;
    }
}
