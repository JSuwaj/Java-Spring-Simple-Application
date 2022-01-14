package lab3.obrazy.galeries.controller;

import lab3.obrazy.galeries.dto.CreatePaintingRequest;
import lab3.obrazy.galeries.dto.GetPaintingResponse;
import lab3.obrazy.galeries.dto.GetPaintingsResponse;
import lab3.obrazy.galeries.dto.UpdatePaintingRequest;
import lab3.obrazy.galeries.entity.Painting;
import lab3.obrazy.galeries.service.ArtGalleryService;
import lab3.obrazy.galeries.service.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/paintings")
public class PaintingController {

    private PaintingService paintingService;

    private ArtGalleryService artGalleryService;

    @Autowired
    public PaintingController(ArtGalleryService artGalleryService, PaintingService paintingService) {
        this.artGalleryService = artGalleryService;
        this.paintingService = paintingService;
    }

    @GetMapping
    public ResponseEntity<GetPaintingsResponse> getPaintings() {

        return ResponseEntity.ok(GetPaintingsResponse.entityToDtoMapper().apply(paintingService.findAll()));
    }

    @GetMapping("{artist}/{name}")
    public ResponseEntity<GetPaintingResponse> getPainting(@PathVariable("name") String name, @PathVariable("artist") String artist) {
        Optional<Painting> painting = paintingService.find(name,artist);
        return painting.map(value -> ResponseEntity.ok(GetPaintingResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createPainting(@RequestBody CreatePaintingRequest request, UriComponentsBuilder builder) {
        Painting painting = CreatePaintingRequest
                .dtoToEntityMapper(adress -> artGalleryService.find(adress).orElseThrow(),null   )
                .apply(request);
        if (paintingService.find(painting.getName(),painting.getArtist()).isPresent())
            return ResponseEntity.badRequest().build();
        painting = paintingService.create(painting);
        return ResponseEntity.created(builder.pathSegment("api", "paintings", "{artist}/{name}").buildAndExpand(painting.getName(), painting.getArtist()).toUri()).build();
    }

    @DeleteMapping("{artist}/{name}")
    public ResponseEntity<Void> deletePainting(@PathVariable("name") String name,@PathVariable("artist") String artist) {
        Optional<Painting> painting = paintingService.find(name,artist);
        if (painting.isPresent()) {
            paintingService.delete(painting.get().getName(),painting.get().getArtist());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{artist}/{name}")
    public ResponseEntity<Void> updatePainting(@RequestBody UpdatePaintingRequest request, @PathVariable("name") String name, @PathVariable("artist") String artist) {
        Optional<Painting> painting = paintingService.find(name,artist);
        if (painting.isPresent()) {
            UpdatePaintingRequest.dtoToEntityUpdater(null).apply(painting.get(), request);
            paintingService.update(painting.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
