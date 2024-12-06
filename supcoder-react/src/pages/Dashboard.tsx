import React from 'react';
import { Card, Row, Col, Statistic, Typography } from 'antd';
import { UserOutlined, ApiOutlined, CloudOutlined } from '@ant-design/icons';

const { Title } = Typography;

const Dashboard = () => {
  const stats = [
    { 
      icon: <UserOutlined className="text-2xl text-blue-500" />,
      title: 'Total Users',
      value: 12346,
      prefix: '',
      suffix: '',
    },
    {
      icon: <ApiOutlined className="text-2xl text-green-500" />,
      title: 'Active APIs',
      value: 45,
      prefix: '',
      suffix: '',
    },
    {
      icon: <CloudOutlined className="text-2xl text-purple-500" />,
      title: 'Weather Stations',
      value: 89,
      prefix: '',
      suffix: '',
    },
  ];

  return (
    <div className="p-6">
      <Title level={2}>Dashboard Overview</Title>
      <Row gutter={[16, 16]} className="mt-6">
        {stats.map((stat, index) => (
          <Col xs={24} sm={12} md={8} key={index}>
            <Card>
              <div className="flex items-center gap-4">
                {stat.icon}
                <Statistic
                  title={stat.title}
                  value={stat.value}
                  prefix={stat.prefix}
                  suffix={stat.suffix}
                />
              </div>
            </Card>
          </Col>
        ))}
      </Row>
    </div>
  );
};

export default Dashboard;