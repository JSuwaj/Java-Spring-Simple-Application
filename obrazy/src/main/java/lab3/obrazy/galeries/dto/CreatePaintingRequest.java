package lab3.obrazy.galeries.dto;

import lab3.obrazy.galeries.entity.ArtGallery;
import lab3.obrazy.galeries.entity.Painting;

import java.util.function.Function;

public class CreatePaintingRequest {

    private String name;

    private String artist;

    private int year;

    private String medium;

    private ArtGallery art_gallery;

    public CreatePaintingRequest(){}

    public CreatePaintingRequest(String name, String artist, int year, String medium, ArtGallery art_gallery) {
        this.name=name;
        this.artist=artist;
        this.year=year;
        this.medium=medium;
        this.art_gallery=art_gallery;
    }

    public static Function<CreatePaintingRequest, Painting> dtoToEntityMapper(
            Function<String, ArtGallery> artGalleryFunction,ArtGallery artGallery) {
        if(artGallery==null)
            return request -> new Painting(request.getName(),request.getArtist(),request.getYear(),request.getMedium(),request.getArt_gallery());
        return request -> new Painting(request.getName(),request.getArtist(),request.getYear(),request.getMedium(),artGallery);
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    public String getMedium() {
        return medium;
    }

    public ArtGallery getArt_gallery() {
        return art_gallery;
    }
}