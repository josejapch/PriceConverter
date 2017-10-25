package com.josejapch.priceconverter;

import java.util.HashMap;

/**
 * Clase intermediaria para la comunicación entre la interfaz (MainActivity) y la clase con las
 * operaciones necesarias para hacer los cálculos (ModelSheet).
 */
class Controller {


    private ModelSheet sheet;

    /**
     * Constructor
     */
    Controller(){

        sheet = new ModelSheet();
    }

    /**
     * Método para la obtención del texto con el cálculo del importe sin IVA.
     * @param amount Importe.
     * @param iva IVA aplicado al importe.
     * @return Importe sin IVA en formato texto.
     */
    String getTextNoIVA(String amount, int iva){

        if (!amount.isEmpty()){
            sheet.setAmount(Double.parseDouble(amount));
        }

        switch (iva){
            case 0: sheet.setIva(21);
                break;
            case 1: sheet.setIva(10);
                break;
            case 2: sheet.setIva(4);
                break;
        }
        return String.valueOf(sheet.getNoIVA());
    }

    /**
     * Método para la obtención del texto con el cálculo del descuento sobre el importe.
     * @param sDiscount Porcentaje de descuento.
     * @return Importe con descuento y símbolo de Euro en formato texto o error si se introduce un
     * porcentaje de descuento no válido.
     */
    String getTextDiscount(String sDiscount){

        String result;

        if (!sDiscount.isEmpty()){

            Double discount = Double.parseDouble(sDiscount);

            if((discount<=100) && (discount>=0)){

                sheet.setDiscount(discount);
                result=(sheet.makeDiscount()).toString()+" €";
            }else{
                result="ERROR";
            }
        }else{
            sheet.setDiscount(0.0);
            result=(sheet.makeDiscount()).toString()+" €";
        }
        return result;
    }

    /**
     * Método para obtener los valores del cambio de moneda según el tipo de moneda que se inserte.
     * @param option Tipo de moneda del importe.
     * @param sAmount Importe.
     * @return Valores del cambio de moneda.
     */
    HashMap<String,String> getChangeValues(int option, String sAmount){

        return sheet.getCoinChange(option,Double.parseDouble(sAmount));
    }

    /**
     * Método para establecer el valor de las monedas (libra y dolar) con respecto al euro.
     * @param gbp Valor de la libra.
     * @param usd Valor del dolar.
     */
    void setCoinValues(double gbp, double usd){

        sheet.setGBPvalue(gbp);
        sheet.setUsd_value(usd);
    }

    /**
     * Método para obtener la instancia de la clase ModelSheet sobre la que se está operando.
     * @return Instancia de ModelSheet.
     */
    ModelSheet getSheet(){ return sheet; }
}
