package com.josejapch.priceconverter;

import java.util.HashMap;

/**
 * Clase con las operaciones necesarias para realizar los cálculos requeridos por la aplicación.
 */

public class ModelSheet {

    double amount = 0.00;
    double discount = 0.00;
    double ch_eu = 0.00;
    double ch_usd = 0.00;
    double ch_pound = 0.00;
    double gbp_value = 0.0;
    double usd_value = 0.0;
    double iva = 21;

    /**
     * Constructor
     */
    public ModelSheet(){}

    /**
     * Método para obtener el importe.
     * @return Importe
     */
    public double getAmount(){ return amount; }

    /**
     * Método para obtener el porcentaje de descuento.
     * @return Porcentaje de descuento
     */
    public double getDiscount(){ return discount; }

    /**
     * Método para obtener el valor del cambio en euros.
     * @return Valor en euros.
     */
    public double getEuro(){ return ch_eu; }

    /**
     * Método para obtener el valor del cambio en dólares.
     * @return Valor en dólares.
     */
    public double getDolar(){ return ch_usd; }

    /**
     * Método para obtener el valor del cambio en libras.
     * @return Valor en libras.
     */
    public double getPound(){ return ch_pound; }

    /**
     * Método para obtener el valor de la libra respecto al euro.
     * @return Valor de la libra.
     */
    public double getGBPvalue(){ return gbp_value; }

    /**
     * Método para obtener el valor del dólar respecto al euro.
     * @return Valor del dólar.
     */
    public double getUSDvalue() { return usd_value; }

    /**
     * Método para obtener el porcentaje de IVA aplicado al importe.
     * @return Porcentaje de IVA.
     */
    public double getIva(){ return iva; }

    /**
     * Método para establecer el importe.
     * @param d Porcentaje de IVA.
     */
    public void setAmount(double d){ amount=d; }

    /**
     * Método para establecer el porcentaje de descuento.
     * @param d Porcentaje de descuento.
     */
    public void setDiscount(double d){ discount=d; }

    /**
     * Método para establecer el valor del cambio en euros.
     * @param d Cambio en euros.
     */
    public void setEuro(double d){ ch_eu=d; }

    /**
     * Método para establecer el valor del cambio en dólares.
     * @param d Cambio en dólares.
     */
    public void setDolar(double d){ ch_usd=d; }

    /**
     * Método para establecer el valor del cambio en libras.
     * @param d Cambio en libras.
     */
    public void setPound(double d){ ch_pound=d; }

    /**
     * Método para obtener el cambio de moneda en euros, dólares y libras.
     * @param option Tipo de moneda de la cantidad sobre la que se efectúa el cambio.
     * @param d Cantidad sobre la que se efectúa el cambio.
     * @return Cambio en euros (EUR), dólares (USD) y libras (GBP).
     */
    public HashMap<String,String> getCoinChange (int option, double d){

        HashMap<String,String> result = new HashMap<String, String>();

        switch (option){
            case 0:
                setEuro(round(d,2));
                setDolar(round(ch_eu*usd_value,2));
                setPound(round(ch_eu*gbp_value,2));
                break;
            case 1:
                setPound(round(d,2));
                if (gbp_value==0) setEuro(0.0);
                else setEuro(round(ch_pound/gbp_value,2));
                setDolar(round(ch_eu*usd_value,2));
                break;
            case 2:
                setDolar(round(d,2));
                if (usd_value==0) setEuro(0.0);
                else setEuro(round(ch_usd/usd_value,2));
                setPound(round(ch_eu*gbp_value,2));
                break;
        }

        result.put("EU",String.valueOf(getEuro())+" €");
        result.put("USD",String.valueOf(getDolar())+" $");
        result.put("GBP",String.valueOf(getPound())+" £");

        return result;
    }

    /**
     * Método para establecer el valor de la libra respecto al euro.
     * @param d Valor de la libra.
     */
    public void setGBPvalue(double d){ gbp_value=d; }

    /**
     * Método para establecer el valor del dólar respecto al euro.
     * @param d Valor del dólar.
     */
    public void setUsd_value(double d) { usd_value=d; }

    /**
     * Método para establecer el porcentaje de IVA aplicado en el importe.
     * @param d Porcentaje de IVA.
     */
    public void setIva(double d) { iva=d; }

    /**
     * Método para obtener el redondeo de una cifra.
     * @param d Cifra a redondear.
     * @param dec_num Número de decimales que se quiere redondear.
     * @return Cifra redondeada.
     */
    public static double round(double d, int dec_num){
        double round_d = d;
        double entera;

        entera = Math.floor(round_d);
        round_d=(round_d-entera)*Math.pow(10, dec_num);
        round_d=Math.round(round_d);
        round_d=(round_d/Math.pow(10, dec_num))+entera;

        return round_d;
    }

    /**
     * Método para obtener el valor sin IVA del importe.
     * @return Importe sin IVA.
     */
    public Double getNoIVA(){

        return round(amount/(1+iva/100),2);
    }

    /**
     * Método para obtener el descuento aplicado.
     * @return Importe con descuento.
     */
    public Double makeDiscount(){

        return round(amount-amount*(discount/100),2);
    }

}
