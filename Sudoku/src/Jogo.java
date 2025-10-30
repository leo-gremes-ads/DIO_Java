import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class Jogo
{
    private Quadradinho[][] mapa;
    private int espacosLivres;

    public Jogo(String nomeArquivo) 
    throws FileNotFoundException, IOException, MapaInvalidoException
    {
        BufferedReader br = new BufferedReader(new FileReader("mapas/" + nomeArquivo));
        Function<String, Quadradinho[]> mapper = s -> 
            s.chars().mapToObj(Quadradinho::criar).toArray(Quadradinho[]::new);

        this.mapa = br.lines().map(mapper).toArray(Quadradinho[][]::new);
        br.close();
        if (!Analisador.dimensoesValidas(mapa))
            throw new MapaInvalidoException("Dimensões inválidas");
        if (!Analisador.numerosValidos(mapa))
            throw new MapaInvalidoException("Mapa com números inválidos");
        if (!Analisador.jogoEhValido(mapa))
            throw new MapaInvalidoException("Mapa com valores duplicados");
        this.espacosLivres = (int)Arrays.stream(this.mapa).flatMap(m -> Arrays.stream(m)).filter(Quadradinho::estaDisponivel).count();
        System.out.println("Espacos livres = " + espacosLivres);
        if (espacosLivres == 0)
            throw new MapaInvalidoException("Mapa está totalmente preenchido");

    }

    public void escrever()
    {
        Scanner s = new Scanner(System.in);
        String jogada;
        String[] campos;
        int num, linha, coluna;

        System.out.println("""
                Digite o número e as coordenadas(linha, coluna) da célula que pretende preencher
                (Ex.: 8 2 3). Digite 0 para interromper:
                """);
        jogada = s.nextLine();
        campos = jogada.trim().split(" ");
        s.close();
    }

    private int[] validarJogada(String jogada) throws JogadaInvalidaException
    {
        String[] campos = jogada.trim().split(" ");
        int num, linha, coluna;
        if (campos.length != 3)
            throw new JogadaInvalidaException("Formato de jogada inválido");
        try {
            num = Integer.parseInt(campos[0]);
            linha = Integer.parseInt(campos[1]);
            coluna = Integer.parseInt(campos[2]);
        } catch (NumberFormatException e) {
            throw new JogadaInvalidaException("Numeros invalidos");
        }        
    }

    public void mostrarMapa()
    {
        System.out.printf("""
                    0 1 2   3 4 5   6 7 8

                0   %s %s %s | %s %s %s | %s %s %s
                1   %s %s %s | %s %s %s | %s %s %s
                2   %s %s %s | %s %s %s | %s %s %s
                    ------+-------+------
                3   %s %s %s | %s %s %s | %s %s %s
                4   %s %s %s | %s %s %s | %s %s %s
                5   %s %s %s | %s %s %s | %s %s %s
                    ------+-------+------
                6   %s %s %s | %s %s %s | %s %s %s
                7   %s %s %s | %s %s %s | %s %s %s
                8   %s %s %s | %s %s %s | %s %s %s
                """,
                mapa[0][0], mapa[0][1], mapa[0][2], mapa[0][3], mapa[0][4], mapa[0][5], mapa[0][6], mapa[0][7], mapa[0][8],
                mapa[1][0], mapa[1][1], mapa[1][2], mapa[1][3], mapa[1][4], mapa[1][5], mapa[1][6], mapa[1][7], mapa[1][8],
                mapa[2][0], mapa[2][1], mapa[2][2], mapa[2][3], mapa[2][4], mapa[2][5], mapa[2][6], mapa[2][7], mapa[2][8],
                mapa[3][0], mapa[3][1], mapa[3][2], mapa[3][3], mapa[3][4], mapa[3][5], mapa[3][6], mapa[3][7], mapa[3][8],
                mapa[4][0], mapa[4][1], mapa[4][2], mapa[4][3], mapa[4][4], mapa[4][5], mapa[4][6], mapa[4][7], mapa[4][8],
                mapa[5][0], mapa[5][1], mapa[5][2], mapa[5][3], mapa[5][4], mapa[5][5], mapa[5][6], mapa[5][7], mapa[5][8],
                mapa[6][0], mapa[6][1], mapa[6][2], mapa[6][3], mapa[6][4], mapa[6][5], mapa[6][6], mapa[6][7], mapa[6][8],
                mapa[7][0], mapa[7][1], mapa[7][2], mapa[7][3], mapa[7][4], mapa[7][5], mapa[7][6], mapa[7][7], mapa[7][8],
                mapa[8][0], mapa[8][1], mapa[8][2], mapa[8][3], mapa[8][4], mapa[8][5], mapa[8][6], mapa[8][7], mapa[8][8]                
                );
    }

    public Quadradinho[][] getMapa() { return this.mapa; }
}