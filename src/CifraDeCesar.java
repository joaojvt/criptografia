import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CifraDeCesar {

    List<String> characters = new ArrayList<String>();

    public CifraDeCesar() {
        characters = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"
                , "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "x", "w", "y", "z", "A", "B", "C", "D", "E", "F"
                , "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "W", "Y", "Z", "0"
                , "1", "2", "3", "4", "5", "6", "7", "8", "9", ",", ".", ";", ":", "/", "?", "!", "-", "=", "+", "*", " ");
    }

    public String encrypt(String fileText, Integer displacementValue) {
        String noAccentsText = removeAccents(fileText);
        String[] splitedText = noAccentsText.split("");

        String encryptedText = encryptText(splitedText, displacementValue);
        encryptedText += ";" + displacementValue;
        return encryptedText;
    }

    public String encryptText(String[] text, Integer displacementValue){
        String encryptedText = null;
        for (int i = 0; i < text.length; i++) {
            encryptedText += encryptCharacter(text[i], displacementValue);
        }
        return encryptedText;
    }

    private String encryptCharacter(String character, Integer dispalcement) {
        if (character.equals(" "))
            return character;
        if (characters.indexOf(character) == -1)
            return " ";

        int characterDisplacementPosition = calculateEncryptPosition(character, dispalcement);
        return characters.get(characterDisplacementPosition);
    }

    private Integer calculateEncryptPosition(String character, Integer dispalcement){
        int characterDisplacementPosition = characters.indexOf(character) + dispalcement;
        if (isBiggerThanSize(characterDisplacementPosition))
            characterDisplacementPosition = characterDisplacementPosition - characters.size();
        return characterDisplacementPosition;
    }

    private boolean isBiggerThanSize(Integer characterDisplacementPosition) {
        if (characterDisplacementPosition >= characters.size())
            return true;
        return false;
    }

    public String uncrypt(String textEncrypted) {
        Integer displacementValue = getDisplacementeFromText(textEncrypted);
        String[] splitedText = textEncrypted.split("");
        splitedText = Arrays.copyOf(splitedText, splitedText.length - 2);

        String uncryptedText = uncryptText(splitedText, displacementValue);
        return uncryptedText;
    }

    private Integer getDisplacementeFromText(String textEncrypted){
        String[] splitedText = textEncrypted.split(";");
        return Integer.parseInt(splitedText[splitedText.length - 1]);
    }

    private String uncryptText(String[] textEncrypted, Integer displacementValue){
        String uncryptedText = "";
        for (int i = 0; i < textEncrypted.length; i++) {
            uncryptedText += uncryptCharacter(textEncrypted[i], displacementValue);
        }
        return uncryptedText;
    }

    private String uncryptCharacter(String character, Integer dispalcement) {
        if (character.equals(" "))
            return character;
        if (characters.indexOf(character) == -1)
            return " ";

        int characterDisplacementPosition = calculateUncryptPosition(character, dispalcement);
        return characters.get(characterDisplacementPosition);
    }

    private Integer calculateUncryptPosition(String character, Integer dispalcement){
        int characterDisplacementPosition = characters.indexOf(character) - dispalcement;
        if(isSmallerThanZero(characterDisplacementPosition))
            characterDisplacementPosition = characterDisplacementPosition + characters.size();
        return characterDisplacementPosition;
    }

    private Boolean isSmallerThanZero(Integer characterDisplacementPosition){
        if (characterDisplacementPosition < 0)
            return true;
        return false;
    }

    private static String removeAccents(String text) {
        String nomalizedText = Normalizer.normalize(text, Normalizer.Form.NFD);
        String noAccentsText = nomalizedText.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return noAccentsText;
    }
}