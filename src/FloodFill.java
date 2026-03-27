import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Algoritmo Flood Fill
 * Passa uma Fila -> expande em ondas (BFS).
 * Passa uma Pilha -> vai fundo numa direção (DFS).
 *
 * Fluxo dentro do loop:
 * 1. Remove o pixel da estrutura
 * 2. Verifica se está dentro da imagem
 * 3. Verifica se a cor é a cor de fundo
 * 4. Se sim -> pinta -> insere os 4 vizinhos
 * 5. A cada X pixels -> salva frame e atualiza a janela
 */
public class FloodFill {

    // cor caramelo clarinho
    private static final int COR_NOVA = new Color(255, 180, 100).getRGB();

    // salva um frame a cada 100 pixels pintados
    private static final int INTERVALO_FRAME = 100;

    public void preencher(BufferedImage imagem, int inicioX, int inicioY,
                          String pastaFrames, EstruturaLinear<int[]> estrutura) throws IOException {

        int corDeFundo = imagem.getRGB(inicioX, inicioY);

        if (corDeFundo == COR_NOVA) return;

        // coordenada inicial
        estrutura.inserir(new int[]{inicioX, inicioY});

        int contadorFrames = 0;
        int totalPintados = 0;

        // loop que só para se não tiver mais um pixel vizinho
        while (!estrutura.estaVazia()) {

            int[] pixel = estrutura.remover(); // tira um pixel da estrutura
            int x = pixel[0];
            int y = pixel[1];

            if (!dentroImagem(imagem, x, y)) continue; // verifica limites da imagem
            if (imagem.getRGB(x, y) != corDeFundo) continue; // verifica se ainda é a cor de fundo

            imagem.setRGB(x, y, COR_NOVA); // pinta com a nova cor
            totalPintados++;

            // a cada X pixels salva frame e atualiza a janela
            if (totalPintados % INTERVALO_FRAME == 0) {
                salvarFrame(imagem, pastaFrames, contadorFrames++);
                Main.atualizar();
            }

            // insere os 4 vizinhos
            estrutura.inserir(new int[]{x + 1, y}); // direita
            estrutura.inserir(new int[]{x - 1, y}); // esquerda
            estrutura.inserir(new int[]{x, y + 1}); // baixo
            estrutura.inserir(new int[]{x, y - 1}); // cima
        }

        salvarFrame(imagem, pastaFrames, contadorFrames);
        Main.atualizar();

        System.out.println(totalPintados + " pixels pintados. "
                + (contadorFrames + 1) + " frames salvos.");
    }

    private boolean dentroImagem(BufferedImage imagem, int x, int y) {
        return x >= 0 && y >= 0 && x < imagem.getWidth() && y < imagem.getHeight();
    }

    // para a animação
    private void salvarFrame(BufferedImage imagem, String pasta, int numero) throws IOException {
        File dir = new File(pasta);
        if (!dir.exists()) dir.mkdirs();
        ImageIO.write(imagem, "png",
                new File(pasta + "/frame_" + String.format("%05d", numero) + ".png"));
    }
}
