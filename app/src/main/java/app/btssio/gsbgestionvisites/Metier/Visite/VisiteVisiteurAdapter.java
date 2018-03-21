package app.btssio.gsbgestionvisites.Metier.Visite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.btssio.gsbgestionvisites.R;


public class VisiteVisiteurAdapter extends ArrayAdapter<Visite> {
    public VisiteVisiteurAdapter(Context context, ArrayList<Visite> visites) {
        super(context, 0, visites);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_visite_visiteur, parent, false);
        }

        Visite currentVisite = getItem(position);

        TextView textViewId = (TextView) listItemView.findViewById(R.id.textViewId);
        TextView textViewIdMedecin = (TextView) listItemView.findViewById(R.id.textViewIdMedecin);
        TextView textViewDate = (TextView) listItemView.findViewById(R.id.textViewDate);
        //TextView textViewCommentaire = (TextView) listItemView.findViewById(R.id.textViewCommentaire);

        textViewId.setText(currentVisite.getId());
        textViewIdMedecin.setText(currentVisite.getMedecin().getNom() + " " + currentVisite.getMedecin().getPrenom());
        textViewDate.setText(currentVisite.getDate());
       // textViewCommentaire.setText(currentVisite.getCommentaire());


        return listItemView;
    }
}
