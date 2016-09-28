package com.example.christina.app;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class fifth extends AppCompatActivity {
    DatabaseHelper1 myDb;
    EditText editName, editSurname, editMarks, editTextID;
    Button btnAddData;
    Button btnViewAll;
    EditText txtmessage;
    Button btnviewUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        myDb= new DatabaseHelper1(this);

        editName =(EditText)findViewById(R.id.editText_date);
        editSurname =(EditText)findViewById(R.id.editText_symptoms);
        editMarks =(EditText)findViewById(R.id.editText_Meds);
        editTextID=(EditText)findViewById(R.id.editText);
        btnAddData=(Button)findViewById(R.id.button_add);
        btnViewAll=(Button)findViewById(R.id.button_view);
        txtmessage= (EditText)findViewById(R.id.editText);
        btnviewUpdate= (Button)findViewById(R.id.button_update);
        btnDelete= (Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }
    public void UpdateData(){
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = myDb.updateDataa(editTextID.getText().toString(), editName.getText().toString(),
                                editSurname.getText().toString(), editMarks.getText().toString());
                        if(isUpdated==true)
                            Toast.makeText(fifth.this, "info Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(fifth.this, "info Updated", Toast.LENGTH_LONG).show();
                    }

                }
        );
    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertDataa(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(fifth.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(fifth.this, "Data Inserted", Toast.LENGTH_LONG).show();

                    }
                }
        );

    }
    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res= myDb. getAllDataa();
                        if(res.getCount()==0) {
                            showMessage("Error","Nothing Found");
                            return;

                        }
                        StringBuffer buffer= new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID :"+ res.getString(0)+"\n");
                            buffer.append("DATE :"+ res.getString(1)+"\n");
                            buffer.append("SYMPTOMS :"+ res.getString(2)+"\n");
                            buffer.append("MEDICINE :"+ res.getString(3)+"\n\n");
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
                        Integer deletedRows= myDb.deleteDataa(editTextID.getText().toString());
                        if (deletedRows> 0)
                            Toast.makeText(fifth.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(fifth.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
