package app.btssio.gsbgestionvisites.Metier.Visiteur;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import app.btssio.gsbgestionvisites.Metier.Visite.Visite;

public class Visiteur  implements Serializable {

    private String id;
    //@SerializedName("nom")
    private String nom;
    private String prenom;
    @Expose(serialize = false, deserialize = false)
    private String test;

    public ArrayList<Visite> getVisites() {
        return visites;
    }

    public void setVisites(ArrayList<Visite> visites) {
        this.visites = visites;
    }

    private ArrayList<Visite> visites;


    public Visiteur (String unId, String unNom, String unPrenom){
        this.id = unId;
        this.nom = unNom;
        this.prenom = unPrenom;
    }

    public Visiteur (String unId, String unNom, String unPrenom, ArrayList<Visite> v ){
        this.id = unId;
        this.nom = unNom;
        this.prenom = unPrenom;
        this.visites = v;
    }


    @Override
    public String toString() {
        return "Visiteur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
