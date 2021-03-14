package com.example.myapplication1.alarm;

import com.example.myapplication1.BasePresenter;
import com.example.myapplication1.BaseView;
import com.example.myapplication1.source.History;
import com.example.myapplication1.source.MedicineAlarm;

/**
 * Created by gautam on 13/07/17.
 */

public interface ReminderContract {

    interface View extends BaseView<Presenter> {

        void showMedicine(MedicineAlarm medicineAlarm);

        void showNoData();

        boolean isActive();

        void onFinish();

    }

    interface Presenter extends BasePresenter {

        void finishActivity();

        void onStart(long id);

        void loadMedicineById(long id);

        void addPillsToHistory(History history);

    }
}
