package com.example.tfg.Sistems.SistemaPerfil.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaHorario.models.Dia;
import com.example.tfg.Sistems.SistemaHorario.models.Mes;
import com.example.tfg.Sistems.SistemaHorario.models.Semana;
import com.example.tfg.Sistems.SistemaPlatos.models.VNut;
import com.example.tfg.app.MyApplication;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmResults;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class EstadisticasFragment extends Fragment {

    private ColumnChartView chart;
    private ColumnChartData data;

    private TextView mesSelec, anioSelec;

    private TextView  medCalMes, diaCal, calTotal;
    private ArrayList<Integer> valEstadistica;

    private static final int DEFAULT_DATA = 0;
    private static final int SUBCOLUMNS_DATA = 1;
    private static final int STACKED_DATA = 2;
    private static final int NEGATIVE_SUBCOLUMNS_DATA = 3;
    private static final int NEGATIVE_STACKED_DATA = 4;

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;
    private int dataType = DEFAULT_DATA;

    private LineChartView chartLine;
    private LineChartData dataLine;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfPoints = 7;

    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;

    private boolean isCubic = false;

    private boolean pointsHaveDifferentColor;
    private boolean hasGradientToTransparent = false;

    private Realm realm;
    private Dia dia;
    private RealmResults<Dia> dias;

    private Semana sem;
    private Mes mes;

    private RealmList<Semana> semanas;

    private ImageButton menMes, masMes;

    private Mes mesActual;
    private ArrayList<VNut> vnutAux;


    public static int REDUCIR_MES = 0;
    public static int AUMENTAR_MES = 1;


    public EstadisticasFragment() {
        // Required empty public constructor
    }

    public static EstadisticasFragment newInstance(String param1, String param2) {
        EstadisticasFragment fragment = new EstadisticasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_estadisticas, container, false);

        setParam(view);

        setRealm();
        generateDataColumn();
        generateDataLine();
        setChangeListeners();
        setOnClickListeners();

        valEstadistica = mes.getValEstadisticas();

        inicializarParam();

        return view;
    }

    private void setParam(View view){
        chart = (ColumnChartView) view.findViewById(R.id.columChart);
        chart.setOnValueTouchListener(new ValueTouchListener());

        chartLine = (LineChartView) view.findViewById(R.id.lineChartView);
        chartLine.setOnValueTouchListener(new ValueTouchListenerLine());

        mesSelec = (TextView) view.findViewById(R.id.estadisticasTextViewMesSelec);
        anioSelec = (TextView) view.findViewById(R.id.estadisticasTextViewAnioSelec);

        menMes = (ImageButton) view.findViewById(R.id.estadisticasMenMes);
        masMes = (ImageButton) view.findViewById(R.id.estadisticasMasMes);
        masMes.setImageResource(R.drawable.flecha_derecha);
        menMes.setImageResource(R.drawable.flecha_izquierda);

        medCalMes = (TextView) view.findViewById(R.id.texViewMedCaloMes);
        diaCal = (TextView) view.findViewById(R.id.texViewMasCalorico);
        calTotal = (TextView) view.findViewById(R.id.texViewCalTotal);

        valEstadistica = new ArrayList<Integer>();


    }

    private void setOnClickListeners(){
        menMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarMes(REDUCIR_MES);
            }
        });

        masMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarMes(AUMENTAR_MES);
            }
        });

    }

    private void setChangeListeners(){


    }

    private void cargarValoresRealm(int mesActu){
        mes=realm.where(Mes.class).equalTo("id", mesActu).findFirst();
        dias = realm.where(Dia.class).equalTo("mes", dia.getMes())
                .findAll();
    }

    private void inicializarParam(){
        diaCal.setText(String.valueOf(mes.getDiaMasCalorico()));
        mesSelec.setText(Mes.getNombMes(mes.getMes()).toUpperCase());
        anioSelec.setText(String.valueOf(mes.getAnio()));
        diaCal.setText(getDiaCalorico(mes.getDiaMasCalorico()));
        medCalMes.setText(valEstadistica.get(0) + " cal");
        calTotal.setText(valEstadistica.get(1) + " cal");
    }

    private void cambiarMes(int opc){
        int mesActu = mes.getId();

        if(opc == REDUCIR_MES){
            if(mesActu > 1)
                mesActu--;
            else
                Toast.makeText(getContext(), "No hay más meses registrados", Toast.LENGTH_SHORT).show();
        }
        else if(opc == AUMENTAR_MES){
            if(mesActu < realm.where(Mes.class).findAll().size()){
                mesActu++;
            }
            else
                Toast.makeText(getContext(), "No hay más meses registrados", Toast.LENGTH_SHORT).show();
        }

        cargarValoresRealm(mesActu);

        vnutAux = mes.getEstadistica();

        valEstadistica = mes.getValEstadisticas();

        diaCal.setText(getDiaCalorico(mes.getDiaMasCalorico()) );
        medCalMes.setText(valEstadistica.get(0) + " cal");
        calTotal.setText(valEstadistica.get(1) + " cal");
        mesSelec.setText(Mes.getNombMes(mes.getMes()).toUpperCase());
        anioSelec.setText(String.valueOf(mes.getAnio()));
        generateDataColumn();
        generateDataLine();

    }

    private void setRealm(){
        realm = Realm.getDefaultInstance();
        sem = realm.where(Semana.class).equalTo("id", 1).findFirst();
        dia = sem.getDias().get(0);

        mes = realm.where(Mes.class).equalTo("mes", dia.getMes()).findFirst();


        vnutAux = mes.getEstadistica();
        dias = realm.where(Dia.class).equalTo("mes", dia.getMes())
                .findAll();
    }



    private void generateDataColumn() {
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;

        for (int i = 0; i < vnutAux.size(); ++i) {

            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue((float) vnutAux.get(i).getProteinas(), VNut.getColor(VNut.PROTEINAS)));
            values.add(new SubcolumnValue((float) vnutAux.get(i).getHidratosCarbono(), VNut.getColor(VNut.HIDRATOS)));
            values.add(new SubcolumnValue((float) vnutAux.get(i).getGrasas(), VNut.getColor(VNut.GRASAS)));

            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }

        data = new ColumnChartData(columns);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);

            axisX.setName(Mes.getNombMes(mes.getMes()));
            axisY.setName("VNuts");

            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        chart.setColumnChartData(data);
    }

    private void generateDataLine() {

        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < 1; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();

            for (int j = 0; j < vnutAux.size(); ++j) {
                values.add(new PointValue(j, vnutAux.get(j).getCalorias()));
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[i]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            //line.setHasGradientToTransparent(hasGradientToTransparent);
            if (pointsHaveDifferentColor){
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            lines.add(line);
        }

        dataLine = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName(Mes.getNombMes(mes.getMes()));
                axisY.setName("VNuts");
            }
            dataLine.setAxisXBottom(axisX);
            dataLine.setAxisYLeft(axisY);
        } else {
            dataLine.setAxisXBottom(null);
            dataLine.setAxisYLeft(null);
        }

        dataLine.setBaseValue(Float.NEGATIVE_INFINITY);
        chartLine.setLineChartData(dataLine);
    }

    private String getDiaCalorico(int diaMasCal){
        Dia diaR;

        if(diaMasCal == - 1)
            return "No hay platos registrados";

        else{
            diaR = realm.where(Dia.class).equalTo("id", diaMasCal).findFirst();

            return "Dia " + diaR.getDia() + " - " + diaR.getvNuts().getCalorias() + " cal";
        }
    }


    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            String x = "";
            switch (subcolumnIndex){
                case 0:
                    x = "Proteínas";
                    break;
                case 1:
                    x = "Hidratos de carbono";
                    break;
                case 2:
                    x = "Grasas";
                    break;

            }

            switch (columnIndex){
                case 0:
                    Toast.makeText(getActivity(), "Dias 1 - 7: " + x + value.toString() +" g", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(getActivity(), "Dias 8 - 14: " + x +value.toString()+ " g", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getActivity(), "Dias 15 - 21: " + x + value.toString()+" g", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getActivity(), "Dias 22 - 28: " + x + value+" g", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(getActivity(), "Dias 29 - " + Mes.getDiasMes(mes.getMes()) + ": " + x + value.toString() +" g", Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }

    private class ValueTouchListenerLine implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {

            switch (pointIndex){
                case 0:
                    Toast.makeText(getActivity(), "Dias 1 - 7: " + value.toString() + " cal", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(getActivity(), "Dias 8 - 14: " + value.toString() + " cal", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getActivity(), "Dias 15 - 21: " + value.toString() + " cal", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getActivity(), "Dias 22 - 28: " + value.toString() + " cal", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(getActivity(), "Dias 29 - " + Mes.getDiasMes(mes.getMes()) + ": " + value.toString() + " cal", Toast.LENGTH_SHORT).show();
                    break;
                default:
            }

        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub
        }
    }
}