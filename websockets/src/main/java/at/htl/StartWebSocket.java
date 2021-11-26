package ilove.quark.us;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

@ServerEndpoint("/start-websocket/{name}")
@ApplicationScoped
public class StartWebSocket {

    Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("name") String name) {
        System.out.println("onOpen> " + name);
        sessions.put(name, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("name") String name) {
        System.out.println("onClose> " + name);
        sessions.remove(name);

    }

    @OnError
    public void onError(Session session, @PathParam("name") String name, Throwable throwable) {
        System.out.println("onError> " + name + ": " + throwable);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("name") String name) {
        System.out.println("onMessage> " + name + ": " + message);
    }
}
