public class Cliente
{
    private String nome;
    private String documento;

    public Cliente(String nome, String documento)
    {
        this.nome = nome;
        this.documento = documento;
    }

    @Override
    public String toString()
    {
        return "[" + this.nome + " [" + this.documento + "]]";
    }

    public String getNome() { return this.nome; }
    public String getDocumento() { return this.documento; }
}
