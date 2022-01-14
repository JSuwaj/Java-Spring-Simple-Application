package lab3.obrazy.galeries.service;

import lab3.obrazy.galeries.entity.ArtGallery;
import lab3.obrazy.galeries.repository.ArtGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtGalleryService {
    private ArtGalleryRepository repository;

    @Autowired
    public ArtGalleryService(ArtGalleryRepository repository) {
        this.repository = repository;

    }

    public Optional<ArtGallery> find(String adress) {
        return repository.findById(adress);
    }

    public List<ArtGallery> findAll() {
        return repository.findAll();
    }

    public ArtGallery create(ArtGallery artGallery) {
        return repository.save(artGallery);
    }

    public void delete(String adress) {
        repository.delete(repository.findById(adress).orElseThrow());
    }

    public void update(ArtGallery artGallery) {
        repository.save(artGallery);
    }

    public void delete(ArtGallery artGallery) {
        repository.delete(artGallery);
    }
}