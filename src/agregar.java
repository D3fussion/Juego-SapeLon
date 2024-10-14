import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;
import java.text.DecimalFormat;

public class agregar extends JPanel {
    public static JFrame hoja;
    public static Font font1 = new Font("Arial", 0, 40);
    static Timer tempo;
    static int segundos;
    static int minutos;
    public static int C = 24;
    public static int PosXPelon;
    public static int PosYPelon;
    public static double TC = 20;
    public static JLabel jHoja;
    static String ddSecond;
    static String ddMinute;
    static DecimalFormat dFormat = new DecimalFormat("00");
    static double rectaX;
    static double rectaY;
    static double x1;
    static double y1;
    static int primera = 0;
    static JFrame reloj;
    static int score = 0;
    static File f1;
    static String direccion = "C:/archivos/highscore";
    static String nombreScore = "";
    static int scoreAlto = 0;
    static String nombreScoreAlto = "-----";
    static int a = 5;
    static Dib d = new Dib();
    static int intentos=0;

    public void paint(Graphics g) {
        super.paint(g);
        new agregar();
        g.setColor(Color.lightGray);
        for(int i = 0; i <= C * TC + TC; i += 20) {
            g.drawLine(0, i, 500, i);
            g.drawLine(i, 0, i, 500);
        }
        g.setColor(Color.black);
        g.drawLine(240,0,240,240);
        g.drawLine(0,240,240,240);
        g.drawLine(240,480,240,240);
        g.drawLine(480,240,240,240);

        g.setColor(Color.red);
        //g.drawArc(230, 230, 20, 20, 0, 360);
        g.drawImage(d.ManoDVD,220,220,40,40,this);
        g.setColor(Color.blue);
        if(PosXPelon >= 240 && PosYPelon <240) {
            //g.drawArc(PosXPelon+10, PosYPelon-10, 20, 20, 0, 360);
            g.drawImage(d.Pelon,PosXPelon+10,PosYPelon-10,20,20,this);
        } else if (PosXPelon < 240 && PosYPelon < 240) {
            //g.drawArc(PosXPelon-10, PosYPelon-10, 20, 20, 0, 360);
            g.drawImage(d.Pelon,PosXPelon-10,PosYPelon-10,20,20,this);
        } else if (PosXPelon < 240 && PosYPelon >= 240) {
            //g.drawArc(PosXPelon-10, PosYPelon+10, 20, 20, 0, 360);
            g.drawImage(d.Pelon,PosXPelon-10,PosYPelon+10,20,20,this);
        } else if (PosXPelon >= 240 && PosYPelon >= 240){
            //g.drawArc(PosXPelon+10, PosYPelon+10, 20, 20, 0, 360);
            g.drawImage(d.Pelon,PosXPelon+10,PosYPelon+10,20,20,this);
        }
    }

