package edu.wgu.d387_sample_code.PAadditions;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class TimeZoneHandler {
    public static String handleTimeZones() {
        StringBuilder timeZones = new StringBuilder();

        ZoneId zEastern = ZoneId.of("America/New_York");
        ZoneId zMountain = ZoneId.of("America/Denver"); // Mountain Time
        ZoneId zUTC = ZoneId.of("UTC"); // Coordinated Universal Time

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

        System.out.println("Local time: " + localDateTime.format(formatter));

        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        ZonedDateTime zonedDateTimeEastern = zonedDateTime.withZoneSameInstant(zEastern);
        LocalDateTime localDateTimeEastern = zonedDateTimeEastern.toLocalDateTime();

        System.out.println("ET: " + localDateTimeEastern.format(formatter));
        timeZones.append("\nET: ").append(localDateTimeEastern.format(formatter));
        timeZones.append(", ");

        ZonedDateTime zonedDateTimeMountain = zonedDateTime.withZoneSameInstant(zMountain);
        LocalDateTime localDateTimeMountain = zonedDateTimeMountain.toLocalDateTime();

        System.out.println("MT: " + localDateTimeMountain.format(formatter));
        timeZones.append("\nMT: ").append(localDateTimeMountain.format(formatter));
        timeZones.append(", and ");

        ZonedDateTime zonedDateTimeUTC = zonedDateTime.withZoneSameInstant(zUTC);
        LocalDateTime localDateTimeUTC = zonedDateTimeUTC.toLocalDateTime();

        System.out.println("UTC: " + localDateTimeUTC.format(formatter));
        timeZones.append("\nUTC: ").append(localDateTimeUTC.format(formatter));

        return timeZones.toString();
    }
}
