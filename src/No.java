/**
 * Nó genérico, base da lista encadeada.
 * Guarda um dado e aponta pro próximo nó.
 */
public class No<T> {
    T dado; // o valor real, no caso a coordenada x, y
    No<T> proximo; // referência para o próximo objeto do mesmo tipo

    public No(T dado) {
        this.dado = dado; // nó recebe e guarda a info passada
        this.proximo = null; // o nó nasce sem apontar para ninguém
    }
}
