import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public abstract class Analisador
{
    private static final Predicate<Integer> naoNulo = i -> i != null;

    public static boolean jogoEhValido(Quadradinho[][] mapa)
    {
        Set<Integer> unicos = new HashSet<>();
        StringBuilder erros = new StringBuilder();
        boolean ehValido = true;

        ehValido = ehValido && verificarLinhas(mapa, erros, unicos);
        ehValido = ehValido && verificarColunas(mapa, erros, unicos);
        ehValido = ehValido && verificarQuadrados(mapa, erros, unicos);

        if (ehValido)
            System.out.println("Mapa OK!");
        else
            System.out.print(erros.toString());
        return ehValido;
    }
    
    private static boolean temDuplicados(List<Integer> numeros, Set<Integer> unicos)
    {
        unicos.clear();
        return numeros.stream().filter(i -> !unicos.add(i)).count() >= 1;
    }

    // Linhas
    private static boolean verificarLinhas(Quadradinho[][] mapa, StringBuilder erros, Set<Integer> unicos)
    {
        boolean ehValido = true;

        for (int i = 0; i < 9; i++) {
            if (temDuplicados(pegarLinha(mapa, i), unicos)) {
                erros.append("Valores duplicados na linha " + i);
                erros.append("\n");
                ehValido = false;
            }
        }
        return ehValido;
    }

    private static List<Integer> pegarLinha(Quadradinho[][] mapa, int l)
    {
        return Arrays.stream(mapa[l])
            .map(Quadradinho::getNumero)
            .filter(naoNulo)
            .toList();
    }

    // Colunas
    private static boolean verificarColunas(Quadradinho[][] mapa, StringBuilder erros, Set<Integer> unicos)
    {
        boolean ehValido = true;

        for (int i = 0; i < 9; i++) {
            if (temDuplicados(pegarColuna(mapa, i), unicos)) {
                erros.append("Valores duplicados na coluna " + i);
                erros.append("\n");
                ehValido = false;
            }
        }
        return ehValido;
    }

    private static List<Integer> pegarColuna(Quadradinho[][] mapa, int c)
    {
        return IntStream.range(0, 9)
            .mapToObj(i -> mapa[i][c].getNumero())
            .filter(naoNulo)
            .toList();
    }

    // Quadrados 3 por 3
    private static boolean verificarQuadrados(Quadradinho[][] mapa, StringBuilder erros, Set<Integer> unicos)
    {
        boolean ehValido = true;
        int iLinha, iColuna;

        for (int i = 0; i < 9; i++) {
            iLinha = i / 3;
            iColuna = i % 3;
            if (temDuplicados(pegarQuadrado(mapa, iLinha, iColuna), unicos)) {
                erros.append("Valores duplicados no quadrado (" + iLinha + ", " + iColuna + ")");
                erros.append("\n");
                ehValido = false;
            }
        }
        return ehValido;
    }

    private static List<Integer> pegarQuadrado(Quadradinho[][] mapa, int l, int c)
    {
        return IntStream.range(l * 3, (l * 3) + 3)
            .mapToObj(i -> Arrays.stream(mapa[i])
                .skip(c * 3)
                .limit(3)
            )
            .flatMap(str -> str.map(Quadradinho::getNumero))
            .filter(naoNulo)
            .toList();
    }
}
