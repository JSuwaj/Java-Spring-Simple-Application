package lab3.obrazy.galeries.service;

import lab3.obrazy.galeries.entity.ArtGallery;
import lab3.obrazy.galeries.entity.Painting;
import lab3.obrazy.galeries.entity.PaintingID;
import lab3.obrazy.galeries.repository.ArtGalleryRepository;
import lab3.obrazy.galeries.repository.PaintingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaintingService {
    private PaintingRepository repository;
    private ArtGalleryRepository artGalleryRepository;

    @Autowired
    public PaintingService(PaintingRepository repository,ArtGalleryRepository artGalleryRepository) {
        this.repository = repository;
        this.artGalleryRepository=artGalleryRepository;
    }

    public Optional<Painting> find(String name, String artist) {
        return repository.findById(new PaintingID(name,artist));
    }

    public Optional<Painting> find(ArtGallery artGallery, String name, String artist) {
        return repository.findAllByArtgalleryAndPaintingID(artGallery,new PaintingID(name,artist));
    }

    public Optional<Painting> find(String adress, String name, String artist) {
        Optional<ArtGallery> artGallery = artGalleryRepository.findById(adress);
        if (artGallery.isPresent()) {
            return repository.findAllByArtgalleryAndPaintingID(artGallery.get(),new PaintingID(name,artist));
        } else {
            return Optional.empty();
        }
    }

    public List<Painting> findAll() {
        return repository.findAll();
    }

    public List<Painting> findAll(ArtGallery artGallery) {
        return repository.findAllByArtgallery(artGallery);
    }

    public Painting create(Painting painting) {
        return repository.save(painting);
    }

    public void delete(String name,String artist) {
        repository.delete(repository.findById(new PaintingID(name,artist)).orElseThrow());
    }

    public void update(Painting painting) {
        repository.save(painting);
    }
}