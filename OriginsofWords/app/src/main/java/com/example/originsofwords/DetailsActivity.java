package com.example.originsofwords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        dBHandler db = new dBHandler(this);
        ArrayList<HashMap<String, String>> userList = db.GetWords();
        ListView lv = (ListView) findViewById(R.id.word_list);
        ListAdapter adapter = new SimpleAdapter(DetailsActivity.this, userList, R.layout.listview,new String[]{"word","origin"}, new int[]{R.id.word1, R.id.origin1});
        lv.setAdapter(adapter);
        Button back = (Button)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DetailsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
