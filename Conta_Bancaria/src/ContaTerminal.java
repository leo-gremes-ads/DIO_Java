public class ContaTerminal
{
    private int numero;
    private String agencia;
    private String nomeCliente;
    private float saldo;

    public ContaTerminal(int numero, String agencia, String nomeCliente, float saldo)
    {
        this.numero = numero;
        this.agencia = agencia;
        this.nomeCliente = nomeCliente;
        this.saldo = saldo;
    }

    public int getNumero() { return this.numero; }
    public String getAgencia() { return this.agencia; }
    public String getNomeCliente() { return this.nomeCliente; }
    public float getSaldo() { return this.saldo; }
}