package pwr.project.ziwg.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Emotions {

    // BAD
    TIRED("Zmęczenie", EmotionsCategories.BAD),
    STRESSED("Zestresownaie", EmotionsCategories.BAD),
    BUSY("Zajętość", EmotionsCategories.BAD),
    BORED("Znudzenie", EmotionsCategories.BAD),

    // HAPPY
    OPTIMISTIC("Optimistyczność", EmotionsCategories.HAPPY),
    TRUSTING("Ufnośc", EmotionsCategories.HAPPY),
    PEACEFUL("Pokojowość", EmotionsCategories.HAPPY),
    POWERFUL("Pełen/na energii", EmotionsCategories.HAPPY),
    ACCEPTED("Akceptacja", EmotionsCategories.HAPPY),
    PROUD("Duma", EmotionsCategories.HAPPY),
    INTERESTED("Zainteresowanie", EmotionsCategories.HAPPY),
    CONTENT("Zadowolenie", EmotionsCategories.HAPPY),
    PLAYFUL("Zabawnie", EmotionsCategories.HAPPY),

    // Angry
    CRITICAL("Krytycznie", EmotionsCategories.ANGRY),
    DISTANT("Zdystansowanie", EmotionsCategories.ANGRY),
    FRUSTRATED("Frustracja", EmotionsCategories.ANGRY),
    AGGRESIVE("Agresja", EmotionsCategories.ANGRY),
    MAD("Szaleństwo", EmotionsCategories.ANGRY),
    BITTER("Gorzko", EmotionsCategories.ANGRY),
    HUMILIATED("Upokorzenie", EmotionsCategories.ANGRY),
    LETDOWN("Zawiedzenie", EmotionsCategories.ANGRY),

    // SAD
    HURT("Skrzywdzenie", EmotionsCategories.SAD),
    DEPRESSED("Depresyjność", EmotionsCategories.SAD),
    GUILTY("Winnie", EmotionsCategories.SAD),
    DESPAIR("Rozpacz", EmotionsCategories.SAD),
    VULNERABLE("Narażenie", EmotionsCategories.SAD),
    LONELY("Samotność", EmotionsCategories.SAD),

    // FEARFUL
    SCARED("Strach", EmotionsCategories.FEARFULL),
    ANXIOUS("Niepokój", EmotionsCategories.FEARFULL),
    INSECURE("Niepewność", EmotionsCategories.FEARFULL),
    WEAK("Słabość", EmotionsCategories.FEARFULL),
    REJECTED("Odrzucenie", EmotionsCategories.FEARFULL),
    THREATENED("Zagrożenie", EmotionsCategories.FEARFULL),

    // SUPRISED
    STARTLED("Zaskoczenie", EmotionsCategories.SUPRISED),
    CONFUSED("Zmieszanie", EmotionsCategories.SUPRISED),
    AMAZED("Zdumienie", EmotionsCategories.SUPRISED),
    EXCITED("Ekscytacja", EmotionsCategories.SUPRISED),

    // DISGUSTED
    DISAPPROVING("Nieakceptacja", EmotionsCategories.DISGUSTED),
    DISAPPOINTED("Rozczarowanie", EmotionsCategories.DISGUSTED),
    AWFUL("Strach", EmotionsCategories.DISGUSTED),
    REPELLED("Odpychanie", EmotionsCategories.DISGUSTED);


    private final String name;
    private final EmotionsCategories categorie;

}
