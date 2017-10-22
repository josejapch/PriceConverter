package com.josejapch.priceconverter;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @author josejapch
 * Test unitarios sobre la clase Controller
 */
public class ControllerTest {

    @Test
    public void getTextNoIVA() throws Exception {

        Controller control = new Controller();
        Double actual, expected;

        actual=Double.parseDouble(control.getTextNoIVA("",0));
        expected = 0.0;
        assertEquals("ERROR CONTROLLER getTextNoIVA: Caso de cadena vacía.", expected,actual);

        actual=Double.parseDouble(control.getTextNoIVA("100",0));
        expected = 82.64;
        assertEquals("ERROR CONTROLLER getTextNoIVA: Caso de 21% iva.", expected,actual);

        actual=Double.parseDouble(control.getTextNoIVA("100",1));
        expected = 90.91;
        assertEquals("ERROR CONTROLLER getTextNoIVA: Caso de 10% iva.", expected,actual);

        actual=Double.parseDouble(control.getTextNoIVA("100",2));
        expected = 96.15;
        assertEquals("ERROR CONTROLLER getTextNoIVA: Caso de 4% iva.", expected,actual);
    }

    @Test
    public void getTextDiscount() throws Exception {

        Controller control = new Controller();
        String actual, expected;

        actual = control.getTextDiscount("");
        expected = "0.0 €";
        assertEquals("ERROR CONTROLLER getTextDiscount: Caso de cadena vacía.", expected,actual);

        actual = control.getTextDiscount("20");
        expected = "0.0 €";
        assertEquals("ERROR CONTROLLER getTextDiscount: Caso de cadena no vacía (20%).", expected,actual);

        actual = control.getTextDiscount("-1");
        expected = "ERROR";
        assertEquals("ERROR CONTROLLER getTextDiscount: Caso de cadena no vacía (-1%).", expected,actual);

        actual = control.getTextDiscount("101");
        expected = "ERROR";
        assertEquals("ERROR CONTROLLER getTextDiscount: Caso de cadena no vacía (101%).", expected,actual);
    }

    @Test
    public void getChangeValues() throws Exception {

        Controller control = new Controller();
        HashMap<String,String> values=control.getChangeValues(0,"1");

        assertEquals("ERROR CONTROLLER getChangeValues: Valor EURO respecto EURO=1.", "1.0 €",values.get("EU"));
        assertEquals("ERROR CONTROLLER getChangeValues: Valor DOLAR respecto EURO=1.", "0.0 $",values.get("USD"));
        assertEquals("ERROR CONTROLLER getChangeValues: Valor LIBRA respecto EURO=1.", "0.0 £",values.get("GBP"));

        values=control.getChangeValues(1,"1");

        assertEquals("ERROR CONTROLLER getChangeValues: Valor EURO respecto LIBRA=1.", "0.0 €",values.get("EU"));
        assertEquals("ERROR CONTROLLER getChangeValues: Valor DOLAR respecto LIBRA=1.", "0.0 $",values.get("USD"));
        assertEquals("ERROR CONTROLLER getChangeValues: Valor LIBRA respecto LIBRA=1.", "1.0 £",values.get("GBP"));

        values=control.getChangeValues(2,"1");

        assertEquals("ERROR CONTROLLER getChangeValues: Valor EURO respecto DOLAR=1.", "0.0 €",values.get("EU"));
        assertEquals("ERROR CONTROLLER getChangeValues: Valor DOLAR respecto DOLAR=1.", "1.0 $",values.get("USD"));
        assertEquals("ERROR CONTROLLER getChangeValues: Valor LIBRA respecto DOLAR=1.", "0.0 £",values.get("GBP"));
    }

    @Test
    public void setCoinValues() throws Exception {

        Controller control = new Controller();
        ModelSheet model = control.getSheet();

        control.setCoinValues(100,200);
        assertEquals("ERROR CONTROLLER setCoinValues: Valor LIBRA.", 100.0,model.getGBPvalue(),0.0);
        assertEquals("ERROR CONTROLLER setCoinValues: Valor DOLAR.", 200.0,model.getUSDvalue(),0.0);
    }

}