package com.example.tfg.UI.PlatosUI.AdptPlatos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.models.Ingrediente;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteCantidad;

import java.util.List;

public class AdptIngredients extends BaseAdapter {

    private Context context;
    private List<IngredienteCantidad> list;
    private int layout;

    public AdptIngredients(Context context, List<IngredienteCantidad> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public IngredienteCantidad getItem(int position) {
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
            vh.textViewNombre = (TextView) convertView.findViewById(R.id.textViewNombreIngrediente);
            vh.imageViewIngrediente = (ImageView) convertView.findViewById(R.id.imageViewIngredienteUnico);
            vh.textViewCantidad = (TextView) convertView.findViewById(R.id.textViewIngredienteCantidadUnico);
            vh.textViewCategoria = (TextView) convertView.findViewById(R.id.textViewNombreCategoria);
            vh.textViewGramos = (TextView) convertView.findViewById(R.id.textViewIngredienteGramos);

            convertView.setTag(vh);
        }else
            vh = (ViewHolder) convertView.getTag();

        IngredienteCantidad ingrediente = list.get(position);

        vh.textViewNombre.setText(ingrediente.getIngrediente().getNombre().toUpperCase());
        vh.imageViewIngrediente.setImageResource(ingrediente.getIngrediente().getImage());
        vh.textViewCantidad.setText(ingrediente.getCantidad() + " " + ingrediente.getTipo());
        vh.textViewCategoria.setText(ingrediente.getIngrediente().getCategoria().toUpperCase());
        vh.textViewGramos.setText(ingrediente.getGramos() + " g");

        return convertView;
    }

    public class ViewHolder{
        TextView textViewNombre;
        ImageView imageViewIngrediente;
        TextView textViewCantidad;
        TextView textViewCategoria;
        TextView textViewGramos;
    }
}
