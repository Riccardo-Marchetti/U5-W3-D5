package riccardo.U5W3D5.entities;

import jakarta.persistence.*;
import lombok.*;
import riccardo.U5W3D5.enums.Role;

import java.util.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue
    @Setter (AccessLevel.NONE)
    private UUID id;
    private String username;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String avatar;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "users_event",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> eventiPrenotati = new HashSet<>();

    public Users(String username, String name, String surname, String email, String password, Role role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }


}
