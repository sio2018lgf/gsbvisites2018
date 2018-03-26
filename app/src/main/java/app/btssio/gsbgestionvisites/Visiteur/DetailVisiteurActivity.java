package app.btssio.gsbgestionvisites.Visiteur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import app.btssio.gsbgestionvisites.Metier.GsonRequest;
import app.btssio.gsbgestionvisites.Metier.Visite.Visite;
import app.btssio.gsbgestionvisites.Metier.Visite.VisiteVisiteurAdapter;
import app.btssio.gsbgestionvisites.Metier.Visite.Visites;
import app.btssio.gsbgestionvisites.Metier.Visiteur.Visiteur;
import app.btssio.gsbgestionvisites.Metier.VolleyHelper;
import app.btssio.gsbgestionvisites.R;
import app.btssio.gsbgestionvisites.Visite.DetailVisiteActivity;

public class DetailVisiteurActivity extends Activity {

    RequestQueue requestQueue;
    String deleteVisiteurUrl = "http://192.168.1.35:8081/gsb_visiteur/visiteurs/delete/";
    //URL de l'API REST (envoie des données au format JSON)
    String visitesUrl = "http://192.168.1.35:8081/gsb_visiteur/visites.json";
    Visiteur visiteur;
    ListView listViewVisites;
    Button buttonRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        listViewVisites = (ListView) findViewById(R.id.lv_visites);
        TextView textViewNom = (TextView) findViewById(R.id.textViewNom);
        TextView textViewPrenom = (TextView) findViewById(R.id.textViewPrenom);
        ImageView imageViewPhoto = (ImageView) findViewById(R.id.imageViewPhoto);
        Button buttonDelete = (Button) findViewById(R.id.btn_delete);
        buttonRetour = (Button) findViewById(R.id.btn_retour);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        visiteur = (Visiteur) getIntent().getSerializableExtra("Visiteur");
        textViewNom.setText("Nom : " + visiteur.getNom());
        textViewPrenom.setText("Prénom : " + visiteur.getPrenom());

        imageViewPhoto.setImageResource(R.drawable.avatar);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        final GsonRequest gsonRequest = new GsonRequest(visitesUrl, Visites.class, null, new Response.Listener<Visites>() {
            @Override
            public void onResponse(Visites visites) {

                ArrayList<Visite> v = new ArrayList<Visite>();
                for(Visite element : visites.getVisites()){
                       if( element.getVisiteur_id().equals(visiteur.getId())){
                            v.add(element);
                        }
                }
                VisiteVisiteurAdapter visiteAdapter = new VisiteVisiteurAdapter(getApplicationContext(), v);
                listViewVisites.setAdapter(visiteAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError != null)
                    Log.e("VisitesActivity", volleyError.getMessage());
            }
        });

        VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteVisiteurUrl += visiteur.getId();
                StringRequest request = new StringRequest(Request.Method.DELETE, deleteVisiteurUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailVisiteurActivity.this, "Visiteur supprimé",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(request);
            }
        });

        listViewVisites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(DetailVisiteurActivity.this, DetailVisiteActivity.class);
                i.putExtra("Visite", (Visite) parent.getItemAtPosition(position));
                startActivity(i);
            }
        });

        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}


