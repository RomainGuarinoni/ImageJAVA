import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Input extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField input;
    private JLabel text;
    private JButton close, submit;
    private Panel top, bottom;
    private String name = "";
    public Boolean closeBool = false;

    Input(JFrame parent) {
        super(parent, "Nom de l'image", true);
        setSize(500, 180);

        Container c = this.getContentPane();
        top = new Panel();
        bottom = new Panel();

        input = new JTextField(16);

        // création du label
        text = new JLabel();
        text.setText("donnez un nom à votre image : ");

        // création du bouton pour fermer la fenêtre
        close = new JButton("fermer");
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        // création du bouton pour enresgiterr l'image sous le nom rentrer en input
        submit = new JButton("enregistrer");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // on récupère la valeur de l'input
                name = input.getText();
                dispose();
            }
        });
        top.add(text);
        top.add(input);

        bottom.add(close);
        bottom.add(submit);

        c.add(top, BorderLayout.NORTH);
        c.add(bottom, BorderLayout.SOUTH);
        setVisible(true);

    }

    // accesseur qui retourne la valeur de l'input
    public String getName() {

        return this.name;
    }
}