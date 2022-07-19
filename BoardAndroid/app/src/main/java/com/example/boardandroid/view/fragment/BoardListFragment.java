package com.example.boardandroid.view.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.boardandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BoardListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BoardListFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static BoardListFragment newInstance(String param1, String param2) {
        BoardListFragment fragment = new BoardListFragment();
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
        View view = inflater.inflate(R.layout.fragment_board_list,container,false);
        // Inflate the layout for this fragment

        FloatingActionButton btnWrite  = view.findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(v-> onClickBoardWriteFragment());

        return view;
    }

    private void onClickBoardWriteFragment(){
        BoardWriteFragment boardWriteFragment = new BoardWriteFragment();
        Bundle args = new Bundle();
//      args.putInt(ArticleFragment.ARG_POSITION, position);
        boardWriteFragment.setArguments(args);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, boardWriteFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
}