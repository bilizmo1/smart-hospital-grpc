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
                        "mon6",
                        "Monitor pooperacyjny 2",
                        "Surgery Ward",
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
                        "bed4",
                        "Łóżko pooperacyjne 1",
                        SmartHospitalProto.BedType.OIT_BED,
                        true,
                        50,
                        true,
                        true,
                        true,
                        "Surgery Ward"
                )
        );

        registry.addHospitalBedDevice(
                new HospitalBedDevice(
                        "bed5",
                        "Łóżko pooperacyjne 2",
                        SmartHospitalProto.BedType.STANDARD_BED,
                        true,
                        42,
                        false,
                        false,
                        false,
                        "Surgery Ward"
                )
        );

        registry.addHospitalBedDevice(
                new HospitalBedDevice(
                        "bed6",
                        "Łóżko zabiegowe 1",
                        SmartHospitalProto.BedType.STANDARD_BED,
                        false,
                        38,
                        true,
                        false,
                        true,
                        "Surgery Ward"
                )
        );
        registry.addHospitalCameraDevice(
                new HospitalCameraDevice(
                        "cam4",
                        "Kamera blok 1",
                        SmartHospitalProto.CameraType.ROOM_CAMERA,
                        true,
                        true,
                        10,
                        0,
                        2,
                        "Surgery Ward"
                )
        );

        registry.addHospitalCameraDevice(
                new HospitalCameraDevice(
                        "cam5",
                        "Kamera blok 2",
                        SmartHospitalProto.CameraType.ROOM_CAMERA,
                        true,
                        false,
                        -15,
                        8,
                        3,
                        "Surgery Ward"
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