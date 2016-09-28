package com.example.christina.app;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class second extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName, editSurname, editMarks, editTextID;
    Button btnAddData;
    Button btnViewAll;
    Button SendBtn;
    EditText txtmessage;
    Button btnviewUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        myDb= new DatabaseHelper(this);

        editName =(EditText)findViewById(R.id.editText_name);
        editSurname =(EditText)findViewById(R.id.editText_surname);
        editMarks =(EditText)findViewById(R.id.editText_Marks);
        editTextID=(EditText)findViewById(R.id.editText);
        btnAddData=(Button)findViewById(R.id.button_add);
        btnViewAll=(Button)findViewById(R.id.button_view);
        SendBtn= (Button)findViewById(R.id.btnSendSMS);
        txtmessage= (EditText)findViewById(R.id.editText);
        btnviewUpdate= (Button)findViewById(R.id.button_update);
        btnDelete= (Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
        sendSMSMessage();
    }
    public void UpdateData(){
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = myDb.updateData(editTextID.getText().toString(), editName.getText().toString(),
                                editSurname.getText().toString(), editMarks.getText().toString());
                        if(isUpdated==true)
                            Toast.makeText(second.this, "Contact info Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(second.this, "Contact info Updated", Toast.LENGTH_LONG).show();
                    }

                }
        );
    }

    private void sendSMSMessage() {
        SendBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res= myDb.getAllData();
                        if (res.getCount()==0){
                            showMessage("Error","Nothing Found");
                            return;
                        }

                        StringBuffer buffer1= new StringBuffer();
                        while(res.moveToNext()) {
                            buffer1.append(res.getString(3));
                        }

                        String[] numbers= new String[3];

                        showMessage("Data", buffer1.toString());
                        Log.i("Send SMS","");
                        String phoneNo= numbers[1];
                        String phoneNo1= numbers[2];
                        String phoneNo2= numbers[3];
                        String message= "Hello, this is Cerebruh. This is a test.";

                        try{
                            SmsManager smsManager =SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneNo, null, message, null, null);
                            smsManager.sendTextMessage(phoneNo1, null, message, null, null);
                            smsManager.sendTextMessage(phoneNo2, null, message, null, null);
                            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e) {
                            Toast.makeText(getApplicationContext(),"SMS failed, please try again.", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(second.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(second.this, "Data Inserted", Toast.LENGTH_LONG).show();

                    }
                }
        );

    }
    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res= myDb. getAllData();
                        if(res.getCount()==0) {
                            showMessage("Error","Nothing Found");
                            return;

                        }
                        StringBuffer buffer= new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID :"+ res.getString(0)+"\n");
                            buffer.append("NAME :"+ res.getString(1)+"\n");
                            buffer.append("SURNAME :"+ res.getString(2)+"\n");
                            buffer.append("PHONE :"+ res.getString(3)+"\n\n");
                        }
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows= myDb.deleteData(editTextID.getText().toString());
                        if (deletedRows> 0)
                            Toast.makeText(second.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(second.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
