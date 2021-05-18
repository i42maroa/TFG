package com.example.tfg.UI.PlatosUI.FragmentsPlatos.SecondaryFragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg.FormularioInicio;
import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdaptadorListViewDialogIngrediente;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdptLCatIngredientSearch;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdptLCatIntroducirFH;
import com.example.tfg.Sistems.SistemaPlatos.models.Category;
import com.example.tfg.Sistems.SistemaPlatos.models.CategoryIngredient;
import com.example.tfg.Sistems.SistemaPlatos.models.Ingrediente;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteCantidad;
import com.example.tfg.Sistems.SistemaPlatos.models.PasoPlato;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.UI.PlatosUI.AdptPlatos.AdptPasosPlato;
import com.example.tfg.UI.PlatosUI.AdptPlatos.AdptRIngredients;
import com.example.tfg.app.Const;
import com.example.tfg.app.RellenarBD;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.example.tfg.R.color.colorAccent;
import static com.example.tfg.Sistems.SistemaPlatos.models.IngredienteCantidad.TIPOS_CANTIDADES;


public class CreacionPlato extends Fragment {

    private RecyclerView recyclerIngredientes;
    private RecyclerView.Adapter adptIngredientes;
    private RecyclerView.LayoutManager mLayoutManagerIng;
    private RecyclerView recyclerPasos;
    private RecyclerView.Adapter adptPasos;
    private RecyclerView.LayoutManager mLayoutManagerPasos;

    private Button butIntr, butPasos, butNPlato;


    private EditText etNombre, etTiempo;
    private TextInputLayout tiNombre, tiTiempo;

    private String text;
    private Realm realm;

    private Plato plato;
    private RealmList<IngredienteCantidad> Ling;
    private RealmList<PasoPlato> Lpasos;

    public CreacionPlato() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerIngredientes.setHasFixedSize(true);
        recyclerIngredientes.setItemAnimator(new DefaultItemAnimator());
        recyclerIngredientes.setLayoutManager(mLayoutManagerIng);
        recyclerIngredientes.setAdapter(adptIngredientes);

