package com.example.tfg.Sistems.SistemaAlmacenamiento.Adaptador;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdaptadorReciclerCategoria;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteLista;
import com.example.tfg.Sistems.SistemaPlatos.models.VNut;
import com.example.tfg.UI.PlatosUI.AdptPlatos.AdptRVN;

import java.text.DecimalFormat;
import java.util.List;

public class AdptReciclerListaCompra extends RecyclerView.Adapter<AdptReciclerListaCompra.ViewHolder>  {

    private List<IngredienteLista> lista;
    private int layout;
    private OnItemClickListener itemClickListener; //Nuestro propio metodo de clickeo definido de forma posterior
    private Context context;

    public AdptReciclerListaCompra(List<IngredienteLista> lista, int layout, OnItemClickListener itemClickListener) {
        this.lista = lista;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewNombre;
        TextView textViewCantidad;
        TextView textViewGramos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCantidad = (TextView) itemView.findViewById(R.id.lvItemListaCompraTextCantidad);
            textViewNombre = (TextView) itemView.findViewById(R.id.lvItemListaCompraTextNombre);
            textViewGramos = (TextView) itemView.findViewById(R.id.lvItemListaCompraTextGramos);
            imageView = (ImageView) itemView.findViewById(R.id.lvItemListaCompraImageIngre);

        }

        public void bind(final IngredienteLista ingredienteLista, final AdptReciclerListaCompra.OnItemClickListener listener){

            textViewNombre.setText(ingredienteLista.getIngrediente().getNombre().toUpperCase());
            textViewCantidad.setText("x" + ingredienteLista.getCantidad());
            textViewGramos.setText(String.valueOf(ingredienteLista.getGramos() + " g"));
            imageView.setImageResource(ingredienteLista.getIngrediente().getImage());

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(ingredienteLista, getAdapterPosition());
                    return true;
                }
            });
        }


    }

    public interface OnItemClickListener {
        void onItemClick(IngredienteLista lista, int position);
        void onItemLongClick(IngredienteLista ingre, int position);
    }

    @NonNull
    @Override
    public AdptReciclerListaCompra.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        AdptReciclerListaCompra.ViewHolder vh = new AdptReciclerListaCompra.ViewHolder(v);
        this.context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdptReciclerListaCompra.ViewHolder holder, int position) {
        holder.bind(lista.get(position), itemClickListener);

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
