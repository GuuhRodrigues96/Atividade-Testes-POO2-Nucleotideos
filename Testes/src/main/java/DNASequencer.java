/******************************************************************************
 Essa classe contém a lógica principal para calcular
 a quantidade de cada tipo de nucleotídeo (A, C, G, T) em uma sequência de DNA,
 além de contar erros de caracteres inválidos.
 @authors Gustavo Rodrigues e Victor Martins
 *******************************************************************************/

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;

public class DNASequencer {

    public int[] calculaNucleotideos(String caminhoArquivo) throws IOException {
        int[] contagem = new int[5]; // [A, C, G, T, erros]
        int totalCaracteres = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                for (char nucleotideo : linha.toCharArray()) {
                    totalCaracteres++;
                    switch (nucleotideo) {
                        case 'A':
                            contagem[0]++;
                            break;
                        case 'C':
                            contagem[1]++;
                            break;
                        case 'G':
                            contagem[2]++;
                            break;
                        case 'T':
                            contagem[3]++;
                            break;
                        default:
                            contagem[4]++; // Contagem de erros
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Arquivo não encontrado: " + caminhoArquivo);
        }

        // Verificar se o número de caracteres inválidos ultrapassa 10% do total
        if (totalCaracteres > 0 && (double) contagem[4] / totalCaracteres > 0.10) {
            return null; // Mais de 10% de caracteres inválidos, retorna null
        }

        return contagem;
    }
}
