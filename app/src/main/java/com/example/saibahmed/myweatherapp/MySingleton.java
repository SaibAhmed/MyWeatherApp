package com.example.saibahmed.myweatherapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Saib Ahmed on 04-11-2017.
 */


public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private MySingleton(Context context)
    {
        mCtx=context;
        requestQueue=getRequestQueue();

    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized MySingleton getInstance(Context context)
    {
        if (mInstance==null)
        {
            mInstance=new MySingleton(context);
        }
        return mInstance;
    }

    public void addToRequestQueue(Request request)
    {
        requestQueue.add(request);
    }
}

