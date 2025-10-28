public class ContaCorrente extends ContaBancaria
{
    private float chequeEspecial;

    public ContaCorrente(String agencia, String numero, Cliente titular,
                         float saldo)
    {
        super(agencia, numero, titular, saldo);
        this.chequeEspecial = 1000f;
    }

    public ContaCorrente(String agencia, String numero, Cliente titular)
    {
        super(agencia, numero, titular, 0f);
        this.chequeEspecial = 1000f;
    }

    @Override
    public void sacar(float valor)
    {
        if (valor >= super.saldo + this.chequeEspecial) {
            System.out.println("Saldo insuficiente");
            return;
        }
        super.saldo -= valor;
    }
}