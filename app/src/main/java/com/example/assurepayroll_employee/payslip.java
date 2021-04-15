package com.example.assurepayroll_employee;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.DividerItemDecoration;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link payslip#newInstance} factory method to
 * create an instance of this fragment.
 */
public class payslip extends Fragment {
    private final String URL="http://192.168.29.195:80/SDP_Payroll/empDisplay_payslip.php"; //maitri's URL
    //private final String URL="http://192.168.43.231:80/SDP_Payroll/employee_list.php";
    static final String TAG = "Register";
    List<PayslipData> payslipDataList;
    RecyclerView recyclerView;
    PayslipAdapter payslipAdapter;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public payslip() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment payslip.
     */
    // TODO: Rename and change types and number of parameters
    public static payslip newInstance(String param1, String param2) {
        payslip fragment = new payslip();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      /*  View view= inflater.inflate(R.layout.fragment_payslip, container, false);
        RecyclerView rv=(RecyclerView) view.findViewById(R.id.payslip_list);
        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rv ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i=new Intent(getContext(), payslip_single.class);
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        String[] lang={"Admin","Admin"};
        rv.setAdapter(new PayslipAdapter(lang));
        return view;*/

        view= inflater.inflate(R.layout.fragment_payslip, container, false);
        recyclerView=view.findViewById(R.id.payslip_list);
        payslipDataList=new ArrayList<>();

        SessionManagement sessionManagement=new SessionManagement(this.getContext());
        String empId=sessionManagement.getSession();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                    JSONArray j = new JSONArray(response);
                   // Toast.makeText(getActivity(), response.toString().trim(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, response.toString());
                    for(int i=0;i<j.length();i++)
                    {
                        try {

                            JSONObject jsonObject=j.getJSONObject(i);
                            Log.d(TAG, jsonObject.toString());

                            PayslipData payslipData=new PayslipData();
                            payslipData.setPid(jsonObject.getString("pid").toString());
                            payslipData.setPay_date(jsonObject.getString("pay_date").toString());
                            payslipData.setEmpId(jsonObject.getString("emp_id").toString());
                            payslipData.setSalaryPM(Integer.parseInt(jsonObject.getString("salaryPM")));
                            payslipData.setFull(Integer.parseInt(jsonObject.getString("full")));
                            payslipData.setHalf(Integer.parseInt(jsonObject.getString("half")));
                            payslipData.setSick(Integer.parseInt(jsonObject.getString("sick")));
                            payslipData.setDfull(Integer.parseInt(jsonObject.getString("dfull")));
                            payslipData.setDhalf(Integer.parseInt(jsonObject.getString("dhalf")));
                            payslipData.setDsick(Integer.parseInt(jsonObject.getString("dsick")));
                            payslipData.setTotalSalary(Integer.parseInt(jsonObject.getString("totalSalary")));
                            payslipData.setTotalDeduction(Integer.parseInt(jsonObject.getString("totalDeduction")));
                            payslipData.setName(jsonObject.getString("emp_name").toString());

                            payslipDataList.add(payslipData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                // recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                payslipAdapter=new PayslipAdapter(getActivity(),payslipDataList);
                recyclerView.setAdapter(payslipAdapter);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data=new HashMap<String, String>();
                data.put("empId",empId);
                return data;
            }
        };

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        //Intent i=new Intent(getApplicationContext(), payslip_details.class);
                        //startActivity(i);
                        Intent i=new Intent(getActivity(),payslip_single.class);
                      //  i.putExtra("emp_name",payslipDataList.get(position).getName());
                        i.putExtra("Pid",payslipDataList.get(position).getPid());
                        Log.d(TAG, empId);
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        return view;
    }
}