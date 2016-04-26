package com.testmultilang.testmultilang;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by lenovo on 20-04-2016.
 */
public class SheetInfo  extends Activity{
    private String selectedLang = "ENGLISH";
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        selectedLang = intent.getStringExtra("selectedLang");
        if(selectedLang.equalsIgnoreCase("english"))
            setContentView(R.layout.sheet_info_en);
        else
            setContentView(R.layout.sheet_info_fr);
        Toast.makeText(SheetInfo.this, "Selected: " + selectedLang, Toast.LENGTH_LONG).show();
        context = SheetInfo.this;
        initViews();

    }
    public void initViews() {
     Button btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(context, FormDetail.class);
                startIntent.putExtra("selectedLang", selectedLang);
                context.startActivity(startIntent);
            }
        });

    }


}
