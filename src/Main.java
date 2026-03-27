import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Entrada do programa.
 * Carrega a imagem, roda o Flood Fill com Fila e com Pilha,
 * exibindo a animação em tempo real em cada execução.
 */
public class Main {

    // ponto inicial, coordenadas pegas com o Paint para começar
    // a partir da barriguinha do gatinho
    private static final int INICIO_X = 150;
    private static final int INICIO_Y = 150;

    // imagem exibida na janela, atualizada a cada frame
    private static BufferedImage imagemNaTela;
    private static JPanel painel;

    public static void main(String[] args) throws IOException {

        // limpa os frames antigos antes de começar
        limparPasta("frames/fila");
        limparPasta("frames/pilha");

        // imagem carregada + duas cópias para não afetar o preenchimento
        BufferedImage original    = ImageIO.read(new File("gatinho_entrada.png"));
        BufferedImage imagemFila  = copiar(original);
        BufferedImage imagemPilha = copiar(original);

        FloodFill ff = new FloodFill();

        // ***Roda com FILA***
        System.out.println("=== Rodando com FILA ===");
        imagemNaTela = imagemFila;
        abrirJanela("Flood Fill: Fila");
        ff.preencher(imagemFila, INICIO_X, INICIO_Y, "frames/fila", new Fila<>()); // passando uma fila, usa o método da interface EstruturaLinear
        ImageIO.write(imagemFila, "png", new File("saida_fila.png"));

        // ***Roda com PILHA***
        System.out.println("=== Rodando com PILHA ===");
        imagemNaTela = imagemPilha;
        abrirJanela("Flood Fill: Pilha ");
        ff.preencher(imagemPilha, INICIO_X, INICIO_Y, "frames/pilha", new Pilha<>()); // passando uma pilha , usa o método da interface EstruturaLinear
        ImageIO.write(imagemPilha, "png", new File("saida_pilha.png"));

        System.out.println("Pronto!");
    }

    // abre a janela com o painel que usa Graphics2D pra desenhar
    private static void abrirJanela(String titulo) {
        painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(imagemNaTela, 0, 0, getWidth(), getHeight(), null);
                g2d.dispose();
            }
        };
        painel.setPreferredSize(new Dimension(600, 600));

        JFrame janela = new JFrame(titulo);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.add(painel);
        janela.pack();
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }

// -----------------------------------------------------------------------------------------------------
// Animação
    /**
     * chama isso toda vez que a imagem mudar e redesenha o painel.
     * repaint() é tipo um F5, a imagem mudou então deve ser desenhada de volta.
     */
    public static void atualizar() {
        painel.repaint();
    }

    // copia pixel por pixel e assim garante que o original nunca é modificado
    private static BufferedImage copiar(BufferedImage original) {
        BufferedImage copia = new BufferedImage(
                original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                copia.setRGB(x, y, original.getRGB(x, y));
            }
        }
        return copia;
    }

    // apaga todos os arquivos da pasta ou cria ela se não existir, apenas uma limpeza básica
    private static void limparPasta(String caminho) {
        File pasta = new File(caminho);
        if (pasta.exists()) {
            for (File f : pasta.listFiles()) f.delete();
        } else {
            pasta.mkdirs();
        }
    }
}
