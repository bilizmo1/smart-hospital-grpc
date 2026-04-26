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

public class SmartHospitalCardiologyWard {
    public static void main(String[] args) throws IOException, InterruptedException {
        DeviceRegistry registry = new DeviceRegistry();

        registry.addPatientMonitorDevice(
                new PatientMonitorDevice(
                        "monC1",
                        "Monitor 1",
                        "Oddział Kardiologii",
                        SmartHospitalProto.MonitorType.BASIC_MONITOR,
                        true,
                        true,
                        78,
                        98,
                        120,
                        80,
                        50,
                        120,
                        90
                )
        );

        registry.addPatientMonitorDevice(
                new PatientMonitorDevice(
                        "monC2",
                        "Monitor 2",
                        "Odział Kardiologii",
                        SmartHospitalProto.MonitorType.OIT_MONITOR,
                        true,
                        false,
                        88,
                        96,
                        130,
                        85,
                        45,
                        130,
                        92
                )
        );

        registry.addPatientMonitorDevice(
                new PatientMonitorDevice(
                        "monC3",
                        "Monitor 3",
                        "Odział Kardiologii",
                        SmartHospitalProto.MonitorType.BASIC_MONITOR,
                        false,
                        true,
                        72,
                        99,
                        118,
                        76,
                        55,
                        115,
                        94
                )
        );

        registry.addHospitalBedDevice(
                new HospitalBedDevice(
                        "bedC1",
                        "Łóżko 1",
                        SmartHospitalProto.BedType.STANDARD_BED,
                        true,
                        40,
                        true,
                        false,
                        true,
                        "Odział Kardiologii"
                )
        );

        registry.addHospitalBedDevice(
                new HospitalBedDevice(
                        "bedC2",
                        "Łóżko  2",
                        SmartHospitalProto.BedType.OIT_BED,
                        true,
                        55,
                        true,
                        true,
                        false,
                        "Oddział Kardiologii"
                )
        );

        registry.addHospitalBedDevice(
                new HospitalBedDevice(
                        "bedC3",
                        "Łóżko  3",
                        SmartHospitalProto.BedType.STANDARD_BED,
                        false,
                        35,
                        false,
                        false,
                        true,
                        "Oddział Kardiologii"
                )
        );

        registry.addHospitalCameraDevice(
                new HospitalCameraDevice(
                        "camC1",
                        "Kamera  1",
                        SmartHospitalProto.CameraType.ROOM_CAMERA,
                        true,
                        true,
                        0,
                        0,
                        1,
                        "Oddział Kardiologii"
                )
        );

        registry.addHospitalCameraDevice(
                new HospitalCameraDevice(
                        "camC2",
                        "Kamera 2",
                        SmartHospitalProto.CameraType.CORRIDOR_CAMERA,
                        true,
                        false,
                        30,
                        5,
                        2,
                        "Oddział Kardiologii"
                )
        );

        registry.addHospitalCameraDevice(
                new HospitalCameraDevice(
                        "camC3",
                        "Kamera  3",
                        SmartHospitalProto.CameraType.ROOM_CAMERA,
                        false,
                        false,
                        -20,
                        10,
                        1,
                        "Oddział Kardiologii"
                )
        );

        Server server = ServerBuilder
                .forPort(50051)
                .addService(new DeviceServiceImpl(registry))
                .addService(new PatientMonitorServiceImpl(registry))
                .addService(new HospitalBedServiceImpl(registry))
                .addService(new HospitalCameraServiceImpl(registry))
                .build();

        server.start();
        System.out.println("Serwer odziału Kardiologii nasłuchuje na porcie 50051");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Zamykanie serwera oddziału Kardiologii...");
            server.shutdown();
        }));

        server.awaitTermination();
    }
}