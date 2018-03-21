package app.btssio.gsbgestionvisites.Medecin;

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
import app.btssio.gsbgestionvisites.Metier.Medecin.Medecin;
import app.btssio.gsbgestionvisites.Metier.Medecin.MedecinAdapter;
import app.btssio.gsbgestionvisites.Metier.Medecin.Medecins;
import app.btssio.gsbgestionvisites.Metier.VolleyHelper;
import app.btssio.gsbgestionvisites.R;

public class MedecinsActivity extends Activity {


    RequestQueue requestQueue;
    //URL de l'API REST (envoie des données au format JSON)
    String medecinsUrl = "http://192.168.1.35:8081/gsb_visiteur/medecins.json";
    ListView listViewMedecins;
    ImageButton btnRefresh;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecins);

        listViewMedecins = (ListView) findViewById(R.id.lv_medecins);
        btnRefresh = (ImageButton) findViewById(R.id.btn_refresh);
        btnCreate = (Button) findViewById(R.id.btn_creer_medecin);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue = Volley.newRequestQueue(getApplicationContext());

                final GsonRequest gsonRequest = new GsonRequest(medecinsUrl, Medecins.class, null, new Response.Listener<Medecins>() {
                    @Override
                    public void onResponse(Medecins medecins) {

                        MedecinAdapter medecinAdapter = new MedecinAdapter(getApplicationContext(), medecins.getMedecins());
                        listViewMedecins.setAdapter(medecinAdapter);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (volleyError != null)
                            Log.e("MedecinsActivity", volleyError.getMessage());
                    }
                });

                VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
            }
        });

        listViewMedecins.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MedecinsActivity.this, DetailMedecinActivity.class);
                i.putExtra("Medecin", (Medecin) parent.getItemAtPosition(position));
                startActivity(i);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MedecinsActivity.this, CreateMedecinActivity.class);
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
