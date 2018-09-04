package com.andreitop.newco.service;

import com.andreitop.newco.dto.AbstractEntity;

import java.util.List;

public interface AbstractService<T extends AbstractEntity> {

    public List<T> findAll();

    public T findById(Long id);

    public void save(T entity);

    public void delete(Long id);

    public void update(T newEntity);
}