package riccardo.U5W3D5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardo.U5W3D5.entities.Event;

import java.util.UUID;

@Repository
public interface EventDAO extends JpaRepository<Event, UUID> {

}
