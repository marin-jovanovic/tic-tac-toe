package com.tic_tac_toe.temp;

import java.util.concurrent.*;

public class TimerExample {

    static String payload() throws InterruptedException {
        Thread.sleep(2000); // Simulate some delay
        return "42";

    }

    public static void main(final String[] args) {
        final ExecutorService service = Executors.newSingleThreadExecutor();

        try {
            final Future<Object> f = service.submit(() -> {
                // Do you long running calculation here

                return payload();
            });

            System.out.println(f.get(1, TimeUnit.SECONDS));
        } catch (final TimeoutException e) {
            System.err.println("Calculation took to long");
        } catch (final Exception e) {
            throw new RuntimeException(e);
        } finally {
            service.shutdown();
        }
    }
}