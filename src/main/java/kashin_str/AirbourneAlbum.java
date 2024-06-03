package kashin_str;

import java.util.*;

public class SSONG implements SONG {
    private final List<String> musicTracks = Arrays.asList(
            "Lore123m' Lorem LoremLoremLoremLoremLorem",
            "Lorem", "Lorem BaLoremLoremck Up",
            "LoremLorem3LoremLoremLorem", "LoremLoremLoremLoremLoremLorem",
            "LoremLoremLoremLoremLoremLorem", "Lorem",
            "Lorem", "Lorem",
            "Lorem", "Lorem");
    @Override
    public List<String> getMusicTracks() {
        return Collections.unmodifiableList(musicTracks);
    }
     @Override
    public String getArtist() {
        return "Airbourne";
    }

    @Override
    public String getAlbumTitle() {
        return "Breakin' Outta Hell";
    }

}
