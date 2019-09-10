package com.example.shiraz.get_your_cv;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListviewActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ListView listView;
    String[] p_type;
    String[] l_names = {"Chronological_CV", "Functional_CV", "Combination_CV"};
    MyCustomAdapter ca;

    int[] imageos = {
            R.drawable.cv_chronological,
            R.drawable.functional_cv,
            R.drawable.combination_resumes,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initView();

//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle(getResources().getString(R.string.app_name));
        listView = (ListView) findViewById(R.id.lv);
        ca = new MyCustomAdapter(l_names, imageos, this);
        listView.setAdapter(ca);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:

                        Intent intent = new Intent(ListviewActivity.this, Image1Activity.class);

                        startActivity(intent);
                        break;
                    case 1:

                        Intent intent1 = new Intent(ListviewActivity.this, Image2Activity.class);

                        startActivity(intent1);
                        break;
                    case 2:

                        Intent intent2 = new Intent(ListviewActivity.this, Image3Activity.class);

                        startActivity(intent2);
                        break;
                }
            }
        });
    }
    private void initView() {
        toolbar=(androidx.appcompat.widget.Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);//function convert tool bar to action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case android.R.id.home:
                Intent intent = new Intent(ListviewActivity.this,DrawerActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
