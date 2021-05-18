package com.example.tfg.UI.PlatosUI.AdptPlatos;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.models.PasoPlato;

import java.util.List;

public class AdptPasosPlato extends RecyclerView.Adapter<AdptPasosPlato.ViewHolder>  {

    private List<PasoPlato> pasos;
    private int layout;
    private OnItemClickListener itemClickListener; //Nuestro propio metodo de clickeo definido de forma posterior
    private Context context;


    public AdptPasosPlato(List<PasoPlato> pasos, int layout, OnItemClickListener itemClickListener) {
        this.pasos = pasos;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tVNumber;
        public ImageView imageView;
        public TextView tVDescription;
        public TextView tVTime;
        public ImageView tiempoImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tVNumber = (TextView) itemView.findViewById(R.id.lVIPasoPlatoTextViewNum);
            imageView = (ImageView) itemView.findViewById(R.id.lVIPasoPlatoImageViewTarea);
            tVDescription = (TextView) itemView.findViewById(R.id.lVIPasoPlatoTextViewPaso);
            tVTime = (TextView) itemView.findViewById(R.id.lVIPasoPlatoTextViewTiempo);
            tiempoImage = (ImageView) itemView.findViewById(R.id.lVIPasoPlatoImageViewTiempo);
        }

        public void bind(final PasoPlato paso, int position, final OnItemClickListener listener){

            tVNumber.setText(String.valueOf(position + 1));
            imageView.setImageResource(paso.getImageCocina());
            tVDescription.setText(paso.getPaso());
            if(paso.getTime() == 0)
                tVTime.setText("");
            else{
                tVTime.setText(paso.getTime() + "'");
                tiempoImage.setImageResource(R.drawable.contador);
            }

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemClickLong(paso, getAdapterPosition());
                    return true;
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PasoPlato paso, int position);
        void onItemClickLong(PasoPlato paso, int position);
    }

    @NonNull
    @Override
    public AdptPasosPlato.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        AdptPasosPlato.ViewHolder vh = new AdptPasosPlato.ViewHolder(v);
        this.context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdptPasosPlato.ViewHolder holder, int position) {
        holder.bind(pasos.get(position), position, itemClickListener);
    }

    @Override
    public int getItemCount() {
        return pasos.size();
    }

}
