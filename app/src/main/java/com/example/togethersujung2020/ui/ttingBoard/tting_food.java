package com.example.togethersujung2020.ui.ttingBoard;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.freeBoard.FreeActivity;
import com.example.togethersujung2020.ui.main.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tting_food#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tting_food extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public tting_food() {
    }
    public static tting_food newInstance(String param1, String param2) {
        tting_food fragment = new tting_food();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tting_food, container, false);
        Button free_board1 = (Button) view.findViewById(R.id.한식) ;
        free_board1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Button1Activity.class) ;
                startActivity(intent) ;
            }
        });
        Button free_board2 = (Button) view.findViewById(R.id.중식) ;
        free_board2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Button2Activity.class) ;
                startActivity(intent) ;
            }
        });
        Button free_board3 = (Button) view.findViewById(R.id.일식) ;
        free_board3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Button3Activity.class) ;
                startActivity(intent) ;
            }
        });
        Button free_board4 = (Button) view.findViewById(R.id.양식) ;
        free_board4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Button4Activity.class) ;
                startActivity(intent) ;
            }
        });
        Button free_board5 = (Button) view.findViewById(R.id.치킨) ;
        free_board5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Button5Activity.class) ;
                startActivity(intent) ;
            }
        });
        Button free_board6 = (Button) view.findViewById(R.id.피자) ;
        free_board6.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Button6Activity.class) ;
                startActivity(intent) ;
            }
        });
        Button free_board7 = (Button) view.findViewById(R.id.분식) ;
        free_board7.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Button7Activity.class) ;
                startActivity(intent) ;
            }
        });
        Button free_board8 = (Button) view.findViewById(R.id.기타) ;
        free_board8.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Button8Activity.class) ;
                startActivity(intent) ;
            }
        });

        return view;
    }
}