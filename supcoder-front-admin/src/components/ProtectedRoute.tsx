// src/components/ProtectedRoute.tsx
import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { RootState } from '../store';

const ProtectedRoute: React.FC = () => {
    const token = useSelector((state: RootState) => state.auth.token);
    if (!token) {
        return <Navigate to="/login" />;
    }
    return <Outlet />;
};

export default ProtectedRoute;
