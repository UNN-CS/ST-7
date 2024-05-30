import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AlbumGruppaKrovi {
    private final List<String> tracks = Arrays.asList(
            "Blood Type",
            "Zakroy za mnoy dver', ya ukhozhu",
            "Voyna",
            "Spokoynaya noch",
            "Mama, my vse soshli s uma",
            "Boshetunmai",
            "V nashikh glazakh",
            "Poprobuy spet' vmeste so mnoy",
            "Dal'she deystvovat' budem my",
            "Legenda"
    );

    private final String artist = "Kino";
    private final String title =  "Gruppa krovi";

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
