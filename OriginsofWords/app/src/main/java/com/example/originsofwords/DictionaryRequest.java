package com.example.originsofwords;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DictionaryRequest extends AsyncTask<String,Integer,String>{

        Context context;
        TextView showOrigin;

        DictionaryRequest(Context context, TextView tV){
            this.context = context;
            showOrigin = tV;

        }
        @Override
        protected String doInBackground(String... params) {

            //TODO: replace with your own app id and app key
            final String app_id = "259acf28";
            final String app_key = "e3ee93ec1c0c467072f25beb0d59f207";
            try {
                URL url = new URL(params[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Accept","application/json");
                urlConnection.setRequestProperty("app_id","259acf28");
                urlConnection.setRequestProperty("app_key","e3ee93ec1c0c467072f25beb0d59f207");

                // read the output from the server
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                return stringBuilder.toString();

            }
            catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            String ori;
            try {
                JSONObject js = new JSONObject(result);
                JSONArray results = js.getJSONArray("results");
                JSONObject lEntries = results.getJSONObject(0);
                JSONArray laArray = lEntries.getJSONArray("lexicalEntries");
                JSONObject entries = laArray.getJSONObject(0);
                JSONArray e =entries.getJSONArray("entries");

                JSONObject jsonObject = e.getJSONObject(0);
                JSONArray d= jsonObject.getJSONArray("etymologies");



                ori = d.getString(0);

                showOrigin.setText(ori);

            } catch (JSONException e) {
                e.printStackTrace();
            }


            super.onPostExecute(result);
            Log.v("Result of Dictionary","onPostExecute"+result);
        }
}
