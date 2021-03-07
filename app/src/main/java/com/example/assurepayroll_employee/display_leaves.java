package com.example.assurepayroll_employee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class display_leaves extends AppCompatActivity {
TextView tfull,thalf,tsick,tv;
    String URL="http://192.168.43.195:80/SDP_Payroll/display_leaves.php";//maitri
    //String URL="http://192.168.43.231:80/SDP_Payroll/display_leaves.php";
   // String full,half,sick;
    static final String TAG = "Register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_leaves);
        //full=half=sick="";
        tfull=findViewById(R.id.full_count);
        thalf=findViewById(R.id.half_count);
        tsick=findViewById(R.id.sick_count);

        SessionManagement sessionManagement=new SessionManagement(display_leaves.this);
        String empId=sessionManagement.getSession();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                //tv.setText(response.toString());
                String JSON_STRING=response.toString();
                String f,h,s;
                try {
                    // get JSONObject from JSON file
                    JSONObject obj = new JSONObject(JSON_STRING);
                    // fetch JSONObject named leave
                    JSONObject leave = obj.getJSONObject("leave");
                    // get employee name and salary
                    f = leave.getString("full");
                    h = leave.getString("half");
                    s = leave.getString("sick");

                    tfull.setText(f);
                    thalf.setText(h);
                    tsick.setText(s);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(display_leaves.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(display_leaves.this,error.toString().trim(),Toast.LENGTH_SHORT).show();
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

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}