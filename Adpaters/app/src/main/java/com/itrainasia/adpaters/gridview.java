package com.itrainasia.adpaters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


public class gridview extends AppCompatActivity implements AdapterView.OnItemClickListener{

    GridView gridView;
    TextView textView;

    String[]list={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        gridView=(GridView)findViewById(R.id.gridview);
        textView=(TextView)findViewById(R.id.textview);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        textView.setText("You have selected"+((TextView)view).getText());
        Toast.makeText(this, "ID:"+id+"Position:"+position, Toast.LENGTH_SHORT).show();

    }
}
