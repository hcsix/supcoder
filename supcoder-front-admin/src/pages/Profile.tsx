import React from 'react';
import { Card, Descriptions, Button } from 'antd';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { RootState } from '../store';
import { logout } from '../store/slices/authSlice';

const Profile: React.FC = () => {
  const { user } = useSelector((state: RootState) => state.auth);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogout = () => {
    dispatch(logout());
    navigate('/login');
  };

  if (!user) {
    return null;
  }

  return (
    <Card
      title="个人信息"
      extra={
        <Button type="primary" danger onClick={handleLogout}>
          退出登录
        </Button>
      }
    >
      <Descriptions bordered>
        <Descriptions.Item label="用户名">{user.username}</Descriptions.Item>
        <Descriptions.Item label="昵称">{user.nickname}</Descriptions.Item>
        <Descriptions.Item label="角色">
          {user.role === 'admin' ? '管理员' : '普通用户'}
        </Descriptions.Item>
        <Descriptions.Item label="秘钥">{user.secretKey}</Descriptions.Item>
      </Descriptions>
    </Card>
  );
};

export default Profile;