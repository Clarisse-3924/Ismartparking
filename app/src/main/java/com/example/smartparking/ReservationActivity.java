package com.example.smartparking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartparking.models.ReservationRequest;
import com.example.smartparking.models.ReservationResponse;
import com.example.smartparking.services.ApiClient;
import com.flutterwave.raveandroid.RaveUiManager;

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


}