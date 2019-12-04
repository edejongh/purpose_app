package net.tilt.purpose.data;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.tilt.purpose.R;
import net.tilt.purpose.controller.AppController;
import net.tilt.purpose.model.Quote;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.*;

import android.util.Log;

public class QuoteData {


    //Class to fetch JSON data (quotes and author)
     ArrayList<Quote>  quoteArrayList = new ArrayList<>();





     public void getQuotes(final QuoteListAsyncResponse callBack) {



         String url = "https://raw.githubusercontent.com/JamesFT/Database-Quotes-JSON/master/quotes.json";
         //String url = "https://raw.githubusercontent.com/edejongh/purpose/dev/quotes.json?token=AAU5ISOFQGRQLMBXAHRWMCC54ULKS";

          JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, new Response.Listener<JSONArray>() {


             @Override
                     public void onResponse(JSONArray response) {
                         Log.d("!@#!@#!@#", "We're in QuoteData.getQuotes() onResponse");
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject quoteObject = response.getJSONObject(i);

                                Quote quote = new Quote();
                                quote.setQuoteText(quoteObject.getString("quoteText"));
                                quote.setQuoteAuthor(quoteObject.getString("quoteAuthor"));



                                quoteArrayList.add(quote);
                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                        } if( callBack != null) {
                            callBack.dataDownloadFinished(quoteArrayList);

                          }
             }

         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError volleyError) {

             }


         });

          AppController.getInstance().addToRequestQueue(jsonArrayRequest);
     }

}
