import React from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { Menu } from 'antd';
import { 
  DashboardOutlined, 
  UserOutlined, 
  ApiOutlined, 
  CloudOutlined 
} from '@ant-design/icons';

type SidebarProps = {
  collapsed: boolean;
};

const Sidebar: React.FC<SidebarProps> = ({ collapsed }) => {
  const navigate = useNavigate();
  const location = useLocation();

  const items = [
    {
      key: '/',
      icon: <DashboardOutlined />,
      label: 'Dashboard',
    },
    {
      key: '/users',
      icon: <UserOutlined />,
      label: 'User Management',
    },
    {
      key: '/lottery',
      icon: <ApiOutlined />,
      label: 'Lottery API',
    },
    {
      key: '/weather',
      icon: <CloudOutlined />,
      label: 'Weather',
    },
  ];

  return (
    <div>
      <div style={{ 
        height: '64px', 
        display: 'flex', 
        alignItems: 'center', 
        justifyContent: collapsed ? 'center' : 'flex-start',
        padding: collapsed ? '0' : '0 16px',
      }}>
        <DashboardOutlined style={{ fontSize: '24px' }} />
        {!collapsed && <span style={{ marginLeft: '12px', fontSize: '18px', fontWeight: 600 }}>Admin Portal</span>}
      </div>
      <Menu
        theme="light"
        mode="inline"
        selectedKeys={[location.pathname]}
        items={items}
        onClick={({ key }) => navigate(key)}
      />
    </div>
  );
};

export default Sidebar;