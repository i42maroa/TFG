package com.example.tfg.UI.PlatosUI.FragmentsPlatos.SecondaryFragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.tfg.MainActivity;
import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdaptadorPlatos;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdptLCatIntroducirFH;
import com.example.tfg.Sistems.SistemaPlatos.models.Category;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteCantidad;
import com.example.tfg.Sistems.SistemaPlatos.models.PasoPlato;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.fragments.adaptors.iDataListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

public class ListPlatosFragment extends Fragment {

    private Realm realm;

    private ListView listView;
    private AdaptadorPlatos adaptadorPlatos;
    private RealmResults<Plato> platos;
    private Category category;

    private TextView vacio;

    ArrayList<Integer> idPlatos;
    private int idCat;
    private ArrayList<Plato> platosAux;

    private iDataListener callback;



    public ListPlatosFragment() {
        // Required empty public constructor
    }

    public static ListPlatosFragment newInstance(int idCategory) {
        
        Bundle args = new Bundle();
        args.putInt("idCategory", idCategory);

        ListPlatosFragment fragment = new ListPlatosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ListPlatosFragment newInstance(ArrayList<Integer> idPlatos) {
        ListPlatosFragment fragment = new ListPlatosFragment();
        Bundle bundleEnvio = new Bundle(); bundleEnvio.putIntegerArrayList("idPlatos", idPlatos);
        fragment.setArguments(bundleEnvio);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        super.onAttach(context);
        try{
            callback = (iDataListener) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString() + "should implement DataListener" );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_platos, container, false);

        realm = Realm.getDefaultInstance();
        idPlatos = new ArrayList<Integer>();
        platosAux = new ArrayList<Plato>();

        idCat = 0;

        if (getArguments().getIntegerArrayList("idPlatos") != null)
            idPlatos = getArguments().getIntegerArrayList("idPlatos");

        else
            idCat = getArguments().getInt("idCategory");


        setRealm();
        setParam(view);
        setListener(listView);


        return view;
    }

    private void setRealm(){
        Plato platoAux;
         if(idCat != 0){
             category = realm.where(Category.class).equalTo("id", idCat).findFirst();
             platos = category.getListPlatos().sort("nombre", Sort.ASCENDING);
         }
        else {

            for(int i=0; i< idPlatos.size(); i++){
                platoAux = realm.where(Plato.class).equalTo("id", idPlatos.get(i)).findFirst();
                platosAux.add(platoAux);
            }
       }
    }

    private void setParam(View view){

        listView = (ListView) view.findViewById(R.id.fragmentListPlatosListView);
        vacio = (TextView) view.findViewById(R.id.fragmentListPlatosTextVacio);

        adaptadorPlatos = new AdaptadorPlatos(view.getContext(), (idCat != 0)? platos : platosAux , R.layout.list_view_plato_item);
        listView.setAdapter(adaptadorPlatos);
        listView.setEmptyView(vacio);
    }

    private void setListener(final ListView listView){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.sendIdPlates((idCat != 0)? platos.get(position).getId() : platosAux.get(position).getId(), MainActivity.GO_TO_PLATO_UNICO);
            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(idCat == 0)
                    Toast.makeText(getContext(), "No se ha podido eliminar el plato", Toast.LENGTH_SHORT).show();
                else{
                    if(platos.get(position).getModCreacion() == Plato.PLATO_PRED)
                        Toast.makeText(getContext(), "No se pueden eliminar platos predefinidos", Toast.LENGTH_SHORT).show();
                    else
                        showDialogDeletePaso(platos.get(position));
                }

                return false;
            }
        });

    }

    public void showDialogDeletePaso(final Plato plato){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_eliminar, null);
        builder.setView(viewInflater);

        TextView text = (TextView) viewInflater.findViewById(R.id.dialog_eliminarPregunta);
        TextView text2 = (TextView) viewInflater.findViewById(R.id.dialog_eliminarPregunta2);

        text.setText("¿DESEA ELIMINAR EL PLATO " + plato.getNombre() + " DE LA LISTA?");
        text2.setText("Si acepta se eliminará de las franjas en las que se encuentre introducido");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eliminarPlato(plato);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void eliminarPlato(Plato plato){
        realm.beginTransaction();
        plato.deleteFromRealm();
        realm.commitTransaction();
        adaptadorPlatos.notifyDataSetChanged();
        Toast.makeText(getContext(), "Plato eliminado", Toast.LENGTH_SHORT).show();

    }
}