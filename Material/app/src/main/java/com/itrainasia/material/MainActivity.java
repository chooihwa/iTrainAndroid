package com.itrainasia.material;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TabLayout tabs = findViewById(R.id.tabs);
        /*tabs.addTab(tabs.newTab().setText("Germany"));
        tabs.addTab(tabs.newTab().setText("Paris"));
        tabs.addTab(tabs.newTab().setText("Switzerland"));*/

        setSupportActionBar(toolbar);
        //TabLayout tabs = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.view_pager);

        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment1A(),"Germany");
        adapter.addFragment(new Fragment1A(),"Paris");
        adapter.addFragment(new Fragment1A(),"Switzerland");

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }
}