    public static void inicio() throws IOException {
        if (primera == 0) {
            hoja = new JFrame();
            reloj = new JFrame();
            hoja.setTitle("Un juego");
            reloj.setTitle("Un reloj");
            hoja.setSize(496, 520);
            reloj.setSize(200, 200);
            hoja.setLocationRelativeTo((Component) null);
            reloj.setLocation(1200, 200);
            agregar objeto = new agregar();
            hoja.add(objeto);
            hoja.setDefaultCloseOperation(3);
            hoja.setResizable(false);
            reloj.setResizable(false);
            jHoja = new JLabel("Reloj");
            jHoja.setHorizontalAlignment(0);
            jHoja.setFont(font1);
            reloj.add(jHoja);
            reloj.setVisible(true);
            primera++;
        }
        hoja.setVisible(true);
        reloj.setVisible(true);
        JOptionPane.showMessageDialog((Component) null, "Pon la ecuacion de una recta que pase por el punto (0,0) y la posicion del pelon\nEn este formato:\n\n                                y = 3/2x\n");
        jHoja.setText("00:25");
        segundos = 25;
        minutos = 0;
        TheFinalCountDown();
        tempo.start();
        pelonXY();
        hoja.repaint();
        String x = "";
        String y = "";
        System.out.println("Soy posX " + (PosXPelon / TC));
        System.out.println("Soy posY " + (PosYPelon / TC - 23) * -1);
        String z = (JOptionPane.showInputDialog("Ingresa la ecuacion y ="));
        int aux = 0;
        int i = 0;
        try {
            while (true) {
                if (aux > 0) {
                    if (z.charAt(i) != 'x') {
                        x = x + z.charAt(i);
                    } else {
                        break;
                    }
                } else {
                    if (z.charAt(i) != '/') {
                        y = y + z.charAt(i);
                    } else {
                        aux = i;
                    }
                }
                i++;
            }
            x1 = Double.parseDouble(x);
            System.out.println("Soy x! " + x1);
            y1 = Double.parseDouble(y);
            System.out.println("Soy y! " + y1);
        } catch (Exception e) {
            tempo.stop();
            JOptionPane.showMessageDialog((Component) null, "El juego termino!\nTu puntuacion fue de "+score);
            RecuperarHighscore();
            if (score > scoreAlto) {
                NuevoHighscore();
            } else { JOptionPane.showMessageDialog(null,"No superaste el highscore de "+nombreScoreAlto+" con puntaje de "+scoreAlto); }
            hoja.dispatchEvent(new WindowEvent(hoja, 201));
            return;
        }
        double error = 0;
        double soyY = ((((PosYPelon / TC - 23) * -1) + error)-12);
        double soyX = (((PosXPelon / TC) + error)-12);
        if(soyX >= 0) soyX++;
        if(soyY >= 0) soyY++;
        hoja.repaint();
        System.out.println("Soy nuevo x! " + x1);
        System.out.println("Soy nuevo y! " + y1);
        System.out.println("Soy el valor Y "+((((PosYPelon / TC - 23) * -1) + error)-12));
        System.out.println("Soy el valor X "+(((PosXPelon / TC) + error)-12));
        System.out.println("Soy el valor Y N"+soyY);
        System.out.println("Soy el valor X N"+soyX);
        System.out.println("Soy el valor fraccion de pos! "+((((PosYPelon / TC - 23) * -1) + error)-12)/(((PosXPelon / TC) + error)-12));
        System.out.println("Soy el valor fraccion de pos! N"+(soyY/soyX));
        if ((y1/x1) != (soyY/soyX)) {
            tempo.stop();
            JOptionPane.showMessageDialog((Component) null, "El juego termino!\nTu puntuacion fue de "+score);
            RecuperarHighscore();
            if(score > scoreAlto){
                NuevoHighscore();
            } else { JOptionPane.showMessageDialog(null,"No superaste el highscore de "+nombreScoreAlto+" con puntaje de "+scoreAlto); }
            hoja.dispatchEvent(new WindowEvent(hoja, 201));
            return;
        }
        tempo.stop();
        JOptionPane.showMessageDialog((Component) null, "Correcto!");
        score++;
        a = 0;
        hoja.setVisible(false);
        reloj.setVisible(false);
    }

    public static void pelonXY() {
        boolean ok = true;
        while(ok) {
            PosYPelon = (int)(Math.random() * (double)C) * 20;
            if (PosYPelon == 0 || PosYPelon == 1 || PosYPelon == 23) {
                System.out.println("Soy un cero " + PosYPelon);
                ok = true;
            } else {
                System.out.println(PosYPelon);
                ok = false;
            }
        }
        ok = true;
        while(ok) {
            PosXPelon = (int)(Math.random() * (double)C) * 20;
            System.out.println(PosXPelon);
            if (PosXPelon == 0 || PosXPelon == 1 || PosXPelon == 23) {
                System.out.println("Soy un cero " + PosXPelon);
                ok = true;
            } else {
                System.out.println(PosXPelon);
                ok = false;
            }
        }
    }

