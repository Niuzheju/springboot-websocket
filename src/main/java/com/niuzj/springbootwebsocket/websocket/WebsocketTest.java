package com.niuzj.springbootwebsocket.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket")
@Component
public class WebsocketTest {

    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<WebsocketTest> websocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        websocketSet.add(this);
        addOnlineCount();
        System.out.println("有新连接加入, 当前在线人数为" + getOnlineCount());
    }

    @OnClose
    public void onClose() {
        websocketSet.remove(this);
        delOnlineCount();
        System.out.println("有连接关闭, 当前现在人数为" + getOnlineCount());
    }

    @OnMessage
    public void onText(String text) {
        System.out.println(text);
        sendText(text);
    }

    @OnMessage
    public void onFile(byte[] file) {
        System.out.println(Arrays.toString(file));
        sendFile(file);
    }

    @OnError
    public void onError(Throwable throwable) {
        System.out.println("发生错误");
        throwable.printStackTrace();
    }

    private void sendText(String message) {
        for (WebsocketTest websocketTest : websocketSet) {
            try {
                websocketTest.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void sendFile(byte[] file) {
        for (WebsocketTest websocketTest : websocketSet) {
            try {
                websocketTest.session.getBasicRemote().sendObject(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void addOnlineCount() {
        onlineCount++;
    }

    private synchronized void delOnlineCount() {
        onlineCount--;
    }

    private synchronized int getOnlineCount() {
        return onlineCount;
    }
}
