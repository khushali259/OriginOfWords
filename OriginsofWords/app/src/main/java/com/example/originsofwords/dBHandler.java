package com.example.originsofwords;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
public class dBHandler extends SQLiteOpenHelper {


        private static final int DB_VERSION = 1;
        private static final String DB_NAME = "usersdb";
        private static final String TABLE_Words = "WordSearch";
        private static final String KEY_Word = "word";
        private static final String KEY_Origin = "origin";

        public dBHandler(Context context){
            super(context,DB_NAME, null, DB_VERSION);
        }

    public dBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
        public void onCreate(SQLiteDatabase db){
            String CREATE_TABLE = "CREATE TABLE " + TABLE_Words + "("
                    + KEY_Word+ " TEXT ," + KEY_Origin + " TEXT"+")";
            db.execSQL(CREATE_TABLE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            // Drop older table if exist
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Words);
            // Create tables again
            onCreate(db);
        }


        // Adding new Word
        void insertWordSearched(String word,String origin){
            //Get the Data Repository in write mode
            SQLiteDatabase db = this.getWritableDatabase();
            //Create a new map of values, where column names are the keys
            ContentValues cValues = new ContentValues();
            cValues.put(KEY_Word, word);
            cValues.put(KEY_Origin, origin);
            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(TABLE_Words,null, cValues);
            db.close();
        }
        // Get word
        public ArrayList<HashMap<String, String>> GetWords(){
            SQLiteDatabase db = this.getWritableDatabase();
            ArrayList<HashMap<String, String>> wordList = new ArrayList<>();
            String query = "SELECT word , origin FROM "+ TABLE_Words;
            Cursor cursor = db.rawQuery(query,null);
            while (cursor.moveToNext()){
                HashMap<String,String> word = new HashMap<>();
                word.put("word",cursor.getString(cursor.getColumnIndex(KEY_Word)));
                word.put("origin",cursor.getString(cursor.getColumnIndex(KEY_Origin)));
                wordList.add(word);
            }
            return  wordList;
        }

    }

