/******************************************************************************
 A classe Main é a classe de entrada do seu programa.
 Ela contém o método main, que é onde a execução do programa começa.
 @authors Gustavo Rodrigues e Victor Martins
 *******************************************************************************/

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        DNASequencer sequencer = new DNASequencer();
        try {
            int[] contagem = sequencer.calculaNucleotideos("src/main/resources/arquivo_exemplo_atividadeTDD.txt");
            if (contagem == null) {
                System.out.println("Erro: mais de 10% de caracteres inválidos.");
            } else {
                System.out.println("A: " + contagem[0]);
                System.out.println("C: " + contagem[1]);
                System.out.println("G: " + contagem[2]);
                System.out.println("T: " + contagem[3]);
                System.out.println("Erros: " + contagem[4]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
