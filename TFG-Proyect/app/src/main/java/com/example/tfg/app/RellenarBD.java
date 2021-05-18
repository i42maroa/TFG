package com.example.tfg.app;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaAlmacenamiento.Models.Almacenamiento;
import com.example.tfg.Sistems.SistemaHorario.models.Anio;
import com.example.tfg.Sistems.SistemaHorario.models.Dia;
import com.example.tfg.Sistems.SistemaHorario.models.FranjaHorario;
import com.example.tfg.Sistems.SistemaHorario.models.Mes;
import com.example.tfg.Sistems.SistemaHorario.models.Semana;
import com.example.tfg.Sistems.SistemaPerfil.models.Usuario;
import com.example.tfg.Sistems.SistemaPlatos.models.Category;
import com.example.tfg.Sistems.SistemaPlatos.models.CategoryIngredient;
import com.example.tfg.Sistems.SistemaPlatos.models.Ingrediente;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteCantidad;
import com.example.tfg.Sistems.SistemaPlatos.models.PasoPlato;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.Sistems.SistemaPlatos.models.VNut;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;


import static com.example.tfg.Sistems.SistemaPlatos.models.Plato.DIFICULTAD_DIFICIL;
import static com.example.tfg.Sistems.SistemaPlatos.models.Plato.DIFICULTAD_FACIL;
import static com.example.tfg.Sistems.SistemaPlatos.models.Plato.DIFICULTAD_MEDIA;



public class RellenarBD {

