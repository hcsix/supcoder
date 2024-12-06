// src/pages/Register.tsx
import { Form, Input, Button, Card, Spin, message } from 'antd';
import { useNavigate } from 'react-router-dom';

const RegisterItem = () => {
    const navigate = useNavigate();

    const onFinish = async (values: { username: string; password: string }) => {
        // 这里需要调用注册API
        try {
            // 假设有一个 register 函数
            // await register(values.username, values.password);
            console.log('注册成功:', values);
            message.success('注册成功，请登录。');
            navigate('/login');
        } catch (error) {
            console.error('注册失败:', error);
            message.error('注册失败，请重试。');
        }
    };

    return (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <Card title="注册" style={{ width: 300 }}>
                <Form name="register" onFinish={onFinish}>
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
                        <Spin spinning={false}>
                            <Button type="primary" htmlType="submit" style={{ width: '100%' }}>
                                注册
                            </Button>
                        </Spin>
                    </Form.Item>
                    <Button type="link" onClick={() => navigate('/login')}>
                        已有账号？登录
                    </Button>
                </Form>
            </Card>
        </div>
    );
};

export default RegisterItem;
