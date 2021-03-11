package com.example.assurepayroll_employee;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.example.assurepayroll_employee.login.MyPREFERENCES;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link leave#newInstance} factory method to
 * create an instance of this fragment.
 */
public class leave extends Fragment {
View view;
EditText etdate,etreason,etleaveType;
TextView tvstatus;
Button btnConfirm;
    SharedPreferences sharedpreferences;
//String URL="http://192.168.43.195:80/SDP_Payroll/request_leave.php";//maitri's url
String URL="http://192.168.43.231:80/SDP_Payroll/request_leave.php";

String date,reason,leave_type,status;
Spinner sp_leave;
ArrayList<String> arrayList_leave;
ArrayAdapter<String> arrayAdapter_leave;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public leave() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment leave.
     */
    // TODO: Rename and change types and number of parameters
    public static leave newInstance(String param1, String param2) {
        leave fragment = new leave();
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
        SessionManagement sessionManagement=new SessionManagement(this.getActivity());
        String empId=sessionManagement.getSession();

        view= inflater.inflate(R.layout.fragment_leave, container, false);
        sp_leave=(Spinner)view.findViewById(R.id.leave_type);
        arrayList_leave=new ArrayList<>();
        arrayList_leave.add("Full");
        arrayList_leave.add("Half");
        arrayList_leave.add("Sick");
        //adapter
        arrayAdapter_leave=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_leave);
        sp_leave.setAdapter(arrayAdapter_leave);

        btnConfirm=view.findViewById(R.id.btnConfirm);
        etdate=view.findViewById(R.id.date);
        etreason=view.findViewById(R.id.reason);
       // etleaveType=view.findViewById(R.id.leave_type);

        tvstatus=view.findViewById(R.id.tvStatus);

        date=reason="";

        Calendar calendar= Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        etdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date =year + "/" + month + "/" + day;
                        etdate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = etdate.getText().toString().trim();
                reason = etreason.getText().toString().trim();
                leave_type=sp_leave.getSelectedItem().toString();
                status = tvstatus.getText().toString().trim();


                if (date.isEmpty()) {
                    etdate.setError("Date is required");
                    etdate.requestFocus();
                }
                else if(reason.isEmpty())
                {
                    etreason.setError("Reason is required");
                    etreason.requestFocus();
                }
                else if ( !date.equals("") && !reason.equals("") && !leave_type.equals(""))
                {
                    Log.i(TAG, "All data are inserted");
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @SuppressLint("WrongConstant")
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response);
                            if (response.equals("success")) {
                                Toast.makeText(getActivity(), "Leave request has been sent", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), display_leaves.class);
                                startActivity(intent);


                            }

                           // tvstatus.setText(response.toString());



                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> data=new HashMap<String, String>();
                            data.put("date",date);
                            data.put("reason",reason);
                            data.put("leave_type",leave_type);
                            data.put("empId",empId);
                            Log.d(TAG, data.toString());
                            return data;
                        }
                    };
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            10000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    ));
                    RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
                    requestQueue.add(stringRequest);
                }
            }
        });
        return  view;
    }

}