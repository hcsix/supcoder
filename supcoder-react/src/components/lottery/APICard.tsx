import React from 'react';
import { Activity } from 'lucide-react';
import { StatusBadge } from '../shared/StatusBadge';
import type { LotteryAPI } from '../../types';

type APICardProps = {
  api: LotteryAPI;
};

export const APICard: React.FC<APICardProps> = ({ api }) => (
  <div className="bg-white rounded-lg shadow-sm p-6">
    <div className="flex justify-between items-start">
      <div>
        <h3 className="text-lg font-medium text-gray-900">{api.name}</h3>
        <p className="text-sm text-gray-500 mt-1">{api.endpoint}</p>
      </div>
      <StatusBadge 
        status={api.status} 
        variant={api.status === 'active' ? 'success' : 'error'} 
      />
    </div>
    <div className="mt-4 flex items-center gap-4">
      <div>
        <p className="text-sm text-gray-500">Last Check</p>
        <p className="text-sm font-medium">{api.lastChecked}</p>
      </div>
      <div>
        <p className="text-sm text-gray-500">Response Time</p>
        <div className="flex items-center gap-1">
          <Activity className="h-4 w-4 text-blue-500" />
          <p className="text-sm font-medium">{api.responseTime}ms</p>
        </div>
      </div>
    </div>
  </div>
);