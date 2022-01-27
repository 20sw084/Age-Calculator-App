package com.example.agecalculator; 
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    final Calendar myCalendar= Calendar.getInstance();
    EditText editText;
    EditText Year;
    EditText Month;
    EditText Day;
    Button calculateButton;
    String currDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText) findViewById(R.id.BirthDate);
        Year=(EditText) findViewById(R.id.editTextYear);
        Month=(EditText) findViewById(R.id.editTextMonth);
        Day=(EditText) findViewById(R.id.editTextDay);
        currDate=new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).format(new Date());
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        calculateButton = (Button) findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable fullDate = editText.getText();
                String date = String.valueOf(fullDate);
                String[] dob = date.split("/", 3);
                String[] currentDate = currDate.split("-", 3);
                currentDate[0] =Integer.toString(Integer.parseInt(currentDate[0])- 2000);


                if (Integer.parseInt(dob[2]) < Integer.parseInt(currentDate[0]) ) {
//                    if (Integer.parseInt(dob[0]) < Integer.parseInt(currentDate[1]) ) {
//                        if (Integer.parseInt(dob[1]) < Integer.parseInt(currentDate[2])) {
//                            int formatYear = (Integer.parseInt(dob[2]) - 2000);
                            Year.setText(Integer.toString((Integer.parseInt(currentDate[0]))-Integer.parseInt(dob[2])));
                            Month.setText(Integer.toString(Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                            Day.setText(Integer.toString(Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                           if((Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0]))<0){
                               Year.setText(Integer.toString(Integer.parseInt(currentDate[0])-Integer.parseInt(dob[2])-1));
                               Month.setText(Integer.toString(12+ Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                            }
                            if((Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1]))<0) {
                                if ((Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])) == 0) {
                                    Year.setText(Integer.toString(Integer.parseInt(currentDate[0])-Integer.parseInt(dob[2])-1));
                                    Month.setText(Integer.toString(11+Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                                    Day.setText(Integer.toString(30+Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                                }
                                else {
                                    Month.setText(Integer.toString(Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0]) - 1));
                                    Day.setText(Integer.toString(30 + Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                                }
                            }
//                        }
//                    }
                }
                else if (Integer.parseInt(dob[2]) > Integer.parseInt(currentDate[0]) ) {

//                    if (Integer.parseInt(dob[0]) < Integer.parseInt(currentDate[1]) ) {
//                        if (Integer.parseInt(dob[1]) < Integer.parseInt(currentDate[2])) {
                    Year.setText(Integer.toString(100-Integer.parseInt(dob[2])+Integer.parseInt(currentDate[0])));
                    Month.setText(Integer.toString(Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                    Day.setText(Integer.toString(Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                    if((Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0]))<0){
                        Year.setText(Integer.toString(Integer.parseInt(dob[2])+Integer.parseInt(currentDate[0])-1));
                        Month.setText(Integer.toString(12+ Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                    }
                    if((Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1]))<0) {
                        if ((Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])) == 0) {
                            Year.setText(Integer.toString(100-Integer.parseInt(dob[2])+Integer.parseInt(currentDate[0])));
                            Month.setText(Integer.toString(11+Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                            Day.setText(Integer.toString(30+Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                        }
                        else {
                            Month.setText(Integer.toString(Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0]) - 1));
                            Day.setText(Integer.toString(30 + Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                        }
                    }
//                        }
//                    }
                }
                else if (Integer.parseInt(dob[2]) == Integer.parseInt(currentDate[0]) ) {
                    if (Integer.parseInt(dob[0]) < Integer.parseInt(currentDate[1]) ) {
//                        if (Integer.parseInt(dob[1]) < Integer.parseInt(currentDate[2])) {
                    if (Integer.parseInt(currentDate[0]) > 1999) {
                        int formattedYear = (Integer.parseInt(currentDate[0]) - 2000);
                        Year.setText(Integer.toString(formattedYear - Integer.parseInt(dob[2])));
                    } else if (Integer.parseInt(currentDate[0]) > 1899){
                        int formatYear = (Integer.parseInt(dob[2]) - 1900);
                        Year.setText(Integer.toString(formatYear+Integer.parseInt(currentDate[0])));
                    }
                    Month.setText(Integer.toString(Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                    Day.setText(Integer.toString(Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                        if((Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0]))<0){
                            if (Integer.parseInt(currentDate[0]) > 1999) {
                                int formattedYear = (Integer.parseInt(currentDate[0]) - 2000);
                                Year.setText(Integer.toString(formattedYear - Integer.parseInt(dob[2])-1));
                            } else if (Integer.parseInt(currentDate[0]) > 1899){
                                int formatYear = (Integer.parseInt(dob[2]) - 1900);
                                Year.setText(Integer.toString(formatYear+Integer.parseInt(currentDate[0])));
                            }
                            Month.setText(Integer.toString(12+ Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                        }
                        if((Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1]))<0) {
                            if ((Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])) == 0) {
                                Month.setText(Integer.toString(Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                                Day.setText(Integer.toString(Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                            }
                            else {
                                Month.setText(Integer.toString(Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0]) - 1));
                                Day.setText(Integer.toString(30 + Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                            }
                        }
                    }
//                    }
                }
                else if (Integer.parseInt(dob[2]) == Integer.parseInt(currentDate[0]) ) {
                    if (Integer.parseInt(dob[0]) == Integer.parseInt(currentDate[1]) ) {
                        if (Integer.parseInt(dob[1]) < Integer.parseInt(currentDate[2])) {
                        if (Integer.parseInt(currentDate[0]) > 1999) {
                            int formattedYear = (Integer.parseInt(currentDate[0]) - 2000);
                            Year.setText(Integer.toString(formattedYear - Integer.parseInt(dob[2])));
                        } else if (Integer.parseInt(currentDate[0]) > 1899){
                            int formatYear = (Integer.parseInt(dob[2]) - 1900);
                            Year.setText(Integer.toString(formatYear+Integer.parseInt(currentDate[0])));
                        }
                        Month.setText(Integer.toString(Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                        Day.setText(Integer.toString(Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                            if((Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0]))<0){
                                if (Integer.parseInt(currentDate[0]) > 1999) {
                                    int formattedYear = (Integer.parseInt(currentDate[0]) - 2000);
                                    Year.setText(Integer.toString(formattedYear - Integer.parseInt(dob[2])-1));
                                } else if (Integer.parseInt(currentDate[0]) > 1899){
                                    int formatYear = (Integer.parseInt(dob[2]) - 1900);
                                    Year.setText(Integer.toString(formatYear+Integer.parseInt(currentDate[0])));
                                }
                                Month.setText(Integer.toString(12+ Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                            }
                            if((Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1]))<0) {
                                if ((Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])) == 0) {
                                    Month.setText(Integer.toString(Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                                    Day.setText(Integer.toString(Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                                }
                                else {
                                    Month.setText(Integer.toString(Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0]) - 1));
                                    Day.setText(Integer.toString(30 + Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                                }
                            }
                        }
                    }
                }
                else if (Integer.parseInt(dob[2]) == Integer.parseInt(currentDate[0]) ) {
                    if (Integer.parseInt(dob[0]) == Integer.parseInt(currentDate[1]) ) {
                        if (Integer.parseInt(dob[1]) >= Integer.parseInt(currentDate[2])) {
                            if (Integer.parseInt(currentDate[0]) > 1999) {
                                int formattedYear = (Integer.parseInt(currentDate[0]) - 2000);
                                Year.setText(Integer.toString(formattedYear - Integer.parseInt(dob[2])));
                            } else if (Integer.parseInt(currentDate[0]) > 1899){
                                int formatYear = (Integer.parseInt(dob[2]) - 1900);
                                Year.setText(Integer.toString(formatYear+Integer.parseInt(currentDate[0])));
                            }
                            Month.setText(Integer.toString(Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                            Day.setText(Integer.toString(Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                            if((Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0]))<0){
                                if (Integer.parseInt(currentDate[0]) > 1999) {
                                    int formattedYear = (Integer.parseInt(currentDate[0]) - 2000);
                                    Year.setText(Integer.toString(formattedYear - Integer.parseInt(dob[2])-1));
                                } else if (Integer.parseInt(currentDate[0]) > 1899){
                                    int formatYear = (Integer.parseInt(dob[2]) - 1900);
                                    Year.setText(Integer.toString(formatYear+Integer.parseInt(currentDate[0])));
                                }
                                Month.setText(Integer.toString(12+ Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                            }
                            if((Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1]))<0) {
                                if ((Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])) == 0) {
                                    Month.setText(Integer.toString(Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0])));
                                    Day.setText(Integer.toString(Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                                }
                                else {
                                    Month.setText(Integer.toString(Integer.parseInt(currentDate[1]) - Integer.parseInt(dob[0]) - 1));
                                    Day.setText(Integer.toString(30 + Integer.parseInt(currentDate[2]) - Integer.parseInt(dob[1])));
                                }
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "BirthDate should atleast be larger then 1 Day!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

        });
    }
    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(myCalendar.getTime()));
    }
}
