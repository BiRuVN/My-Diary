package com.example.mydiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import yuku.ambilwarna.AmbilWarnaDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

import java.util.Calendar;

public class PostActivity extends AppCompatActivity
{
    BottomNavigationView bottomNavigationView;
    TextView txtDate,txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    MaterialCardView materialCardView;
    int DefaultColor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_new_content);

        bottomNavigationView=findViewById(R.id.bottom_bar_view);
        txtDate=findViewById(R.id.post_edit_dates);
        txtTime=findViewById(R.id.post_edit_time);
        DefaultColor = ContextCompat.getColor( this,R.color.colorWhite);
        materialCardView=findViewById(R.id.post_edit_card_container);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setCheckable(true);
                switch (menuItem.getItemId()) {
                    case R.id.date_picker:
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(PostActivity.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {
                                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                        break;
                    case R.id.time_picker:
                        final Calendar cld = Calendar.getInstance();
                        mHour = cld.get(Calendar.HOUR_OF_DAY);
                        mMinute = cld.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(PostActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {
                                        txtTime.setText(hourOfDay + ":" + minute);
                                    }
                                }, mHour, mMinute, false);
                        timePickerDialog.show();
                        break;
                    case R.id.post_bg_color:
                        AmbilWarnaDialog dialog = new AmbilWarnaDialog(
                                PostActivity.this,DefaultColor , new AmbilWarnaDialog.OnAmbilWarnaListener() {
                            @Override
                            public void onOk(AmbilWarnaDialog dialog, int color) {
                                DefaultColor = color;
                                materialCardView.setBackgroundColor(color);
                            }
                            @Override
                            public void onCancel(AmbilWarnaDialog dialog) {
                            }
                        });
                        dialog.show();
                        break;
                    case R.id.delete_post:
                        break;
                }
                return true;
            }
        });
    }


}