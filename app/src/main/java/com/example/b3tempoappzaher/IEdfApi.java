package com.example.b3tempoappzaher;

import com.example.b3tempoappzaher.TempoDaysLeft;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IEdfApi {
    String EDF_TEMPO_API_ALERT_TYPE = "TEMPO";

    // https://particulier.edf.fr/services/rest/referentiel/getNbTempoDays?TypeAlerte=TEMPO

    @GET("services/rest/referentiel/getNbTempoDays")
    Call<TempoDaysLeft> getTempoDaysLeft(@Query("TypeAlerte")String alertType);

    // https://particulier.edf.fr/services/rest/referentiel/searchTempoStore?dateRelevant=2024-02-20&TypeAlerte=TEMPO
    @GET("services/rest/referentiel/searchTempoStore")
    Call<TempoDaysColor> getTempoDaysColor(
            @Query("dateRelevant") String dateBegin,
            @Query("TypeAlerte")String alertType);

    // https://particulier.edf.fr/services/rest/referentiel/historicTEMPOStore?dateBegin=2023&dateEnd=2024

    @GET("services/rest/referentiel/historicTEMPOStore")
    Call<TempoHistory> getTempoHistory(
            @Query("dateBegin") String dateBegin,
            @Query("dateEnd") String dateEnd
    );
}
