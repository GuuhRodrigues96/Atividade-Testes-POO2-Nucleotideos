/******************************************************************************
 Essa classe contém os testes de unidade para o
 método calculaNucleotideos usando o framework JUnit 5.
 Ela valida se o método está funcionando corretamente
 com diferentes cenários.
 @authors Gustavo Rodrigues e Victor Martins
 *******************************************************************************/

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class DNASequencerTest {

    private DNASequencer sequencer;

    @BeforeEach
    void setUp() {
        sequencer = new DNASequencer();
    }

    @Test
    @DisplayName("Verifica se o valor retornado está correto para a sequência válida 'AAAGTCTGAC'")
    void testCalculaNucleotideosSequenciaValida() throws IOException {
        String sequencia = "AAAGTCTGAC";
        File arquivoTeste = criarArquivoDeTeste(sequencia);

        int[] resultado = sequencer.calculaNucleotideos(arquivoTeste.getAbsolutePath());

        assertEquals(4, resultado[0]); // A
        assertEquals(2, resultado[1]); // C
        assertEquals(2, resultado[2]); // G
        assertEquals(2, resultado[3]); // T
        assertEquals(0, resultado[4]); // Erros
    }

    @Test
    @DisplayName("Verifica se a exceção é lançada quando o arquivo de entrada não é encontrado")
    void testCalculaNucleotideosArquivoNaoEncontrado() {
        String caminhoInvalido = "caminho/invalido/arquivo.txt"; // Caminho inexistente

        Exception exception = assertThrows(IOException.class, () -> sequencer.calculaNucleotideos(caminhoInvalido));

        String mensagemEsperada = "Arquivo não encontrado: " + caminhoInvalido;
        String mensagemAtual = exception.getMessage();

        assertTrue(mensagemAtual.contains(mensagemEsperada),
                "A mensagem de erro deveria indicar que o arquivo não foi encontrado.");
    }

    // Método auxiliar para criar arquivos de teste
    private File criarArquivoDeTeste(String conteudo) throws IOException {
        File arquivo = File.createTempFile("dnaTeste", ".txt");
        arquivo.deleteOnExit(); // Para garantir que o arquivo seja deletado ao final do teste
        java.nio.file.Files.write(arquivo.toPath(), conteudo.getBytes());
        return arquivo;
    }
}
