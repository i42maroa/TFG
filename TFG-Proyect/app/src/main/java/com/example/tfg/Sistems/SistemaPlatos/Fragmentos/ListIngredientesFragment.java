package com.example.tfg.Sistems.SistemaPlatos.Fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tfg.R;
import com.example.tfg.UI.PlatosUI.AdptPlatos.AdptIngredients;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteCantidad;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.fragments.adaptors.iDataListener;

import io.realm.Realm;
import io.realm.RealmList;


public class ListIngredientesFragment extends Fragment {

    private ListView listView;
    private AdptIngredients adptIngredients;
    private RealmList<IngredienteCantidad> ingredientes;
    private Realm realm;

    private int idPlato;
    private Plato plato;

    private iDataListener callback;

    public ListIngredientesFragment() {
    }


    public static ListIngredientesFragment newInstance(String param1, String param2) {
        ListIngredientesFragment fragment = new ListIngredientesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_ingredientes, container, false);

        Bundle idFranjaRecibida = new Bundle(getArguments());
        if(idFranjaRecibida != null){
            idPlato = idFranjaRecibida.getInt("idPlato");
        }

        realm = Realm.getDefaultInstance();

        plato = realm.where(Plato.class).equalTo("id", idPlato).findFirst();

        ingredientes = plato.getIngredientes();

        listView = (ListView) view.findViewById(R.id.fragmentListIngredientesListView);

        adptIngredients = new AdptIngredients(view.getContext(), ingredientes, R.layout.list_view_item_ingrediente);
        listView.setAdapter(adptIngredients);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback = (iDataListener) getContext();
                callback.enviarIdIngrediente(ingredientes.get(position).getIngrediente().getId());
            }
        });

        return view;
    }
}