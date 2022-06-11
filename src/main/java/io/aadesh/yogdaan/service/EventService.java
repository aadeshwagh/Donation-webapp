package io.aadesh.yogdaan.service;

import io.aadesh.yogdaan.entity.Event;
import io.aadesh.yogdaan.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public void saveEvent(Event event){
        eventRepository.save(event);
    }
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
    public List<Event> getAllEventsByUserId(String userId){
        return eventRepository.findAllByUserId(userId);
    }
    public Event getEventById(int id) throws Exception {
        Optional<Event> byId = eventRepository.findById(id);
        if(byId.isEmpty()){
            throw new Exception("Event with id "+id+" does not exits");
        }else{
            return byId.get();
        }

    }
}
