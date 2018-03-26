package app.btssio.gsbgestionvisites.Visite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.btssio.gsbgestionvisites.Metier.Visite.Visite;
import app.btssio.gsbgestionvisites.Metier.Visiteur.Visiteur;
import app.btssio.gsbgestionvisites.R;

public class DetailVisiteActivity extends Activity {

    Visite visite;
    TextView textViewDate, textViewMedecin, textViewCommentaire;
    Button buttonRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_visite);

        textViewDate = (TextView) findViewById(R.id.tv_date_visite);
        textViewMedecin = (TextView) findViewById(R.id.tv_medecin_visite);
        textViewCommentaire = (TextView) findViewById(R.id.tv_commentaire_visite);
        buttonRetour = (Button) findViewById(R.id.btn_retour);

        visite = (Visite) getIntent().getSerializableExtra("Visite");

       textViewDate.setText(visite.getDate());
        textViewMedecin.setText(visite.getMedecin().getNom() + " " + visite.getMedecin().getPrenom());
        textViewCommentaire.setText(visite.getCommentaire());

        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
