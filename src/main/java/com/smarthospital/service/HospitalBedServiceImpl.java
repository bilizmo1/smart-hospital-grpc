package com.smarthospital.service;

import com.smarthospital.grpc.HospitalBedServiceGrpc;
import com.smarthospital.grpc.SmartHospitalProto;
import com.smarthospital.model.HospitalBedDevice;
import com.smarthospital.registry.DeviceRegistry;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class HospitalBedServiceImpl extends HospitalBedServiceGrpc.HospitalBedServiceImplBase{

    private final DeviceRegistry registry;

    public HospitalBedServiceImpl(DeviceRegistry registry) {
        this.registry = registry;
    }


    @Override
    public void getState(SmartHospitalProto.DeviceIdRequest request, StreamObserver<SmartHospitalProto.HospitalBedState> responseObserver) {
       HospitalBedDevice hospitalBedDevice = registry.getHospitalBedDevice(request.getDeviceId());

        if(hospitalBedDevice == null){
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Brak Łóżka: " + request.getDeviceId())
                            .asRuntimeException()
            );
            return;
        }

        SmartHospitalProto.HospitalBedState response =
                SmartHospitalProto.HospitalBedState.newBuilder()
                        .setDeviceId(hospitalBedDevice.getDeviceId())
                        .setName(hospitalBedDevice.getName())
                        .setBedType(hospitalBedDevice.getBedType())
                        .setIsOn(hospitalBedDevice.isOn())
                        .setHeight(hospitalBedDevice.getHeight())
                        .setWheelsLocked(hospitalBedDevice.isWheelsLocked())
                        .setEmergencyMode(hospitalBedDevice.isEmergencyMode())
                        .setOccupied(hospitalBedDevice.isOccupied())
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setEmergencyMode(SmartHospitalProto.SetEmergencyModeRequest request, StreamObserver<SmartHospitalProto.OperationStatus> responseObserver) {
        HospitalBedDevice hospitalBedDevice = registry.getHospitalBedDevice(request.getDeviceId());

        if(hospitalBedDevice == null){
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Brak Łóżka: " + request.getDeviceId())
                            .asRuntimeException()
            );
            return;
        }

        hospitalBedDevice.setEmergencyMode(request.getEmergencyMode());

        SmartHospitalProto.OperationStatus response =
                SmartHospitalProto.OperationStatus.newBuilder()
                        .setSuccess(true)
                        .setMessage("Załączono/Zdezaktywowano tryb awaryjny")
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();


    }

    @Override
    public void setOccupied(SmartHospitalProto.SetOccupiedRequest request, StreamObserver<SmartHospitalProto.OperationStatus> responseObserver) {
        HospitalBedDevice hospitalBedDevice = registry.getHospitalBedDevice(request.getDeviceId());

        if(hospitalBedDevice == null){
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Brak Łóżka: " + request.getDeviceId())
                            .asRuntimeException()
            );
            return;
        }

        hospitalBedDevice.setOccupied(request.getOccupied());

        SmartHospitalProto.OperationStatus response =
                SmartHospitalProto.OperationStatus.newBuilder()
                        .setSuccess(true)
                        .setMessage("Zmieniono status łóżka")
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setWheelsLock(SmartHospitalProto.SetWheelsLockRequest request, StreamObserver<SmartHospitalProto.OperationStatus> responseObserver) {
        HospitalBedDevice hospitalBedDevice = registry.getHospitalBedDevice(request.getDeviceId());

        if(hospitalBedDevice == null){
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Brak Łóżka: " + request.getDeviceId())
                            .asRuntimeException()
            );
            return;
        }

        hospitalBedDevice.setWheelsLocked(request.getWheelsLocked());

        SmartHospitalProto.OperationStatus response =
                SmartHospitalProto.OperationStatus.newBuilder()
                        .setSuccess(true)
                        .setMessage("Zmieniono status kólek")
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setHeight(SmartHospitalProto.SetHeightRequest request, StreamObserver<SmartHospitalProto.OperationStatus> responseObserver) {
        HospitalBedDevice hospitalBedDevice = registry.getHospitalBedDevice(request.getDeviceId());

        if(hospitalBedDevice == null){
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Brak Łóżka: " + request.getDeviceId())
                            .asRuntimeException()
            );
            return;
        }

        hospitalBedDevice.setHeight(request.getHeight());

        SmartHospitalProto.OperationStatus response =
                SmartHospitalProto.OperationStatus.newBuilder()
                        .setSuccess(true)
                        .setMessage("Ustawiono wysokośc łózka")
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setPower(SmartHospitalProto.SetBedPowerRequest request, StreamObserver<SmartHospitalProto.OperationStatus> responseObserver) {
        HospitalBedDevice hospitalBedDevice = registry.getHospitalBedDevice(request.getDeviceId());

        if(hospitalBedDevice == null){
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Brak Łóżka: " + request.getDeviceId())
                            .asRuntimeException()
            );
            return;
        }

        hospitalBedDevice.setIsOn(request.getIsOn());

        SmartHospitalProto.OperationStatus response =
                SmartHospitalProto.OperationStatus.newBuilder()
                        .setSuccess(true)
                        .setMessage("Załączono/Zdezaktywowano łóżko")
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}


