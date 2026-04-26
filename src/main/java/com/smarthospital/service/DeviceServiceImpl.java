package com.smarthospital.service;

import com.smarthospital.grpc.DeviceServiceGrpc;
import com.smarthospital.grpc.SmartHospitalProto;
import com.smarthospital.model.HospitalBedDevice;
import com.smarthospital.model.HospitalCameraDevice;
import com.smarthospital.model.PatientMonitorDevice;
import com.smarthospital.registry.DeviceRegistry;
import io.grpc.stub.StreamObserver;

import java.util.Map;

public class DeviceServiceImpl  extends  DeviceServiceGrpc.DeviceServiceImplBase {
    private DeviceRegistry deviceRegistry;

    public DeviceServiceImpl(DeviceRegistry deviceRegistry) {
        this.deviceRegistry = deviceRegistry;
    }

    @Override
    public void listDevices(SmartHospitalProto.Empty request, StreamObserver<SmartHospitalProto.DeviceList> responseObserver) {
            Map<String, PatientMonitorDevice> patientMonitorDevices = deviceRegistry.getPatientMonitorDevices();
            Map<String, HospitalCameraDevice> hospitalCameraDevices = deviceRegistry.getHospitalCameraDevices();
            Map<String, HospitalBedDevice> hospitalBedDevices = deviceRegistry.getHospitalBedDevices();

            System.out.println("Zwróc wszystkie możliwe urządzenia");

        SmartHospitalProto.DeviceList.Builder deviceListBuilder = SmartHospitalProto.DeviceList.newBuilder();

        for (PatientMonitorDevice device : patientMonitorDevices.values()) {
            deviceListBuilder.addDevices(
                    SmartHospitalProto.DeviceInfo.newBuilder()
                            .setDeviceId(device.getDeviceId())
                            .setName(device.getName())
                            .setType(SmartHospitalProto.DeviceType.PATIENT_MONITOR)
                            .setLocation(device.getLocation())
                            .build()
            );
        }

        for (HospitalBedDevice device : hospitalBedDevices.values()) {
            deviceListBuilder.addDevices(
                    SmartHospitalProto.DeviceInfo.newBuilder()
                            .setDeviceId(device.getDeviceId())
                            .setName(device.getName())
                            .setType(SmartHospitalProto.DeviceType.PATIENT_BED)
                            .setLocation(device.getLocation())
                            .build()
            );
        }

        for (HospitalCameraDevice  device : hospitalCameraDevices.values()) {
            deviceListBuilder.addDevices(
                    SmartHospitalProto.DeviceInfo.newBuilder()
                            .setDeviceId(device.getDeviceId())
                            .setName(device.getName())
                            .setType(SmartHospitalProto.DeviceType.HOSPITAL_CAMERA)
                            .setLocation(device.getLocation())
                            .build()
            );
        }
        responseObserver.onNext(deviceListBuilder.build());
        responseObserver.onCompleted();




    }
}
