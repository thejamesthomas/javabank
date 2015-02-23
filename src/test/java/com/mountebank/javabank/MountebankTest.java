package com.mountebank.javabank;

import com.mountebank.javabank.Imposter;
import com.mountebank.javabank.Mountebank;
import org.apache.commons.exec.CommandLine;
import org.junit.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MountebankTest {
    @Test
    public void shouldCreateAnImposter() {
        Mountebank mountebank = new Mountebank();

        Imposter imposter = Imposter.anHttpImposter()
                .withPort(4545);
        mountebank.createImposter(imposter);
    }

    @Test
    public void shouldStartMountebank() throws IOException {
        Mountebank.start();

        CommandLine cmdLine = CommandLine.parse("ps aux");
//        DefaultExecutor executor = new DefaultExecutor();
//        int exitValue = executor.execute(cmdLine);
//        executor.setStreamHandler(new PumpStreamHandler());
//        executor.setStreamHandler(new ExecuteStreamHandler() {
//        });

//        DefaultExecutor executor = new DefaultExecutor();
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
//        executor.setStreamHandler(streamHandler);
//        executor.execute(cmdLine);

//        BufferedReader br = new BufferedReader(new StringReader(outputStream.toString()));
//        String line;
//        while (( line = br.readLine()) != null) System.out.println(line);

        Process p = Runtime.getRuntime().exec(new String[] { "bash", "-c", "ps aux | grep mb" });
        BufferedReader reader = new BufferedReader (new InputStreamReader(p.getInputStream()));

        String line;
        while ((line = reader.readLine ()) != null) {
            System.out.println ("Stdout: " + line);
        }

        assertThat(true).isTrue();
    }

    @Test
    public void shouldDetermineIfMountebankIsRunning() {
//        com.mountebank.javabank.Mountebank.start()
    }
}