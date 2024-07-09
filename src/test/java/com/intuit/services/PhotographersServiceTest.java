package com.intuit.services;

import com.intuit.components.PhotographersComponent;
import com.intuit.models.Photographers;
import com.intuit.models.PhotographersEvents;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PhotographersServiceTest {

    @Mock
    private PhotographersComponent photographersComponent;

    @InjectMocks
    private PhotographersService photographersService;

    @Test
    public void testGetAllPhotographers() {
        Photographers photographer1 = new Photographers();
        Photographers photographer2 = new Photographers();
        List<Photographers> mockPhotographersList = Arrays.asList(photographer1, photographer2);

        when(photographersComponent.getAllPhotographers()).thenReturn(mockPhotographersList);

        List<Photographers> result = photographersService.getAllPhotographers();

        assertEquals(2, result.size());
        assertEquals(photographer1, result.get(0));
        assertEquals(photographer2, result.get(1));

        verify(photographersComponent, times(1)).getAllPhotographers();
    }

    @Test
    public void testGetPhotographerById() {
        String idValue = "1";
        Integer id = Integer.parseInt(idValue);
        Photographers photographer = new Photographers();

        when(photographersComponent.getPhotographerById(idValue)).thenReturn(photographer);

        Photographers result = photographersService.getPhotographerById(idValue);

        assertNotNull(result);
        assertEquals(photographer, result);

        verify(photographersComponent, times(1)).getPhotographerById(idValue);
    }

    @Test
    public void testGetPhotographersByEvents() {
        String eventType = "wedding";
        PhotographersEvents event1 = new PhotographersEvents();
        PhotographersEvents event2 = new PhotographersEvents();
        List<PhotographersEvents> mockEventsList = Arrays.asList(event1, event2);

        when(photographersComponent.getPhotographersByEvents(eventType)).thenReturn(mockEventsList);

        List<PhotographersEvents> result = photographersService.getPhotographersByEvents(eventType);

        assertEquals(2, result.size());
        assertEquals(event1, result.get(0));
        assertEquals(event2, result.get(1));

        verify(photographersComponent, times(1)).getPhotographersByEvents(eventType);
    }
}
