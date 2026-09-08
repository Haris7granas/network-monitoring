package com.haris.network_monitoring.service;


import com.haris.network_monitoring.entity.NetworkDevice;
import com.haris.network_monitoring.repository.NetworkDeviceRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.haris.network_monitoring.exception.DeviceNotFoundException;



@Service
public class NetworkDeviceService {

    private final NetworkDeviceRepository networkDeviceRepository;

    public NetworkDeviceService(NetworkDeviceRepository networkDeviceRepository) {
        this.networkDeviceRepository = networkDeviceRepository;
    }

    public List<NetworkDevice> getAllDevices() {
        return networkDeviceRepository.findAll();
    }

    public NetworkDevice createDevice(NetworkDevice networkDevice) {
        return networkDeviceRepository.save(networkDevice);
    }

    public NetworkDevice getDeviceById(Long id) {
        return networkDeviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(id));
    }

    public NetworkDevice updateDevice(Long id, NetworkDevice updatedDevice) {
        NetworkDevice existingDevice = networkDeviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(id));

        existingDevice.setName(updatedDevice.getName());
        existingDevice.setIpAddress(updatedDevice.getIpAddress());
        existingDevice.setHostname(updatedDevice.getHostname());
        existingDevice.setType(updatedDevice.getType());
        existingDevice.setStatus(updatedDevice.getStatus());
        existingDevice.setPort(updatedDevice.getPort());
        existingDevice.setResponseTime(updatedDevice.getResponseTime());

        return networkDeviceRepository.save(existingDevice);
    }

    public void deleteDevice(Long id) {
        NetworkDevice existingDevice = networkDeviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(id));

        networkDeviceRepository.delete(existingDevice);
    }
}
