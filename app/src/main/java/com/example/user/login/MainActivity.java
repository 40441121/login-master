package com.example.user.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText loginid,password;
    Button submit;
    CheckBox cb1,cb2;

    String myLoginid, myPassword;
    Boolean bl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            findview();
            init();

    }


    public void init(){
        SharedPreferences setting = getSharedPreferences("login", MODE_PRIVATE);

        myLoginid = setting.getString("loginid", "");
        myPassword=setting.getString("password", "");


    }

    public void findview(){
        loginid = (EditText)findViewById(R.id.loginid);
        password = (EditText)findViewById(R.id.password);
        submit = (Button)findViewById(R.id.submit);
        cb1=(CheckBox)findViewById(R.id.cb1);
        cb2=(CheckBox)findViewById(R.id.cb2);

        loginid.setText(myLoginid);
        password.setText(myPassword);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }
        });
    }

    public void doSubmit(){


        if(loginid.getText().toString().equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("登入失敗")
                    .setMessage("帳號不得為空")
                    .setPositiveButton("ok",null)
                    .show();

            loginid.setFocusableInTouchMode(true);
            loginid.requestFocus();

        }else if (password.getText().toString().equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("登入失敗")
                    .setMessage("密碼不得為空")
                    .setPositiveButton("ok",null)
                    .show();

            password.setFocusableInTouchMode(true);
            password.requestFocus();

        }else {
            cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if (isChecked)
                        bl=true;
                    else
                        bl=false;
                }
            });




            Intent intent = new Intent(this, CheckActivity.class);

            Bundle bag = new Bundle();
            bag.putString("loginid", loginid.getText().toString());
            bag.putString("password", password.getText().toString());
            bag.putBoolean("KEY_BOOL",bl);







            intent.putExtras(bag);

            startActivity(intent);





        }
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    SharedPreferences setting = getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences.Editor edit=setting.edit();
                    edit.putString("loginid", loginid.getText().toString());
                    edit.putString("password", password.getText().toString());
                    edit.commit();
                }
                if(!isChecked)
                {

                    SharedPreferences setting = getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences.Editor edit=setting.edit();
                    edit.putString("loginid", "");
                    edit.putString("password", "");
                    edit.commit();
                }
            }
        });



    }

}
