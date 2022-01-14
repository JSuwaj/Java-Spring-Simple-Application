package lab3.obrazy.galeries.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@ToString
@EqualsAndHashCode
public class GetPaintingsResponse {

    GetPaintingsResponse(){
        this.paintings=new ArrayList<>();
    }

    public static class Painting {

        /**
         * Unique id identifying character.
         */
        private String name;

        /**
         * Name of the character.
         */
        private String artist;

        public Painting(String name, String artist) {
            this.name=name;
            this.artist=artist;
        }

        public String getName() {
            return name;
        }

        public String getArtist() {
            return artist;
        }
    }

    private List<Painting> paintings;

    public List<Painting> getPaintings() {
        return paintings;
    }

    public void AddPainting(Painting painting){
        paintings.add(painting);
    }

    public static Function<Collection<lab3.obrazy.galeries.entity.Painting>, GetPaintingsResponse> entityToDtoMapper() {
        return paintings -> {
            GetPaintingsResponse  response = new GetPaintingsResponse();
            paintings.stream()
                    .map(painting -> new Painting(painting.getName(),painting.getArtist()))
                    .forEach(response::AddPainting);
            return response;
        };
    }

}