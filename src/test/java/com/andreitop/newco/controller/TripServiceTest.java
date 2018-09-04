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

    @Test
    public void whenFindAll_returnList() {
        when(tripRepository.findAll()).thenReturn(new ArrayList<TripDto>());
        List<TripDto> tripDtoList = tripRepository.findAll();
        assertNotNull(tripDtoList);
        verify(tripRepository, times(1)).findAll();
        verifyNoMoreInteractions(tripRepository);
        assertTrue(tripDtoList instanceof List<?>);
        assertEquals(0, tripDtoList.size());
    }

    @Test
    public void whenSaveTrip_createNewTrip() {

        TripDto tripDto = new TripDto();
        tripDto.setId(2L);
        tripDto.setOrigin("LED");
        tripDto.setDestination("MOW");
        tripDto.setPrice(5464);

    }
}
