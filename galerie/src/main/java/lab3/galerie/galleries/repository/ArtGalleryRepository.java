package lab3.galerie.galleries.repository;

import lab3.galerie.galleries.entity.ArtGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtGalleryRepository extends JpaRepository<ArtGallery, String > {
}