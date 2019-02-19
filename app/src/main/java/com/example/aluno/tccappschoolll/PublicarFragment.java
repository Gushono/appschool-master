package com.example.aluno.tccappschoolll;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PublicarFragment extends Fragment {
    View view;
    TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_publicar, container, false);

//        tv = (TextView) view.findViewById(R.id.Oi);

        return view;

    }
}
