package com.example.myapplication1;

import android.content.Context;
import androidx.annotation.NonNull;

import com.example.myapplication1.source.MedicineDataSource;
import com.example.myapplication1.source.MedicineRepository;
import com.example.myapplication1.source.local.MedicinesLocalDataSource;

/**
 * Created by gautam on 13/07/17.
 */

public class Injection {

    public static MedicineRepository provideMedicineRepository(@NonNull Context context) {
        return MedicineRepository.getInstance((MedicineDataSource) MedicinesLocalDataSource.getInstance(context));
    }
}
