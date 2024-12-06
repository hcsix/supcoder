import { Card, Button, Space, Tag, Typography, Row, Col } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import type { LotteryAPI } from '../types';

const { Title, Text } = Typography;

const mockAPIs: LotteryAPI[] = [
  {
    id: '1',
    name: 'National Lottery API',
    endpoint: 'https://api.lottery.com/v1/national',
    status: 'active',
    lastChecked: '2024-03-15 14:30:00',
    responseTime: 245,
  },
  {
    id: '2',
    name: 'State Lottery API',
    endpoint: 'https://api.lottery.com/v1/state',
    status: 'inactive',
    lastChecked: '2024-03-15 14:25:00',
    responseTime: 532,
  },
];

const LotteryAPIManagement = () => {
  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <Title level={2}>Lottery API Management</Title>
        <Button type="primary" icon={<PlusOutlined />}>
          Add API
        </Button>
      </div>
      <Row gutter={[16, 16]}>
        {mockAPIs.map((api) => (
          <Col xs={24} md={12} key={api.id}>
            <Card>
              <div className="flex justify-between items-start mb-4">
                <div>
                  <Title level={4}>{api.name}</Title>
                  <Text type="secondary">{api.endpoint}</Text>
                </div>
                <Tag color={api.status === 'active' ? 'success' : 'error'}>
                  {api.status}
                </Tag>
              </div>
              <Space direction="vertical">
                <Text>
                  Last Check: <Text strong>{api.lastChecked}</Text>
                </Text>
                <Text>
                  Response Time: <Text strong>{api.responseTime}ms</Text>
                </Text>
              </Space>
            </Card>
          </Col>
        ))}
      </Row>
    </div>
  );
};

export default LotteryAPIManagement;