package com.example.assurepayroll_employee;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.DividerItemDecoration;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link payslip#newInstance} factory method to
 * create an instance of this fragment.
 */
public class payslip extends Fragment {

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
        View view= inflater.inflate(R.layout.fragment_payslip, container, false);
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
        return view;
    }
}