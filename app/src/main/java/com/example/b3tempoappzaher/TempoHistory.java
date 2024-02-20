
package com.example.b3tempoappzaher;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempoHistory {

    @SerializedName("dates")
    @Expose
    private List<TempoDate> dates;

    public List<TempoDate> getDates() {
        return dates;
    }

    public void setDates(List<TempoDate> dates) {
        this.dates = dates;
    }

}
