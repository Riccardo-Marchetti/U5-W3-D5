package riccardo.U5W3D5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.U5W3D5.entities.Event;
import riccardo.U5W3D5.entities.Users;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersDAO extends JpaRepository<Users, UUID> {
    Optional<Users> findByEmail (String email);
}
