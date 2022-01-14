package lab3.galerie;

import lab3.galerie.galleries.service.ArtGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLine implements CommandLineRunner {


    private ArtGalleryService artGalleryService;

    @Autowired
    public CommandLine(ArtGalleryService artGalleryService) {
        this.artGalleryService=artGalleryService;
    }

    @Override
    public void run(String... args) throws Exception {
        artGalleryService.findAll().forEach(System.out::println);

    }

}