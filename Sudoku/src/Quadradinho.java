public class Quadradinho
{
    private Integer numero;
    private Estado estado;

    //Construtores
    public Quadradinho(int num) {
        this.numero = num;
        this.estado = Estado.FIXO;
    }

    public Quadradinho() {
        this.numero = null;
        this.estado = Estado.LIVRE;
    }

    public static Quadradinho criar(int n)
    {
        if ((char)n == '.')
            return new Quadradinho();
        else
            return new Quadradinho(n - '0');
    }

    //Metodos
    public boolean estaDisponivel()
    {
        return this.estado == Estado.LIVRE;
    }

    public void preencher(int num)
    {
        this.numero = num;
        this.estado = Estado.PREENCHIDO;
    }

    public void apagar()
    {
        this.numero = null;
        this.estado = Estado.LIVRE;
    }

    //Getters
    public Integer getNumero() { return this.numero; }
    public Estado getEstado() { return this.estado; }

    //Overrides
    @Override
    public String toString()
    {
        String valor = (this.numero == null) ? "." : String.valueOf(this.numero);
        String cor = (this.estado == Estado.FIXO) ? "\033[34;1m" : "\033[0m";
        return String.format("%s%s\033[0m", cor, valor);
    }
}
