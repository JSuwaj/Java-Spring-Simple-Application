package lab3.obrazy.galeries.dto;

import lab3.obrazy.galeries.entity.ArtGallery;
import lab3.obrazy.galeries.entity.Painting;

import java.util.function.Function;

public class GetPaintingResponse {

    private String name;

    private String artist;

    private int year;

    private String medium;

//    private ArtGallery art_gallery;

    public GetPaintingResponse(String name, String artist, int year, String medium, ArtGallery art_gallery) {
        this.name=name;
        this.artist=artist;
        this.year=year;
        this.medium=medium;
        //this.art_gallery=art_gallery;
    }

    public GetPaintingResponse(String name, String artist, int year, String medium) {
        this.name=name;
        this.artist=artist;
        this.year=year;
        this.medium=medium;
    }

    public static Function<Painting, GetPaintingResponse> entityToDtoMapper() {
        return painting -> new GetPaintingResponse(painting.getName(),painting.getArtist(),painting.getYear(),painting.getMedium());
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

//    public ArtGallery getArt_gallery() {
//        return art_gallery;
//    }
}