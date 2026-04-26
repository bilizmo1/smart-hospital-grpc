package com.smarthospital.model;


import com.smarthospital.grpc.SmartHospitalProto;
import com.smarthospital.grpc.SmartHospitalProto.MonitorType;

public class PatientMonitorDevice {

    private final String deviceId;
    private final String name;
    private final MonitorType monitorType;
    private final String location;

    private boolean isOn;
    private boolean alarmEnabled;
    private int heartRate;
    private int saturation;
    private int systolic;
    private int diastolic;

    private int minHeartRate;
    private int maxHeartRate;
    private int minSaturation;

    public PatientMonitorDevice(String deviceId, String name, String location,
                                 SmartHospitalProto.MonitorType monitorType,
                                 boolean isOn, boolean alarmEnabled,
                                 int heartRate, int saturation,
                                 int systolic, int diastolic,
                                 int minHeartRate, int maxHeartRate, int minSaturation) {
        this.deviceId = deviceId;
        this.name = name;
        this.location = location;
        this.monitorType = monitorType;
        this.isOn = isOn;
        this.alarmEnabled = alarmEnabled;
        this.heartRate = heartRate;
        this.saturation = saturation;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.minHeartRate = minHeartRate;
        this.maxHeartRate = maxHeartRate;
        this.minSaturation = minSaturation;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getName() {
        return name;
    }

    public MonitorType getMonitorType() {
        return monitorType;
    }
    public String getLocation() {
        return location;
    }


    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public boolean isAlarmEnabled() {
        return alarmEnabled;
    }

    public void setAlarmEnabled(boolean alarmEnabled) {
        this.alarmEnabled = alarmEnabled;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getSaturation() {
        return saturation;
    }

    public void setSaturation(int saturation) {
        this.saturation = saturation;

    }

    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public int getMinHeartRate() {
        return minHeartRate;
    }

    public int getMaxHeartRate() {
        return maxHeartRate;
    }


    public int getMinSaturation() {
        return minSaturation;
    }

    public void setThresholds(int minHeartRate, int maxHeartRate, int minSaturation) {
        this.minHeartRate = minHeartRate;
        this.maxHeartRate = maxHeartRate;
        this.minSaturation = minSaturation;
    }
}
