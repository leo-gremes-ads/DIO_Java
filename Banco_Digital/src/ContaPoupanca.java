public class ContaPoupanca extends ContaBancaria
{
    private float taxaRendimento;

    public ContaPoupanca(String agencia, String numero, Cliente titular)
    {
        super(agencia, numero, titular, 0f);
        this.taxaRendimento = 0.05f;
    }

    public ContaPoupanca(String agencia, String numero,
                         Cliente titular, float saldo)
    {
        super(agencia, numero, titular, saldo);
        this.taxaRendimento = 0.05f;
    }

    @Override
    public void depositar(float valor)
    {
        super.saldo += valor;
        super.saldo *= (1 + this.taxaRendimento);
    }
}