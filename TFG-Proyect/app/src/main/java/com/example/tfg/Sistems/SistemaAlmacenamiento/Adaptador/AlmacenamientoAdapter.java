package com.example.tfg.Sistems.SistemaAlmacenamiento.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaAlmacenamiento.Models.Almacenamiento;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdaptadorPlatos;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;

import java.util.List;

public class AlmacenamientoAdapter extends BaseAdapter {

    private Context context;
    private List<Almacenamiento> list;
    private int layout;

    public AlmacenamientoAdapter(Context context, List<Almacenamiento> plato, int layout) {
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

        AlmacenamientoAdapter.ViewHolder vh;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.textViewCantidad = (TextView)convertView.findViewById(R.id.lvItemTextViewCantPlatos);
            vh.textViewNombre = (TextView) convertView.findViewById(R.id.lvItemTextViewNombre);
            vh.imageView = (ImageView) convertView.findViewById(R.id.lvItemImageViewAlmacen);

            convertView.setTag(vh);
        }else{
            vh = (AlmacenamientoAdapter.ViewHolder) convertView.getTag();
        }

        Almacenamiento almacenamiento = list.get(position);

        vh.textViewNombre.setText(almacenamiento.getNombreAlmacen().toUpperCase());
        vh.textViewCantidad.setText("Platos: " + String.valueOf(almacenamiento.getPlatos().size() ));
        vh.imageView.setImageResource(R.drawable.frigorifico);

        return convertView;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView textViewNombre;
        TextView textViewCantidad;
    }
}
