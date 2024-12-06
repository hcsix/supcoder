import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { ConfigProvider } from 'antd';
import { ThemeProvider } from './contexts/ThemeContext';
import MainLayout from './components/Layout/MainLayout';
import Dashboard from './pages/Dashboard';
import UserManagement from './pages/UserManagement';
import LotteryAPI from './pages/LotteryAPI';
import WeatherAPI from './pages/WeatherAPI';
import Login from './pages/Login';
import './i18n';

function App() {
  const isLoggedIn = () => {
    // 这里应该是你的登录状态检查逻辑
    return false; // 示例：假设用户未登录
  };

  return (
    <ThemeProvider>
      <ConfigProvider>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={isLoggedIn() ? <MainLayout /> : <Navigate to="/login" />} />
            <Route path="/login" element={<Login />} />
            {/* 如果用户已登录，渲染以下路由 */}
            {isLoggedIn() && (
              <>
              <Route index element={<Dashboard />} />
                <Route path="users" element={<UserManagement />} />
                <Route path="lottery" element={<LotteryAPI />} />
                <Route path="weather" element={<WeatherAPI />} />
              </>
            )}
          </Routes>
        </BrowserRouter>
      </ConfigProvider>
    </ThemeProvider>
  );
}

export default App;