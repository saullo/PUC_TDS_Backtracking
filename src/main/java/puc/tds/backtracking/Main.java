package puc.tds.backtracking;

import java.util.Scanner;

public class Main {
    private final int[][] board;

    public Main(int width) {
        this.board = new int[width][width];
    }

    /**
     * Verifica se já existe uma rainha na linha, coluna ou diagonal
     *
     * @param row Linha para verificar
     * @param column Coluna para verificar
     * @return True caso não exista, False já exisitir uma rainha
     */
    private boolean validate(int row, int column) {
        // Verifica todas as colunas 0..n, horizontalmente
        for (int i = 0; i < column; i++) {
            if (this.board[row][i] == 1)
                return false;
        }

        // Verifica as colunas e linhas de row..0 column...0, verticalmente
        for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {
            if (this.board[i][j] == 1)
                return false;
        }

        // Verifica as colunas e linhas de row..board_limit column...0, diagonalmente
        for (int i = row, j = column; i < this.board.length && j >= 0 ; i++, j--) {
            if (this.board[i][j] == 1)
                return false;
        }

        return true;
    }

    /**
     * Testa recursivamente todas as possibilidades do tabuleiro, começando pela coluna 0
     *
     * @param column Número da coluna atual
     * @return True quando achar todas as possibilidades, False caso não encontrar uma solução
     */
    public boolean check(int column) {
        // Verifica se chegamos ao limite do tabuleiro
        if (column == this.board.length)
            return true;

        // Verifica linha por linha se a posição da rainha é valida
        for (int i = 0; i < this.board.length; i++) {

            // Caso a posição estiver valida, marcamos no tabuleiro e passamos para proxima coluna
            if (validate(i, column)) {
                // Marca a posição no tabuleiro
                this.board[i][column] = 1;

                if (check(column + 1))
                    return true;

                // Backtracking, desmarca um local do tabuleiro
                this.board[i][column] = 0;
            }
        }

        return false;
    }

    @Override
    public String toString(){
        var stringBuilder = new StringBuilder();
        for (var piece : this.board) {
            for (int j = 0; j < this.board.length; j++) {
                var format = String.format(" %d", piece[j]);
                stringBuilder.append(format);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args){
        var scanner = new Scanner(System.in);

        System.out.print("Tamanho do tabuleiro: ");
        var width = scanner.nextInt();

        var main = new Main(width);
        if(!main.check(0)) {
            System.out.println("Solução não encontrada!");
        }
        System.out.print(main);
    }
}