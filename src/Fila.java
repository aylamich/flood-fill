/**
 * Fila com lista encadeada — FIFO.
 * Primeiro que entra é o primeiro que sai.
 * Implementa EstruturaLinear pra poder ser usada no FloodFill
 * junto com a Pilha sem precisar de métodos separados.
 */
public class Fila<T> implements EstruturaLinear<T> {

    private No<T> inicio; // o "primeiro da fila"
    private No<T> fim; // o "último da fila"

    @Override
    public void inserir(T dado) {
        No<T> novo = new No<>(dado); // novo nó
        if (estaVazia()) { // se não tem ninguém na fila
            inicio = novo; // o novo é o PRIMEIRO
            fim = novo; // e também o último já que ele entra e sai
        } else { // se já tem gente na fila
            fim.proximo = novo; // o último aponta para o próximo da fila
            fim = novo; // e esse próximo é o novo "fim"
        }
    }

    @Override
    public T remover() {
        T dado = inicio.dado; // salva o valor do primeiro
        inicio = inicio.proximo; // o segunda da fila passa a ser o primeiro
        if (inicio == null) { // se depois de tirar a lista ficou vazia
            fim = null; // então o fim é anulado
        }
        return dado; // retorna o valor de quem saiu
    }

    // ver se a fila está vazia
    @Override
    public boolean estaVazia() {
        return inicio == null;
    }
}
