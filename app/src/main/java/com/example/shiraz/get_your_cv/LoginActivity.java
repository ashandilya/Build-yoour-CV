package com.example.shiraz.get_your_cv;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button btn1,btn2;
    EditText et1;
    TextInputEditText et2;
    String s1,s2;
    ProgressDialog pDialog;

    String url = "https://app-1533963054.000webhostapp.com/JIS/CV.php";
    RequestQueue requestQueue;
    StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        et1=(EditText) findViewById(R.id.et1);
        et2= (TextInputEditText) findViewById(R.id.et2);
        pDialog=new ProgressDialog(this);
        pDialog.setMessage("wait until .. it gets saved");
        requestQueue = Volley.newRequestQueue(getApplicationContext());

    }
    public void login(View view) {
        s1=et1.getText().toString();
        s2=et2.getText().toString();


        stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                        if(response.equals("success")) {
                            Intent intent=new Intent(LoginActivity.this, DrawerActivity.class);
                            startActivity(intent);
                        }else{
                            showMsg("Server Response","Invalid User");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.hide();
                        showMsg("Server Response","Page not reachable");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("Username",s1);
                params.put("Password",s2);
                return params;

            }
        };
        requestQueue.add(stringRequest);
        pDialog.show();
    }

    public void showMsg(String title, String msg){
        android.app.AlertDialog.Builder mBuilder=new android.app.AlertDialog.Builder(LoginActivity.this);
        mBuilder.setTitle(title);
        mBuilder.setMessage(msg);
        mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                et1.setText("");
                et2.setText("");
                et1.requestFocus();
                dialog.dismiss();
            }
        });
        android.app.AlertDialog dialog=mBuilder.create();
        dialog.show();
    }





    public void newuser(View view) {
        Intent intent1 = new Intent(LoginActivity.this, Login2Activity.class);
        startActivity(intent1);
    }

}


