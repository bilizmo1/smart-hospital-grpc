package com.smarthospital.model;

import com.smarthospital.grpc.SmartHospitalProto;

public class HospitalCameraDevice {

    private final String deviceId;
    private final String name;
    private final SmartHospitalProto.CameraType cameraType;
    private final String location;

    private boolean isOn;
    private boolean isRecording;
    private int pan;
    private int tilt;
    private int zoom;

    public HospitalCameraDevice(String deviceId, String name, SmartHospitalProto.CameraType cameraType,
                                boolean isOn, boolean isRecording,
                                int pan, int tilt, int zoom, String location) {
        this.deviceId = deviceId;
        this.name = name;
        this.cameraType = cameraType;
        this.isOn = isOn;
        this.isRecording = isRecording;
        this.pan = pan;
        this.tilt = tilt;
        this.zoom = zoom;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public SmartHospitalProto.CameraType getCameraType() {
        return cameraType;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public boolean isRecording() {
        return isRecording;
    }

    public void setRecording(boolean recording) {
        isRecording = recording;
    }

    public int getPan() {
        return pan;
    }

    public void setPan(int pan) {
        this.pan = pan;
    }

    public int getTilt() {
        return tilt;
    }

    public void setTilt(int tilt) {
        this.tilt = tilt;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
    public String getLocation() {
        return location;
    }

}

