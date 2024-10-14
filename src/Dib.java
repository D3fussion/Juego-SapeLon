import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class Dib extends JPanel {

    //En esta clase se crea la pelota
    //las dos variables indican la posicion de la bola
    private static int x=0;
    private static int y=0;
    int c = 25;
    int p = 1;
    int[] cuadradosX = new int[c];
    int[] cuadradosY = new int[c];
    int[] pelonesX = new int[p];
    int[] pelonesY = new int[p];
    static int pelotitaX;
    static int pelotitaY;
    static BufferedImage Pelon;
    static BufferedImage Campo;
    static BufferedImage Casa;
    static BufferedImage Mano;
    static BufferedImage ManoDVD;
    static BufferedImage SAPELON;
    static boolean imageLoaded = false;
    //En este metodo se crean las cualidades de la bola (color, forma y tamaño)
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(Campo, 0, 0, 1000,600,this);
        g.drawImage(Mano,x,y,30,30,this);
        // g.fillOval(x,y,30,30);
        // g.setColor(Color.red);
        for (int i = 0; i < c; i++) {
            // g.fillRect(cuadradosX[i],cuadradosY[i],50,50);
            g.drawImage(Casa,cuadradosX[i]-10,cuadradosY[i],69,50,this);
        }
        // g.setColor(Color.green);
        for (int i = 0; i < p; i++) {
            // g.fillRect(pelonesX[i],pelonesY[i],50,50);
            g.drawImage(Pelon,pelonesX[i],pelonesY[i],50,50,this);
        }
    }

    public void imagenes(){
        // The ImageObserver implementation to observe loading of the image
        ImageObserver myImageObserver = new ImageObserver() {
            public boolean imageUpdate(Image image, int flags, int x, int y, int width, int height) {
                if ((flags & ALLBITS) != 0) {
                    imageLoaded = true;
                    System.out.println("La imagen se cargo");
                    return false;
                }
                return true;
            }
        };

        // The image URL - change to where your image file is located!

        String PelonURL = "C:\\Users\\Diego\\IdeaProjects\\juegoJava\\res\\recursitos\\recursitos\\sapelon ccopia.png";
        String CampoURL = "C:\\Users\\Diego\\IdeaProjects\\juegoJava\\res\\campo.png";
        String ManoURL = "C:\\Users\\Diego\\IdeaProjects\\juegoJava\\res\\recursitos\\recursitos\\sapelon ccopia - copia.png";
        String CasaURL = "C:\\Users\\Diego\\IdeaProjects\\juegoJava\\res\\casa.png";
        String ManoDVDUrl = "C:\\Users\\Diego\\IdeaProjects\\juegoJava\\res\\recursitos\\recursitos\\mano sapelon copia.png";
        String SAPELONURL = "C:\\Users\\Diego\\IdeaProjects\\juegoJava\\res\\recursitos\\recursitos\\SAPELON.png";

        /**
         * This call returns immediately and pixels are loaded in the background
         * We use an ImageObserver to be notified when the loading of the image
         * is complete
         */
        Image sourcePelon = Toolkit.getDefaultToolkit().getImage(PelonURL);
        Image sourceCampo = Toolkit.getDefaultToolkit().getImage(CampoURL);
        Image sourceMano = Toolkit.getDefaultToolkit().getImage(ManoURL);
        Image sourceCasa = Toolkit.getDefaultToolkit().getImage(CasaURL);
        Image sourceManoDVD = Toolkit.getDefaultToolkit().getImage(ManoDVDUrl);
        Image sourceSAPELON = Toolkit.getDefaultToolkit().getImage(SAPELONURL);
        sourcePelon.getWidth(myImageObserver);
        sourceCasa.getWidth(myImageObserver);
        sourceCampo.getWidth(myImageObserver);
        sourceMano.getWidth(myImageObserver);
        sourceManoDVD.getWidth(myImageObserver);
        sourceSAPELON.getWidth(myImageObserver);
        // We wait until the image is fully loaded
        while (!imageLoaded) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        // Create a buffered image from the source image with a format that's compatible with the screen
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
        // If the source image has no alpha info use Transparency.OPAQUE instead
        Pelon = graphicsConfiguration.createCompatibleImage(sourcePelon.getWidth(null), sourcePelon.getHeight(null), Transparency.BITMASK);
        Casa = graphicsConfiguration.createCompatibleImage(sourceCasa.getWidth(null), sourceCasa.getHeight(null), Transparency.BITMASK);
        Campo = graphicsConfiguration.createCompatibleImage(sourceCampo.getWidth(null), sourceCampo.getHeight(null), Transparency.BITMASK);
        Mano = graphicsConfiguration.createCompatibleImage(sourceMano.getWidth(null), sourceMano.getHeight(null), Transparency.BITMASK);
        ManoDVD = graphicsConfiguration.createCompatibleImage(sourceManoDVD.getWidth(null), sourceManoDVD.getHeight(null), Transparency.BITMASK);
        SAPELON = graphicsConfiguration.createCompatibleImage(sourceSAPELON.getWidth(null), sourceSAPELON.getHeight(null), Transparency.BITMASK);
        // Copy image to buffered image
        Graphics graphicsPelon = Pelon.createGraphics();
        Graphics graphicsCasa = Casa.createGraphics();
        Graphics graphicsMano = Mano.createGraphics();
        Graphics graphicsCampo = Campo.createGraphics();
        Graphics graphicsManoDVD = ManoDVD.createGraphics();
        Graphics graphicsSAPELON = SAPELON.createGraphics();
        // Paint the image onto the buffered image
        graphicsPelon.drawImage(sourcePelon, 0, 0, null);
        graphicsPelon.dispose();
        graphicsCampo.drawImage(sourceCampo, 0, 0, null);
        graphicsCampo.dispose();
        graphicsMano.drawImage(sourceMano, 0, 0, null);
        graphicsMano.dispose();
        graphicsCasa.drawImage(sourceCasa, 0, 0, null);
        graphicsCasa.dispose();
        graphicsManoDVD.drawImage(sourceManoDVD, 0, 0, null);
        graphicsManoDVD.dispose();
        graphicsSAPELON.drawImage(sourceSAPELON, 0, 0, null);
        graphicsSAPELON.dispose();
    }

    public void random(){
        int ok=0;
        for (int i = 0; i < c; i++) {
            cuadradosX[i] = (int) (Math.random() * 20);
            cuadradosY[i] = (int) (Math.random() * 12);
        }
        int compro = 1;
        while (compro > 0)
            compro = 0;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                ok = 0;
                if(cuadradosX[i] == cuadradosX[j]){
                    if(cuadradosY[i]+1 == cuadradosY[j]) ok++;
                    else if (cuadradosY[i]-1 == cuadradosY[j]) ok++;
                }
                if(cuadradosY[i] == cuadradosY[j]){
                    if(cuadradosX[i]+1 == cuadradosX[j]) ok++;
                    else if (cuadradosX[i]-1 == cuadradosX[j]) ok++;
                }
                if(ok > 0){
                    compro++;
                    cuadradosX[j] = (int) (Math.random() * 20);
                    cuadradosY[j] = (int) (Math.random() * 12);
                }
            }
        }
        for (int i = 0; i < c; i++) {
            cuadradosX[i] = cuadradosX[i] * 50;
            cuadradosY[i] = cuadradosY[i] * 50;
        }
        for (int i = 0; i < p; i++) {
            do {
                ok = 0;
                pelonesX[i] = (int) (Math.random() * 20);
                pelonesY[i] = (int) (Math.random() * 12);
                for (int j = 0; j < c; j++) {
                    if ((pelonesX[i] * 50) == cuadradosX[j] && (pelonesY[i]*50) == cuadradosY[j]) {
                        ok++;
                        break;
                    }
                }
            } while (ok != 0);
            pelonesX[i] = pelonesX[i]*50;
            pelonesY[i] = pelonesY[i]*50;
        }
        do{
            ok = 0;
            pelotitaX = (int) (Math.random() * 20);
            pelotitaY = (int) (Math.random() * 12);
            for (int j = 0; j < c; j++) {
                if ((pelotitaX * 50) == cuadradosX[j] && (pelotitaY*50) == cuadradosY[j]) {
                    ok++;
                    break;
                }
            }
            for (int j = 0; j < p; j++) {
                if ((pelotitaX * 50) == pelonesX[j] && (pelotitaY*50) == pelonesY[j]) {
                    ok++;
                    break;
                }
            }
        } while (ok != 0);
        pelotitaX = pelotitaX*50;
        pelotitaY = pelotitaY*50;
        x = pelotitaX;
        y = pelotitaY;
    }

    public void setX(int x) {

        this.x = x;
    }

    public void setY(int y) {

        this.y = y;
    }

    public int getX1() {

        return x;
    }

    public int getY1() {

        return y;
    }
}