    public static void NuevoHighscore() throws IOException {
        nombreScore = JOptionPane.showInputDialog("Superaste el highscore de "+nombreScoreAlto+" con puntaje de "+scoreAlto+
                "\nCual es tu nombre para agregarlo al highscore?");
        f1 = new File(direccion);
        if (!f1.exists()) f1.mkdirs();
        f1 = new File(direccion + "scores.isi");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f1));
        bw.write(""+score);
        bw.newLine();
        bw.write(nombreScore);
        bw.flush();
        bw.close();
    }

    public static void RecuperarHighscore() throws IOException {
        try {
            f1 = new File(direccion);
            BufferedReader br = new BufferedReader(new FileReader(direccion + "scores.isi"));
            try {
                scoreAlto = Integer.parseInt(br.readLine());
                nombreScoreAlto = br.readLine();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ahora mismo no hay un highscore");
            }
            br.close();
        } catch (Exception e) {JOptionPane.showMessageDialog(null, "Ahora mismo no hay un highscore");}
    }

    public static void TheFinalCountDown() {
        tempo = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                --agregar.segundos;
                agregar.ddSecond = agregar.dFormat.format((long)agregar.segundos);
                agregar.ddMinute = agregar.dFormat.format((long)agregar.minutos);
                agregar.jHoja.setText(agregar.ddMinute + ":" + agregar.ddSecond);
                if (agregar.segundos == -1) {
                    agregar.segundos = 59;
                    --agregar.minutos;
                    agregar.ddSecond = agregar.dFormat.format((long)agregar.segundos);
                    agregar.ddMinute = agregar.dFormat.format((long)agregar.minutos);
                    agregar.jHoja.setText(agregar.ddMinute + ":" + agregar.ddSecond);
                }
                if (agregar.minutos == 0 && agregar.segundos == 0) {
                    agregar.tempo.stop();
                    JOptionPane.showMessageDialog((Component)null, "Se te acabo el tiempo!");
                    JOptionPane.showMessageDialog((Component) null, "El juego termino!\nTu puntuacion fue de "+score);
                    try {
                        RecuperarHighscore();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    if(score > scoreAlto){
                        try {
                            NuevoHighscore();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else { JOptionPane.showMessageDialog(null,"No superaste el highscore de "+nombreScoreAlto+" con puntaje de "+scoreAlto); }
                    hoja.dispatchEvent(new WindowEvent(hoja, 201));
                }

            }
        });
    }

    void cerrar() throws IOException {
        JOptionPane.showMessageDialog((Component) null, "El juego termino!\nTu puntuacion fue de "+score);
        RecuperarHighscore();
        if (score > scoreAlto) {
            NuevoHighscore();
        } else {
            JOptionPane.showMessageDialog(null,"No superaste el highscore de "+nombreScoreAlto+" con puntaje de "+scoreAlto);
        }
        String Intentar;
        while (true) {
            Intentar = JOptionPane.showInputDialog("Quieres intentarlo denuevo?\nIngresa S para Si\nIngresa N para No");
            if(Intentar.charAt(0) == 'N' && Intentar.length() == 1){
                break;
            } else if(Intentar.charAt(0) == 'S' && Intentar.length() == 1){
                break;
            } else {JOptionPane.showMessageDialog(null,"El valor es invalido"); }
        }
        if(Intentar.equals("S")){
            score = 0;
            intentos = 0;
        } else {
            if (primera == 0) {
                hoja = new JFrame();
                hoja.setTitle("Un juego");
                hoja.setSize(496, 520);
                hoja.setLocationRelativeTo((Component) null);
                agregar objeto = new agregar();
                hoja.add(objeto);
                hoja.setDefaultCloseOperation(3);
                hoja.setResizable(false);
                primera++;
            }
            hoja.dispatchEvent(new WindowEvent(hoja, 201));
            return;
        }
    }

    public int getPosXPelon() {
        return PosXPelon;
    }

    public int getPosYPelon() {
        return PosYPelon;
    }
}
