package com.testmultilang.testmultilang;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.testmultilang.testmultilang.dataModal.SaveDataModel;
import com.testmultilang.testmultilang.database.MyDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lenovo on 15-04-2016.
 */
public class FormDetail extends Activity implements View.OnClickListener{
    AppController appController;

    private String selectedLang = "ENGLISH";
    private Context context;

    // Views for Shelter and Site
    private RadioGroup shelterRdoGrp1, shelterRdoGrp2, siteRdoGrp1, siteRdoGrp2, siteRdoGrp3, siteRdoGrp4, siteRdoGrp5,
                        siteRdoGrp6, siteRdoGrp7, siteRdoGrp8, siteRdoGrp9 ;
    private EditText shelterComment1, shelterComment2, siteComment1, siteComment2, siteComment3, siteComment4, siteComment5, siteComment6,
                     siteComment7, siteComment8, siteComment9 ;

    //Views for Generator and Emergency
    private RadioGroup generatorRdoGrp1, generatorRdoGrp2, generatorRdoGrp3, generatorRdoGrp4,emergencyRdoGrp1, emergencyRdoGrp2 ;
    private EditText generatorComment1, generatorComment2, generatorComment3, generatorComment4, emergencyComment1, emergencyComment2;

    // Views for AirConditioner and Tower
    private RadioGroup airConditionerRdoGrp1, airConditionerRdoGrp2, towerRdoGrp1, towerRdoGrp2, towerRdoGrp3, towerRdoGrp4;
    private EditText airConditionerComment1, airConditionerComment2,towerComment1, towerComment2, towerComment3, towerComment4;

   // Views for Fire
    private RadioGroup fireRdoGrp1;
    private EditText fireComment1;

    ArrayList<RadioGroup> listOfRadioGrp;
    ArrayList<EditText> listOfEditText;

    String[] listOfQuesKeys = { "txt_shelterRdoGrp1", "txt_shelterRdoGrp2", "txt_siteRdoGrp1", "txt_siteRdoGrp2", "txt_siteRdoGrp3",
            "txt_siteRdoGrp4", "txt_siteRdoGrp5","txt_siteRdoGrp6", "txt_siteRdoGrp7", "txt_siteRdoGrp8", "txt_siteRdoGrp9",
            "txt_generatorRdoGrp1", "txt_generatorRdoGrp2", "txt_generatorRdoGrp3", "txt_generatorRdoGrp4",
            "txt_emergencyRdoGrp1", "txt_emergencyRdoGrp2", "txt_airConditionerRdoGrp1", "txt_airConditionerRdoGrp2",
            "txt_towerRdoGrp1", "txt_towerRdoGrp2", "txt_towerRdoGrp3", "txt_towerRdoGrp4", "txt_fireRdoGrp1"

    };

    String[] listOfCommentKeys = { "edt_shelterRdoGrp1", "edt_shelterRdoGrp2", "edt_siteRdoGrp1", "edt_siteRdoGrp2", "edt_siteRdoGrp3",
            "edt_siteRdoGrp4", "edt_siteRdoGrp5","edt_siteRdoGrp6", "edt_siteRdoGrp7", "edt_siteRdoGrp8", "edt_siteRdoGrp9",
            "edt_generatorRdoGrp1", "edt_generatorRdoGrp2", "edt_generatorRdoGrp3", "edt_generatorRdoGrp4",
            "edt_emergencyRdoGrp1", "edt_emergencyRdoGrp2", "edt_airConditionerRdoGrp1", "edt_airConditionerRdoGrp2",
            "edt_towerRdoGrp1", "edt_towerRdoGrp2", "edt_towerRdoGrp3", "edt_towerRdoGrp4", "edt_fireRdoGrp1"

    };

