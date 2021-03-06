package com.example.assurepayroll_employee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.leaveviewholder> {
    private String[] data;
    public LeaveAdapter(String[] data){
        this.data=data;
    }
    @NonNull
    @Override
    public leaveviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_item_layout,parent,false);
        return new leaveviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull leaveviewholder holder, int position) {
        String month=data[position];
        holder.month.setText(month);
        String day=data[position];
        holder.day.setText(day);
        String type=data[position];
        holder.type.setText(type);
        String status=data[position];
        holder.status.setText(status);
        String reason=data[position];
        holder.reason.setText(reason);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class leaveviewholder extends RecyclerView.ViewHolder{
        TextView month,day,type,status,reason;
        public leaveviewholder(@NonNull View itemView) {
            super(itemView);
            month=(TextView) itemView.findViewById(R.id.month);
            day=(TextView) itemView.findViewById(R.id.day);
            type=(TextView) itemView.findViewById(R.id.type);
            reason=(TextView) itemView.findViewById(R.id.reason);
            status=(TextView) itemView.findViewById(R.id.status);
        }
    }

}
