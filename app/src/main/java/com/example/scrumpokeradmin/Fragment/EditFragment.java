package com.example.scrumpokeradmin.Fragment;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scrumpokeradmin.Activity.LoginActivity;
import com.example.scrumpokeradmin.Object.Question;
import com.example.scrumpokeradmin.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class EditFragment extends DialogFragment {
    private View view;
    private EditText questionEditText,timeEditText;
    private Button sendQuestionButton,deleteButton;
    String question,time;
    public EditFragment(String question,String time) {
        this.question = question;
        this.time = time;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_edit, container, false);
        inicialize();
        questionEditText.setText(question);
        timeEditText.setText(time);
        final String key = LoginActivity.db.getQuestionKey(question);
        sendQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!questionEditText.getText().toString().equals("") && !timeEditText.getText().toString().equals("")){
                    String time = timeEditText.getText().toString();
                    int index = time.indexOf(":");
                    String hour = time.substring(0,index);
                    String minute = time.substring(index+1);
                    Question question = new Question(questionEditText.getText().toString(),minute,hour);
                    LoginActivity.db.setQuestion(question,key);
                }else{
                    Toast.makeText(view.getContext(),"You did'n write a question!",Toast.LENGTH_LONG).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.db.deleteQuestion(key);
            }
        });

        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getFragmentManager(), "time picker");
            }
        });
        return view;
    }

    public void inicialize()
    {
        questionEditText = view.findViewById(R.id.questionEditText);
        sendQuestionButton = view.findViewById(R.id.setQuestionButton);
        timeEditText = view.findViewById(R.id.timeEditText);
        deleteButton = view.findViewById(R.id.deleteQuestionButton);
    }


}
