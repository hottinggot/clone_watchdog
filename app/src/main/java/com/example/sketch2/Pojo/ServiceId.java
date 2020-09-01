package com.example.sketch2.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class ServiceId {

    private String total_count;

    @SerializedName(value = "row")
    private List<Row> rowList;

    @SerializedName(value = "RESULT")
    private Result result;
}