        recyclerPasos.setHasFixedSize(true);
        recyclerPasos.setItemAnimator(new DefaultItemAnimator());
        recyclerPasos.setLayoutManager(mLayoutManagerPasos);
        recyclerPasos.setAdapter(adptPasos);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_creacion_plato, container, false);

        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        plato = new Plato("", "", 0, "", R.drawable.category_usuario, Plato.PLATO_USU);
        Ling = plato.getIngredientes();
        Lpasos = plato.getPasosPlato();
        realm.commitTransaction();

        etTiempo = (TextInputEditText) view.findViewById(R.id.dialogNewPlatoTiempoET);
        tiTiempo = (TextInputLayout) view.findViewById(R.id.dialogNewPlatoTiempo);
        etNombre = (TextInputEditText) view.findViewById(R.id.dialogNewPlatoNombreET);
        tiNombre = (TextInputLayout) view.findViewById(R.id.dialogNewPlatoNombre);
        butIntr = (Button) view.findViewById(R.id.fCreacionIntroducirIngr);
        butNPlato = (Button) view.findViewById(R.id.fCreacionButtonCrearPlato);
        butPasos  = (Button) view.findViewById(R.id.fCreacionIntroducirPasos);
        recyclerIngredientes = (RecyclerView) view.findViewById(R.id.fCreacionPlatoRV);
        recyclerPasos = (RecyclerView) view.findViewById(R.id.fCreacionPlatoRVPasos);
        mLayoutManagerIng = new LinearLayoutManager(view.getContext());
        mLayoutManagerPasos = new LinearLayoutManager(view.getContext());



        final Spinner spinner = (Spinner) view.findViewById(R.id.dificultad_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.tipos_dificultad, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        adptIngredientes = new AdptRIngredients(Ling, R.layout.list_view_item_ingrediente, new AdptRIngredients.OnItemClickListener() {
            @Override
            public void onItemClick(IngredienteCantidad ingrediente, int position) {
                //callback.enviarIdIngrediente(ing.get(position).getIngrediente().getId());
            }

            @Override
            public void onItemLongClick(IngredienteCantidad ingrediente, int position) {
                //Toast.makeText(getContext(), "Ingrediente", Toast.LENGTH_SHORT).show();
                showDialogDelete(ingrediente);
            }
        });

        adptPasos = new AdptPasosPlato(Lpasos, R.layout.list_view_item_paso_platos, new AdptPasosPlato.OnItemClickListener() {
            @Override
            public void onItemClick(PasoPlato paso, int position) {
            }

            @Override
            public void onItemClickLong(PasoPlato paso, int position) {
                showDialogDeletePaso(paso);
            }
        });

        butIntr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForAddIngredients("SELECCIONE INGREDIENTE");
            }
        });

        butPasos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogNewPasosPlato("NUEVO PASO");
            }
        });

        butNPlato.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean comp = true;
                int aux;
                if(Ling.size() > 0){
                    if(etNombre.getText().toString().trim().isEmpty()){
                        tiNombre.setError(FormularioInicio.CAMPO_INCOMPLETO);
                        comp=false;
                    }
                    else
                        tiNombre.setError(null);


                    if(isEntero(etTiempo.getText().toString().trim())){
                        aux = Integer.parseInt(etTiempo.getText().toString().trim());

                        if(aux < 0 || aux > 200){
                            tiTiempo.setError("Introduce un valor válido");
                            comp =false;
                        }
                        else
                            tiTiempo.setError(null);
                    }
                    else{
                        tiTiempo.setError("Introduce un valor válido");
                        comp =false;
                    }


                    if(comp){
                        final String text = spinner.getSelectedItem().toString();
                        plato.setNombre(etNombre.getText().toString().trim());
                        plato.setDificultad(text);
                        plato.setTiempoElav(Integer.parseInt(etTiempo.getText().toString().trim()));
                        plato.refrescarVN();
                        showAlertForCategory("Selecciona categoria", "");

                        ListCategoryMainFragment listCategoryMainFragment = new ListCategoryMainFragment();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentPlatoGenericoContenedor, listCategoryMainFragment)
                                .addToBackStack(null);
                        transaction.commit();
                    }
                }else
                    Toast.makeText(getContext(), "Debe haber mínimo un ingrediente registrado", Toast.LENGTH_SHORT).show();
            }
        });




        return view;
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

        final AdaptadorListViewDialogIngrediente adaptadorIngredientes = new AdaptadorListViewDialogIngrediente(viewInflater.getContext(), listIng, R.layout.list_view_item_busqueda_ingrediente);
        listView.setAdapter(adaptadorIngredientes);


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


        builder.setNegativeButton("Atras", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogNewIngredientPlato("Introduce parámetros", adaptadorIngredientes.getListaIngrediente().get(position).getId());
                alertDialog.dismiss();
            }
        });

    }

    private void showDialogNewIngredientPlato(String title, int id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_nuevo_plato, null);
        builder.setView(viewInflater);


        TextView textNombre = (TextView) viewInflater.findViewById(R.id.dialogNewPlatoNombre);
        ImageView imageView = (ImageView) viewInflater.findViewById(R.id.dialogNewPlatoImage);

        final Spinner spinner = (Spinner) viewInflater.findViewById(R.id.planets_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.tipos_cantidad, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        final EditText etUnidad = (TextInputEditText) viewInflater.findViewById(R.id.dialogNewPlatoUnidadET);

        final EditText etPC = (TextInputEditText) viewInflater.findViewById(R.id.dialogNewPlatoCantidadET);


        final TextInputLayout tiUnidad = (TextInputLayout) viewInflater.findViewById(R.id.dialogNewPlatoUnidad);

        final TextInputLayout tiPC = (TextInputLayout) viewInflater.findViewById(R.id.dialogNewPlatoCantidad);

        realm = Realm.getDefaultInstance();
        final Ingrediente ing = realm.where(Ingrediente.class).equalTo("id", id).findFirst();

        textNombre.setText(ing.getNombre().toUpperCase());
        imageView.setImageResource(ing.getImage());


        builder.setPositiveButton("INTRODUCIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                boolean comp =  true;
                double aux;

                if(isEntero(etUnidad.getText().toString().trim())){
                    aux = Integer.parseInt(etUnidad.getText().toString().trim());

                    if(aux < 0 || aux > 20){
                        tiUnidad.setError("Introduce un valor válido");
                        comp =false;
                    }
                    else
                        tiUnidad.setError(null);
                }
                else{
                    tiUnidad.setError("Introduce un valor válido");
                    comp =false;
                }


                if(etPC.getText().toString().trim().isEmpty()){
                    tiPC.setError(FormularioInicio.CAMPO_INCOMPLETO);
                    comp =false;
                }
                else
                    tiPC.setError(null);

                if(comp){
                    String text = spinner.getSelectedItem().toString();

                    realm.beginTransaction();
                    IngredienteCantidad ingC = new IngredienteCantidad(ing,
                            Integer.parseInt(etUnidad.getText().toString().trim()),
                            text,
                            Integer.parseInt(etPC.getText().toString().trim()));
                    plato.getIngredientes().add(ingC);
                    realm.commitTransaction();

                    Ling=plato.getIngredientes();
                    adptIngredientes.notifyDataSetChanged();

                    Toast.makeText(getContext(), ing.getNombre() + " introducido", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getContext(), "Error al introducir el ingrediente", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Atrás", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAlertForAddIngredients("SELECCIONE INGREDIENTE");
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void showDialogNewPasosPlato(String title) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_nuevo_paso, null);
        builder.setView(viewInflater);

        final Spinner spinner = (Spinner) viewInflater.findViewById(R.id.tipo_paso_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.tipos_pasos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final String text = spinner.getSelectedItem().toString();

        final EditText etTiempo = (TextInputEditText) viewInflater.findViewById(R.id.dialogNewPasoPlatoTiempoET);
        final EditText etExplicacion = (TextInputEditText) viewInflater.findViewById(R.id.dialogNewPasoPlatoExplicacionET);
        final TextInputLayout tiTiempo = (TextInputLayout) viewInflater.findViewById(R.id.dialogNewPasoPlatoTiempo);
        final TextInputLayout tiExplicacion = (TextInputLayout) viewInflater.findViewById(R.id.dialogNewPasoPlatoExplicacion);


        builder.setPositiveButton("Introducir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                boolean comp =  true;
                double aux;

                if(etExplicacion.getText().toString().trim().isEmpty()){
                    tiExplicacion.setError(FormularioInicio.CAMPO_INCOMPLETO);
                    comp =false;
                }
                else
                    tiExplicacion.setError(null);

                if(isEntero(etTiempo.getText().toString().trim())){
                    aux = Integer.parseInt(etTiempo.getText().toString().trim());

                    if(aux < 0 || aux > 100){
                        tiTiempo.setError("Introduce un valor válido");
                        comp =false;
                    }
                    else
                        tiTiempo.setError(null);
                }
                else{
                    tiTiempo.setError("Introduce un valor válido");
                    comp =false;
                }

                if(comp){
                    String text = spinner.getSelectedItem().toString();
                    realm.beginTransaction();
                    plato.getPasosPlato().add(realm.copyToRealm(new PasoPlato(etExplicacion.getText().toString(), Integer.parseInt(etTiempo.getText().toString().trim()), PasoPlato.getImageCocinaText(text))));
                    realm.commitTransaction();

                    adptPasos.notifyDataSetChanged();

                }
                else
                    Toast.makeText(getContext(), "Error al introducir el paso", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlertForCategory(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(title != null) builder.setTitle(title);

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_create_plato, null);
        builder.setView(viewInflater);

        ListView listView = (ListView) viewInflater.findViewById(R.id.dialogBuscarPlatosListView);

        realm = Realm.getDefaultInstance();
        RealmResults<Category> cat = realm.where(Category.class).notEqualTo("name", Const.CATEGORIA_FAVORITE).notEqualTo("name", Const.CATEGORIA_STORE).findAll();

        final RealmResults<Category> categorias = cat;

        AdptLCatIntroducirFH adaptadorCategoria = new AdptLCatIntroducirFH(viewInflater.getContext(), categorias, R.layout.list_view_item_categoria_introducir_plato);

        listView.setAdapter(adaptadorCategoria);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                realm.beginTransaction();
                Category category = realm.where(Category.class).equalTo("id", categorias.get(position).getId()).findFirst();
                plato.setImage(category.getImage());
                realm.copyToRealmOrUpdate(plato);
                category.getListPlatos().add(plato);
                realm.commitTransaction();
                alertDialog.dismiss();
                Toast.makeText(getContext(), "Plato introducido en" + category.getNombreCategoria(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showDialogDelete( final IngredienteCantidad ing){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_eliminar, null);
        builder.setView(viewInflater);

        TextView text = (TextView) viewInflater.findViewById(R.id.dialog_eliminarPregunta);

        text.setText("¿DESEA ELIMINAR " + ing.getIngrediente().getNombre() + " DE LA LISTA?");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eliminarIng(ing);
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


    public void showDialogDeletePaso(final PasoPlato pas){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_eliminar, null);
        builder.setView(viewInflater);

        TextView text = (TextView) viewInflater.findViewById(R.id.dialog_eliminarPregunta);

        text.setText("¿DESEA ELIMINAR EL PASO DE LA LISTA?");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eliminarPaso(pas);
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

    public void eliminarIng(IngredienteCantidad ingrediente){
        realm.beginTransaction();
        plato.getIngredientes().remove(ingrediente);
        realm.commitTransaction();
        Ling = plato.getIngredientes();
        adptIngredientes.notifyDataSetChanged();
        Toast.makeText(getContext(), "INGREDIENTE ELIMINADO", Toast.LENGTH_SHORT).show();
    }

    public void eliminarPaso(PasoPlato pasoPlato){
        realm.beginTransaction();
        plato.getPasosPlato().remove(pasoPlato);
        realm.commitTransaction();
        adptPasos.notifyDataSetChanged();
        Toast.makeText(getContext(), "PASO ELIMINADO", Toast.LENGTH_SHORT).show();
    }

    public boolean isEntero(String numero){
        try{
            Integer.parseInt(numero);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}