import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AriaAlbum {

    private final List<String> listMusicTracks;
    private final String artistName;
    private final String titleAlbum;

    public AriaAlbum(String artistName, String titleAlbum, List<String> musicsTracks) {
        if (musicsTracks == null || musicsTracks.isEmpty()) {
            throw new IllegalArgumentException("Track list must not be null or empty.");
        }
        if (musicsTracks.size() > 16) {
            throw new IllegalArgumentException("Max tracks in form must be <= 16.");
        }
        this.artistName = artistName;
        this.titleAlbum = titleAlbum;
        this.listMusicTracks = List.copyOf(musicsTracks);
    }

    public String getArtistName() {
        return artistName;
    }

    public String getTitleAlbum() {
        return titleAlbum;
    }

    public List<String> getMusicsTracks() {
        return Collections.unmodifiableList(listMusicTracks);
    }
}
