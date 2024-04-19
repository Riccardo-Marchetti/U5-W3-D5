package riccardo.U5W3D5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import riccardo.U5W3D5.enums.Role;

import java.util.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"password", "role", "authorities", "credentialsNonExpired", "accountNonExpired", "accountNonLocked", "enabled"})
public class Users implements UserDetails {
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
    @JsonIgnore
    private Set<Event> event = new HashSet<>();

    public Users(String username, String name, String surname, String email, String password, Role role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = Role.NORMAL_USER;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
