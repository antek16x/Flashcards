package com.example.zadanie_15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private static ViewPagerFragment[] tabFragm;
    private Button sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TypedArray words = getResources().obtainTypedArray(R.array.nazwa_tablicy);
        tabFragm = new ViewPagerFragment[words.length()];
        for (int i = 0; i < tabFragm.length; i++) {
            int random = new Random().nextInt(Integer.valueOf("ffffff", 16));
            String color = "000000" + Integer.toHexString(random);
            color = "#" + color.substring(color.length() - 6);

            String plWord = words.getString(i);
            int index = plWord.indexOf(":");
            String enWord = plWord.substring(index + 1);
            plWord = plWord.substring(0, index);

            tabFragm[i] = new ViewPagerFragment(color, plWord, enWord);
        }
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        sum = (Button) findViewById(R.id.sum);
        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int howManyCorrect = 0;
                for (int i = 0; i < tabFragm.length; i++) {
                    if (tabFragm[i].isCorrect()) {
                        howManyCorrect++;
                    }
                }
                Toast.makeText(getApplicationContext(),
                        "The correct answers are " + howManyCorrect + " out of "
                                + tabFragm.length, Toast.LENGTH_LONG).show();
            }
        });
    }

    //================================================================================

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return tabFragm[position];
        }

        @Override
        public int getCount() {
            return tabFragm.length;
        }
    }
}