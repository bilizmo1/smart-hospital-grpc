import grpc
import smart_hospital_pb2 as pb2
import smart_hospital_pb2_grpc as pb2_grpc


def create_connection():
    channel = grpc.insecure_channel("localhost:50051")
    device_stub = pb2_grpc.DeviceServiceStub(channel)
    return channel, device_stub


def list_devices(device_stub):
    return device_stub.ListDevices(pb2.Empty()).devices


def print_devices(devices):
    print("=== DEVICES ===")
    i = 0
    for dev in devices:
        print(f"{i}. {dev.device_id}, {dev.name}, {dev.type}, {dev.location}")
        i += 1


def main():
    channel, device_stub = create_connection()

    try:
        devices = list_devices(device_stub)
        print_devices(devices)

    except grpc.RpcError as e:
        print("\nRPC ERROR")
        print("code:", e.code())
        print("details:", e.details())

    finally:
        channel.close()


if __name__ == "__main__":
    main()