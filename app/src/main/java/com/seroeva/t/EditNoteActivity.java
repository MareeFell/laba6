package com.seroeva.t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {
    EditText titleText, contentText;

    boolean isCreate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        isCreate = getIntent().getBooleanExtra("isCreate", false);

        titleText = findViewById(R.id.title_text);
        contentText = findViewById(R.id.content_text);

        titleText.setText(title);
        contentText.setText(content);
    }

    public void onOk(View view) {
        Intent i = new Intent();

        i.putExtra("title", titleText.getText().toString());
        i.putExtra("content", contentText.getText().toString());
        i.putExtra("isCreate", isCreate);

        setResult(RESULT_OK, i);
        finish();
    }

    public void onCancel(View view) {
        Intent i = new Intent();

        setResult(RESULT_CANCELED, i);
        finish();
    }
}