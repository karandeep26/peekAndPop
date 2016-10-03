package com.example.stpl.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by stpl on 9/30/2016.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private String[] data;
    private Context context;
    long startTime;
    TimerTask task;
    Timer timer;
    Bundle arguments;
    boolean run=false;
    FragmentManager manager;
    RecyclerView.LayoutManager layoutManager;
    MainActivity activity;
    RecyclerView recyclerView;
    MyDialogFragment fragment=new MyDialogFragment();

    public RecyclerViewAdapter(MainActivity activity,String[] data)
    {
        this.activity=activity;
        this.data=data;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rowview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        final TextView textView=holder.textView;
        textView.setText(data[position]);
         arguments=new Bundle();
        arguments.putSerializable("details",data);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                 startTime= System.currentTimeMillis();
                int action=event.getAction();
                Log.d("on ","touch");
                fragment=new MyDialogFragment();
                manager=activity.getSupportFragmentManager();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        fragment.setArguments(arguments);
                        Log.d("on ","down");
                        timer=new Timer();
                        task=new TimerTask() {
                            @Override
                            public void run() {
                                Log.d("run","is running");
                                if((System.currentTimeMillis()-startTime>(2000)))
                                {

                                    fragment.show(manager,"my dialog");
                                    if(manager!=null)
                                        Log.d("manager","not null");
                                    else
                                        Log.d("manager","null");
                                    task.cancel();
                                    timer.cancel();
                                    timer.purge();


                                }
                            }


                        };
                        timer.schedule(task,2000);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("action","moving");
                        break;


                    case MotionEvent.ACTION_UP:


                        Log.d("on ","action up");
                        if(timer!=null)
                        {
                            timer.purge();
                            timer.cancel();
                            timer=null;
                        }
                        fragment=(MyDialogFragment) manager.findFragmentByTag("my dialog");
                        if(fragment!=null)
                        {
                            Log.d("on ","action up");
                            fragment.dismiss();
                        }
                        else
                            Toast.makeText(activity,"null",Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.d("action","cancel");
                        break;
                    default:
                        Log.d("some other ","motion");





                }



                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.textView);
        }
    }
}