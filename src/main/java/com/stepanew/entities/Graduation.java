package com.stepanew.entities;

import java.util.Arrays;
import java.util.List;

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

    public Graduation() {
        super(TITLE, ARTIST, TRACKS);
    }

}
