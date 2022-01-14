package lab3.galerie.galleries.dto;

import lab3.galerie.galleries.entity.ArtGallery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public class CreateArtGalleryRequest {
    private String adress;

    private int openingHour;

    private int closingHour;

    //private Collection<Painting> paintings = new ArrayList<>();

    public CreateArtGalleryRequest(){

    }
    public CreateArtGalleryRequest(String adress,int openingHour,int closingHour){
        this.adress=adress;
        this.openingHour=openingHour;
        this.closingHour=closingHour;
    }

//    public CreateArtGalleryRequest(String adress,int openingHour,int closingHour,Collection<Painting> paintings){
//        this.adress=adress;
//        this.openingHour=openingHour;
//        this.closingHour=closingHour;
//        this.paintings=paintings;
//    }

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

//    public Collection   <Painting> getPaintings() {
//        return paintings;
//    }
//
//    public void setPaintings(ArrayList<Painting> paintings) {
//        this.paintings = paintings;
//    }

    public static Function<CreateArtGalleryRequest, ArtGallery> dtoToEntityMapper() {
        return request -> new ArtGallery(request.getAdress(),request.getOpeningHour(),request.getClosingHour());//,request.getPaintings());
    }

}