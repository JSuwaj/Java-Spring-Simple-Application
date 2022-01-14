package lab3.obrazy.galeries.dto;

import lab3.obrazy.galeries.entity.ArtGallery;
import lab3.obrazy.galeries.entity.Painting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public class CreateArtGalleryRequest {
    private String adress;

    private Collection<Painting> paintings = new ArrayList<>();

    public CreateArtGalleryRequest(){

    }
    public CreateArtGalleryRequest(String adress){
        this.adress=adress;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String toString() {
        return "Adres: " + getAdress();//+ ", Paintings: " + getPaintings();
    }

    public Collection   <Painting> getPaintings() {
        return paintings;
    }

    public void setPaintings(ArrayList<Painting> paintings) {
        this.paintings = paintings;
    }

    public static Function<CreateArtGalleryRequest, ArtGallery> dtoToEntityMapper() {
        return request -> new ArtGallery(request.getAdress());
    }

}
