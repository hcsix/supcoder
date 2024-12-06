// src/api/apiClient.ts
import axios, { AxiosInstance } from 'axios';

const apiClient: AxiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL , // 根据环境变量设置 baseUrl
    timeout: 10000, // 设置请求超时时间
    headers: {
        'Content-Type': 'application/json',
    },
});

export default apiClient;
