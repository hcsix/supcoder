export type User = {
  id: string;
  username: string;
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
};

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