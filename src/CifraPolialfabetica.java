import java.text.Normalizer;
import java.util.*;


public class CifraPolialfabetica{

    List<Character> alfabeto = Arrays.asList('a','b','c','d','e','f','g','h','i','j','k'
    ,'l','m','n','o','p','q','r','s','t','u','v','x','w','y','z','A','B','C','D','E','F'
    ,'G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','X','W','Y','Z','0'
    ,'1','2','3','4','5','6','7','8','9',',','.',';',':','/','?','!','-','=','+','*',' ');
    List<Character> newAlfabeto = new ArrayList<Character>();

    public String encrypt(String texto){
        texto = removeAccents(texto);
        this.newAlfabeto.addAll(this.alfabeto); //copia alfabeto
        Collections.shuffle(this.newAlfabeto); //cria novo alfabeto aleatorio
        String newTexto = "";

        for(int i = 0; i < texto.length();i++){
            //cria texto criptografado
            for (int j = 0; j < this.alfabeto.size(); j++){
                if(texto.charAt(i) == this.alfabeto.get(j)){
                    newTexto += this.newAlfabeto.get(j);
                }
            }
        }
        //insere novo alfabeto no fim do texto
        for(int j = 0; j < this.newAlfabeto.size(); j++){
            newTexto += this.newAlfabeto.get(j);
        }

        return newTexto;
    }

    public String uncrypt(String texto){
        texto = removeAccents(texto);
        String newTexto = "";

        //recupera alfabeto do fim do texto
        for(int i=(texto.length()-alfabeto.size());i < texto.length();i++){
            this.newAlfabeto.add(texto.charAt(i));
        }

        for(int i = 0; i < (texto.length()-alfabeto.size());i++){
            //descriptografa texto
            for(int j = 0; j < this.alfabeto.size(); j++){
                if(texto.charAt(i) == this.newAlfabeto.get(j)){
                    newTexto += this.alfabeto.get(j);
                }
            }
        }
        return newTexto;
    }

    public static String removeAccents(String text) {
        String nomalizedText = Normalizer.normalize(text, Normalizer.Form.NFD);
        String noAccentsText = nomalizedText.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return noAccentsText;
    }
}