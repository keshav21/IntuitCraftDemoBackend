package com.intuit.controllers;


import com.intuit.models.Photographers;
import com.intuit.models.PhotographersEvents;
import com.intuit.services.PhotographersService;
import com.intuit.utils.ValidityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PhotographersController {

    @Autowired
    PhotographersService photographersService;

    @GetMapping("/photographers")
    public ResponseEntity<List<Photographers>> getAllPhotographers(@RequestParam(required = false) String email) {
        List<Photographers> photographers = null;
        if(ValidityUtil.isValidString(email)){
            photographers = photographersService.getAllPhotographersByEmail(email);
        }else {
            photographers = photographersService.getAllPhotographers();
        }
        if (photographers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(photographers);
        }
    }

    @GetMapping("/photographers/{photographerID}")
    public ResponseEntity<Photographers> getPhotographerById(@PathVariable String photographerID) {
        Photographers photographer = photographersService.getPhotographerById(photographerID);
        if (photographer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(photographer);
        }
    }

    @GetMapping("/photographers/event/{eventType}")
    public ResponseEntity<List<PhotographersEvents>> getPhotographersByEvents(@PathVariable String eventType) {
        List<PhotographersEvents> events = photographersService.getPhotographersByEvents(eventType);
        if (events.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(events);
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

