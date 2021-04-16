package com.example.assurepayroll_employee;

import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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
 * Use the {@link recycler_leave#newInstance} factory method to
 * create an instance of this fragment.
 */
public class recycler_leave extends Fragment {
    //String URL="http://192.168.43.195:80/SDP_Payroll/display_leavesList_employee.php";
    String URL="http://192.168.29.195:80/SDP_Payroll/display_leavesList_employee.php"; //maitri
    static final String TAG = "Register";

    List<LeavesData> leavesDataList;
    RecyclerView recyclerView;
    LeaveAdapter leaveAdapter;
    View view;


  //  List<LeavesData> leavesDataList;
   /* RecyclerView recyclerView;
    LeaveAdapter leaveAdapter;
    View view;*/
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public recycler_leave() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment recycler_leave.
     */
    // TODO: Rename and change types and number of parameters
    public static recycler_leave newInstance(String param1, String param2) {
        recycler_leave fragment = new recycler_leave();
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
        view= inflater.inflate(R.layout.fragment_recycler_leave, container, false);
        recyclerView=view.findViewById(R.id.leave_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        leavesDataList=new ArrayList<>();

        SessionManagement sessionManagement=new SessionManagement(this.getActivity());
        String empId=sessionManagement.getSession();

        /*view= inflater.inflate(R.layout.fragment_recycler_leave, container, false);
        recyclerView=view.findViewById(R.id.leave_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));*/
      //  leavesDataList=new ArrayList<>();
      /*  JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.POST, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_LONG).show();
                Log.d(TAG, response.toString());

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                String[] lang={"mar", "6"};
                recyclerView.setAdapter(new LeaveAdapter(lang));



            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data=new HashMap<String,String>();
                data.put("empId",empId);

                return data;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);*/
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray j = new JSONArray(response);
                    for(int i=0;i<j.length();i++)
                    {
                        try {

                            JSONObject jsonObject=j.getJSONObject(i);
                            Log.d(TAG, jsonObject.toString());
                            LeavesData leavesData=new LeavesData();
                            leavesData.setId(jsonObject.getString("id").toString());
                            leavesData.setDate(jsonObject.getString("date").toString());
                            leavesData.setReson(jsonObject.getString("reason").toString());
                            leavesData.setLeave_type_id(jsonObject.getString("leave_type_id").toString());
                            leavesData.setEmp_id(jsonObject.getString("emp_id").toString());
                            leavesData.setStatus(jsonObject.getString("status").toString());

                            leavesDataList.add(leavesData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    leaveAdapter=new LeaveAdapter(getActivity(),leavesDataList);
                    recyclerView.setAdapter(leaveAdapter);
                    /*Log.d(TAG, j.toString());
                    Toast.makeText(getActivity(),j.toString(),Toast.LENGTH_LONG).show();*/
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data=new HashMap<String,String>();
                data.put("empId",empId);

                return data;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);



     /*  RecyclerView rv=(RecyclerView) root.findViewById(R.id.leave_list);
       rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        String[] lang={"mar", "6"};
        rv.setAdapter(new LeaveAdapter(lang));
        return root;*/
    return view;
    }
}