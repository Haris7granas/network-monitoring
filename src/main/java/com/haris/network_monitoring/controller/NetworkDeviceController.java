package com.haris.network_monitoring.controller;


import com.haris.network_monitoring.entity.NetworkDevice;
import com.haris.network_monitoring.service.NetworkDeviceService;
import com.haris.network_monitoring.service.NetworkMonitoringService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/api/devices")
public class NetworkDeviceController {

    private final NetworkDeviceService networkDeviceService;
    private final NetworkMonitoringService networkMonitoringService;

    public NetworkDeviceController(NetworkDeviceService networkDeviceService, NetworkMonitoringService networkMonitoringService) {
        this.networkDeviceService = networkDeviceService;
        this.networkMonitoringService = networkMonitoringService;
    }

    @GetMapping
    public List<NetworkDevice> getAllDevices() {
        return networkDeviceService.getAllDevices();
    }

    @PostMapping
    public NetworkDevice createDevice(@RequestBody NetworkDevice networkDevice) {
        return networkDeviceService.createDevice(networkDevice);
    }

    @GetMapping("/{id}")
    public NetworkDevice getDeviceById(@PathVariable Long id) {
        return networkDeviceService.getDeviceById(id);
    }

    @PutMapping("/{id}")
    public NetworkDevice updateDevice(
            @PathVariable Long id,
            @RequestBody NetworkDevice networkDevice) {
        return networkDeviceService.updateDevice(id, networkDevice);
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable Long id) {
        networkDeviceService.deleteDevice(id);
    }

    @PostMapping("/{id}/check")
    public void checkDevice(@PathVariable Long id) {
        networkMonitoringService.checkDevice(id);
    }
}
