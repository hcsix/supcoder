import React from 'react';
import { Form, Input, Button, Card, message } from 'antd';
import { useNavigate } from 'react-router-dom';

const Register: React.FC = () => {
  const navigate = useNavigate();

  const onFinish = async (values: any) => {
    try {
      // TODO: Replace with actual API call
      message.success('注册成功');
      navigate('/login');
    } catch (error) {
      message.error('注册失败');
    }
  };

  return (
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
      <Card title="注册" style={{ width: 400 }}>
        <Form
          name="register"
          onFinish={onFinish}
          autoComplete="off"
        >
          <Form.Item
            name="username"
            rules={[{ required: true, message: '请输入用户名!' }]}
          >
            <Input placeholder="用户名" />
          </Form.Item>

          <Form.Item
            name="nickname"
            rules={[{ required: true, message: '请输入昵称!' }]}
          >
            <Input placeholder="昵称" />
          </Form.Item>

          <Form.Item
            name="password"
            rules={[{ required: true, message: '请输入密码!' }]}
          >
            <Input.Password placeholder="密码" />
          </Form.Item>

          <Form.Item
            name="confirmPassword"
            dependencies={['password']}
            rules={[
              { required: true, message: '请确认密码!' },
              ({ getFieldValue }) => ({
                validator(_, value) {
                  if (!value || getFieldValue('password') === value) {
                    return Promise.resolve();
                  }
                  return Promise.reject(new Error('两次输入的密码不匹配!'));
                },
              }),
            ]}
          >
            <Input.Password placeholder="确认密码" />
          </Form.Item>

          <Form.Item>
            <Button type="primary" htmlType="submit" style={{ width: '100%' }}>
              注册
            </Button>
          </Form.Item>
          
          <Button type="link" onClick={() => navigate('/login')}>
            返回登录
          </Button>
        </Form>
      </Card>
    </div>
  );
};

export default Register;