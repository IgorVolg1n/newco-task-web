package com.andreitop.newco.service;

import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService implements AbstractService<TripDto> {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public List<TripDto> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public TripDto findById(Long id) {
        return tripRepository.findById(id);
    }

    @Override
    public void save(TripDto trip) {
        tripRepository.save(trip);
    }

    @Override
    public void delete(Long id) {
        tripRepository.delete(id);
    }

    @Override
    public void update(TripDto newTrip) {
        tripRepository.update(newTrip);
    }
}
