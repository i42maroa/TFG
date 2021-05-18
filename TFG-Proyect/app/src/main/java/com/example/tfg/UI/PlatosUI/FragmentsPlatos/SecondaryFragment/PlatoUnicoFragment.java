package com.example.tfg.UI.PlatosUI.FragmentsPlatos.SecondaryFragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPerfil.Fragments.EstadisticasFragment;
import com.example.tfg.Sistems.SistemaPlatos.models.Category;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteCantidad;
import com.example.tfg.Sistems.SistemaPlatos.models.PasoPlato;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.Sistems.SistemaPlatos.models.VNut;
import com.example.tfg.UI.PlatosUI.AdptPlatos.AdptIngredients;
import com.example.tfg.UI.PlatosUI.AdptPlatos.AdptPasosPlato;
import com.example.tfg.UI.PlatosUI.AdptPlatos.AdptRIngredients;
import com.example.tfg.UI.PlatosUI.AdptPlatos.AdptRVN;
import com.example.tfg.UI.PlatosUI.FragmentsPlatos.CharVNut;
import com.example.tfg.app.Const;
import com.example.tfg.fragments.adaptors.iDataListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;


public class PlatoUnicoFragment extends Fragment {


    private RecyclerView recyclerPasos;
    private RecyclerView recyclerIngredientes;
    private RecyclerView recyclerVN;
    private RecyclerView.Adapter adptPasos;
    private RecyclerView.Adapter adptIngredientes;
    private RecyclerView.Adapter adptVN;
    private RecyclerView.LayoutManager mLayoutManagerPasos;
    private RecyclerView.LayoutManager mLayoutManagerIng;
    private RecyclerView.LayoutManager mLayoutManagerVN;



    private TextView nombre, dificultad, tiempElav, cal, cantCal, paRealizar;
    private ImageView imagePlato, paRealizarImg;
    private ImageButton bFavorito;

    private Category cat;

    private Realm realm;
    private int idPlato;
    private Plato plato;
    private RealmResults<IngredienteCantidad> ingredientes;
    private RealmList<PasoPlato> pasos;

    private PieChartView chart;

    private iDataListener callback;

    public PlatoUnicoFragment() {
        // Required empty public constructor
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

        recyclerVN.setHasFixedSize(true);
        recyclerVN.setItemAnimator(new DefaultItemAnimator());
        recyclerVN.setLayoutManager(mLayoutManagerVN);
        recyclerVN.setAdapter(adptVN);
    }

    public static PlatoUnicoFragment newInstance(int idPlato) {
        PlatoUnicoFragment fragment = new PlatoUnicoFragment();
        Bundle bundleEnvio = new Bundle(); bundleEnvio.putInt("idPlato", idPlato);
        fragment.setArguments(bundleEnvio);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plato, container, false);

        Bundle idFranjaRecibida = new Bundle(getArguments());

        if(idFranjaRecibida.size() != 0)
            idPlato = idFranjaRecibida.getInt("idPlato");
        else
            idPlato = 1;

        initParam(view);
        setRealm();
        setParam();
        setReciclerViewIngredients();
        setReciclerView();


        chart.setPieChartData(CharVNut.generateDataCharVNUT(plato.getvNuts()));
        setCharListener();

        bFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm.beginTransaction();
                cat = realm.where(Category.class).equalTo("name", Const.CATEGORIA_FAVORITE).findFirst();

