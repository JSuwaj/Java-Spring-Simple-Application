package lab3.obrazy;

import lab3.obrazy.galeries.service.ArtGalleryService;
import lab3.obrazy.galeries.service.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLine implements CommandLineRunner {

    private PaintingService paintingService;
    private ArtGalleryService artGalleryService;

    @Autowired
    public CommandLine(PaintingService paintingService, ArtGalleryService artGalleryService) {
        this.paintingService = paintingService;
        this.artGalleryService=artGalleryService;
    }

    @Override
    public void run(String... args) throws Exception {
        paintingService.findAll().forEach(System.out::println);
        artGalleryService.findAll().forEach(System.out::println);

    }

}