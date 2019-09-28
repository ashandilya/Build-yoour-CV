package com.ashandilya.android.get_your_cv;

import android.content.DialogInterface;
import android.content.Intent;

import com.example.shiraz.get_your_cv.R;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login2Activity extends AppCompatActivity {
    EditText et1;
    TextInputEditText et2;
    Button btn1,btn2;
    String s1,s2;

    String url="https://app-1533963054.000webhostapp.com/JIS/signup.php";


    RequestQueue requestQueue;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        et1=(EditText) findViewById(R.id.et1);
        et2= (TextInputEditText) findViewById(R.id.et2);
        btn1=(Button) findViewById(R.id.btn1);
        btn2=(Button) findViewById(R.id.btn2);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
    }

    public void click(View view) {
        s1=et1.getText().toString();
        s2=et2.getText().toString();

        stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showMsg("Server Response",response);
//                        tv.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showMsg("Server Response","Page not reachable");
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put("Username",s1);
                params.put("Password",s2);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void showMsg(String title, String msg){
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(Login2Activity.this);

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
        AlertDialog dialog=mBuilder.create();
        dialog.show();
    }

    public void coming(View view) {
        Intent intent1 = new Intent(Login2Activity.this, LoginActivity.class);
        startActivity(intent1);
    }
}


