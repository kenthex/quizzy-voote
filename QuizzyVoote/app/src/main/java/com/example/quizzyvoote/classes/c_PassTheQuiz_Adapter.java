package com.example.quizzyvoote.classes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzyvoote.PassTheQuizActivity;
import com.example.quizzyvoote.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class c_PassTheQuiz_Adapter extends RecyclerView.Adapter<c_PassTheQuiz_Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<api_Answers> answers;
    private int lastSelectedPosition = -1;

    public c_PassTheQuiz_Adapter(Context context, List<api_Answers> answers) {
        this.answers = answers;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public c_PassTheQuiz_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.extra_list_item_show_answers, parent, false);
        return new ViewHolder(view);
    }
    private RadioButton lastCheckedRB = null;

    @Override
    public void onBindViewHolder(@NonNull final c_PassTheQuiz_Adapter.ViewHolder holder, final int position) {
        final api_Answers answers = this.answers.get(position);
        holder.title.setText(answers.getAnswer());
        holder.title.setChecked(lastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public void update(List<api_Answers> newlist) {
        answers.clear();
        answers.addAll(newlist);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton title, lastTitle;
        RadioGroup radioGroup;
        CardView cardview;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.rb_select_answer);
            cardview = itemView.findViewById(R.id.card_answer);
            radioGroup = itemView.findViewById(R.id.radioGrouppen);
            lastTitle = itemView.findViewById(R.id.rb_select_answer);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    answers.add(new api_Answers(" "));
                    answers.remove(( answers.size() -1 ));
                    notifyDataSetChanged();

                    Context context = v.getContext();
                    Toast.makeText(context, Storage.getProperty("CURRENT_QUESTION_ID"), Toast.LENGTH_SHORT).show();


                    api_Votes vote = new api_Votes(Integer.parseInt(Storage.getProperty("USER_ID")), Integer.parseInt(Storage.getProperty("CURRENT_QUESTION_ID")), answers.get(lastSelectedPosition).getAnswer());
                    api_NetworkService.getInstance()
                            .getJSONApi()
                            .createVote(vote)
                            .enqueue(new Callback<api_Votes>() {
                                @Override
                                public void onResponse(@NonNull Call<api_Votes> call, @NonNull Response<api_Votes> response) {
                                    api_Votes votes = response.body();
                                }
                                @Override
                                public void onFailure(@NonNull Call<api_Votes> call, @NonNull Throwable t) {
                                    Log.d("RES", "ERROR");
                                    t.printStackTrace();
                                }
                            });

                }
            });

        }
    }
}
