package com.takuchan.ros2viassh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class MainActivity extends AppCompatActivity {

    Button connectButton;
    EditText addressEdit,passwordEdit,userNameEdit;

    String address,password,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectButton = (Button)findViewById(R.id.button);

        addressEdit = (EditText)findViewById(R.id.address_edittext);
        passwordEdit = (EditText)findViewById(R.id.password_edittext);
        userNameEdit = (EditText)findViewById(R.id.username_edittext);



        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SSHConnection task = new SSHConnection(userNameEdit.getText().toString(),passwordEdit.getText().toString(),addressEdit.getText().toString());
                task.execute();
            }
        });

    }
}