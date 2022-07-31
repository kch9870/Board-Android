package com.example.boardandroid.view.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boardandroid.R;
import com.example.boardandroid.repository.model.response.BoardListResponse;

import java.util.ArrayList;


public class BoardAdapter extends RecyclerView.Adapter {

    // 리사이클러뷰에 넣을 데이터 리스트
    ArrayList<BoardListItem> boardListModel;
    Context context;

    // 생성자를 통하여 데이터 리스트 context 받는다.
    public BoardAdapter(Context context, ArrayList<BoardListItem> boardListModel){
        this.boardListModel = boardListModel;
        this.context = context;
    }
    public class BoardViewHolder extends RecyclerView.ViewHolder{
        // 제목, 작성자, 조회수, 날짜 변수 선언
        View layout;
        TextView views;
        TextView title;
        TextView nickName;
        TextView date;
        TextView commentCount;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            views = itemView.findViewById(R.id.tvViews);
            title = itemView.findViewById(R.id.tvTitle);
            nickName = itemView.findViewById(R.id.tvNickName);
            date = itemView.findViewById(R.id.tvDate);
            commentCount = itemView.findViewById(R.id.tvCommentCount);
            layout = itemView.findViewById(R.id.itemLayout);
        }
    }

    // 레이아웃 설정해주기
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board_list,parent,false);
        BoardViewHolder boardViewHolder = new BoardViewHolder(view);

        return boardViewHolder;
    }

    // 아이템 클릭시 이벤트 처리
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BoardViewHolder boardViewHolder = (BoardViewHolder) holder;

        boardViewHolder.views.setText(String.valueOf(boardListModel.get(position).views));
        boardViewHolder.title.setText(boardListModel.get(position).title);
        boardViewHolder.nickName.setText(boardListModel.get(position).nickName);
        boardViewHolder.date.setText(boardListModel.get(position).date);
        boardViewHolder.commentCount.setText(String.valueOf(boardListModel.get(position).commentCount));

        boardViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,position+"번째 글",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount(){
        return boardListModel.size();
    }


}
