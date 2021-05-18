package com.example.tfg.UI.PlatosUI.AdptPlatos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteCantidad;
import com.example.tfg.Sistems.SistemaPlatos.models.VNut;

import java.text.DecimalFormat;
import java.util.List;

public class AdptRVN extends RecyclerView.Adapter<AdptRVN.ViewHolder>{

    private List<Double> vNuts;
    private int layout;
    private OnItemClickListener itemClickListener; //Nuestro propio metodo de clickeo definido de forma posterior
    private Context context;
    private int cont = 0;

    public AdptRVN(List<Double> vNuts, int layout, OnItemClickListener itemClickListener) {
        this.vNuts = vNuts;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView parametro;
        public TextView valor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parametro = (TextView) itemView.findViewById(R.id.textViewItemVNParametro);
            valor = (TextView) itemView.findViewById(R.id.textViewItemVNValor);
        }

        public void bind(final Double vNut, final AdptRVN.OnItemClickListener listener){
            parametro.setText(VNut.getNameNut(cont));
            parametro.setTextColor(VNut.getColor(VNut.getNameNut(cont)));
            valor.setText(new DecimalFormat("0.00").format(vNut) + " g");
            cont++;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(VNut vNut, int position);
    }

    @NonNull
    @Override
    public AdptRVN.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        AdptRVN.ViewHolder vh = new AdptRVN.ViewHolder(v);
        this.context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdptRVN.ViewHolder holder, int position) {
        holder.bind(vNuts.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return vNuts.size();
    }
}
