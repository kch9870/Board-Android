package com.example.boardandroid.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.boardandroid.R;
import com.example.boardandroid.repository.model.UserInfo;
import com.example.boardandroid.repository.model.response.GetNickNameResponse;
import com.example.boardandroid.view.fragment.BoardListFragment;
import com.example.boardandroid.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UserInfo 객체 불러오기 (싱글톤)
        UserInfo userInfo = UserInfo.getInstance();
        TextView userNickName = findViewById(R.id.tvNickName);

        userNickName.setText(userInfo.getNickName()+"님 환영합니다.");

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