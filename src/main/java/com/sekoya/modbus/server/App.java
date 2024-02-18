package com.sekoya.modbus.server;

import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;

import java.net.InetAddress;


public class App {

    static public void main(String[] args) {
        try {
            while (true) {
                TcpParameters tcpParameters = new TcpParameters();
                tcpParameters.setHost(InetAddress.getByName("127.0.0.1"));
                tcpParameters.setPort(50210);
                ModbusMaster m = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);


                int offset = 0;
                int quantity = 7;
                try {

                    if (!m.isConnected()) {
                        m.connect();
                    }

                    int[] registerValues = m.readHoldingRegisters(1, offset, quantity);

                    for (int value : registerValues) {
                        System.out.println("Address: " + offset++ + ", Value: " + value);
                    }

                } finally {
                    System.out.println("-----------------------------------------------");
                }

                Thread.sleep(1000);
            }


        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}