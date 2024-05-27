package com.stepanew.entities;

import java.util.Collections;
import java.util.List;

public abstract class Album {

    private final String title;

    private final String artist;

    private final List<String> tracks;

    public Album(String title, String artist, List<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = Collections.unmodifiableList(tracks);
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public List<String> getTracks() {
        return tracks;
    }

}
