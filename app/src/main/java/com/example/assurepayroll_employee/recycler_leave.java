package com.example.assurepayroll_employee;

import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link recycler_leave#newInstance} factory method to
 * create an instance of this fragment.
 */
public class recycler_leave extends Fragment {

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
        View root= inflater.inflate(R.layout.fragment_recycler_leave, container, false);

        RecyclerView rv=(RecyclerView) root.findViewById(R.id.leave_list);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        String[] lang={"mar", "6"};
        rv.setAdapter(new LeaveAdapter(lang));
        return root;
    }
}