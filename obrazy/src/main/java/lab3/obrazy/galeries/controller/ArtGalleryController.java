package lab3.obrazy.galeries.controller;

import lab3.obrazy.galeries.dto.CreateArtGalleryRequest;
import lab3.obrazy.galeries.entity.ArtGallery;
import lab3.obrazy.galeries.service.ArtGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/artGalleries")
public class ArtGalleryController {

    private ArtGalleryService artGalleryService;

    @Autowired
    public ArtGalleryController(ArtGalleryService artGalleryService) {
        this.artGalleryService = artGalleryService;
    }

    @DeleteMapping("{adress}")
    public ResponseEntity<Void> deleteArtGallery(@PathVariable("adress") String adress) {
        Optional<ArtGallery> artGallery = artGalleryService.find(adress);
        if (artGallery.isPresent()) {
            artGalleryService.delete(artGallery.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> createArtGallery(@RequestBody CreateArtGalleryRequest request, UriComponentsBuilder builder) {
        ArtGallery artGallery = CreateArtGalleryRequest.dtoToEntityMapper().apply(request);
        artGalleryService.create(artGallery);
        return ResponseEntity.created(builder.pathSegment("api", "artGalleries", "{adress}")
                .buildAndExpand(artGallery.getAdress()).toUri()).build();
    }

}
