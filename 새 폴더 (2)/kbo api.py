import requests
from bs4 import BeautifulSoup
import csv

# 웹 페이지에서 데이터를 가져오는 함수
def get_data(url):
    response = requests.get(url)
    if response.status_code == 200:
        return response.content
    else:
        return None

# 데이터 파싱 및 CSV 파일 작성 함수
def parse_and_save(data, output_file):
    soup = BeautifulSoup(data, 'html.parser')
    table = soup.find('기본')  # 원하는 데이터가 포함된 테이블을 찾습니다.

    with open(output_file, 'w', newline='', encoding='utf-8') as csvfile:
        writer = csv.writer(csvfile)
        for row in table.find_all('tr'):  # 모든 행을 반복합니다.
            row_data = []
            for cell in row.find_all(['th', 'td']):  # 각 행의 셀을 반복합니다.
                row_data.append(cell.get_text(strip=True))
            writer.writerow(row_data)

# 메인 함수
def main():
    url = 'https://statiz.sporki.com/stats/'
    output_file = 'statiz_data.csv'
    data = get_data(url)
    if data:
        parse_and_save(data, output_file)
        print(f"CSV 파일 '{output_file}'이(가) 생성되었습니다.")
    else:
        print("데이터를 가져올 수 없습니다.")

if __name__ == "__main__":
    main()
