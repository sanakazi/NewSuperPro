package com.superapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.superapp.R;

/**
 * Created by SanaKazi on 11/30/2017.
 */

public class SampleFragment extends Fragment {
    TextView txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.nav_item_row, container, false);

        //  txt.setText();

        return v;
    }
}
