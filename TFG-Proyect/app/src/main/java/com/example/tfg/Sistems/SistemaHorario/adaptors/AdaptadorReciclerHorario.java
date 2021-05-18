package com.example.tfg.Sistems.SistemaHorario.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaHorario.models.Dia;
import com.example.tfg.Sistems.SistemaHorario.models.FranjaHorario;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;


public class AdaptadorReciclerHorario extends RecyclerView.Adapter<AdaptadorReciclerHorario.ViewHolder> {

    private List<Dia> dias;
    private int layout;
    private OnItemClickListener itemClickListener; //Nuestro propio metodo de clickeo definido de forma posterior
    private Context context;
    private RealmList<FranjaHorario> franjaHorario;
    private ArrayList<Integer> confCal;

    public AdaptadorReciclerHorario(List<Dia> dias, int layout, ArrayList<Integer> confCal, OnItemClickListener itemClickListener) {
        this.dias = dias;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
        this.confCal = confCal;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageViewDesayuno;
        public ImageView imageViewAlmuerzo;
        public ImageView imageViewMerienda;
        public ImageView imageViewCena;
        public TextView textViewDiaSem;
        public TextView textViewDia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textViewDiaSem = (TextView) itemView.findViewById(R.id.gridIHorarioDiaSem);
            this.textViewDia = (TextView) itemView.findViewById(R.id.gridIHorarioDia);
            this.imageViewDesayuno = (ImageView) itemView.findViewById(R.id.imageViewItemHorarioDesayuno);
            this.imageViewAlmuerzo = (ImageView) itemView.findViewById(R.id.imageViewItemHorarioAlmuerzo);
            this.imageViewMerienda = (ImageView) itemView.findViewById(R.id.imageViewItemHorarioCena);
            this.imageViewCena = (ImageView) itemView.findViewById(R.id.imageViewItemHorarioComplemento);
        }

        //Volcado de datos
        public void bind(final Dia dia, final OnItemClickListener listener){ //Se pasa los datos, si fuera una clase se pasaría la clase
            franjaHorario = dia.getFranjas();

            textViewDiaSem.setText(dia.getNombreDia());
            textViewDia.setText(String.valueOf(dia.getDia()));
            imageViewDesayuno.setImageResource(FranjaHorario.getColorFranja(franjaHorario.get(0).getCalFranja(), confCal.get(0)));
            imageViewAlmuerzo.setImageResource(FranjaHorario.getColorFranja(franjaHorario.get(1).getCalFranja(), confCal.get(1)));
            imageViewCena.setImageResource(FranjaHorario.getColorFranja(franjaHorario.get(3).getCalFranja(), confCal.get(2)));
            imageViewMerienda.setImageResource(FranjaHorario.getColorFranja(franjaHorario.get(2).getCalFranja(), confCal.get(3)));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(dia, getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //Toast.makeText(context, "Dia " + franjaHorario.getDia(), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Dia franjaHorario, int position);
    }

    @NonNull
    @Override
    public AdaptadorReciclerHorario.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        this.context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorReciclerHorario.ViewHolder holder, int position) {
        holder.bind(dias.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return dias.size();
    }

}
