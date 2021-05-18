package com.example.tfg.UI.PlatosUI.FragmentsPlatos.SecondaryFragment;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdaptadorVN;
import com.example.tfg.Sistems.SistemaPlatos.models.CategoryIngredient;
import com.example.tfg.Sistems.SistemaPlatos.models.Ingrediente;
import com.example.tfg.Sistems.SistemaPlatos.models.VNut;
import com.example.tfg.UI.PlatosUI.AdptPlatos.AdptRVN;
import com.example.tfg.UI.PlatosUI.FragmentsPlatos.CharVNut;

import java.text.DecimalFormat;
import java.util.List;

import io.realm.Realm;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;


public class IngredienteUnicoFragment extends Fragment {


    private TextView nombre;
    private TextView categoria;
    private ImageView image;
    private ListView listView;


    private Realm realm;
    private Ingrediente ingrediente;

    private int idIngr;

    private TextView cal, cantCal;
    private RecyclerView recyclerVN;
    private RecyclerView.Adapter adptVN;
    private RecyclerView.LayoutManager mLayoutManagerVN;

    private PieChartView chart;


    public IngredienteUnicoFragment() {
        // Required empty public constructor
    }

    public static IngredienteUnicoFragment newInstance(int idIngrediente) {
        IngredienteUnicoFragment fragment = new IngredienteUnicoFragment();
        Bundle bundleEnvio = new Bundle(); bundleEnvio.putInt("idIngrediente", idIngrediente);
        fragment.setArguments(bundleEnvio);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerVN.setHasFixedSize(true);
        recyclerVN.setItemAnimator(new DefaultItemAnimator());
        recyclerVN.setLayoutManager(mLayoutManagerVN);
        recyclerVN.setAdapter(adptVN);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ingrediente_unico, container, false);

        Bundle idFranjaRecibida = new Bundle(getArguments());

        if(idFranjaRecibida != null)
            idIngr = idFranjaRecibida.getInt("idIngrediente");


        setRealm();
        setReferencias(view);
        setterElementos(view);
        setAdptVN();

        chart.setPieChartData(CharVNut.generateDataCharVNUT(ingrediente.getvNuts()));
        setCharListener();

        return view;
    }

    private void setRealm(){
        realm = Realm.getDefaultInstance();
        ingrediente = realm.where(Ingrediente.class).equalTo("id", idIngr).findFirst();

    }

    private void setReferencias(View view){
        nombre = (TextView) view.findViewById(R.id.fragmentIngredienteUnicoTextViewNombre);
        categoria = (TextView) view.findViewById(R.id.fragmentIngredienteUnicoTextViewCategoria);
        image = (ImageView) view.findViewById(R.id.fragmentIngredienteUnicoImageView);
        recyclerVN = (RecyclerView) view.findViewById(R.id.fIngredienteUnicoRVVN);
        cal = (TextView) view.findViewById(R.id.textViewItemVNParametro);
        cantCal = (TextView) view.findViewById(R.id.textViewItemVNValor);
        mLayoutManagerVN = new LinearLayoutManager(view.getContext());
        chart = (PieChartView) view.findViewById(R.id.piechartIngredient);

        cal.setText("Calorias");
        cantCal.setText(ingrediente.getCal() + " cal");
        cal.setTextColor(ChartUtils.COLOR_RED);
    }

    private void setterElementos(View view){
        nombre.setText(ingrediente.getNombre().toUpperCase());
        categoria.setText(ingrediente.getCategoria());
        image.setImageResource(ingrediente.getImage());
    }

    private void setAdptVN(){
        adptVN = new AdptRVN(ingrediente.getvNuts().getValores(), R.layout.list_view_item_val_nut, new AdptRVN.OnItemClickListener() {
            @Override
            public void onItemClick(VNut vNut, int position) {
            }
        });
    }

    private void setCharListener(){
        chart.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int arcIndex, SliceValue sliceValue) {
                String text = "";

                if(arcIndex == 2)
                    arcIndex=4;

                text = text + String.format(VNut.getNameNut(arcIndex).toUpperCase() + " " + new DecimalFormat("0.00").format(ingrediente.getvNuts().getValNut(arcIndex))) + "g";

                if(VNut.getNameNut(arcIndex) == VNut.HIDRATOS){
                    text = text + "\nDe los cuales:";
                    text = text + "\n\t\t" + VNut.getNameNut(2).toUpperCase() + " " + new DecimalFormat("0.00").format(ingrediente.getvNuts().getValNut(2))+ "g";
                    text = text + "\n\t\t" + VNut.getNameNut(3).toUpperCase() + " " + new DecimalFormat("0.00").format(ingrediente.getvNuts().getValNut(3))+ "g";
                }

                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();            }

            @Override
            public void onValueDeselected() {

            }
        });
    }
}