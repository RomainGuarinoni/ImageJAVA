import java.util.Random;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageGeneree extends JComponent {
    private static final long serialVersionUID = 1L;
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

    // fonction qui retourne le niveau de rouge, de vert ou de bleu d'un pixel en
    // fonction de sa position et d'une expression mathématique
    int createRGBlevel(int width, int height, int i, int j, Expr expr) {

        // on réduit x et y a l'intervalle [-1,1]
        double ratioX = i / (float) width;
        double ratioY = j / (float) height;

        // on évalue la fonction avec ratioX et ratioY
        double Aux = expr.eval(ratioX, ratioY);

        // Calcul des niveaux de rgb
        int level = (int) (((float) 255 / 2) + (((float) 255 / 2) * Aux));

        return level;
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
                buff.setRGB(i, j,
                        (new Color(createRGBlevel(width, height, i, j, this.exp_r),
                                createRGBlevel(width, height, i, j, this.exp_g),
                                createRGBlevel(width, height, i, j, this.exp_b))).getRGB());
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
