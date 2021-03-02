package com.example.smartparking;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.smartparking.models.ReservationRequest;
import com.example.smartparking.models.ReservationResponse;
import com.example.smartparking.services.ApiClient;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RaveUiManager;
import com.flutterwave.raveandroid.rave_java_commons.RaveConstants;

import java.sql.Time;
import java.text.DateFormat;
import java.time.Duration;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.editTextEntryDate)
    EditText entryDate;
    @BindView(R.id.editTextExitTime)
    EditText exitTime;
    @BindView(R.id.editTextEntryTime)
    EditText entryTime;
    @BindView(R.id.plateNumberEditText)
    EditText plateNumber;
    @BindView(R.id.editTextDuration)
    EditText duration;
    @BindView(R.id.editTextLocation)
    EditText location;
    @BindView(R.id.reservationButton)
    Button reservationButton;
    private int mHour, mMinute,mSecond,mYear, mMonth, mDay;

    Time diff;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        ButterKnife.bind(this);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        entryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReservationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String sDate=year+ "-"+(monthOfYear + 1)+"-"+dayOfMonth;
                                entryDate.setText(sDate);
                                //entryDate.setText(year+ "-"+(monthOfYear + 1)+"-"+dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });

        entryTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                mSecond=c.get(Calendar.SECOND);
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(ReservationActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                if((hourOfDay <= (c.get(Calendar.HOUR_OF_DAY)))&&
                                        (minute <= (c.get(Calendar.MINUTE)))){
                                    Toast.makeText(ReservationActivity.this, "You can't pick a previous hour",
                                            Toast.LENGTH_SHORT).show();
                                }else {
                                    entryTime.setText(hourOfDay + ":" + minute + ":" + mSecond);
                                }
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        exitTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                mSecond=c.get(Calendar.SECOND);
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(ReservationActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                exitTime.setText(hourOfDay + ":" + minute + ":" + mSecond);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });


        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReservation(addReservation());
            }
        });


    }

    public ReservationRequest addReservation(){
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setUser_id(1);
        reservationRequest.setParking_slot_id(1);
        reservationRequest.setBooking_date(entryDate.getText().toString());
        reservationRequest.setEntry_time(entryTime.getText().toString());
        reservationRequest.setExit_time(exitTime.getText().toString());
        reservationRequest.setPlate_No(Integer.parseInt(plateNumber.getText().toString()));
        reservationRequest.setLocation(Integer.parseInt(location.getText().toString()));
        reservationRequest.setDuration_in_minutes(Integer.parseInt(duration.getText().toString()));

        return reservationRequest;
    }

    public void saveReservation(ReservationRequest reservationRequest){

        Call<ReservationResponse> reservationResponseCall = ApiClient.getReservationService().saveReservation(reservationRequest);
        reservationResponseCall.enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {

                if (response.isSuccessful()){
                    Toast.makeText(ReservationActivity.this, "Booking successful", Toast.LENGTH_LONG).show();

                    new RaveUiManager(ReservationActivity.this).setAmount(100)
                            .setCurrency("RWF")
                            .setEmail("ubelyse1@gmail.com")
                            .setfName("Belyse")
                            .setlName("Uwambayinema")
                            .setNarration("narration")
                            .setPublicKey("FLWPUBK-153c54964b77d3382386e19706048494-X")
                            .setEncryptionKey("1a59522dfb665a5bd9e0c0e2")
                            .setTxRef("txRef")
                            .setPhoneNumber("+250787905576", true)
                            .acceptAccountPayments(false)
                            .acceptCardPayments(true)
                            .acceptMpesaPayments(false)
                            .acceptAchPayments(false)
                            .acceptGHMobileMoneyPayments(false)
                            .acceptUgMobileMoneyPayments(false)
                            .acceptZmMobileMoneyPayments(false)
                            .acceptRwfMobileMoneyPayments(true)
                            .acceptSaBankPayments(false)
                            .acceptUkPayments(false)
                            .acceptBankTransferPayments(false)
                            .acceptUssdPayments(false)
                            .acceptBarterPayments(false)
                            .acceptFrancMobileMoneyPayments(false)
                            .allowSaveCardFeature(false)
                            .onStagingEnv(false)
                            .withTheme(R.style.MyCustomTheme)
                            .initialize();
                }else{
                    Toast.makeText(ReservationActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ReservationResponse> call, Throwable t) {

                Toast.makeText(ReservationActivity.this, "Booking unsuccessful"+ t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String date =year + "-" + month + "-" + dayOfMonth;
        EditText entryDate = (EditText) findViewById(R.id.editTextEntryDate);
        entryDate.setText(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Toast.makeText(this, "SUCCESS " + message, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReservationActivity.this, ThankYouActivity.class);
                startActivity(intent);
                finish();
            }
            else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(this, "ERROR " + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "CANCELLED " + message, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}