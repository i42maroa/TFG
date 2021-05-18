package com.example.tfg.UI.HorarioUI.AdptHorario;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaHorario.adaptors.AdaptadorReclicerFranjaHorarioPlatos;
import com.example.tfg.Sistems.SistemaHorario.models.FranjaHorario;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdptLCatIntroducirFH;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdaptadorListPlatosIntroducirFranjaHorario;
import com.example.tfg.Sistems.SistemaPlatos.models.Category;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.fragments.adaptors.iDataListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static com.example.tfg.MainActivity.CALORIAS_PERSONA;
import static com.example.tfg.MainActivity.GO_TO_PLATO_UNICO;

public class AdptRFranjaHorario extends RecyclerView.Adapter<AdptRFranjaHorario.ViewHolder>  {

    private List<FranjaHorario> franjaHorarios;

    private int layout;
    private OnItemClickListener itemClickListener;
    private Context context;

    private ArrayList<RecyclerView> mRecyclerViewPlato;
    private RecyclerView.Adapter mAdapterPlato;
    private RecyclerView.Adapter mAdapterPlato1;
    private RecyclerView.Adapter mAdapterPlato2;
    private RecyclerView.Adapter mAdapterPlato3;
    private ArrayList<RecyclerView.LayoutManager> mLayoutManagerPlato;

    private RealmList<Plato> platos;
    private Button buttonAdd;

    private Realm realm;

    private iDataListener callback;

    private int MAX_PLATOS_FRANJA = 6;

    private AdaptadorReclicerFranjaHorarioPlatos.OnItemClickListener adptListener = new AdaptadorReclicerFranjaHorarioPlatos.OnItemClickListener() {
        @Override
        public void onItemClick(int idFranja, Plato plato, int position) {
            callback = (iDataListener) context;
            callback.sendIdPlates(plato.getId(), GO_TO_PLATO_UNICO);
        }

        @Override
        public void onItemLongClick(int idFranja, Plato plato, int position) {

        }


    };

    public AdptRFranjaHorario(List<FranjaHorario> franjaHorarios, int layout, AdptRFranjaHorario.OnItemClickListener itemClickListener) {
        this.franjaHorarios = franjaHorarios;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView textView;
        public ImageView colorImage;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textViewFranjaHorarioItem);
            colorImage = (ImageView) itemView.findViewById(R.id.cardViewFranjaHorarioItemImageView);

            mRecyclerViewPlato = new ArrayList<RecyclerView> (){{
                add((RecyclerView) itemView.findViewById(R.id.recyclerViewFranjaHorarioItemPlatos));
                add((RecyclerView) itemView.findViewById(R.id.recyclerViewFranjaHorarioItemPlatos));
                add((RecyclerView) itemView.findViewById(R.id.recyclerViewFranjaHorarioItemPlatos));
                add((RecyclerView) itemView.findViewById(R.id.recyclerViewFranjaHorarioItemPlatos));
            }};

            mLayoutManagerPlato = new ArrayList<RecyclerView.LayoutManager>() {{
               add(new GridLayoutManager(context, 3));
               add(new GridLayoutManager(context, 3));
               add(new GridLayoutManager(context, 3));
               add(new GridLayoutManager(context, 3));
            }};

            //mAdapterPlato = new ArrayList<RecyclerView.Adapter>();

