// src/pages/Login.tsx
import  { useEffect, useState } from 'react';
import { Form, Input, Button, Card, Spin, message, Checkbox } from 'antd';
import { login } from '../../api/auth';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../../store';
import { setCredentials } from "../../store/slices/authSlice";
import bannerImage from '../../assets/banner.jpg'; // 假设有一张 banner 图片
import styled from 'styled-components';

const BannerContainer = styled.div`     flex: 1;
     display: none;
     @media (min-width: 768px) {
       display: block;
     }
   `;

const Auth = () => {
    const [loading, setLoading] = useState(false);
    const [remember, setRemember] = useState(false);
    const [username, setUsername] = useState(localStorage.getItem('username') || '');
    const [isRegister, setIsRegister] = useState(false);
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const token = useSelector((state: RootState) => state.auth.token);

    useEffect(() => {
        if (token) {
            navigate('/');
        }
    }, [token, navigate]);

    useEffect(() => {
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
            const authData = {
                user: {
                    id: '1',
                    username: values.username,
                    nickname: 'Test User',
                    role: 'user',
                    secretKey: 'test-key',
                    password: '',
                },
                token: response.data,
            };

            dispatch(setCredentials(authData));

            if (remember) {
                localStorage.setItem('username', values.username);
            } else {
                localStorage.removeItem('username');
            }

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

    const toggleForm = () => {
        setIsRegister(!isRegister);
    };

    return (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <BannerContainer>
                <img src={bannerImage} alt="Banner" style={{ width: '100%', height: '100vh', objectFit: 'cover' }} />
            </BannerContainer>
            <div style={{ flex: 1, padding: '20px' }}>
                <Card title={isRegister ? "注册" : "登录"} style={{ width: 300, margin: 'auto' }}>
                    {!isRegister ? (
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
                            <Button type="link" onClick={toggleForm}>
                                没有账号？注册
                            </Button>
                        </Form>
                    ) : (
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
                            <Button type="link" onClick={toggleForm}>
                                已有账号？登录
                            </Button>
                        </Form>
                    )}
                </Card>
            </div>
        </div>
    );
};

export default Auth;
