package com.example.assurepayroll_employee;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.leaveviewholder> {
  //  private String[] data;
  static final String TAG = "Register";
    List<LeavesData> leavesData;
    private Context context;
    public LeaveAdapter(Context context,List<LeavesData> leavesData) {
        this.context=context;
        this.leavesData=leavesData;
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
        //extract date
        String date = leavesData.get(position).getDate();
        String month="";
        String day="";
        String year="";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            month = checkDigit(cal.get(Calendar.MONTH)+1);
            day = checkDigit(cal.get(Calendar.DATE));
            year = checkDigit(cal.get(Calendar.YEAR));

        } catch (Exception e) {
            e.printStackTrace();
        }




        holder.month.setText(month);

        holder.day.setText(day);

        holder.year.setText(year);

        //type
        String leave_type=leavesData.get(position).getLeave_type_id();
        String leave_name="";
        switch (leave_type) {
            case "1":
                leave_name = "Full";
                break;
            case "2":
                leave_name = "Half";
                break;
            case "3":
                leave_name = "Sick";
                break;
        }
        holder.type.setText(leave_name);

        holder.reason.setText(leavesData.get(position).getReson());
        String status=leavesData.get(position).getStatus();
        holder.status.setText(status);
        if(status.equals("Grant") || status.equals("Approved"))
        {
            holder.status.setBackgroundColor(Color.GREEN);
        }
        else if(status.equals("Deny") || status.equals("Denied"))
        {
            holder.status.setBackgroundColor(Color.RED);
        }


    }

    @Override
    public int getItemCount() {
        return leavesData.size();
    }

    public class leaveviewholder extends RecyclerView.ViewHolder{
        TextView month,day,year,type,status,reason;
        public leaveviewholder(@NonNull View itemView) {
            super(itemView);
            month=(TextView) itemView.findViewById(R.id.month);
            day=(TextView) itemView.findViewById(R.id.day);
            year=(TextView) itemView.findViewById(R.id.year);
            type=(TextView) itemView.findViewById(R.id.type);
            reason=(TextView) itemView.findViewById(R.id.reason);
            status=(TextView) itemView.findViewById(R.id.status);
        }
    }
    public String checkDigit (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
}
