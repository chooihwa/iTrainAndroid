package com.itrainasia.userpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textView);
        editText=(EditText)findViewById(R.id.editText);


    }

    static final private  String FILE = "output.txt";


    void writeFile(View view){
        FileOutputStream fops;
        try {
            fops = openFileOutput(FILE, Context.MODE_PRIVATE);
            Toast.makeText(this, "Opening file", Toast.LENGTH_SHORT).show();
            String fileContent = editText.getText().toString();
            fops.write(fileContent.getBytes());
        }
        catch (FileNotFoundException e){
            Log.w("writeFile", e);
        } catch (IOException e) {
            Log.w("IOException", e);
        }
    }

    void readFile(View view){
        FileInputStream fips;
        try{
            fips = openFileInput(FILE);
            int data = fips.read();
            textView.setText(String.valueOf(data));
            fips.close();
        } catch (FileNotFoundException e) {
            Log.w("File not found", e);
        } catch (IOException e) {
            Log.w("IOException",e);
        }
    }



        }



