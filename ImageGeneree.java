import java.util.Random;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageGeneree extends JComponent {
    Random graine = new Random();
    Expr exp_r;
    Expr exp_g;
    Expr exp_b;
    RenderedImage im;
    int imageIndex = 0;

    Expr random_expr(int level) {
        if (level == 0) {
            if (graine.nextBoolean()) {
                return (new X());
            } else {
                return (new Y());
            }
        } else {
            Expr e;
            switch (graine.nextInt(4)) {
                case 0:
                    e = new Sin(random_expr(level - 1));
                    break;
                case 1:
                    e = new Cos(random_expr(level - 1));
                    break;
                case 2:
                    e = new Moyenne(random_expr(level - 1), random_expr(level - 1));
                    break;
                case 3:
                    e = new Mult(random_expr(level - 1), random_expr(level - 1));
                    break;
                default:
                    e = new Expr();
                    break;
            }
            return e;
        }
    }

    void construitImage(int width, int height, int RLevel, int GLevel, int BLevel) {
        BufferedImage buff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // on définit les fonctions pour le rouge,vert et bleu en fonction de la valeur
        // des scrollbar correspondant
        this.exp_r = random_expr(RLevel);
        this.exp_g = random_expr(GLevel);
        this.exp_b = random_expr(BLevel);

        // on parcours chaque pixel de l'image
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {

                // on réduit x et y a l'intervalle [-1,1]
                double ratioX = i / (float) width;
                double ratioY = j / (float) height;

                // on évalue les fonctions avec ratioX et ratioY
                double rAux = this.exp_r.eval(ratioX, ratioY);
                double gAux = this.exp_g.eval(ratioX, ratioY);
                double bAux = this.exp_b.eval(ratioX, ratioY);

                // Calcul des niveaux de rgb
                int r = (int) (((float) 255 / 2) + (((float) 255 / 2) * rAux));
                int g = (int) (((float) 255 / 2) + (((float) 255 / 2) * gAux));
                int b = (int) (((float) 255 / 2) + (((float) 255 / 2) * bAux));

                buff.setRGB(i, j, (new Color(r, g, b)).getRGB());
            }
        ;
        im = buff;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawRenderedImage(im, null);

    }

    public void sauvegarder() {
        imageIndex++;
        try {

            // on récupère la date d'aujourd,hui pour faire une photo unique
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy-HH-mm-ss");
            Date date = new Date();
            File f = new File("fondEcran" + dateFormat.format(date) + ".jpg");
            ImageIO.write(im, "jpg", f);
        } catch (IOException e) {
        }
    }
}
