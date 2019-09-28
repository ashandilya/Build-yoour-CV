package com.ashandilya.android.get_your_cv;

import android.content.Intent;
import androidx.annotation.NonNull;

import com.example.shiraz.get_your_cv.R;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DrawerActivity extends AppCompatActivity {
    Button cv_btn;
    Toolbar  toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        setupToolbar();//this function given by me.
        navigationView=findViewById(R.id.navigation_view);
        menuItemClick();

    }

    private void menuItemClick() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.nav_setting:
                        toast("Setting");
                        break;
                    case R.id.user_Profile:
                        toast("User");
                        break;
                    case R.id.samplecv:
                        Intent intent = new Intent(DrawerActivity.this,ListviewActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.home:
                        Intent intent1 = new Intent(DrawerActivity.this,DrawerActivity.class);
                        startActivity(intent1);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private void setupToolbar() {
        toolbar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        toggle=new ActionBarDrawerToggle(DrawerActivity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    public void toast(String item){
        Toast.makeText(DrawerActivity.this,item,Toast.LENGTH_LONG).show();
    }
    public void createcv(View view){
        Intent intent2 = new Intent(DrawerActivity.this,PdfActivity.class);
        startActivity(intent2);
    }
}

