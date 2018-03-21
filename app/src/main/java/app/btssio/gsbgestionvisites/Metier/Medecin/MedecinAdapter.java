package app.btssio.gsbgestionvisites.Metier.Medecin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.btssio.gsbgestionvisites.R;


public class MedecinAdapter extends ArrayAdapter<Medecin> {
    public MedecinAdapter(Context context, ArrayList<Medecin> medecins) {
        super(context, 0, medecins);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_person, parent, false);
        }

        Medecin currentMedecin = getItem(position);

        TextView textViewNom = (TextView) listItemView.findViewById(R.id.textViewNom);
        textViewNom.setText(currentMedecin.getNom());


        TextView textViewPrenom = (TextView) listItemView.findViewById(R.id.textViewPrenom);
        textViewPrenom.setText(currentMedecin.getPrenom());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.imageViewPhoto);
        iconView.setImageResource(R.drawable.avatar);

        return listItemView;
    }
}
