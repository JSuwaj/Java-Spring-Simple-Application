package lab3.obrazy.galeries.repository;

import lab3.obrazy.galeries.entity.ArtGallery;
import lab3.obrazy.galeries.entity.Painting;
import lab3.obrazy.galeries.entity.PaintingID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, PaintingID> {

    List<Painting> findAllByArtgallery(ArtGallery artGallery);

    Optional<Painting> findAllByArtgalleryAndPaintingID(ArtGallery artGallery, PaintingID paintingID);
}