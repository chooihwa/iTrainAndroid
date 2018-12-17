package chc.cchappintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "";

    EditText editText;
    Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText1);
        button1 = findViewById(R.id.button1);
    }


    /*
     * Our onClick method from the button in the xml file
     */
    public void passData(View v) {
        /*
         * Create an intent to bind from MainActivity to SecondActivity
         */
        Intent intent = new Intent(this, SecondActivity.class);
        /*
         * Get our EditText by it's ID
         */
        EditText editText1 = findViewById(R.id.editText1);

        intent.putExtra(EXTRA_MESSAGE, editText1.getText().toString());
        startActivity(intent);
    }


    public void openLink(View v) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share my link");
        intent.putExtra(Intent.EXTRA_TEXT, "http://itrainasia.com");

    }

    public void withChooser(View v) {


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share my link");
        intent.putExtra(Intent.EXTRA_TEXT, "http://itrainasia.com");
        String message = "Hello This is a toast!";
        startActivity(Intent.createChooser(intent, "How would you want to share?"));
    }

    public void showToast(View v) {
        //String message = "Hello This is a toast!";
        //Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        View layout = getLayoutInflater().inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = layout.findViewById(R.id.toasttext);
        text.setText("Custom text");

        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void showDialog(View v){
        new AlertDialog.Builder (this)
            .setTitle("Dialog")
            .setMessage("Warning, you will lost date")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Positive", Toast.LENGTH_SHORT).show();
                }
            })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Negative", Toast.LENGTH_SHORT).show();
                    }
                    

                })

                .setNeutralButton("back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(MainActivity.this, "Back", Toast.LENGTH_SHORT).show();
                    }
                })

                .show();

    }

    public void showNotification (View v) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        String id = "1234";
        NotificationManager mgr = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel mChannel = mgr.getNotificationChannel(id);
        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (mChannel == null)
            mChannel = new NotificationChannel(id, "Intent App", importance);
            mgr.createNotificationChannel(mChannel);
         NotificationCompat.Builder builder =
                 new NotificationCompat.Builder(getApplicationContext(),id);
         PendingIntent pi = PendingIntent.getActivity(getApplication(),0,
                 new Intent(getApplication(), MainActivity.class), 0);

                 builder.setAutoCancel(true)
                 .setContentIntent(pi)
             .setContentTitle("Intent App")
                     .setContentText("You have a notification")
                     .setSmallIcon(R.drawable.android)
                     .setTicker("Status message!")
                     .setWhen(System.currentTimeMillis());

             mgr.notify(0, builder.build());

    }



















    }



    }