package lab3.galerie.galleries.service;

import lab3.galerie.galleries.entity.ArtGallery;
import lab3.galerie.galleries.event.repository.EventRepository;
import lab3.galerie.galleries.repository.ArtGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArtGalleryService {
    private ArtGalleryRepository repository;
    private EventRepository eventRepository;

    @Autowired
    public ArtGalleryService(ArtGalleryRepository repository,EventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository=eventRepository;
    }

    public Optional<ArtGallery> find(String adress) {
        return repository.findById(adress);
    }

    public List<ArtGallery> findAll() {
        return repository.findAll();
    }

    @Transactional
    public ArtGallery create(ArtGallery artGallery) {
        ArtGallery newArtGallery=repository.save(artGallery);
        eventRepository.create(artGallery);
        return newArtGallery;
    }

    public ArtGallery save(ArtGallery artGallery) {
        return repository.save(artGallery);
    }

    @Transactional
    public void delete(String adress) {
        eventRepository.delete(repository.findById(adress).orElseThrow());
        repository.delete(repository.findById(adress).orElseThrow());
    }

    public void update(ArtGallery artGallery) {
        repository.save(artGallery);
    }

}