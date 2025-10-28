import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class Banco
{
    private int proxNumeroConta;
    private List<ContaBancaria> contas;
    private ClienteController cc;

    public Banco()
    {
        this.proxNumeroConta = 1051;
        this.contas = new ArrayList<>();
        this.cc = new ClienteController();
    }

    public void menu()
    {
        String[] opcoes = {"Criar Conta", "Acessar Conta", "Mostrar Contas", "Sair"};
        int opcao = 0;
        ContaBancaria conta;

        while (opcao != 3) {
            opcao = JOptionPane.showOptionDialog(null,
                "Escolha a ação necessária", "Banco", -1, -1, null,
                opcoes, 3);
            switch(opcao) {
                case 0:
                    criarConta();
                    break;
                case 1:
                    conta = pesquisarPorNumero(Integer.parseInt(
                        JOptionPane.showInputDialog("Digite o número da conta para acessar")));
                    if (conta == null)
                        JOptionPane.showMessageDialog(null, "Conta não encontrada");
                    else
                        acessarConta(conta);
                    break;
                case 2:
                    mostrarContas();
                    break;
            }
        }
    }

    public void mostrarContas()
    {
        StringBuilder sb = new StringBuilder();

        this.contas.stream()
            .forEach(c -> {
                sb.append(c);
                sb.append('\n');
            });
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void criarConta()
    {
        String nome = JOptionPane.showInputDialog("Informe o nome do cliente:");
        Cliente titular = cc.pesquisarPorNome(nome);
        String[] opcoes = {"Corrente", "Poupanca"};
        
        if (titular == null) {
            String documento = JOptionPane.showInputDialog(
                "Cliente não encontrado, informe o documento para cadastro");
            titular = new Cliente(nome, documento);
            cc.adicionarCliente(titular);
        } else
            JOptionPane.showMessageDialog(null,
                "Cliente encontrado: documento = " + titular.getDocumento());
        int opc = JOptionPane.showOptionDialog(null, "Escolha o tipo da conta",
            "Abertura de Conta", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, opcoes, 0);
        if (opc == 0) {
            ContaCorrente conta = new ContaCorrente(obterNUmeroAgencia(), obterNumeroConta(), titular);
            JOptionPane.showMessageDialog(null, "Criada conta " + conta.getNumero() + " na agencia " + conta.getAgencia());
            this.contas.add(conta);
        } else {
            ContaPoupanca conta = new ContaPoupanca(obterNUmeroAgencia(), obterNumeroConta(), titular);
            JOptionPane.showMessageDialog(null, "Criada conta " + conta.getNumero() + " na agencia " + conta.getAgencia());
            this.contas.add(conta);
        }
    }

    public void acessarConta(ContaBancaria conta)
    {
        String[] opcoes = {"Sacar", "Depositar", "Transferir", "Ver Saldo", "Sair"};
        int opcao = 0;
        float valor;
        ContaBancaria destino;

        while (opcao != 4) {
            opcao = JOptionPane.showOptionDialog(null, 
                "Escoha a ação desejada", "Menu Conta: " + conta.getNumero(),
                -1, -1, null, opcoes, 4);
            switch(opcao) {
                case 0:
                    valor = Float.parseFloat(
                        JOptionPane.showInputDialog("Digite o valor do saque"));
                    conta.sacar(valor);
                    break;
                case 1:
                    valor = Float.parseFloat(
                        JOptionPane.showInputDialog("Digite o valor do deposito"));
                    conta.depositar(valor);
                    break;
                case 2:
                    destino = pesquisarPorNumero(Integer.parseInt(
                        JOptionPane.showInputDialog("Digite o numero da conta de destino")));
                    if (destino == null) {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada");
                        break;
                    }
                    valor = Float.parseFloat(
                        JOptionPane.showInputDialog("Digite o valor para transferir"));
                    conta.transferir(valor, destino);
                    break;
                case 3:
                    conta.mostrarSaldo();
                    break;
            }
        }
    }

    private ContaBancaria pesquisarPorNumero(int numero)
    {
        String formatado = String.format("%05d", numero);
        return this.contas.stream()
            .filter(c -> c.getNumero().equals(formatado))
            .findAny().orElse(null);
    }

    private String obterNumeroConta()
    {
        String numero = String.format("%05d", this.proxNumeroConta);
        this.proxNumeroConta++;
        return numero;
    }

    private String obterNUmeroAgencia()
    {
        Random r = new Random();
        String agencia = String.format("%03d", r.nextInt(3) + 1);
        return agencia;
    }


}
