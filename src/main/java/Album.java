import java.util.Arrays;
import java.util.List;

public record Album(String artist, String title, List<String> tracks) {
    static final Album albumGruppaKrovi = new Album(
            "Kino",
            "Gruppa krovi",
            Arrays.asList(
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
            )
    );
}

