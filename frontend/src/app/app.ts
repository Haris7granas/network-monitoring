import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NetworkDevice } from './model/network-device';
import { NetworkDeviceService } from './services/network-device.service';

@Component({
  imports: [RouterOutlet],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {

  constructor(private networkDeviceService: NetworkDeviceService) {
    this.networkDeviceService.getDevices().subscribe({
      next: (devices: NetworkDevice[]) => {
        console.log(devices);
        this.devices = devices;
      }
    });
  }

  protected readonly title = signal('frontend');
  name = 'Atlas Network Monitoring';
  devices: NetworkDevice[] = [];
}
