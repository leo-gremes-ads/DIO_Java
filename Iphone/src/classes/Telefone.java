package classes;

public class Telefone
{
    public Telefone()
    {
        super();
    }

    public void ligar(String numero)
    {
        System.out.println("Ligando para " + numero);
    }

    public void atender()
    {
        System.out.println("Atendendo uma ligação");
    }

    public void iniciarCorreioDeVoz()
    {
        System.out.println("Iniciando correio de voz");
    }
}
