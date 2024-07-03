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

namespace WindowsFormsApp2
{
    

    public partial class Form1 : Form
    {
        private SerialPort serialPort = new SerialPort();
        public Form1()
        {
            InitializeComponent();
        }
        // 시리얼 데이터 수신 이벤트 처리 함수
        private void SerialPort_DataReceived(Object sender, SerialDataReceivedEventArgs e)
        {
            String recvData = this.serialPort.ReadExisting();
            Console.WriteLine(recvData);

            this.Invoke(
                (MethodInvoker)delegate { this.textBox1.AppendText(recvData + "/r/n"); }    
            );
            

            Thread.Sleep(1000);
        }
        private void button1_MouseClick(object sender, MouseEventArgs e)
        {
            Console.WriteLine("연결 Btn Clicked..");
            String port_number = null;

            try
            {
                if(this.comboBox1.SelectedIndex > -1)
                {
                    port_number = this.comboBox1.Items[this.comboBox1.SelectedIndex].ToString();
                    Console.WriteLine("Selected Port : " + port_number);

                    // SERIAL PORT 연결
                    this.serialPort.PortName = port_number;
                    this.serialPort.BaudRate = 9600;
                    this.serialPort.DataBits = 8;
                    this.serialPort.StopBits = System.IO.Ports.StopBits.One;
                    this.serialPort.Parity = System.IO.Ports.Parity.None;
                    this.serialPort.Open();
                    Console.WriteLine("CONNECTION SUCCESS..." + this.serialPort);
                }
            }
            catch (Exception ex)
            {
                Console.Write("CONNECTION ERROR : " + ex);
                
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
