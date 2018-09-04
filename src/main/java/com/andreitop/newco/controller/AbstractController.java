package com.andreitop.newco.controller;

import com.andreitop.newco.dto.AbstractEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface AbstractController<T extends AbstractEntity> {

    public List<T> findAll();

    public T findById(final Long id);

    public void create(final T entity);

    public void delete(final Long id);

    public void update(final T newEntity);

}
