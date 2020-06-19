import java.io.*;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        new Principal().run();
    }

    public void run() {
        Scanner ler = new Scanner(System.in);
        int opcao = 0;
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Digite uma opcao:");
            System.out.println("1 - encriptar:");
            System.out.println("2 - descriptar");
            System.out.println("0 - sair");
            opcao = ler.nextInt();
            switch (opcao) {
                case 1:
                    encrypt();
                    break;
                case 2:
                    uncrypt();
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Selecione uma opcap valida");
                    break;
            }
        }
    }


    public String readFile(String nameFile) {
        String textFile = "";
        BufferedReader buffer = null;

        //Retirar "./src/" antes de enviar
        try {
            buffer = new BufferedReader(new FileReader(nameFile));
            while (buffer.ready()) {
                String line = buffer.readLine();
                textFile += line + "\n";
                textFile = textFile.substring(0, textFile.length() - 1);
            }
            buffer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado");
            System.out.println("Tente outra vez");
        } catch (IOException e) {
            System.out.println("erro ao ler o arquivo");
        }
        return textFile;
    }

    public void saveFile(String textFile, String nameFile) {
        try {
            //cria arquivo
            File fileObj = new File(nameFile);
            if (!fileObj.exists()) {
                System.out.println("Arquivo salvo: " + fileObj.getName());

                //salva texto
                FileWriter Writer = new FileWriter(nameFile);
                Writer.write(textFile);
                Writer.close();
            } else {
                fileObj.renameTo(new File(nameFile));
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public void encrypt() {
        Integer option = null;
        Scanner read = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            isRunning = false;
            System.out.println("Escolha uma opcao:");
            System.out.println("1 - Criptografia de Cesar");
            System.out.println("2 - Criptografia por cifras Polialfabeitcas");
            System.out.println("3 - Hibrido");
            System.out.println("0 - Sair");
            option = read.nextInt();
            switch (option) {
                case 1:
                    cesarCipherEncrypt();
                    isRunning = false;
                    break;
                case 2:
                    poiligraphyEncrypt();
                    isRunning = false;
                    break;
                case 3:
                    hybridEncrypt();
                    isRunning = false;
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Selecione uma opcao valida");
                    isRunning = true;
                    break;
            }
        }
    }

    private void uncrypt() {
        Integer option = null;
        boolean isRunnig = true;
        Scanner read = new Scanner(System.in);
        while (isRunnig) {
            System.out.println("Digite o tipo de criptografia utilizada");
            System.out.println("1 - Cifra de Cesar");
            System.out.println("2 - Cifra Polialfabetica");
            System.out.println("3 - Hibirida");
            System.out.println("0 - Sair");
            option = read.nextInt();
            switch (option){
                case 1:
                    cesarCipherUncrypt();
                    isRunnig = false;
                    break;
                case 2:
                    poiligraphyUncrypt();
                    isRunnig = false;
                    break;
                case 3:
                    hybridUncrypt();
                    isRunnig = false;
                    break;
                case 0:
                    isRunnig = false;
                    break;
                default:
                    System.out.println("Digite uma opcao valida");
                    isRunnig = true;
                    break;
            }
        }
    }

    private void cesarCipherEncrypt() {
        Scanner read = new Scanner(System.in);
        String fileText = null;
        String nameFile = null;

        System.out.println("Digite o nome do arquivo:");
        System.out.println("Ditite \"0\" para sair");
        boolean isRunning = true;
        while (isRunning) {
            nameFile = read.nextLine();
            if (nameFile.equals("0")) System.exit(0);
            fileText = readFile(nameFile);
            if (fileText != null) isRunning = false;
        }

        System.out.println("Digite o valor do parametro de deslocamento");
        int displacementValue = read.nextInt();

        CrifraDeCesar cifra = new CrifraDeCesar();
        String encryptedText = cifra.encrypt(fileText, displacementValue);

        nameFile = nameFile.split("\\.")[0];
        saveFile(encryptedText, nameFile + "-encrypted.txt");
    }

    public void cesarCipherUncrypt(){
        Scanner read = new Scanner(System.in);
        String fileText = null;
        String nameFile = null;

        System.out.println("Digite o nome do arquivo:");
        System.out.println("Ditite \"0\" para sair");
        boolean isRunning = true;
        while (isRunning) {
            nameFile = read.nextLine();
            if (nameFile.equals("0")) System.exit(0);
            fileText = readFile(nameFile);
            if (fileText != null) isRunning = false;
        }

        CrifraDeCesar cifra = new CrifraDeCesar();
        String uncryptedText = cifra.uncrypt(fileText);

        nameFile = nameFile.split("-")[0];
        saveFile(uncryptedText, nameFile + "-uncrypted.txt");
    }

    private void poiligraphyEncrypt() {

        String fileText = null;

    }
    private void poiligraphyUncrypt() {

        String fileText = null;

    }

    private void hybridUncrypt() {

    }
    private void hybridEncrypt() {

    }
}
