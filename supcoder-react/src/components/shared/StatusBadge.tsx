import React from 'react';

type StatusBadgeProps = {
  status: string;
  variant?: 'success' | 'error' | 'purple' | 'gray';
};

const variants = {
  success: 'bg-green-100 text-green-800',
  error: 'bg-red-100 text-red-800',
  purple: 'bg-purple-100 text-purple-800',
  gray: 'bg-gray-100 text-gray-800',
};

export const StatusBadge: React.FC<StatusBadgeProps> = ({ status, variant = 'success' }) => (
  <span className={`px-2 py-1 text-xs font-semibold rounded-full ${variants[variant]}`}>
    {status}
  </span>
);