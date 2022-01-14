package lab3.obrazy.galeries.repository;

import lab3.obrazy.galeries.entity.ArtGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtGalleryRepository extends JpaRepository<ArtGallery, String > {

}