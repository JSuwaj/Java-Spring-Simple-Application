package lab3.obrazy.galeries.controller;

import lab3.obrazy.galeries.dto.*;
import lab3.obrazy.galeries.entity.ArtGallery;
import lab3.obrazy.galeries.entity.Painting;
import lab3.obrazy.galeries.service.ArtGalleryService;
import lab3.obrazy.galeries.service.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
@RestController
@RequestMapping("api/artGalleries/{adress}/paintings")
public class ArtGalleryPaintingController {
    private PaintingService paintingService;

    private ArtGalleryService artGalleryService;

    @Autowired
    public ArtGalleryPaintingController(ArtGalleryService artGalleryService,PaintingService paintingService) {
        this.artGalleryService = artGalleryService;
        this.paintingService = paintingService;
    }

//    @GetMapping
//    public ResponseEntity<GetArtGalleriesResponse> getArtGalleries() {
//
//        return ResponseEntity.ok(GetArtGalleriesResponse.entityToDtoMapper().apply(artGalleryService.findAll()));
//    }
//
//    @GetMapping("{adress}")
//    public ResponseEntity<GetArtGalleryResponse> getArtGallery(@PathVariable("adress") String adress) {
//        Optional<ArtGallery> artGallery = artGalleryService.find(adress);
//        return artGallery.map(value -> ResponseEntity.ok(GetArtGalleryResponse.entityToDtoMapper().apply(value)))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<Void> createArtGallery(@RequestBody CreateArtGalleryRequest request, UriComponentsBuilder builder) {
//
//        ArtGallery artGallery = CreateArtGalleryRequest
//                .dtoToEntityMapper()
//                .apply(request);
//        if (artGalleryService.find(artGallery.getAdress()).isPresent())
//            return ResponseEntity.badRequest().build();
//        artGallery = artGalleryService.create(artGallery);
//        return ResponseEntity.created(builder.pathSegment("api", "artGalleries", "{adress}").buildAndExpand(artGallery.getAdress()).toUri()).build();
//    }



//    @PutMapping("{adress}")
//    public ResponseEntity<Void> updateArtGallery(@RequestBody UpdateArtGalleryRequest request, @PathVariable("adress") String adress) {
//        Optional<ArtGallery> artGallery = artGalleryService.find(adress);
//        if (artGallery.isPresent()) {
//            UpdateArtGalleryRequest.dtoToEntityUpdater().apply(artGallery.get(), request);
//            artGalleryService.update(artGallery.get());
//            return ResponseEntity.accepted().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping("{artist}/{name}")
    public ResponseEntity<Void> deletePainting(@PathVariable("adress") String adress,
                                               @PathVariable("artist") String artist,
                                               @PathVariable("name") String name) {
        Optional<Painting> painting = paintingService.find(adress,name,artist);
        if (painting.isPresent()) {
            paintingService.delete(painting.get().getName(),painting.get().getArtist());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<GetPaintingsResponse> getPaintings(@PathVariable("adress") String adress) {
        Optional<ArtGallery> artGallery = artGalleryService.find(adress);
        return artGallery.map(value -> ResponseEntity.ok(GetPaintingsResponse.entityToDtoMapper().apply(paintingService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{artist}/{name}")
    public ResponseEntity<GetPaintingResponse> getPaintings(@PathVariable("adress") String adress,
                                                            @PathVariable("artist") String artist,
                                                            @PathVariable("name") String name) {
        Optional<ArtGallery> artGallery = artGalleryService.find(adress);
        if (artGallery.isPresent()) {
            Optional<Painting> painting = paintingService.find(artGallery.get(), name,artist);
            return painting.map(value -> ResponseEntity.ok(GetPaintingResponse.entityToDtoMapper().apply(value)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createPainting(@PathVariable("adress") String adress,
                                                @RequestBody CreatePaintingRequest request,
                                                UriComponentsBuilder builder) {
        Optional<ArtGallery> artGallery = artGalleryService.find(adress);
        if (artGallery.isPresent()) {
            Painting painting = CreatePaintingRequest
                    .dtoToEntityMapper(adres -> artGalleryService.find(adres).orElseThrow(),artGallery.get()  )
                    .apply(request);
            if(paintingService.find(painting.getName(),painting.getArtist()).isPresent())
                return ResponseEntity.badRequest().build();
            painting = paintingService.create(painting);
            return ResponseEntity.created(builder.pathSegment("api", "paintings", "{artist}/{name}").buildAndExpand(painting.getName(), painting.getArtist()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{artist}/{name}")
    public ResponseEntity<Void> updatePainting(@PathVariable("adress") String adress,@RequestBody UpdatePaintingRequest request, @PathVariable("name") String name, @PathVariable("artist") String artist) {
        Optional<Painting> painting = paintingService.find(adress,name, artist);
        Optional<ArtGallery> artGallery = artGalleryService.find(adress);
        if (painting.isPresent() && artGallery.isPresent()) {
            UpdatePaintingRequest.dtoToEntityUpdater(artGallery.get()).apply(painting.get(), request);
            paintingService.update(painting.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
