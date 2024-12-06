import apiClient from "./apiClient.ts";


const BASE_AUTH_URL = '/auth';

export const login = async (username: string, password: string) => {
  return apiClient.post(`${BASE_AUTH_URL}/login`, {
    username,
    password,
  });
};