package com.example.tfg.UI.HorarioUI.HorarioFragments;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaHorario.adaptors.AdaptadorReclicerFranjaHorarioPlatos;
import com.example.tfg.Sistems.SistemaPerfil.models.Usuario;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdaptadorListPlatosIntroducirFranjaHorario;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdptLCatIntroducirFH;
import com.example.tfg.Sistems.SistemaPlatos.models.Category;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.Sistems.SistemaPlatos.models.VNut;
import com.example.tfg.Sistems.SistemaHorario.models.Dia;
import com.example.tfg.Sistems.SistemaHorario.models.FranjaHorario;
import com.example.tfg.UI.PlatosUI.FragmentsPlatos.CharVNut;
import com.example.tfg.fragments.adaptors.iDataListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

import static com.example.tfg.MainActivity.GO_TO_PLATO_UNICO;


public class FranjaHorarioFragment extends Fragment  {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<RecyclerView> mRecyclerViewPlato;

    private RecyclerView.Adapter mAdapterPlato;
    private RecyclerView.Adapter mAdapterPlato1;
    private RecyclerView.Adapter mAdapterPlato2;
    private RecyclerView.Adapter mAdapterPlato3;


    private RecyclerView mRecyclerViewVN;
    private RecyclerView.Adapter adptVN;
    private RecyclerView.LayoutManager mLayoutManagerVN;

    private ArrayList<RecyclerView.LayoutManager> mLayoutManagerPlato;

    private Realm realm;
    private RealmList<FranjaHorario> franjasDia;

    private int diaHorario;
    private int mesHorario;

    private int calTotalDia;

    private Button buttonAdd;
    private Button buttonAdd1;
    private Button buttonAdd2;
    private Button buttonAdd3;

    private ImageView imageView;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;


    private TextView cantCal, cantProteina, cantHC, cantFib, cantAz, cantGr, cantSal;

    private VNut vNut;

    private int MAX_PLATOS_FRANJA = 6;

    private ArrayList<Integer> calPred;

    private iDataListener callback;

    private Dia dia;

    private PieChartView chart;

    private AdaptadorReclicerFranjaHorarioPlatos.OnItemClickListener adptListener = new AdaptadorReclicerFranjaHorarioPlatos.OnItemClickListener() {
        @Override
        public void onItemClick(int idFranja, Plato plato, int position) {
            callback = (iDataListener) getContext();
            callback.sendIdPlates(plato.getId(), GO_TO_PLATO_UNICO);
        }

        @Override
        public void onItemLongClick(int idFranja, Plato plato, int position) {
            eliminarPlatoFranja(idFranja, plato);
            Toast.makeText(getContext(), plato.getNombre() + " eliminado", Toast.LENGTH_SHORT).show();
        }


    };


    public FranjaHorarioFragment() {
        // Required empty public constructor
    }

    public static FranjaHorario newInstance() {
        FranjaHorario fragment = new FranjaHorario();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.franjas_horario_layout, container, false);

        Bundle idDiaRecibido = new Bundle(getArguments());

        if(idDiaRecibido != null){
            diaHorario = idDiaRecibido.getInt("dia");
            mesHorario = idDiaRecibido.getInt("mes");
        }

        setRealm();
        setmRecyclerView(view);
        setmAdapter(view);
        setButtonsClick();
        addChangeListenerFranja(franjasDia);
        setImageClickListeners();

        chart.setPieChartData(CharVNut.generateDataCharVNUT(dia.getvNuts()));
        setCharListener();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mRecyclerViewPlato.get(0).setHasFixedSize(true);
        mRecyclerViewPlato.get(0).setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewPlato.get(0).setLayoutManager(mLayoutManagerPlato.get(0));
        mRecyclerViewPlato.get(0).setAdapter(mAdapterPlato);

        mRecyclerViewPlato.get(1).setHasFixedSize(true);
        mRecyclerViewPlato.get(1).setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewPlato.get(1).setLayoutManager(mLayoutManagerPlato.get(1));
        mRecyclerViewPlato.get(1).setAdapter(mAdapterPlato1);

