package com.example.tfg.UI.PerfilUI.StorageFragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg.FormularioInicio;
import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaAlmacenamiento.Adaptador.AlmacenamientoAdapter;
import com.example.tfg.Sistems.SistemaAlmacenamiento.Models.Almacenamiento;
import com.example.tfg.fragments.adaptors.iDataListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import static com.example.tfg.Sistems.SistemaAlmacenamiento.Models.Almacenamiento.TIPO_FRIGORIFICO;


public class AlmacenamientoFragment extends Fragment {

    private Realm realm;

    private ListView listView;
    private TextView vacio;
    private AlmacenamientoAdapter almacenamientoAdapter;
    private RealmResults<Almacenamiento> alm;

    private FloatingActionButton fab;

    private iDataListener callback;

    public AlmacenamientoFragment() {
    }


    public static AlmacenamientoFragment newInstance(String param1, String param2) {
        AlmacenamientoFragment fragment = new AlmacenamientoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
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
        View view = inflater.inflate(R.layout.fragment_almacenamiento, container, false);

        setRealm();
        setReferencias(view);
        setListViewListener(listView);
        setFloatingButtonListener(fab);
        registerForContextMenu(listView);
        setAlmacenamientoChangeListener(alm);

        return view;
    }

    private void setRealm(){
        realm = Realm.getDefaultInstance();
        alm = realm.where(Almacenamiento.class).findAll();
    }

    private void setReferencias(View view){
        listView = (ListView) view.findViewById(R.id.fragmentAlmacenamientoListView);
        vacio = (TextView) view.findViewById(R.id.fragmentAlmacenamientoTextVacio);
        fab = (FloatingActionButton) view.findViewById(R.id.fragmentAlmacenamientoFloatingButton);
        almacenamientoAdapter = new AlmacenamientoAdapter(view.getContext(), alm, R.layout.list_view_item_almacenamiento);
        listView.setAdapter(almacenamientoAdapter);
        listView.setEmptyView(vacio);
    }

    private void setListViewListener(final ListView listView){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.enviarIdAlmacen(alm.get(position).getId());
            }
        });
    }

    private void setFloatingButtonListener(final FloatingActionButton floatingButtonListener){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForIntroducirAlmacen("Nuevo Almacenamiento", "Introduce los parámetros para el nuevo almacenamiento");
            }
        });
    }

    private void setAlmacenamientoChangeListener(final  RealmResults<Almacenamiento> alm ){
        alm.addChangeListener(new RealmChangeListener<RealmResults<Almacenamiento>>() {
            @Override
            public void onChange(RealmResults<Almacenamiento> almacenamientos) {
                almacenamientoAdapter.notifyDataSetChanged();
            }
        });
    }

    //CRUD
    //--------------------------
    private void createNewAlmacenamiento(String nombreAlmacen, String tipo) {
        realm.beginTransaction();
        Almacenamiento almacenamiento = new Almacenamiento(nombreAlmacen, tipo);
        realm.copyToRealm(almacenamiento);
        realm.commitTransaction();
    }

    private void deleteAlmacenamiento(Almacenamiento almacenamiento){
        realm.beginTransaction();
        almacenamiento.deleteFromRealm();
        realm.commitTransaction();
    }

    private void editAlmacen(String newName, Almacenamiento almacenamiento){
        realm.beginTransaction();
        almacenamiento.setNombreAlmacen(newName);
        realm.copyToRealmOrUpdate(almacenamiento);
        realm.commitTransaction();
    }


    //ALERTAS
    //---------
    private void showAlertForIntroducirAlmacen(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(title != null) builder.setTitle(title);


        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_crear_almacenamiento, null);
        builder.setView(viewInflater);

        final EditText etNombre = (TextInputEditText) viewInflater.findViewById(R.id.dialogCrearAlmacen);
        final TextInputLayout tiNombre = (TextInputLayout) viewInflater.findViewById(R.id.dialogCrearAlmacenTi);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(etNombre.getText().toString().trim().isEmpty()){
                    tiNombre.setError(FormularioInicio.CAMPO_INCOMPLETO);
                    Toast.makeText(getContext(), "Error al introducir almacenamiento", Toast.LENGTH_SHORT).show();
                }
                else{
                    String almacenName =etNombre.getText().toString().trim(); //Trim es para quitar los espacios iniciales o finales
                    createNewAlmacenamiento(almacenName, TIPO_FRIGORIFICO);
                }

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void showAlertForEditingAlmacen(String title, String message, final Almacenamiento almacenamiento){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(title != null) builder.setTitle(title);

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_crear_almacenamiento, null);
        builder.setView(viewInflater);

        final EditText etNombre = (TextInputEditText) viewInflater.findViewById(R.id.dialogCrearAlmacen);
        final TextInputLayout tiNombre = (TextInputLayout) viewInflater.findViewById(R.id.dialogCrearAlmacenTi);
        etNombre.setText(almacenamiento.getNombreAlmacen());

        builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String AlmNombre = etNombre.getText().toString().trim(); //Trim es para quitar los espacios iniciales o finales

                if(AlmNombre.length() == 0)
                    Toast.makeText(getContext(), "Error al modificar el nombre", Toast.LENGTH_SHORT).show();
                else{
                    editAlmacen(AlmNombre, almacenamiento);
                    Toast.makeText(getContext(), "Nombre modificado correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(alm.get(info.position).getNombreAlmacen());
        getActivity().getMenuInflater().inflate(R.menu.context_menu_plato_activity, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.edit_plato:
                showAlertForEditingAlmacen("Edit plato", "Change de name of plate", alm.get(info.position));
                return true;
            case R.id.delete_plato:
                deleteAlmacenamiento(alm.get(info.position));
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}