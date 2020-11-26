package com.example.rateapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;

public class MainActivity extends AppCompatActivity {

    EditText ResName;
    EditText ResType;
    EditText ResAddress;
    EditText ResHotline;
    EditText AvgPrice;
    EditText Reporter;
    EditText resVisitDateTime;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ResName = findViewById(R.id.ResName);
        ResType = findViewById(R.id.ResType);
        ResAddress = findViewById(R.id.Address);
        ResHotline = findViewById(R.id.Hotline);
        AvgPrice = findViewById(R.id.AvgPrice);
        Reporter = findViewById(R.id.Reporter);

        // Calendar
        resVisitDateTime = findViewById(R.id.DateTime);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        resVisitDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        resVisitDateTime.setText(date);
                    }
                },day,month,year);
                datePickerDialog.show();
            }
        });

        // Validate Type Data
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.ResName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.resNameError);
        awesomeValidation.addValidation(this, R.id.ResType, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.resTypeError);
        awesomeValidation.addValidation(this, R.id.Hotline, "^[0-9]{2}[0-9]{8}$", R.string.resHotlineError);
        awesomeValidation.addValidation(this, R.id.DateTime, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.resDateError);
        awesomeValidation.addValidation(this, R.id.Reporter, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.resReporterError);



    }
    boolean validateInputNull() {
        String resName, resType, Address, Hotline, DateTime, AveragePrice, NameReporter;
        resName = ResName.getText().toString();
        resType = ResType.getText().toString();
        Address = ResAddress.getText().toString();
        Hotline = ResHotline.getText().toString();
        DateTime = resVisitDateTime.getText().toString();
        AveragePrice = AvgPrice.getText().toString();
        NameReporter = Reporter.getText().toString();

        boolean result = true;
        if(resName.length() <= 0 ){
            result = false;
            ResName.setError("Enter Restaurant Name!!");
        }
        if(resType.length() <= 0 ){
            result = false;
            ResType.setError("Enter Restaurant Type!!");
        }
        if(Address.length() <= 0 ){
            result = false;
            ResAddress.setError("Enter Address!!");
        }
        if(Hotline.length() < 8) {
            result = false;
            ResHotline.setError("Hotline must be more than 8 characters!!");
        }
        if(AveragePrice.length() <= 0) {
            result = false;
            AvgPrice.setError("Enter Average meal price person!!");
        }
        if(NameReporter.length() <= 0) {
            result = false;
            Reporter.setError("Enter name of the reporter!!");
        }
        return result;
    }
    public void onSubmit(View view){
        if(awesomeValidation.validate() || validateInputNull()){
            Toast.makeText(this, "Feedback Successfull", Toast.LENGTH_LONG).show();
        }
    }
}