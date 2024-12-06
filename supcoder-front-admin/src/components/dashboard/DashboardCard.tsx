import React from 'react';

type DashboardCardProps = {
  icon: React.ElementType;
  title: string;
  value: string;
  change: string;
};

export const DashboardCard: React.FC<DashboardCardProps> = ({ icon: Icon, title, value, change }) => (
  <div className="bg-white rounded-lg p-6 shadow-sm">
    <div className="flex items-center justify-between">
      <div>
        <p className="text-sm text-gray-600">{title}</p>
        <p className="text-2xl font-semibold mt-1">{value}</p>
      </div>
      <div className="bg-blue-50 p-3 rounded-full">
        <Icon className="h-6 w-6 text-blue-600" />
      </div>
    </div>
    <p className="text-sm text-gray-600 mt-4">
      <span className={change.startsWith('+') ? 'text-green-500' : 'text-red-500'}>
        {change}
      </span>
      {' '}vs last month
    </p>
  </div>
);