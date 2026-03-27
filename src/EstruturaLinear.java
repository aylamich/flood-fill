/**
 * DICA DA PROF:
 *  Aqui a gente combina as regras que tanto a Fila quanto a Pilha
 *  precisam seguir: inserir, remover e verificar se está vazia. *só a ordem muda entre elas*
 *  Assim o FloodFill usa as duas do mesmo jeito, sem precisar repetir código no FloodFill.java
 *
 * POLIMORFISMO:
 * o FloodFill não precisa saber se está usando Fila ou Pilha,
 * só precisa chamar inserir() e remover(), cada uma faz do seu jeito
 */
//  Interface que define o contrato de uma estrutura linear
public interface EstruturaLinear<T> {
    void inserir(T dado);
    T remover();
    boolean estaVazia();
}
