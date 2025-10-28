package classes;

import javax.swing.JOptionPane;
import interfaces.Navegador;
import interfaces.ReprodutorMusical;

public class Iphone
{
    private Telefone telefone;
    private Navegador navegador;
    private ReprodutorMusical reprodutor;

    public Iphone()
    {
        this.telefone = new Telefone();
        this.navegador = new Safari();
        this.reprodutor = new Ipod();
    }

    public void menu()
    {
        String[] opcoes = {"Ipod", "Telefone", "Safari", "Sair"};
        int opcao = 0;

        while (opcao != 3) {
            opcao = JOptionPane.showOptionDialog(null, "Selecione o App desejado",
                 "iPhone", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                 null, opcoes, 3);
            switch(opcao) {
                case 0:
                    menuReprodutor();
                    break;
                case 1:
                    menuTelefone();
                    break;
                case 2:
                    menuNavegador();
                    break;
            }
        }
    }

    public void menuReprodutor()
    {
        String[] opcoes = {"Tocar", "Pausar", "Selecionar Música", "Sair"};
        String musica;
        int opcao = 0;

        while (opcao != 3) {
            opcao = JOptionPane.showOptionDialog(null, "Selecione a ação desejada",
                 "Reprodutor Musical", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                 null, opcoes, 3);
            switch(opcao) {
                case 0:
                    this.reprodutor.tocar();
                    break;
                case 1:
                    this.reprodutor.pausar();
                    break;
                case 2:
                    musica = JOptionPane.showInputDialog("Selecione a música");
                    this.reprodutor.selecionarMusica(musica);
                    break;
            }
        }
    }

    public void menuTelefone()
    {
        String[] opcoes = {"Ligar", "Atender", "Correio de Voz", "Sair"};
        String numero;
        int opcao = 0;

        while (opcao != 3) {
            opcao = JOptionPane.showOptionDialog(null, "Selecione a ação desejada",
                 "Telefone", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                 null, opcoes, 3);
            switch(opcao) {
                case 0:
                    numero = JOptionPane.showInputDialog("Digite o número para o qual deseja ligar");
                    this.telefone.ligar(numero);
                    break;
                case 1:
                    this.telefone.atender();
                    break;
                case 2:
                    this.telefone.iniciarCorreioDeVoz();
                    break;
            }
        }
    }

    public void menuNavegador()
    {
        String[] opcoes = {"Exibir Pagina", "Adicionar Aba", "Atualizar Pagina", "Fechar Aba",
            "Selecionar Aba", "Mostrar Abas", "Sair"};
        String url;
        int aba;
        int opcao = 0;

        while (opcao != 6) {
            opcao = JOptionPane.showOptionDialog(null, "Selecione a ação desejada",
                 "Navegador", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                 null, opcoes, 6);
            switch(opcao) {
                case 0:
                    url = JOptionPane.showInputDialog("Digite o endereço da pagina");
                    this.navegador.exibirPagina(url);
                    break;
                case 1:
                    this.navegador.adicionarNovaAba();
                    break;
                case 2:
                    this.navegador.atualizarPagina();
                    break;
                case 3:
                    this.navegador.fecharAba();
                    break;
                case 4:
                    aba = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da aba"));    
                    this.navegador.selecionarAba(aba);
                    break;
                case 5:
                    this.navegador.mostrarAbas();
                    break;
            }
        }
    }
}
