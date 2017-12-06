package com.superapp.fragment.projectowner.projectdetail.overview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superapp.R;
import com.superapp.custom.CircularTextView;
import com.superapp.fragment.Model;

import java.util.ArrayList;

/**
 * Created by SanaKazi on 12/1/2017.
 */

public class AdapterProjectDetailOverview extends RecyclerView.Adapter<AdapterProjectDetailOverview.SingleItemRowHolder> {

    private ArrayList<Model> itemsList;
    private Context mContext;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private static final String TAG = AdapterProjectDetailOverview.class.getSimpleName();
    //  ItemClickListener mListener;


    public interface ItemClickListener {
        void ItemClick(String fruitName);
    }

    public AdapterProjectDetailOverview(Context context, ArrayList<Model> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
        //   mListener=(ItemClickListener)context;

    }

    @Override
    public AdapterProjectDetailOverview.SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_ITEM) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.projectdetail_overview_item, null);
        AdapterProjectDetailOverview.SingleItemRowHolder vhItem = new AdapterProjectDetailOverview.SingleItemRowHolder(v,viewType);
        return vhItem;}
        else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.projectdetail_overview_header_item, null);

            SingleItemRowHolder vhHeader = new SingleItemRowHolder(v, viewType);
            return vhHeader;


        }
        return null;

    }

    @Override
    public void onBindViewHolder(AdapterProjectDetailOverview.SingleItemRowHolder holder, final int i) {

     /*   holder.txt_id.setText(String.valueOf(itemsList.get(i).getId()));
        holder.txt_fruit_name.setText(String.valueOf(itemsList.get(i).getFruit()));
        holder.txt_color.setText(String.valueOf(itemsList.get(i).getColor()));
        holder.l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.ItemClick(itemsList.get(i).getFruit());
            }
        });*/
        if (holder.Holderid == 1) {

            if(i==1)
            {
                holder.label.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.label.setVisibility(View.GONE);
            }

        }
        else
        {

        }

    }

    @Override
    public int getItemCount() {
        return itemsList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
            if (isPositionHeader(position))
                return TYPE_HEADER;
             else return TYPE_ITEM;

    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {
        int Holderid;
        protected RelativeLayout project_layout;
        protected TextView label;
        CircularTextView circularTextView1, circularTextView2, circularTextView3, circularTextView4;


        public SingleItemRowHolder(View itemView, int ViewType){
            super(itemView);

            if (ViewType == TYPE_ITEM) {
                circularTextView1 = itemView.findViewById(R.id.circularTextView1);
                circularTextView1.setStrokeWidth(1);
                circularTextView1.setStrokeColor("#ffffff");
                circularTextView1.setSolidColor("#9c9c9c");

                circularTextView2 = itemView.findViewById(R.id.circularTextView2);
                circularTextView2.setStrokeWidth(1);
                circularTextView2.setStrokeColor("#ffffff");
                circularTextView2.setSolidColor("#dddddd");

                circularTextView3 = itemView.findViewById(R.id.circularTextView3);
                circularTextView3.setStrokeWidth(1);
                circularTextView3.setStrokeColor("#ffffff");
                circularTextView3.setSolidColor("#2cac1e");

                label = itemView.findViewById(R.id.label);

                 Holderid=1;

            }
            else
            {
                circularTextView1 = itemView.findViewById(R.id.circularTextView1);
                circularTextView2 = itemView.findViewById(R.id.circularTextView2);
                circularTextView3 = itemView.findViewById(R.id.circularTextView3);
                circularTextView4 = itemView.findViewById(R.id.circularTextView4);

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



          /*  this.txt_id = (TextView) view.findViewById(R.id.txt_id);
            this.txt_fruit_name = (TextView) view.findViewById(R.id.txt_fruit_name);
            this.txt_color = (TextView) view.findViewById(R.id.txt_color);
            this.l1=view.findViewById(R.id.l1);
*/

        }

    }

}
