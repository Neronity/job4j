package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.core.QuartzScheduler;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class JobParser {
    private static Properties props = new Properties();
    private static final Logger LOG = LogManager.getLogger();

    public static void initProps() {
        try (InputStream in = JobParser.class.getClassLoader().getResourceAsStream("app.properties")) {
            props.load(in);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) throws IOException {
        initProps();
        try {
            Scheduler s = StdSchedulerFactory.getDefaultScheduler();
            JobDataMap map = new JobDataMap();
            map.putIfAbsent("props", props);
            JobDetail job = JobBuilder
                    .newJob(ScheduledParser.class)
                    .usingJobData(map)
                    .withIdentity("job1")
                    .build();
            CronTrigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("trigger")
                    //.withSchedule(SimpleScheduleBuilder.repeatMinutelyForever())
                    .withSchedule(CronScheduleBuilder
                            .cronSchedule(props.getProperty("cron.time"))).forJob("job1")
                    .build();
            s.scheduleJob(job, trigger);
            s.start();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }



}
