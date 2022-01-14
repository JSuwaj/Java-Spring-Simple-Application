package lab3.galerie.datainit;

import lab3.galerie.galleries.entity.ArtGallery;
import lab3.galerie.galleries.service.ArtGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class InitializeData {
    private final ArtGalleryService artGalleryService;

  // private final PaintingService paintingService;

    @Autowired
    public InitializeData(ArtGalleryService artGalleryService) {
        this.artGalleryService = artGalleryService;
        //this.paintingService = paintingService;
    }

    @PostConstruct
    private synchronized void init() {
        ArtGallery g1 = new ArtGallery("Jana Pawła 35", 9, 20);
        ArtGallery g2 = new ArtGallery("Dworcowa 54", 11, 18);
        ArtGallery g3 = new ArtGallery("Szkolna 17", 8, 16);

        artGalleryService.save(g1);
        artGalleryService.save(g2);
        artGalleryService.save(g3);

//        Painting p1 = new Painting("Dama z gronostajem", "Leonardo da Vinci", 1489, "olej", g1);
//        Painting p2 = new Painting("Trwałość pamięci", "Salvador Dalí", 1931, "olej", g2);
//        Painting p3 = new Painting("Syn człowieczy", "René Magritte", 1964, "olej", g2);
//        Painting p4 = new Painting("Still life with guitar", "Pablo Picasso", 1924, "akryl", g2);
//        Painting p5 = new Painting("Plaża w Pourville", "Claude Monet", 1882, "olej", g3);
//        Painting p6 = new Painting("Dworzec Saint-Lazare", "Claude Monet", 1877, "olej", g3);
//
//
//        ArrayList<Painting> g1Paintings = new ArrayList<Painting>();
//        g1Paintings.add(p1);
//        ArrayList<Painting> g2Paintings = new ArrayList<Painting>();
//        g2Paintings.add(p2);
//        g2Paintings.add(p3);
//        g2Paintings.add(p4);
//        ArrayList<Painting> g3Paintings = new ArrayList<Painting>();
//        g3Paintings.add(p5);
//        g3Paintings.add(p6);
//
//        g1.setPaintings(g1Paintings);
//        g2.setPaintings(g2Paintings);
//        g3.setPaintings(g3Paintings);
//
//        paintingService.create(p1);
//        paintingService.create(p2);
//        paintingService.create(p3);
//        paintingService.create(p4);
//        paintingService.create(p5);
//        paintingService.create(p6);


    }

}