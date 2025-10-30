import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Function;

public class Jogo
{
    private Quadradinho[][] mapa;
    private int espacosLivres;

    public Jogo(String nomeArquivo) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        Function<String, Quadradinho[]> mapper = s -> {
            return s.chars().mapToObj(Quadradinho::criar).toArray(Quadradinho[]::new);
        };   

        this.mapa = br.lines().map(mapper).toArray(Quadradinho[][]::new);
        br.close();
        this.espacosLivres = (int)Arrays.stream(this.mapa).flatMap(m -> Arrays.stream(m)).filter(Quadradinho::estaDisponivel).count();
        System.out.printf("Jogo criado com %d espaços livres\n", this.espacosLivres);
        Analisador.jogoEhValido(this.mapa);
    }

    public Quadradinho[][] getMapa() { return this.mapa; }
}
