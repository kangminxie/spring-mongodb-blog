package com.kangmin.app.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TimerSchedulerTask {

    // private int count = 0;

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    private static final int TIME_REPORT_INIT_DELAY = 15 * 1000;  // 15 seconds
    private static final int TIME_REPORT_FREQUENCY = 60 * 1000;  // 60 seconds = 1 minute

//    @Scheduled(cron = "*/15 * * * * ?")
//    private void processSecondsTask() {
//        System.out.println(">>> This is scheduler task running  " + (count++));
//    }

    @Scheduled(initialDelay = TIME_REPORT_INIT_DELAY, fixedRate = TIME_REPORT_FREQUENCY)
    public void reportCurrentTimePerFifteenSeconds() {
        System.out.println(">>> 现在时间：" + TIME_FORMAT.format(new Date()));
    }
}
