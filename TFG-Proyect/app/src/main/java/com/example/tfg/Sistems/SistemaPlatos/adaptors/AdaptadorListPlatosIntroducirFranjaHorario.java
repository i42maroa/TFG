package com.example.tfg.Sistems.SistemaPlatos.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.models.Ingrediente;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;

import java.util.List;

public class AdaptadorListPlatosIntroducirFranjaHorario extends BaseAdapter {

    private Context context;
    private List<Plato> list;
    private int layout;

    public AdaptadorListPlatosIntroducirFranjaHorario(Context context, List<Plato> list, int layout) {
        this.context = context;
        this.list = list;
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
            vh.textViewNombre = (TextView) convertView.findViewById(R.id.listViewItemFranjaHorarioIntroducirPlatoTextView);
            vh.imageViewIngrediente = (ImageView) convertView.findViewById(R.id.listViewItemFranjaHorarioIntroducirPlatoImageView);
            convertView.setTag(vh);
        }
        else
            vh = (ViewHolder) convertView.getTag();

        Plato plato = list.get(position);

        vh.textViewNombre.setText(plato.getNombre().toUpperCase());
        vh.imageViewIngrediente.setImageResource(plato.getImage());

        return convertView;
    }

    public class ViewHolder{
        TextView textViewNombre;
        ImageView imageViewIngrediente;
    }
}
