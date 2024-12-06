import axios from 'axios';


const BASE_LOTTERY_URL = '/api/lottery';

export const setLotteryResult = async (lotteryType: string, period: string,number: string) => {
  return axios.post(`${BASE_LOTTERY_URL}/result`, {
    lotteryType,
    period,
    number
  });
};