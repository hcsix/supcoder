import React from 'react';
import { MapPin } from 'lucide-react';
import { StatusBadge } from '../shared/StatusBadge';
import type { WeatherStation } from '../../types';

type StationCardProps = {
  station: WeatherStation;
};

export const StationCard: React.FC<StationCardProps> = ({ station }) => (
  <div className="bg-white rounded-lg shadow-sm p-6">
    <div className="flex justify-between items-start">
      <div className="flex items-start gap-3">
        <div className="mt-1">
          <MapPin className="h-5 w-5 text-blue-500" />
        </div>
        <div>
          <h3 className="text-lg font-medium text-gray-900">{station.location}</h3>
          <p className="text-sm text-gray-500 mt-1">
            {station.coordinates.lat.toFixed(4)}°N, {station.coordinates.lng.toFixed(4)}°E
          </p>
        </div>
      </div>
      <StatusBadge 
        status={station.status} 
        variant={station.status === 'online' ? 'success' : 'error'} 
      />
    </div>
    <div className="mt-4">
      <p className="text-sm text-gray-500">Last Update</p>
      <p className="text-sm font-medium">{station.lastUpdate}</p>
    </div>
  </div>
);