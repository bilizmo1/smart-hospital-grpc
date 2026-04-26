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
                        "Łóżko sala 1",
                        SmartHospitalProto.BedType.STANDARD_BED,
                        true,
                        40,
                        true,
                        false,
                        true,
                        "Cardiology Ward"
                )
        );

        registry.addHospitalBedDevice(
                new HospitalBedDevice(
                        "bedC2",
                        "Łóżko sala 2",
                        SmartHospitalProto.BedType.OIT_BED,
                        true,
                        55,
                        true,
                        true,
                        false,
                        "Cardiology Ward"
                )
        );

        registry.addHospitalBedDevice(
                new HospitalBedDevice(
                        "bedC3",
                        "Łóżko sala 3",
                        SmartHospitalProto.BedType.STANDARD_BED,
                        false,
                        35,
                        false,
                        false,
                        true,
                        "Cardiology Ward"
                )
        );

        registry.addHospitalCameraDevice(
                new HospitalCameraDevice(
                        "camC1",
                        "Kamera sala 1",
                        SmartHospitalProto.CameraType.ROOM_CAMERA,
                        true,
                        true,
                        0,
                        0,
                        1,
                        "Cardiology Ward"
                )
        );

        registry.addHospitalCameraDevice(
                new HospitalCameraDevice(
                        "camC2",
                        "Kamera korytarz 1",
                        SmartHospitalProto.CameraType.CORRIDOR_CAMERA,
                        true,
                        false,
                        30,
                        5,
                        2,
                        "Cardiology Ward"
                )
        );

        registry.addHospitalCameraDevice(
                new HospitalCameraDevice(
                        "camC3",
                        "Kamera sala 2",
                        SmartHospitalProto.CameraType.ROOM_CAMERA,
                        false,
                        false,
                        -20,
                        10,
                        1,
                        "Cardiology Ward"
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
        System.out.println("CardiologyWard started on port 50051");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down CardiologyWard...");
            server.shutdown();
        }));

        server.awaitTermination();
    }
}