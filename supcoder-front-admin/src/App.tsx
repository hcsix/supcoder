// src/App.tsx
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { ConfigProvider } from 'antd';
import { ThemeProvider } from './contexts/ThemeContext';
import { Provider } from 'react-redux';
import { store } from './store';
import MainLayout from './components/Layout/MainLayout';
import Dashboard from './pages/Dashboard';
import UserManagement from './pages/UserManagement';
import LotteryAPI from './pages/LotteryAPI';
import WeatherAPI from './pages/WeatherAPI';
import Login from './pages/Login';
import './i18n';
import ProtectedRoute from "./components/ProtectedRoute.tsx";

function App() {
  return (
      <Provider store={store}>
        <ThemeProvider>
          <ConfigProvider>
            <BrowserRouter>
              <Routes>
                <Route path="/login" element={<Login />} />
                <Route
                    path="/"
                    element={
                      <ProtectedRoute>
                        <MainLayout />
                      </ProtectedRoute>
                    }
                >
                  <Route index={true} element={<Dashboard />} />
                  <Route path="users" element={<UserManagement />} />
                  <Route path="lottery" element={<LotteryAPI />} />
                  <Route path="weather" element={<WeatherAPI />} />
                </Route>
              </Routes>
            </BrowserRouter>
          </ConfigProvider>
        </ThemeProvider>
      </Provider>
  );
}

export default App;
