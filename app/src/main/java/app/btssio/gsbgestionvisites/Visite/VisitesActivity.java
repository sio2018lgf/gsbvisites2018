package app.btssio.gsbgestionvisites.Visite;

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
import app.btssio.gsbgestionvisites.Metier.Visite.Visite;
import app.btssio.gsbgestionvisites.Metier.Visite.VisiteAdapter;
import app.btssio.gsbgestionvisites.Metier.Visite.Visites;

import app.btssio.gsbgestionvisites.Metier.VolleyHelper;
import app.btssio.gsbgestionvisites.R;
import app.btssio.gsbgestionvisites.Visiteur.CreateVisiteurActivity;
import app.btssio.gsbgestionvisites.Visiteur.DetailVisiteurActivity;

public class VisitesActivity extends Activity {


    RequestQueue requestQueue;
    //URL de l'API REST (envoie des données au format JSON)
    String visitesUrl = "http://192.168.1.35:8081/gsb_visiteur/visites.json";
    ListView listViewVisites;
    ImageButton btnRefresh;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visites);

        listViewVisites = (ListView) findViewById(R.id.lv_visites);
        btnRefresh = (ImageButton) findViewById(R.id.btn_refresh);
        btnCreate = (Button) findViewById(R.id.btn_creer_visite);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue = Volley.newRequestQueue(getApplicationContext());

                final GsonRequest gsonRequest = new GsonRequest(visitesUrl, Visites.class, null, new Response.Listener<Visites>() {
                    @Override
                    public void onResponse(Visites visites) {

                        VisiteAdapter visiteAdapter = new VisiteAdapter(getApplicationContext(), visites.getVisites());
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
            }
        });

        listViewVisites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(VisitesActivity.this, DetailVisiteActivity.class);
                i.putExtra("Visite", (Visite) parent.getItemAtPosition(position));
                startActivity(i);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VisitesActivity.this, CreateVisiteurActivity.class);
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
