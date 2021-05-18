package com.example.tfg.UI.HorarioUI.HorarioFragments;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.material.datepicker.CalendarConstraints;


class RangeValidator implements CalendarConstraints.DateValidator {

    private Long minDate;
    private Long maxDate;

    public RangeValidator() {}

    public RangeValidator(Long minDate, Long maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public RangeValidator(Parcel parcel) {
        parcel.readLong();
        parcel.readLong();
    }

    @Override
    public boolean isValid(long date) {
        return !(minDate > date || maxDate < date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<RangeValidator> CREATOR = new Parcelable.Creator<RangeValidator>(){
        @Override
        public RangeValidator createFromParcel(Parcel parcel) {
            return new RangeValidator(parcel);
        }

        @Override
        public RangeValidator[] newArray(int size) {
            return new RangeValidator[size];
        }
    };
}