package ru.job4j.io.socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.StringJoiner;

public class OracleServerTest {
    private Joiner j = Joiner.on(System.lineSeparator());

    @Test
    public void whenExitThenStopWithNoAnswer() throws IOException {
        assertThat(testOracleServer("exit"),
                is(""));
    }

    @Test
    public void whenHelloThenHelloRespond() throws IOException {
        assertThat(testOracleServer(j.join("hello", "exit")),
                is(j.join("Hello, dear friend, I'm an oracle.", "", "")));
    }

    @Test
    public void whenRequestThenWisePhrase() throws IOException {
        assertThat(testOracleServer(j.join("test", "exit")), is(j.join("Some wisely put phrase.", "", "")));
    }

    private String testOracleServer(String input) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        
        OracleServer server = new OracleServer(socket);
        server.start();
        return out.toString();
    }
}