package com.example.b3tempoappzaher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.b3tempoappzaher.databinding.ActivityMainBinding;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String RED_TEMPO_ALERT_CHANNEL_ID = "red_tempo_alert_channel_id";
    public static final String WHITE_TEMPO_ALERT_CHANNEL_ID = "white_tempo_alert_channel_id";
    public static final String BLUE_TEMPO_ALERT_CHANNEL_ID = "blue_tempo_alert_channel_id";

    private ActivityMainBinding binding;
    public static IEdfApi edfApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Retrofit retrofitClient = ApiClient.get();
        if (retrofitClient != null) {
            edfApi = retrofitClient.create(IEdfApi.class);
        } else {
            Log.e(LOG_TAG, "unable to initialize Retrofit client");
            finish();
        }

        Call<TempoDaysLeft> call = edfApi.getTempoDaysLeft(IEdfApi.EDF_TEMPO_API_ALERT_TYPE);

        call.enqueue(new Callback<TempoDaysLeft>() {
            @Override
            public void onResponse(@NonNull Call<TempoDaysLeft> call, @NonNull Response<TempoDaysLeft> response) {
                TempoDaysLeft tempoDaysLeft = response.body();
                if (response.code() == HttpURLConnection.HTTP_OK && tempoDaysLeft != null) {
                    Log.d(LOG_TAG, "nb red days = " + tempoDaysLeft.getParamNbJRouge());
                    Log.d(LOG_TAG, "nb white days = " + tempoDaysLeft.getParamNbJBlanc());
                    Log.d(LOG_TAG, "nb blue days = " + tempoDaysLeft.getParamNbJBleu());
                    binding.blueDaysTv.setText(String.valueOf(tempoDaysLeft.getParamNbJBleu()));
                    binding.whiteDaysTv.setText(String.valueOf(tempoDaysLeft.getParamNbJBlanc()));
                    binding.redDaysTv.setText(String.valueOf(tempoDaysLeft.getParamNbJRouge()));
                } else {
                    Log.w(LOG_TAG, "call to getTempoDaysLeft() failed with error code " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TempoDaysLeft> call, @NonNull Throwable t) {
                Log.e(LOG_TAG, "call to getTempoDaysLeft () failed ");
            }
        });

        Call<TempoDaysColor> call2 = edfApi.getTempoDaysColor("2024-03-05", IEdfApi.EDF_TEMPO_API_ALERT_TYPE);

        call2.enqueue(new Callback<TempoDaysColor>() {

            @Override
            public void onResponse(@NonNull Call<TempoDaysColor> call, @NonNull Response<TempoDaysColor> response) {
                TempoDaysColor tempoDaysColor = response.body();
                if (response.code() == HttpURLConnection.HTTP_OK && tempoDaysColor != null) {
                    Log.d(LOG_TAG, "Today color = " + tempoDaysColor.getCouleurJourJ());
                    Log.d(LOG_TAG, "Tomorrow color = " + tempoDaysColor.getCouleurJourJ1());
                } else {
                    Log.w(LOG_TAG, "call to getTempoDaysColor() failed with error code " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TempoDaysColor> call, @NonNull Throwable t) {
                Log.e(LOG_TAG, "call to getTempoDaysColor() failed ");
            }
        });
    }
    public void showHome(View view) {
        finish();
    }
    public void showHistory(View view) {
        Intent intent = new Intent();
        intent.setClass(this,HistoryActivity.class);
        startActivity(intent);
    }

    private void createNotificationChannels() {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        List<NotificationChannel> channels = new ArrayList<>();

        NotificationChannel channel = new NotificationChannel(
                BLUE_TEMPO_ALERT_CHANNEL_ID,
                getString(R.string.blue_channel_name),
                importance);
        channel.setDescription(getString(R.string.blue_channel_description));
        channels.add(channel);

        channel = new NotificationChannel(
                WHITE_TEMPO_ALERT_CHANNEL_ID,
                getString(R.string.white_channel_name),
                importance);
        channel.setDescription(getString(R.string.white_channel_description));
        channels.add(channel);

        channel = new NotificationChannel(
                RED_TEMPO_ALERT_CHANNEL_ID,
                getString(R.string.red_channel_name),
                importance);
        channel.setDescription(getString(R.string.red_channel_description));
        channels.add(channel);

        // Register the channels with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannels(channels);

    }
    private void sendColorNotification(){
        Log.d(LOG_TAG, "sendColorNotification()");
    }
    @Override
    public void onClick(View v) {

    }
}