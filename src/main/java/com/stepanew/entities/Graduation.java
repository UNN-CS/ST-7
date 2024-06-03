package com.stepanew.entities;

import java.util.Arrays;
import java.util.List;

/**
 * Класс, представляющий альбом "Graduation" исполнителя Kanye West.
 * Этот класс расширяет класс Album и предоставляет статическую информацию
 * о названии альбома, исполнителе и списке треков.
 */
public class Graduation extends Album {

    private final static String TITLE = "Graduation";

    private final static String ARTIST = "Kanye West";

    private final static List<String> TRACKS = Arrays.asList(
            "Good Morning", "Champion", "Stronger", "I Wonder", "Good Life (Ft. T-Pain)",
            "Can't Tell Me Nothing", "Barry Bonds (Ft. Lil Wayne)", "Drunk and Hot Girls",
            "Flashing Lights (Ft. Dwele)", "Everything I Am (Ft. DJ Premier)", "The Glory",
            "Homecoming (Ft. Chris Martin)", "Big Brother", "Good Night (Ft. Al Be Back & Yasiin Bey)",
            "Bittersweet Poetry (Ft. John Mayer)"
    );

    /**
     * Конструктор, создающий объект альбома "Graduation".
     * Вызывает конструктор родительского класса Album с параметрами TITLE, ARTIST и TRACKS.
     */
    public Graduation() {
        super(TITLE, ARTIST, TRACKS);
    }
}
