package org.example;

import org.microhttp.EventLoop;
import org.microhttp.Handler;
import org.microhttp.Header;
import org.microhttp.Response;

import java.util.List;

public class MicrohttpTest {
    public static void main(String[] args) throws Exception {
        Response response = new Response(
            200,
            "OK",
            List.of(new Header("Content-Type", "text/plain")),
            "hello world\n".getBytes());
        Handler handler = (req, callback) -> callback.accept(response);
        EventLoop eventLoop = new EventLoop(handler);
        eventLoop.start();
        eventLoop.join();
    }
}
