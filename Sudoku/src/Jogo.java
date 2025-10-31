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
    private boolean fimDeJogo;

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
        if (!Analisador.jogoEhValido(mapa, false))
            throw new MapaInvalidoException("Mapa com valores duplicados");
        this.espacosLivres = (int)Arrays.stream(this.mapa).flatMap(m -> Arrays.stream(m)).filter(Quadradinho::estaDisponivel).count();
        if (espacosLivres == 0)
            throw new MapaInvalidoException("Mapa está totalmente preenchido");
        fimDeJogo = false;
    }

    // Menu
    public void menu()
    {
        Scanner s = new Scanner(System.in);
        String opcao = null;

        while (!fimDeJogo) {
            System.out.print("""

                    ------------- Menu -------------
                    1 - Preencher célula
                    2 - Apagar célula
                    3 - Mostrar Status do jogo
                    4 - Limpar
                    5 - Sair

                    Digite a opção desejada:\s """);
            opcao = s.nextLine();
            if (opcao.equals("1"))
                preencher(s);
            else if (opcao.equals("2"))
                apagar(s);
            else if (opcao.equals("3"))
                mostrarStatus();
            else if (opcao.equals("4"))
                limparJogo();
            else if (opcao.equals("5"))
                break;
            else
                System.out.println("\nOpção inválida");
        }
        s.close();
    }

    // Preencher
    private void preencher(Scanner s)
    {
        String jogada = "";

        System.out.println("""
                Digite o número e as coordenadas(linha, coluna) da célula que pretende preencher
                (Ex.: 8 2 3). Digite 0 para interromper.
                """);
        while (true) {
            mostrarMapa();
            System.out.print("Insira o próximo preenchimento: ");
            jogada = s.nextLine();
            if (jogada.equals("0"))
                break;
            try {
                validarPreenchimento(jogada);
            } catch (JogadaInvalidaException e) {
                System.out.println(e.getMessage());
            }
            if (espacosLivres == 0) {
                if (Analisador.jogoEhValido(mapa, false)) {
                    mostrarMensagemVitoria();
                    fimDeJogo = true;
                    return;
                } else
                    System.out.println("\nTodas as células estão preenchidas, porém existem um ou mais erros no jogo." +
                        "Por favor, consulte o status e/ou apague algumas células para corrigir o(s) erro(s).");
            }
        }
    }

    private void validarPreenchimento(String jogada) throws JogadaInvalidaException
    {
        String[] campos = jogada.trim().split(" ");
        int num, linha, coluna;
        if (campos.length != 3)
            throw new JogadaInvalidaException("Formato de preenchimento inválido");
        try {
            num = Integer.parseInt(campos[0]);
            linha = Integer.parseInt(campos[1]);
            coluna = Integer.parseInt(campos[2]);
        } catch (NumberFormatException e) {
            throw new JogadaInvalidaException("Números inválidos");
        }
        if (num <= 0 || num > 9)
            throw new JogadaInvalidaException("Número deve estar entre 0 e 9");   
        if (linha < 0 || linha >= 9 || coluna < 0 || coluna >=9)  
            throw new JogadaInvalidaException("Coordenadas inválidas");
        if (!mapa[linha][coluna].estaDisponivel())
            throw new JogadaInvalidaException("Célula já está preenchida");
        mapa[linha][coluna].preencher(num);
        espacosLivres--;
    }

    // Apagar
    private void apagar(Scanner s)
    {
        String jogada = "";

        System.out.println("""
                Digite as coordenadas(linha, coluna) da célula que deseja limpar (Ex.: 2 3).
                Digite 0 para interromper
                """);
        while (true) {
            mostrarMapa();
            System.out.print("Insira as coordenadas: ");
            jogada = s.nextLine();
            if (jogada.equals("0"))
                break;
            try {
                validarLimpeza(jogada);
            } catch (JogadaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validarLimpeza(String jogada) throws JogadaInvalidaException
    {
        String[] campos = jogada.trim().split(" ");
        int linha, coluna;

        if (campos.length != 2)
            throw new JogadaInvalidaException("Formato de preenchimento inválido");
        try {
            linha = Integer.parseInt(campos[0]);
            coluna = Integer.parseInt(campos[1]);
        } catch (NumberFormatException e) {
            throw new JogadaInvalidaException("Números inválidos");
        }
        if (linha < 0 || linha >= 9 || coluna < 0 || coluna >=9)  
            throw new JogadaInvalidaException("Coordenadas inválidas");
        if (mapa[linha][coluna].getEstado() == Estado.FIXO)
            throw new JogadaInvalidaException("Não pode apagar célula inicial");
        if (mapa[linha][coluna].getEstado() == Estado.LIVRE)
            throw new JogadaInvalidaException("Não pode apagar célula vazia");
        mapa[linha][coluna].apagar();
        espacosLivres++;   
    }

    // Limpar
    public void limparJogo()
    {
        espacosLivres += (int)Arrays.stream(mapa)
            .flatMap(arr -> Arrays.stream(arr))
            .filter(q -> q.getEstado() == Estado.PREENCHIDO)
            .peek(Quadradinho::apagar).count();
    }

    // Status
    private void mostrarStatus()
    {
        mostrarMapa();
        if (Analisador.jogoEhValido(mapa, true))
            System.out.println("Jogo OK!");
    }

    // Outros
    private void mostrarMapa()
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

    private void mostrarMensagemVitoria()
    {
        System.out.println("""
            \n    \033[32;1m███████████                                █████                                
            ░░███░░░░░███                              ░░███                                 
            ░███    ░███  ██████   ████████   ██████   ░███████   ██████  ████████    █████ 
            ░██████████  ░░░░░███ ░░███░░███ ░░░░░███  ░███░░███ ███░░███░░███░░███  ███░░  
            ░███░░░░░░    ███████  ░███ ░░░   ███████  ░███ ░███░███████  ░███ ░███ ░░█████ 
            ░███         ███░░███  ░███      ███░░███  ░███ ░███░███░░░   ░███ ░███  ░░░░███
            █████       ░░████████ █████    ░░████████ ████████ ░░██████  ████ █████ ██████ 
            ░░░░░         ░░░░░░░░ ░░░░░      ░░░░░░░░ ░░░░░░░░   ░░░░░░  ░░░░ ░░░░░ ░░░░░░\033[0m
        """);
    }
}