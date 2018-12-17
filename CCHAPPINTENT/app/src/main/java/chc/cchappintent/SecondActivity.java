package chc.cchappintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        TextView textView = findViewById(R.id.textView);
        String message = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        textView.setText(message);
    }
}
