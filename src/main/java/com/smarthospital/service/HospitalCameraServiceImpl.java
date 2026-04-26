package com.smarthospital.service;

import com.smarthospital.grpc.HospitalCameraServiceGrpc;
import com.smarthospital.grpc.SmartHospitalProto;
import com.smarthospital.model.HospitalCameraDevice;
import com.smarthospital.registry.DeviceRegistry;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class HospitalCameraServiceImpl extends HospitalCameraServiceGrpc.HospitalCameraServiceImplBase {

    private final DeviceRegistry registry;

    public HospitalCameraServiceImpl(DeviceRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void getState(SmartHospitalProto.DeviceIdRequest request, StreamObserver<SmartHospitalProto.HospitalCameraState> responseObserver) {
        HospitalCameraDevice hospitalCameraDevice = registry.getHospitalCameraDevice(request.getDeviceId());

        if (hospitalCameraDevice == null) {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Brak Kamery: " + request.getDeviceId())
                            .asRuntimeException()
            );
            return;
        }

        SmartHospitalProto.HospitalCameraState response =
                SmartHospitalProto.HospitalCameraState.newBuilder()
                        .setDeviceId(hospitalCameraDevice.getDeviceId())
                        .setName(hospitalCameraDevice.getName())
                        .setCameraType(hospitalCameraDevice.getCameraType())
                        .setIsOn(hospitalCameraDevice.isOn())
                        .setIsRecording(hospitalCameraDevice.isRecording())
                        .setPan(hospitalCameraDevice.getPan())
                        .setTilt(hospitalCameraDevice.getTilt())
                        .setZoom(hospitalCameraDevice.getZoom())
                        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setPower(SmartHospitalProto.SetCameraPowerRequest request, StreamObserver<SmartHospitalProto.OperationStatus> responseObserver) {
        HospitalCameraDevice hospitalCameraDevice = registry.getHospitalCameraDevice(request.getDeviceId());

        if (hospitalCameraDevice == null) {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Brak Kamery: " + request.getDeviceId())
                            .asRuntimeException()
            );
            return;
        }

        hospitalCameraDevice.setOn(request.getIsOn());

        SmartHospitalProto.OperationStatus response =
                SmartHospitalProto.OperationStatus.newBuilder()
                        .setSuccess(true)
                        .setMessage("Zmieniono status Kamery")
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setRecording(SmartHospitalProto.SetRecordingRequest request, StreamObserver<SmartHospitalProto.OperationStatus> responseObserver) {
        HospitalCameraDevice hospitalCameraDevice = registry.getHospitalCameraDevice(request.getDeviceId());

        if (hospitalCameraDevice == null) {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Brak Kamery: " + request.getDeviceId())
                            .asRuntimeException()
            );
            return;
        }

        hospitalCameraDevice.setRecording(request.getIsRecording());

        SmartHospitalProto.OperationStatus response =
                SmartHospitalProto.OperationStatus.newBuilder()
                        .setSuccess(true)
                        .setMessage("Nagrywanie ")
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void moveCamera(SmartHospitalProto.MoveCameraRequest request, StreamObserver<SmartHospitalProto.OperationStatus> responseObserver) {
        HospitalCameraDevice hospitalCameraDevice = registry.getHospitalCameraDevice(request.getDeviceId());

        if (hospitalCameraDevice == null) {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Brak kamery: " + request.getDeviceId())
                            .asRuntimeException()
            );
            return;
        }

        hospitalCameraDevice.setPan(request.getPan());
        hospitalCameraDevice.setTilt(request.getTilt());
        hospitalCameraDevice.setZoom(request.getZoom());

        SmartHospitalProto.OperationStatus response =
                SmartHospitalProto.OperationStatus.newBuilder()
                        .setSuccess(true)
                        .setMessage("Zmieniono pozycję kamery")
                        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}

