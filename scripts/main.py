import requests
import json
import socket
import time
from datetime import datetime
from cryptography.hazmat.primitives.asymmetric import padding
from cryptography.hazmat.primitives import hashes, serialization
from cryptography.hazmat.primitives.asymmetric import rsa
from fetch_lottery import fetch_lottery_data
from fetch_lottery import format_lottery_data
import logging


# 读取配置文件
def load_config(config_path="config.json"):
    with open(config_path, "r") as config_file:
        config = json.load(config_file)
    return config


def encrypt_data(public_key, data):
    return public_key.encrypt(
        data.encode(),
        padding.OAEP(
            mgf=padding.MGF1(algorithm=hashes.SHA256()),
            algorithm=hashes.SHA256(),
            label=None
        )
    )


def send_lottery_data(lottery_data):
    # # 获取本机IP
    # local_ip = socket.gethostbyname(socket.gethostname())
    # # 获取当前时间戳
    # timestamp = str(int(time.time()))
    #
    # # 加载公钥
    # with open("public_key.pem", "rb") as key_file:
    #     public_key = serialization.load_pem_public_key(key_file.read())
    #
    # # 加密信息
    # encrypted_info = encrypt_data(public_key, f"{local_ip},{timestamp}")
    #
    # 准备请求数据
    data = {
        "lotteryItems": lottery_data,
        "secretKey": "encrypted_info.hex()"  # 将加密数据转换为十六进制字符串
    }

    # 发送POST请求
    response = requests.post(config["api_url"], json=data)
    if config["print_log"]:
        logging.info(f"Response status code: {response.status_code}")
        logging.info(f"Response content: {response.content}")


def write_json_file(data):
    if config["write_file"]:
        # 获取当前日期并格式化为字符串
        current_date_str = datetime.now().strftime("%Y-%m-%d")
        # 构建文件名
        filename = f"gen/{current_date_str}.json"
        with open(filename, 'w') as f:
            json.dump(data, f, indent=4)
        if config["print_log"]:
            logging.info(f"JSON数据已成功写入 {filename}")


if __name__ == "__main__":
    # 配置日志记录
    logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
    config = load_config()
    lottery_data = fetch_lottery_data(config["source_url"])
    format_data = format_lottery_data(lottery_data)
    write_json_file(format_data)
    send_lottery_data(format_data)
