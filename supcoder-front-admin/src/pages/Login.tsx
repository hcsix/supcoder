// src/pages/Login.tsx
import  { useEffect, useState } from 'react';
import {Form, Input, Button, Card, Spin, message, Checkbox} from 'antd';
import { login } from '../api/auth';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setToken } from '../store/authSlice';
import { RootState } from '../store';

const Login = () => {
  const [loading, setLoading] = useState(false);
  const [remember, setRemember] = useState(false);
  const [username, setUsername] = useState(localStorage.getItem('username') || '');
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const token = useSelector((state: RootState) => state.auth.token);

  useEffect(() => {
    // 如果已经登录，重定向到首页
    if (token) {
      navigate('/');
    }
  }, [token, navigate]);

  useEffect(() => {
    // 如果记住密码被勾选，则保存用户名到 localStorage
    if (remember && username) {
      localStorage.setItem('username', username);
    } else {
      localStorage.removeItem('username');
    }
  }, [remember, username]);

  const onFinish = async (values: { username: string; password: string }) => {
    setLoading(true);
    try {
      const response = await login(values.username, values.password);
      console.log('登录成功:', response.data);
      dispatch(setToken(response.data));

      // 如果记住密码被勾选，则保存用户名到 localStorage
      if (remember) {
        localStorage.setItem('username', values.username);
      } else {
        localStorage.removeItem('username');
      }

      // 等待2秒后再重定向到首页
      setTimeout(() => {
        navigate('/');
      }, 2000);
    } catch (error) {
      console.error('登录失败:', error);
      message.error('登录失败，请检查用户名和密码。');
    } finally {
      setLoading(false);
    }
  };

  return (
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
        <Card title="登录" style={{ width: 300 }}>
          <Form
              name="login"
              initialValues={{ remember, username }}
              onFinish={onFinish}
          >
            <Form.Item
                name="username"
                rules={[{ required: true, message: '请输入用户名!' }]}
            >
              <Input placeholder="用户名" onChange={(e) => setUsername(e.target.value)} />
            </Form.Item>

            <Form.Item
                name="password"
                rules={[{ required: true, message: '请输入密码!' }]}
            >
              <Input.Password placeholder="密码" />
            </Form.Item>

            <Form.Item valuePropName="checked">
              <Checkbox onChange={(e) => setRemember(e.target.checked)}>
                记住密码
              </Checkbox>
            </Form.Item>

            <Form.Item>
              <Spin spinning={loading} indicator={<Spin size="large" style={{ marginBottom: 16 }} />}>
                <Button type="primary" htmlType="submit" style={{ width: '100%' }} disabled={loading}>
                  登录
                </Button>
              </Spin>
            </Form.Item>
          </Form>
        </Card>
      </div>
  );
};

export default Login;
