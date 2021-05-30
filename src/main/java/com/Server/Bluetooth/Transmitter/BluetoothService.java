package com.Server.Bluetooth.Transmitter;

import org.springframework.stereotype.Service;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class BluetoothService {
    private final String hc05Url = "btspp://0018E504B1FC:1;authenticate=false;encrypt=false;master=false";


    public String processCommand(int command) throws IOException {
        StreamConnection streamConnection = (StreamConnection) Connector.open(hc05Url);
        OutputStream os = streamConnection.openOutputStream();
        InputStream is = streamConnection.openInputStream();
        byte data = Byte.parseByte(Integer.toString(command));
        os.write(data); //just send '1' to the device
        os.close();
        is.close();
        streamConnection.close();
        return "success " + data;
    }
}
