import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrifraDeCesar {

    List<String> characters = new ArrayList<String>();

    public CrifraDeCesar() {
        characters = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k"
                ,"l","m","n","o","p","q","r","s","t","u","v","x","w","y","z","A","B","C","D","E","F"
                ,"G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","W","Y","Z","0"
                ,"1","2","3","4","5","6","7","8","9",",",".",";",":","/","?","!","-","=","+","*"," ");
    }

    public static String removeAccents(String text) {
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return text;
    }

    public String encrypt(String fileText, Integer displacementValue) {
        fileText = removeAccents(fileText);
        String[] splitedText = fileText.split("");

        String encryptedText = "";
        for (int i = 0; i < splitedText.length; i++) {
            encryptedText += encryptCharacter(splitedText[i], displacementValue);
        }

        encryptedText += ";" + displacementValue;
        return encryptedText;
    }

    private String encryptCharacter(String character, Integer dispalcement) {
        if (character.equals(" ")) return character;
        if (characters.indexOf(character) == -1) return " ";
        int characterDisplacementPosition = characters.indexOf(character) + dispalcement;
        characterDisplacementPosition = characterDisplacementPosition >= characters.size() ? characterDisplacementPosition - characters.size() : characterDisplacementPosition;
        return characters.get(characterDisplacementPosition);
    }

    public String uncrypt(String textEncrypted) {
        String[] splitedText = textEncrypted.split(";");
        Integer displacementValue = Integer.parseInt(splitedText[splitedText.length-1]);
        splitedText = textEncrypted.split("");
        splitedText = Arrays.copyOf(splitedText, splitedText.length-2);
        String uncryptedText = "";
        for (int i = 0; i < splitedText.length; i++) {
            uncryptedText += uncryptCharacter(splitedText[i], displacementValue);
        }
        return uncryptedText;
    }

    private String uncryptCharacter(String character, Integer dispalcement) {
        if (character.equals(" ")) return character;
        if (characters.indexOf(character) == -1) return " ";
        int characterDisplacementPosition = characters.indexOf(character) - dispalcement;
        characterDisplacementPosition = characterDisplacementPosition < 0 ? characterDisplacementPosition + characters.size() : characterDisplacementPosition;
        return characters.get(characterDisplacementPosition);
    }


}