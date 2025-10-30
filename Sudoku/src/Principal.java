public class Principal
{
    public static void main(String[] args) throws Exception
    {
        Jogo sudoku = new Jogo(args[0]);
        sudoku.validarLinhas();
        sudoku.verificarLinhas();
        sudoku.validarColunas();
        sudoku.validarQuadrados();
        sudoku.verificarQuadrados();
    }
}
