package com.itrainasia.mymaterial;

import android.support.design.widget.TabLayout;
import android.support.v4.app.*;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewParent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_search:
                Toast.makeText(getApplication(),"You have Click Search Button",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(getApplication(),"You have Click Settings Button",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:
                Toast.makeText(getApplication(),"You have Click Share Button",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about_us:
                Toast.makeText(getApplication(),"You have Click About Us Button",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager());
        adapter.addItem(new ListFragment(),"List View");
        adapter.addItem(new TileFragment(), "Tile View");
        adapter.addItem(new CardFragment(), "Card View");

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);

    }

    static class CustomAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<Fragment>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public CustomAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public void addItem(Fragment fragment, String title){
            mFragmentTitleList.add(title);
            mFragmentList.add(fragment);
        }
    }
}
