import requests
from bs4 import BeautifulSoup
import re

# 目标网址
url = 'https://kaijiang.500.com/'

def fetch_lottery_data(url):
    # 发起HTTP请求获取网页内容
    response = requests.get(url)
    if response.status_code != 200:
        print("请求失败，状态码：", response.status_code)
        return {}

    # 解析HTML内容
    soup = BeautifulSoup(response.content, 'html.parser')
    script_tags = soup.find_all('script')
    lottery_data = {}

    # 提取所有彩票数据
    rows = soup.find_all('tr')
    for row in rows:
        # 获取彩票类型
        script_tag = row.find('script', string=re.compile(r"formatResult\('(\w+)'"))
        if script_tag and script_tag.string:
            match = re.search(r"formatResult\('(\w+)'", script_tag.string)
            if match:
                lottery_type = match.group(1)

                # 提取期号和开奖时间
                name = row.find_all('td')[0].get_text(strip=True)
                period = row.find_all('td')[1].get_text(strip=True)
                date = row.find_all('td')[2].get_text(strip=True)
                draw_date = row.find_all('td')[5].get_text(strip=True)

                # 初始化彩票数据字典
                if lottery_type not in lottery_data:
                    lottery_data[lottery_type] = {}

                # 存储彩票信息
                lottery_data[lottery_type]['period'] = period
                lottery_data[lottery_type]['date'] = date
                lottery_data[lottery_type]['name'] = name
                lottery_data[lottery_type]['draw_date'] = draw_date

    # 提取结果和奖金
    for script in script_tags:
        if script.string:
            data = extract_lottery_data(script.string)
            for key, value in data.items():
                if key in lottery_data:
                    # 合并现有数据
                    lottery_data[key].update(value)
                else:
                    # 添加新数据
                    lottery_data[key] = value

    return lottery_data

def extract_lottery_data(script_text):
    # 正则表达式匹配彩票结果和奖金
    result_pattern = re.compile(r"formatResult\('(\w+)','([^']*)'\)")
    money_pattern = re.compile(r"formatCCMoney\('(\w+)','([^']*)'\)")
    results = result_pattern.findall(script_text)
    moneys = money_pattern.findall(script_text)

    lottery_data = {}
    # 提取彩票结果
    for result, numbers in results:
        print(f"Extracted numbers: {result} -> {numbers}")
        if result not in lottery_data:
            lottery_data[result] = {}
        lottery_data[result]['numbers'] = numbers
    # 提取彩票奖金
    for result, money in moneys:
        print(f"Extracted money: {result} -> {money}")
        if result not in lottery_data:
            lottery_data[result] = {}
        lottery_data[result]['money'] = money
    return lottery_data

def print_lottery_data(lottery_data):
    # 打印彩票数据
    for lottery, data in lottery_data.items():
        print("--------------------------------")
        try:
            print(f"Lottery: {lottery}")
            if 'name' in data:
                print(f"name: {data['name']}")
            if 'numbers' in data:
                print(f"Numbers: {data['numbers']}")
            if 'money' in data:
                print(f"Money: {data['money']}")
            if 'period' in data:
                print(f"Period: {data['period']}")
            if 'date' in data:
                print(f"Date: {data['date']}\n")
            if 'draw_date' in data:
                print(f"draw_date: {data['draw_date']}\n")
         
        except KeyError as e:
            print(f"数据缺失: {e}")

if __name__ == "__main__":
    # 主程序入口
    lottery_data = fetch_lottery_data(url)
    print_lottery_data(lottery_data)