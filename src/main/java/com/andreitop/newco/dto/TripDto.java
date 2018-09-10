package com.andreitop.newco.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class TripDto extends AbstractEntity {

    private static final long serialVersionUID = 5914366185889783660L;

    @Pattern(regexp = "(^[A-Z]{3}$)", message = "IATA airport code should be right format")
    private String origin;

    @Pattern(regexp = "(^[A-Z]{3}$)", message = "IATA airport code should be right format")
    private String destination;

    @Min(0)
    @Max(1000000)
    private Integer price;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
