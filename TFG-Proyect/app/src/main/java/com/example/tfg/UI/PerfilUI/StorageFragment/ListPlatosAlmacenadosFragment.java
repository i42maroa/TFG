package com.example.tfg.UI.PerfilUI.StorageFragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaAlmacenamiento.Models.Almacenamiento;
import com.example.tfg.Sistems.SistemaAlmacenamiento.Models.PlatoAlmacenado;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdptLCatIntroducirFH;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdaptadorListPlatosIntroducirFranjaHorario;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdaptadorPlatosAlmacenados;
import com.example.tfg.Sistems.SistemaPlatos.models.Category;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.app.Const;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ListPlatosAlmacenadosFragment extends Fragment {

    private Realm realm;
    private ListView listView;
    private AdaptadorPlatosAlmacenados almacenamientoAdapter;
    private RealmList<PlatoAlmacenado> platos;
    private Almacenamiento almacenamiento;
    private int idAlmacen, idPlatoSel;

    private TextView vacio;

    private FloatingActionButton fab;

    public ListPlatosAlmacenadosFragment() {
        // Required empty public constructor
    }

    public static ListPlatosAlmacenadosFragment newInstance(String param1, String param2) {
        ListPlatosAlmacenadosFragment fragment = new ListPlatosAlmacenadosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_platos_almacenados, container, false);

        Bundle idFranjaRecibida = new Bundle(getArguments());
        if(idFranjaRecibida != null)
            idAlmacen = idFranjaRecibida.getInt("idAlmacen");


        setRealm();
        setReferencias(view);
        setListViewListener(listView);
        setFloatingButtonListener(fab);
        setAlmacenamientoChangeListener(platos);
        return view;
    }

    private void setRealm(){
        realm = Realm.getDefaultInstance();
        almacenamiento = realm.where(Almacenamiento.class).equalTo("id", idAlmacen).findFirst();
        platos = almacenamiento.getPlatos();
    }

    private void setReferencias(View view){
        listView = (ListView) view.findViewById(R.id.fragmentListaPlatosAlmacenadosListView);
        vacio = (TextView) view.findViewById(R.id.fragmentListPlatosAlmTextVacio);
        almacenamientoAdapter = new AdaptadorPlatosAlmacenados(view.getContext(), platos, R.layout.list_view_item_platos_almacenados);
        fab = (FloatingActionButton) view.findViewById(R.id.fragmentListaPlatosAlmacenadosFloatingButton);
        listView.setAdapter(almacenamientoAdapter);
        listView.setEmptyView(vacio);
    }

    private void setListViewListener(final ListView listView){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void setFloatingButtonListener(final FloatingActionButton floatingButtonListener){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForCategory("Nuevo Almacenamiento", "Selecciona el plato ");
            }
        });
    }

    private void setAlmacenamientoChangeListener(final RealmList<PlatoAlmacenado> platos){
        platos.addChangeListener(new RealmChangeListener<RealmList<PlatoAlmacenado>>() {
            @Override
            public void onChange(RealmList<PlatoAlmacenado> platoAlmacenados) {
                almacenamientoAdapter.notifyDataSetChanged();
            }
        });
    }

    //DIALOG
    //---------------------------
    private void showAlertForCategory(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(title != null) builder.setTitle(title);
        if(message != null) builder.setMessage(message);

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_create_plato, null);
        builder.setView(viewInflater);

        ListView listView = (ListView) viewInflater.findViewById(R.id.dialogBuscarPlatosListView);

        realm = Realm.getDefaultInstance();
        RealmResults<Category> cat = realm.where(Category.class).notEqualTo("name", Const.CATEGORIA_STORE).findAll();
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
                showAlertForAddPlato("Introduce plato", "Selecciona el plato que quiera introducir",  categorias.get(position).getId());
                alertDialog.dismiss();
            }
        });
    }

    private void showAlertForAddPlato(String title, String message, int idCategory){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(title != null) builder.setTitle(title);
        if(message != null) builder.setMessage(message);

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_create_plato, null);
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
                showAlertForCategory("Seleccione categoria", "Selecciona la categoria de platos");
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idPlatoSel = listPlatoFinal.get(position).getId();
                setRealmIntroducirPlato();
                alertDialog.dismiss();
            }
        });
    }

    //CRUD
    //-------------

    private void setRealmIntroducirPlato(){
        realm.beginTransaction();
        PlatoAlmacenado platoAlmacenado = new PlatoAlmacenado(idPlatoSel, new Date());
        realm.copyToRealm(platoAlmacenado);
        almacenamiento.getPlatos().add(platoAlmacenado);
        Category alm = realm.where(Category.class).equalTo("name", Const.CATEGORIA_STORE).findFirst();
        alm.getListPlatos().add(realm.where(Plato.class).equalTo("id", platoAlmacenado.getIdPlato()).findFirst());
        realm.commitTransaction();
    }


}