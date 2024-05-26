import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TheEminemShowAlbum implements Album {

    private final List<String> tracks = Arrays.asList(
            "Without Me", "Till I Collapse", "Superman", "Sing For The Moment",
            "Drips", "Soldier", "Hailie's Song", "Say What You Say", "Business",
            "White America"
    );

    @Override
    public String getArtist() {
        return "Eminem";
    }

    @Override
    public String getTitle() {
        return "The Eminem Show";
    }

    @Override
    public List<String> getTracks() {
        return Collections.unmodifiableList(tracks);
    }
}
