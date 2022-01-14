package lab3.galerie.galleries.event.dto;

import lab3.galerie.galleries.entity.ArtGallery;
import lombok.Builder;

import java.util.function.Function;

@Builder
public class CreateRequest {

    private String adress;

    public CreateRequest(String adress) {
        this.adress=adress;
    }

    public static Function<ArtGallery, CreateRequest> entityToDtoMapper() {
        return entity -> new CreateRequest(entity.getAdress());
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
