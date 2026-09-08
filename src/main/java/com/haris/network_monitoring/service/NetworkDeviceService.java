package com.haris.network_monitoring.service;


import com.haris.network_monitoring.entity.NetworkDevice;
import com.haris.network_monitoring.repository.NetworkDeviceRepository;
import org.springframework.stereotype.Service;
import java.util.List;



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
                .orElse(null);
    }
}
