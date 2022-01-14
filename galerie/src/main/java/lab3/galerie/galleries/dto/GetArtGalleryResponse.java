package lab3.galerie.galleries.dto;

import lab3.galerie.galleries.entity.ArtGallery;

import java.util.function.Function;

public class GetArtGalleryResponse {
    private String adress;

    private int openingHour;

    private int closingHour;


    public GetArtGalleryResponse(){

    }
    public GetArtGalleryResponse(String adress,int openingHour,int closingHour){
        this.adress=adress;
        this.openingHour=openingHour;
        this.closingHour=closingHour;
    }


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(int openingHour) {
        this.openingHour = openingHour;
    }

    public int getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(int closingHour) {
        this.closingHour = closingHour;
    }

    public String toString() {
        return "Adres: " + getAdress() + ", Opening hour: " + getOpeningHour() + ", Closing hour: " + getClosingHour();//+ ", Paintings: " + getPaintings();
    }



    public static Function<ArtGallery, GetArtGalleryResponse> entityToDtoMapper() {
        return artGallery -> new GetArtGalleryResponse(artGallery.getAdress(),artGallery.getOpeningHour(),artGallery.getClosingHour());
    }
}