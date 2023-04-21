package com.example.phamthiyennhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bttTao=(Button) findViewById(R.id.bttTao);
        Button bttXoa=(Button) findViewById(R.id.bttXoa);

        Button bttChude=(Button) findViewById(R.id.bttChude);
        Button bttNoidung=(Button) findViewById(R.id.bttNoidung);

        bttTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SQLiteDatabase database = openOrCreateDatabase("phanhoi.db", MODE_PRIVATE, null);
                    Toast.makeText(MainActivity.this, "Tao CSDL thanh cong", Toast.LENGTH_SHORT).show();

                }
                catch (Exception ex){
                    Toast.makeText(MainActivity.this,"Tao CSDL [KHONG] thanh cong",Toast.LENGTH_SHORT).show();
                }
            }
        });
        bttXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg="";
                if(deleteDatabase("phanhoi.db")==true)
                    msg+="Xoa CSDL [qlTKB] thanh cong";
                else
                    msg+="Xoa CSDL [qlTKB] khong thanh cong";
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
            }
        });
//chuyen trang
        bttChude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (MainActivity.this, CHUDE.class);
                Bundle bundle=new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        bttNoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (MainActivity.this, NOIDUNG.class);
                Bundle bundle=new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
    //goi doAction
    public boolean doAction(String sql)
    {
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

    //chude
    public void CHUDE()
    {
        //viet sql sang tao bang create table
        String sql="CREATE TABLE CHUDE(";
        sql+="MACD TEXT NOT NULL PRIMARY KEY,";
        sql+="TENCD TEXT";
        //Goi thu tuc doAction
        if(doAction(sql)==true)
            Toast.makeText(this, "Tao bang [CHUDE] thanh cong", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Tao bang [CHUDE KHONG] thanh cong", Toast.LENGTH_SHORT).show();
    }
    //noi dung
    public void NOIDUNG()
    {
        //viet sql sang tao bang create table
        String sql="CREATE TABLE NOIDUNG(";
        sql+="MAND TEXT NOT NULL PRIMARY KEY,";
        sql+="CHITIET TEXT";
        sql+="HOTEN TEXT";
        sql+="MACD TEXT NOT NULL CONSTRAINT MACD REFERENCES CHUDE(MACD) ON DELETE CASCADE)";
        //Goi thu tuc doAction
        if(doAction(sql)==true)
            Toast.makeText(this, "Tao bang [NOI DUNG] thanh cong", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Tao bang [NOI DUNG KHONG] thanh cong", Toast.LENGTH_SHORT).show();
    }
}