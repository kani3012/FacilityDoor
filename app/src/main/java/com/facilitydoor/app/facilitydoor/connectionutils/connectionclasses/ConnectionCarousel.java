package com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facilitydoor.app.facilitydoor.CitySelect;
import com.facilitydoor.app.facilitydoor.MainActivity;
import com.facilitydoor.app.facilitydoor.SplashScreen;

/**
 * Created by root on 13/6/16.
 */
public class ConnectionCarousel {
    String url;
    Context context;
    SplashScreen mainActivity;
    public ConnectionCarousel(String url,Context context,SplashScreen splashScreen) {
        this.url=url;
        this.context=context;
        this.mainActivity=splashScreen;
    }

    public void connect()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parsejsonconnect(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.v("Carousel", error.toString());
                        parsejsonconnect("error");
                    }
                });

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

        int socketTimeout = 20000;//20 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
    }

    private void parsejsonconnect(String response) {
        mainActivity.parsejsoncarousel(response);

    }
}
