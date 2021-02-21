import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

///////////////////////////////////////////////////////////////////////////
//                                                                       //
//                                                                       //
//                          Romain Guarinoni                             // 
//                                                                       //
//                            17/02/2021                                 //
//                                                                       //
///////////////////////////////////////////////////////////////////////////

public class MaFenetre extends JFrame {

    private static final long serialVersionUID = 1L;
    private JButton quitter, sauvegarder, generer;
    private JLabel label1, label2, label3;
    private int rScroll = 0;
    private int gScroll = 0;
    private int bScroll = 0;
    private ImageGeneree im = new ImageGeneree();

    MaFenetre() {
        super("Mon magnifique TP");
        setSize(1050, 850);

        // création du container
        Container c = this.getContentPane();

        // création des 2 panels
        Panel p1 = new Panel();
        Panel p2 = new Panel();

        // création des 3 boutons
        generer = new JButton("générer");
        sauvegarder = new JButton("sauvegarder");
        quitter = new JButton("quitter");

        // je desactive le bouton sauvegarder tant qu'une image n'est pas générée
        sauvegarder.setEnabled(false);

        // ajout des event aux boutons
        quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        sauvegarder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // on ouvre la nouvelle boite de dialogue pour récupérer le nom de notre image à
                // enregistrer
                Input input = new Input(MaFenetre.this);

                // on attend que la méthode getName nous retourne uine valeur
                String name = input.getName();

                // on enregistre cette image avec la valeur name retourner
                im.sauvegarder(name);

            }
        });
        generer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                im.construitImage(1050, 800, rScroll, gScroll, bScroll);

                // on redessine une image au dessus de l'ancienne
                im.repaint();
                c.add(im);

                // on actrive le bouton sauvegarder
                sauvegarder.setEnabled(true);
                setVisible(true);
            }
        });

        // création des 3 scrollbar
        JScrollBar scrollR = new JScrollBar(Adjustable.HORIZONTAL);
        JScrollBar scrollG = new JScrollBar(Adjustable.HORIZONTAL);
        JScrollBar scrollB = new JScrollBar(Adjustable.HORIZONTAL);

        // Resize les 3 scroll bar
        scrollR.setPreferredSize(new Dimension(190, 15));
        scrollG.setPreferredSize(new Dimension(190, 15));
        scrollB.setPreferredSize(new Dimension(190, 15));

        // set maximum complexity

        scrollR.setMaximum(30);
        scrollG.setMaximum(30);
        scrollB.setMaximum(30);

        // ajout des events aux scrolls
        scrollR.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent ae) {
                if (scrollR.getValueIsAdjusting())
                    return;
                rScroll = (int) (ae.getValue());
            }
        });
        scrollG.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent ae) {
                if (scrollG.getValueIsAdjusting())
                    return;
                gScroll = (int) (ae.getValue());
            }
        });
        scrollB.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent ae) {
                if (scrollB.getValueIsAdjusting())
                    return;
                bScroll = (int) (ae.getValue());
            }
        });

        // création des labels des scrollbar
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label1.setText("R:");
        label2.setText("G:");
        label3.setText("B:");

        // ajout des boutons,scroll,et labels au panels respectifs
        p1.add(label1);
        p1.add(scrollR);
        p1.add(label2);
        p1.add(scrollG);
        p1.add(label3);
        p1.add(scrollB);
        p2.add(generer);
        p2.add(quitter);
        p2.add(sauvegarder);

        // ajout des panels aux container
        c.add(p1, BorderLayout.NORTH);
        c.add(p2, BorderLayout.SOUTH);
        setVisible(true);

    }

    public static void main(String[] args) {
        new MaFenetre();
    }
}
