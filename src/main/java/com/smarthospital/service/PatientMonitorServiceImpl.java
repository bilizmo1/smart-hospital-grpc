package com.smarthospital.service;

import com.smarthospital.grpc.PatientMonitorServiceGrpc;
import com.smarthospital.grpc.SmartHospitalProto;
import com.smarthospital.model.PatientMonitorDevice;
import com.smarthospital.registry.DeviceRegistry;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;


public class PatientMonitorServiceImpl extends PatientMonitorServiceGrpc.PatientMonitorServiceImplBase {

    private DeviceRegistry registry;

    public PatientMonitorServiceImpl(DeviceRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void getState(SmartHospitalProto.DeviceIdRequest request, StreamObserver<SmartHospitalProto.PatientMonitorState> responseObserver) {
        PatientMonitorDevice patientMonitorDevice = registry.getPatientMonitorDevice(request.getDeviceId());

        if(patientMonitorDevice == null){
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Brak monitora: " + request.getDeviceId())
                            .asRuntimeException()
            );
            return;
        }

        SmartHospitalProto.PatientMonitorState response =
                SmartHospitalProto.PatientMonitorState.newBuilder()
                        .setDeviceId(patientMonitorDevice.getDeviceId())
                        .setName(patientMonitorDevice.getName())
                        .setMonitorType(patientMonitorDevice.getMonitorType())
                        .setIsOn(patientMonitorDevice.isOn())
                        .setAlarmEnabled(patientMonitorDevice.isAlarmEnabled())
                        .setHeartRate(patientMonitorDevice.getHeartRate())
                        .setSaturation(patientMonitorDevice.getSaturation())
                        .setPressure(
                                SmartHospitalProto.BloodPressure.newBuilder()
                                        .setSystolic(patientMonitorDevice.getSystolic())
                                        .setDiastolic(patientMonitorDevice.getDiastolic())
                                        .build()
                        )
                        .setThresholds(
                                SmartHospitalProto.MonitorAlarmThresholds.newBuilder()
                                        .setMinHeartRate(patientMonitorDevice.getMinHeartRate())
                                        .setMaxHeartRate(patientMonitorDevice.getMaxHeartRate())
                                        .setMinSaturation(patientMonitorDevice.getMinSaturation())
                                        .build()
                        )
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void setPower(SmartHospitalProto.SetMonitorPowerRequest request, StreamObserver<SmartHospitalProto.OperationStatus> responseObserver) {
        PatientMonitorDevice patientMonitorDevice = registry.getPatientMonitorDevice(request.getDeviceId());

        if(patientMonitorDevice == null){
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Brak monitora: " + request.getDeviceId())
                            .asRuntimeException()
            );
            return;
        }

        patientMonitorDevice.setOn(request.getIsOn());

        SmartHospitalProto.OperationStatus response =
                SmartHospitalProto.OperationStatus.newBuilder()
                        .setSuccess(true)
                        .setMessage("Zmieniono stan zasilania monitora")
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();


    }

    @Override
    public void setAlarmEnabled(SmartHospitalProto.SetMonitorAlarmRequest request, StreamObserver<SmartHospitalProto.OperationStatus> responseObserver) {
        PatientMonitorDevice patientMonitorDevice = registry.getPatientMonitorDevice(request.getDeviceId());

        if(patientMonitorDevice == null){
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Brak monitora: " + request.getDeviceId())
                            .asRuntimeException()
            );
            return;
        }

        patientMonitorDevice.setAlarmEnabled(request.getAlarmEnabled());

        SmartHospitalProto.OperationStatus response =
                SmartHospitalProto.OperationStatus.newBuilder()
                        .setSuccess(true)
                        .setMessage("Zmieniono stan Alarmu")
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setThresholds(SmartHospitalProto.SetMonitorThresholdsRequest request, StreamObserver<SmartHospitalProto.OperationStatus> responseObserver) {
        PatientMonitorDevice patientMonitorDevice = registry.getPatientMonitorDevice(request.getDeviceId());

        if(patientMonitorDevice == null){
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Brak monitora: " + request.getDeviceId())
                            .asRuntimeException()
            );
            return;
        }

        patientMonitorDevice.setThresholds(
                request.getThresholds().getMinHeartRate(),
                request.getThresholds().getMaxHeartRate(),
                request.getThresholds().getMinSaturation()
        );

        SmartHospitalProto.OperationStatus response =
                SmartHospitalProto.OperationStatus.newBuilder()
                        .setSuccess(true)
                        .setMessage("Ustawiono Progi alarmowe monitora ")
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

