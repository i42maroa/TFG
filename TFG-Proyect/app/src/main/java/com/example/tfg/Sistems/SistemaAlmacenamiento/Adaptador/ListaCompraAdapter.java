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
import com.example.tfg.Sistems.SistemaPlatos.models.Ingrediente;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteCantidad;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteLista;

import java.util.List;

public class ListaCompraAdapter extends BaseAdapter {

    private Context context;
    private List<IngredienteLista> list;
    private int layout;

    public ListaCompraAdapter(Context context, List<IngredienteLista> ingrediente, int layout) {
        this.context = context;
        this.list = ingrediente;
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
            vh.textViewCantidad = (TextView)convertView.findViewById(R.id.lvItemListaCompraTextCantidad);
            vh.textViewNombre = (TextView) convertView.findViewById(R.id.lvItemListaCompraTextNombre);
            vh.textViewGramos = (TextView) convertView.findViewById(R.id.lvItemListaCompraTextGramos);
            vh.imageView = (ImageView) convertView.findViewById(R.id.lvItemListaCompraImageIngre);

            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        IngredienteLista ingredienteCantidad = list.get(position);

        vh.textViewNombre.setText(ingredienteCantidad.getIngrediente().getNombre().toUpperCase());
        vh.textViewCantidad.setText("x" + ingredienteCantidad.getCantidad());
        vh.textViewGramos.setText(String.valueOf(ingredienteCantidad.getGramos() + " g"));
        vh.imageView.setImageResource(ingredienteCantidad.getIngrediente().getImage());

        return convertView;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView textViewNombre;
        TextView textViewCantidad;
        TextView textViewGramos;
    }
}
