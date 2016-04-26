package com.testmultilang.testmultilang;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.testmultilang.testmultilang.service.OfflineSyncService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lenovo on 19-04-2016.
 */
public class LoginActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private String selectedLang = "ENGLISH";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        context = LoginActivity.this;
         initViews();
        //startBackgroundService();
/*        JSONObject jsonObject = createAndGetJsonObjFromParameters();
        if(jsonObject != null) {
            addRequest(jsonObject);
        }*/

    }



    public void initViews() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_lang);
        Button login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(context, SheetInfo.class);
                startIntent.putExtra("selectedLang", selectedLang);
                        context.startActivity(startIntent);
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.array_languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(LoginActivity.this);

    }

    private void startBackgroundService() {
        Intent serviceIntent = new Intent(this, OfflineSyncService.class);
        startService(serviceIntent);
    }

    public JSONObject createAndGetJsonObjFromParameters(){

/*        SaveDataModel saveDataModelObj = new SaveDataModel();  // yet to work
        Gson gson = new Gson();
        String json = gson.toJson(saveDataModelObj);*/

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("key", "value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public void addRequest(JSONObject jsonObject){
        String url = "http://api.androidhive.info/volley/person_object.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                //mTextView.setText(response.toString());
            }
        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // mTextView.setText(error.toString());
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(request);
        // AppController.getInstance().getRequestQueue().add(request);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedLang = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + selectedLang, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
