package app.btssio.gsbgestionvisites.Metier.Visite;


import java.io.Serializable;
import java.util.HashMap;

import app.btssio.gsbgestionvisites.Metier.Medecin.Medecin;
import app.btssio.gsbgestionvisites.Metier.Visiteur.Visiteur;

public class Visite implements Serializable {

    private String id;
    private String date;
    private String commentaire;
    private String visiteur_id;
    private String medecin_id;
    private Visiteur visiteur;
    private Medecin medecin;

    public Visite(String id, String date, String commentaire, String visiteur, String medecin, Medecin m, Visiteur v){
        this.id = id;
        this.date = date;
        this.commentaire = commentaire;
        this.medecin_id = medecin;
        this.visiteur_id = visiteur;
        this.visiteur = v;
        this.medecin = m;
    }

    public HashMap<String, String> getVisite(){
        HashMap<String, String> ligne = new HashMap<String, String>();
        ligne.put("id", this.id);
        ligne.put("date", this.date);
        ligne.put("commentaire", this.commentaire);
        return ligne;
    }

    public Visiteur getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(Visiteur v) {
        this.visiteur = v;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin m) {
        this.medecin = m;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getVisiteur_id() {
        return visiteur_id;
    }

    public void setVisiteur_id(String visiteur_id) {
        this.visiteur_id = visiteur_id;
    }

    public String getMedecin_id() {
        return medecin_id;
    }

    public void setMedecin_id(String medecin_id) {
        this.medecin_id = medecin_id;
    }

}
