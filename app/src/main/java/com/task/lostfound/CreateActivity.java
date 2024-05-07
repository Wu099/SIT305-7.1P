package com.task.lostfound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private EditText name_edit,phone_edit,description_edit,location_edit,date_edit;
    private RadioGroup postTypeGroup;
    private String postType="Lost";
    private LostFoundSqlHelper  lostFoundSqlHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        name_edit=findViewById(R.id.name_edit);
        phone_edit=findViewById(R.id.phone_edit);
        description_edit=findViewById(R.id.description_edit);
        location_edit=findViewById(R.id.location_edit);
        date_edit=findViewById(R.id.date_edit);
        postTypeGroup=findViewById(R.id.postTypeGroup);
        postTypeGroup.setOnCheckedChangeListener(this);
    }

    public void saveClick(View v){
        String name=name_edit.getText().toString().trim();
        String phone=phone_edit.getText().toString().trim();
        String description=description_edit.getText().toString().trim();
        String location=location_edit.getText().toString().trim();
        String date=date_edit.getText().toString().trim();
        LostFound lostFound=new LostFound(name,postType,phone,description,location,date);
        long result = lostFoundSqlHelper.insert(lostFound);
        if(result!=-1){
            Toast.makeText(this,"ADD SUCCESS!",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,"ADD Failed!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        RadioButton checkRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        postType = checkRadioButton.getText().toString().trim();
    }

    @Override
    protected void onStart() {
        super.onStart();
        lostFoundSqlHelper = LostFoundSqlHelper.getInstance(this, 1);
        //打开数据库帮助器的写连接
        lostFoundSqlHelper.openWriteLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
        lostFoundSqlHelper.closeLink();
    }
}