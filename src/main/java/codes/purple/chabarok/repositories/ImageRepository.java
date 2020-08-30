package codes.purple.chabarok.repositories;

import codes.purple.chabarok.models.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageFile, Long> {
}
