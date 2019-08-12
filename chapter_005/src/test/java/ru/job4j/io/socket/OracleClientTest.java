package ru.job4j.io.socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OracleClientTest {
    private Joiner j = Joiner.on(System.lineSeparator());

    @Test
    public void whenExitThenNothingPrinted() throws IOException {
        String[] result = testOracleClient("exit", "");
        assertThat(result[0], is(j.join("exit", "")));
        assertThat(result[1], is(""));
    }

    @Test
    public void whenMessageSentThenAnswerPrinted() throws IOException {
        String[] result = testOracleClient(j.join("test", "exit"), "1");
        assertThat(result[0], is(j.join("test", "exit", "")));
        assertThat(result[1], is(j.join("1", "")));
    }

    private String[] testOracleClient(String userInput, String output) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(userInput.getBytes())));
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(output.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();
        PrintStream pw = new PrintStream(consoleOut);

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        OracleClient client = new OracleClient(socket, reader, pw);
        client.start();
        return new String[] {out.toString(), consoleOut.toString()};
    }

}