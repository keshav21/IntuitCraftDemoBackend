package com.intuit.controllers;

import com.intuit.models.Photographers;
import com.intuit.models.PhotographersEvents;
import com.intuit.services.PhotographersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PhotographersControllerTest {

    @Mock
    private PhotographersService photographersService;

    @InjectMocks
    private PhotographersController photographersController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPhotographers() {
        Photographers photographer1 = new Photographers();
        Photographers photographer2 = new Photographers();
        List<Photographers> mockPhotographersList = Arrays.asList(photographer1, photographer2);

        when(photographersService.getAllPhotographers()).thenReturn(mockPhotographersList);

        ResponseEntity<List<Photographers>> response = photographersController.getAllPhotographers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPhotographersList, response.getBody());

        verify(photographersService, times(1)).getAllPhotographers();
    }

    @Test
    public void testGetPhotographerById() {
        String photographerID = "1";
        Photographers photographer = new Photographers();

        when(photographersService.getPhotographerById(photographerID)).thenReturn(photographer);

        ResponseEntity<Photographers> response = photographersController.getPhotographerById(photographerID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(photographer, response.getBody());

        verify(photographersService, times(1)).getPhotographerById(photographerID);
    }

    @Test
    public void testGetPhotographersByEvents() {
        String eventType = "wedding";
        PhotographersEvents event1 = new PhotographersEvents();
        PhotographersEvents event2 = new PhotographersEvents();
        List<PhotographersEvents> mockEventsList = Arrays.asList(event1, event2);

        when(photographersService.getPhotographersByEvents(eventType)).thenReturn(mockEventsList);

        ResponseEntity<List<PhotographersEvents>> response = photographersController.getPhotographersByEvents(eventType);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockEventsList, response.getBody());

        verify(photographersService, times(1)).getPhotographersByEvents(eventType);
    }
}
