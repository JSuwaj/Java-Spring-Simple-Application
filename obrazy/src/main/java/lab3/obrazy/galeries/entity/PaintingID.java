package lab3.obrazy.galeries.entity;

import java.io.Serializable;
import java.util.Objects;

public class PaintingID implements Serializable {

    private String name;

    private String artist;

    public String getName(){
        return this.name;
    }

    public String getArtist(){
        return this.artist;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setArtist(String artist){
        this.artist=artist;
    }

    public PaintingID(){

    }

    public PaintingID(String name,String artist) {
        this.artist = artist;
        this.name=name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaintingID paintingID = (PaintingID) o;
        return name.equals(paintingID.name) &&
                artist.equals(paintingID.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, artist);
    }
}