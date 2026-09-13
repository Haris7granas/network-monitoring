package com.haris.network_monitoring.service;

import com.haris.network_monitoring.entity.NetworkDevice;
import com.haris.network_monitoring.exception.DeviceNotFoundException;
import com.haris.network_monitoring.repository.NetworkDeviceRepository;
import org.springframework.stereotype.Service;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.List;

@Service
public class NetworkMonitoringService {

    private final NetworkDeviceRepository networkDeviceRepository;

    public NetworkMonitoringService(NetworkDeviceRepository networkDeviceRepository) {
        this.networkDeviceRepository = networkDeviceRepository;
    }

    public NetworkDevice getDevice(Long id) {
        return networkDeviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(id));
    }

    public void checkDevice(Long id) {
        NetworkDevice device = getDevice(id);

        try {
            Socket socket = new Socket();

            long startTime = System.currentTimeMillis();

            socket.connect(
                    new InetSocketAddress(device.getIpAddress(), device.getPort()),
                    3000
            );

            long responseTime = System.currentTimeMillis() - startTime;

            device.setStatus("UP");
            device.setResponseTime(responseTime);
            device.setLastChecked(LocalDateTime.now());

            socket.close();

        } catch (Exception e) {
            device.setStatus("DOWN");
            device.setLastChecked(LocalDateTime.now());
        }

        networkDeviceRepository.save(device);

    }

    @Scheduled(fixedRate = 30000)
    public void checkAllDevices() {
        List<NetworkDevice> devices = networkDeviceRepository.findAll();

        for (NetworkDevice device : devices) {
            checkDevice(device.getId());
        }
    }
}