/**
 * Pilha com lista encadeada — LIFO.
 * Último que entra é o primeiro que sai.
 * Implementa EstruturaLinear pra poder ser usada no FloodFill
 * junto com a Fila sem precisar de métodos separados.
 */
public class Pilha<T> implements EstruturaLinear<T> {

    private No<T> topo; // quem está no topo, entra e sai por aqui

    @Override
    public void inserir(T dado) {
        No<T> novo = new No<>(dado); // novo nó
        novo.proximo = topo; // o novo aponta para quem era o antigo topo
        // o novo entra tipo "por cima" empurrando o topo anterior para baixo na hierarquia
        topo = novo; // o novo vira o ponto de entrada oficial
    }

    @Override
    public T remover() {
        T dado = topo.dado; // salva o valor de quem está saindo
        topo = topo.proximo; // o novo topo é quem estava abaixo
        return dado; // devolve o valor que foi removido
    }

    // ver se a fila está vazia
    @Override
    public boolean estaVazia() {
        return topo == null;
    }
}