    //Button view
    private Button btn_save, btn_submit, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.form_detail_screen_en);
        Intent intent = getIntent();
        selectedLang = intent.getStringExtra("selectedLang");
        if(selectedLang.equalsIgnoreCase("english"))
            setContentView(R.layout.form_detail_screen_en);
        else
            setContentView(R.layout.form_detail_screen_fr);
       // Toast.makeText(FormDetail.this, "Selected: " + selectedLang, Toast.LENGTH_LONG).show();

        context = FormDetail.this;
        initViews();
    }


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-04-21 12:32:34 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void initViews() {
        shelterRdoGrp1 = (RadioGroup)findViewById( R.id.shelter_rdo_grp_1 );
        shelterComment1 = (EditText)findViewById( R.id.shelter_comment_1 );
        shelterRdoGrp2 = (RadioGroup)findViewById( R.id.shelter_rdo_grp_2 );
        shelterComment2 = (EditText)findViewById( R.id.shelter_comment_2 );

        siteRdoGrp1 = (RadioGroup)findViewById( R.id.site_rdo_grp_1 );
        siteComment1 = (EditText)findViewById( R.id.site_comment_1 );
        siteRdoGrp2 = (RadioGroup)findViewById( R.id.site_rdo_grp_2 );
        siteComment2 = (EditText)findViewById( R.id.site_comment_2 );
        siteRdoGrp3 = (RadioGroup)findViewById( R.id.site_rdo_grp_3 );
        siteComment3 = (EditText)findViewById( R.id.site_comment_3 );
        siteRdoGrp4 = (RadioGroup)findViewById( R.id.site_rdo_grp_4 );
        siteComment4 = (EditText)findViewById( R.id.site_comment_4 );
        siteRdoGrp5 = (RadioGroup)findViewById( R.id.site_rdo_grp_5 );
        siteComment5 = (EditText)findViewById( R.id.site_comment_5 );
        siteRdoGrp6 = (RadioGroup)findViewById( R.id.site_rdo_grp_6 );
        siteComment6 = (EditText)findViewById( R.id.site_comment_6 );
        siteRdoGrp7 = (RadioGroup)findViewById( R.id.site_rdo_grp_7 );
        siteComment7 = (EditText)findViewById( R.id.site_comment_7 );
        siteRdoGrp8 = (RadioGroup)findViewById( R.id.site_rdo_grp_8 );
        siteComment8 = (EditText)findViewById( R.id.site_comment_8 );
        siteRdoGrp9 = (RadioGroup)findViewById( R.id.site_rdo_grp_9 );
        siteComment9 = (EditText)findViewById( R.id.site_comment_9 );


        generatorRdoGrp1 = (RadioGroup)findViewById( R.id.generator_rdo_grp_1 );
        generatorComment1 = (EditText)findViewById( R.id.generator_comment_1 );
        generatorRdoGrp2 = (RadioGroup)findViewById( R.id.generator_rdo_grp_2 );
        generatorComment2 = (EditText)findViewById( R.id.generator_comment_2 );
        generatorRdoGrp3 = (RadioGroup)findViewById( R.id.generator_rdo_grp_3 );
        generatorComment3 = (EditText)findViewById( R.id.generator_comment_3 );
        generatorRdoGrp4 = (RadioGroup)findViewById( R.id.generator_rdo_grp_4 );
        generatorComment4 = (EditText)findViewById( R.id.generator_comment_4 );

        emergencyRdoGrp1 = (RadioGroup)findViewById( R.id.emergency_rdo_grp_1 );
        emergencyComment1 = (EditText)findViewById( R.id.emergency_comment_1 );
        emergencyRdoGrp2 = (RadioGroup)findViewById( R.id.emergency_rdo_grp_2 );
        emergencyComment2 = (EditText)findViewById( R.id.emergency_comment_2 );

        airConditionerRdoGrp1 = (RadioGroup)findViewById( R.id.air_conditioner_rdo_grp_1 );
        airConditionerComment1 = (EditText)findViewById( R.id.air_conditioner_comment_1 );
        airConditionerRdoGrp2 = (RadioGroup)findViewById( R.id.air_conditioner_rdo_grp_2 );
        airConditionerComment2 = (EditText)findViewById( R.id.air_conditioner_comment_2 );

        towerRdoGrp1 = (RadioGroup)findViewById( R.id.tower_rdo_grp_1 );
        towerComment1 = (EditText)findViewById( R.id.tower_comment_1 );
        towerRdoGrp2 = (RadioGroup)findViewById( R.id.tower_rdo_grp_2 );
        towerComment2 = (EditText)findViewById( R.id.tower_comment_2 );
        towerRdoGrp3 = (RadioGroup)findViewById( R.id.tower_rdo_grp_3 );
        towerComment3 = (EditText)findViewById( R.id.tower_comment_3 );
        towerRdoGrp4 = (RadioGroup)findViewById( R.id.tower_rdo_grp_4 );
        towerComment4 = (EditText)findViewById( R.id.tower_comment_4 );

        fireRdoGrp1 = (RadioGroup)findViewById( R.id.fire_rdo_grp_1 );
        fireComment1 = (EditText)findViewById( R.id.fire_comment_1 );

       // btn_save = (Button)findViewById( R.id.btn_save );
        btn_submit = (Button)findViewById(R.id.btn_submit);
        btn_cancel = (Button)findViewById( R.id.btn_cancel );

        //btn_save.setOnClickListener(FormDetail.this);
        btn_submit.setOnClickListener(FormDetail.this);
        btn_cancel.setOnClickListener(FormDetail.this);

        listOfRadioGrp = new ArrayList<RadioGroup>();
        listOfEditText = new ArrayList<EditText>();
        addRadioGrpViewsToList();
        addEditTextViewsToList();

        //appController = (AppController)getApplication();

    }

    @Override
    public void onClick(View v) {
       /* if( v == btn_save ){
            Toast.makeText(FormDetail.this, "Selected: save" , Toast.LENGTH_LONG).show();
        }*/
        if( v == btn_submit ){
            String jsonString = "";
            jsonString = createAndGetJSONObjOfQues();
            //if(!isNetworkConnected()){
                if(!jsonString.equalsIgnoreCase("")) {
                    MyDB myDB = new MyDB(FormDetail.this);
                    myDB.insertRecords(jsonString);

                    for(int j = 0; j < myDB.getRecords().size(); j++) {
                        Log.i("database records   ",myDB.getRecords().get(j).getSurveyFormId()+"   "+myDB.getRecords().get(j).getSurveyFormDetailObj());
                    }
                    myDB.dBClose();
                    displayDialog("Airtel Survey", "Please check internet connection.Data saved offline.");
                }
           /* } else{
                    displayDialog("Airtel Survey", "Data submitted successfully");
            }*/
            //refreshActivity();

        }
        if( v == btn_cancel ){
            refreshActivity();
            Toast.makeText(FormDetail.this, "Selected: cancel" , Toast.LENGTH_LONG).show();
        }

    }
    public void refreshActivity() {
        finish();
        startActivity(getIntent());
/*        Intent startIntent = new Intent(FormDetail.this, FormDetail.class);
        startIntent.putExtra("selectedLang", selectedLang);

        startActivity(startIntent);
        FormDetail.this.finish();*/

    }

    public void displayDialog(String title, String message){

        AlertDialog.Builder alert = new AlertDialog.Builder(FormDetail.this);
       // alert.setTitle("Doctor");
        alert.setMessage(message);
        alert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        refreshActivity();
                    }
                });
        alert.show();
    }

    public void addRadioGrpViewsToList() {

        //ArrayList of radio group views
        listOfRadioGrp.add(shelterRdoGrp1);
        listOfRadioGrp.add(shelterRdoGrp2);
        listOfRadioGrp.add(siteRdoGrp1);
        listOfRadioGrp.add(siteRdoGrp2);
        listOfRadioGrp.add(siteRdoGrp3);
        listOfRadioGrp.add(siteRdoGrp4);
        listOfRadioGrp.add(siteRdoGrp5);
        listOfRadioGrp.add(siteRdoGrp6);
        listOfRadioGrp.add(siteRdoGrp7);
        listOfRadioGrp.add(siteRdoGrp8);
        listOfRadioGrp.add(siteRdoGrp9);
        listOfRadioGrp.add(generatorRdoGrp1);
        listOfRadioGrp.add(generatorRdoGrp2);
        listOfRadioGrp.add(generatorRdoGrp3);
        listOfRadioGrp.add(generatorRdoGrp4);
        listOfRadioGrp.add(emergencyRdoGrp1);
        listOfRadioGrp.add(emergencyRdoGrp2);
        listOfRadioGrp.add(airConditionerRdoGrp1);
        listOfRadioGrp.add(airConditionerRdoGrp2);
        listOfRadioGrp.add(towerRdoGrp1);
        listOfRadioGrp.add(towerRdoGrp2);
        listOfRadioGrp.add(towerRdoGrp3);
        listOfRadioGrp.add(towerRdoGrp4);
        listOfRadioGrp.add(fireRdoGrp1);

    }

    public void addEditTextViewsToList() {

        //ArrayList of radio group views
        listOfEditText.add(shelterComment1);
        listOfEditText.add(shelterComment2);
        listOfEditText.add(siteComment1);
        listOfEditText.add(siteComment2);
        listOfEditText.add(siteComment3);
        listOfEditText.add(siteComment4);
        listOfEditText.add(siteComment5);
        listOfEditText.add(siteComment6);
        listOfEditText.add(siteComment7);
        listOfEditText.add(siteComment8);
        listOfEditText.add(siteComment9);
        listOfEditText.add(generatorComment1);
        listOfEditText.add(generatorComment2);
        listOfEditText.add(generatorComment3);
        listOfEditText.add(generatorComment4);
        listOfEditText.add(emergencyComment1);
        listOfEditText.add(emergencyComment2);
        listOfEditText.add(airConditionerComment1);
        listOfEditText.add(airConditionerComment2);
        listOfEditText.add(towerComment1);
        listOfEditText.add(towerComment2);
        listOfEditText.add(towerComment3);
        listOfEditText.add(towerComment4);
        listOfEditText.add(fireComment1);

    }

    public String createAndGetJSONObjOfQues() {
        JSONObject jsonObj = new JSONObject();
       if( listOfRadioGrp != null && listOfRadioGrp.size()>0 ){

           for(int i = 0; i < listOfRadioGrp.size(); i++ ) {
              // Log.i("jsonObj",listOfQuesKeys[i] +"  "+getRdoGrpSelectedVal(listOfRadioGrp.get(i)));
               try {
                   jsonObj.put( listOfQuesKeys[i], getRdoGrpSelectedVal(listOfRadioGrp.get(i)));
                   jsonObj.put( listOfCommentKeys[i], getEditTextComment(listOfEditText.get(i)));
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }

           Log.i("jsonObj",jsonObj.toString());
       }
        return jsonObj.toString();
    }

    public  String getEditTextComment(EditText editText){
        String comment = "";
        comment = editText.getText().toString();
        return comment;
    }

    public String getRdoGrpSelectedVal(RadioGroup rg) {
        String selection = "";
        if(rg.getCheckedRadioButtonId()!=-1){
            int id= rg.getCheckedRadioButtonId();
            View radioButton = rg.findViewById(id);
            int radioId = rg.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rg.getChildAt(radioId);
             selection = (String) btn.getText();
            Toast.makeText(FormDetail.this, "Selected: submit" + selection, Toast.LENGTH_LONG).show();

        }
        return selection;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


}
