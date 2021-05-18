package com.example.tfg.Sistems.SistemaPlatos.Fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdaptadorPlatosFavoritos;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.fragments.adaptors.iDataListener;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.example.tfg.Sistems.SistemaPlatos.models.Plato.FAVORITO_SI;
import static com.example.tfg.MainActivity.GO_TO_PLATO_UNICO;

public class ListPlatosFavoritosFragment extends Fragment {

    private Realm realm;

    private ListView listView;
    private AdaptadorPlatosFavoritos adaptadorPlatosFavoritos;
    private RealmResults<Plato> platos;
    private TextView vacio;

    private iDataListener callback;

    public ListPlatosFavoritosFragment() {
    }

    public static ListPlatosFavoritosFragment newInstance(String param1, String param2) {
        ListPlatosFavoritosFragment fragment = new ListPlatosFavoritosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_platos_favoritos, container, false);

        setRealm();
        //platos.addChangeListener(this);
        defParametros(view);
        setListenerView(listView);

        return view;
    }

    private void setRealm(){
        realm = Realm.getDefaultInstance();
        platos = realm.where(Plato.class).equalTo("favorito", FAVORITO_SI).findAll();
    }

    private void defParametros(View view){
        listView = (ListView) view.findViewById(R.id.fragmentListPlatosFavoritoListView);
        adaptadorPlatosFavoritos = new AdaptadorPlatosFavoritos(view.getContext(), platos, R.layout.list_view_item_platos_favoritos);
        vacio = (TextView) view.findViewById(R.id.fragmentAlmacenamientoTextVacio);
        listView.setAdapter(adaptadorPlatosFavoritos);
        listView.setEmptyView(vacio);
    }

    private void setListenerView(final ListView listView){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback = (iDataListener) getContext();
                callback.sendIdPlates(platos.get(position).getId(), GO_TO_PLATO_UNICO);
            }
        });
    }
}