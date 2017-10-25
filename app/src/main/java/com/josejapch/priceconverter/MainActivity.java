package com.josejapch.priceconverter;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Calculadora de descuentos en precios y cambio de moneda (actualmente cambio entre €, £, $).
 * @author josejapch
 * @version 1.0
 *
 * */
public class MainActivity extends AppCompatActivity {

    static final int MY_PERMISSIONS_REQUEST_INTERNET = 1;

    Spinner lista_iva;
    String[] iva ={"IVA General (21%)", "IVA Reducido (10%)", "IVA superreducido (4%)"};

    Spinner lista_monedas;
    String[] monedas ={"Euro", "Libra", "Dolar"};

    EditText campo_importe;
    TextView texto_noiva;

    EditText campo_descuento;
    TextView texto_descuento;

    EditText campo_origen;
    TextView texto_dolar;
    TextView texto_euro;
    TextView texto_libra;

    ProgressBar progreso;

    View layout_Cambio;

    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campo_importe = (EditText) findViewById(R.id.importe);
        texto_noiva = (TextView) findViewById(R.id.p_noiva);
        lista_iva = (Spinner) findViewById(R.id.iva_list);
        campo_descuento = (EditText) findViewById(R.id.descuento);
        texto_descuento = (TextView) findViewById(R.id.p_descuento);
        progreso = (ProgressBar) findViewById(R.id.progressBar);
        layout_Cambio = findViewById(R.id.change_layout);
        lista_monedas = (Spinner) findViewById(R.id.change_list);
        campo_origen = (EditText) findViewById(R.id.origen);
        texto_dolar = (TextView) findViewById(R.id.dolar);
        texto_euro = (TextView) findViewById(R.id.euro);
        texto_libra = (TextView) findViewById(R.id.libra);

        layout_Cambio.setVisibility(View.INVISIBLE);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.INTERNET)) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Permiso requerido")
                        .setMessage("Se requiere permiso de acceso a internet para el cambio de moneda.")
                        .setPositiveButton("Permitir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.INTERNET},
                                        MY_PERMISSIONS_REQUEST_INTERNET);


                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                finish();
                            }
                        })
                        .show();
            } else {

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.INTERNET},
                        MY_PERMISSIONS_REQUEST_INTERNET);
            }
        }

        controller = new Controller();

        URLConnectionJson urlCon = new URLConnectionJson();
        urlCon.execute();

        createInterfazSpinners();

        setFieldEvents();

        show_noIVA();
        show_descuento();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_INTERNET: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    URLConnectionJson urlCon = new URLConnectionJson();
                    urlCon.execute();

                }
            }
        }
    }

    /**
     * Método para mostrar en la interfaz el mensaje con el cálculo del importe sin IVA.
     */
    protected void show_noIVA(){

        String sImporte = campo_importe.getText().toString();
        int option = lista_iva.getFirstVisiblePosition();

        int resId = getResources().getIdentifier("text_noiva", "string", getBaseContext().getPackageName());
        String noiva = getString(resId);
        texto_noiva.setText(noiva+controller.getTextNoIVA(sImporte,option)+" €");
    }

    /**
     * Método para lanzar el mensaje de alerta si se ha introducido un porcentaje de descuento menor
     * que 0 o mayor que 100.
     */
    protected void error_descuento (){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Descuento no válido")
                .setMessage("El descuento debe ser mayor o igual que 0%, o menor o igual que 100%")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String desc = campo_descuento.getText().toString();
                        campo_descuento.setText(desc.substring(0,desc.length()-1));
                        campo_descuento.setSelection(desc.length()-1);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Método para mostrar el mensaje con el cálculo del descuento sobre el importe introducido.
     */
    protected void show_descuento(){

        String textDiscount = controller.getTextDiscount(campo_descuento.getText().toString());

        if (textDiscount.equals("ERROR")){

            error_descuento();
        }else if (!textDiscount.isEmpty()){

            texto_descuento.setText(textDiscount);
        }
    }

    /**
     * Método para crear los Spinners de la interfaz con las distintas opciones elegibles.
     */
    protected void createInterfazSpinners(){

        ArrayAdapter<String> iva_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,iva);
        lista_iva.setAdapter(iva_adapter);

        lista_iva.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                show_noIVA();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                show_noIVA();
            }
        });

        ArrayAdapter<String> cambio_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,monedas);
        lista_monedas.setAdapter(cambio_adapter);

        lista_monedas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cambiar_moneda();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cambiar_moneda();
            }
        });
    }

    /**
     * Método que establece las acciones que se realizarán con la interacción con la interfaz.
     */
    protected void setFieldEvents(){

        //Eventos de cambios en campo importe
        campo_importe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                show_noIVA();
                show_descuento();
            }
        });

        //Eventos de cambios en campo descuento
        campo_descuento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                show_descuento();
            }
        });

        //Eventos de cambios en campo origen
        campo_origen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                cambiar_moneda();
            }
        });
    }

    /**
     * Método para mostrar el mensaje con el cálculo del cambio de moneda.
     */
    protected void cambiar_moneda(){


        int moneda = lista_monedas.getFirstVisiblePosition();
        String amount = campo_origen.getText().toString();

        if (amount.isEmpty()){
            amount="0";
        }

        HashMap<String,String> coin_value = controller.getChangeValues(moneda, amount);

        texto_dolar.setText(coin_value.get("USD"));
        texto_euro.setText(coin_value.get("EU"));
        texto_libra.setText(coin_value.get("GBP"));
    }

    /**
     * Clase para realizar la conexión (asíncrona) a la API encargada de suministrar el valor
     * actualizado de la moneda.
     */
    private class URLConnectionJson extends AsyncTask<String,Void,String>{

        HttpURLConnection urlConnection;

        @Override
        protected void onPreExecute(){
        }

        @Override
        protected String doInBackground(String... params) {
            StringBuilder res = new StringBuilder();

            try {
                URL url = new URL("http://api.fixer.io/latest?symbols=USD,GBP");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream input = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String linea;
                while ((linea=reader.readLine())!=null){
                    res.append(linea);
                }

            } catch (MalformedURLException e) { //URL
                e.printStackTrace();
            } catch (IOException e) { //Connection
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }

            return res.toString();
        }

        @Override
        protected void onPostExecute(String result){

            try {
                JSONObject json = new JSONObject(result);
                JSONObject rates  = json.getJSONObject("rates");
                double gbp = rates.getDouble("GBP");
                double usd = rates.getDouble("USD");

                controller.setCoinValues(gbp,usd);

                layout_Cambio.setVisibility(View.VISIBLE);
                progreso.setVisibility(View.INVISIBLE);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}
