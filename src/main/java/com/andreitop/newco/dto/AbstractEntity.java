package com.andreitop.newco.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AbstractEntity implements Serializable {

    @NotNull
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
