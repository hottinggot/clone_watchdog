package com.example.sketch2.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class Row implements Parcelable {
    private String PRDLST_CD;
    private String FRMLCUNIT;
    private String RTRVL_GRDCD_NM;
    private String BSSHNM;
    private String DISTBTMLMT;
    private String RTRVLPLANDOC_RTRVLMTHD;
    private String BRCDNO;
    private String RTRVLDSUSE_SEQ;
    private String CRET_DTM;
    private String PRDLST_REPORT_NO;
    private String MNFDT;
    private String PRDLST_CD_NM;
    private String RTRVLPRVNS;
    private String PRDTNM;
    private String PRCSCITYPOINT_TELNO;
    private String ADDR;
    private String IMG_FILE_PATH;
    private String PRDLST_TYPE;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.PRDLST_CD);
        dest.writeString(this.FRMLCUNIT);
        dest.writeString(this.RTRVL_GRDCD_NM);
        dest.writeString(this.BSSHNM);
        dest.writeString(this.DISTBTMLMT);
        dest.writeString(this.RTRVLPLANDOC_RTRVLMTHD);
        dest.writeString(this.BRCDNO);
        dest.writeString(this.RTRVLDSUSE_SEQ);
        dest.writeString(this.CRET_DTM);
        dest.writeString(this.PRDLST_REPORT_NO);
        dest.writeString(this.MNFDT);
        dest.writeString(this.PRDLST_CD_NM);
        dest.writeString(this.RTRVLPRVNS);
        dest.writeString(this.PRDTNM);
        dest.writeString(this.PRCSCITYPOINT_TELNO);
        dest.writeString(this.ADDR);
        dest.writeString(this.IMG_FILE_PATH);
        dest.writeString(this.PRDLST_TYPE);
    }

    protected Row(Parcel in) {
        this.PRDLST_CD = in.readString();
        this.FRMLCUNIT = in.readString();
        this.RTRVL_GRDCD_NM = in.readString();
        this.BSSHNM = in.readString();
        this.DISTBTMLMT = in.readString();
        this.RTRVLPLANDOC_RTRVLMTHD = in.readString();
        this.BRCDNO = in.readString();
        this.RTRVLDSUSE_SEQ = in.readString();
        this.CRET_DTM = in.readString();
        this.PRDLST_REPORT_NO = in.readString();
        this.MNFDT = in.readString();
        this.PRDLST_CD_NM = in.readString();
        this.RTRVLPRVNS = in.readString();
        this.PRDTNM = in.readString();
        this.PRCSCITYPOINT_TELNO = in.readString();
        this.ADDR = in.readString();
        this.IMG_FILE_PATH = in.readString();
        this.PRDLST_TYPE = in.readString();
    }

    public static final Creator<Row> CREATOR = new Creator<Row>() {
        @Override
        public Row createFromParcel(Parcel source) {
            return new Row(source);
        }

        @Override
        public Row[] newArray(int size) {
            return new Row[size];
        }
    };
}

