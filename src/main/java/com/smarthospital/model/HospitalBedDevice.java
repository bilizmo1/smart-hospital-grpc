package com.smarthospital.model;

import com.smarthospital.grpc.SmartHospitalProto;

public class HospitalBedDevice {
    private final String deviceId;
    private final String name;
    private final SmartHospitalProto.BedType bedType;
    private final String location;

    private boolean isOn;
    private int height;
    private boolean wheelsLocked;
    private boolean emergencyMode;
    private boolean occupied;

    public HospitalBedDevice(String deviceId, String name, SmartHospitalProto.BedType bedType,
                             boolean isOn, int height,
                             boolean wheelsLocked, boolean emergencyMode, boolean occupied,String location ) {
        this.deviceId = deviceId;
        this.name = name;
        this.bedType = bedType;
        this.isOn = isOn;
        this.height = height;
        this.wheelsLocked = wheelsLocked;
        this.emergencyMode = emergencyMode;
        this.occupied = occupied;
        this.location = location;
    }

    public String getDeviceId() {
        return deviceId;
    }
    public String getName() {
        return name;
    }
    public SmartHospitalProto.BedType getBedType() {
        return bedType;
    }
    public boolean isOn() {
        return isOn;
    }
    public int getHeight() {
        return height;
    }
    public String getLocation() { return location; }
    public boolean isWheelsLocked() {
        return wheelsLocked;
    }
    public boolean isEmergencyMode() {
        return emergencyMode;
    }
    public boolean isOccupied() {
        return occupied;
    }
    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }
    public void setHeight(int height) {
        this.height = height;

    }

    public void setWheelsLocked(boolean wheelsLocked) {
        this.wheelsLocked = wheelsLocked;
    }
    public void setEmergencyMode(boolean emergencyMode) {
        this.emergencyMode = emergencyMode;
    }
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }


}

