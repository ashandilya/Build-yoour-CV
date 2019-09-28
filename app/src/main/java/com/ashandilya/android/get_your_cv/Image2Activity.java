package com.ashandilya.android.get_your_cv;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.shiraz.get_your_cv.R;

public class Image2Activity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image2);
        initView();
        imageView = findViewById(R.id.functional);

    }

    private void initView() {
        toolbar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);//function convert tool bar to action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case android.R.id.home:
                Intent intent = new Intent(Image2Activity.this,ListviewActivity.class);
                startActivity(intent);
            case R.id.samplecv:
                Intent intent1 = new Intent(Image2Activity.this,ListviewActivity.class);
                startActivity(intent1);
                break;
            case R.id.abc:
                Intent intent2 = new Intent(Image2Activity.this,DrawerActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


