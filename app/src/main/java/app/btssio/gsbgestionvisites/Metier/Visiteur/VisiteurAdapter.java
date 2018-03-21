package app.btssio.gsbgestionvisites.Metier.Visiteur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.btssio.gsbgestionvisites.R;


public class VisiteurAdapter extends ArrayAdapter<Visiteur> {
    public VisiteurAdapter(Context context, ArrayList<Visiteur> visiteurs) {
        super(context, 0, visiteurs);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_person, parent, false);
        }

        Visiteur currentVisiteur = getItem(position);

        TextView textViewNom = (TextView) listItemView.findViewById(R.id.textViewNom);
        textViewNom.setText(currentVisiteur.getNom());


        TextView textViewPrenom = (TextView) listItemView.findViewById(R.id.textViewPrenom);
        textViewPrenom.setText(currentVisiteur.getPrenom());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.imageViewPhoto);
        iconView.setImageResource(R.drawable.avatar);

        return listItemView;
    }
}
