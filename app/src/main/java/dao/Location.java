package dao;

import org.litepal.crud.DataSupport;

/**
 * Created by raphael on 2017/5/6.
 */

public class Location extends DataSupport{

    private double sLatitude;
    private double sLongitude;
    private double eLatitude;
    private double eLongitude;

    public double getsLatitude() {
        return sLatitude;
    }

    public void setsLatitude(double sLatitude) {
        this.sLatitude = sLatitude;
    }

    public double getsLongitude() {
        return sLongitude;
    }

    public void setsLongitude(double sLongitude) {
        this.sLongitude = sLongitude;
    }

    public double geteLatitude() {
        return eLatitude;
    }

    public void seteLatitude(double eLatitude) {
        this.eLatitude = eLatitude;
    }

    public double geteLongitude() {
        return eLongitude;
    }

    public void seteLongitude(double eLongitude) {
        this.eLongitude = eLongitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    private double distance;
}
