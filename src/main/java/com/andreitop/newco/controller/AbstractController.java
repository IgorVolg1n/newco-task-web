package com.andreitop.newco.controller;

import com.andreitop.newco.dto.AbstractEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public interface AbstractController<T extends AbstractEntity> {

    public List<T> findAll();

    public T findById(final Long id);

    public void create(@Valid final T entity);

    public void delete(final Long id);

    public void update(@Valid final T newEntity);

}
