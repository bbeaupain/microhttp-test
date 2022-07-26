package org.example;

import org.microhttp.EventLoop;
import org.microhttp.Handler;
import org.microhttp.Header;
import org.microhttp.LogEntry;
import org.microhttp.Logger;
import org.microhttp.Options;
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
        Options options = new Options()
            .withHost("0.0.0.0")
            .withPort(8080)
            .withConcurrency(Runtime.getRuntime().availableProcessors());
        Logger logger = new NoOpLogger();
        EventLoop eventLoop = new EventLoop(options, handler);
        eventLoop.start();
        eventLoop.join();
    }

    static class NoOpLogger implements Logger {

        @Override
        public boolean enabled() {
            return false;
        }

        @Override
        public void log(LogEntry... logEntries) {

        }

        @Override
        public void log(Exception e, LogEntry... logEntries) {

        }
    }
}
