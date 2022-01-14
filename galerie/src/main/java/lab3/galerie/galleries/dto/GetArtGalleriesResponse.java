package lab3.galerie.galleries.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class GetArtGalleriesResponse {

    GetArtGalleriesResponse(){
        this.artGalleries=new ArrayList<>();
    }

    public static class ArtGallery {

        /**
         * Unique id identifying character.
         */
        private String adress;

        /**
         * Name of the character.
         */
        public ArtGallery() {
        }

        public ArtGallery(String adress) {
            this.adress=adress;
        }


        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }
    }

    private List<ArtGallery> artGalleries;

    public List<GetArtGalleriesResponse.ArtGallery> getArtGalleries() {
        return artGalleries;
    }

    public void AddArtGallery(GetArtGalleriesResponse.ArtGallery artGallery){
        artGalleries.add(artGallery);
    }

    public static Function<Collection<lab3.galerie.galleries.entity.ArtGallery>, GetArtGalleriesResponse> entityToDtoMapper() {
        return artGalleries -> {
            GetArtGalleriesResponse  response = new GetArtGalleriesResponse();
            artGalleries.stream()
                    .map(artGallery -> new GetArtGalleriesResponse.ArtGallery(artGallery.getAdress()))
                    .forEach(response::AddArtGallery);
            return response;
        };
    }
}
