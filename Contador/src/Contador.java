import java.util.InputMismatchException;
import java.util.Scanner;

public class Contador
{
    public static void main(String[] args)
    {
        int p1, p2;
        Scanner s = new Scanner(System.in);

        try {
            System.out.print("Digite o primeiro parâmetro: ");
            p1 = s.nextInt();
            System.out.print("Digite o segundo parâmetro: ");
            p2 = s.nextInt();
            contar(p1, p2);
        } catch (ParametrosInvalidosException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Formato de número inválido");
        }
    }

    public static void contar(int p1, int p2) throws ParametrosInvalidosException
    {
        if (p1 >= p2)
            throw new ParametrosInvalidosException(
                "Segundo parâmetro deve ser maior que o primeiro");
        int vezes = p2 - p1;
        for (int i = 1; i <= vezes; i++)
            System.out.println("Imprimindo o número " + i);
    }
}
