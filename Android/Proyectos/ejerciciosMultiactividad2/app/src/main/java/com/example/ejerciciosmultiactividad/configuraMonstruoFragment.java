package com.example.ejerciciosmultiactividad;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link configuraMonstruoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class configuraMonstruoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public configuraMonstruoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ut04f2_ConfiguraMonstruo.
     */
    // TODO: Rename and change types and number of parameters
    public static configuraMonstruoFragment newInstance(String param1, String param2) {
        configuraMonstruoFragment fragment = new configuraMonstruoFragment();
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

    TextView tvConfMonstruo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_configura_monstruo, container, false);
        tvConfMonstruo = layout.findViewById(R.id.ut04f1idtvConfMonstruo);

        return layout;
    }
    public void setText(String msg) {
        tvConfMonstruo.append(msg);
    }
    public void setColor(String color) {
        tvConfMonstruo.setTextColor(Color.parseColor(color));
    }
}