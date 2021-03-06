package com.superapp.fragment.projectowner.milestone.task;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superapp.R;
import com.superapp.fragment.Model;
import com.superapp.fragment.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by SanaKazi on 12/1/2017.
 */

public class FragmentMilestoneTask extends BaseFragment {
    RecyclerView recycler_view;
    ArrayList<Model> itemsList;
    AdapterMilestoneTask adapterMilestoneTask;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_milestone_task, container, false);
        recycler_view = v.findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recycler_view.setLayoutManager(llm);
        createData();
        return v;
    }


    private void createData()
    {
        itemsList = new ArrayList<>();
        Model a = new Model(1,"Chiku", "brown");
        Model b = new Model(2,"Mango", "yellow");
        Model c = new Model(3,"Watermelon", "green");
        itemsList.add(a);
        itemsList.add(b);
        itemsList.add(c);

        setAdapter();



    }

    private  void setAdapter()
    {
        adapterMilestoneTask = new AdapterMilestoneTask(getActivity(),itemsList);
        recycler_view.setAdapter(adapterMilestoneTask);

    }


}

