package com.niuzj.springbootwebsocket.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import java.io.*;
import java.util.Arrays;
import java.util.UUID;

@ServerEndpoint("/fileUpload")
@Component
public class FileUpload {

    @OnMessage
    public void fileUpload(byte[] data){
        System.out.println(Arrays.toString(data));
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("e:\\" + UUID.randomUUID().toString() + ".txt");
            fileOutputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnMessage
    public void text(String message){
        System.out.println(message);
    }

}
