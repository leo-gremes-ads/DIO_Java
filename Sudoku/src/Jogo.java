import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Jogo
{
    private Quadradinho[][] mapa;
    private int linhas;
    private int colunas;
    private int espacosLivres;

    public Jogo(String nomeArquivo) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        Function<String, Quadradinho[]> mapper = s -> {
            return s.chars().mapToObj(Quadradinho::criar).toArray(Quadradinho[]::new);
        };   

        this.mapa = br.lines().map(mapper).toArray(Quadradinho[][]::new);
        br.close();
        this.linhas = this.mapa.length;
        this.colunas = this.mapa[0].length;
        this.espacosLivres = (int)Arrays.stream(this.mapa).flatMap(m -> Arrays.stream(m)).filter(Quadradinho::estaDisponivel).count();
        System.out.printf("Jogo criado com %d linhas, %d colunas e %d espaços livres\n", this.linhas, this.colunas, this.espacosLivres);
    }

    public boolean validarLinhas()
    {
        Set<Integer> numeros = new HashSet<>();
        Integer atual;
        boolean resposta = true;

        for (int linha = 0; linha < this.linhas; linha++) {
            numeros.clear();
            for (int coluna = 0; coluna < this.colunas; coluna++) {
                atual = mapa[linha][coluna].getNumero();
                if (atual != null) {
                    if (!numeros.add(atual)) {
                        resposta = false;
                        System.out.println("Valores duplicados na linha " + linha);
                    }
                }
            }
        }
        if (resposta)
            System.out.println("Linhas válidas");
        return resposta;
    }

    public boolean validarColunas()
    {
        Set<Integer> numeros = new HashSet<>();
        Integer atual;
        boolean resposta = true;

        for (int coluna = 0; coluna < this.colunas; coluna++) {
            numeros.clear();
            for (int linha = 0; linha < this.linhas; linha++) {
                atual = mapa[linha][coluna].getNumero();
                if (atual != null) {
                    if (!numeros.add(atual)) {
                        resposta = false;
                        System.out.println("Valores duplicados na coluna " + coluna);
                    }
                }
            }
        }
        if (resposta)
            System.out.println("Colunas válidas");
        return resposta;
    }

    public boolean validarQuadrados()
    {
        Set<Integer> numeros = new HashSet<>();
        Integer atual;
        boolean resposta = true;
        int qLinhas = this.linhas / 3;
        int qColunas = this.colunas / 3;

        for (int ql = 0; ql < qLinhas; ql++) {
            for (int qc = 0; qc < qColunas; qc++) {
                numeros.clear();
                for (int linha = 0; linha < 3; linha++) {
                    for (int coluna = 0; coluna < 3; coluna++) {
                        atual = mapa[linha + (ql * 3)][coluna + (qc * 3)].getNumero();
                        if (atual != null) {
                            if (!numeros.add(atual)) {
                                resposta = false;
                                System.out.printf("Valores duplicados no quadrado [%d][%d]\n", ql, qc);
                            }
                        }
                    }
                }
            }
        }
        if (resposta)
            System.out.println("Quadrados válidos");
        return resposta;
    }

    private boolean temDuplicados(List<Integer> lista)
    {
        return lista.stream().filter(i -> Collections.frequency(lista, i) > 1).count() >= 1;
    }

    public boolean verificarLinhas()
    {
        boolean resposta = true;
        List<Integer> lista;

        for (int linha = 0; linha < this.linhas; linha++) {
            lista = Arrays.stream(mapa[linha])
                .map(Quadradinho::getNumero)
                .filter(n -> n != null)
                .toList();
            if (temDuplicados(lista)) {
                resposta = false;
                System.out.println("Valores duplicados na linha " + linha);
            }
        }
        if (resposta)
            System.out.println("Linhas sem erro");
        return resposta;
    }

    public boolean verificarQuadrados()
    {
        boolean resposta = true;

        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                if (temDuplicados(obterQuadrados(linha * 3, coluna * 3))) {
                    resposta = false;
                    System.out.println("Valores duplicados no quadrado " + linha + ", " + coluna);
                }
            }
        }
        if (resposta)
            System.out.println("Quadrados OK");
        return resposta;
    }

    private List<Integer> obterQuadrados(int linhaInicial, int colunaInicial)
    {
        return IntStream.range(linhaInicial, linhaInicial + 3)
            .mapToObj(i -> Arrays.stream(this.mapa[i])
                .skip(colunaInicial)
                .limit(3)
            )
            .flatMap(q -> q.map(Quadradinho::getNumero))
            .filter(i -> i != null)
            .toList();
    }

    public Quadradinho[][] getMapa() { return this.mapa; }
}
