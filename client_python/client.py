import grpc
import smart_hospital_pb2 as pb2
import smart_hospital_pb2_grpc as pb2_grpc

# Funkcje do wypisania urządzeń :
def list_devices(device_stub):
    devices = device_stub.ListDevices(pb2.Empty()).devices
    return len(devices), devices

def print_devices(devices):
    i = 0
    for dev in devices:
        print(f"{i}, {dev.device_id}, {dev.name}, {dev.type}, {dev.location} ")
        i += 1

#Monitory -- funkcje:

"""
service PatientMonitorService{
  rpc GetState(DeviceIdRequest) returns (PatientMonitorState);
  rpc SetPower(SetMonitorPowerRequest) returns (OperationStatus);
  rpc SetAlarmEnabled(SetMonitorAlarmRequest) returns (OperationStatus);
  rpc SetThresholds(SetMonitorThresholdsRequest) returns (OperationStatus);
}
"""

def get_monitor_state(monitor_stub, device_id):

    state = monitor_stub.GetState(pb2.DeviceIdRequest(device_id=device_id))
    print("\n=== STAN MONITORA  ===")
    print("id:", state.device_id)
    print("name:", state.name)
    print("is_on:", state.is_on)
    print("alarm_enabled:", state.alarm_enabled)
    print("heart_rate:", state.heart_rate)
    print("saturation:", state.saturation)
    print("pressure:", f"{state.pressure.systolic}/{state.pressure.diastolic}")

def set_monitor_power(monitor_stub, device_id, is_on):
    response = monitor_stub.SetPower(pb2.SetMonitorPowerRequest(
            device_id=device_id,
            is_on=is_on
        ))
    print("\n=== SET POWER ===")
    print("success:", response.success)
    print("message:", response.message)

def set_alarm_enabled(monitor_stub, device_id, alarm_enabled):
    response = monitor_stub.SetAlarmEnabled(pb2.SetMonitorAlarmRequest(
        device_id=device_id,
        alarm_enabled =  alarm_enabled
    ))
    print("success:", response.success)
    print("message:", response.message)

def set_monitor_thresholds(monitor_stub, device_id, min_hr, max_hr, min_sat):
    thresholds = pb2.MonitorAlarmThresholds(
        min_heart_rate=min_hr,
        max_heart_rate=max_hr,
        min_saturation=min_sat
    )
    response = monitor_stub.SetThresholds(pb2.SetMonitorThresholdsRequest(
        device_id=device_id,
        thresholds=thresholds
    ))
    print("success:", response.success)
    print("message:", response.message)


# Łóżka --  funkcje
"""
service HospitalBedService{
  rpc GetState(DeviceIdRequest) returns (HospitalBedState);
  rpc SetPower(SetBedPowerRequest) returns (OperationStatus);
  rpc SetHeight(SetHeightRequest)  returns (OperationStatus);
  rpc SetWheelsLock(SetWheelsLockRequest) returns (OperationStatus);
  rpc SetOccupied(SetOccupiedRequest) returns (OperationStatus);
  rpc SetEmergencyMode(SetEmergencyModeRequest) returns (OperationStatus);
}

"""


def get_bed_state(bed_stub, device_id):
    state = bed_stub.GetState(pb2.DeviceIdRequest(
        device_id=device_id
    ))
    print("\n=== BED STATE ===")
    print("bed_id:", state.device_id)
    print("name:", state.name)
    print("is_on:", state.is_on)
    print("bed_type:", state.bed_type)
    print("height:", state.height)
    print("wheels_locked:", state.wheels_locked)
    print("emergency_mode:", state.emergency_mode)
    print("occupied:", state.occupied)

def set_bed_power(bed_stub, device_id, is_on):
    response = bed_stub.SetPower(
        pb2.SetBedPowerRequest(
            device_id=device_id,
            is_on=is_on
        )
    )

    print("\n=== SET BED POWER ===")
    print("success:", response.success)
    print("message:", response.message)


def set_bed_height(bed_stub, device_id, height):
    response = bed_stub.SetHeight(
        pb2.SetHeightRequest(
            device_id=device_id,
            height=height
        )
    )

    print("\n=== SET BED HEIGHT ===")
    print("success:", response.success)
    print("message:", response.message)


def set_wheels_locked(bed_stub, device_id, wheels_locked):
    response = bed_stub.SetWheelsLock(
        pb2.SetWheelsLockRequest(
            device_id=device_id,
            wheels_locked=wheels_locked
        )
    )

    print("\n=== SET BED WHEELS LOCK ===")
    print("success:", response.success)
    print("message:", response.message)


def set_occupied(bed_stub, device_id, occupied):
    response = bed_stub.SetOccupied(
        pb2.SetOccupiedRequest(
            device_id=device_id,
            occupied=occupied
        )
    )

    print("\n=== SET BED OCCUPIED ===")
    print("success:", response.success)
    print("message:", response.message)


