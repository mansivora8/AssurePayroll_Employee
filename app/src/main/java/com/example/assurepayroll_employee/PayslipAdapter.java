package com.example.assurepayroll_employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PayslipAdapter extends RecyclerView.Adapter<PayslipAdapter.payslipviewholder> {
   /*private String[] data;
    public PayslipAdapter(String[] data){
        this.data=data;
    }*/
   List<PayslipData> payslipDataList;
    private Context context;
    LayoutInflater inflater;

    public PayslipAdapter(Context context, List<PayslipData> payslipDataList) {
        this.inflater=LayoutInflater.from(context);
        // this.context=context;
        this.payslipDataList=payslipDataList;
    }


    @NonNull
    @Override
    public payslipviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.payslip_list_item_layout,parent,false);
        return new payslipviewholder(view);*/
        View view=inflater.inflate(R.layout.payslip_list_item_layout,parent,false);
        return new payslipviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull payslipviewholder holder, int position) {
        /*String name=data[position];
        holder.name.setText(name);
        String date=data[position];
        holder.date.setText(date);
        String amount=data[position];
        holder.amount.setText(amount);*/

        String date = payslipDataList.get(position).getPay_date();
        Integer salary = payslipDataList.get(position).getTotalSalary();
        holder.date.setText(date);
        holder.salary.setText(salary.toString());
    }

    @Override
    public int getItemCount() {
        return payslipDataList.size();
    }

    public class payslipviewholder extends RecyclerView.ViewHolder{
        TextView date,salary;
        public payslipviewholder(@NonNull View itemView) {
            super(itemView);
            date= itemView.findViewById(R.id.date);
            salary= itemView.findViewById(R.id.salary);
        }
    }

}