                if(plato.getFavorito() == Plato.FAVORITO_SI){
                    plato.setFavorito(Plato.FAVORITO_NO);
                    Toast.makeText(getContext(), "Plato eliminado de favoritos", Toast.LENGTH_SHORT).show();
                    cat.getListPlatos().remove(plato);
                }
                else{
                    plato.setFavorito(Plato.FAVORITO_SI);
                    Toast.makeText(getContext(), "Plato introducido en favoritos", Toast.LENGTH_SHORT).show();
                    cat.getListPlatos().add(plato);
                }
                setImgFav(plato);
                realm.copyToRealmOrUpdate(plato);
                realm.commitTransaction();
            }
        });

        adptVN = new AdptRVN(plato.getvNuts().getValores(), R.layout.list_view_item_val_nut, new AdptRVN.OnItemClickListener() {
            @Override
            public void onItemClick(VNut vNut, int position) {
            }
        });

        return view;
    }

    private void setReciclerView(){
        adptPasos = new AdptPasosPlato(pasos, R.layout.list_view_item_paso_platos, new AdptPasosPlato.OnItemClickListener() {
            @Override
            public void onItemClick(PasoPlato paso, int position) {
            }

            @Override
            public void onItemClickLong(PasoPlato paso, int position) {

            }
        });
    }

    private void setReciclerViewIngredients(){
        adptIngredientes = new AdptRIngredients(ingredientes, R.layout.list_view_item_ingrediente, new AdptRIngredients.OnItemClickListener() {
            @Override
            public void onItemClick(IngredienteCantidad ingrediente, int position) {
                callback.enviarIdIngrediente(ingredientes.get(position).getIngrediente().getId());
            }

            @Override
            public void onItemLongClick(IngredienteCantidad ingrediente, int position) {
                Toast.makeText(getContext(), "Ingrediente", Toast.LENGTH_SHORT).show();
            }
        });
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


    private void initParam(View view){

        nombre = (TextView) view.findViewById(R.id.fragmentPlatoUnicoTextViewNombre);
        dificultad = (TextView) view.findViewById(R.id.fragmentPlatoUnicoTextViewDificultad);
        tiempElav = (TextView) view.findViewById(R.id.fragmentPlatoUnicoTextViewTiempo);
        imagePlato = (ImageView) view.findViewById(R.id.fragmentPlatoUnicoImageView);
        recyclerIngredientes = (RecyclerView) view.findViewById(R.id.fPlatoUnicoRVIngredients);
        recyclerPasos = (RecyclerView) view.findViewById(R.id.fragmentPlatoUnicoReciclerView);
        recyclerVN = (RecyclerView) view.findViewById(R.id.fPlatoUnicoRVVN);
        bFavorito = (ImageButton) view.findViewById(R.id.fragmentPlatoUnicoButtonFavorite);
        mLayoutManagerIng = new  LinearLayoutManager(view.getContext());
        mLayoutManagerPasos = new LinearLayoutManager(view.getContext());
        mLayoutManagerVN = new LinearLayoutManager(view.getContext());
        chart = (PieChartView) view.findViewById(R.id.piechart);
        cal = (TextView) view.findViewById(R.id.textViewItemVNParametro);
        cantCal = (TextView) view.findViewById(R.id.textViewItemVNValor);
        paRealizar = (TextView) view.findViewById(R.id.fPlatoUnicoPasos);
        paRealizarImg = (ImageView) view.findViewById(R.id.fPlatoUnicoPasosLinea);
    }

    private void setRealm(){
        realm = Realm.getDefaultInstance();
        plato = realm.where(Plato.class).equalTo("id", idPlato).findFirst();
        ingredientes = plato.getIngredientes().sort("ingrediente.nombre", Sort.ASCENDING);
        pasos = plato.getPasosPlato();
    }

    private void setParam(){
        nombre.setText(plato.getNombre().toUpperCase());
        dificultad.setText(plato.getDificultad());
        tiempElav.setText(plato.getTiempoElav() + " min");
        imagePlato.setImageResource(plato.getImage());
        cal.setText("Calorias");
        cantCal.setText(plato.getCaloriasTotales() + " cal");
        cal.setTextColor(ChartUtils.COLOR_RED);
        setImgFav(plato);

        if(plato.getPasosPlato().size() == 0){
            paRealizar.setText("");
            paRealizarImg.setBackgroundColor(0);
        }
    }


    private void setCharListener(){
        chart.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int arcIndex, SliceValue sliceValue) {
                String text = "";

                if(arcIndex == 2)
                    arcIndex=4;

                text = text + String.format(VNut.getNameNut(arcIndex).toUpperCase() + " " + new DecimalFormat("0.00").format(plato.getvNuts().getValNut(arcIndex))) + "g";

                if(VNut.getNameNut(arcIndex) == VNut.HIDRATOS){
                    text = text + "\nDe los cuales:";
                    text = text + "\n\t\t" + VNut.getNameNut(2).toUpperCase() + " " + new DecimalFormat("0.00").format(plato.getvNuts().getValNut(2))+ "g";
                    text = text + "\n\t\t" + VNut.getNameNut(3).toUpperCase() + " " + new DecimalFormat("0.00").format(plato.getvNuts().getValNut(3))+ "g";
                }

                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });
    }

    private void setImgFav(Plato plato){
        if(plato.getFavorito() == Plato.FAVORITO_NO)
            bFavorito.setImageResource(R.drawable.no_favorito);
        else
            bFavorito.setImageResource(R.drawable.favorito);
    }
}