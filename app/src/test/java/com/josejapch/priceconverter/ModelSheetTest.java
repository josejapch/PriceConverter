package com.josejapch.priceconverter;

import android.graphics.PorterDuff;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @author josejapch
 * Test unitarios sobre la clase ModelSheet
 */
public class ModelSheetTest {
    @Test
    public void getCoinChange() throws Exception {
        ModelSheet model = new ModelSheet();
        HashMap<String,String> values=model.getCoinChange(0,1);

        assertEquals("ERROR MODELSHEET getCoinChange: Valor EURO respecto EURO=1.", "1.0 €",values.get("EU"));
        assertEquals("ERROR MODELSHEET getCoinChange: Valor DOLAR respecto EURO=1.", "0.0 $",values.get("USD"));
        assertEquals("ERROR MODELSHEET getCoinChange: Valor LIBRA respecto EURO=1.", "0.0 £",values.get("GBP"));

        values=model.getCoinChange(1,1);

        assertEquals("ERROR MODELSHEET getCoinChange: Valor EURO respecto LIBRA=1.", "0.0 €",values.get("EU"));
        assertEquals("ERROR MODELSHEET getCoinChange: Valor DOLAR respecto LIBRA=1.", "0.0 $",values.get("USD"));
        assertEquals("ERROR MODELSHEET getCoinChange: Valor LIBRA respecto LIBRA=1.", "1.0 £",values.get("GBP"));

        values=model.getCoinChange(2,1);

        assertEquals("ERROR MODELSHEET getCoinChange: Valor EURO respecto DOLAR=1.", "0.0 €",values.get("EU"));
        assertEquals("ERROR MODELSHEET getCoinChange: Valor DOLAR respecto DOLAR=1.", "1.0 $",values.get("USD"));
        assertEquals("ERROR MODELSHEET getCoinChange: Valor LIBRA respecto DOLAR=1.", "0.0 £",values.get("GBP"));
    }

    @Test
    public void round() throws Exception {

        Double actual = ModelSheet.round(1.234,2);
        Double expected = 1.23;
        assertEquals("ERROR MODELSHEET round: Caso de redondeo (2 decimales, abajo).", expected,actual);

        actual = ModelSheet.round(1.235,2);
        expected = 1.24;
        assertEquals("ERROR MODELSHEET round: Caso de redondeo (2 decimales, arriba).", expected,actual);
    }

    @Test
    public void getNoIVA() throws Exception {
        ModelSheet model = new ModelSheet();
        Double actual, expected;

        actual=model.getNoIVA();
        expected = 0.0;
        assertEquals("ERROR CONTROLLER getNoIVA: Caso de cadena vacía.", expected,actual);

        model.setAmount(100);
        model.setIva(21);
        actual=model.getNoIVA();
        expected = 82.64;
        assertEquals("ERROR CONTROLLER getNoIVA: Caso de 21% iva.", expected,actual);

        model.setIva(10);
        actual=model.getNoIVA();
        expected = 90.91;
        assertEquals("ERROR CONTROLLER getNoIVA: Caso de 10% iva.", expected,actual);

        model.setIva(4);
        actual=model.getNoIVA();
        expected = 96.15;
        assertEquals("ERROR CONTROLLER getNoIVA: Caso de 4% iva.", expected,actual);
    }

    @Test
    public void makeDiscount() throws Exception {

        ModelSheet model = new ModelSheet();
        Double expected, actual;

        actual=model.makeDiscount();
        expected=0.0;
        assertEquals("ERROR makeDiscount: Sin cambios en importe y descuento.", expected,actual);

        model.setDiscount(20);
        model.setAmount(100);
        actual=model.makeDiscount();
        expected=80.0;
        assertEquals("ERROR makeDiscount: Importe y descuento establecido", expected,actual);

    }

}