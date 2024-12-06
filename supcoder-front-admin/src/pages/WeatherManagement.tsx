import React from 'react';
import { Card, Button, Space, Tag, Typography, Row, Col } from 'antd';
import { PlusOutlined, EnvironmentOutlined } from '@ant-design/icons';
import type { WeatherStation } from '../types';

const { Title, Text } = Typography;

const mockStations: WeatherStation[] = [
  {
    id: '1',
    location: 'Beijing Central',
    coordinates: { lat: 39.9042, lng: 116.4074 },
    status: 'online',
    lastUpdate: '2024-03-15 14:30:00',
  },
  {
    id: '2',
    location: 'Shanghai Station',
    coordinates: { lat: 31.2304, lng: 121.4737 },
    status: 'offline',
    lastUpdate: '2024-03-15 14:25:00',
  },
];

const WeatherManagement = () => {
  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <Title level={2}>Weather Station Management</Title>
        <Button type="primary" icon={<PlusOutlined />}>
          Add Station
        </Button>
      </div>
      <Row gutter={[16, 16]}>
        {mockStations.map((station) => (
          <Col xs={24} md={12} key={station.id}>
            <Card>
              <div className="flex justify-between items-start mb-4">
                <Space>
                  <EnvironmentOutlined className="text-blue-500 text-xl" />
                  <div>
                    <Title level={4} style={{ margin: 0 }}>{station.location}</Title>
                    <Text type="secondary">
                      {station.coordinates.lat.toFixed(4)}°N, {station.coordinates.lng.toFixed(4)}°E
                    </Text>
                  </div>
                </Space>
                <Tag color={station.status === 'online' ? 'success' : 'error'}>
                  {station.status}
                </Tag>
              </div>
              <Text>
                Last Update: <Text strong>{station.lastUpdate}</Text>
              </Text>
            </Card>
          </Col>
        ))}
      </Row>
    </div>
  );
};

export default WeatherManagement;