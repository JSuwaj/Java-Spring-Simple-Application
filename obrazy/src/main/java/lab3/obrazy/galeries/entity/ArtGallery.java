package lab3.obrazy.galeries.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "art_galleries")
public class ArtGallery implements Serializable {
    @Id
    private String adress;

    @OneToMany
    @JoinColumn(name = "art_gallery")
    @JsonManagedReference
    private Collection<Painting> paintings = new ArrayList<>();

    public ArtGallery(){

    }
    public ArtGallery(String adress,Collection<Painting> paintings){
        this.adress=adress;
        this.paintings=paintings;
    }

    public ArtGallery(String adress){
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
}