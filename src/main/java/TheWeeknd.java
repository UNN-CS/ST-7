import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TheWeeknd {
    private final List<String> tracks = Arrays.asList(
            "Save Your Tears",
            "Blinding Lights",
            "In Your Eyes",
            "Can't Feel My Face",
            "Heartless",
            "Often",
            "The Hills",
            "Call Out My Name",
            "Die For You",
            "Acquainted",
            "Wicked Games",
            "The Morning",
            "After Hours"
    );

    private final String artist = "The Weeknd";
    private final String title =  "The Highlights";

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
