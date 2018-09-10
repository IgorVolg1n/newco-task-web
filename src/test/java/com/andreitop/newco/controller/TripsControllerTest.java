package com.andreitop.newco.controller;

import com.andreitop.newco.common.ApiConstant;
import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.service.TripService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TripsController.class)
public class TripsControllerTest {

    private static final String TRIP_JSON = "{\"id\": \"1\" ,\"origin\": \"LED\" , \"destination\":\"MOW\", \"price\" : 12256}";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String API_URL = ApiConstant.API_V_1 + "/trips";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Validator validator;

    @MockBean
    private TripService tripService;

    @Test
    public void whenPostTrip_thenCreateTrip() throws Exception {
        mockMvc.perform(post(API_URL)
                .contentType(CONTENT_TYPE)
                .content(TRIP_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenTrips_whenGetTrips_thenReturnJsonArray() throws Exception {

        TripDto tripDto = new TripDto();
        tripDto.setId(1L);
        tripDto.setOrigin("MOW");
        tripDto.setDestination("LED");
        tripDto.setPrice(4232);

        List<TripDto> allTrips = Collections.singletonList(tripDto);
        given(tripService.findAll()).willReturn(allTrips);

        mockMvc.perform(get(API_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].origin", is("MOW")))
                .andExpect(jsonPath("$[0].destination", is("LED")))
                .andExpect(jsonPath("$[0].price", is(4232)));
    }

    @Test
    public void givenTrips_whenFindById_thenReturnJson() throws Exception {

        TripDto tripDto = new TripDto();
        tripDto.setId(2L);
        tripDto.setOrigin("LED");
        tripDto.setDestination("MOW");
        tripDto.setPrice(5464);

        List<TripDto> allTrips = Collections.singletonList(tripDto);
        given(tripService.findById(2L)).willReturn(allTrips.get(0));

        mockMvc.perform(get(API_URL + "/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(2)));
    }

    @Test
    public void whenDelete_thenStatusIsNoContent() throws Exception {

        mockMvc.perform(delete(API_URL + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void whenUpdateTrip_thenStatusIsOk() throws Exception {

        mockMvc.perform(put(API_URL)
                .contentType(CONTENT_TYPE)
                .content(TRIP_JSON))
                .andExpect(status().isOk());
    }
}