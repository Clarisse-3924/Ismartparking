package com.example.smartparking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartparking.models.ReservationRequest;
import com.example.smartparking.models.ReservationResponse;
import com.example.smartparking.services.ApiClient;

import java.sql.Time;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        ButterKnife.bind(this);

        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReservation(addReservation());
            }
        });
    }

    public ReservationRequest addReservation(){
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setBooking_date(entryDate.getText().toString());
        reservationRequest.setEntry_time(Time.valueOf(entryTime.getText().toString()));
        reservationRequest.setExit_time(Time.valueOf(exitTime.getText().toString()));
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
}