package com.example.todoapp;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.ViewHolder> {

    public List<MissionModel> missionModelList;
    Context context;

    public MissionAdapter(List<MissionModel> list, Context context) {
        missionModelList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mission_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getCb_missionCompleted().setChecked(false);
        holder.getTv_missionDesc().setText(missionModelList.get(position).getMissionDescription());

        holder.getFab_deleteMission().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("page", missionModelList.get(0).getMissionDescription());
                missionModelList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, missionModelList.size());
            }
        });

        holder.getCb_missionCompleted().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    holder.getTv_missionDesc().setPaintFlags(holder.getTv_missionDesc().getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.getTv_missionDesc().setTextColor(holder.getTv_missionDesc().getResources().getColor(R.color.add_button_background_gray));
                } else {
                    holder.getTv_missionDesc().setPaintFlags(Paint.ANTI_ALIAS_FLAG);
                    holder.getTv_missionDesc().setTextColor(holder.getTv_missionDesc().getResources().getColor(R.color.white));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return missionModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final CheckBox cb_missionCompleted;
        final TextView tv_missionDesc;
        final FloatingActionButton fab_deleteMission;
        MissionAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cb_missionCompleted = itemView.findViewById(R.id.cb_completed);
            tv_missionDesc = itemView.findViewById(R.id.tv_missionDesc);
            fab_deleteMission = itemView.findViewById(R.id.fab_deleteMission);



        }

        public ViewHolder linkAdapter(MissionAdapter missionAdapter) {
            this.adapter = missionAdapter;
            return this;
        }

        public CheckBox getCb_missionCompleted() {
            return cb_missionCompleted;
        }

        public FloatingActionButton getFab_deleteMission() {
            return fab_deleteMission;
        }

        public TextView getTv_missionDesc() {
            return tv_missionDesc;
        }
    }
}
