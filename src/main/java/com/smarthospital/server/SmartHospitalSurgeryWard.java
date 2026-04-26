package com.smarthospital.server;

import com.smarthospital.grpc.SmartHospitalProto;
import com.smarthospital.model.HospitalBedDevice;
import com.smarthospital.model.HospitalCameraDevice;
import com.smarthospital.model.PatientMonitorDevice;
import com.smarthospital.registry.DeviceRegistry;
import com.smarthospital.service.DeviceServiceImpl;
import com.smarthospital.service.HospitalBedServiceImpl;
import com.smarthospital.service.HospitalCameraServiceImpl;
import com.smarthospital.service.PatientMonitorServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class SmartHospitalSurgeryWard {
    public static void main(String[] args) throws IOException, InterruptedException {
        DeviceRegistry registry = new DeviceRegistry();

        registry.addPatientMonitorDevice(
                new PatientMonitorDevice(
                        "monS1",
                        "Monitor 1",
                        "Oddział Chirurgii",
                        SmartHospitalProto.MonitorType.OIT_MONITOR,
                        true,
                        true,
                        70,
                        97,
                        125,
                        100,
                        50,
                        125,
                        90
                )
        );

        registry.addPatientMonitorDevice(
                new PatientMonitorDevice(
                        "monS2",
                        "Monitor 2",
                        "Oddział Chirurgii",
                        SmartHospitalProto.MonitorType.BASIC_MONITOR,
                        true,
                        false,
                        76,
                        95,
                        119,
                        78,
                        48,
                        118,
                        90
                )
        );

        registry.addPatientMonitorDevice(
                new PatientMonitorDevice(
                        "monS3",
                        "Monitor 2",
                        "Oddział Chirurgii",
                        SmartHospitalProto.MonitorType.BASIC_MONITOR,
                        false,
                        true,
                        70,
                        99,
                        117,
                        75,
                        52,
                        116,
                        93
                )
        );
        registry.addHospitalBedDevice(
                new HospitalBedDevice(
                        "bedS1",
                        "Łóżko  1",
                        SmartHospitalProto.BedType.OIT_BED,
                        true,
                        50,
                        true,
                        true,
                        true,
                        "Oddział Chirurgii"
                )
        );

        registry.addHospitalBedDevice(
                new HospitalBedDevice(
                        "bedS2",
                        "Łóżko  2",
                        SmartHospitalProto.BedType.STANDARD_BED,
                        true,
                        42,
                        false,
                        false,
                        false,
                        "Oddział Chirurgii"
                )
        );

        registry.addHospitalBedDevice(
                new HospitalBedDevice(
                        "bedS3",
                        "Łóżko 3",
                        SmartHospitalProto.BedType.STANDARD_BED,
                        false,
                        38,
                        true,
                        false,
                        true,
                        "Oddział Chirurgii"
                )
        );
        registry.addHospitalCameraDevice(
                new HospitalCameraDevice(
                        "camS1",
                        "Kamera 1",
                        SmartHospitalProto.CameraType.ROOM_CAMERA,
                        true,
                        true,
                        10,
                        0,
                        2,
                        "Oddział Chirurgii"
                )
        );

        registry.addHospitalCameraDevice(
                new HospitalCameraDevice(
                        "camS2",
                        "Kamera 2",
                        SmartHospitalProto.CameraType.ROOM_CAMERA,
                        true,
                        false,
                        -15,
                        8,
                        3,
                        "Oddział Chirurgii"
                )
        );

        Server server = ServerBuilder
                .forPort(50052)
                .addService(new DeviceServiceImpl(registry))
                .addService(new PatientMonitorServiceImpl(registry))
                .addService(new HospitalBedServiceImpl(registry))
                .addService(new HospitalCameraServiceImpl(registry))
                .build();

        server.start();
        System.out.println("Odział Chirurgii działa na porcie 50052");

        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));

        server.awaitTermination();
    }
}