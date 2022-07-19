package com.example.boardandroid.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.boardandroid.R;
import com.example.boardandroid.repository.model.UserInfo;
import com.example.boardandroid.view.fragment.BoardListFragment;
import com.example.boardandroid.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserInfo userInfo = UserInfo.getInstance();
        TextView userNickName = findViewById(R.id.tvNickName);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getNickName(userInfo.getUserId());
        // Main Fragment
        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }
            BoardListFragment boardListFragment = new BoardListFragment();
            boardListFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, boardListFragment).commit();
        }
    }
}