package com.example.myapplication1.addmedicine;

import com.example.myapplication1.BasePresenter;
import com.example.myapplication1.BaseView;
import com.example.myapplication1.source.MedicineAlarm;
import com.example.myapplication1.source.Pills;

import java.util.List;

/**
 * Created by gautam on 12/07/17.
 */

public interface AddMedicineContract {

    interface View extends BaseView<Presenter> {

        void showEmptyMedicineError();

        void showMedicineList();

        boolean isActive();

    }

    interface  Presenter extends BasePresenter{


        void saveMedicine(MedicineAlarm alarm, Pills pills);


        boolean isDataMissing();

        boolean isMedicineExits(String pillName);

        long addPills(Pills pills);

        Pills getPillsByName(String pillName);

        List<MedicineAlarm> getMedicineByPillName(String pillName);

        List<Long> tempIds();

        void deleteMedicineAlarm(long alarmId);

    }
}
