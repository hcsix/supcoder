import { useState } from 'react';
import { Form, Input, Button, Card, Spin, message } from 'antd';
import { login } from '../api/auth.ts';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const onFinish = async (values: { username: string; password: string }) => {
    setLoading(true);
    try {
      const response = await login(values.username, values.password);
      console.log('登录成功:', response.data);
      localStorage.setItem('token', response.data.token);
      navigate('/');
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
          initialValues={{ remember: true }}
          onFinish={onFinish}
        >
          <Form.Item
            name="username"
            rules={[{ required: true, message: '请输入用户名!' }]}
          >
            <Input placeholder="用户名" />
          </Form.Item>

          <Form.Item
            name="password"
            rules={[{ required: true, message: '请输入密码!' }]}
          >
            <Input.Password placeholder="密码" />
          </Form.Item>

          <Form.Item>
            <Spin spinning={loading}>
              <Button type="primary" htmlType="submit" style={{ width: '100%' }} disabled={loading}>
                登录
              </Button>
            </Spin>
          </Form.Item>
        </Form>
      </Card>
    </div>
  );
}

export default Login;