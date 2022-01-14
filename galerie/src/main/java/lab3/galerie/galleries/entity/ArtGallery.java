package lab3.galerie.galleries.entity;

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

    private int openingHour;

    private int closingHour;

    public ArtGallery(){

    }


    public ArtGallery(String adress,int openingHour,int closingHour){
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

}

