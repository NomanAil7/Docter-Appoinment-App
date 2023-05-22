package com.example.cc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail. Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet. InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import java.util.Properties;
import java.util.regex.Pattern;

public class Activity2 extends AppCompatActivity {
    private static final Pattern PHONE=
            Pattern.compile(".{10,}");
    String[] items = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
    String[] itemss = {"9AM - 9.30AM", "9.30AM - 10AM","10AM - 10.30AM","10.30AM - 11AM","11AM - 11.30AM","11.30AM - 12PM","12PM - 12.30 PM"};
    AutoCompleteTextView autoCompleteText;
    AutoCompleteTextView AutoCompleteText;
    ArrayAdapter<String> adapterItems;

    TextInputEditText _Name,_Phone,_Age,_Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        autoCompleteText = findViewById(R.id.auto_complete_txt);
        _Name=findViewById(R.id.Name);
        _Phone=findViewById(R.id.Phone);
        _Age=findViewById(R.id.Age);
       _Add=findViewById(R.id.Add);

        adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown, items);
        autoCompleteText.setAdapter(adapterItems);

        autoCompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), "Group" + item, Toast.LENGTH_SHORT).show();
            }

        });
        AutoCompleteText = findViewById(R.id.auto_complete_txet);

        adapterItems = new ArrayAdapter<String>(this, R.layout.time, itemss);
        AutoCompleteText.setAdapter(adapterItems);

        AutoCompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), "Time" + item, Toast.LENGTH_SHORT).show();
            }

        });

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
        getSupportActionBar().setTitle("PATIENT FORM");
    }

    public void buttonSendEmail(View view) {

        String stringSenderEmail = "nomansk7744@gmail.com";
        String stringReceiverEmail ="psakharkar7@gmail.com";
        String stringPasswordSenderEmail = "qkigmxpkusypdxvx";
        String stringHost = "smtp.gmail.com";
        String Name = _Name.getText().toString();
        if(Name.isEmpty())
        {
            _Name.setError("Can Not Be Empty");
        }
        String Phone = _Phone.getText().toString();
        if(!PHONE.matcher(Phone).matches()) {
            _Phone.setError("10 digit And Also Can Not Be Empty");
        }

        String Age = _Age.getText().toString();
        if(Age.isEmpty())
        {
            _Age.setError("Can Not Be Empty");
        }

        String Group = autoCompleteText.getText().toString();
        if(Group.isEmpty())
        {
            autoCompleteText.setError("Can Not Be Empty");
        }
        String Time = AutoCompleteText.getText().toString();
        if(Time.isEmpty()) {
            AutoCompleteText.setError("Can Not Be Empty");
        }
        String Add = _Add.getText().toString();
        if(Add.isEmpty()) {
            _Add.setError("Can Not Be Empty");
        }
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.host", stringHost);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
            }
        });
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));
            mimeMessage.setSubject("Appointment has been fixed");
            mimeMessage.setText("Name :" + Name + "\n Phone:" + Phone + "\n Age:" + Age + "\n Blood :" + Group + "\n Time :" + Time + "\n Address :" + Add);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Transport.send(mimeMessage);
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                Toast.makeText(this, "SUBMITED", Toast.LENGTH_SHORT).show();
            }
        catch (AddressException e)
        {
            e.printStackTrace();
        }
        catch (MessagingException e) {

            e.printStackTrace();
        }

    }

}