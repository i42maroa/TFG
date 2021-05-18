package com.example.tfg.UI.PlatosUI.FragmentsPlatos.MainFragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdaptadorListViewDialogIngrediente;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdptLCatIngredientSearch;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdptLCatIntroducirFH;
import com.example.tfg.Sistems.SistemaPlatos.models.Category;
import com.example.tfg.Sistems.SistemaPlatos.models.CategoryIngredient;
import com.example.tfg.Sistems.SistemaPlatos.models.Ingrediente;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.fragments.adaptors.iToTabPlato;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

public class SearchMainFragment extends Fragment {

    private String TEXT_PREDEFINIDO_INGREDIENTES = "Ingredientes a introducir";

    private TextView textViewListIngr, textViewCal, textViewTiemp, textViewDif;
    private SeekBar seekBarCal, seekBarTiemp, seekBarDif;
    private Button buttonBuscar, buttonAddIng, buttonElmIng;

    private RealmList<Plato> platos;
    private Realm realm;

    private int calBusqueda = 0, tiempBusqueda = 0;
    private String difBusqueda;

    private ArrayList<Ingrediente> ingredientes;

    private iToTabPlato callback;

    private ArrayList<Ingrediente> listIngSel;
    private ArrayList<Integer> listIdPlatos ;

    public SearchMainFragment() {
        // Required empty public constructor
    }

    public static SearchMainFragment newInstance() {
        SearchMainFragment fragment = new SearchMainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_busqueda_platos, container, false);

        listIdPlatos = new ArrayList<Integer>();

        setReferences(view);
        setTexts();
        setSeeks();

