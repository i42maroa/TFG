package com.example.tfg.UI.PlatosUI.FragmentsPlatos;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.models.VNut;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;

public class CharVNut {

    public static PieChartData generateDataCharVNUT(VNut vNut) {

        final VNut vNutAux = vNut;

        List<SliceValue> values = new ArrayList<SliceValue>(){{
            add(new SliceValue((float) vNutAux.getProteinas(), ChartUtils.COLOR_BLUE));
            add(new SliceValue((float) vNutAux.getHidratosCarbono(), ChartUtils.COLOR_GREEN));
            add(new SliceValue((float) vNutAux.getGrasas(), ChartUtils.COLOR_ORANGE));
        }};

        return new PieChartData(values)
                .setHasLabels(true)
                .setHasLabelsOnlyForSelected(false)
                .setHasLabelsOutside(false)
                .setHasCenterCircle(false)
                .setSlicesSpacing(1);
    }
}
