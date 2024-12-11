import requests
import json
import socket
import time
from cryptography.hazmat.primitives.asymmetric import padding
from cryptography.hazmat.primitives import hashes, serialization
from cryptography.hazmat.primitives.asymmetric import rsa
from fetch_lottery import fetch_lottery_data

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
    # 获取本机IP
    local_ip = socket.gethostbyname(socket.gethostname())
    # 获取当前时间戳
    timestamp = str(int(time.time()))

    # 加载公钥
    with open("public_key.pem", "rb") as key_file:
        public_key = serialization.load_pem_public_key(key_file.read())

    # 加密信息
    encrypted_info = encrypt_data(public_key, f"{local_ip},{timestamp}")

    # 准备请求数据
    data = {
        "lottery_data": lottery_data,
        "encrypted_info": encrypted_info.hex()  # 将加密数据转换为十六进制字符串
    }

    # 发送POST请求
    response = requests.post("http://127.0.0.1/api/lottery", json=data)
    print("Response status code:", response.status_code)
    print("Response content:", response.content)

if __name__ == "__main__":
    url = "https://kaijiang.500.com/"  # 确保定义了url变量
    lottery_data = fetch_lottery_data(url)
    send_lottery_data(lottery_data)