package riccardo.U5W3D5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    @Setter (AccessLevel.NONE)
    private UUID id;

    private String title;

    private String description;

    private LocalDate date;

    private String place;

    private int numberOfPlacesAvailable;

    @ManyToMany(mappedBy = "event")
    @JsonIgnore
    private Set<Users> users = new HashSet<>();

    public Event(String title, String description, LocalDate date, String place, int numberOfPlacesAvailable) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.place = place;
        this.numberOfPlacesAvailable = numberOfPlacesAvailable;
    }
}
