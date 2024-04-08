package com.seroeva.t;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<Note> listAdapter;
    ListView listView;
    List<Note> listItems = new ArrayList<>();
    int positionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);

        listView = findViewById(R.id.items);
        listView.setAdapter(listAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            positionData = position;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            return;
        }

        if (resultCode == RESULT_OK) {
            Note note = new Note(data.getStringExtra("title"), data.getStringExtra("content"));
            if (data.getBooleanExtra("isCreate", false)) {
                listItems.add(note);
            } else {
                listItems.set(positionData, note);
            }

            listAdapter.notifyDataSetChanged();
        }

        listAdapter.notifyDataSetChanged();
    }

    public void onAddClicked(View w) {
        Intent i = new Intent(getApplicationContext(), EditNoteActivity.class);
        i.putExtra("title", "");
        i.putExtra("content", "");
        i.putExtra("isCreate", true);
        startActivityForResult(i, 1);

    }

    public void onEditClicked(View w) {
        Note note = listItems.get(positionData);

        Intent i = new Intent(getApplicationContext(), EditNoteActivity.class);
        i.putExtra("title", note.getTitle());
        i.putExtra("content", note.getContent());
        i.putExtra("isCreate", false);
        startActivityForResult(i, 1);
    }

    public void onDeleteClicked(View w) {
        new AlertDialog.Builder(this).setTitle("Вы точно хотите удалить?").setPositiveButton("Да", (dialog, which) -> {
                    listItems.remove(positionData);
                    listAdapter.notifyDataSetChanged();
        })
                .setNegativeButton("Нет", (dialog, which) -> {

                }).show();

    }
}