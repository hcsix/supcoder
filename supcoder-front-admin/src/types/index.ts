export type User = {
  id: string;
  username: string;
  nickname: string;
  password: string;
  secretKey: string;
  email: string;
  role: 'admin' | 'user';
  status: 'active' | 'inactive';
  createdAt: string;
};

export type LotteryAPI = {
  id: string;
  name: string;
  endpoint: string;
  status: 'active' | 'inactive';
  lastChecked: string;
  responseTime: number;
  callCount: number;
  description: string;
};

export interface WeatherAPI {
  id: string;
  city: string;
  temperature: number;
  condition: string;
  callCount: number;
  lastUpdated: string;
}

export type WeatherStation = {
  id: string;
  location: string;
  coordinates: {
    lat: number;
    lng: number;
  };
  status: 'online' | 'offline';
  lastUpdate: string;
};