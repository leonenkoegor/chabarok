package codes.purple.chabarok.repositories;

import codes.purple.chabarok.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
