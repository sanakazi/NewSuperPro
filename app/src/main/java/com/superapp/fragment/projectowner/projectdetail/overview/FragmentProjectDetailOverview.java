package com.superapp.fragment.projectowner.projectdetail.overview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superapp.R;
import com.superapp.custom.CircularTextView;
import com.superapp.fragment.Model;
import com.superapp.fragment.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by SanaKazi on 12/1/2017.
 */

public class FragmentProjectDetailOverview extends BaseFragment {
    RecyclerView recyler_view_overview;
    ArrayList<Model> itemsList;
    AdapterProjectDetailOverview adapterProjectDetailOverview;
    CircularTextView circularTextView1, circularTextView2, circularTextView3, circularTextView4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_projectdetail_overview, container, false);
        init(v);
        createData();


        return v;
    }


    private void init(View v) {

        recyler_view_overview = v.findViewById(R.id.recyler_view_overview);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyler_view_overview.setLayoutManager(llm);

        circularTextView1 = v.findViewById(R.id.circularTextView1);
        circularTextView2 = v.findViewById(R.id.circularTextView2);
        circularTextView3 = v.findViewById(R.id.circularTextView3);
        circularTextView4 = v.findViewById(R.id.circularTextView4);

        circularTextView1.setStrokeWidth(1);
        circularTextView1.setStrokeColor("#ffffff");
        circularTextView1.setSolidColor("#3fb5d2");

        circularTextView2.setStrokeWidth(1);
        circularTextView2.setStrokeColor("#ffffff");
        circularTextView2.setSolidColor("#6d53ce");

        circularTextView3.setStrokeWidth(1);
        circularTextView3.setStrokeColor("#ffffff");
        circularTextView3.setSolidColor("#046cbd");

        circularTextView4.setStrokeWidth(1);
        circularTextView4.setStrokeColor("#ffffff");
        circularTextView4.setSolidColor("#23a915");

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
        adapterProjectDetailOverview = new AdapterProjectDetailOverview(getActivity(), itemsList);
        recyler_view_overview.setAdapter(adapterProjectDetailOverview);

    }

}
