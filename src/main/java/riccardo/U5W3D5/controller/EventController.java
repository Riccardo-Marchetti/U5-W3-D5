package riccardo.U5W3D5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardo.U5W3D5.entities.Event;
import riccardo.U5W3D5.payloads.EventDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/event")
public class EventController {

    @GetMapping
    private List<Event> getAllEvent (){
        return null;
    }

    @GetMapping ("/{eventId}")
    private Event getEventById (@PathVariable UUID eventId){
        return null;
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    private Event saveEvent (@RequestBody EventDTO body){
        return null;
    }

    @PutMapping ("/{eventId}")
    private Event findEventAndUpdate (@PathVariable UUID eventId, @RequestBody EventDTO body ){
        return null;
    }
    @DeleteMapping ("/{eventId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    private void deleteEvent (@PathVariable UUID eventId){

    }

}
