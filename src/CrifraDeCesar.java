import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrifraDeCesar {

    List<String> characters = new ArrayList<String>();

    public CrifraDeCesar() {
        characters.add("a");
        characters.add("b");
        characters.add("c");
        characters.add("d");
        characters.add("e");
        characters.add("f");
        characters.add("g");
        characters.add("h");
        characters.add("i");
        characters.add("j");
        characters.add("k");
        characters.add("l");
        characters.add("m");
        characters.add("n");
        characters.add("o");
        characters.add("p");
        characters.add("q");
        characters.add("r");
        characters.add("s");
        characters.add("t");
        characters.add("u");
        characters.add("b");
        characters.add("x");
        characters.add("y");
        characters.add("w");
        characters.add("z");
        characters.add("A");
        characters.add("B");
        characters.add("C");
        characters.add("D");
        characters.add("E");
        characters.add("F");
        characters.add("G");
        characters.add("H");
        characters.add("I");
        characters.add("J");
        characters.add("K");
        characters.add("L");
        characters.add("M");
        characters.add("N");
        characters.add("O");
        characters.add("P");
        characters.add("Q");
        characters.add("R");
        characters.add("S");
        characters.add("T");
        characters.add("U");
        characters.add("V");
        characters.add("X");
        characters.add("Y");
        characters.add("W");
        characters.add("Z");
        characters.add("0");
        characters.add("1");
        characters.add("2");
        characters.add("3");
        characters.add("4");
        characters.add("5");
        characters.add("6");
        characters.add("7");
        characters.add("8");
        characters.add("9");
        characters.add(",");
        characters.add(".");
        characters.add(";");
        characters.add(":");
        characters.add("/");
        characters.add("?");
        characters.add("!");
        characters.add("-");
        characters.add("=");
        characters.add("+");
        characters.add("*");
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
        int characterDisplacementPosition = characters.indexOf(character) - dispalcement;
        characterDisplacementPosition = characterDisplacementPosition < 0 ? characterDisplacementPosition + characters.size() : characterDisplacementPosition;
        return characters.get(characterDisplacementPosition);
    }


}