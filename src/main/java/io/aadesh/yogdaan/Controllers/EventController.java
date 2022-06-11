package io.aadesh.yogdaan.Controllers;

import io.aadesh.yogdaan.entity.AppUser;
import io.aadesh.yogdaan.entity.Event;
import io.aadesh.yogdaan.entity.EventDonations;
import io.aadesh.yogdaan.repository.EventRepository;
import io.aadesh.yogdaan.service.DonationService;
import io.aadesh.yogdaan.service.EventService;
import io.aadesh.yogdaan.service.UserService;
import io.aadesh.yogdaan.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EventController {

    @Autowired
    Utility utility;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    DonationService donationService;

    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        AppUser user = userService.getAppUser(utility.getUserId());
        if(user==null){
            return "pre-registration";
        }
        List<EventDonations> userDonations = donationService.getEventsByUser(user.getUserId());
        List<Event> userOrganized = eventService.getAllEventsByUserId(user.getUserId());
        if(!userOrganized.isEmpty()){
            model.addAttribute("events",userOrganized);
        }

        model.addAttribute("donations",userDonations);

        return "dashboard";


    }
    @GetMapping("/getAllEvents")
    public String getAllEvents(Model model){
        if(userService.getAppUser(utility.getUserId())==null){
            return "pre-registration";
        }
        model.addAttribute("events",eventService.getAllEvents());
        return "events";
    }
    @GetMapping("/getEventById/{id}")
    public String getEventById(Model model, @PathVariable int id) throws Exception {
        if(userService.getAppUser(utility.getUserId())==null){
            return "pre-registration";
        }
        List<Integer> collect = donationService.getEventsByUser(utility.getUserId()).stream().map(EventDonations::getEventId).collect(Collectors.toList());
        if(collect.contains(id)){
            model.addAttribute("registered","registered");
        }
        Event eventById = eventService.getEventById(id);
        AppUser organization = userService.getAppUser(eventById.getUserId());
        model.addAttribute("organization",organization);
        model.addAttribute("event", eventById);
        return "event";
    }

    @GetMapping("/donate/{eventId}/{mode}")
    public String donate(@PathVariable int eventId , @PathVariable String mode ,Model model) throws Exception {
        if(userService.getAppUser(utility.getUserId())==null){
            return "pre-registration";
        }
        EventDonations eventDonations =new EventDonations();
        Event event = eventService.getEventById(eventId);
        eventDonations.setEventId(eventId);
        eventDonations.setDonationMode(mode);
        eventDonations.setUserId(utility.getUserId());
        eventDonations.setEventName(event.getEventName());
        eventDonations.setNgo(userService.getAppUser(utility.getUserId()).getName());

        donationService.saveEventDonations(eventDonations);
        return getDashboard(model);
    }

    @GetMapping("/getEventRegistration")
    public String getEventRegistration(){
        if(userService.getAppUser(utility.getUserId())==null){
            return "pre-registration";
        }
        return "event-registration";
    }

    @PostMapping("/registerEvent")
    public String registerEvent(@RequestBody MultiValueMap<String,String> formData,Model model){
        AppUser user  =userService.getAppUser(utility.getUserId());
        if(user==null){
            return "pre-registration";
        }
        Event event =new Event();
        event.setUserId(utility.getUserId());
        event.setEventName(formData.getFirst("eventName"));
        event.setAddress(formData.getFirst("address"));
        event.setCity(formData.getFirst("city"));
        event.setState(formData.getFirst("state"));
        event.setDescription(formData.getFirst("description"));
        event.setDate(LocalDate.parse(formData.getFirst("date")));
        event.setCategory(formData.getFirst("category"));


        eventService.saveEvent(event);


        return getAllEvents(model);

    }

}
