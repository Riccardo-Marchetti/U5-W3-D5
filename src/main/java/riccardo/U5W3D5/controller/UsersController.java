package riccardo.U5W3D5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardo.U5W3D5.entities.Event;
import riccardo.U5W3D5.payloads.EventDTO;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping ("/user")
public class UsersController {
    @GetMapping
    private List<Event> getAllEvent (){
        return null;
    }

    @GetMapping ("/{userId}")
    private Event getEventById (@PathVariable UUID userId){
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Event saveEvent (@RequestBody EventDTO body){
        return null;
    }

    @PutMapping ("/{userId}")
    private Event findEventAndUpdate (@PathVariable UUID userId, @RequestBody EventDTO body ){
        return null;
    }
    @DeleteMapping ("/{userId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    private void deleteEvent (@PathVariable UUID eventId){

    }
}
