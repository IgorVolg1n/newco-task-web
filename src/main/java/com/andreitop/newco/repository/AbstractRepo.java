package com.andreitop.newco.repository;

import com.andreitop.newco.dto.AbstractEntity;

import java.util.List;

public interface AbstractRepo<T extends AbstractEntity> {

    public List<T> findAll();

    public T findById(final Long id);

    public void save(final T entity);

    public void delete(final Long id);

    public void update(final T newEntity);
}