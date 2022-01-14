package lab3.galerie.galleries.controller;

import lab3.galerie.galleries.dto.CreateArtGalleryRequest;
import lab3.galerie.galleries.dto.GetArtGalleriesResponse;
import lab3.galerie.galleries.dto.GetArtGalleryResponse;
import lab3.galerie.galleries.dto.UpdateArtGalleryRequest;
import lab3.galerie.galleries.entity.ArtGallery;
import lab3.galerie.galleries.service.ArtGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/artGalleries")
public class ArtGalleryController {
    //private PaintingService paintingService;

    private ArtGalleryService artGalleryService;

    @Autowired
    public ArtGalleryController(ArtGalleryService artGalleryService) {
        this.artGalleryService = artGalleryService;
        //this.paintingService = paintingService;
    }

    @GetMapping
    public ResponseEntity<GetArtGalleriesResponse> getArtGalleries() {

        return ResponseEntity.ok(GetArtGalleriesResponse.entityToDtoMapper().apply(artGalleryService.findAll()));
    }

    @GetMapping("{adress}")
    public ResponseEntity<GetArtGalleryResponse> getArtGallery(@PathVariable("adress") String adress) {
        Optional<ArtGallery> artGallery = artGalleryService.find(adress);
        return artGallery.map(value -> ResponseEntity.ok(GetArtGalleryResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createArtGallery(@RequestBody CreateArtGalleryRequest request, UriComponentsBuilder builder) {

        ArtGallery artGallery = CreateArtGalleryRequest
                .dtoToEntityMapper()
                .apply(request);
        if (artGalleryService.find(artGallery.getAdress()).isPresent())
            return ResponseEntity.badRequest().build();
        artGallery = artGalleryService.create(artGallery);
        return ResponseEntity.created(builder.pathSegment("api", "artGalleries", "{adress}").buildAndExpand(artGallery.getAdress()).toUri()).build();
    }

    @PutMapping("{adress}")
    public ResponseEntity<Void> updateArtGallery(@RequestBody UpdateArtGalleryRequest request, @PathVariable("adress") String adress) {
        Optional<ArtGallery> artGallery = artGalleryService.find(adress);
        if (artGallery.isPresent()) {
            UpdateArtGalleryRequest.dtoToEntityUpdater().apply(artGallery.get(), request);
            artGalleryService.update(artGallery.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{adress}")
    public ResponseEntity<Void> deleteArtGallery(@PathVariable("adress") String adress) {
        Optional<ArtGallery> artGallery = artGalleryService.find(adress);
        if (artGallery.isPresent()) {
            artGalleryService.delete(artGallery.get().getAdress());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//
//    @GetMapping("{adress}/paintings")
//    public ResponseEntity<GetPaintingsResponse> getPaintings(@PathVariable("adress") String adress) {
//        Optional<ArtGallery> artGallery = artGalleryService.find(adress);
//        return artGallery.map(value -> ResponseEntity.ok(GetPaintingsResponse.entityToDtoMapper().apply(paintingService.findAll(value))))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("{adress}/paintings/{artist}/{name}")
//    public ResponseEntity<GetPaintingResponse> getPaintings(@PathVariable("adress") String adress,
//                                                            @PathVariable("artist") String artist,
//                                                            @PathVariable("name") String name) {
//        Optional<ArtGallery> artGallery = artGalleryService.find(adress);
//        if (artGallery.isPresent()) {
//            Optional<Painting> painting = paintingService.find(artGallery.get(), name,artist);
//            return painting.map(value -> ResponseEntity.ok(GetPaintingResponse.entityToDtoMapper().apply(value)))
//                    .orElseGet(() -> ResponseEntity.notFound().build());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}