        setBuscarListener(buttonBuscar);
        setAddListener(buttonAddIng);
        setDelListener(buttonElmIng);

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            this.callback = (iToTabPlato) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString() + "should implement DataListener" );
        }

    }

    private void setReferences(View view){
        textViewListIngr = (TextView) view.findViewById(R.id.fragmentBusquedaTextListIngredientes);
        textViewCal = (TextView) view.findViewById(R.id.fragmentBusquedaTextViewCal);
        textViewDif = (TextView) view.findViewById(R.id.fragmentBusquedaTextViewDificultad);
        textViewTiemp = (TextView) view.findViewById(R.id.fragmentBusquedaTextViewTiempo);
        seekBarCal = (SeekBar) view.findViewById(R.id.fragmentBusquedaSeekBarCal);
        seekBarTiemp = (SeekBar) view.findViewById(R.id.fragmentBusquedaSeekBarTiempo);
        seekBarDif = (SeekBar) view.findViewById(R.id.fragmentBusquedaSeekBarDificultad);
        buttonBuscar = (Button) view.findViewById(R.id.fragmentBusquedaButtonBuscar);
        buttonAddIng = (Button) view.findViewById(R.id.fragmentBusquedaButtonAddIngrediente);
        buttonElmIng = (Button) view.findViewById(R.id.fragmentBusquedaButtonEliminarIngrediente);

        ingredientes = new ArrayList<Ingrediente>();
        listIngSel = new ArrayList<Ingrediente>();
        realm = Realm.getDefaultInstance();
    }

    private void setTexts(){
        textViewListIngr.setText(TEXT_PREDEFINIDO_INGREDIENTES);
        textViewCal.setText(String.valueOf(seekBarCal.getProgress()) + " cal");
        textViewTiemp.setText(String.valueOf(seekBarTiemp.getProgress())+ " min");
        textViewDif.setText(getDificultad(seekBarDif.getProgress()));
    }

    private void setSeeks(){
        seekBarCal.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewCal.setText(String.valueOf(seekBarCal.getProgress() )+ " cal");
                calBusqueda = seekBarCal.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekBarTiemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewTiemp.setText(String.valueOf(seekBarTiemp.getProgress())+ " min");
                tiempBusqueda = seekBarTiemp.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekBarDif.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewDif.setText(getDificultad(seekBarDif.getProgress()));
                difBusqueda = getDificultad(seekBarDif.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    private void busquedaRealmPlato(){
        RealmResults<Plato> platosEncontrados;
        ArrayList<Integer> listFind = new ArrayList<Integer>();
        Ingrediente ingAux;
        Plato platoAux;
        listIdPlatos.clear();

        difBusqueda = getDificultad(seekBarDif.getProgress());

        platosEncontrados = realm.where(Plato.class)
                .lessThanOrEqualTo("calTotal", calBusqueda)
                .lessThanOrEqualTo("tiempoElav", tiempBusqueda)
                .equalTo("dificultad", difBusqueda)
                .sort("nombre", Sort.DESCENDING)
                .findAll();

        for(int i=0;i<platosEncontrados.size();i++)
            listIdPlatos.add(platosEncontrados.get(i).getId());

        for(int x=0; x < listIngSel.size();x++){
            for(int i=0;i<platosEncontrados.size();i++){
                platoAux = platosEncontrados.get(i);
                for(int j=0;j<platoAux.getIngredientes().size();j++){
                    ingAux = platoAux.getIngredientes().get(j).getIngrediente();

                    if(ingAux.getId() == listIngSel.get(x).getId()){
                        if(!listFind.contains(platoAux.getId()))
                            listFind.add(platoAux.getId());

                        break;
                    }
                }
            }
        }

        if(listIngSel.size() != 0)
            listIdPlatos = listFind;
    }


    private void setBuscarListener(final Button buttonBuscar){
        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                busquedaRealmPlato();

                if (listIdPlatos.size() == 0 ){
                    Toast.makeText(getContext(), "No hay resultados", Toast.LENGTH_SHORT).show();
                }
                else if(listIdPlatos.size() > 1){ //ha encontrado mas de un plato
                    Toast.makeText(getContext(), "Desliza para ver los resultados", Toast.LENGTH_SHORT).show();
                    callback.enviarListIdPlatos(listIdPlatos);
                }
                else{ //ha encontrado solo un plato
                    Toast.makeText(getContext(), "Desliza para ver los resultados", Toast.LENGTH_SHORT).show();
                    callback.enviarListIdPlatos(listIdPlatos);
                }
            }
        });
    }

    private void setAddListener(final Button buttonAdd){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForAddIngredients("SELECCIONE INGREDIENTE");
            }
        });
    }

    private void setDelListener(final Button buttonDel){
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listIngSel.size() > 0)
                    listIngSel.remove(listIngSel.size()-1);
                else
                    Toast.makeText(getContext(), "No hay ingredientes introducidos", Toast.LENGTH_SHORT).show();

                textViewListIngr.setText(getTextBusqueda(listIngSel));
            }
        });
    }



    private void showAlertForAddIngredients(String title){

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(title != null) builder.setTitle(title.toUpperCase());

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_buscar_ingrediente, null);

        builder.setView(viewInflater);

        final EditText input = (EditText) viewInflater.findViewById(R.id.dialogBuscarIngredienteAddIngrediente);
        ListView listView = (ListView) viewInflater.findViewById(R.id.dialogBuscarIngredienteListView);

        realm = Realm.getDefaultInstance();
        final RealmResults<Ingrediente> listIng = realm.where(Ingrediente.class)
                .sort("nombre", Sort.ASCENDING)
                .findAll();

        ArrayList<Ingrediente> listIngredientes = new ArrayList<Ingrediente>();
        final AdaptadorListViewDialogIngrediente adaptadorIngredientes = new AdaptadorListViewDialogIngrediente(viewInflater.getContext(), listIng, R.layout.list_view_item_busqueda_ingrediente);
        listView.setAdapter(adaptadorIngredientes);

        builder.setNegativeButton("Atrás", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adaptadorIngredientes.filtrar(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listIngSel.add(adaptadorIngredientes.getListaIngrediente().get(position));
                textViewListIngr.setText(getTextBusqueda(listIngSel));
                alertDialog.dismiss();
            }
        });

    }

    private String getTextBusqueda(final ArrayList<Ingrediente> listIngSel){
        String aux="";

        if(listIngSel.size() == 0)
            return TEXT_PREDEFINIDO_INGREDIENTES;

        else
            for(Ingrediente ing :listIngSel)
                aux = aux + ", " + ing.getNombre();

        return aux;
    }

    //Funcion que devuelve la dificultad dependiendo del progreso de la barra
    private String getDificultad(int progress){
        switch (progress){
            case 0:
                return Plato.DIFICULTAD_FACIL;
            case 1:
                return Plato.DIFICULTAD_MEDIA;
            case 2:
                return Plato.DIFICULTAD_DIFICIL;
        }
        return Plato.DIFICULTAD_FACIL;
    }
}