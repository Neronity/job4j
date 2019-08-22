package parser;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Vacancy {
    private String name;
    private String desc;
    private String url;
    private Timestamp date;

    public Vacancy(String name, String desc, String url, Timestamp date) {
        this.name = name;
        this.desc = desc;
        this.url = url;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    public Timestamp getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacancy)) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(name, vacancy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
