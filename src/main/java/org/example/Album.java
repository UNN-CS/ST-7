package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class Album {
    private final ArrayList<String> tracks = new ArrayList<>();
    public Album() {
        tracks.addAll(Arrays.asList("Night Witches", "No Bullets Fly",
                "Smoking Snakes", "Inmate 4859",
                "To Hell And Back", "The Ballad Of Bull",
                "Resit And Bite", "Soldier Of 3 Armies",
                "Far From The Fame", "Hearts Of Iron",
                "7734", "Man OF War"));
    }

    public String getArtist() {
        return "Sabaton";
    }

    public String getAlbumTitle() {
        return "Heroes";
    }

    public List<String> getMusicTracks() {
        return Collections.unmodifiableList(tracks);
    }
}
