package riccardo.U5W3D5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardo.U5W3D5.entities.Event;
import riccardo.U5W3D5.payloads.EventDTO;
import riccardo.U5W3D5.services.EventService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    private List<Event> getAllEvent (){
        return this.eventService.getAllEvent();
    }

    @GetMapping ("/{eventId}")
    private Event getEventById (@PathVariable UUID eventId){
        return this.eventService.getEventById(eventId);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    private Event saveEvent (@RequestBody EventDTO body){
        return this.eventService.saveEvent(body);
    }

    @PutMapping ("/{eventId}")
    private Event findEventAndUpdate (@PathVariable UUID eventId, @RequestBody EventDTO body ){
        return this.eventService.findEventAndUpdate(body, eventId);
    }
    @DeleteMapping ("/{eventId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    private void deleteEvent (@PathVariable UUID eventId){
        this.eventService.deleteEvent(eventId);
    }

}
