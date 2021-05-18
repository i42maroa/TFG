package com.example.tfg.Sistems.SistemaPlatos.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.models.Category;

import java.util.List;

public class AdaptadorReciclerCategoria extends RecyclerView.Adapter<AdaptadorReciclerCategoria.ViewHolder>  {

    private List<Category> categorias;
    private int layout;
    private OnItemClickListener itemClickListener; //Nuestro propio metodo de clickeo definido de forma posterior
    private Context context;


    public AdaptadorReciclerCategoria(List<Category> categorias, int layout, AdaptadorReciclerCategoria.OnItemClickListener itemClickListener) {
        this.categorias = categorias;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewNombre;
        public ImageView imageView;
        public String descripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNombre = (TextView) itemView.findViewById(R.id.listaCategoriasTextViewNombre);
            imageView = (ImageView) itemView.findViewById(R.id.listaCategoriasImageView);
        }

        public void bind(final Category category, final AdaptadorReciclerCategoria.OnItemClickListener listener){

            textViewNombre.setText(category.getNombreCategoria().toUpperCase());
            imageView.setImageResource(category.getImage());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(category, getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemClick(category, getAdapterPosition());
                    return true;
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Category category, int position);
        void onItemLongClick(Category category, int position);
    }


    @NonNull
    @Override
    public AdaptadorReciclerCategoria.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        AdaptadorReciclerCategoria.ViewHolder vh = new AdaptadorReciclerCategoria.ViewHolder(v);
        this.context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorReciclerCategoria.ViewHolder holder, int position) {
        holder.bind(categorias.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

}
