package com.itrainasia.flipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    float lastKnownX;
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper = findViewById(R.id.vFlipper);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                lastKnownX = event.getX();
                break;
            }
            case MotionEvent.ACTION_UP:{
                float currentX = event.getX();
                if(lastKnownX < currentX){
                    if (viewFlipper.getDisplayedChild()==0)
                        break;
                    viewFlipper.setInAnimation(this, R.anim.from_left);
                    viewFlipper.setOutAnimation(this, R.anim.to_right);
                    viewFlipper.showNext();
                }
                if(lastKnownX > currentX){
                    if(viewFlipper.getDisplayedChild()==1)
                        break;
                    viewFlipper.setInAnimation(this, R.anim.from_right);
                    viewFlipper.setOutAnimation(this, R.anim.to_left);
                    viewFlipper.showPrevious();
                }
            }
        }
        return false;
    }
}
