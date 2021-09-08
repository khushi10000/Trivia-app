package com.example.android.trivia.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.android.trivia.controller.AppController;
import com.example.android.trivia.model.QUESTIONS;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    String url="https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
    ArrayList<QUESTIONS> questionarraylist= new ArrayList<>();

    public List<QUESTIONS> getquestions(final arrayasync callback){
        JsonArrayRequest jsonarrayrequest= new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {

                    try {
                        QUESTIONS question = new QUESTIONS(response.getJSONArray(i).get(0).toString(), response.getJSONArray(i).getBoolean(1));
                        questionarraylist.add(question);
                        //Log.d("main",question.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(null!=callback) callback.processFinished(questionarraylist);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(jsonarrayrequest);
        return questionarraylist;
    }

}