    public static List<Ingrediente> rellenarListaIngredientes(){
        return new ArrayList<Ingrediente>(){{
            add(new Ingrediente(Const.ING_CEBOLLA, Const.CATEGORIA_ING_VERDURA,  new VNut(42, 0.92, 10.11, 1.4, 4.28, 0.08, 0.003), R.drawable.ingrediente_cebolla, 100));
            add(new Ingrediente(Const.ING_CEBOLLIN,Const.CATEGORIA_ING_VERDURA,  new VNut(30, 3.27, 4.35, 2.5, 1.85, 0.73, 0.003), R.drawable.ingrediente_cebolla,100));
            add(new Ingrediente(Const.ING_ZANAHORIA, Const.CATEGORIA_ING_VERDURA,new VNut(41, 0.93, 9.58, 2.8, 4.54, 0.24, 0.069), R.drawable.ingrediente_zanh, 80));
            add(new Ingrediente(Const.ING_PIM_VERDE,Const.CATEGORIA_ING_VERDURA, new VNut(20, 0.86, 4.64, 1.7, 2.4, 0.17, 0.03), R.drawable.ingrediente_pimiento, 200));
            add(new Ingrediente(Const.ING_PIM_ROJO,Const.CATEGORIA_ING_VERDURA, new VNut(26, 0.99, 6.03, 2, 4.2, 0.3, 0.02), R.drawable.ingrediente_pimiento_rojo, 200));
            add(new Ingrediente(Const.ING_PIM_AMA,Const.CATEGORIA_ING_VERDURA, new VNut(27, 1, 6.32, 0.9, 0, 0.21, 0.02), R.drawable.ingrediente_pimiento_amarillo, 200));
            add(new Ingrediente(Const.ING_TOMATE,Const.CATEGORIA_ING_VERDURA, new VNut(18, 0.88, 3.92, 1.2, 2.63, 0.2, 0.005), R.drawable.ingrediente_tomate, 110));
            add(new Ingrediente(Const.ING_TOMATE_FRITO,Const.CATEGORIA_ING_VERDURA, new VNut(79, 1.82, 9.92, 1.8, 4.48, 3.53, 0.9), R.drawable.ingrediente_tomate_frito, 400));
            add(new Ingrediente(Const.ING_TOMATE_CHERRY, Const.CATEGORIA_ING_VERDURA, new VNut(18, 0.88, 3.92, 1.2, 2.63, 0.2, 0.005), R.drawable.ingrediente_tomate, 15));
            add(new Ingrediente(Const.ING_ACEITUNA_VER,Const.CATEGORIA_ING_VERDURA, new VNut(42, 0.3, 1.11, 1, 0.16, 4.44, 0.451), R.drawable.ingrediente_aceituna, 10));
            add(new Ingrediente(Const.ING_ACEITUNA_NEG, Const.CATEGORIA_ING_VERDURA, new VNut(36, 0.3, 2.06, 1, 0, 3.24, 0.299), R.drawable.ingrediente_aceituna, 10));
            add(new Ingrediente(Const.ING_AGUACATE, Const.CATEGORIA_ING_VERDURA,new VNut(322, 4.02, 17.15, 13.5, 1.33, 29.47, 0.014), R.drawable.ingrediente_aguacate,300));
            add(new Ingrediente(Const.ING_AJO, Const.CATEGORIA_ING_VERDURA, new VNut(149, 6.36, 33.06, 2.1, 1, 0.5, 0.017), R.drawable.ingrediente_ajo, 10));
            add(new Ingrediente(Const.ING_BERENJENA,Const.CATEGORIA_ING_VERDURA, new VNut(24, 1.01, 5.7, 3.4, 2.35, 0.19, 0.02), R.drawable.ingrediente_berenjena,170));
            add(new Ingrediente(Const.ING_BROCOLI, Const.CATEGORIA_ING_VERDURA, new VNut(31, 2.57, 6.04, 2.4, 1.55, 0.34, 0.003), R.drawable.ingrediente_brocoli, 500));
            add(new Ingrediente(Const.ING_CALABAZA, Const.CATEGORIA_ING_VERDURA, new VNut(26, 1, 6.5, 0.5, 1.36, 0.1, 7), R.drawable.ingrediente_calabaza, 2000));
            add(new Ingrediente(Const.ING_CHAMPINION, Const.CATEGORIA_ING_VERDURA,new VNut(22, 3.09, 3.28, 1, 1.65, 0.34, 0.05), R.drawable.ingrediente_champinion, 20));
            add(new Ingrediente(Const.ING_COLIFLOR,Const.CATEGORIA_ING_VERDURA,  new VNut(25, 1.98, 5.3, 2.5, 2.4, 0.1, 0.03), R.drawable.ingrediente_coliflor, 2000));
            add(new Ingrediente(Const.ING_LECHUGA,Const.CATEGORIA_ING_VERDURA,  new VNut(14, 0.9, 2.97, 1.2, 1.76, 0.14, 0.001), R.drawable.ingrediente_lechuga, 300));
            add(new Ingrediente(Const.ING_LIMA, Const.CATEGORIA_ING_VERDURA, new VNut(20, 0.47, 7.06, 1.9, 1.13, 0.13, 0.001), R.drawable.ingrediente_lima, 60));
            add(new Ingrediente(Const.ING_LIMON,Const.CATEGORIA_ING_VERDURA,  new VNut(17, 0.64, 5.41, 1.6, 1.45, 0.17, 0.001), R.drawable.ingrediente_limon, 60));
            add(new Ingrediente(Const.ING_PATATA_ASAD,Const.CATEGORIA_ING_VERDURA,new VNut(93, 2.49, 21.04, 2.2, 1.17, 0.13, 0.086), R.drawable.ingrediente_patata, 80));
            add(new Ingrediente(Const.ING_PATATA_COC,Const.CATEGORIA_ING_VERDURA,  new VNut(86, 1.71, 20.01, 2, 0.85, 0.1, 0.241), R.drawable.ingrediente_patata, 80));
            add(new Ingrediente(Const.ING_PATATA_FRIT, Const.CATEGORIA_ING_VERDURA,new VNut(273, 3.48, 35.62, 3.3, 0.59, 14.03, 0.302), R.drawable.ingrediente_patata, 80));
            add(new Ingrediente(Const.ING_PEPINILLO, Const.CATEGORIA_ING_VERDURA,new VNut(18, 0.62, 4.12, 1.2, 3.51, 0.19, 1.282), R.drawable.ingrediente_pepinillo, 250));
            add(new Ingrediente(Const.ING_ESPINACAS, Const.CATEGORIA_ING_VERDURA, new VNut(23, 2.86, 3.69, 2.2, 0.42, 0.39, 0.079), R.drawable.ingrediente_lechuga, 300));
            add(new Ingrediente(Const.ING_COLES,Const.CATEGORIA_ING_VERDURA, new VNut(30, 2.45, 5.69, 3.6, 0.46, 0.42, 0.002), R.drawable.ingrediente_lechuga, 300));
            add(new Ingrediente(Const.ING_ESPARRAGOS,Const.CATEGORIA_ING_VERDURA, new VNut(20, 2.2, 3.88, 2.1, 1.88, 0.12, 0.002), R.drawable.ingrediente_esparrago, 250));
            add(new Ingrediente(Const.ING_REMOLACHA, Const.CATEGORIA_ING_VERDURA, new VNut(43, 1.61, 9.56, 2.8, 6.76, 0.17, 0.078), R.drawable.ingrediente_lechuga, 100));
            add(new Ingrediente(Const.ING_ALCACHOFAS, Const.CATEGORIA_ING_VERDURA, new VNut(47, 3.27, 10.51, 5.4, 0, 0.15, 0.094), R.drawable.ingrediente_lechuga, 200));
            add(new Ingrediente(Const.ING_PEPINOS,Const.CATEGORIA_ING_VERDURA,  new VNut(12, 0.59, 2.16, 0.7, 1.38, 0.16, 0.002), R.drawable.ingrediente_pepinillo, 300));
            add(new Ingrediente(Const.ING_CALABACIN, Const.CATEGORIA_ING_VERDURA,new VNut(16, 1.21, 3.35, 1.1, 1.73, 0.18, 0.01), R.drawable.ingrediente_lechuga, 250));
            add(new Ingrediente(Const.ING_GUISANTES,Const.CATEGORIA_ING_VERDURA, new VNut(81, 5.42, 14.46, 5.1, 5.67, 0.4, 0.05), R.drawable.ingrediente_guisantes, 200));
            add(new Ingrediente(Const.ING_MAIZ,Const.CATEGORIA_ING_VERDURA, new VNut(86, 3.22, 19.02, 2.7, 3.22, 1.18, 0.015), R.drawable.ingrediente_maiz, 100));
            add(new Ingrediente(Const.ING_APIO,Const.CATEGORIA_ING_VERDURA, new VNut(14, 0.69, 2.97, 1.6, 1.83, 0.17, 0.008), R.drawable.ingenieria_puerro, 200));
            add(new Ingrediente(Const.ING_PUERRO, Const.CATEGORIA_ING_VERDURA,  new VNut(61, 1.5, 14.15, 1.8, 3.9, 0.3, 0.02), R.drawable.ingenieria_puerro, 200));
            add(new Ingrediente(Const.ING_HABAS, Const.CATEGORIA_ING_VERDURA,  new VNut(176, 10.67, 31.47, 7.6, 2.31, 1.34, 0.012), R.drawable.ingrediente_haba, 200));

            add(new Ingrediente(Const.ING_MANZ_VERDE, Const.CATEGORIA_ING_FRUTA, new VNut(55, 0.24, 14.48, 2.2, 11.45, 0.25, 0.002), R.drawable.ingrediente_manzana, 170));
            add(new Ingrediente(Const.ING_MANZ_ROJA, Const.CATEGORIA_ING_FRUTA, new VNut(52, 0.26, 13.81, 2.4, 10.39, 0.17, 0.001), R.drawable.ingrediente_manzana, 170));
            add(new Ingrediente(Const.ING_MELOCOTON, Const.CATEGORIA_ING_FRUTA,  new VNut(39, 0.91, 9.54, 1.5, 8.39, 0.25, 0), R.drawable.ingrediente_melocoton, 150));
            add(new Ingrediente(Const.ING_ALBARICOQUE, Const.CATEGORIA_ING_FRUTA, new VNut(48, 1.4, 11.12, 2, 9.24, 0.39, 0.001), R.drawable.ingrediente_melocoton, 50));
            add(new Ingrediente(Const.ING_PERA, Const.CATEGORIA_ING_FRUTA, new VNut(58, 0.38, 15.46, 3.1, 9.8, 0.12, 0.001), R.drawable.ingrediente_pera, 180));
            add(new Ingrediente(Const.ING_PLATANO, Const.CATEGORIA_ING_FRUTA,new VNut(89, 1.09, 22.84, 2.6, 12.23, 0.33, 0.001), R.drawable.ingrediente_platano, 150));
            add(new Ingrediente(Const.ING_MELON, Const.CATEGORIA_ING_FRUTA, new VNut(34, 0.84, 8.16, 0.9, 7.86, 0.19, 0.016), R.drawable.ingrediente_melon, 2000));
            add(new Ingrediente(Const.ING_NARANJA, Const.CATEGORIA_ING_FRUTA, new VNut(47, 0.94, 11.75, 2.4, 9.35, 0.12, 0.018), R.drawable.ingrediente_naranja, 40));
            add(new Ingrediente(Const.ING_SANDIA, Const.CATEGORIA_ING_FRUTA, new VNut(30, 0.61, 7.55, 0.4, 6.2, 0.15, 0.001), R.drawable.ingrediente_sandia, 2000));


            add(new Ingrediente(Const.ING_PISTACHO,Const.CATEGORIA_ING_FRUTO_SECO, new VNut(557, 20.61, 27.97, 10.3, 7.64, 44.44, 0.006), R.drawable.ingrediente_pistacho, 200));
            add(new Ingrediente(Const.ING_PASAS, Const.CATEGORIA_ING_FRUTO_SECO, new VNut(299, 3.07, 79.18, 3.7, 59.19, 0.46, 0.011), R.drawable.ingrediente_pasas, 200));

            add(new Ingrediente(Const.ING_LENTEJA,Const.CATEGORIA_ING_LEGUMBRES, new VNut(353, 25.8, 60.08, 30.5, 2.03, 1.06, 0.006), R.drawable.ingrediente_haba, 1000));
            add(new Ingrediente(Const.ING_GARBANZOS, Const.CATEGORIA_ING_LEGUMBRES, new VNut(180, 9.54, 29.98, 8.6, 5.29, 2.99, 0.243), R.drawable.ingrediente_haba, 1000));
            add(new Ingrediente(Const.ING_HABICHUELA, Const.CATEGORIA_ING_LEGUMBRES, new VNut(31, 1.82, 7.13, 3.4, 1.4, 0.12, 0.006), R.drawable.ingrediente_haba, 1000));


            add(new Ingrediente(Const.ING_LAUREL, Const.CATEGORIA_ING_COMP,  new VNut(0, 0, 0, 0, 0, 0, 0), R.drawable.ingrediente_laurel, 2));
            add(new Ingrediente(Const.ING_PIMIENTA_NEGRA, Const.CATEGORIA_ING_COMP,  new VNut(0, 0, 0, 0, 0, 0, 0), R.drawable.ingrediente_salpim, 50));
            add(new Ingrediente(Const.ING_TOMILLO, Const.CATEGORIA_ING_COMP, new VNut(0, 0, 0, 0, 0, 0, 0), R.drawable.ingrediente_laurel, 50));
            add(new Ingrediente(Const.ING_ACEITE,Const.CATEGORIA_ING_COMP,  new VNut(884, 0, 0, 0, 0, 100, 0.002), R.drawable.ingrediente_aceite, 1000));
            add(new Ingrediente(Const.ING_VINO_BLANCO,Const.CATEGORIA_ING_COMP,  new VNut(83, 0.07, 2.6, 0, 0.96, 0, 0.005), R.drawable.ingrediente_vino, 1500));
            add(new Ingrediente(Const.ING_VINO_TINTO,Const.CATEGORIA_ING_COMP, new VNut(85, 0.07, 2.59, 0, 0.62, 0, 0.004), R.drawable.ingrediente_vino, 1500));
            add(new Ingrediente(Const.ING_VINO_PX,Const.CATEGORIA_ING_COMP, new VNut(160, 0.2, 13, 0, 0, 0, 0), R.drawable.ingrediente_vino, 1500));
            add(new Ingrediente(Const.ING_CANELA_MOLIDA, Const.CATEGORIA_ING_COMP, new VNut(261, 3.89, 79.85, 54.3, 2.17, 3.19, 0.026), R.drawable.ingrediente_canela, 50));
            add(new Ingrediente(Const.ING_MIEL,Const.CATEGORIA_ING_COMP,  new VNut(304, 0.3, 82.4, 0.2, 82.12, 0, 0.004), R.drawable.ingrediente_miel, 200));
            add(new Ingrediente(Const.ING_PIMENTON, Const.CATEGORIA_ING_COMP, new VNut(29, 0.8, 6.68, 0, 0, 0.41, 0.004), R.drawable.ingrediente_especias, 50));
            add(new Ingrediente(Const.ING_PEREJIL, Const.CATEGORIA_ING_COMP, new VNut(36, 2.97, 6.33, 3.3, 0.85, 0.79, 0.056), R.drawable.ingrediente_laurel, 50));
            add(new Ingrediente(Const.ING_MAYONESA, Const.CATEGORIA_ING_COMP, new VNut(390, 0.9, 23.9, 0, 6.4, 33.4, 0.711), R.drawable.ingrediente_mayonesa, 300));


            add(new Ingrediente(Const.ING_MUSLO_POLLO, Const.CATEGORIA_ING_CARNE, new VNut(209, 25.94, 0, 0, 0, 10.88, 0.088), R.drawable.ingrediente_muslo, 1000));
            add(new Ingrediente(Const.ING_LOMO_TERNERA, Const.CATEGORIA_ING_CARNE, new VNut(228, 20.61, 0, 0, 0, 15.49, 0.052), R.drawable.ingrediente_ternera, 1000));
            add(new Ingrediente(Const.ING_LOMO_CERDO, Const.CATEGORIA_ING_CARNE, new VNut(136, 20.54, 0, 0, 5.41, 5.41, 0.049), R.drawable.ingrediente_lomo_cerdo, 1000));

            add(new Ingrediente(Const.ING_SOLOMILLO_C, Const.CATEGORIA_ING_CARNE, new VNut(158, 22.05, 0, 0, 0, 7.12, 0.048), R.drawable.ingrediente_ternera, 1000));
            add(new Ingrediente(Const.ING_CALDO_AVE, Const.CATEGORIA_ING_CARNE, new VNut(5, 0.39, 0.62, 0, 0, 0.12, 0.326), R.drawable.ingrediente_muslo, 1000));
            add(new Ingrediente(Const.ING_C_PICADA_CERD, Const.CATEGORIA_ING_CARNE, new VNut(212, 17, 1, 0, 1, 16, 0.326), R.drawable.ingrediente_carne_picada, 1000));
            add(new Ingrediente(Const.ING_C_PICADA_TER, Const.CATEGORIA_ING_CARNE, new VNut(131, 20.7, 1, 0, 0, 5.4, 0.326), R.drawable.ingrediente_carne_picada, 1000));
            add(new Ingrediente(Const.ING_C_PICADA_MIX, Const.CATEGORIA_ING_CARNE, new VNut(200, 16, 0, 0, 0, 15, 0), R.drawable.ingrediente_carne_picada, 1000));
            add(new Ingrediente(Const.ING_JAMON, Const.CATEGORIA_ING_CARNE, new VNut(195, 27.8, 0.3, 0, 0, 8.32, 6.74), R.drawable.ingrediente_muslo, 1000));
            add(new Ingrediente(Const.ING_HUEVO_COCIDO, Const.CATEGORIA_ING_CARNE, new VNut(154, 12.53, 1.12, 0, 1.12, 10.57, 0.278), R.drawable.ingrediente_huevo_cocido, 80));
            add(new Ingrediente(Const.ING_HUEVO_FRITO, Const.CATEGORIA_ING_CARNE, new VNut(194, 13.56, 0.93, 0, 0.83, 14.69, 0.518), R.drawable.ingrediente_huevo_frito, 80));
            add(new Ingrediente(Const.ING_CHORIZO, Const.CATEGORIA_ING_CARNE, new VNut(339, 13.72, 2.85, 0, 0, 29.18, 0.846), R.drawable.ingrediente_chorizo, 1000));
            add(new Ingrediente(Const.ING_SALCHICHAS, Const.CATEGORIA_ING_CARNE, new VNut(176, 8.3, 10, 0.3, 0, 11.40, 1), R.drawable.plato_salchichas_vino, 1000));
            add(new Ingrediente(Const.ING_PECHUGA_POLLO, Const.CATEGORIA_ING_CARNE, new VNut(195, 29.55, 0, 0, 0, 7.72, 0.393), R.drawable.ingrediente_muslo, 1000));
            add(new Ingrediente(Const.ING_YORK, Const.CATEGORIA_ING_CARNE, new VNut(117, 16, 2, 0, 0, 5, 0.776), R.drawable.ingrediente_lomo_cerdo, 1000));


            add(new Ingrediente(Const.ING_SALMON, Const.CATEGORIA__ING_PESCADO, new VNut(146, 21.62, 0, 0, 0, 5.93, 0.046), R.drawable.ingrediente_salmon, 1000));
            add(new Ingrediente(Const.ING_SALMON_AHUMADO, Const.CATEGORIA__ING_PESCADO, new VNut(117, 18.28, 0, 0, 0, 4.32, 0.784), R.drawable.ingrediente_salmon, 1000));
            add(new Ingrediente(Const.ING_ATUN, Const.CATEGORIA__ING_PESCADO, new VNut(117, 21, 1.8, 0, 0, 2.83, 0.466), R.drawable.ingrediente_atun, 80));
            add(new Ingrediente(Const.ING_MERLUZA, Const.CATEGORIA__ING_PESCADO, new VNut(132, 21.38, 0.41, 0, 0.09, 4.38, 0.406), R.drawable.ingrediente_merluza, 80));
            add(new Ingrediente(Const.ING_PEZ_ESPADA, Const.CATEGORIA__ING_PESCADO, new VNut(121, 19.8, 0, 0, 0, 4.01, 0.09), R.drawable.ingrediente_merluza, 80));
            add(new Ingrediente(Const.ING_ALMEJAS, Const.CATEGORIA__ING_PESCADO, new VNut(74, 12.77, 2.57, 0, 0, 0.97, 0.056), R.drawable.plato_almejas, 100));


            add(new Ingrediente(Const.ING_QUESO, Const.CATEGORIA_ING_LACTEOS, new VNut(350, 22.21, 4.71, 0, 3.54, 26.91, 0.96), R.drawable.ingrediente_queso, 1000));
            add(new Ingrediente(Const.ING_LECHE_ENT, Const.CATEGORIA_ING_LACTEOS, new VNut(60, 3.22, 4.52, 0, 5.26, 3.25, 0.04), R.drawable.ingrediente_leche, 1000));
            add(new Ingrediente(Const.ING_NATA, Const.CATEGORIA_ING_LACTEOS, new VNut(345, 2.05, 2.79, 0, 0.11, 37, 0.04), R.drawable.ingrediente_leche, 1000));
            add(new Ingrediente(Const.ING_LECHE_DES, Const.CATEGORIA_ING_LACTEOS, new VNut(33, 3.15, 4.62, 0, 4.79, 0.33, 0.040), R.drawable.ingrediente_leche, 1000));
            add(new Ingrediente(Const.ING_YOGURT, Const.CATEGORIA_ING_LACTEOS, new VNut(99, 3.98, 18.64, 0, 18.64, 1.15, 0.053), R.drawable.plato_yogur, 1000));

            add(new Ingrediente(Const.ING_CAFE, Const.CATEGORIA_ING_BEBIDAS, new VNut(1, 0.12, 0.04, 0, 0, 0.2, 0.002), R.drawable.ingrediente_cafe, 1000));
            add(new Ingrediente(Const.ING_COLACAO, Const.CATEGORIA_ING_BEBIDAS, new VNut(378, 6.8, 78, 8.4, 53.8, 2.4, 0.001), R.drawable.ingrediente_colacao, 1000));
            add(new Ingrediente(Const.ING_REFRESCO, Const.CATEGORIA_ING_BEBIDAS, new VNut(136, 0.26, 35.18, 0, 33.01, 0.07, 0.001), R.drawable.plato_refresco, 1000));
            add(new Ingrediente(Const.ING_CERVEZA, Const.CATEGORIA_ING_BEBIDAS, new VNut(43, 0.46, 3.55, 0, 0, 0, 0.004), R.drawable.plato_cerveza, 1000));

            add(new Ingrediente(Const.ING_PASTA, Const.CATEGORIA_ING_PASTA, new VNut(157, 5.76, 30.68, 1.8, 0.56, 0.92, 0.232), R.drawable.ingrediente_spaguettis, 1000));
            add(new Ingrediente(Const.ING_RAVIOLI, Const.CATEGORIA_ING_PASTA, new VNut(191, 12.58, 17.36, 0.6, 0.3, 7.43, 0.077), R.drawable.ingrediente_ravioli, 1000));
            add(new Ingrediente(Const.ING_PAN, Const.CATEGORIA_ING_PASTA, new VNut(266, 7.64, 50.61, 2.4, 4.31, 3.29, 0.681), R.drawable.ingrediente_pan, 1000));
            add(new Ingrediente(Const.ING_CEREALES, Const.CATEGORIA_ING_PASTA, new VNut(376, 7.24, 83.02, 5.3, 22.57, 3.38, 0.676), R.drawable.plato_cereales, 400));
            add(new Ingrediente(Const.ING_GALLETAS, Const.CATEGORIA_ING_PASTA, new VNut(486, 5.42, 68.97, 1.6, 27.62, 21.39, 0.328), R.drawable.ingrediente_galletas, 400));
            add(new Ingrediente(Const.ING_ARROZ, Const.CATEGORIA_ING_PASTA, new VNut(129, 2.66, 27.9, 0.4, 0.05, 0.28, 0.365), R.drawable.ingrediente_arroz, 1000));
            add(new Ingrediente(Const.ING_MAGDALENA, Const.CATEGORIA_ING_PASTA, new VNut(435, 4.35, 51.66, 1, 24.85, 23.87, 0.001), R.drawable.ingrediente_magdalena, 30));
            add(new Ingrediente(Const.ING_HARINA, Const.CATEGORIA_ING_PASTA, new VNut(364, 10.33, 76.31, 2.7, 0.27, 0.98, 0.002), R.drawable.ingrediente_harina, 30));
            add(new Ingrediente(Const.ING_CREMA_CHOCO, Const.CATEGORIA_ING_PASTA, new VNut(553, 13.33, 19.9, 16.6, 19.9, 46.6, 0.002), R.drawable.ingrediente_crema_choco, 500));
            add(new Ingrediente(Const.ING_CHOCO, Const.CATEGORIA_ING_PASTA, new VNut(535, 7.65, 59.4, 3.4, 51.5, 29.6, 0.079), R.drawable.ingrediente_chocolate, 200));

            add(new Ingrediente(Const.ING_CROQUETAS, Const.CATEGORIA_ING_PASTA, new VNut(241, 15.02, 12.87, 0.6, 2.78, 14.1, 0.858), R.drawable.plato_croqueta, 200));

        }};
    }

