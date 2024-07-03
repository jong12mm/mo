using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO.Ports;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Threading;


namespace WindowsFormsApp1
{
    public partial class Form1 : Form                       // Form1 클래스 정의 : 폼 디자인 및 로직을 담당하는 클래스
    {
        private SerialPort serialPort = new SerialPort();   // 시리얼 통신을 위한 SerialPort 객체 생성 및 초기화

        public Form1()                                      // Form1 클래스의 생성자 메서드
        {
            InitializeComponent();                          // 컴포넌트 초기화 : 윈도우 폼의 컴포넌트 초기화를 위해 자동으로 생성된 메서드 호출
        }
        //-----------------------------------
        // 시리얼 데이터 수신 이벤트 처리 함수
        //-----------------------------------

        // 시리얼 데이터 수신 이벤트 핸들러
        private void SerialPort_DataReceived(Object sender, SerialDataReceivedEventArgs e)
        {
            // 시리얼 포트로부터 한 줄의 데이터를 읽어옴
            String recvData = this.serialPort.ReadLine();
            // 읽어온 데이터를 콘솔에 출력
            Console.WriteLine(recvData);

            //-----------------------------
            // LED 신호 전달 문자열 처리
            //-----------------------------

            // 받은 데이터가 LED 신호를 나타내는지 확인
            if (recvData.StartsWith("LED:")) 
            {
                // 스레드 생성 및 실행 (UI 스레드에서 컨트롤을 조작하기 위해 Invoke 사용)
                Invoke(new Action(() =>
                {
                    // 텍스트 박스에 데이터 추가
                    this.textBox1.AppendText(recvData + "\r\n");
                }));
                // 10 밀리초 동안 스레드 일시 정지
                Thread.Sleep(10);
            }
            //-----------------------------
            // 조도센서 전달 문자열 처리
            //-----------------------------

            // 받은 데이터가 조도센서 값을 나타내는지 확인
            if (recvData.StartsWith("SUN:"))
            {
                // 스레드 생성 및 실행 (UI 스레드에서 컨트롤을 조작하기 위해 Invoke 사용)
                Invoke(new Action(() =>
                {
                    // 텍스트 박스에 데이터 설정
                    this.textBox2.Text = recvData.Replace("SUN:", "");
                }));
                // 10 밀리초 동안 스레드 일시 정지
                Thread.Sleep(10);
            }
            //-----------------------------
            // 온도센서 전달 문자열 처리
            //-----------------------------

            // 받은 데이터가 온도센서 값을 나타내는지 확인
            if (recvData.StartsWith("TEMP:")) 
            {
                // 스레드 생성 및 실행 (UI 스레드에서 컨트롤을 조작하기 위해 Invoke 사용)
                Invoke(new Action(() =>
                {
                    // 텍스트 박스에 데이터 설정
                    this.textBox3.Text = recvData.Replace("TEMP:", "");
                }));
                // 10 밀리초 동안 스레드 일시 정지
                Thread.Sleep(10);
            }
            //-----------------------------
            // 초음파센서 전달 문자열 처리
            //-----------------------------

            // 받은 데이터가 초음파센서 값을 나타내는지 확인
            if (recvData.StartsWith("DIS:"))
            {
                // 스레드 생성 및 실행 (UI 스레드에서 컨트롤을 조작하기 위해 Invoke 사용)
                Invoke(new Action(() =>
                {
                    // 텍스트 박스에 데이터 설정
                    this.textBox4.Text = recvData.Replace("DIS:", "");
                }));
                // 10 밀리초 동안 스레드 일시 정지
                Thread.Sleep(10);
            }
        }
        //-----------------------------
        // 연결 버튼 클릭 이벤트 핸들러
        //-----------------------------

        // 연결 버튼 클릭 시 이벤트 핸들러
        private void button1_MouseClick(object sender, MouseEventArgs e)
        {
            // 연결 버튼 클릭 시 콘솔에 메시지 출력
            Console.WriteLine("연결 Btn Clicked..");
            // 포트 번호 변수 초기화
            String port_number = null; 

            try // 예외 처리 구문 시작
            {
                // 콤보 박스에서 포트가 선택되었는지 확인
                if (this.comboBox1.SelectedIndex > -1) 
                {
                    // 선택된 포트 번호 가져오기
                    port_number = this.comboBox1.Items[this.comboBox1.SelectedIndex].ToString();
                    // 선택된 포트 번호 콘솔에 출력
                    Console.WriteLine("Selected Port : " + port_number); 

                    // SERIAL PORT 연결 설정
                    this.serialPort.PortName = port_number;                         // 포트 이름 설정
                    this.serialPort.BaudRate = 9600;                                // 통신 속도 설정
                    this.serialPort.DataBits = 8;                                   // 데이터 비트 설정
                    this.serialPort.StopBits = System.IO.Ports.StopBits.One;        // 정지 비트 설정
                    this.serialPort.Parity = System.IO.Ports.Parity.None;           // 패리티 설정
                    this.serialPort.Open();                                         // 시리얼 포트 열기
                    Console.WriteLine("CONNECTION SUCCESS... " + this.serialPort);  // 연결 성공 메시지 출력
                    this.serialPort.DataReceived += new SerialDataReceivedEventHandler(SerialPort_DataReceived); // 데이터 수신 이벤트 핸들러 등록
                }
            }
            // 예외 발생 시 처리
            catch (Exception ex) 
            {
                // 연결 에러 메시지 출력
                Console.Write("CONNECTION ERROR : " + ex);
                // 시리얼 포트 닫기
                this.serialPort.Close(); 
            }
        }
        //-----------------------------
        // LED ON 버튼 클릭 이벤트 핸들러
        //-----------------------------

        // LED ON 버튼 클릭 시 이벤트 핸들러
        private void btn_on_Click(object sender, EventArgs e)
        {
            // LED ON 버튼 클릭 시 콘솔에 메시지 출력
            Console.WriteLine("LED ON BTN CLICKED..."); 
            // 시리얼 포트를 통해 데이터 전송 (LED ON 신호)
            serialPort.Write("1"); 
        }
        //-------------------------------
        // LED OFF 버튼 클릭 이벤트 핸들러
        //-------------------------------

        // LED OFF 버튼 클릭 시 이벤트 핸들러
        private void btn_off_Click(object sender, EventArgs e) 
        {
            // LED OFF 버튼 클릭 시 콘솔에 메시지 출력
            Console.WriteLine("LED OFF BTN CLICKED..."); 
            // 시리얼 포트를 통해 데이터 전송 (LED OFF 신호)
            serialPort.Write("0"); 
        }

        private void groupBox3_Enter(object sender, EventArgs e)
        {
            // 그룹 박스가 클릭되었을 때의 이벤트 핸들러 (현재는 비어 있음)
        }
    }
}