            buttonAdd = (Button) itemView.findViewById(R.id.cardViewFranjaHorarioButtonAdd);
            realm = Realm.getDefaultInstance();

        }

        //Volcado de datos
        public void bind(final FranjaHorario franjaHorario, final int position, final AdptRFranjaHorario.OnItemClickListener listener){ //Se pasa los datos, si fuera una clase se pasaría la clase

            textView.setText(franjaHorario.getNombFranja(franjaHorario.getFranja()).toUpperCase());
            colorImage.setImageResource(FranjaHorario.getColorFranja( franjaHorario.getCalFranja(), CALORIAS_PERSONA));
            platos = franjaHorario.getPlatos();

            mAdapterPlato = new AdaptadorReclicerFranjaHorarioPlatos(franjaHorario.getId(), platos, R.layout.franja_horario_item_plato, adptListener);


            mRecyclerViewPlato.get(position).setItemAnimator(new DefaultItemAnimator());
            mRecyclerViewPlato.get(position).setLayoutManager(mLayoutManagerPlato.get(position));
            mRecyclerViewPlato.get(position).setAdapter(mAdapterPlato);


            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(franjaHorario.getPlatos().size() < MAX_PLATOS_FRANJA)
                        showAlertForCategory("Seleccione categoria", "Selecciona la categoria de platos", franjaHorario.getId());
                    else
                        Toast.makeText(context, "Cantidad máxima de platos almacenados", Toast.LENGTH_SHORT).show();
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(franjaHorario, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(FranjaHorario franjaHorario, int position);
    }

    @NonNull
    @Override
    public AdptRFranjaHorario.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        AdptRFranjaHorario.ViewHolder vh = new AdptRFranjaHorario.ViewHolder(v);
        this.context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdptRFranjaHorario.ViewHolder holder, int position) {
        holder.bind(franjaHorarios.get(position), position, itemClickListener);
    }

    @Override
    public int getItemCount() {
        return franjaHorarios.size();
    }



    //Alerted
    private void showAlertForCategory(String title, String message, final int idFranja){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if(title != null) builder.setTitle(title);
        if(message != null) builder.setMessage(message);

        View viewInflater = LayoutInflater.from(context).inflate(R.layout.dialog_create_plato, null);
        builder.setView(viewInflater);

        ListView listView = (ListView) viewInflater.findViewById(R.id.dialogBuscarPlatosListView);

        realm = Realm.getDefaultInstance();
        RealmResults<Category> cat = realm.where(Category.class).findAll();
        RealmResults<Plato> listPlato = realm.where(Plato.class).findAll();

        final RealmResults<Category> categorias = cat;

        AdptLCatIntroducirFH adaptadorCategoria = new AdptLCatIntroducirFH(viewInflater.getContext(), categorias, R.layout.list_view_item_categoria_introducir_plato);

        listView.setAdapter(adaptadorCategoria);

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertForAddPlato("Introduce plato", "Selecciona el plato que quiera introducir",  idFranja, categorias.get(position).getId());
                alertDialog.dismiss();
            }
        });
    }

    private void showAlertForAddPlato(String title, String message, final int idFranja, int idCategory){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if(title != null) builder.setTitle(title);
        if(message != null) builder.setMessage(message);

        View viewInflater = LayoutInflater.from(context).inflate(R.layout.dialog_create_plato, null);
        builder.setView(viewInflater);

        ListView listView = (ListView) viewInflater.findViewById(R.id.dialogBuscarPlatosListView);

        realm = Realm.getDefaultInstance();
        RealmResults<Category> cat = realm.where(Category.class).findAll();

        final RealmList<Plato> listPlatoFinal = realm.where(Category.class).equalTo("id", idCategory).findFirst().getListPlatos();


        AdaptadorListPlatosIntroducirFranjaHorario adaptadorIngredientes = new AdaptadorListPlatosIntroducirFranjaHorario(viewInflater.getContext(), listPlatoFinal, R.layout.list_view_item_franja_introducir_plato);

        listView.setAdapter(adaptadorIngredientes);

        builder.setNegativeButton("Atras", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAlertForCategory("Seleccione categoria", "Selecciona la categoria de platos", idFranja);
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setRealmIntroducirPlato(idFranja, listPlatoFinal.get(position).getId());
                alertDialog.dismiss();
            }
        });
    }

    private void setRealmIntroducirPlato(final int idFranja, final int idPlatoSel){
        FranjaHorario franjaHorario;
        realm.beginTransaction();
        franjaHorario = realm.where(FranjaHorario.class).equalTo("id", idFranja).findFirst();
        franjaHorario.getPlatos().add(realm.where(Plato.class).equalTo("id", idPlatoSel).findFirst());
        franjaHorario.refrescarVN();
        realm.copyToRealmOrUpdate(franjaHorario);
        realm.commitTransaction();
        Toast.makeText(context, "Franja " + idFranja + ", plato " + idPlatoSel, Toast.LENGTH_SHORT ).show();
    }

    private void eliminarPlatoFranja(final int idFranja, Plato plato){
        FranjaHorario franjaHorario;
        realm.beginTransaction();
        franjaHorario = realm.where(FranjaHorario.class).equalTo("id", idFranja).findFirst();
        franjaHorario.getPlatos().remove(plato);
        franjaHorario.refrescarVN();
        realm.copyToRealmOrUpdate(franjaHorario);
        realm.commitTransaction();
    }




}