def set_emergency_mode(bed_stub, device_id, emergency_mode):
    response = bed_stub.SetEmergencyMode(
        pb2.SetEmergencyModeRequest(
            device_id=device_id,
            emergency_mode=emergency_mode
        )
    )

    print("\n=== SET BED EMERGENCY MODE ===")
    print("success:", response.success)
    print("message:", response.message)


#Kamery -- funkcje

"""
service HospitalCameraService{
  rpc GetState(DeviceIdRequest) returns (HospitalCameraState);
  rpc SetPower(SetCameraPowerRequest) returns (OperationStatus);
  rpc SetRecording(SetRecordingRequest) returns (OperationStatus);
  rpc MoveCamera(MoveCameraRequest) returns (OperationStatus);
}
"""



def get_camera_state(camera_stub, device_id):
    state = camera_stub.GetState(pb2.DeviceIdRequest(
        device_id=device_id
    ))
    print("\n=== CAMERA STATE ===")
    print("camera_id:", state.device_id)
    print("name:", state.name)
    print("camera_type:", state.camera_type)
    print("is_on:", state.is_on)
    print("is_recording:", state.is_recording)
    print("pan:", state.pan)
    print("tilt:", state.tilt)
    print("zoom:", state.zoom)

def set_camera_power(camera_stub, device_id, is_on):
    response = camera_stub.SetPower(
        pb2.SetCameraPowerRequest(
            device_id=device_id,
            is_on=is_on
        )
    )

    print("\n=== SET CAMERA POWER ===")
    print("success:", response.success)
    print("message:", response.message)


def set_recording(camera_stub, device_id, is_recording):
    response = camera_stub.SetRecording(
        pb2.SetRecordingRequest(
            device_id=device_id,
            is_recording = is_recording
        )
    )

    print("\n=== SET Recording ===")
    print("success:", response.success)
    print("message:", response.message)


def move_camera(camera_stub, device_id, pan, tilt, zoom):
    response = camera_stub.MoveCamera(
        pb2.MoveCameraRequest(
            device_id=device_id,
            pan = pan,
            tilt = tilt,
            zoom = zoom
        )
    )

    print("\n=== MOVE CAMERA ===")
    print("success:", response.success)
    print("message:", response.message)



# Menu dla monitora
"""
Co udostępnia Menu:
- pokaż stan monitora 
- włącz monitor 
- wyłącz monitor 
- włącz alarm
- wyłącz alarm
- ustaw progi alarmowe 
-

"""
def monitor_menu(monitor_stub, device_id):
    while True:
        print(f"\n=== MENU MONITORA: {device_id} ===")
        print("1. Pokaż stan monitora")
        print("2. Włącz monitor")
        print("3. Wyłącz monitor")
        print("4. Włącz alarm")
        print("5. Wyłącz alarm")
        print("6. Ustaw progi alarmowe")
        print("0. Wróć")

        choice = input("Wybierz opcję: ").strip()

        if choice == "0":
            break
        elif choice == "1":
            get_monitor_state(monitor_stub, device_id)
        elif choice == "2":
            set_monitor_power(monitor_stub, device_id, True)
            get_monitor_state(monitor_stub, device_id)

        elif choice == "3":
            set_monitor_power(monitor_stub, device_id, False)
            get_monitor_state(monitor_stub, device_id)

        elif choice == "4":
            set_alarm_enabled(monitor_stub, device_id, True)
            get_monitor_state(monitor_stub, device_id)


        elif choice == "5":
            set_alarm_enabled(monitor_stub, device_id, False)
            get_monitor_state(monitor_stub, device_id)

        elif choice == "6":
            min_hr = int(input("Podaj minimalne tętno: "))
            max_hr = int(input("Podaj maksymalne tętno: "))
            min_sat = int(input("Podaj minimalną saturację: "))

            set_monitor_thresholds(monitor_stub, device_id, min_hr, max_hr, min_sat)
            get_monitor_state(monitor_stub, device_id)


# Menu dla łózka
"""
Co udostępnia Bed :
- pokaż stan Łóżka 
- włącz monitor 
- wyłącz monitor 
- włącz alarm
- wyłącz alarm
- ustaw progi alarmowe 
-

"""

