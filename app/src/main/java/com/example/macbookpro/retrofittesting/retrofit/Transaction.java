package com.example.macbookpro.retrofittesting.retrofit;

import android.content.Context;
import android.util.Log;

import com.example.macbookpro.retrofittesting.model.RESPONSE_OBJECT;
import com.example.macbookpro.retrofittesting.network.ServiceGenerator;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Transaction {
    private ApiInterface apiInterface;
    private Context context;
    private ITransactionListener transactionListener;

    public Transaction(Context context, ITransactionListener transactionListener){
        this.context = context;
        this.transactionListener = transactionListener;
        this.apiInterface = ServiceGenerator.createService().create(ApiInterface.class);

    }

    public void requestTransaction(boolean methodType, Map<String, Object> hashMap, String methodName, Class paraMeterType) throws NoSuchMethodException, UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        Method method = ApiInterface.class.getDeclaredMethod(methodName, paraMeterType);

        Map<String, Object> objRequest = new HashMap<>();
        objRequest.put("REQ_DATA", hashMap);
        String jsonObject = new Gson().toJson(objRequest).toString();
        if(methodType) {

            Call<RESPONSE_OBJECT> call = (Call<RESPONSE_OBJECT>) method.invoke(apiInterface, URLEncoder.encode(jsonObject, "UTF-8"));
            call.enqueue(new Callback<RESPONSE_OBJECT>() {
                @Override
                public void onResponse(Call<RESPONSE_OBJECT> call, Response<RESPONSE_OBJECT> response) {
                    Log.d("msg msg ", "onResponse");
                    responseTransaction(response);
                }

                @Override
                public void onFailure(Call<RESPONSE_OBJECT> call, Throwable t) {
                    transactionListener.onTransactionError(t);
                }
            });
        }

    }

    private void responseTransaction(Response<RESPONSE_OBJECT> response){
        Log.d("msg msg ", response.body().toString());
    }
    public interface ITransactionListener{
        void onTransactionResponse(JsonObject response);
        void onTransactionError( Object error);
    }


}
