package com.example.saibahmed.myweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText city;
    TextView result;

    //http://api.openweathermap.org/data/2.5/weather?q=Paris&appid=1411acb4ad0d354b9980d86446c9dfab

    String baseUrl="http://api.openweathermap.org/data/2.5/weather?q=";
    String Api="&appid=1411acb4ad0d354b9980d86446c9dfab";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.button);
        city=(EditText)findViewById(R.id.city);
        result=(TextView)findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                String MyUrl=baseUrl + city.getText().toString() + Api;
                Log.i("Url","url"+ MyUrl);

                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, MyUrl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Log.i("JSON","JSON"+jsonObject);

                               try {

                                   String info=jsonObject.getString("weather");
                                   JSONArray ar=new JSONArray(info);

                                    for(int i=0;i<ar.length();i++){
                                        JSONObject parObj=ar.getJSONObject(i);
                                        String Mywetaher=parObj.getString("main");
                                        result.setText(Mywetaher);
                                    }

                               }


                               catch (JSONException e) {
                                   e.printStackTrace();
                               }

                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.i("Error","Something went wrong"+ volleyError);
                            }
                        }


                );

                MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);
            }

        });





    }

}
