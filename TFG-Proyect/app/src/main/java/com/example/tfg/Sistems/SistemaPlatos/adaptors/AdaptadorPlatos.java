package com.example.tfg.Sistems.SistemaPlatos.adaptors;

import android.annotation.SuppressLint;
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

public class AdaptadorPlatos  extends BaseAdapter {

    private Context context;
    private List<Plato> list;
    private int layout;

    public AdaptadorPlatos(Context context, List<Plato> plato, int layout) {
        this.context = context;
        this.list = plato;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Plato getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.title = (TextView) convertView.findViewById(R.id.textViewPlatoTitle);
            vh.tiempElv = (TextView) convertView.findViewById(R.id.textViewPlatoTiempElav);
            vh.dificultad = (TextView) convertView.findViewById(R.id.textViewPlatoDificultad);
            vh.imagePlato = (ImageView) convertView.findViewById(R.id.imageViewPlatoUnico);
            vh.imagePlatoUsuario = (ImageView) convertView.findViewById(R.id.imagenPlatoUsuario);

            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        Plato plato = list.get(position);

        vh.title.setText(plato.getNombre().toUpperCase());
        vh.tiempElv.setText((plato.getTiempoElav() == 0)? "" : plato.getTiempoElav() + " min");
        vh.dificultad.setText((plato.getTiempoElav() == 0)? "" : plato.getDificultad());
        vh.imagePlato.setImageResource(plato.getImage());
        if(plato.getModCreacion() == Plato.PLATO_USU)
            vh.imagePlatoUsuario.setImageResource(R.drawable.category_usuario);
        //Picasso.with(context).load(plato.getImage()).fit().into(vh.imagePlato);

        return convertView;
    }

    public class ViewHolder{
        TextView title, tiempElv, dificultad;
        ImageView imagePlato, imagePlatoUsuario;
    }
}
