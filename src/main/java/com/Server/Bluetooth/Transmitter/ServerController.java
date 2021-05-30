package com.Server.Bluetooth.Transmitter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ServerController {

    private final BluetoothService bluetoothService;

    public ServerController(BluetoothService bluetoothService) {
        this.bluetoothService = bluetoothService;
    }

    @GetMapping("/send/{cmd}")
    public String sendBluetoothCommand (@PathVariable("cmd") int command)  {
        try {
            return bluetoothService.processCommand(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Fail";
    }

}
