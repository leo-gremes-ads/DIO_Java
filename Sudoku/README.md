# Jogo de Sudoku

Escreva um código que irá criar um jogo de sudoku

https://pt.wikipedia.org/wiki/Sudoku

  ## Requisitos
    Deve-se ter um menu interativo onde poderemos escolher entre as seguintes opções:

        1. Iniciar um novo o jogo: Deve-se exibir na tela o jogo inicial, com os espaços preenchidos somente pelos números iniciais (usar os args do método main para informar os números iniciais e suas devidas posições);

        2. Colocar um novo número: Deve-se solicitar as seguintes informações do jogador (número a ser colocado, indice horizontal e indice vertical do número), não se deve permitir que seja colocado um número em uma posição que já esteja preenchida ( seja número fixo ou informado pelo jogador);

        3. Remover um número: deve-se solicitar os índices verticais e horizontais do número que deseja remover ( caso o número seja um número fixo do jogo deve-se exibir uma mensagem informado que o número não pode ser removido);

        4. Verificar jogo: Vizualizar a situação atual do jogo;

        5. Verificar status do jogo: Deve-se verificar o status atual do jogo ( não iniciado, incompleto e completo) e se contém ou não erros ( o jogo está errado quando tem números em posições conflitantes) todos os status do jogo podem conter ou não erros, exceto o status não iniciado que é sempre sem erro;
        6. Limpar: remove todos os números informados pelo usuário e mantém os fixos do jogo;

        7. finalizar o jogo: Se o jogo estiver com todos os espaços preenchidos de forma válida o jogo é encerrado, senão informar ao usuário que ele deve preencher todos os espaços com seus respectivos números;

  ## Extras (requisitos opcionais)
    1. Usar algum ambiente gráfico ( AWT, Swing) para criação do jogo
    2. ter a opção de colocar números de rascunho nos quadrados, para isso deve-se seguir o modelo proposto na sessão modelo de rascunho:

---
# Solução

Fiz a implementação do projeto com algumas diferenças em relação ao enunciado e ao projeto desenvolvido nos vídeos, são elas:
- Utilizei o *args* para preencher o mapa, porém, deve-se passar o nome de um arquivo de texto presente no diretório ```mapas``` , onde o caractere ```.``` representa uma célula inicial vazia e os números representam uma célula preenchida.
    - fiz a validação da grade no que diz respeito as dimensões (deve ser 9 x 9), e ao estado inicial (não pode conter erros, nem estar totalmente preenchido, nem conter números fora do intervalo).
- A verificação de erros ocorre apenas pela identtificação de números duplicados em uma linha, coluna ou quadrado de 3x3, ao invés da comparação do número preenchido com o número esperado.
- Não há uma opção para mostrar o estado do jogo (não iniciado, incompleto, completo), uma vez que o jogo inicia e termina junto com o programa, porém, há a verificação de erros no preenchimento.
- Não implementei a opção de finalizar o jogo, visto que, quando todas as células são preenchidas, o programa encerra com aviso de "parabéns" ou mostra um aviso de erros.
- Uma vez selecionadas as opções de preencher ou apagar um número, o programa permite várias operações sem voltar para o menu, exigindo que o usuário digite ```0``` parar regressar.
- Não desenvolvi os requisitos adicionais de tela pois não é o meu foco no momento.

Eu gostei bastante de desenvolver esse projeto, pude exercitar meu conhecimento em Java e praticar um pouco do que estive aprendendo até então: *collections*, *streams*, *enums*, *exceptions*, etc.