package app.btssio.gsbgestionvisites.Medecin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import app.btssio.gsbgestionvisites.Metier.Medecin.Medecin;
import app.btssio.gsbgestionvisites.R;

public class DetailMedecinActivity extends Activity {

    RequestQueue requestQueue;
    String deleteMedecinUrl = "http://192.168.1.35:8081/gsb_visiteur/medecins/delete/";
    Medecin medecin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView textViewNom = (TextView) findViewById(R.id.textViewNom);
        TextView textViewPrenom = (TextView) findViewById(R.id.textViewPrenom);
        ImageView imageViewPhoto = (ImageView) findViewById(R.id.imageViewPhoto);
        Button buttonDelete = (Button) findViewById(R.id.btn_delete);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        medecin = (Medecin) getIntent().getSerializableExtra("Medecin");
        textViewNom.setText("Nom : " + medecin.getNom());
        textViewPrenom.setText("Prénom : " + medecin.getPrenom());

        imageViewPhoto.setImageResource(R.drawable.avatar);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMedecinUrl += medecin.getId();
                StringRequest request = new StringRequest(Request.Method.DELETE, deleteMedecinUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailMedecinActivity.this, "Medecin supprimé",
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
    }
}


