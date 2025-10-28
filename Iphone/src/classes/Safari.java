package classes;

import java.util.ArrayList;
import java.util.List;
import interfaces.Navegador;

public class Safari implements Navegador
{
    private String homePage;
    private int abaAtual;
    private List<String> abas;

    public Safari()
    {
        this.homePage = "www.dio.me";
        this.abas = new ArrayList<>();
        this.abaAtual = 0;
        this.abas.add(String.valueOf(this.homePage));
    }
    
    @Override
    public void exibirPagina(String url)
    {
        if (this.abas.isEmpty()) {
            adicionarNovaAba();
            abaAtual = 0;
        }
        abas.set(abaAtual, url);
        System.out.println("Exibindo a página" + url + " na aba " + this.abaAtual);   
    }

    @Override
    public void adicionarNovaAba()
    {
        abas.add(String.valueOf(this.homePage));
        this.abaAtual = abas.size() - 1;
        System.out.println("Aba " + this.abaAtual + " adicionada");
    }

    @Override
    public void atualizarPagina()
    {
        if (this.abas.isEmpty()) {
            System.out.println("Não tem abas abertas no navegador");
            return;
        }
        System.out.println("A pagina " + this.abas.get(this.abaAtual) + " foi atualizada");
    }

    @Override
    public void fecharAba()
    {
        if (this.abas.isEmpty())
            return;
        this.abas.remove(this.abaAtual);
        if (this.abaAtual >= this.abas.size())
            this.abaAtual--;
    }

    @Override
    public void selecionarAba(int num)
    {
        if (this.abas.isEmpty()) {
            System.out.println("Não há abas abertas");
            this.abaAtual = -1;
        }
        else if (num < 0 || num >= this.abas.size()) {
            System.out.println("Não há abas nesse índice");
            this.abaAtual = -1;
        }
        this.abaAtual = num;
    }

    @Override
    public void mostrarAbas()
    {
        int qtd = this.abas.size();

        for (int i = 0; i < qtd; i++)
            System.out.println(i + ": " + this.abas.get(i));
    }
}
