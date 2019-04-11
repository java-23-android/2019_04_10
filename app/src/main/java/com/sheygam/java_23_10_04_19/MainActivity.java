package com.sheygam.java_23_10_04_19;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements MyFragment.Callback {
    int count = 0;
    float scale = 1.0F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int color() {
        Random rnd = new Random();
        int r = rnd.nextInt(256);
        int g = rnd.nextInt(256);
        int b = rnd.nextInt(256);
        return Color.rgb(r, g, b);
    }

    public void add(View view) {
        if (count == 2) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, MyFragment.instance(scale, color()), "FOR_REMOVE")
                    .addToBackStack(null)
                    .commit();
            count++;
        } else {
//            MyFragment fragment = new MyFragment();
            MyFragment fragment = MyFragment.instance(scale, color());
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.addToBackStack(null);
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();
            count++;
        }
        scale -= 0.1;
    }

    public void detach(View view) {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentByTag("FOR_REMOVE");
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .detach(fragment)
                    .commit();
        }
    }

    public void attach(View view) {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentByTag("FOR_REMOVE");
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .attach(fragment)
                    .commit();
        }
    }

    public void replace(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, MyFragment.instance(scale, color()))
                .commit();
        scale -= 0.1;
    }

    public void remove(View view) {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentByTag("FOR_REMOVE");
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragment)
                    .commit();
        }

    }

    @Override
    public void onBtnClicked() {
        Toast.makeText(this, "Click callback", Toast.LENGTH_SHORT).show();
    }
}
