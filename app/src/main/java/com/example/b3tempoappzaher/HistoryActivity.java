package com.example.b3tempoappzaher;

import static com.example.b3tempoappzaher.MainActivity.edfApi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.b3tempoappzaher.databinding.ActivityHistoryBinding;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    private final static String LOG_TAG = HistoryActivity.class.getSimpleName();
    private ActivityHistoryBinding binding;

    private List<TempoDate> tempoDates = new ArrayList<>();
    private TempoDateAdapter tempoDateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Init recycler view
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tempoDateAdapter = new TempoDateAdapter(tempoDates, this);
        binding.recyclerView.setAdapter(tempoDateAdapter);

        updateTempoHistory();

    }

    private void updateTempoHistory() {
        Call<TempoHistory> call = edfApi.getTempoHistory("2023","2024");
        call.enqueue(new Callback<TempoHistory>() {
            @Override
            public void onResponse(@NonNull Call<TempoHistory> call, @NonNull Response<TempoHistory> response) {
                tempoDates.clear();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body()!= null) {
                    tempoDates.addAll(response.body().getTempoDates());
                    Log.d(LOG_TAG,"nb elements = " + tempoDates.size());
                    tempoDateAdapter.notifyDataSetChanged();
                } else {
                    Log.e(LOG_TAG," call to getTempoHistory() failed with error code "+ response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TempoHistory> call, @NonNull Throwable t) {
                Log.e(LOG_TAG," call to getTempoHistory() failed");

            }
        });
    }

}