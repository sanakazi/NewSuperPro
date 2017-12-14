package com.superapp.fragment.projectowner.dashboard.projects;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.superapp.R;
import com.superapp.activity.contextualmenus.clientprofile.ActivityClientProfile;
import com.superapp.activity.contextualmenus.communication.ActivityCommunication;
import com.superapp.activity.contextualmenus.notepad.ActivityNotepadLandingScreen;
import com.superapp.activity.contextualmenus.task.ActivityCreateTask;
import com.superapp.activity.contextualmenus.transaction.ActivityClientTransaction;
import com.superapp.activity.projectowner.ActivityCreateProject;
import com.superapp.activity.projectowner.ActivityProjectDetail;
import com.superapp.activity.contextualmenus.appointment.ActivityAppointment;
import com.superapp.custom.CircularTextView;
import com.superapp.fragment.Model;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by SanaKazi on 12/1/2017.
 */

public class AdapterDashboardProject extends RecyclerView.Adapter<AdapterDashboardProject.SingleItemRowHolder> {

    private ArrayList<Model> itemsList;
    private Context mContext;
    private static final String TAG = AdapterDashboardProject.class.getSimpleName();
    //  ItemClickListener mListener;


    public interface ItemClickListener {
        void ItemClick(String fruitName);
    }

    public AdapterDashboardProject(Context context, ArrayList<Model> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
        //   mListener=(ItemClickListener)context;

    }

    @Override
    public AdapterDashboardProject.SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dashboard_project_item, null);
        AdapterDashboardProject.SingleItemRowHolder mh = new AdapterDashboardProject.SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(AdapterDashboardProject.SingleItemRowHolder holder, final int i) {

     /*   holder.txt_id.setText(String.valueOf(itemsList.get(i).getId()));
        holder.txt_fruit_name.setText(String.valueOf(itemsList.get(i).getFruit()));
        holder.txt_color.setText(String.valueOf(itemsList.get(i).getColor()));
        holder.l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.ItemClick(itemsList.get(i).getFruit());
            }
        });*/

        holder.project_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityProjectDetail.class);
                mContext.startActivity(intent);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(mContext, holder.edit);
                //inflating menu from xml resource
                popup.inflate(R.menu.dashboard_project_owner_menu);

                // region Force icons to show
                Object menuHelper;
                Class[] argTypes;
                try {
                    Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
                    fMenuHelper.setAccessible(true);
                    menuHelper = fMenuHelper.get(popup);
                    argTypes = new Class[]{boolean.class};
                    menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
                } catch (Exception e) {
                    // Possible exceptions are NoSuchMethodError and NoSuchFieldError
                    //
                    // In either case, an exception indicates something is wrong with the reflection code, or the
                    // structure of the PopupMenu class or its dependencies has changed.
                    //
                    // These exceptions should never happen since we're shipping the AppCompat library in our own apk,
                    // but in the case that they do, we simply can't force icons to display, so log the error and
                    // show the menu normally.

                    Log.w(TAG, "error forcing menu icons to show", e);
                    return;
                }
                //endregion
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()) {
                            case R.id.menu_add_task:
                                intent  = new Intent(mContext, ActivityCreateTask.class);
                                mContext.startActivity(intent);

                                break;
                            case R.id.menu_edit_project:
                                intent  = new Intent(mContext, ActivityCreateProject.class);
                                mContext.startActivity(intent);

                                break;
                            case R.id.menu_communication:
                                intent  = new Intent(mContext, ActivityCommunication.class);
                                mContext.startActivity(intent);
                                break;

                            case R.id.menu_appointment:
                                intent  = new Intent(mContext, ActivityAppointment.class);
                                mContext.startActivity(intent);

                                break;
                            case R.id.menu_transaction:
                                intent  = new Intent(mContext, ActivityClientTransaction.class);
                                mContext.startActivity(intent);

                                break;
                            case R.id.menu_client_profile:
                                intent  = new Intent(mContext, ActivityClientProfile.class);
                                mContext.startActivity(intent);

                                break;

                            case R.id.menu_notepad:
                                intent  = new Intent(mContext, ActivityNotepadLandingScreen.class);
                                mContext.startActivity(intent);
                                break;
                            case R.id.menu_reminder:

                                break;
                            case R.id.menu_supervisor:

                                break;

                            case R.id.menu_delete:
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }


    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected RelativeLayout project_layout;
        protected CircularTextView circularTextView1, circularTextView2;
        ImageView edit;

        public SingleItemRowHolder(View view) {
            super(view);
            circularTextView1 = view.findViewById(R.id.circularTextView1);
            circularTextView1.setStrokeWidth(1);
            circularTextView1.setStrokeColor("#ffffff");
            circularTextView1.setSolidColor("#6d53ce");


            circularTextView2 = view.findViewById(R.id.circularTextView2);
            circularTextView2.setStrokeWidth(1);
            circularTextView2.setStrokeColor("#ffffff");
            circularTextView2.setSolidColor("#23a915");

            project_layout = (RelativeLayout) view.findViewById(R.id.project_layout);
            edit = view.findViewById(R.id.edit);

          /*  this.txt_id = (TextView) view.findViewById(R.id.txt_id);
            this.txt_fruit_name = (TextView) view.findViewById(R.id.txt_fruit_name);
            this.txt_color = (TextView) view.findViewById(R.id.txt_color);
            this.l1=view.findViewById(R.id.l1);
*/

        }

    }

}
