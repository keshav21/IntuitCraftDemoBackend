package com.intuit.components;

import com.intuit.models.Photographers;
import com.intuit.models.PhotographersEvents;
import com.intuit.models.repository.PhotographersEventsRepository;
import com.intuit.models.repository.PhotographersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PhotographersComponentTest {

    @Mock
    private PhotographersRepository photographersRepository;

    @Mock
    private PhotographersEventsRepository photographersEventsRepository;

    @InjectMocks
    private PhotographersComponent photographersComponent;

    @Test
    public void testGetAllPhotographers() {
        Photographers photographer1 = new Photographers();
        Photographers photographer2 = new Photographers();
        List<Photographers> mockPhotographersList = Arrays.asList(photographer1, photographer2);

        when(photographersRepository.findAll()).thenReturn(mockPhotographersList);

        List<Photographers> result = photographersComponent.getAllPhotographers();

        assertEquals(2, result.size());
        assertEquals(photographer1, result.get(0));
        assertEquals(photographer2, result.get(1));

        verify(photographersRepository, times(1)).findAll();
    }

    @Test
    public void testGetPhotographerById() {
        Integer id = 1;
        Photographers photographer = new Photographers();

        when(photographersRepository.findById(id)).thenReturn(Optional.of(photographer));

        Photographers result = photographersComponent.getPhotographerById(String.valueOf(id));

        assertNotNull(result);
        assertEquals(photographer, result);

        verify(photographersRepository, times(2)).findById(id);
    }

    @Test
    public void testGetPhotographerById_NotFound() {
        Integer id = 1;

        when(photographersRepository.findById(id)).thenReturn(Optional.empty());

        Photographers result = photographersComponent.getPhotographerById(String.valueOf(id));

        assertNull(result);

        verify(photographersRepository, times(1)).findById(id);
    }

    @Test
    public void testGetPhotographersByEvents() {
        String eventType = "wedding";
        PhotographersEvents event1 = new PhotographersEvents();
        PhotographersEvents event2 = new PhotographersEvents();
        List<PhotographersEvents> mockEventsList = Arrays.asList(event1, event2);

        when(photographersEventsRepository.findByEventType(eventType)).thenReturn(mockEventsList);

        List<PhotographersEvents> result = photographersComponent.getPhotographersByEvents(eventType);

        assertEquals(2, result.size());
        assertEquals(event1, result.get(0));
        assertEquals(event2, result.get(1));

        verify(photographersEventsRepository, times(1)).findByEventType(eventType);
    }
}
