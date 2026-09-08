package com.haris.network_monitoring.repository;

import com.haris.network_monitoring.entity.NetworkDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworkDeviceRepository extends JpaRepository<NetworkDevice, Long>{
}
