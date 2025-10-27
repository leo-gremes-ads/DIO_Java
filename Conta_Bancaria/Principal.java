import java.util.Scanner;

public class Principal
{
    public static void main(String[] args)
    {
        ContaTerminal conta;
        Scanner s = new Scanner(System.in);
        int numero;
        String nome, agencia;
        float saldo;

        System.out.print("Informe o numero da conta: ");
        numero = Integer.parseInt(s.nextLine());
        System.out.print("Informe o numero da agencia: ");
        agencia = s.nextLine();
        System.out.print("Informe o nome do titular: ");
        nome = s.nextLine();
        System.out.print("Informe o saldo inicial: ");
        saldo = Float.parseFloat(s.nextLine());
        s.close();
        conta = new ContaTerminal(numero, agencia, nome, saldo);

        System.out.printf("Olá %s, obrigado por criar uma conta em nosso banco, " + 
            "sua agência é %s, conta %d, e seu saldo %.2f já está disponível para saque\n",
            conta.getNomeCliente(), conta.getAgencia(), conta.getNumero(), conta.getSaldo());
    }   
}