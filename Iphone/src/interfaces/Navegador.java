package interfaces;

public interface Navegador
{
    public void exibirPagina(String url);
    public void adicionarNovaAba();
    public void atualizarPagina();
    public void fecharAba();    
    public void selecionarAba(int num);
    public void mostrarAbas();
}