package com.intuit.models.repository;

import com.intuit.models.Photographers;
import com.intuit.models.PhotographersEvents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PhotographersEventsRepository extends JpaRepository<PhotographersEvents, UUID> {
    List<PhotographersEvents> findByEventType(String eventType);
}