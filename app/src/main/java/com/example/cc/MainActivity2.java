package com.example.cc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity2 extends AppCompatActivity  {
    public Button button;
    public TextInputEditText _email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        _email=findViewById(R.id.email);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
        getSupportActionBar().setTitle("REGISTER");
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                open();
            }
        });
    }
    public boolean openActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        return false;
    }


public boolean validName(TextInputEditText _email)
{
    String emailInput = _email.getText().toString().trim();


    if (emailInput.isEmpty()) {
        _email.setError("Field Empty");
        return false;
    } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
        _email.setError("Pleas enter valid email add");
        return false;
    }
    else {
        return  true;
    }
}

    public boolean open()
    {
        if(validName(_email))
        {
            return openActivity();
        }
        else {
            return false;
        }

    }
    }
