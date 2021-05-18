package com.example.tfg.Sistems.SistemaHorario.Fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaAlmacenamiento.Adaptador.AdptReciclerListaCompra;
import com.example.tfg.Sistems.SistemaAlmacenamiento.Adaptador.ListaCompraAdapter;
import com.example.tfg.Sistems.SistemaHorario.models.Dia;
import com.example.tfg.Sistems.SistemaHorario.models.FranjaHorario;
import com.example.tfg.Sistems.SistemaHorario.models.Semana;
import com.example.tfg.Sistems.SistemaPlatos.models.Ingrediente;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteCantidad;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteLista;
import com.example.tfg.Sistems.SistemaPlatos.models.PasoPlato;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.Sistems.SistemaPlatos.models.VNut;

import io.realm.Realm;
import io.realm.RealmList;

import static android.content.Context.CLIPBOARD_SERVICE;


public class ListaCompraFragment extends Fragment {

    private Realm realm;

    private ListView listView;
    private TextView vacio;
    private RecyclerView recyclerLista;
    private RecyclerView.Adapter adptLista;
    private RecyclerView.LayoutManager mLayoutManagerLista;
    private RealmList<IngredienteCantidad> ing;
    private RealmList<IngredienteLista> listaCompra;
    private RealmList<Dia> semana;

    private Button butExp;

    public static boolean ALIMENTO_EN_LISTA = true;
    public static boolean ALIMENTO_NO_ENCONTRADO = false;

    private IngredienteCantidad ingredienteAux;

    public ListaCompraFragment() {
        // Required empty public constructor
    }


    public static ListaCompraFragment newInstance(String param1, String param2) {
        ListaCompraFragment fragment = new ListaCompraFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerLista.setHasFixedSize(true);
        recyclerLista.setItemAnimator(new DefaultItemAnimator());
        recyclerLista.setLayoutManager(mLayoutManagerLista);
        recyclerLista.setAdapter(adptLista);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_compra, container, false);
        setRealm();
        setReferencias(view);

        butExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aux = "";

                for(int i=0; i<listaCompra.size();i++){
                    aux = aux + listaCompra.get(i).getIngrediente().getNombre() + " x" + listaCompra.get(i).getCantidad() + " (" + listaCompra.get(i).getGramos() + "g)\n";
                }

                ClipData clip = ClipData.newPlainText("text", aux);
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getContext(), "Lista copiada al cortapapeles", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void setRealm(){
        realm = Realm.getDefaultInstance();
        semana = realm.where(Semana.class).equalTo("id", 1).findFirst().getDias();
        ing= new RealmList<IngredienteCantidad>();
        listaCompra = new RealmList<IngredienteLista>();
        obtenerIngredientes();

    }

    private void setReferencias(View view){
        recyclerLista = (RecyclerView) view.findViewById(R.id.fragmentListaCompraLV);
        vacio = (TextView) view.findViewById(R.id.fragmentListaCompraTextVacio);
        butExp = (Button) view.findViewById(R.id.fListaCompraButton);
        mLayoutManagerLista = new LinearLayoutManager(view.getContext());
        adptLista = new AdptReciclerListaCompra(listaCompra, R.layout.list_view_item_lista_compra, new AdptReciclerListaCompra.OnItemClickListener() {

            @Override
            public void onItemClick(IngredienteLista lista, int position) {

            }

            @Override
            public void onItemLongClick(IngredienteLista ingre, int position) {
                showDialogDeleteIngredienteLista(ingre);
            }
        });
        comprobarVacio();

    }
    private void comprobarVacio(){
        if (listaCompra.isEmpty()) {
            recyclerLista.setVisibility(View.GONE);
            vacio.setVisibility(View.VISIBLE);
        }
        else {
            recyclerLista.setVisibility(View.VISIBLE);
            vacio.setVisibility(View.GONE);
        }
    }


    private void obtenerIngredientes(){
        Dia diaAux;
        FranjaHorario franjaHorarioAux;
        Plato platoAux;
        IngredienteCantidad iCAux;

        for (int i=0; i<semana.size();i++){
            diaAux = semana.get(i);

           for(int j=0; j<4; j++){
               franjaHorarioAux = diaAux.getFranjas().get(j);

                for( int x=0; x<franjaHorarioAux.getPlatos().size(); x++){
                    platoAux = franjaHorarioAux.getPlatos().get(x);

                    for( int y=0; y < platoAux.getIngredientes().size(); y++){
                        iCAux = platoAux.getIngredientes().get(y);

                        if(listaContiene(iCAux)) {

                        }
                        else
                            listaCompra.add(new IngredienteLista(iCAux.getIngrediente(), iCAux.getCantidad(), iCAux.getGramos()));
                    }
                }
            }
        }

    }

    private boolean listaContiene(IngredienteCantidad ingrediente){
        for(int i=0;i<listaCompra.size();i++){
            if (listaCompra.get(i).getIngrediente().getId() == ingrediente.getIngrediente().getId()){
                listaCompra.get(i).sumarIngrediente(ingrediente);
                return ALIMENTO_EN_LISTA;
            }
        }
        return ALIMENTO_NO_ENCONTRADO;
    }



    public void deleteIngredienteLista(IngredienteLista ingredienteAux){
        realm.beginTransaction();
        listaCompra.remove(ingredienteAux);
        realm.commitTransaction();
        Toast.makeText(getContext(), "Ingrediente eliminado", Toast.LENGTH_SHORT).show();
        adptLista.notifyDataSetChanged();
        comprobarVacio();
    }

    public void showDialogDeleteIngredienteLista(final IngredienteLista ingredienteAux){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_eliminar, null);
        builder.setView(viewInflater);

        TextView text = (TextView) viewInflater.findViewById(R.id.dialog_eliminarPregunta);

        text.setText("¿DESEA ELIMINAR " + ingredienteAux.getIngrediente().getNombre().toUpperCase() + " DE LA LISTA?");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteIngredienteLista(ingredienteAux);
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
}