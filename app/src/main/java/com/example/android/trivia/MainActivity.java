package com.example.android.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.android.trivia.controller.AppController;
import com.example.android.trivia.data.Repository;
import com.example.android.trivia.data.arrayasync;
import com.example.android.trivia.databinding.ActivityMainBinding;
import com.example.android.trivia.model.QUESTIONS;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int questionindex= 0;
    private List<QUESTIONS> questinn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

       questinn=  new Repository().getquestions(new arrayasync() {
            @Override
            public void processFinished(ArrayList<QUESTIONS> questionsArrayList) {
                binding.que.setText(questionsArrayList.get(questionindex).getAnswer().toString());
            }
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questinn.get(questionindex).getAnswertrue().toString()=="true"){
                    fadeAnimation();
                    Snackbar.make(binding.cardView,"Correct Anaswer!", BaseTransientBottomBar.LENGTH_SHORT).show();}
                else{
                    shakeAnimation();
                    Snackbar.make(binding.cardView,"Wrong Answer!!",BaseTransientBottomBar.LENGTH_SHORT).show();
                }



            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questinn.get(questionindex).getAnswertrue().toString()=="false"){
                    fadeAnimation();
                    Snackbar.make(binding.cardView,"Correct Answer!!",BaseTransientBottomBar.LENGTH_SHORT).show();
                }
                else
                {   shakeAnimation();
                    Snackbar.make(binding.cardView,"Wrong Input",BaseTransientBottomBar.LENGTH_SHORT).show();
                }

            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                questionindex=(questionindex+1) % questinn.size();
                display();



            }
        });



    }
    private void display(){
        String queee= questinn.get(questionindex).getAnswer();
        String index= "Question: " + questionindex + "/" + questinn.size();
        binding.que.setText(queee);
    binding.textView.setText(index); }
    private void shakeAnimation(){
        Animation shake= AnimationUtils.loadAnimation(MainActivity.this,R.anim.shake_animation);
        binding.cardView.startAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.que.setTextColor(Color.RED);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.que.setTextColor(Color.WHITE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }
        });
    }
    private void fadeAnimation(){
        Animation fade= AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_animation);
        binding.cardView.startAnimation(fade);
        fade.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.que.setTextColor(Color.GREEN);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.que.setTextColor(Color.WHITE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }



}