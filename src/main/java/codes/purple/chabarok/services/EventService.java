package codes.purple.chabarok.services;

import codes.purple.chabarok.models.Event;
import codes.purple.chabarok.models.ImageFile;
import codes.purple.chabarok.repositories.EventRepository;
import codes.purple.chabarok.repositories.ImageRepository;
import codes.purple.chabarok.services.exceptions.EventNotFoundException;
import codes.purple.chabarok.services.exceptions.ImageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ImageRepository imageRepository;

    public void updateEvent(Event event) {
        eventRepository.save(event);
    }

    public Event findById(Long eventId) throws EventNotFoundException {
        return eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);
    }

    public List<Event> findAllEvent(){
        return eventRepository.findAll();
    }

    public void saveImage(Long eventId, MultipartFile image) throws IOException, EventNotFoundException {
        Event event = findById(eventId);
        ImageFile eventImage = event.getImage();
        eventImage = new ImageFile();
        event.setImage(eventImage);
        eventImage.setName(image.getName());
        eventImage.setType(image.getContentType());
        eventImage.setData(image.getBytes());
        imageRepository.save(eventImage);
        eventRepository.save(event);
    }

    public ImageFile getEventImage(Long eventId) throws ImageNotFoundException, EventNotFoundException {
        ImageFile imageFile = findById(eventId).getImage();
        if(imageFile.getData() == null) {
            throw new ImageNotFoundException();
        }
        return imageFile;
    }
}
