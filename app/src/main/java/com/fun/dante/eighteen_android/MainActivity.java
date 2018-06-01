package com.fun.dante.eighteen_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private Button Write;
    private Button Read;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.Edit_text);

        Write = findViewById(R.id.Write);
        Write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputStream out=null;
                try{
                    FileOutputStream fileOutputStream = openFileOutput("DanteFun_File",MODE_PRIVATE);
                    out = new BufferedOutputStream(fileOutputStream);
                    String message = editText.getText().toString();
                    try{
                        out.write(message.getBytes());
                    }finally {
                        out.close();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        Read = findViewById(R.id.Read);
        Read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in=null;
                try{
                    FileInputStream fileInputStream = openFileInput("DanteFun_File");
                    in = new BufferedInputStream(fileInputStream);
                    int c;
                    StringBuilder stringBuilder = new StringBuilder("");
                    try{
                        while ((c =in.read())!=-1)
                        {
                            stringBuilder.append((char)c);
                        }
                        Toast.makeText(MainActivity.this,stringBuilder.toString(),Toast.LENGTH_LONG).show();
                    }finally {
                        if(in!=null)
                        {
                            in.close();
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
