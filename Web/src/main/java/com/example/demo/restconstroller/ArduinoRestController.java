package com.example.demo.restconstroller;


import com.fazecast.jSerialComm.SerialPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@Slf4j
@RequestMapping("/arduino")
public class ArduinoRestController {

    private SerialPort serialPort;
    private OutputStream outputStream;
    private InputStream inputStream;

    private String LedLog;
    private String TmpLog;
    private String LightLog;
    private String DistanceLog;

    @GetMapping("/connection/{COM}")
    public ResponseEntity<String> setConnection(@PathVariable("COM") String COM, HttpServletRequest request) {
        log.info("GET /arduino/connection " + COM + " IP: " + request.getRemoteAddr());

        if (serialPort != null) { // 포트가 열려있다면
            serialPort.closePort(); // 포트를 닫음
            serialPort = null;
        }

        //Port Setting
        serialPort = SerialPort.getCommPort(COM);

        //Connection Setting
        serialPort.setBaudRate(9600); //시리얼통신속도 9600 설정
        serialPort.setNumDataBits(8); //시리얼통신 데이터 비트 8비트로 설정
        serialPort.setNumStopBits(0); // 시리얼통신 멈춤
        serialPort.setParity(0); // 시리얼통신 패리티 사용하지않음
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING,2000,0); // 읽기작업이 데이터를 대기하는 동안 2초동안 멈추고 타임아웃이 발생하지않게 설정

        boolean isOpen = serialPort.openPort();
        log.info("isOpen ? " + isOpen);


        if (isOpen) { //시리얼포트가 연동되엇다면
            this.outputStream = serialPort.getOutputStream();
            this.inputStream = serialPort.getInputStream();

            //----------------------------------------------------------------
            //수신 스레드 동작
            //----------------------------------------------------------------
            Worker worker = new Worker();
            Thread th = new Thread(worker);
            th.start();


            return new ResponseEntity("Connection Success!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Connection Fail...!", HttpStatus.BAD_GATEWAY);
        }


    }

    @GetMapping("/led/{value}")
    public void led_Control(@PathVariable String value, HttpServletRequest request) throws IOException {
        log.info("GET /arduino/led/value : " + value + " IP : " + request.getRemoteAddr());
        if (serialPort.isOpen()) { //시리얼 포트가 열려있다면 .
            outputStream.write(value.getBytes()); // LED 제어명령 전송
            outputStream.flush();
        }
    }

    @GetMapping("/message/led")
    public String led_Message() throws InterruptedException {
        return LedLog;
    }

    @GetMapping("/message/tmp")
    public String tmp_Message() {
        return TmpLog;
    }

    @GetMapping("/message/light")
    public String light_Message() {
        return LightLog;
    }

    @GetMapping("/message/distance")
    public String distance_Message() {
        return DistanceLog;
    }




    //----------------------------------------------------------------
    // 수신 스레드 클래스
    //----------------------------------------------------------------
    class Worker implements Runnable {
        DataInputStream din;
        @Override
        public void run() {
            din = new DataInputStream(inputStream);
            try{
                while(!Thread.interrupted()) {
                    if (din != null) {
                        String data = din.readLine();
                        System.out.println("[DATA] : " + data);
                        String[] arr = data.split("_"); //LED ,TMP , LIGHT , DIS // 3,4
                        try {
                            if (arr.length > 3) {  
                                LedLog = arr[0]; //
                                TmpLog = arr[1];
                                LightLog = arr[2];
                                DistanceLog = arr[3];
                            } else {
                                TmpLog = arr[0];
                                LightLog = arr[1];
                                DistanceLog = arr[2];
                            }
                        }catch(ArrayIndexOutOfBoundsException e1){e1.printStackTrace();}
//                        if(data.startsWith("LED:")){
//                            LedLog = data;
//                        }else if(data.startsWith("TMP:")) {
//                            TmpLog = data;
//                        }else if(data.startsWith("LIGHT:")){
//                            LightLog = data;
//                        }else if(data.startsWith("DIS:")){
//                            DistanceLog = data;
//                        }




                    }
                    Thread.sleep(200);
                }
            }catch(Exception e){
                e.printStackTrace();
            }


        }
    }
    //----------------------------------------------------------------

}











