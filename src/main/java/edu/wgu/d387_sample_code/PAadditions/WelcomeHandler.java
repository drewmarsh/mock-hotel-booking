package edu.wgu.d387_sample_code.PAadditions;

import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WelcomeHandler {
    private static final ExecutorService messageExecutor = Executors.newFixedThreadPool(2);

    public static String[] handleMessages() {
        Properties properties = new Properties();
        String[] messages = new String[2];
        CountDownLatch latch = new CountDownLatch(2);

        messageExecutor.execute(() -> {
            try {
                InputStream stream = new ClassPathResource("translation_en_CA.properties").getInputStream();
                properties.load(stream);
                messages[0] = properties.getProperty("welcome");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        });

        messageExecutor.execute(() -> {
            try {
                InputStream stream = new ClassPathResource("translation_fr_CA.properties").getInputStream();
                properties.load(stream);
                messages[1] = properties.getProperty("welcome");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("fail");
            } finally {
                latch.countDown();
            }
        });

        try {
            latch.await(); // Wait for both tasks to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return messages;
    }
}
