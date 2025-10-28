import java.util.ArrayList;
import java.util.List;

public class ClienteController
{
    private List<Cliente> clientes;

    public ClienteController()
    {
        this.clientes = new ArrayList<>();
    }

    public void adicionarCliente(String nome, String documento)
    {
        this.clientes.add(new Cliente(nome, documento));
    }

    public void adicionarCliente(Cliente cliente)
    {
        this.clientes.add(cliente);
    }

    public Cliente pesquisarPorNome(String nome)
    {
        return clientes.stream()
            .filter(c -> c.getNome().equals(nome))
            .findAny().orElse(null);
    }

    public List<Cliente> pesquisarTodos()
    {
        return this.clientes;
    }
}
