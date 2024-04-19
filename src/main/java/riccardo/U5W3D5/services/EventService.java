package riccardo.U5W3D5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardo.U5W3D5.entities.Event;
import riccardo.U5W3D5.entities.Users;
import riccardo.U5W3D5.exceptions.BadRequestException;
import riccardo.U5W3D5.exceptions.NotFoundException;
import riccardo.U5W3D5.payloads.EventDTO;
import riccardo.U5W3D5.payloads.UsersDTO;
import riccardo.U5W3D5.repositories.EventDAO;
import riccardo.U5W3D5.repositories.UsersDAO;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventDAO eventDAO;

    public List<Event> getAllEvent (){
        return eventDAO.findAll();
    }

    public Event getEventById (UUID eventId){
        return eventDAO.findById(eventId).orElseThrow(() -> new NotFoundException(eventId));
    }

    public Event saveEvent (EventDTO body){
        Event event = new Event(body.title(), body.description(), body.date(), body.place(), body.numberOfPlacesAvailable());
        return this.eventDAO.save(event);
    }

    public Event findEventAndUpdate (EventDTO body, UUID eventId){
        Event event = this.eventDAO.findById(eventId).orElseThrow(() -> new NotFoundException(eventId));
        event.setTitle(body.title());
        event.setDescription(body.description());
        event.setDate(body.date());
        event.setPlace(body.place());
        event.setNumberOfPlacesAvailable(body.numberOfPlacesAvailable());
        return this.eventDAO.save(event);
    }

    public void deleteEvent (UUID eventId){
        Event event = this.eventDAO.findById(eventId).orElseThrow(() -> new NotFoundException(eventId));
        this.eventDAO.delete(event);
    }
}
