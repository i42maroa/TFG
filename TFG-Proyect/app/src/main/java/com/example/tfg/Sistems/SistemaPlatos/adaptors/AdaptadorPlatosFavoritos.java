package com.example.tfg.Sistems.SistemaPlatos.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;

import java.util.List;

public class AdaptadorPlatosFavoritos extends BaseAdapter {

    private Context context;
    private List<Plato> list;
    private int layout;

    public AdaptadorPlatosFavoritos(Context context, List<Plato> plato, int layout) {
        this.context = context;
        this.list = plato;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
            return list.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.title = (TextView) convertView.findViewById(R.id.listViewItemPlatoFavTextNombre);
            vh.tiempElv = (TextView) convertView.findViewById(R.id.listViewItemPlatoFavTextDificultad);
            vh.dificultad = (TextView) convertView.findViewById(R.id.listViewItemPlatoFavTextTiempo);
            vh.imagePlato = (ImageView) convertView.findViewById(R.id.listViewItemPlatoFavImage);

            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        Plato plato = list.get(position);

        vh.title.setText(plato.getNombre().toUpperCase());
        vh.tiempElv.setText(plato.getTiempoElav() + " min");
        vh.dificultad.setText(plato.getDificultad());
        vh.imagePlato.setImageResource(plato.getImage());

        //Picasso.with(context).load(plato.getImage()).fit().into(vh.imagePlato);

        return convertView;
    }

    public class ViewHolder{
        TextView title;
        TextView tiempElv;
        TextView dificultad;
        ImageView imagePlato;
    }
}
