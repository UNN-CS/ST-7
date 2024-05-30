import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AlbumWhyBaby {
    private final List<String> tracks = Arrays.asList(
            "DARK SIDE",
            "CASH",
            "LIDOCAIN",
            "ALL INCLUSIVE",
            "BAD BOY",
            "GREEN PAPER",
            "MAMI",
            "HOROSCOPE"
    );

    private final String artist = "WhyBaby?";
    private final String title =  "DARK SIDE";

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTracks() {
        return Collections.unmodifiableList(tracks);
    }
}
