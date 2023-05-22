package com.example.cc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
private Button button;

TextView textView;
public TextInputEditText _email ,_Name;
    private boolean add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _email = findViewById(R.id.email);
        _Name = findViewById(R.id.Name);
        textView=(TextView)findViewById(R.id.txt_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
        getSupportActionBar().setTitle("LOGIN");
button = (Button) findViewById(R.id.button);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //openActivity2();
        open();


    }
});

    }
    public boolean openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
        return false;
    }
    public boolean validateEmailAdd(TextInputEditText _email , TextInputEditText _Name) {

        String nameInput = _Name.getText().toString().trim();
        if (nameInput.isEmpty()) {
            _Name.setError("Field Empty");
            return false;
        }
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
        if(validateEmailAdd(_email,_Name)  )
        {
            return openActivity2();
        }
        else {
            return false;
        }

    }
    }
