package com.superapp.fragment.projectowner.projectdetail.team;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superapp.R;
import com.superapp.custom.CircularTextView;
import com.superapp.fragment.Model;

import java.util.ArrayList;

/**
 * Created by SanaKazi on 12/1/2017.
 */

public class AdapterProjectDetailTeam extends RecyclerView.Adapter<AdapterProjectDetailTeam.SingleItemRowHolder> {

    private ArrayList<Model> itemsList;
    private Context mContext;
    private static final String TAG= AdapterProjectDetailTeam.class.getSimpleName();
    //  ItemClickListener mListener;


    public interface ItemClickListener{
        void ItemClick(String fruitName);
    }

    public AdapterProjectDetailTeam(Context context, ArrayList<Model> itemsList ) {
        this.itemsList = itemsList;
        this.mContext = context;
        //   mListener=(ItemClickListener)context;

    }

    @Override
    public AdapterProjectDetailTeam.SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dashboard_client_item, null);
        AdapterProjectDetailTeam.SingleItemRowHolder mh = new AdapterProjectDetailTeam.SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(AdapterProjectDetailTeam.SingleItemRowHolder holder, final int i) {

     /*   holder.txt_id.setText(String.valueOf(itemsList.get(i).getId()));
        holder.txt_fruit_name.setText(String.valueOf(itemsList.get(i).getFruit()));
        holder.txt_color.setText(String.valueOf(itemsList.get(i).getColor()));
        holder.l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.ItemClick(itemsList.get(i).getFruit());
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {


        protected CircularTextView circularTextView1,circularTextView2;
        public SingleItemRowHolder(View view) {
            super(view);

          /*  this.txt_id = (TextView) view.findViewById(R.id.txt_id);
            this.txt_fruit_name = (TextView) view.findViewById(R.id.txt_fruit_name);
            this.txt_color = (TextView) view.findViewById(R.id.txt_color);
            this.l1=view.findViewById(R.id.l1);
*/

        }

    }

}
