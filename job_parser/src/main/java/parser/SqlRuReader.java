package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SqlRuReader {

    private int pageNum = 1;
    private Elements currentPage;
    private Iterator<Element> iter;

    private void readPage() throws IOException {
        Document doc = Jsoup.connect(String.format("https://www.sql.ru/forum/job-offers/%s", this.pageNum)).get();
        this.currentPage = doc.select(".forumTable tr .postslisttopic a:not(.pages):not(.newTopic)");
        this.iter = this.currentPage.iterator();
        this.pageNum++;
    }

    public Vacancy getNextVacancy() throws IOException {
        Vacancy result = null;
        if (this.currentPage == null || !this.iter.hasNext()) {
            readPage();
        }
        if (this.iter.hasNext()) {
            Element el = iter.next();
            String name = el.text();
            String url = el.absUrl("href");
            if (name.matches("\\b(?i)java\\b?(?!Script)(?-i).*")) {
                Element content = null;
                content = Jsoup.connect(url).get()
                        .select("#content-wrapper-forum .msgTable").get(0);
                result = new Vacancy(name,
                        content.select("td.msgBody").get(1).text(),
                        url,
                        this.parseDate(content.select("td.msgFooter").text().split("\\[")[0].trim()));
            } else {
                result = new Vacancy("", "", "empty", Timestamp.valueOf(LocalDateTime.now()));
            }
        }
        return result;
    }

    private String getNumericMonth(String ruMonth) {
        String result;
        switch (ruMonth) {
            case "янв":
                result = "01";
                break;
            case "фев":
                result = "02";
                break;
            case "мар":
                result = "03";
                break;
            case "апр":
                result = "04";
                break;
            case "май":
                result = "05";
                break;
            case "июн":
                result = "06";
                break;
            case "июл":
                result = "07";
                break;
            case "авг":
                result = "08";
                break;
            case "сен":
                result = "09";
                break;
            case "окт":
                result = "10";
                break;
            case "ноя":
                result = "11";
                break;
            case "дек":
                result = "12";
                break;
            default:
                result = "0";
        }
        return result;
    }

    private Timestamp parseDate(String date) {
        LocalDateTime ld;
        if (date.contains("вчера")) {
            String[] time = date.split(", ")[1].split(":");
            ld = LocalDateTime.of(LocalDate.now().minusDays(1L),
                    LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1])));
        } else {
            String[] sp = date.split(" ");
            if (sp[0].length() == 1) date = String.format("%s%s", 0, date);
            String month = sp[1];
            date = date.replaceAll(month, getNumericMonth(month));
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MM yy, HH:mm");
            ld = LocalDateTime.parse(date, f);
        }
        return Timestamp.valueOf(ld);
    }

}
