package com.example.assurepayroll_employee;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment implements View.OnClickListener {
    CardView c1,c2,c3;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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
        View root= inflater.inflate(R.layout.fragment_home, container, false);
        c1=(CardView)root.findViewById(R.id.leave_card);
        c2=(CardView)root.findViewById(R.id.attendance_card);
        c3=(CardView)root.findViewById(R.id.payslip_card);
        c1.setOnClickListener((View.OnClickListener) this);
        c2.setOnClickListener((View.OnClickListener) this);
        c3.setOnClickListener((View.OnClickListener) this);
        return root;
    }
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){

            case R.id.attendance_card:
                i=new Intent(getActivity(),attendance.class);
                startActivity(i);
                break;

            case R.id.payslip_card:
                i=new Intent(getActivity(),display_payslips.class);
                startActivity(i);
                break;

            case R.id.leave_card:
                i=new Intent(getActivity(),display_leaves.class);
                startActivity(i);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}