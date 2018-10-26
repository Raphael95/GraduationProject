package dao;

import org.litepal.crud.DataSupport;

/**
 * Created by raphael on 2017/5/8.
 */

public class WayBill extends DataSupport{

    private Integer driverId;
    private String wayBillId;
    private String plateNumber;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDangerousCargoName() {
        return dangerousCargoName;
    }

    public void setDangerousCargoName(String dangerousCargoName) {
        this.dangerousCargoName = dangerousCargoName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private String dangerousCargoName;
    private String clientName;
    private String start;
    private String end;
    private String address;
    private String contacts;
    private String phone;
    private Double price;
    private Double tonnage;
    private Integer dangerousCargoId;
    private String transportTime;
    private Double outTonnage;
    private Double inTonnage;
    private Double meters;
    private Double tax;
    private Double revenue;
    private Double driverRevenue;
    private Double substitutePay;
    private Double fuelPay;
    private Integer isAccept;

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }


    public String getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(String wayBillId) {
        this.wayBillId = wayBillId;
    }



    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }



    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTonnage() {
        return tonnage;
    }

    public void setTonnage(Double tonnage) {
        this.tonnage = tonnage;
    }

    public Integer getDangerousCargoId() {
        return dangerousCargoId;
    }

    public void setDangerousCargoId(Integer dangerousCargoId) {
        this.dangerousCargoId = dangerousCargoId;
    }

    public String getTransportTime() {
        return transportTime;
    }

    public void setTransportTime(String transportTime) {
        this.transportTime = transportTime;
    }

    public Double getOutTonnage() {
        return outTonnage;
    }

    public void setOutTonnage(Double outTonnage) {
        this.outTonnage = outTonnage;
    }

    public Double getInTonnage() {
        return inTonnage;
    }

    public void setInTonnage(Double inTonnage) {
        this.inTonnage = inTonnage;
    }

    public Double getMeters() {
        return meters;
    }

    public void setMeters(Double meters) {
        this.meters = meters;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Double getDriverRevenue() {
        return driverRevenue;
    }

    public void setDriverRevenue(Double driverRevenue) {
        this.driverRevenue = driverRevenue;
    }

    public Double getSubstitutePay() {
        return substitutePay;
    }

    public void setSubstitutePay(Double substitutePay) {
        this.substitutePay = substitutePay;
    }

    public Double getFuelPay() {
        return fuelPay;
    }

    public void setFuelPay(Double fuelPay) {
        this.fuelPay = fuelPay;
    }

    public Integer getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(Integer isAccept) {
        this.isAccept = isAccept;
    }


}
