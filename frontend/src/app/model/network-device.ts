export interface NetworkDevice {
  id: number;
  name: string;
  ipAddress: string;
  hostname: string;
  type: string;
  status: string;
  port: number;
  responseTime: number | null;
  lastChecked: string | null;
  createdAt: string | null;
}