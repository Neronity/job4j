package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ScheduledParser implements Job {

    private final static Logger LOG = LogManager.getLogger();
    private static int jobCount;
    private Timestamp lastTime;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Properties props = (Properties) jobExecutionContext.getJobDetail().getJobDataMap().get("props");
        this.lastTime = jobCount == 0 ?
                Timestamp.valueOf(LocalDateTime.now().minusYears(1L)) :
                new Timestamp(jobExecutionContext.getPreviousFireTime().getTime());
        List<Vacancy> vacancies = new ArrayList<>();
        SqlRuReader vacancyReader = new SqlRuReader();

        try (Connection c = DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("username"),
                props.getProperty("password")
        )) {
            Vacancy v = vacancyReader.getNextVacancy();
            while (v != null && this.lastTime.compareTo(v.getDate()) < 0) {
                if (!"empty".equals(v.getUrl())) {
                    vacancies.add(v);
                }
                v = vacancyReader.getNextVacancy();
            }
            if (!vacancies.isEmpty()) {
                try (SqlWriter w = new SqlWriter(vacancies, c)) {
                    w.writeData();
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        } catch (IOException | SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        jobCount++;
        System.out.println(jobCount);
    }
}
