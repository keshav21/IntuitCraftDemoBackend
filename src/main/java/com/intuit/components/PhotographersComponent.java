package com.intuit.components;


import com.intuit.models.Photographers;
import com.intuit.models.PhotographersEvents;
import com.intuit.models.repository.PhotographersEventsRepository;
import com.intuit.models.repository.PhotographersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PhotographersComponent {

    @Autowired
    PhotographersRepository photographersRepository;

    @Autowired
    PhotographersEventsRepository photographersEventsRepository;

    public List<Photographers> getAllPhotographers(){
       return photographersRepository.findAll();
    }


    public List<Photographers> getAllPhotographersByEmail(String email){
        System.out.println("i am here"+ email);
        return photographersRepository.findAllPhotographersByEmail(email);
    }

    public Photographers getPhotographerById (String idValue){
        Integer id = Integer.parseInt(idValue);
        if( photographersRepository.findById(id).isPresent()){
            return photographersRepository.findById(id).get();
        }
        return null;
    }

    public List<PhotographersEvents> getPhotographersByEvents (String eventType){
        return photographersEventsRepository.findByEventType(eventType);
    }
}
