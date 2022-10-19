package com.example.sensorsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvSize;
    public static List<String> questionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etAddItem = findViewById(R.id.et_Add_Item);
        Button btnAddItem = findViewById(R.id.btn_Add_Item);
        tvSize = findViewById(R.id.tv_Size);
        Button btnStart = findViewById(R.id.btn_Start);
        etAddItem.setText("");




        btnAddItem.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            String question = etAddItem.getText().toString();
            if (question != null && !question.equals("")){
                questionsList.add(question);
                int size = questionsList.size();
                tvSize.setText("عدد الأسئلة :"+size);
                etAddItem.setText("");
            }else {
                Toast.makeText(getApplicationContext() , "Enter question ." , Toast.LENGTH_SHORT).show();
            }
        });

        btnStart.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            if (questionsList != null && !questionsList.isEmpty()){
                Intent intent = new Intent(getApplicationContext() , QuestionsActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext() , "Add question ." , Toast.LENGTH_SHORT).show();
            }
        });

    }
}

