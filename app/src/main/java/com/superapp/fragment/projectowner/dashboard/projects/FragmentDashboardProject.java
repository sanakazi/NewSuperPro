package com.superapp.fragment.projectowner.dashboard.projects;

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

public class FragmentDashboardProject extends BaseFragment {
    RecyclerView projects_recycler_view;
    ArrayList<Model> itemsList;
    AdapterDashboardProject adapterDashboardProject;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard_project, container, false);
        projects_recycler_view = v.findViewById(R.id.projects_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        projects_recycler_view.setLayoutManager(llm);
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
        adapterDashboardProject = new AdapterDashboardProject(getActivity(),itemsList);
        projects_recycler_view.setAdapter(adapterDashboardProject);

    }

}
