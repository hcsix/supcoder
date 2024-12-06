import axios from 'axios';


const BASE_AUTH_URL = '/api/auth';

export const login = async (username: string, password: string) => {
  return axios.post(`${BASE_AUTH_URL}/login`, {
    username,
    password,
  });
};