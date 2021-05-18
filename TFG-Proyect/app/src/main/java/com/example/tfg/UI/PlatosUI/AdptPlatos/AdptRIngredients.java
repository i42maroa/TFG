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
import com.example.tfg.Sistems.SistemaPlatos.models.Ingrediente;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteCantidad;
import com.example.tfg.Sistems.SistemaPlatos.models.PasoPlato;

import java.util.List;

public class AdptRIngredients extends RecyclerView.Adapter<AdptRIngredients.ViewHolder>  {

    private List<IngredienteCantidad> ing;
    private int layout;
    private OnItemClickListener itemClickListener; //Nuestro propio metodo de clickeo definido de forma posterior
    private Context context;

    public AdptRIngredients(List<IngredienteCantidad> ing, int layout, OnItemClickListener itemClickListener) {
        this.ing = ing;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewNombre;
        public ImageView imageViewIngrediente;
        public TextView textViewCantidad;
        public TextView textViewCategoria;
        public TextView textViewGramos;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNombre = (TextView) itemView.findViewById(R.id.textViewNombreIngrediente);
            imageViewIngrediente = (ImageView) itemView.findViewById(R.id.imageViewIngredienteUnico);
            textViewCantidad = (TextView) itemView.findViewById(R.id.textViewIngredienteCantidadUnico);
            textViewCategoria = (TextView) itemView.findViewById(R.id.textViewNombreCategoria);
            textViewGramos = (TextView) itemView.findViewById(R.id.textViewIngredienteGramos);
        }

        public void bind(final IngredienteCantidad ingrediente, final AdptRIngredients.OnItemClickListener listener){
            textViewNombre.setText(ingrediente.getIngrediente().getNombre().toUpperCase());
            imageViewIngrediente.setImageResource(ingrediente.getIngrediente().getImage());
            textViewCantidad.setText(((ingrediente.getCantidad() <= 1 )? "" : ingrediente.getCantidad()) + " " + ingrediente.getTipo());
            textViewCategoria.setText(ingrediente.getIngrediente().getCategoria().toUpperCase());
            textViewGramos.setText(ingrediente.getGramos() + " g");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(ingrediente, getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(ingrediente, getAdapterPosition());
                    return true;
                }
            });


        }
    }

    public interface OnItemClickListener {
        void onItemClick(IngredienteCantidad ingrediente, int position);
        void onItemLongClick(IngredienteCantidad ingrediente, int position);
    }

    @NonNull
    @Override
    public AdptRIngredients.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        AdptRIngredients.ViewHolder vh = new AdptRIngredients.ViewHolder(v);
        this.context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdptRIngredients.ViewHolder holder, int position) {
        holder.bind(ing.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return ing.size();
    }
}
