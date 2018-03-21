package app.btssio.gsbgestionvisites.Metier.Medecin;


import java.io.Serializable;

public class Medecin implements Serializable {

    private String id;
    private String nom;
    private String prenom;



    public Medecin(String unId, String unNom, String unPrenom){
        this.id = unId;
        this.nom = unNom;
        this.prenom = unPrenom;
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
