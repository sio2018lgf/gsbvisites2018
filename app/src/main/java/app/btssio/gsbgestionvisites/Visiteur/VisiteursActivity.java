package app.btssio.gsbgestionvisites.Visiteur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import app.btssio.gsbgestionvisites.Metier.GsonRequest;
import app.btssio.gsbgestionvisites.Metier.Visiteur.Visiteur;
import app.btssio.gsbgestionvisites.Metier.Visiteur.VisiteurAdapter;
import app.btssio.gsbgestionvisites.Metier.Visiteur.Visiteurs;
import app.btssio.gsbgestionvisites.Metier.VolleyHelper;
import app.btssio.gsbgestionvisites.R;

public class VisiteursActivity extends Activity {


    RequestQueue requestQueue;
    //URL de l'API REST (envoie des données au format JSON)
    String visiteursUrl = "http://192.168.1.35:8081/gsb_visiteur/visiteurs.json";
    ListView listViewVisiteurs;
    ImageButton btnRefresh;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiteurs);

        listViewVisiteurs = (ListView) findViewById(R.id.lv_visiteurs);
        btnRefresh = (ImageButton) findViewById(R.id.btn_refresh);
        btnCreate = (Button) findViewById(R.id.btn_creer_visiteur);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue = Volley.newRequestQueue(getApplicationContext());

                final GsonRequest gsonRequest = new GsonRequest(visiteursUrl, Visiteurs.class, null, new Response.Listener<Visiteurs>() {
                    @Override
                    public void onResponse(Visiteurs visiteurs) {

                        VisiteurAdapter visiteurAdapter = new VisiteurAdapter(getApplicationContext(), visiteurs.getVisiteurs());
                        listViewVisiteurs.setAdapter(visiteurAdapter);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (volleyError != null)
                            Log.e("VisiteursActivity", volleyError.getMessage());
                    }
                });

                VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
            }
        });

        listViewVisiteurs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(VisiteursActivity.this, DetailVisiteurActivity.class);
                i.putExtra("Visiteur", (Visiteur) parent.getItemAtPosition(position));
                startActivity(i);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VisiteursActivity.this, CreateVisiteurActivity.class);
                startActivity(i);
            }
        });

        btnRefresh.callOnClick();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
            btnRefresh.callOnClick();
    }

}
