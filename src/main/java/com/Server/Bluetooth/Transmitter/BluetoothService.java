package com.Server.Bluetooth.Transmitter;

import org.springframework.stereotype.Service;

import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class BluetoothService {


    public String processCommand(int command, StreamConnection streamConnection, OutputStream os, InputStream is) throws IOException {
        
    }
}
