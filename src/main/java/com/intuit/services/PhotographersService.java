package com.intuit.services;


import com.intuit.components.PhotographersComponent;
import com.intuit.models.Photographers;
import com.intuit.models.PhotographersEvents;
import com.intuit.models.repository.PhotographersEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotographersService {

    @Autowired
    PhotographersComponent photographersComponent;

    public List<Photographers> getAllPhotographers(){
        return photographersComponent.getAllPhotographers();
    }

    public List<Photographers> getAllPhotographersByEmail(String email){
        return photographersComponent.getAllPhotographersByEmail(email);
    }

    public Photographers getPhotographerById (String idValue){
        return photographersComponent.getPhotographerById(idValue);
    }

    public List<PhotographersEvents> getPhotographersByEvents (String eventType){
        return photographersComponent.getPhotographersByEvents(eventType);
    }


}