def bed_menu(bed_stub, device_id):
    while True:
        print(f"\n=== MENU ŁÓŻKA: {device_id} ===")
        print("1. Pokaż stan łóżka")
        print("2. Włącz łóżko")
        print("3. Wyłącz łóżko")
        print("4. Ustaw wysokość")
        print("5. Zablokuj kółka")
        print("6. Odblokuj kółka")
        print("7. Oznacz jako zajęte")
        print("8. Oznacz jako wolne")
        print("9. Włącz tryb awaryjny")
        print("10. Wyłącz tryb awaryjny")
        print("0. Wróć")

        choice = input("Wybierz opcję: ").strip()

        if choice == "0":
            break

        elif choice == "1":
            get_bed_state(bed_stub, device_id)

        elif choice == "2":
            set_bed_power(bed_stub, device_id, True)
            get_bed_state(bed_stub, device_id)

        elif choice == "3":
            set_bed_power(bed_stub, device_id, False)
            get_bed_state(bed_stub, device_id)

        elif choice == "4":
            height = int(input("Podaj wysokość łóżka: "))
            set_bed_height(bed_stub, device_id, height)
            get_bed_state(bed_stub, device_id)

        elif choice == "5":
            set_wheels_locked(bed_stub, device_id, True)
            get_bed_state(bed_stub, device_id)

        elif choice == "6":
            set_wheels_locked(bed_stub, device_id, False)
            get_bed_state(bed_stub, device_id)

        elif choice == "7":
            set_occupied(bed_stub, device_id, True)
            get_bed_state(bed_stub, device_id)

        elif choice == "8":
            set_occupied(bed_stub, device_id, False)
            get_bed_state(bed_stub, device_id)

        elif choice == "9":
            set_emergency_mode(bed_stub, device_id, True)
            get_bed_state(bed_stub, device_id)

        elif choice == "10":
            set_emergency_mode(bed_stub, device_id, False)
            get_bed_state(bed_stub, device_id)

        else:
            print("Nieprawidłowa opcja.")


# Menu dla kamer
"""
Co udostępnia Kamera:
- pokaż stan monitora 
- włącz monitor 
- wyłącz monitor 
- włącz alarm
- wyłącz alarm
- ustaw progi alarmowe 
-

"""
def camera_menu(camera_stub, device_id):
    while True:
        print(f"\n=== MENU KAMERY: {device_id} ===")
        print("1. Pokaż stan kamery")
        print("2. Włącz kamerę")
        print("3. Wyłącz kamerę")
        print("4. Włącz nagrywanie")
        print("5. Wyłącz nagrywanie")
        print("6. Ustaw pozycję kamery")
        print("0. Wróć")

        choice = input("Wybierz opcję: ").strip()

        if choice == "0":
            break

        elif choice == "1":
            get_camera_state(camera_stub, device_id)

        elif choice == "2":
            set_camera_power(camera_stub, device_id, True)
            get_camera_state(camera_stub, device_id)

        elif choice == "3":
            set_camera_power(camera_stub, device_id, False)
            get_camera_state(camera_stub, device_id)

        elif choice == "4":
            set_recording(camera_stub, device_id, True)
            get_camera_state(camera_stub, device_id)

        elif choice == "5":
            set_recording(camera_stub, device_id, False)
            get_camera_state(camera_stub, device_id)

        elif choice == "6":
            pan = int(input("Podaj pan: "))
            tilt = int(input("Podaj tilt: "))
            zoom = int(input("Podaj zoom: "))

            move_camera(camera_stub, device_id, pan, tilt, zoom)
            get_camera_state(camera_stub, device_id)

        else:
            print("Nieprawidłowa opcja.")



def choose_ward():
    print("Wybierz oddział:")
    print("1. Kardiologia")
    print("2. Chirurgia")

    choice = input("Twój wybór: ").strip()
    if choice == "1":
        return "CardiologyWard", "localhost", 50051
    elif choice == "2":
        return "SurgeryWard", "localhost", 50052
    else:
        print("Nieprawidłowy wybór.")
        return None, None, None


#łączenie z serwerem

def create_connection(host, port):
    address = f"{host}:{port}"
    channel = grpc.insecure_channel(address)
    device_stub = pb2_grpc.DeviceServiceStub(channel)
    monitor_stub = pb2_grpc.PatientMonitorServiceStub(channel)
    bed_stub = pb2_grpc.HospitalBedServiceStub(channel)
    camera_stub = pb2_grpc.HospitalCameraServiceStub(channel)

    return channel, device_stub, monitor_stub, bed_stub, camera_stub


def main_menu():
    print("Witam w panelu medycznym")

    ward, host, port = choose_ward()

    if host is None:
        return

    channel, device_stub, monitor_stub, bed_stub, camera_stub = create_connection(host,port)
    try:
        while True:
            n,devices = list_devices(device_stub)
            print_devices(devices)
            choice = input("\nWybierz numer urządzenia lub q aby wyjść: ").strip()
            index = int(choice)
            if choice.lower() == "q":
                break
            elif not choice.isdigit():
                print("Podaj numer albo q")
                continue
            elif  index < 0 or index > n -1:
                print(f" Podaj numer w zakresie: 0 do {n-1} ")
                continue
            else:
                selected_device = devices[index]
                print(f"\nWybrano: {selected_device.device_id} | {selected_device.name}")

                if selected_device.type == pb2.PATIENT_MONITOR:
                    monitor_menu(monitor_stub, selected_device.device_id)
                elif selected_device.type == pb2.PATIENT_BED:
                    bed_menu(bed_stub, selected_device.device_id)
                elif selected_device.type == pb2.HOSPITAL_CAMERA:
                    camera_menu(camera_stub, selected_device.device_id)
                else:
                    print("Nieznany typ urządzenia.")
    except grpc.RpcError as e:
        print("\nRPC ERROR")
        print("code:", e.code())
        print("details:", e.details())
    finally:
        channel.close()



if __name__ == "__main__":
    main_menu()