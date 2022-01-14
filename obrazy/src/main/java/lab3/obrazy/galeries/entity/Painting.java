package lab3.obrazy.galeries.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "paintings")
@IdClass(PaintingID.class)
public class Painting implements Serializable {

    private String name;

    private String artist;

    @Id
    private PaintingID paintingID;

    private int year;

    private String medium;

    @ManyToOne
    @JoinColumn(name = "art_gallery")
    @JsonBackReference
    private ArtGallery artgallery;

    public Painting(){

    }
    public Painting(String name,String artist,int year,String medium,ArtGallery artgallery){
        this.name=name;
        this.artist=artist;
        this.paintingID=new PaintingID(name,artist);
        this.year=year;
        this.medium=medium;
        this.artgallery = artgallery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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

    public ArtGallery getArtgallery() {
        return artgallery;
    }

    public void setArtgallery(ArtGallery artGallery) {
        this.artgallery = artGallery;
    }

    public String toString() {
        return "Name: " + getName() + ", Artist: " + getArtist() + ", Year: " + getYear()+ ", Medium: " + getMedium();
    }

    public PaintingID getPaintingID() {
        return paintingID;
    }

    public void setPaintingID(PaintingID paintingID) {
        this.paintingID = paintingID;
    }
}
