package com.example.quizzyvoote.classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzyvoote.PassTheQuizActivity;
import com.example.quizzyvoote.R;

import java.util.ArrayList;

public class c_Quiz_List_Adapter extends RecyclerView.Adapter<c_Quiz_List_Adapter.ListViewHolder> implements View.OnClickListener {

    private ArrayList<c_Quiz> quiz_list;

    @Override
    public void onClick(View v) { }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listView = inflater.inflate(R.layout.extra_list_item_show_quizzes, parent, false);

        ListViewHolder viewHolder = new ListViewHolder(listView);
        return viewHolder;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView quiz_id, quiz_creator, quiz_name;
        CardView card_quiz;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            card_quiz = itemView.findViewById(R.id.card_quiz);
            quiz_id = itemView.findViewById(R.id.tv_quiz_id);
            quiz_creator = itemView.findViewById(R.id.tv_quiz_creator_name);
            quiz_name = itemView.findViewById(R.id.tv_label_name);
        }
    }

    public c_Quiz_List_Adapter(ArrayList<c_Quiz> quiz_list) {
        this.quiz_list = quiz_list;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final c_Quiz currentQuiz = quiz_list.get(position);

        holder.quiz_id.setText(currentQuiz.getQuizID().toString());
        holder.quiz_creator.setText(currentQuiz.getQuizCreator());
        holder.quiz_name.setText(currentQuiz.getQuizName());

        holder.card_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, PassTheQuizActivity.class);
                intent.putExtra(PassTheQuizActivity.ID, currentQuiz.getQuizID().toString());
                intent.putExtra(PassTheQuizActivity.CREATOR, currentQuiz.getQuizCreator());
                intent.putExtra(PassTheQuizActivity.NAME, currentQuiz.getQuizName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quiz_list.size();
    }
}
