package lab3.obrazy.galeries.dto;

import lab3.obrazy.galeries.entity.ArtGallery;
import lab3.obrazy.galeries.entity.Painting;

import java.util.function.BiFunction;

public class UpdatePaintingRequest {

    private int year;

    private String medium;

    private ArtGallery art_gallery;

    public UpdatePaintingRequest(){

    }
    public UpdatePaintingRequest(int year,String medium,ArtGallery art_gallery){
        this.year=year;
        this.medium=medium;
        this.art_gallery = art_gallery;
    }




    public ArtGallery getArt_gallery() {
        return art_gallery;
    }

    public void setArt_gallery(ArtGallery artGallery) {
        this.art_gallery = artGallery;
    }


    public static BiFunction<Painting, UpdatePaintingRequest, Painting> dtoToEntityUpdater(ArtGallery artGallery) {
        if(artGallery!=null)
        {
            return (painting, request) -> {
                painting.setYear(request.getYear());
                painting.setMedium(request.getMedium());
                painting.setArtgallery(artGallery);
                return painting;
            };
        }
        return (painting, request) -> {
            painting.setYear(request.getYear());
            painting.setMedium(request.getMedium());
            painting.setArtgallery(request.getArt_gallery());
            return painting;
        };
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