    public static List<Almacenamiento> rellenarListaAlmacen(){
        return new ArrayList<Almacenamiento> (){{

        }};
    }

    public static void introducirIngredienteCategorias(Realm realm){

        realm.copyToRealm(new CategoryIngredient(Const.CATEGORIA_ING_CARNE, "desayuno", R.drawable.category_carne));
        realm.copyToRealm(new CategoryIngredient(Const.CATEGORIA__ING_PESCADO, "desayuno", R.drawable.category_pescado));
        realm.copyToRealm(new CategoryIngredient(Const.CATEGORIA_ING_FRUTA, "desayuno", R.drawable.category_frutas));
        realm.copyToRealm(new CategoryIngredient(Const.CATEGORIA_ING_VERDURA, "desayuno", R.drawable.category_vegetable));
        realm.copyToRealm(new CategoryIngredient(Const.CATEGORIA_ING_LEGUMBRES, "desayuno", R.drawable.category_desayuno));
        realm.copyToRealm(new CategoryIngredient(Const.CATEGORIA_ING_LACTEOS, "desayuno", R.drawable.category_desayuno));
        realm.copyToRealm(new CategoryIngredient(Const.CATEGORIA_ING_PASTA, "desayuno", R.drawable.category_pasta));
        realm.copyToRealm(new CategoryIngredient(Const.CATEGORIA_ING_COMP, "desayuno", R.drawable.ingrediente_especias));
        realm.copyToRealm(new CategoryIngredient(Const.CATEGORIA_ING_BEBIDAS, "desayuno", R.drawable.plato_refresco));
        realm.copyToRealm(new CategoryIngredient(Const.CATEGORIA_ING_FRUTO_SECO, "desayuno", R.drawable.ingrediente_pistacho));
    }


