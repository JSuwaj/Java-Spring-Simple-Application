package lab3.galerie.galleries.dto;

import lab3.galerie.galleries.entity.ArtGallery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiFunction;

public class UpdateArtGalleryRequest {
    private int openingHour;

    private int closingHour;

    //private Collection<Painting> paintings = new ArrayList<>();

    public UpdateArtGalleryRequest(){

    }
    public UpdateArtGalleryRequest(int openingHour,int closingHour){
        this.openingHour=openingHour;
        this.closingHour=closingHour;
    }

//    public UpdateArtGalleryRequest(int openingHour,int closingHour,Collection<Painting> paintings){
//        this.openingHour=openingHour;
//        this.closingHour=closingHour;
//        this.paintings=paintings;
//    }

//    public Collection<Painting> getPaintings() {
//        return paintings;
//    }
//
//    public void setPaintings(Collection<Painting> paintings) {
//        this.paintings = paintings;
//    }

    public int getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(int closingHour) {
        this.closingHour = closingHour;
    }

    public int getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(int openingHour) {
        this.openingHour = openingHour;
    }

    public static BiFunction<ArtGallery, UpdateArtGalleryRequest, ArtGallery> dtoToEntityUpdater() {
        return (artGallery, request) -> {
            artGallery.setOpeningHour(request.getOpeningHour());
            artGallery.setClosingHour(request.getClosingHour());
           // artGallery.setPaintings((ArrayList<Painting>) request.getPaintings());
            return artGallery;
        };
    }
}
