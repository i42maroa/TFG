package com.example.tfg.app;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaHorario.models.FranjaHorario;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdptLCatIntroducirFH;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdaptadorListPlatosIntroducirFranjaHorario;
import com.example.tfg.Sistems.SistemaPlatos.models.Category;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class AlertsDialgPerson {

    public static void showAlertForCategory(String title, String message, final Context context, final FranjaHorario franjaHorario){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if(title != null) builder.setTitle(title);
        if(message != null) builder.setMessage(message);

        View viewInflater = LayoutInflater.from(context).inflate(R.layout.dialog_create_plato, null);
        builder.setView(viewInflater);

        ListView listView = (ListView) viewInflater.findViewById(R.id.dialogBuscarPlatosListView);

        Realm realm = Realm.getDefaultInstance();
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
                showAlertForAddPlato("Introduce plato", "Selecciona el plato que quiera introducir", context, franjaHorario, categorias.get(position).getId());
                alertDialog.dismiss();
            }
        });


    }


    public static void showAlertForAddPlato(String title, String message, final Context context, final FranjaHorario franjaHorario, int idCategory){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if(title != null) builder.setTitle(title);
        if(message != null) builder.setMessage(message);

        View viewInflater = LayoutInflater.from(context).inflate(R.layout.dialog_create_plato, null);
        builder.setView(viewInflater);

        ListView listView = (ListView) viewInflater.findViewById(R.id.dialogBuscarPlatosListView);

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Category> cat = realm.where(Category.class).findAll();

        final RealmList<Plato> listPlatoFinal = realm.where(Category.class).equalTo("id", idCategory).findFirst().getListPlatos();


        AdaptadorListPlatosIntroducirFranjaHorario adaptadorIngredientes = new AdaptadorListPlatosIntroducirFranjaHorario(viewInflater.getContext(), listPlatoFinal, R.layout.list_view_item_franja_introducir_plato);

        listView.setAdapter(adaptadorIngredientes);

        builder.setNegativeButton("Atras", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAlertForCategory("Seleccione categoria", "Selecciona la categoria de platos", context, franjaHorario);
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //setRealmIntroducirPlato(franjaHorario, listPlatoFinal.get(position).getId());
                alertDialog.dismiss();
            }
        });
    }
}
