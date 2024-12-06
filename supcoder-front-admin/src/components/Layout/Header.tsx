import React from 'react';
import { Layout, Avatar, Badge, Space, Typography, Switch, Select } from 'antd';
import { BellOutlined, UserOutlined } from '@ant-design/icons';
import { useTranslation } from 'react-i18next';
import { useTheme } from '../../contexts/ThemeContext';

const { Header: AntHeader } = Layout;
const { Text } = Typography;

type HeaderProps = {
  children?: React.ReactNode;
};

const Header: React.FC<HeaderProps> = ({ children }) => {
  const { i18n } = useTranslation();
  const { isDarkMode, toggleTheme } = useTheme();

  return (
    <AntHeader style={{ padding: '0 16px', background: 'var(--header-bg)', display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        {children}
      </div>
      <Space size={16}>
        <Select
          value={i18n.language}
          onChange={(value) => i18n.changeLanguage(value)}
          options={[
            { value: 'en', label: 'English' },
            { value: 'zh', label: '中文' },
          ]}
        />
        <Switch
          checked={isDarkMode}
          onChange={toggleTheme}
          checkedChildren="🌙"
          unCheckedChildren="☀️"
        />
        <Badge dot>
          <BellOutlined style={{ fontSize: '18px' }} />
        </Badge>
        <Space>
          <Avatar icon={<UserOutlined />} />
          <Text>Admin User</Text>
        </Space>
      </Space>
    </AntHeader>
  );
};

export default Header;