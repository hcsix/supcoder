import apiClient from "./apiClient.ts";


const BASE_LOTTERY_URL = '/lottery';

export const setLotteryResult = async (lotteryType: string, period: string,number: string) => {
  return apiClient.post(`${BASE_LOTTERY_URL}/result`, {
    lotteryType,
    period,
    number
  });
};