    public static void introducirPlatosCategorias(Realm realm){
        Category categoria;

        realm.copyToRealm(new Category(Const.CATEGORIA_FAVORITE, "Platos favoritos", R.drawable.favorito));
        realm.copyToRealm(new Category(Const.CATEGORIA_STORE, "Platos almacenados", R.drawable.frigorifico));

        categoria = realm.copyToRealm(new Category(Const.CATEGORIA_DESAYUNO, "Platos frecuentados en el desayuno", R.drawable.category_desayuno));
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_COLACAO).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_GALLETAS).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_CEREALES).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_TOSTADA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_TOSTADA_ACE).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_TOSTADA_JAM).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_MAGDALENA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.ING_YOGURT).findFirst());

        categoria = realm.copyToRealm(new Category(Const.CATEGORIA_CARNE, "Platos con ingredientes cárnicos", R.drawable.category_carne));
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_POLLO_HORNO).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_TERNERA_PX).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_SOL_MOZ).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PECHUGA_PLANCHA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_CHULETA_PLANCHA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_LOMO_PLANCHA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_SALCHICHAS_VINO).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PASTEL_CARNE).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_ALBONDIGAS).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_CARNE_TOMATE).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PECHUGA_EMPANADA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_HUEVO_PASADO_AGUA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_HAMBURGUESA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_BOC_SERRA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_BOC_PECHUGA).findFirst());

        categoria = realm.copyToRealm(new Category(Const.CATEGORIA_PESCADO, "pescado", R.drawable.category_pescado));
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_MERLUZA_PLANCHA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PESCADO).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_CALAMARES_FRITOS).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PEZ_ESPADA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_SALMON_PLANCHA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_ALMEJAS).findFirst());


        categoria= realm.copyToRealm(new Category(Const.CATEGORIA_VEGETALES, "Vegetales", R.drawable.category_vegetable));
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_ENSALADA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_HABAS_JAM).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_TORTILLA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PISTO).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_ASADILLO).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PIMIENTO_FRITO).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_ENSALADILLA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_ESPINACAS_REVUELTAS).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_TORTILLA_ESPINACAS).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_CHAMPINIONES_ANDALUZA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_VERDURA_PLANCHA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PURE_PATA).findFirst());

        categoria = realm.copyToRealm(new Category(Const.CATEGORIA_PASTA, "Pasta", R.drawable.category_pasta));
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PASTA_ESP).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PASTA_BOLOÑESA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_ENSALADA_PASTA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_RAVIOLI_CHAMP).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_ARROZ_CUBANA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_LASAÑA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_RISOTTO).findFirst());

        categoria = realm.copyToRealm(new Category(Const.CATEGORIA_CUCHARA, "Platos de cuchara", R.drawable.categoria_cuchara));
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_SALMOREJO).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_POTAJE).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_LENTEJAS).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_GARBANZOS).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_SOPA).findFirst());

        categoria = realm.copyToRealm(new Category(Const.CATEGORIA_FRUTA, "Piezas de fruta", R.drawable.category_frutas));
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_MANZ_ROJA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_MANZ_VERDE).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_MELOCOTON).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PERA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PLATANO).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_MELON).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_NARANJA).findFirst());

        categoria = realm.copyToRealm(new Category(Const.CATEGORIA_PRECOCINADO, "Precocinados", R.drawable.categoria_comida_basura));
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_SANDWICH).findFirst());

        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_CROQUETAS).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PIZZA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PATATAS_FRITAS).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_PATATAS_FRITAS_BOLSA).findFirst());

        categoria = realm.copyToRealm(new Category(Const.CATEGORIA_BEBIDA, "Bebida", R.drawable.category_desayuno));
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_CAFE_SOLO).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_CAFE_LECHE).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_LECHE_ENTERA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_LECHE_DESNATADA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_ZUMO_NARANJA).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_REFRESCO).findFirst());
        categoria.getListPlatos().add(realm.where(Plato.class).equalTo("nombre", Const.PLAT_CERVEZA).findFirst());
    }

    public static Usuario crearUsuario(){
        return new Usuario("vacio",  "", Usuario.USUARIO_HOMBRE, "", -1, 0);
    }

    public static void crearPlatos(Realm realm) {
        Plato platoAux;
        IngredienteCantidad ingrCant;
        PasoPlato pasoPlato;

      platoAux = realm.copyToRealm(new Plato(Const.PLAT_POLLO_HORNO, DIFICULTAD_MEDIA, 30, "", R.drawable.plato_pollo, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_MUSLO_POLLO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA,150));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PATATA_ASAD).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 70));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_AJO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 10));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pelan las patatas y se cortan en rodajas finas para ponerlas como base en una bandeja para horno.", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pela y corta las cebollas y se ponen sobre la patata", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se ponen los muslos encima de las patatas y se añade a todo un vaso de agua y un chorro de aceite de oliva.", 0, R.drawable.paso_bol)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Salpimentamos y añadimos un buen pellizco de tomillo seco a cada muslo.", 0, R.drawable.paso_especias)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se introduce el pollo al horno a 220ºC con calor arriba y abajo.", 30, R.drawable.paso_horno)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Cuando esté bien dorado, se le da la vuelta a las piezas para que se doren uniformemente", 0, R.drawable.paso_horno)));

        platoAux = realm.copyToRealm(new Plato(Const.PLAT_ENSALADA, DIFICULTAD_FACIL, 10, "", R.drawable.plato_ensalada, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMATE).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PIM_VERDE).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ZANAHORIA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LECHUGA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.refrescarVN();

        platoAux = realm.copyToRealm( new Plato(Const.PLAT_PASTA_BOLOÑESA, DIFICULTAD_FACIL, 20, "", R.drawable.plato_espaguetti_tomate, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PASTA).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMATE).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_C_PICADA_MIX).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 100));
        platoAux.refrescarVN();


        platoAux = realm.copyToRealm( new Plato(Const.PLAT_PASTA_ESP, DIFICULTAD_MEDIA, 20, "", R.drawable.plato_spaguettis_con_tomate, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PASTA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_C_PICADA_TER).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 80));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PUERRO).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ZANAHORIA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 70));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CALABACIN).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_VINO_TINTO).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 70));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_AJO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 15));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PIMENTON).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pican las verduras en trozos pequeños", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa el aceite en la olla y añadimos las verduras excepto el ajo", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Cuando se hallan pochado las verduras, se añade el vaso de vino tinto y un vaso de agua y se cuece hasta que se reduzca", 0, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se tritura las verduras", 0, R.drawable.paso_batir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se empieza a cocer los espaguettis", 12, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se salpimenta la carne picada", 0, R.drawable.paso_especias)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se prepara una sartén con aceite y se echa el ajo", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Una vez refrito el ajo, se echa la carne picada", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se mezcla la carne picada y las verduras trituradas, y se le añade el pimentón", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se cuela la pasta y se mezcla con la salsa y carne", 0, R.drawable.pasos_emplatar)));



        platoAux = realm.copyToRealm(new Plato(Const.PLAT_SANDWICH, DIFICULTAD_FACIL, 5, "121", R.drawable.plato_sandwich, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PAN).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_YORK).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_QUESO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 10));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LECHUGA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 20));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMATE).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.refrescarVN();

        //-----------------------------------------------------------------------------------------------------------------------------------------------------------
        platoAux = realm.copyToRealm( new Plato(Const.PLAT_TERNERA_PX, DIFICULTAD_DIFICIL, 40, "", R.drawable.plato_carne_salsa, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LOMO_TERNERA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_AJO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 15));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ZANAHORIA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 70));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_VINO_PX).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 70));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMILLO).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 15));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PIMIENTA_NEGRA).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 5));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa el aceite en la olla y añadimos la carne con un poco de sal", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se cocina por los dos lados hasta quedarse hecha", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Partimos la zanahoria, cebolla y ajo en trozos pequeños", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Una vez hecha la carne, se saca y se echan a la olla los ingredientes anteriores", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Cuando las verduras esten tiernas, se apartan y se echan a la batidora", 0, R.drawable.paso_batir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se introduce medio vaso de agua y se bate hasta conseguir textura uniforme", 0, R.drawable.paso_batir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se vuelve a echar a la sarten la carne y lo batido anteriormente", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se le añada el tomillo y la pimienta negra", 0, R.drawable.paso_especias)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se vuelve a cocinar todo", 3, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se añade el pedro jimenez y se deja cocer lentamente", 20, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Comprobamos que la carne esta tierna y emplatamos", 0, R.drawable.pasos_emplatar)));

        //-----------------------------------------------------------------------------------------------------------------------------------------------------------
        platoAux = realm.copyToRealm( new Plato(Const.PLAT_SOL_MOZ, DIFICULTAD_DIFICIL, 40, "", R.drawable.plato_carne_salsa, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_SOLOMILLO_C).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PIMIENTA_NEGRA).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 5));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ALBARICOQUE).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 20));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PASAS).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 20));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_VINO_BLANCO).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 70));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CALDO_AVE).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 70));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CANELA_MOLIDA).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 5));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_MIEL).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 5));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se salpimenta el trozo de solomillo", 0, R.drawable.paso_especias)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa el aceite a la olla y añadimos el trozo de carne", 0, R.drawable.paso_especias)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se cocina por los lados hasta quedarse hecha", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se parte la cebolla y los albaricoques en trozos pequeños", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Una vez hecha la carne, se saca y se echan a la olla la cebolla", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Cuando se haya dorado la cebolla se echa el albaricoque, las pasas, el vino, el caldo de pollo y la canela", 5, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa la cucharada de miel y el solomillo", 20, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se aparta el solomillo en el plato y se echa la salsa en la batidora", 0, R.drawable.paso_batir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se bate la salsa y se rocia el solomillo con el resultado", 20, R.drawable.pasos_emplatar)));

        platoAux = realm.copyToRealm( new Plato(Const.PLAT_HABAS_JAM, DIFICULTAD_MEDIA, 15, "", R.drawable.plato_habas_jamon, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_HABAS).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_JAMON).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_AJO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 15));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PIMIENTA_NEGRA).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 15));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se sacan las habas de las vainas y se corta la cebolla en trozos no demasiado pequeños", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("En una sartén con suficiente aceite de oliva se añade lo anterior", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se añade la sal y la pimientamos", 0, R.drawable.paso_especias)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se rehoga hasta estar tiernas", 25, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Cuando estén casi listas se incorpora el jamón en taquitos y lo rehogamos todo junto ", 5, R.drawable.pasos_freir)));


        platoAux = realm.copyToRealm( new Plato(Const.PLAT_TORTILLA, DIFICULTAD_MEDIA, 20, "", R.drawable.plato_tortilla_patatas, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PATATA_FRIT).findFirst(), 2, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_HUEVO_FRITO).findFirst(), 4, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pelan y cortan en rodajas las patatas y la cebolla", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se colocan en la sartén y se recubre de aceite", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se frie ambos en fuego medio-suave", 10, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se apartan y se escurre el aceite", 0, R.drawable.paso_colar)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se baten los huevos", 0, R.drawable.paso_batir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se mezcla en un cuenco las patatas y el huevo batido", 10, R.drawable.paso_batir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa en una sartén la mezcla y se cuaja el huevo a fuego lento", 3, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se da la vuelta con un plato y se cuaja la otra parte", 3, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se da la vuelta con un plato y se cuaja la otra parte", 3, R.drawable.pasos_freir)));

        platoAux = realm.copyToRealm( new Plato(Const.PLAT_LENTEJAS, DIFICULTAD_MEDIA, 70, "", R.drawable.plato_lentejas, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LENTEJA).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_AJO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 10));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PATATA_COC).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 70));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ZANAHORIA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 80));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CHORIZO).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PIM_VERDE).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 70));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 15));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pican todas las verduras y se lavan las lentejas", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pone a cocer una olla con agua y se le añade un poco de aceite", 0, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se introducen todas las verduras y lentejas", 20, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Cuando lleve un tiempo cocido, se introduce la patata picada y el trozo de chorizo y se deja cocer", 40, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Una vez las lentejas estén blandas, se aparta el plato", 0, R.drawable.pasos_emplatar)));

        platoAux = realm.copyToRealm( new Plato(Const.PLAT_GARBANZOS, DIFICULTAD_FACIL, 40, "", R.drawable.plato_garbanzos, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_GARBANZOS).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CHORIZO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PATATA_COC).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 70));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ZANAHORIA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PIM_VERDE).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 70));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se debe de haber dejado reposar los garbanzos el día anterior", 0, R.drawable.paso_bol)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pican todas las verduras y se lavan los garbanzos", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pone a cocer una olla con agua y se le añade un poco de aceite", 0, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se introducen todas las verduras y garbanzos", 20, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Cuando lleve un tiempo cocido, se introduce la patata picada y el trozo de chorizo y se deja cocer", 40, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Una vez las garbanzos estén blandas, se aparta el plato", 0, R.drawable.pasos_emplatar)));

        platoAux = realm.copyToRealm( new Plato(Const.PLAT_PAELLA, DIFICULTAD_MEDIA, 40, "", R.drawable.plato_paella, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ARROZ).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 80));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CHAMPINION).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 80));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ZANAHORIA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LAUREL).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 5));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PECHUGA_POLLO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 80));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pican todas las verduras y la carne", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se añade un poco de aceite a la olla y se refrie las verduras", 5, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se introduce  la carne a la olla y se frie un poco", 5, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se introduce los champiñones a la olla y se refrie con lo demás", 5, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa el arroz y dos vasos de agua y se deja cocer", 10, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se deja reposar el arroz en la olla y se emplata", 3, R.drawable.pasos_emplatar)));

        platoAux = realm.copyToRealm( new Plato(Const.PLAT_POTAJE, DIFICULTAD_MEDIA, 40, "", R.drawable.plato_sopa, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ZANAHORIA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PATATA_COC).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ALCACHOFAS).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LOMO_TERNERA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pican todas las verduras y la carne", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se añade un poco de aceite a la olla y se refrie las verduras", 5, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se introduce la carne a la olla y se frie un poco", 5, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa el agua y las alcachofas a la olla y se deja cocer", 10, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa la patata a la olla", 10, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Cuando este la carne tierna y la patata cocida, se aparta", 0, R.drawable.pasos_emplatar)));

        platoAux = realm.copyToRealm( new Plato(Const.PLAT_TOSTADA, DIFICULTAD_FACIL, 5, "", R.drawable.plato_tostada, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PAN).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 80));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMATE).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 60));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.refrescarVN();

        platoAux = realm.copyToRealm( new Plato(Const.PLAT_TOSTADA_ACE, DIFICULTAD_FACIL, 5, "", R.drawable.plato_tostada, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PAN).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 80));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.refrescarVN();

        platoAux = realm.copyToRealm( new Plato(Const.PLAT_TOSTADA_JAM, DIFICULTAD_FACIL, 5, "", R.drawable.plato_tostada, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PAN).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 80));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMATE).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 60));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_JAMON).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 30));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.refrescarVN();

        platoAux = realm.copyToRealm( new Plato(Const.PLAT_COLACAO, DIFICULTAD_FACIL, 0, "", R.drawable.ingrediente_colacao, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_COLACAO).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 20));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_LECHE_ENTERA, DIFICULTAD_FACIL, 0, "", R.drawable.ingrediente_leche, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LECHE_ENT).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 200));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_YOGURT, DIFICULTAD_FACIL, 0, "", R.drawable.plato_yogur, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_YOGURT).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 150));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_LECHE_DESNATADA, DIFICULTAD_FACIL, 1, "", R.drawable.ingrediente_leche, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LECHE_DES).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 200));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_CAFE_LECHE, DIFICULTAD_FACIL, 0, "", R.drawable.plato_cafe, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CAFE).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LECHE_ENT).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 150));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_CAFE_SOLO, DIFICULTAD_FACIL, 0, "", R.drawable.plato_cafe, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CAFE).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_REFRESCO, DIFICULTAD_FACIL, 0, "", R.drawable.plato_refresco, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_REFRESCO).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 200));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_CERVEZA, DIFICULTAD_FACIL, 0, "", R.drawable.plato_cerveza, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CERVEZA).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 200));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_GALLETAS, DIFICULTAD_FACIL, 0, "", R.drawable.ingrediente_galletas, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_GALLETAS).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 80));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_CEREALES, DIFICULTAD_FACIL, 0, "", R.drawable.plato_cereales, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEREALES).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 80));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_SALMOREJO, DIFICULTAD_FACIL, 10, "", R.drawable.plato_salmorejo, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMATE).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 200));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_AJO).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 10));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_JAMON).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 60));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 15));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PAN).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 50));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pica el tomate", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se introduce el tomate, el pan, el diente de ajo y el aceite a la batidora", 0, R.drawable.paso_batir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa un poco de agua si se desea una testura más suave", 0, R.drawable.paso_batir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Una vez batido, se emplata y se echa el jamón por encima", 0, R.drawable.pasos_emplatar)));

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_ENSALADA_PASTA, DIFICULTAD_FACIL, 20, "", R.drawable.plato_ensalada_pasta, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMATE).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PASTA).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_HUEVO_COCIDO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_AJO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 15));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PIM_VERDE).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pican todas las verduras", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se cuece el huevo", 15, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se cuece la pasta", 8, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se separa la llema del huevo y se echa en un mortero", 0, R.drawable.paso_batir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa el ajo picado y una cuchara de aceite al mortero y se aplasta con el huevo ", 0, R.drawable.paso_batir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se aparta en un bol la pasta y se mezcla con la verdura picada y el resultado del mortero", 0, R.drawable.paso_bol)));


        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_PISTO, DIFICULTAD_MEDIA, 40, "", R.drawable.plato_salmorejo, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_BERENJENA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CALABACIN).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMATE).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_AJO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 15));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_HUEVO_FRITO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 80));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 15));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pican todas las verduras", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa la cuchara de aceite a la olla y se echa la cebolla y el ajo", 0, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa las demás verduras y se deja cocer", 30, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Una vez esten blandas se aparta", 0, R.drawable.pasos_emplatar)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se frie un huevo para acompañar", 0, R.drawable.pasos_freir)));

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_MANZ_ROJA, DIFICULTAD_FACIL, 0, "", R.drawable.ingrediente_manzana, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_MANZ_ROJA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.refrescarVN();
        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_MANZ_VERDE, DIFICULTAD_FACIL, 0, "", R.drawable.ingrediente_manzana, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_MANZ_VERDE).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.refrescarVN();
        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_MELOCOTON, DIFICULTAD_FACIL, 0, "", R.drawable.ingrediente_melocoton, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_MELOCOTON).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.refrescarVN();
        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_PERA, DIFICULTAD_FACIL, 0, "", R.drawable.ingrediente_pera, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PERA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.refrescarVN();
        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_PLATANO, DIFICULTAD_FACIL, 0, "", R.drawable.ingrediente_platano, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PLATANO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.refrescarVN();
        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_MELON, DIFICULTAD_FACIL, 0, "", R.drawable.ingrediente_melon, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_MELON).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.refrescarVN();


        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_ALMEJAS, DIFICULTAD_FACIL, 0, "", R.drawable.plato_almejas, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ALMEJAS).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 100));
        platoAux.refrescarVN();


        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_RAVIOLI_CHAMP, DIFICULTAD_FACIL, 20, "", R.drawable.plato_pasta_ravioli, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_RAVIOLI).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 150));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_NATA).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CHAMPINION).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 15));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pica la cebolla y los champiñones", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa la cuchara de aceite a la sartén y se frie la cebolla", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echan los champiñones a la sartén y se cocinan con la cebolla", 5, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa la nata y se deja cocinar todo", 5, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se cuecen los raviolis", 3, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echan los raviolis dentro de la salsa, se mezcla todo y se aparta", 3, R.drawable.pasos_emplatar)));

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_ASADILLO, DIFICULTAD_FACIL, 20, "", R.drawable.ingrediente_pimiento_rojo, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PIM_ROJO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_AJO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 15));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se mete el pimiento y los dientes de ajo al horno", 30, R.drawable.paso_horno)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pelan los pimientos hechos y el ajo y se mezcla juntos al aceite en un plato", 0, R.drawable.pasos_freir)));


        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_PIMIENTO_FRITO, DIFICULTAD_FACIL, 10, "", R.drawable.ingrediente_pimiento, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PIM_VERDE).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 30));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_PURE_PATA, DIFICULTAD_FACIL, 10, "", R.drawable.plato_pure_patata, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PATATA_COC).findFirst(), 2, IngredienteCantidad.TIPO_PIEZA, 200));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LECHE_ENT).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 20));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_ENSALADILLA, DIFICULTAD_MEDIA, 40, "", R.drawable.plato_ensaladilla, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PATATA_COC).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ZANAHORIA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITUNA_VER).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 40));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_MAIZ).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ATUN).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 80));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_MAYONESA).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 50));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pela y pica la zamahoria y la cebolla", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pone a cocer la patata y el huevo", 20, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pela y parte la patata y el huevo dentro de un bol", 0, R.drawable.paso_bol)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa todo lo demás y se mezcla dentro del bol", 0, R.drawable.paso_bol)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa la mayonesa y se mezcla todo", 0, R.drawable.paso_bol)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se mete en la nevera y se deja enfriar para poder apartarse", 0, R.drawable.pasos_emplatar)));

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_ESPINACAS_REVUELTAS, DIFICULTAD_FACIL, 20, "", R.drawable.plato_espinacas_revueltas, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ESPINACAS).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 200));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_HUEVO_FRITO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 80));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_AJO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 15));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 15));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se parte bien el ajo", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se cuecen las espinacas en una olla", 10, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa en una sartén  la cuchara de aceite y el ajo partido para que se dore", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echan las espinacas a la sartén", 5, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa el huevo y se mezcla bien", 5, R.drawable.pasos_freir)));


        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_TORTILLA_ESPINACAS, DIFICULTAD_FACIL, 20, "", R.drawable.plato_tortilla_patatas, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ESPINACAS).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 200));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_HUEVO_FRITO).findFirst(), 3, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se cuecen las espinacas en una olla", 10, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se baten los huevos", 0, R.drawable.paso_batir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa en una sartén las espinacas cocidas y los huevos batidos y se deja hacer la cara", 3, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se le da la vuelta para hacer la otra cara", 3, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se aparta en un plato una vez que esten bien hechas las dos caras", 0, R.drawable.pasos_emplatar)));



        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_CHAMPINIONES_ANDALUZA, DIFICULTAD_MEDIA, 10, "", R.drawable.plato_champiniones, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_AJO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 15));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PEREJIL).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 15));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CHAMPINION).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 200));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PIMENTON).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pica la cebolla, el ajo y los champiñones", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa en una sartén el aceite y la cebolla", 3, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echan a la sartén los champiñones", 5, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("En un mortero se echa el ajo picado, pimentón, perejil y un poco de aceite y se machaca", 0, R.drawable.paso_batir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa la mezcla a la sartén y se mezcla con los champiñones", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se apartada cuando se encuentre todo mezclado uniformemente", 0, R.drawable.pasos_emplatar)));

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_PECHUGA_PLANCHA, DIFICULTAD_FACIL, 10, "", R.drawable.plato_lomo_plancha, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PECHUGA_POLLO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_PECHUGA_EMPANADA, DIFICULTAD_MEDIA, 15, "", R.drawable.plato_filete_carne, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PECHUGA_POLLO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_LOMO_PLANCHA, DIFICULTAD_FACIL, 10, "", R.drawable.plato_lomo_plancha, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LOMO_CERDO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_CHULETA_PLANCHA, DIFICULTAD_FACIL, 10, "", R.drawable.plato_filete_carne, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LOMO_TERNERA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_HUEVO_PASADO_AGUA, DIFICULTAD_FACIL, 5, "", R.drawable.plato_huevo_agua, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_HUEVO_COCIDO).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 80));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se cuece el huevo en una olla", 3, R.drawable.paso_cocer)));


        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_ALBONDIGAS, DIFICULTAD_MEDIA, 20, "", R.drawable.plato_albondigas, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_C_PICADA_MIX).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_AJO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 15));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PEREJIL).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 5));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 15));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pica bien pequeño el ajo y el perejil", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa en un bol la carne picada, el ajo y el perjil y se mezcla bien", 0, R.drawable.paso_bol)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se tapa el bol y se deja reposar la masa un tiempo en el frigorífico", 0, R.drawable.paso_bol)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se hacen bolas con las manos y se echan a la sartén para freirlas", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se doran bien por todas las partes y se apartan", 0, R.drawable.pasos_emplatar)));

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_CARNE_TOMATE, DIFICULTAD_MEDIA, 40, "", R.drawable.plato_carne_salsa, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LOMO_TERNERA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMATE_FRITO).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMILLO).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 5));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_VINO_BLANCO).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 80));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CALDO_AVE).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 100));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pica la cebolla y la ternera en tacos", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa un poco de aceite en una olla y se echa la cebolla", 5, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa la carne a la sartén y se dora un poco", 0, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa un vaso de vino blanco y esperamos a que reduzca ", 5, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa un vaso de caldo de pollo y se deja que reduzca", 10, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa la salsa de tomate y el tomillo  y se deja que reduzca", 10, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Cuando este la carne tierna, se saca del fuego y se emplata ", 0, R.drawable.pasos_emplatar)));

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_SALCHICHAS_VINO, DIFICULTAD_FACIL, 10, "", R.drawable.plato_salchichas_vino, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_SALCHICHAS).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_VINO_BLANCO).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 150));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_AJO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 15));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_PASTEL_CARNE, DIFICULTAD_MEDIA, 60, "", R.drawable.plato_pastel_carne, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_C_PICADA_MIX).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PATATA_COC).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_QUESO).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMATE_FRITO).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 50));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se fríe la carne picada en una sartén", 10, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se cuece la patata en una olla", 15, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se aplasta las patatas en un bol hasta hacer un puré", 15, R.drawable.paso_batir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("En una bandeja de horno se esparce la mitad del puré en la base", 0, R.drawable.paso_horno)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Encima de la base se unta unas cucharadas de tomate frito y se echa la carne picada", 0, R.drawable.paso_bol)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa otras cucharadas de tomate frito y se esparce la otra mitad del puré", 0, R.drawable.paso_bol)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se raya y se esparce el queso encima de la última capa", 0, R.drawable.paso_rayar)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se mete la bandeja al horno a 200 grados", 0, R.drawable.paso_horno)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Una vez fundido el queso, se aparta el plato", 0, R.drawable.pasos_emplatar)));



        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_SALMON_PLANCHA, DIFICULTAD_FACIL, 10, "", R.drawable.plato_salmon_plancha, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_SALMON).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.refrescarVN();


        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_MERLUZA_PLANCHA, DIFICULTAD_FACIL, 10, "", R.drawable.plato_merluza, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_MERLUZA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_AJO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 10));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.refrescarVN();


        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_PESCADO, DIFICULTAD_MEDIA, 20, "", R.drawable.plato_pescado_frito, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_MERLUZA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_CALAMARES_FRITOS, DIFICULTAD_MEDIA, 10, "", R.drawable.plato_pescado_frito, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_MUSLO_POLLO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_PEZ_ESPADA, DIFICULTAD_FACIL, 10, "", R.drawable.plato_pez_espada_limon, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PEZ_ESPADA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 150));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LIMON).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_SOPA, DIFICULTAD_FACIL, 10, "", R.drawable.plato_sopa, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CALDO_AVE).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PASTA).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_HUEVO_COCIDO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 80));
        platoAux.refrescarVN();


        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_ARROZ_CUBANA, DIFICULTAD_FACIL, 10, "", R.drawable.plato_arroz_cubana, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ARROZ).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 150));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMATE_FRITO).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_HUEVO_FRITO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 80));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_HAMBURGUESA, DIFICULTAD_FACIL, 10, "", R.drawable.plato_hamburguesa, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PAN).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMATE).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LECHUGA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 20));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_C_PICADA_MIX).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_QUESO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 10));
        platoAux.refrescarVN();




        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_CROQUETAS, DIFICULTAD_FACIL, 5, "", R.drawable.plato_croqueta, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CROQUETAS).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 50));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_BOC_SERRA, DIFICULTAD_FACIL, 15, "", R.drawable.plato_bocadillo, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PAN).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_LOMO_CERDO).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PIM_VERDE).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_JAMON).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_QUESO).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_BOC_PECHUGA, DIFICULTAD_FACIL, 10, "", R.drawable.plato_bocadillo, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PAN).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PECHUGA_POLLO).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_QUESO).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_PIZZA, DIFICULTAD_FACIL, 20, "", R.drawable.plato_pizza, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_MUSLO_POLLO).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 100));
        platoAux.refrescarVN();


        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_PATATAS_FRITAS, DIFICULTAD_FACIL, 10, "", R.drawable.plato_patatas_fritas, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PATATA_FRIT).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 150));
        platoAux.refrescarVN();

        platoAux =  realm.copyToRealm( new Plato(Const.PLAT_PATATAS_FRITAS_BOLSA, DIFICULTAD_FACIL, 0, "", R.drawable.plato_patatas_fritas_bolsa, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PATATA_FRIT).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 100));
        platoAux.refrescarVN();


        platoAux = realm.copyToRealm( new Plato(Const.PLAT_MAGDALENA, DIFICULTAD_FACIL, 0, "", R.drawable.ingrediente_magdalena, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_MAGDALENA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 50));
        platoAux.refrescarVN();

        platoAux = realm.copyToRealm( new Plato(Const.PLAT_VERDURA_PLANCHA, DIFICULTAD_FACIL, 10, "", R.drawable.plato_verduras_plancha, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CALABACIN).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_BERENJENA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ESPARRAGOS).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ACEITE).findFirst(), 1, IngredienteCantidad.TIPO_CUCHARADA, 10));
        platoAux.refrescarVN();

        platoAux = realm.copyToRealm( new Plato(Const.PLAT_LASAÑA, DIFICULTAD_MEDIA, 30, "", R.drawable.plato_pastel_carne, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_PASTA).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_C_PICADA_MIX).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_TOMATE_FRITO).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_QUESO).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 50));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se fríe la carne picada en una sartén", 10, R.drawable.pasos_freir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se cuece las láminas de pasta en una olla", 15, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("En una bandeja de horno se esparce las primeras láminas en la base", 0, R.drawable.paso_horno)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Encima de la base se unta unas cucharadas de tomate frito y se esparce la mitad de carne picada", 0, R.drawable.paso_bol)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa otras cucharadas de tomate frito y se esparce más láminas", 0, R.drawable.paso_bol)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se repite el proceso otra vez dejando la capa de pasta encima", 0, R.drawable.paso_bol)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se raya y se esparce el queso encima de la última capa", 0, R.drawable.paso_rayar)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se mete la bandeja al horno a 200 grados", 0, R.drawable.paso_horno)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Una vez fundido el queso, se aparta el plato", 0, R.drawable.pasos_emplatar)));


        platoAux = realm.copyToRealm( new Plato(Const.PLAT_NARANJA, DIFICULTAD_FACIL, 0, "", R.drawable.ingrediente_naranja, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_NARANJA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 100));
        platoAux.refrescarVN();

        platoAux = realm.copyToRealm( new Plato(Const.PLAT_ZUMO_NARANJA, DIFICULTAD_FACIL, 5, "", R.drawable.plato_zumo_naranja, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_NARANJA).findFirst(), 3, IngredienteCantidad.TIPO_PIEZA, 160));
        platoAux.refrescarVN();

        platoAux = realm.copyToRealm( new Plato(Const.PLAT_RISOTTO, DIFICULTAD_DIFICIL, 30, "", R.drawable.plato_arrozfrito, Plato.PLATO_PRED));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ARROZ).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CEBOLLA).findFirst(), 1, IngredienteCantidad.TIPO_MITAD, 50));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_ZANAHORIA).findFirst(), 1, IngredienteCantidad.TIPO_PIEZA, 80));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CHAMPINION).findFirst(), 1, IngredienteCantidad.TIPO_PESO_GRAMOS, 100));
        platoAux.getIngredientes().add(new IngredienteCantidad(realm.where(Ingrediente.class).equalTo("nombre", Const.ING_CALDO_AVE).findFirst(), 1, IngredienteCantidad.TIPO_VASO, 100));
        platoAux.refrescarVN();
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se pica la cebolla y la zanahoria", 0, R.drawable.paso_partir)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa a una olla el caldo de pollo, la zanahoria y la cebolla", 10, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("En otro olla echamos una cucharada de aceite y los champiñones dejandolos hacerse", 0, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se echa el arroz y sofreímos brevemente el arroz", 0, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se va echando con una cucharada poco a poco el caldo de la otra olla al arroz dejandole que cueca", 0, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se repite este paso hasta que la otra olla se acabe", 0, R.drawable.paso_cocer)));
        platoAux.getPasosPlato().add(realm.copyToRealm(new PasoPlato("Se deja reposar un poco el arroz  y se le echa queso al gusto", 0, R.drawable.pasos_emplatar)));
    }

    public static void asignarSemana(Realm realm){
        Semana semana ;
        Dia diaD, diaAux;

        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        int dia = Integer.valueOf(sdf.format(new Date()));
        sdf = new SimpleDateFormat("MM");
        int mes = Integer.valueOf(sdf.format(new Date()));

        diaD = realm.where(Dia.class).equalTo("dia",dia).equalTo("mes", mes).findFirst();
        diaAux = realm.where(Dia.class).equalTo("id", diaD.getId()+6).findFirst();

        semana = realm.copyToRealm(new Semana(dia,mes,diaAux.getDia(),diaAux.getMes()));
        semana.cargarDias();

    }

    public static void crearHorario(Realm realm){

        Mes mes;
        Dia dia;
        FranjaHorario franja;
        Anio anio;
        int cont = 3;

        for(int a=0;a<4;a++) {
            anio = realm.copyToRealm(new Anio(2020 + a));

            for (int i = 1; i < 13; i++) {
                mes = realm.copyToRealm(new Mes(anio.getAnio(), i));
                realm.copyToRealm(mes);
                anio.getList_mes().add(mes);


                for (int j = 1; j < Mes.getDiasMes(i) + 1; j++) {
                    dia = realm.copyToRealm(new Dia(anio.getAnio(), i, j, Dia.getDia(cont % 7)));
                    mes.getListDias().add(dia);
                    cont++;

                    for (int x = 0; x < 4; x++) {
                        franja = realm.copyToRealm(new FranjaHorario(anio.getAnio(), i, j, x));
                        dia.getFranjas().add(franja);
                    }
                }
            }
        }
    }
}
