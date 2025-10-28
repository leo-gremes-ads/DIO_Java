package classes;

import interfaces.ReprodutorMusical;

public class Ipod implements ReprodutorMusical
{
    private String musicaAtual;

    public Ipod()
    {
        this.musicaAtual = null;
    }

    @Override
    public void tocar()
    {
        if (this.musicaAtual == null)
            System.out.println("Não há música selecionada");
        else
            System.out.println("Tocando " + this.musicaAtual);
    }

    @Override
    public void pausar()
    {
        if (this.musicaAtual == null)
            System.out.println("Não há música selecionada");
        else
            System.out.println(this.musicaAtual + " está pausada");
    }

    @Override
    public void selecionarMusica(String musica)
    {
        this.musicaAtual = musica;
        System.out.println("Música " + musica + " selecionada");
    }
}
