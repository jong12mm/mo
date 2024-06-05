using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO.Ports;
using System.Threading;

//추가
using System.Net.Http;
using System.Net;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {

        private SerialPort serialPort = new SerialPort();

        public Form1()
        {
            InitializeComponent();
        }

        //시리얼 데이터 수신 이벤트 처리 함수
        private void SerialPort_DataReceived(Object sender, SerialDataReceivedEventArgs e)
        {
            String recvData = this.serialPort.ReadLine();
            Console.WriteLine(recvData);

           
            //조도센서 전달 문자열
            if (recvData.StartsWith("SUN:"))
            {
                //스레드 생성 실행
                Invoke(new Action(() =>
                {
                    this.textBox2.Text = recvData.Replace("SUN:", "");//조도센서(텍스트박스)에 조도센서 정보를 표시 
                }));
                Thread.Sleep(10);
            }
            //온도센서 전달 문자열
            if (recvData.StartsWith("TEMP:"))
            {
                //스레드 생성 실행
                Invoke(new Action(() =>
                {
                    this.textBox3.Text = recvData.Replace("TEMP:", "");//온도센서(텍스트박스)에 온도센서 정보를 표시 
                }));
                Thread.Sleep(10);
            }
            //초음파센서 전달 문자열
            if (recvData.StartsWith("DIS:"))
            {
                //스레드 생성 실행
                Invoke(new Action(() =>
                {
                    this.textBox4.Text = recvData.Replace("DIS:", "");// 초음파센서(텍스트박스)에 초음파 센서 정보를 표시 
                }));
                Thread.Sleep(10);
            }


        }
        private void conn_btn_Click(object sender, EventArgs e)
        {
            
            
            String port =  this.comboBox1.Items[  this.comboBox1.SelectedIndex  ].ToString();
            Console.WriteLine("PORT : " + port);
            HttpWebRequest request=null;
            HttpWebResponse response = null;
            try
            {   
                request =  (HttpWebRequest)HttpWebRequest.Create("http://localhost:8080/arduino/connection/" + port);
                request.Method = "GET";
                request.ContentType = "application/json";
                //request.Timeout = 30 * 1000;

                response = (HttpWebResponse)request.GetResponse();
           
                if(response.StatusCode == HttpStatusCode.OK)
                {
                    Console.WriteLine("RESPONSE CODE : " + response.StatusCode);

                }
               
            }catch(Exception ex)
            {
                Console.WriteLine("Ex : " + ex);
            }


        }

        private void led_on_btn_Click(object sender, EventArgs e)
        {
            HttpWebRequest request = (HttpWebRequest)HttpWebRequest.Create("http://localhost:8080/arduino/led/1");
            request.Method = "GET";
            request.ContentType = "application/json";
            //request.Timeout = 30 * 1000;
            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
        }

        private void led_off_btn_Click(object sender, EventArgs e)
        {
            HttpWebRequest request = (HttpWebRequest)HttpWebRequest.Create("http://localhost:8080/arduino/led/0");
            request.Method = "GET";
            request.ContentType = "application/json";
            //request.Timeout = 30 * 1000;
            HttpWebResponse response = (HttpWebResponse)request.GetResponse();

        }
    }
}
