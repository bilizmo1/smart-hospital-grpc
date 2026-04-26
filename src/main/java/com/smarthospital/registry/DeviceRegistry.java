package com.smarthospital.registry;

import com.smarthospital.model.HospitalBedDevice;
import com.smarthospital.model.HospitalCameraDevice;
import com.smarthospital.model.PatientMonitorDevice;

import java.util.HashMap;
import java.util.Map;

public class DeviceRegistry {
    private final Map<String, PatientMonitorDevice> patientMonitorDevices = new HashMap<>();
    private final Map<String, HospitalBedDevice> hospitalBedDevices = new HashMap<>();
    private final Map<String, HospitalCameraDevice> hospitalCameraDevices = new HashMap<>();

    //dodajemy do struktur
    public void addPatientMonitorDevice(PatientMonitorDevice patientMonitorDevice) {
        patientMonitorDevices.put(patientMonitorDevice.getDeviceId(), patientMonitorDevice);
    }

    public void addHospitalBedDevice(HospitalBedDevice bedDevice) {
        hospitalBedDevices.put(bedDevice.getDeviceId(), bedDevice);
    }
    public void addHospitalCameraDevice(HospitalCameraDevice cameraDevice) {
        hospitalCameraDevices.put(cameraDevice.getDeviceId(), cameraDevice);
    }
    //pobieramy każde urządzenie
    public PatientMonitorDevice getPatientMonitorDevice(String deviceId) {
        return patientMonitorDevices.get(deviceId);
    }
    public HospitalBedDevice getHospitalBedDevice(String deviceId) {
        return hospitalBedDevices.get(deviceId);
    }
    public HospitalCameraDevice getHospitalCameraDevice(String deviceId) {
        return hospitalCameraDevices.get(deviceId);
    }

    // zwracamy wszystkie urządzenie

    public Map<String, PatientMonitorDevice> getPatientMonitorDevices() {
        return patientMonitorDevices;
    }
    public Map<String, HospitalBedDevice> getHospitalBedDevices() {
        return hospitalBedDevices;
    }
    public Map<String, HospitalCameraDevice> getHospitalCameraDevices() {
        return hospitalCameraDevices;
    }




}


