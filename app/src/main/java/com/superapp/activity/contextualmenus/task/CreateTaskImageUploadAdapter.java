package com.superapp.activity.contextualmenus.task;

/**
 * Created by pratap.kesaboyina on 24-12-2014.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.superapp.R;
import com.superapp.fragment.Model;

import java.util.ArrayList;

public class CreateTaskImageUploadAdapter extends RecyclerView.Adapter<CreateTaskImageUploadAdapter.SingleItemRowHolder> {

    private String itemsList;

    private Context mContext;
    int category_Id;
    private ImageLoader mImageLoader;
    private String categoryName;

    public CreateTaskImageUploadAdapter(Context context, String itemsList) {

        this.mContext = context;
        this.itemsList = itemsList;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.createtask_uploadimage_item, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, final int position) {


    }

    @Override
    public int getItemCount() {

        //return (null != itemsList ? itemsList.size() : 0);
        return 5;
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;
        protected ImageView itemImage;
        protected CardView cardView;
        protected LinearLayout itemImageOverlay_opacity;
        protected ImageView itemImageOverlay;

        public SingleItemRowHolder(View view) {
            super(view);


        }

    }

}