package com.example.stpl.recyclerview;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements MyDialogFragment.DialogListener{
    String[] data={"first","Second","third","fourth","fifth","six","seventh","eighth"};
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this,data);
        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager=new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();


    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("on pause","called");


    }



    @Override
    public void dialogIsVisible() {
        Log.d("message","passing");


        mLayoutManager=new LinearLayoutManager(MainActivity.this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return true;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    public void dialogIsNotVisible() {
        Log.d("not ","visible");

        mLayoutManager=new LinearLayoutManager(MainActivity.this){
            @Override
            public boolean canScrollVertically() {
                return true;
            }
            @Override
            public boolean canScrollHorizontally() {
                return true;
            }
        };
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

    }
}
