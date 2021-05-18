package com.example.tfg.Sistems.SistemaPlatos.adaptors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.models.Ingrediente;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorListViewDialogIngrediente  extends BaseAdapter {

    private Context context;
    private List<Ingrediente> list;
    private List<Ingrediente> copylist = new ArrayList<Ingrediente>();
    private int layout;

    public AdaptadorListViewDialogIngrediente(Context context, List<Ingrediente> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
        this.copylist.addAll(list);
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
            vh.textViewNombre = (TextView) convertView.findViewById(R.id.listViewItemBusquedaIngredienteTextView);
            vh.imageViewIngrediente = (ImageView) convertView.findViewById(R.id.listViewItemBusquedaIngredienteImageView);

            convertView.setTag(vh);
        }else
            vh = (ViewHolder) convertView.getTag();

        Ingrediente ingrediente = list.get(position);

        vh.textViewNombre.setText(ingrediente.getNombre().toUpperCase());
        vh.imageViewIngrediente.setImageResource(ingrediente.getImage());

        return convertView;
    }

    public void filtrar(String texto){

        list = new ArrayList<Ingrediente>();

        if (texto.length() == 0) {
            list.addAll(copylist);
        } else {

            // Recorre todos los elementos que contiene el ArrayList copiado
            // y dependiendo de si estos contienen el texto ingresado por el
            // usuario los agrega de nuevo al ArrayList que se carga en los
            // elementos del adaptador.
            for (Ingrediente ing : copylist) {
                String nom = ing.getNombre().trim().toUpperCase();

                if (nom.contains(texto.trim().toUpperCase()))
                    list.add(ing);
            }
        }
        notifyDataSetChanged();
    }

    public List<Ingrediente> getListaIngrediente() {
        return list;
    }

    public class ViewHolder{
        TextView textViewNombre;
        ImageView imageViewIngrediente;
    }
}
