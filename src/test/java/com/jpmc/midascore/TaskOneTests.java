package com.jpmc.midascore;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class TaskOneTests {
    static final Logger logger = LoggerFactory.getLogger(TaskOneTests.class);

    /*
    Initially, spring tred to start the application context, however it failed, because it was trying to instantiate KafkaProducer
    To rectify it, we initially tried to mock KafkaProducer by doing
        @Mock
        private KafkaProducer kafkaProducer;

       however this failed, because KafkaProducer contains final feild, and no-args constructor, leading to MockitoException

       Thus, instead of mocking we created a test configuration bean that Spring can inject. This beans will do nothing,
       it is created just for the purpose of testing.
     */
    @TestConfiguration
    static class KafkaProducerTestConfig {
        @Bean
        public KafkaProducer kafkaProducer() {
            // return a dummy implementation that does nothing
            return new KafkaProducer("test-topic", null) {
                @Override
                public void send(String transactionLine) {
                    // do nothing
                }
            };
        }
    }

    @Test
    void task_one_verifier() throws InterruptedException {
        Thread.sleep(2000);
        logger.info("----------------------------------------------------------");
        logger.info("----------------------------------------------------------");
        logger.info("----------------------------------------------------------");
        logger.info("Congrats! It looks like your application booted without issue");
        logger.info("submit the following output to complete the task (include begin and end output denotations)");
        StringBuilder output = new StringBuilder("\n").append("---begin output ---").append("\n");
        for (int i = 0; i < 10; i++) {
            output.append(String.valueOf((int) Math.floor(Math.pow(i, i))));
        }
        output.append("\n").append("---end output ---");
        logger.info(output.toString());

    }

}
