package com.example.bookstoreapp.test;

import java.time.*;

public class TimezoneDemo {
    public static void main(String[] args) {
        Instant utcInstant = Instant.parse("2025-11-10T13:00:00Z");

        ZonedDateTime london = utcInstant.atZone(ZoneId.of("Europe/London"));
        ZonedDateTime newYork = utcInstant.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime tokyo  = utcInstant.atZone(ZoneId.of("Asia/Tokyo"));
        ZonedDateTime india = utcInstant.atZone(ZoneId.of("Asia/Mumbai"));

        System.out.println("UTC     : " + utcInstant);
        System.out.println("London  : " + london);
        System.out.println("New York: " + newYork);
        System.out.println("Tokyo   : " + tokyo);
        System.out.println("India   : " + india);
    }
}
