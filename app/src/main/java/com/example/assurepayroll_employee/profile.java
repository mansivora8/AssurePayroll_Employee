package com.example.assurepayroll_employee;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import static android.content.ContentValues.TAG;


public class profile extends Fragment {
    TextView name,email_id,contact,dob;
    String sname,semail_id,sdob,scontact;

    Button btnUpdate;
    View view;
    String URL="http://192.168.29.195:80/SDP_Payroll/profile.php";//maitri's url
    String EditURL="http://192.168.29.195:80/SDP_Payroll/update_profile.php";//maitri's url
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public profile() {

    }


    public static profile newInstance(String param1, String param2) {
        profile fragment = new profile();
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
        SessionManagement sessionManagement=new SessionManagement(this.getActivity());
        String empId=sessionManagement.getSession();

        view= inflater.inflate(R.layout.fragment_profile, container, false);

        name=view.findViewById(R.id.name);
        email_id=view.findViewById(R.id.email_id);
        contact=view.findViewById(R.id.contact);
        dob=view.findViewById(R.id.dob);
        btnUpdate=view.findViewById(R.id.btnUpdate);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Toast.makeText(getContext(),response.toString().trim(),Toast.LENGTH_SHORT).show();
               // Log.d(TAG, response);
                try{

                    JSONArray j = new JSONArray(response);
                    for(int i=0;i<j.length();i++)
                    {
                        try {

                            JSONObject jsonObject=j.getJSONObject(i);
                            Log.d(TAG, jsonObject.toString());
                            //Log.d(TAG,jsonObject.getString("name").toString());
                            name.setText(jsonObject.getString("name").toString());
                            email_id.setText(jsonObject.getString("email_id").toString());
                            contact.setText(jsonObject.getString("contact").toString());
                            dob.setText(jsonObject.getString("dob").toString());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                data.put("empId",empId);
                Log.d(TAG, data.toString());
                return data;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sname = name.getText().toString().trim();
                semail_id = email_id.getText().toString().trim();
                scontact =contact.getText().toString().trim();
                sdob = dob.getText().toString().trim();

                StringRequest stringRequest1= new StringRequest(Request.Method.POST, EditURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
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
                        //  data.put("eid",eid);
                        data.put("empId",empId);
                        data.put("name",sname);
                        data.put("email_id",semail_id);
                        data.put("contact",scontact );
                        data.put("dob",sdob);

                        Log.d(TAG, data.toString());

                        return  data;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
                requestQueue.add(stringRequest1);
            }
        });

        return view;
    }
}