        mRecyclerViewPlato.get(2).setHasFixedSize(true);
        mRecyclerViewPlato.get(2).setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewPlato.get(2).setLayoutManager(mLayoutManagerPlato.get(2));
        mRecyclerViewPlato.get(2).setAdapter(mAdapterPlato2);

        mRecyclerViewPlato.get(3).setHasFixedSize(true);
        mRecyclerViewPlato.get(3).setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewPlato.get(3).setLayoutManager(mLayoutManagerPlato.get(3));
        mRecyclerViewPlato.get(3).setAdapter(mAdapterPlato3);
    }

    private void setRealm(){
        this.realm = Realm.getDefaultInstance();
        this.dia = realm.where(Dia.class).equalTo("dia", diaHorario).equalTo("mes", mesHorario).findFirst();
        this.franjasDia = dia.getFranjas();
        this.vNut = dia.getvNuts();

        calPred = new ArrayList<Integer>();
        Usuario us = realm.where(Usuario.class).findFirst();
        calPred.add(us.getCalDes());
        calPred.add(us.getCalAl());
        calPred.add(us.getCalCena());
        calPred.add(us.getCalComp());
        calTotalDia = us.getCalDes()+us.getCalAl()+us.getCalCena()+us.getCalComp();
    }

    private void addChangeListenerFranja(final RealmList<FranjaHorario> franjaHorario){
        franjaHorario.addChangeListener(new RealmChangeListener<RealmList<FranjaHorario>>() {
            @Override
            public void onChange(RealmList<FranjaHorario> franjaHorarios) {
                mAdapterPlato.notifyDataSetChanged();
                mAdapterPlato1.notifyDataSetChanged();
                mAdapterPlato2.notifyDataSetChanged();
                mAdapterPlato3.notifyDataSetChanged();
            }
        });
    }

    private void setmRecyclerView(final View view){

        mRecyclerViewPlato = new ArrayList<RecyclerView> (){{
            add((RecyclerView) view.findViewById(R.id.recyclerViewFranjaHorarioItemPlatos0));
            add((RecyclerView) view.findViewById(R.id.recyclerViewFranjaHorarioItemPlatos1));
            add((RecyclerView) view.findViewById(R.id.recyclerViewFranjaHorarioItemPlatos2));
            add((RecyclerView) view.findViewById(R.id.recyclerViewFranjaHorarioItemPlatos3));
        }};


        mLayoutManagerPlato = new ArrayList<RecyclerView.LayoutManager>() {{
            add(new GridLayoutManager(getContext(), 3));
            add(new GridLayoutManager(getContext(), 3));
            add(new GridLayoutManager(getContext(), 3));
            add(new GridLayoutManager(getContext(), 3));
        }};

        mLayoutManagerVN = new LinearLayoutManager(view.getContext());

        buttonAdd = (Button) view.findViewById(R.id.cardViewFranjaHorarioButtonAdd0);
        buttonAdd1 = (Button) view.findViewById(R.id.cardViewFranjaHorarioButtonAdd1);
        buttonAdd2 = (Button) view.findViewById(R.id.cardViewFranjaHorarioButtonAdd2);
        buttonAdd3 = (Button) view.findViewById(R.id.cardViewFranjaHorarioButtonAdd3);

        imageView = (ImageView) view.findViewById(R.id.cardViewFranjaHorarioItemImageView0);
        imageView1 = (ImageView) view.findViewById(R.id.cardViewFranjaHorarioItemImageView1);
        imageView2 = (ImageView) view.findViewById(R.id.cardViewFranjaHorarioItemImageView2);
        imageView3 = (ImageView) view.findViewById(R.id.cardViewFranjaHorarioItemImageView3);

        cantCal = (TextView) view.findViewById(R.id.franjaHorarioCaloria);
        cantProteina = (TextView) view.findViewById(R.id.franjaHorarioProteina);
        cantHC = (TextView) view.findViewById(R.id.franjaHorarioHC);
        cantFib = (TextView) view.findViewById(R.id.franjaHorarioFibra);
        cantAz = (TextView) view.findViewById(R.id.franjaHorarioAzucar);
        cantGr = (TextView) view.findViewById(R.id.franjaHorarioGrasa);
        cantSal = (TextView) view.findViewById(R.id.franjaHorarioSal);

        chart = (PieChartView) view.findViewById(R.id.piechartFranjaHorario);
    }



    private void setmAdapter(View view){
        mAdapterPlato = new AdaptadorReclicerFranjaHorarioPlatos(franjasDia.get(0).getId(), franjasDia.get(0).getPlatos(), R.layout.franja_horario_item_plato, adptListener);
        mAdapterPlato1 = new AdaptadorReclicerFranjaHorarioPlatos(franjasDia.get(1).getId(), franjasDia.get(1).getPlatos(), R.layout.franja_horario_item_plato, adptListener);
        mAdapterPlato2 = new AdaptadorReclicerFranjaHorarioPlatos(franjasDia.get(2).getId(), franjasDia.get(2).getPlatos(), R.layout.franja_horario_item_plato, adptListener);
        mAdapterPlato3 = new AdaptadorReclicerFranjaHorarioPlatos(franjasDia.get(3).getId(), franjasDia.get(3).getPlatos(), R.layout.franja_horario_item_plato, adptListener);


        actualizarFranjas();
    }

    private void setImageClickListeners(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), franjasDia.get(0).getvNuts().getCalorias() + " cal", Toast.LENGTH_SHORT).show();
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), franjasDia.get(1).getvNuts().getCalorias() + " cal", Toast.LENGTH_SHORT).show();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), franjasDia.get(2).getvNuts().getCalorias() + " cal", Toast.LENGTH_SHORT).show();
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), franjasDia.get(3).getvNuts().getCalorias() + " cal", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //Alerted
    private void showAlertForCategory(String title, String message, final int idFranja){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(title != null) builder.setTitle(title);
        if(message != null) builder.setMessage(message);

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_create_plato, null);
        builder.setView(viewInflater);

        ListView listView = (ListView) viewInflater.findViewById(R.id.dialogBuscarPlatosListView);

        realm = Realm.getDefaultInstance();
        RealmResults<Category> cat = realm.where(Category.class).findAll();
        RealmResults<Plato> listPlato = realm.where(Plato.class).findAll();

        final RealmResults<Category> categorias = cat;

        AdptLCatIntroducirFH adaptadorCategoria = new AdptLCatIntroducirFH(viewInflater.getContext(), categorias, R.layout.list_view_item_categoria_introducir_plato);

        listView.setAdapter(adaptadorCategoria);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
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

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(title != null) builder.setTitle(title);
        if(message != null) builder.setMessage(message);

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_create_plato, null);
        builder.setView(viewInflater);

        ListView listView = (ListView) viewInflater.findViewById(R.id.dialogBuscarPlatosListView);

        realm = Realm.getDefaultInstance();
        RealmResults<Category> cat = realm.where(Category.class).findAll();

        final RealmResults<Plato> listPlatoFinal = realm.where(Category.class)
                .equalTo("id", idCategory)
                .findFirst()
                .getListPlatos()
                .sort("nombre", Sort.ASCENDING);


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
        dia.refrescarVN();
        realm.copyToRealmOrUpdate(franjaHorario);
        realm.commitTransaction();

        vNut=dia.getvNuts();
        actualizarFranjas();
    }

    private void eliminarPlatoFranja(final int idFranja, Plato plato){
        FranjaHorario franjaHorario;
        realm.beginTransaction();
        franjaHorario = realm.where(FranjaHorario.class).equalTo("id", idFranja).findFirst();
        franjaHorario.getPlatos().remove(plato);
        franjaHorario.refrescarVN();
        dia.refrescarVN();
        realm.copyToRealmOrUpdate(franjaHorario);
        realm.commitTransaction();

        vNut=dia.getvNuts();
        actualizarFranjas();
    }

    private void setButtonsClick(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(franjasDia.get(0).getPlatos().size() < MAX_PLATOS_FRANJA)
                    showAlertForCategory("Seleccione categoria", "Selecciona la categoria de platos", franjasDia.get(0).getId());
                else
                    Toast.makeText(getContext(), "Cantidad máxima de platos almacenados", Toast.LENGTH_SHORT).show();
            }
        });
        buttonAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(franjasDia.get(1).getPlatos().size() < MAX_PLATOS_FRANJA)
                    showAlertForCategory("Seleccione categoria", "Selecciona la categoria de platos", franjasDia.get(1).getId());
                else
                    Toast.makeText(getContext(), "Cantidad máxima de platos almacenados", Toast.LENGTH_SHORT).show();
            }
        });
        buttonAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(franjasDia.get(2).getPlatos().size() < MAX_PLATOS_FRANJA)
                    showAlertForCategory("Seleccione categoria", "Selecciona la categoria de platos", franjasDia.get(2).getId());
                else
                    Toast.makeText(getContext(), "Cantidad máxima de platos almacenados", Toast.LENGTH_SHORT).show();
            }
        });
        buttonAdd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(franjasDia.get(3).getPlatos().size() < MAX_PLATOS_FRANJA)
                    showAlertForCategory("Seleccione categoria", "Selecciona la categoria de platos", franjasDia.get(3).getId());
                else
                    Toast.makeText(getContext(), "Cantidad máxima de platos almacenados", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public static int getColorCal(int numCal, int calPersona){
        if (numCal < calPersona-200)
            return (numCal == 0) ? Color.GRAY : ChartUtils.COLOR_GREEN;
        else if (numCal > calPersona)
            return ChartUtils.COLOR_RED;
        else
            return ChartUtils.COLOR_ORANGE;
    }

    private void actualizarFranjas(){

        imageView.setImageResource(FranjaHorario.getColorFranja(franjasDia.get(0).getCalFranja(), calPred.get(0)));
        imageView1.setImageResource(FranjaHorario.getColorFranja(franjasDia.get(1).getCalFranja(), calPred.get(1)));
        imageView2.setImageResource(FranjaHorario.getColorFranja(franjasDia.get(2).getCalFranja(), calPred.get(2)));
        imageView3.setImageResource(FranjaHorario.getColorFranja(franjasDia.get(3).getCalFranja(), calPred.get(3)));

        cantCal.setText(vNut.getCalorias() + " cal");
        cantCal.setTextColor(getColorCal(vNut.getCalorias(), calTotalDia));
        cantProteina.setText(new DecimalFormat("0.00").format(vNut.getProteinas()) + " g");
        cantHC.setText(new DecimalFormat("0.00").format(vNut.getHidratosCarbono()) + " g");
        cantFib.setText(new DecimalFormat("0.00").format(vNut.getFibra()) + " g");
        cantAz.setText(new DecimalFormat("0.00").format(vNut.getAzucares()) + " g");
        cantGr.setText(new DecimalFormat("0.00").format(vNut.getGrasas()) + " g");
        cantSal.setText(new DecimalFormat("0.00").format(vNut.getSal()) + " g");

        chart.setPieChartData(CharVNut.generateDataCharVNUT(dia.getvNuts()));
    }

    private void setCharListener(){
        chart.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int arcIndex, SliceValue sliceValue) {
                String text = "";

                if(arcIndex == 2)
                    arcIndex=4;

                text = text + String.format(VNut.getNameNut(arcIndex).toUpperCase() + " " + new DecimalFormat("0.00").format(dia.getvNuts().getValNut(arcIndex))) + "g";

                if(VNut.getNameNut(arcIndex) == VNut.HIDRATOS){
                    text = text + "\nDe los cuales:";
                    text = text + "\n\t\t" + VNut.getNameNut(2).toUpperCase() + " " + new DecimalFormat("0.00").format(dia.getvNuts().getValNut(2))+ "g";
                    text = text + "\n\t\t" + VNut.getNameNut(3).toUpperCase() + " " + new DecimalFormat("0.00").format(dia.getvNuts().getValNut(3))+ "g";
                }

                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });
    }

}