import React from 'react';
import { User } from 'lucide-react';

type UserAvatarProps = {
  username: string;
  email: string;
};

export const UserAvatar: React.FC<UserAvatarProps> = ({ username, email }) => (
  <div className="flex items-center">
    <div className="h-10 w-10 rounded-full bg-gray-200 flex items-center justify-center">
      <User className="h-5 w-5 text-gray-500" />
    </div>
    <div className="ml-4">
      <div className="text-sm font-medium text-gray-900">{username}</div>
      <div className="text-sm text-gray-500">{email}</div>
    </div>
  </div>
);