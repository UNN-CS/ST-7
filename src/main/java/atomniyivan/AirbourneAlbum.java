package atomniyivan;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AirbourneAlbum implements Album {
    private final List<String> musicTracks = Arrays.asList(
            "Breakin' Outta Hell",
            "Rivalry", "Get Back Up",
            "It's Never Too Loud For Me", "Thin The Blood",
            "I'm Going To Hell For This", "Down On You",
            "Never Been Rocked Like This", "When I Drink I Go Crazy",
            "Do Me Like You Do Yourself", "It's All For Rock N' Roll");

    @Override
    public String getArtist() {
        return "Airbourne";
    }

    @Override
    public String getAlbumTitle() {
        return "Breakin' Outta Hell";
    }

    @Override
    public List<String> getMusicTracks() {
        return Collections.unmodifiableList(musicTracks);
    }
}
