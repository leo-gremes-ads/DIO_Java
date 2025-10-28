import javax.swing.JOptionPane;

public abstract class ContaBancaria 
{
    private String agencia;
    private String numero;
    private Cliente titular;
    protected float saldo;

    public ContaBancaria(String agencia, String numero,
                         Cliente titular)
    {
        this(agencia, numero, titular, 0f);
    }

    public ContaBancaria(String agencia, String numero,
                         Cliente titular, float saldo)
    {
        this.agencia = agencia;
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    public void sacar(float valor)
    {
        if (valor > this.saldo) {
            JOptionPane.showMessageDialog(null, "Não há saldo suficiente para retirada");
            return;
        }
        this.saldo -= valor;
    }

    public void depositar(float valor)
    {
        this.saldo += valor;
    }

    public void transferir(float valor, ContaBancaria destino)
    {
        if (valor > this.saldo) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente");
            return;
        }
        this.sacar(valor);
        destino.depositar(valor);
    }

    public void mostrarSaldo()
    {
        JOptionPane.showMessageDialog(null, String.format("Ag: %s, Conta: %s, Saldo: R$ %.2f\n",
            this.agencia, this.numero, this.saldo));
    }

    public String getAgencia() { return this.agencia; }
    public String getNumero() { return this.numero; }
    public Cliente getTitular() { return this.titular; }
    public float getSaldo() { return this.saldo; }

    @Override
    public String toString()
    {
        return String.format("{%s, %s: %s (R$ %.2f)}",
            this.agencia, this.numero, this.titular.getNome(), this.saldo);
    }
}
