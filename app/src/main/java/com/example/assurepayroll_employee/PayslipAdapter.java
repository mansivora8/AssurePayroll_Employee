package com.example.assurepayroll_employee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PayslipAdapter extends RecyclerView.Adapter<PayslipAdapter.payslipviewholder> {
    private String[] data;
    public PayslipAdapter(String[] data){
        this.data=data;
    }
    @NonNull
    @Override
    public payslipviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
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
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class payslipviewholder extends RecyclerView.ViewHolder{
        TextView name,date,time,amount;
        public payslipviewholder(@NonNull View itemView) {
            super(itemView);
            /*name=(TextView) itemView.findViewById(R.id.name);
            date=(TextView) itemView.findViewById(R.id.date);
            amount=(TextView) itemView.findViewById(R.id.amount);*/
        }
    }

}
