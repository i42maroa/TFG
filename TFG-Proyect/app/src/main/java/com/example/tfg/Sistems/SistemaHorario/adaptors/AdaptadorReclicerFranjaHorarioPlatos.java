package com.example.tfg.Sistems.SistemaHorario.adaptors;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;

import java.util.List;

public class AdaptadorReclicerFranjaHorarioPlatos extends RecyclerView.Adapter<AdaptadorReclicerFranjaHorarioPlatos.ViewHolder>{

    private List<Plato> platos;
    private int layout;
    private OnItemClickListener itemClickListener; //Nuestro propio metodo de clickeo definido de forma posterior
    private Context context;
    private int idFranja;


    public AdaptadorReclicerFranjaHorarioPlatos(int idFranja, List<Plato> platos,  int layout, OnItemClickListener itemClickListener) {
        this.platos = platos;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
        this.idFranja = idFranja;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageView = (ImageView) itemView.findViewById(R.id.imageViewItemFranjaHorarioPlato);
        }

        //Volcado de datos
        public void bind(final Plato plato, final OnItemClickListener listener){ //Se pasa los datos, si fuera una clase se pasaría la clase

            imageView.setImageResource(plato.getImage());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(idFranja, plato, getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(idFranja, plato, getAdapterPosition());
                    return false;
                }
            });

        }
     }


    public interface OnItemClickListener {
        void onItemClick(int idFranja, Plato plato, int position);
        void onItemLongClick(int idFranja, Plato plato, int position);
    }


    @NonNull
    @Override
    public AdaptadorReclicerFranjaHorarioPlatos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        AdaptadorReclicerFranjaHorarioPlatos.ViewHolder vh = new AdaptadorReclicerFranjaHorarioPlatos.ViewHolder(v);
        this.context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorReclicerFranjaHorarioPlatos.ViewHolder holder, int position) {
        holder.bind(platos.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

}