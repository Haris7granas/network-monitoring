import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NetworkDevice } from './model/network-device';
import { NetworkDeviceService } from './services/network-device.service';
import { DatePipe } from '@angular/common';
import { interval } from 'rxjs';

@Component({
  imports: [RouterOutlet, DatePipe],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {

  constructor(private networkDeviceService: NetworkDeviceService) {
    this.loadDevices();

    interval(35000).subscribe(() => {
          this.loadDevices();
    });
  }

  loadDevices(): void {
  this.networkDeviceService.getDevices().subscribe({
    next: (devices: NetworkDevice[]) => {
      console.log(devices);
      this.devices.set(devices);
      this.apiConnected.set(true);
      this.lastRefresh.set(new Date());
    },
    error: (error) => {
      console.error('API connection error:', error);
      this.apiConnected.set(false);
    }
  });
}

  protected readonly title = signal('frontend');
  name = 'Atlas Network Monitoring';
  devices = signal<NetworkDevice[]>([]);
  lastRefresh = signal<Date | null>(null);
  apiConnected = signal<boolean>(false);
  getOnlineDevices(): number {
   return this.devices().filter(device => device.status === 'UP').length;
  }
}
