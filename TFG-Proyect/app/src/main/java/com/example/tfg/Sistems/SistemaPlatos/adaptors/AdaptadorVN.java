package com.example.tfg.Sistems.SistemaPlatos.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tfg.R;

import java.util.List;

public class AdaptadorVN extends BaseAdapter {

    private Context context;
    private List<String> list; //parametros
    private List<String> val; //valores
    private int layout;

    public AdaptadorVN(Context context, List<String> list, List<String> val, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
        this.val = val;
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
            vh = new AdaptadorVN.ViewHolder();
            vh.parametro = (TextView) convertView.findViewById(R.id.textViewItemVNParametro);
            vh.valor = (TextView) convertView.findViewById(R.id.textViewItemVNValor);

            convertView.setTag(vh);

        }else
            vh = (AdaptadorVN.ViewHolder) convertView.getTag();


        vh.parametro.setText(list.get(position));
        vh.valor.setText(val.get(position));

        return convertView;
    }

    public class ViewHolder{
        TextView parametro;
        TextView valor;
    }
}
