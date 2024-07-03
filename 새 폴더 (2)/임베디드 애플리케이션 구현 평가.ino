// LED 핀
const int ledPin = 10;

// 조도센서 핀
const int sunPin = A1;

// 온도 변수
float temp;

// 초음파 핀
const int trig_pin = 11;
const int echo_pin = 12;

// 초기 설정을 위한 setup() 함수
void setup() {
  // 시리얼 통신을 9600bps로 시작
  Serial.begin(9600);
  
  // LED 핀을 출력 모드로 설정
  pinMode(ledPin, OUTPUT);
  
  // 초음파 센서의 트리거 핀을 출력 모드로, 에코 핀을 입력 모드로 설정
  pinMode(trig_pin, OUTPUT);
  pinMode(echo_pin, INPUT);
} 

// 주기적으로 실행되는 메인 루프
void loop() {
  if (Serial.available()) {                   // 시리얼 포트로부터 데이터를 읽을 수 있는지 확인
    char inputVal = Serial.read();            // 시리얼 포트로부터 데이터를 읽음
    if (inputVal == '1') {                    // 읽은 데이터가 '1'인지 확인 (LED를 켜는 명령)
      digitalWrite(ledPin, HIGH);             // LED를 켬
      Serial.println("LED:ON");               // 시리얼 모니터에 LED가 켜졌음을 표시하는 메시지 출력
    } else if (inputVal == '0') {             // 읽은 데이터가 '0'인지 확인 (LED를 끄는 명령)
      digitalWrite(ledPin, LOW);              // LED를 끔   
      Serial.println("LED:OFF");              // 시리얼 모니터에 LED가 꺼졌음을 표시하는 메시지 출력
    }
  }

 // 조도 센서 읽기
  int sunValue = analogRead(A1);              // 조도 센서 값을 읽음
  Serial.print("SUN :");                      // 시리얼 모니터에 출력할 메시지 출력
  Serial.println(sunValue);                   // 조도 센서 값 출력
  if (sunValue < 400) {                       // 조도 센서 값이 400 미만인 경우 (어두움을 감지한 경우)
    digitalWrite(ledPin, HIGH);               // LED를 켬
    Serial.println("LED:ON");                 // 시리얼 모니터에 LED가 켜졌음을 표시하는 메시지 출력
  } else {                                    // 조도 센서 값이 400 이상인 경우 (밝음을 감지한 경우)
    digitalWrite(ledPin, LOW);                // LED를 끔
    Serial.println("LED:OFF");                // 시리얼 모니터에 LED가 꺼졌음을 표시하는 메시지 출력
  }

  // 온도 센서 읽기
  int val = analogRead(A0);                   // 온도 센서 값을 읽음
  temp = val * 0.48828125;                    // 온도 값을 섭씨로 변환
  Serial.print("TEMP:");                      // 시리얼 모니터에 출력할 메시지 출력
  Serial.println(temp);                       // 온도 값 출력

  // 초음파 센서 읽기
  digitalWrite(trig_pin, LOW);                // 초음파 송신 핀을 LOW로 설정해 초음파 OUT 신호 초기화
  delayMicroseconds(2);                       // 2 마이크로 초 동안 대기
  digitalWrite(trig_pin, HIGH);               // 초음파 송신 핀을 HIGH로 설정해 초음파 OUT 발사
  delayMicroseconds(10);                      // 10 마이크로 초 동안 대기
  digitalWrite(trig_pin, LOW);                // 초음파 송신 핀을 LOW로 설정해 초음파 OUT 신호 초기화

  long duration = pulseIn(echo_pin, HIGH);    //에코핀에서 초음파가 반사되어 돌아오는 시간 측정(HIGH값이 유지되는시간을통한 측정)
  long distance = (duration / 2) / 29.1;      //측정된 이동 시간을 거리로 반환(초당 초음파의 이동 거리 : 약 29.1cm)

  Serial.print("DIS:");                       // 시리얼 모니터에 출력할 메시지 출력
  Serial.println(distance);                   // 초음파 이동거리 출력
  if (distance < 5) {                         // 초음파 이동거리가 5보다 작은 경우 (장애물 감지)
    digitalWrite(ledPin, HIGH);               // LED를 켬
    Serial.println("LED:ON");                 // 시리얼 모니터에 LED가 켜졌음을 표시하는 메시지 출력
  }
  delay(500);                                 // 0.5초 대기
}