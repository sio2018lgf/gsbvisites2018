package app.btssio.gsbgestionvisites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import app.btssio.gsbgestionvisites.Medecin.MedecinsActivity;
import app.btssio.gsbgestionvisites.Visite.VisitesActivity;
import app.btssio.gsbgestionvisites.Visiteur.VisiteursActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textViewVisiteurs = (TextView) findViewById(R.id.tv_visiteurs);
        TextView textViewMedecins = (TextView) findViewById(R.id.tv_medecins);
        TextView textViewVisites = (TextView) findViewById(R.id.tv_visites);

        textViewVisiteurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VisiteursActivity.class);
                startActivity(intent);
            }
        });


        textViewMedecins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MedecinsActivity.class);
                startActivity(intent);
            }
        });

        textViewVisites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VisitesActivity.class);
                startActivity(intent);
            }
        });

    }
}
