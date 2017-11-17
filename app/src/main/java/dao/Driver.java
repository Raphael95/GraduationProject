package dao;

import org.litepal.crud.DataSupport;

/**
 * Created by raphael on 2017/5/3.
 */

public class Driver extends DataSupport{
    private Integer userId;
    private String licensePlate;
    private String birthDate;
    private String nativePlace;
    private String identification;
    private String address;
    private String vehicleModel;
    private String limitedDate;
    private String startDate;
    private String archivesNumber;
    private String qualificationNumber;
    private String qualificationType;
    private String qualificationContinueDate;
    private String certificateDate;
    private String photoPath;
    private String certificatePath;
    private String qualificationPath;
    private String identificationPath;
    private Integer checkStatus;

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getLimitedDate() {
        return limitedDate;
    }

    public void setLimitedDate(String limitedDate) {
        this.limitedDate = limitedDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getArchivesNumber() {
        return archivesNumber;
    }

    public void setArchivesNumber(String archivesNumber) {
        this.archivesNumber = archivesNumber;
    }

    public String getQualificationNumber() {
        return qualificationNumber;
    }

    public void setQualificationNumber(String qualificationNumber) {
        this.qualificationNumber = qualificationNumber;
    }

    public String getQualificationType() {
        return qualificationType;
    }

    public void setQualificationType(String qualificationType) {
        this.qualificationType = qualificationType;
    }

    public String getQualificationContinueDate() {
        return qualificationContinueDate;
    }

    public void setQualificationContinueDate(String qualificationContinueDate) {
        this.qualificationContinueDate = qualificationContinueDate;
    }

    public String getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(String certificateDate) {
        this.certificateDate = certificateDate;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }

    public String getQualificationPath() {
        return qualificationPath;
    }

    public void setQualificationPath(String qualificationPath) {
        this.qualificationPath = qualificationPath;
    }

    public String getIdentificationPath() {
        return identificationPath;
    }

    public void setIdentificationPath(String identificationPath) {
        this.identificationPath = identificationPath;
    }
}
