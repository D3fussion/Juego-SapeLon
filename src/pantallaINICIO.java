import javax.swing.*;
import java.awt.*;

public class pantallaINICIO extends JPanel {
    static Dib d = new Dib();
    public void paint(Graphics g){
        super.paint(g);
        new pantallaINICIO();
        g.drawImage(d.SAPELON,0,0,500,500,this);
    }

    void pantalla() throws InterruptedException {
        JFrame p = new JFrame();
        p.setSize(516, 539);
        p.setVisible(true);
        p.setResizable(false);
        p.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        p.setLocationRelativeTo(null);
        pantallaINICIO PI = new pantallaINICIO();
        p.add(PI);
        p.repaint();
        Thread.sleep(3000);
        p.setVisible(false);
        JOptionPane.showMessageDialog(null, "El juego se trata de darle un sape a un pelon que se\nencuentra en el mapa, a traves de ir rebotando por las\nparedes de las casas y el area, una ves que te acerques a\nel, tendras que introducir una ecuacion de una recta que\npase por tu mano y el pelon");
    }
}
