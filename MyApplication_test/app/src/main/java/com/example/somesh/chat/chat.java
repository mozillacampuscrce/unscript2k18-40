package com.example.somesh.chat;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.somesh.myapplication_test.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class chat extends AppCompatActivity {

    ListView listView;
    EditText editText;
    List<ChatModel> list_chat = new ArrayList<>();
    ImageButton button;
    private JSONObject jsonObject;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        listView = (ListView)findViewById(R.id.list_of_message);
        editText = (EditText)findViewById(R.id.user_message);
        button=(ImageButton)findViewById(R.id.btnSend);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                ChatModel model = new ChatModel(text,true); // user send message
                list_chat.add(model);
                new witAPI().execute(list_chat);

                editText.setText("");
            }
        });
    }
    private class witAPI extends AsyncTask<List<ChatModel>,Void,String> {
        String stream = null;
        List<ChatModel> models;
        String text = editText.getText().toString();
        String key="H6ROTGW4H4ZUT24X2YLUOMXARB4RZHWW";
        @Override
        protected String doInBackground(List<ChatModel>... params) {
            String url = "https://api.wit.ai/message?v=20170307&q=";
            models = params[0];
            HttpDataHandler httpDataHandler = new HttpDataHandler();
            stream = httpDataHandler.GetHTTPData(url,key,text);
            if(stream!=null)
            Log.d("Wit Responce",stream);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                jsonObject = new JSONObject(s);
                String value=jsonObject.getJSONObject("entities").getJSONArray("response").getJSONObject
                        (0).getString("value");
                ChatModel chatModel = new ChatModel(value,false); // get response from simsimi
                models.add(chatModel);
                CustomAdapter adapter = new CustomAdapter(models,getApplicationContext());
                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
