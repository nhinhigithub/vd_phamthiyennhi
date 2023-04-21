package com.example.phamthiyennhi;
//phamthiyennhi
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class CHUDE extends AppCompatActivity {

    SQLiteDatabase database=null;
    //khai bao cac dieu khien
    EditText editMaCD,editTenCD;
    ListView lvCD;
    Button bttThem, bttCapnhat, bttXoa;
    //khai bao bienlay du lieu tu Activity
    String MACD, TENCD;
    ArrayList listCD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chude);

//tham chieu cac dieu khien
        editMaCD=(EditText) findViewById(R.id.editMaCD);
        editTenCD=(EditText) findViewById(R.id.editTenCD);
        lvCD=(ListView) findViewById(R.id.lvCD);
        bttThem=(Button) findViewById(R.id.bttThem);
        bttXoa=(Button) findViewById(R.id.bttXoa);
        bttCapnhat=(Button) findViewById(R.id.bttCapnhat);


        bttThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themCD();
            }
        });

    }
    public void themCD(){
        MACD=editMaCD.getText().toString();
        TENCD=editTenCD.getText().toString();
        String sql="Insert into CHUDE ";
        sql+="Valuse('"+MACD+"','"+TENCD+"')";
        if(doAction(sql)==true){
            Toast.makeText(this, "Them [CHU DE] thanh cong", Toast.LENGTH_SHORT).show();
            hienthiCD();
        }
        else
            Toast.makeText(this, "Them [CHU DE KHONG] thanh cong", Toast.LENGTH_SHORT).show();
    }

    public void hienthiCD() {
        //khoi tao ArrayList
        listCD = new ArrayList();
        String sql = "Select MACD, TENCD From CHUDE Order by TENCD";
        database = openOrCreateDatabase("phanhoi.db", MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                listCD.add(new MyChude(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listCD);
        lvCD.setAdapter(adapter);
    }
// gji chu
    public boolean doAction(String sql) {
        try{
            //mo csdl
            database=SQLiteDatabase.openOrCreateDatabase("phanhoi.db",null);
            //thuc thi
            database.execSQL(sql);
            return true;
        }
        catch (Exception exception){
            return false;//thuc thi k thanh cong tra ve false
        }
        finally {
            database.close();
        }
    }
}