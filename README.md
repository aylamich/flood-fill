# Flood Fill — PBL01

Trabalho da disciplina de Resolução de Problemas Estruturados em Computação.
O programa aplica o algoritmo Flood Fill em uma imagem PNG, preenchendo uma região
com cor nova a partir de um ponto inicial. Roda duas vezes: uma com Fila e outra com Pilha,
mostrando a diferença no comportamento de cada estrutura.

---

## Estrutura do projeto

```
FloodFill_projeto/
├── src/
│   ├── No.java               — nó da lista encadeada
│   ├── EstruturaLinear.java  — interface com as regras que Fila e Pilha seguem
│   ├── Fila.java             — estrutura FIFO
│   ├── Pilha.java            — estrutura LIFO
│   ├── FloodFill.java        — algoritmo principal
│   └── Main.java             — ponto de entrada e animação
├── gatinho_entrada.png       — imagem de entrada
└── .gitignore
```

---

## Como rodar

1. Clone o repositório
2. Abra a pasta como projeto Java na sua IDE
3. Marque a pasta `src` como Sources Root
4. Rode o `Main.java`

O programa vai gerar as pastas `frames/fila` e `frames/pilha` com os frames da animação,
e salvar `saida_fila.png` e `saida_pilha.png` com o resultado final.

Para mudar o ponto de partida do preenchimento, altere `INICIO_X` e `INICIO_Y` no `Main.java`.

---


## Como a Fila e a Pilha funcionam

As duas são listas encadeadas, a diferença é só na ordem de saída dos elementos.

A Fila segue o modelo FIFO: o primeiro que entrou é o primeiro que sai, igual uma fila de banco.
No Flood Fill isso faz o preenchimento se expandir em camadas a partir do ponto inicial,
como uma onda.

A Pilha segue o modelo LIFO: o último que entrou é o primeiro que sai, igual uma pilha de pratos.
No Flood Fill isso faz o algoritmo ir fundo em uma direção antes de explorar as outras.

O resultado final é o mesmo, mas a animação mostra claramente a diferença no comportamento.

---

## Polimorfismo

O `FloodFill.java` tem um único método `preencher` que aceita qualquer `EstruturaLinear`.
Tanto `Fila` quanto `Pilha` implementam essa interface, então o algoritmo não precisa
saber qual das duas está usando: só chama `inserir` e `remover` e cada estrutura faz do seu jeito.
