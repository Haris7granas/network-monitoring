import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NetworkDevice } from '../model/network-device';

@Injectable()
export class NetworkDeviceService {

    constructor(private http: HttpClient) {
    }

    getDevices(): Observable<NetworkDevice[]> {
        return this.http.get<NetworkDevice[]>('http://localhost:8080/api/devices');
    }
}