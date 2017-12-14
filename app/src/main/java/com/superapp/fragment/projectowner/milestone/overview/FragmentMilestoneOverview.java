package com.superapp.fragment.projectowner.milestone.overview;

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

public class FragmentMilestoneOverview extends BaseFragment {
    RecyclerView recyler_view_overview;
    ArrayList<Model> itemsList;
    AdapterMilestoneOverview adapterMilestoneOverview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_milestone_overview, container, false);
        init(v);
        createData();


        return v;
    }


    private void init(View v) {

        recyler_view_overview = v.findViewById(R.id.recyler_view_overview);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyler_view_overview.setLayoutManager(llm);

    }

    private void createData() {
        itemsList = new ArrayList<>();
        Model a = new Model(1, "Chiku", "brown");
        Model b = new Model(2, "Mango", "yellow");
        Model c = new Model(3, "Watermelon", "green");
        itemsList.add(a);
        itemsList.add(b);
        itemsList.add(c);

        setAdapter();


    }

    private void setAdapter() {
        adapterMilestoneOverview = new AdapterMilestoneOverview(getActivity(), itemsList);
        recyler_view_overview.setAdapter(adapterMilestoneOverview);

    }

}
