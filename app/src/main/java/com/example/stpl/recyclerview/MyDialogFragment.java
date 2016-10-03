package com.example.stpl.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by stpl on 9/30/2016.
 */

public class MyDialogFragment extends DialogFragment  {

    private Context context;
    private String[] details;
    ListAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;

    public interface DialogListener
    {
         void dialogIsVisible();
         void dialogIsNotVisible();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    ((DialogListener)getActivity()).dialogIsVisible();
        this.context=context;





    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.hello_peek,null,false);
        final ListView listView= (ListView) view.findViewById(R.id.list);


        Bundle bundle=getArguments();
        details=(String[])bundle.get("details");
        adapter=new ListAdapter(getContext(),details);
        listView.setAdapter(adapter);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getContext(),"test",Toast.LENGTH_SHORT).show();
                return false;
            }
        });





        return view;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        ((DialogListener)getActivity()).dialogIsNotVisible();


    }
}