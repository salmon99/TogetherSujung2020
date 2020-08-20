package com.example.togethersujung2020.ui.ttingBoard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.ttingBoard.dummy.DummyContent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A fragment representing a list of Items.
 */
public class tting_stuff extends Fragment {

    public tting_stuff() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static tting_stuff newInstance(int columnCount) {
        tting_stuff fragment = new tting_stuff();
        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_tting_stuff_list, container, false);

        ListView listview = null;
        final TtingListViewAdapter adapter;

        // Adapter 생성
        adapter = new TtingListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Query query = database.child("ttingboard").orderByChild("div").equalTo("공동구매");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                TtingBoard board = snapshot.getValue(TtingBoard.class);
                adapter.addItem(board.getTitle(), board.getContent(), board.getDiv(), snapshot.getKey());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                adapter.notifyDataSetChanged();
            }
        }); //글 목록 데이터베이스와 리스트뷰 연결


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                TtingListViewItem item = (TtingListViewItem) parent.getItemAtPosition(position);

                String titleStr = item.getTitle();
                String descStr = item.getDesc();
                String divStr = item.getDiv();
                String key = item.getKey();

                Intent postview = new Intent(getActivity(), TtingPostViewActivity.class);
                postview.putExtra("title", titleStr);
                postview.putExtra("content", descStr);
                postview.putExtra("div", divStr);
                postview.putExtra("postkey", key);
                startActivity(postview);
            }
        });

        Button free_board1 = (Button) view.findViewById(R.id.button);
        free_board1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newPost = new Intent(getActivity(), TtingWritePostActivity.class);
                newPost.putExtra("div","공동구매");
                startActivity(newPost);
                Toast.makeText(getActivity(), "새 글 등록 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}