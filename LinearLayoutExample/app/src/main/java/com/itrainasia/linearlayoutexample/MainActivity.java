package com.itrainasia.linearlayoutexample;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, TextWatcher {

    EditText name;
    EditText email;
    Button send;
    Spinner spinner;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        spinner = (Spinner) findViewById(R.id.spinner);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autocomplete);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.profession, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> states_adapter = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_dropdown_item_1line);
        states_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setAdapter(states_adapter);

        autoCompleteTextView.addTextChangedListener(this);
        send.setOnClickListener(this);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.about_us:
                Toast.makeText(getApplication(),"About Us Selected",Toast.LENGTH_SHORT).show();
                Intent webIntent = new Intent(MainActivity.this, WebActivity.class);
                startActivity(webIntent);
                break;
            case R.id.email:
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Application Feedback");
                String[] emails = {"ryanoccg@gmail.com"};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, emails);
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Here is my feedback on the app");
                startActivity(emailIntent);
                break;
            case R.id.settings:
                Toast.makeText(getApplication(),"Settings Selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                Toast.makeText(getApplication(),"Bye Bye",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        String message = "Your name was "+name.getText().toString();
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();

        Intent newpage = new Intent(MainActivity.this, NewActivity.class);

        newpage.putExtra("name", name.getText().toString());
        newpage.putExtra("email", email.getText().toString());

        startActivity(newpage);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplication(),((TextView)view).getText().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d("debug","User type "+autoCompleteTextView.getText());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
