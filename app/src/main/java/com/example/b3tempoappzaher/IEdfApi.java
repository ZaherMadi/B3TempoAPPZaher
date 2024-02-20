package com.example.b3tempoappzaher;
import com.journaldev.retrofitintro.pojo.MultipleResource;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.journaldev.retrofitintro.pojo.MultipleResource;
import com.journaldev.retrofitintro.pojo.User;
import com.journaldev.retrofitintro.pojo.UserList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
public interface IEdfApi {
    @GET("/services/rest/referentiel/getNbTempoDays")
    Call<TempoDaysLeft> getTempoDaysLeft(@Query("TypeAlerte") String TypeAlerte);

    @GET("/services/rest/referentiel/getNbTempoDays")
    Call<TempoDaysLeft> getTempoDaysLeft(@Query("TypeAlerte") String TypeAlerte);
}

// https://particulier.edf.fr/services/rest/referentiel/getNbTempoDays?TypeAlerte=TEMPO
// https://particulier.edf.fr/services/rest/referentiel/searchTempoStore?dateRelevant=2024-02-20&TypeAlerte=TEMPO
//https://particulier.edf.fr/services/rest/referentiel/historicTEMPOStore?dateBegin=2023&dateEnd=2024



