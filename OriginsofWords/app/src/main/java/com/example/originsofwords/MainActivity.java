package com.example.originsofwords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String url;
    private TextView showOrigin;
    private EditText enterWord;
    Intent intent;
    String word;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showOrigin=findViewById(R.id.origin);
        enterWord=findViewById(R.id.word);


    }
    private String dictionaryEntries() {
        final String language = "en-gb";
        word = enterWord.getText().toString();
        final String fields ="etymologies";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }
    public void sendRequestOnClick(View v){

        DictionaryRequest dR= new DictionaryRequest(this ,showOrigin);
        url = dictionaryEntries();
        dR.execute(url);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                String origin = showOrigin.getText().toString();
                dBHandler dbHandler = new dBHandler(MainActivity.this);
                dbHandler.insertWordSearched(word, origin);
            }
        }, 4000);




    }
    public void seeWord(View view){
        intent = new Intent(MainActivity.this,DetailsActivity.class);
        startActivity(intent);
    }
}

