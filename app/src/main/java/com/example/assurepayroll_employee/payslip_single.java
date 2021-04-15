package com.example.assurepayroll_employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class payslip_single extends AppCompatActivity {
    String URL="http://192.168.29.195:80/SDP_Payroll/empPayslip_single.php"; //maitri
    static final String TAG = "Register";
    TextView name;
    TextView totalSalary;
    TextView pay_date;
    TextView totalLeaves;
    TextView totalDeduction;
    TextView finalSalary;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payslip_single);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


      String Pid=  getIntent().getStringExtra("Pid");
        name=findViewById(R.id.emp_name);
        totalSalary=findViewById(R.id.totalSalary);
        pay_date=findViewById(R.id.pay_date);
        totalLeaves=findViewById(R.id.totalLeaves);
        totalDeduction=findViewById(R.id.totalDeduction);
        finalSalary=findViewById(R.id.finalSalary);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response.toString().trim(), Toast.LENGTH_SHORT).show();
                try{

                    JSONArray j = new JSONArray(response);
                    for(int i=0;i<j.length();i++)
                    {
                        try {

                            JSONObject jsonObject=j.getJSONObject(i);
                            Log.d(TAG, jsonObject.toString());
                            String emp_name=jsonObject.getString("emp_name").toString();
                            String salary=jsonObject.getString("totalSalary").toString();
                            String date=jsonObject.getString("pay_date").toString();
                            String Deduction=jsonObject.getString("totalDeduction").toString();
                            Integer leaves=Integer.parseInt(jsonObject.getString("full")) + Integer.parseInt(jsonObject.getString("half")) + Integer.parseInt(jsonObject.getString("sick"));

                            name.setText(emp_name);
                            totalSalary.setText(salary);
                            pay_date.setText(date);
                            totalLeaves.setText(leaves.toString());
                            totalDeduction.setText(Deduction);
                            finalSalary.setText(salary);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data=new HashMap<String, String>();
                data.put("Pid",Pid);
                return data;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}