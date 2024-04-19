package riccardo.U5W3D5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardo.U5W3D5.entities.Event;
import riccardo.U5W3D5.exceptions.BadRequestException;
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
    public Page<Event> getAllEvent (@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "10") int size, @RequestParam (defaultValue = "username") String sortBy){
        return this.eventService.getAllEvent(page, size, sortBy);
    }

    @GetMapping ("/{eventId}")
    public Event getEventById (@PathVariable UUID eventId){
        return this.eventService.getEventById(eventId);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('EVENT_ORGANIZER')")
    public Event saveEvent (@RequestBody @Validated EventDTO body, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.eventService.saveEvent(body);
    }

    @PutMapping ("/{eventId}")
    @PreAuthorize("hasAuthority('EVENT_ORGANIZER')")
    public Event findEventAndUpdate (@PathVariable UUID eventId, @RequestBody @Validated EventDTO body, BindingResult validation ){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.eventService.findEventAndUpdate(body, eventId);
    }
    @DeleteMapping ("/{eventId}")
    @PreAuthorize("hasAuthority('EVENT_ORGANIZER')")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteEvent (@PathVariable UUID eventId){
        this.eventService.deleteEvent(eventId);
    }

    @PostMapping ("/{eventId}/prenotare")
    @ResponseStatus (HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('NORMAL_USER')")
    public ResponseEntity<String> reserveSeat (@PathVariable UUID eventId){
        boolean prenotazioneRiuscita = eventService.prenotaPosto(eventId);
        if (prenotazioneRiuscita) {
            return ResponseEntity.ok("Prenotazione avvenuta con successo!");
        } else {
            return ResponseEntity.badRequest().body("Impossibile effettuare la prenotazione per questo evento. Posti esauriti.");
        }

    }
}
