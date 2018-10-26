package dao;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by raphael on 2017/5/18.
 */

public class Vehicle extends DataSupport implements Serializable{

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    private String plateNumber;

}
