package com.example.projetandroid;

import androidx.room.TypeConverter;

import java.util.Date;

public class Convertors {
    @TypeConverter
    public Date fromTimeStamp(Long value){
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp(Date date){
        return date == null ? null : date.getTime();
    }

}