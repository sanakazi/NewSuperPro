package com.superapp.fragment.projectowner.milestone.task;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.superapp.R;
import com.superapp.activity.projectowner.ActivityTaskOverview;
import com.superapp.custom.CircularTextView;
import com.superapp.fragment.Model;

import java.util.ArrayList;

/**
 * Created by SanaKazi on 12/1/2017.
 */

public class AdapterMilestoneTask extends RecyclerView.Adapter<AdapterMilestoneTask.SingleItemRowHolder> {

    private ArrayList<Model> itemsList;
    private Context mContext;
    private static final String TAG= AdapterMilestoneTask.class.getSimpleName();
    //  ItemClickListener mListener;


    public interface ItemClickListener{
        void ItemClick(String fruitName);
    }

    public AdapterMilestoneTask(Context context, ArrayList<Model> itemsList ) {
        this.itemsList = itemsList;
        this.mContext = context;
        //   mListener=(ItemClickListener)context;

    }

    @Override
    public AdapterMilestoneTask.SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.milestone_task_item, null);
        AdapterMilestoneTask.SingleItemRowHolder mh = new AdapterMilestoneTask.SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(AdapterMilestoneTask.SingleItemRowHolder holder, final int i) {

     /*   holder.txt_id.setText(String.valueOf(itemsList.get(i).getId()));
        holder.txt_fruit_name.setText(String.valueOf(itemsList.get(i).getFruit()));
        holder.txt_color.setText(String.valueOf(itemsList.get(i).getColor()));
        holder.l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.ItemClick(itemsList.get(i).getFruit());
            }
        });*/

     holder.milestone_layout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(mContext, ActivityTaskOverview.class);
             mContext.startActivity(intent);
         }
     });

    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected RelativeLayout milestone_layout;
        protected CircularTextView circularTextView1;
        public SingleItemRowHolder(View view) {
            super(view);
            circularTextView1= view.findViewById(R.id.circularTextView1);
            circularTextView1.setStrokeWidth(1);
            circularTextView1.setStrokeColor("#ffffff");
            circularTextView1.setSolidColor("#9c9c9c");


            milestone_layout= view.findViewById(R.id.milestone_layout);

          /*  this.txt_id = (TextView) view.findViewById(R.id.txt_id);
            this.txt_fruit_name = (TextView) view.findViewById(R.id.txt_fruit_name);
            this.txt_color = (TextView) view.findViewById(R.id.txt_color);
            this.l1=view.findViewById(R.id.l1);
*/

        }

    }

}
