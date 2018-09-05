package com.andreitop.newco.controller;

import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.repository.TripRepository;
import com.andreitop.newco.service.TripService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TripService.class)
public class TripServiceTest {

    @MockBean
    TripRepository tripRepository;

    TripService tripService;

    @Test
    public void whenFindAll_returnList() {
        tripService = new TripService(tripRepository);

        when(tripRepository.findAll()).thenReturn(new ArrayList<TripDto>());
        List<TripDto> tripDtoList = tripService.findAll();

        assertNotNull(tripDtoList);
        assertEquals(0, tripDtoList.size());

        verify(tripRepository, times(1)).findAll();
        verifyNoMoreInteractions(tripRepository);
    }

    @Test
    public void whenFindById_returnTripDto() {
        tripService = new TripService(tripRepository);

        TripDto tripDto = new TripDto();
        tripDto.setId(2L);
        tripDto.setOrigin("LED");
        tripDto.setDestination("MOW");
        tripDto.setPrice(5464);

        when(tripRepository.findById(2L)).thenReturn(tripDto);

        TripDto testTrip = tripService.findById(2L);
        assertTrue(testTrip instanceof TripDto);
        assertEquals(new Long(2L), testTrip.getId());
    }

    @Test
    public void whenSaveTrip_createNewTrip() {
        tripService = new TripService(tripRepository);

        TripDto tripDto = new TripDto();
        tripDto.setId(2L);
        tripDto.setOrigin("LED");
        tripDto.setDestination("MOW");
        tripDto.setPrice(5464);

        doNothing().when(tripRepository).save(tripDto);
        tripService.save(tripDto);

        verify(tripRepository, times(1)).save(tripDto);
        verifyNoMoreInteractions(tripRepository);
    }
}
