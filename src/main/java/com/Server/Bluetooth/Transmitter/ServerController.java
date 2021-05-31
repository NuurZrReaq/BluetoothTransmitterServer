package com.Server.Bluetooth.Transmitter;

import javax.microedition.io.Connector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.microedition.io.StreamConnection;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

@RestController
public class ServerController {

    private final BluetoothService bluetoothService;
    private final String hc05Url = "btspp://0018E504B1FC:1;authenticate=false;encrypt=false;master=false";
    private StreamConnection streamConnection;
    private OutputStream os;
    private InputStream is;

    public ServerController(BluetoothService bluetoothService) {
        this.bluetoothService = bluetoothService;
    }

    @GetMapping("/start")
    public String openBluetoothConnection() {
        try {
            this.streamConnection = (StreamConnection) Connector.open(hc05Url);
            this.os = streamConnection.openOutputStream();
            this.is = streamConnection.openInputStream();
            return "Success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Fail";
    }

    @GetMapping("/send/{cmd}")
    public String sendBluetoothCommand(@PathVariable("cmd") int command) {
        try {
            byte data = Byte.parseByte(Integer.toString(command));
            os.write(data); // just send '1' to the device
            return "success " + data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Fail";
    }

    @GetMapping("/stop")
    public String closeBluetoothConnection() {
        try {
            os.close();
            is.close();
            streamConnection.close();
            return "Success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Fail";
    }

}
