package com.example.macbookpro.retrofittesting;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.macbookpro.retrofittesting.retrofit.Transaction;
import com.example.macbookpro.retrofittesting.webservice.WebService;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Transaction.ITransactionListener{
    private Transaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transaction = new Transaction(this, this);

        Map<String, Object> req = new HashMap<>();
        //{"REQ_DATA":{"api_key":"oto_logout",
        // "token":"83cb676867dc781be565c6cdb1c4de85",
        // "driver_code":"drv000001"}}
        req.put("api_key","oto_logout");
        req.put("token","83cb676867dc781be565c6cdb1c4de85");
        req.put("driver_code","drv000001");

        try {
            transaction.requestTransaction(true, req, "logout", String.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void onTransactionResponse(JsonObject response) {

    }

    @Override
    public void onTransactionError(Object error) {

    }
}
