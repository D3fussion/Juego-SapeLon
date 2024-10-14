import javax.swing.*;
import java.io.IOException;

public class Main extends JFrame {
    agregar ag = new agregar();
    boolean aX = true;
    boolean aY = true;
    int v = 5;
    int pG;
    int angulo=0;
    int first=0;
    boolean ParaElRandom = true;
    pantallaINICIO pI1 = new pantallaINICIO();
    public Main() throws IOException, InterruptedException {
        //Aqui se define el tamaño del que sera el panel
        setSize(1016, 639); //600 x 400 Lo demas son margenes de error
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(3);
        setVisible(false);
        setLocationRelativeTo(null);
        Dib d = new Dib();
        add(d);
        d.imagenes();
        setVisible(false);
        pI1.pantalla();
        setVisible(true);
        while (true) {
            pG=0;
            if(ParaElRandom) d.random();
            ParaElRandom = true;
            d.repaint();
            if(first == 0){
                try {
                    angulo = Integer.parseInt(JOptionPane.showInputDialog("Ingresa un angulo diagonal al que quieres que la pelota vaya (45, 135, 225, 315)"));
                } catch (Exception e) {
                    ag.cerrar();
                }
                if (angulo == 45) {
                    aX = true;
                    aY = false;
                } else if (angulo == 135) {
                    aX = false;
                    aY = false;
                } else if (angulo == 225) {
                    aX = false;
                    aY = true;
                } else if (angulo == 315) {
                    aX = true;
                    aY = true;
                } else
                    ag.cerrar();
                first++;
            }
            while (pG != d.p) {
                boolean ok = false;
                if (d.getX1() >= (getWidth() - 45))
                    aX = false;//Este if se encarga de el punto en el que rebotara la pelota en la orilla derecha
                //Mientras mas grande el numero mas lejos de la orilla es el rebote
                if (d.getY1() >= (getHeight() - 70))
                    aY = false;//Este if se encarga de el punto en el que rebotara la pelota en la orilla de abajo
                //Mientras mas grande el numero mas lejos de la orilla es el rebote
                if (d.getX1() <= 0)
                    aX = true;//Este if se encarga de el punto en el que rebotara la pelota en la orilla izquierda
                //Mientras mas grande el numero mas lejos del panel se saldra la bola
                if (d.getY1() <= 0)
                    aY = true;//Este if se encarga de el punto en el que rebotara la pelota en la orilla de arriba
                //Mientras mas grande el numero mas lejos de la orilla es el rebote
                for (int i = 0; i < d.c; i++) {
                    int random = (int) (Math.random() * 4);
                    if (random == 0) {
                        if (d.getX1() == d.cuadradosX[i] - 30) {
                            if (d.getY1() >= d.cuadradosY[i] - 30 && d.getY1() <= d.cuadradosY[i] + 50) aX = false;
                        } else if (d.getX1() == d.cuadradosX[i] + 50) {
                            if (d.getY1() >= d.cuadradosY[i] - 30 && d.getY1() <= d.cuadradosY[i] + 50) aX = true;
                        } else if (d.getY1() == d.cuadradosY[i] - 30) {
                            if (d.getX1() >= d.cuadradosX[i] - 30 && d.getX1() <= d.cuadradosX[i] + 50) aY = false;
                        } else if (d.getY1() == d.cuadradosY[i] + 50) {
                            if (d.getX1() >= d.cuadradosX[i] - 30 && d.getX1() <= d.cuadradosX[i] + 50) aY = true;
                        }
                    } else if (random == 1) {
                        if (d.getX1() == d.cuadradosX[i] + 50) {
                            if (d.getY1() >= d.cuadradosY[i] - 30 && d.getY1() <= d.cuadradosY[i] + 50) aX = true;
                        } else if (d.getY1() == d.cuadradosY[i] - 30) {
                            if (d.getX1() >= d.cuadradosX[i] - 30 && d.getX1() <= d.cuadradosX[i] + 50) aY = false;
                        } else if (d.getY1() == d.cuadradosY[i] + 50) {
                            if (d.getX1() >= d.cuadradosX[i] - 30 && d.getX1() <= d.cuadradosX[i] + 50) aY = true;
                        } else if (d.getX1() == d.cuadradosX[i] - 30) {
                            if (d.getY1() >= d.cuadradosY[i] - 30 && d.getY1() <= d.cuadradosY[i] + 50) aX = false;
                        }
                    } else if (random == 2) {
                        if (d.getY1() == d.cuadradosY[i] - 30) {
                            if (d.getX1() >= d.cuadradosX[i] - 30 && d.getX1() <= d.cuadradosX[i] + 50) aY = false;
                        } else if (d.getY1() == d.cuadradosY[i] + 50) {
                            if (d.getX1() >= d.cuadradosX[i] - 30 && d.getX1() <= d.cuadradosX[i] + 50) aY = true;
                        } else if (d.getX1() == d.cuadradosX[i] - 30) {
                            if (d.getY1() >= d.cuadradosY[i] - 30 && d.getY1() <= d.cuadradosY[i] + 50) aX = false;
                        } else if (d.getX1() == d.cuadradosX[i] + 50) {
                            if (d.getY1() >= d.cuadradosY[i] - 30 && d.getY1() <= d.cuadradosY[i] + 50) aX = true;
                        }
                    } else if (random == 3) {
                        if (d.getY1() == d.cuadradosY[i] + 50) {
                            if (d.getX1() >= d.cuadradosX[i] - 30 && d.getX1() <= d.cuadradosX[i] + 50) aY = true;
                        } else if (d.getX1() == d.cuadradosX[i] - 30) {
                            if (d.getY1() >= d.cuadradosY[i] - 30 && d.getY1() <= d.cuadradosY[i] + 50) aX = false;
                        } else if (d.getX1() == d.cuadradosX[i] + 50) {
                            if (d.getY1() >= d.cuadradosY[i] - 30 && d.getY1() <= d.cuadradosY[i] + 50) aX = true;
                        } else if (d.getY1() == d.cuadradosY[i] - 30) {
                            if (d.getX1() >= d.cuadradosX[i] - 30 && d.getX1() <= d.cuadradosX[i] + 50) aY = false;
                        }
                    }
                }
                for (int i = 0; i < d.p; i++) {
                    if (d.getX1() == d.pelonesX[i] - 30) {
                        if (d.getY1() >= d.pelonesY[i] - 30 && d.getY1() <= d.pelonesY[i] + 50) {
                            d.pelonesX[i] = 1300;
                            d.pelonesY[i] = 1300;
                            ok = true;
                        }
                    }
                    if (d.getY1() == d.pelonesY[i] - 30) {
                        if (d.getX1() >= d.pelonesX[i] - 30 && d.getX1() <= d.pelonesX[i] + 50) {
                            d.pelonesX[i] = 1300;
                            d.pelonesY[i] = 1300;
                            ok = true;
                        }
                    }
                    if (d.getX1() == d.pelonesX[i] + 50) {
                        if (d.getY1() >= d.pelonesY[i] - 30 && d.getY1() <= d.pelonesY[i] + 50) {
                            d.pelonesX[i] = 1300;
                            d.pelonesY[i] = 1300;
                            ok = true;
                        }
                    }
                    if (d.getY1() == d.pelonesY[i] + 50) {
                        if (d.getX1() >= d.pelonesX[i] - 30 && d.getX1() <= d.pelonesX[i] + 50) {
                            d.pelonesX[i] = 1300;
                            d.pelonesY[i] = 1300;
                            ok = true;
                        }
                    }
                }
                // if(ok) break;
                //Aqui se indica la velocidad y direccion del rebote, tambien la direccion que tomara la bola al iniciar
                //En el if se indica que mientras no toque alguna orilla del eje X o Y el movimiento seguira positivo pero si choca con una orilla el else indica que el movimiento se convierte en negativo causando el rebote
                if (aX) {
                    d.setX(d.getX1() + 10);//Este positivo indica el movimieto en el eje X el cual es dentro del panel, si se cambia a negativo la bola se movera en sentido contrario y se saldra del panel
                } else {
                    d.setX(d.getX1() - 10);//Este negativo indica el rebote, si fuera positivo la bola no rebotaria, el tamaño del numero es la velocidad el rebote, entre mas grande el rebote tendra mayor velocidad
                }

                if (aY) {
                    d.setY(d.getY1() + 10);//Este positivo indica el movimiento en el eje Y el cual es dentro del panel, si se cambia a negativo la bola se movera en sentido contrario y se saldra del panel
                } else {
                    d.setY(d.getY1() - 10);//Este negativo indica el rebote, si fuera positivo la bola no rebotaria, el tamaño del numero es la velocidad el rebote, entre mas grande el rebote tendra mayor velocidad
                }
                if (ok) {
                    angulo = 0;
                    agregar.inicio();
                    pG++;
                    System.out.println(pG);
                    if (pG != d.p) {
                        try {
                            angulo = Integer.parseInt(JOptionPane.showInputDialog("Ingresa un angulo diagonal al que quieres que la pelota vaya"));
                        } catch (Exception e) {
                            ag.cerrar();
                        }
                        if (angulo == 45) {
                            aX = true;
                            aY = false;
                        } else if (angulo == 135) {
                            aX = false;
                            aY = false;
                        } else if (angulo == 225) {
                            aX = false;
                            aY = true;
                        } else if (angulo == 315) {
                            aX = true;
                            aY = true;
                        } else
                            ag.cerrar();
                    }
                }
                //Aqui definimos la velocidad de la pelota
                if(ag.a == 5) v = 30;
                if(ag.a == 45) v = 45;
                if(ag.a == 90) v = 60;
                if(ag.a == 150) v = 75;
                if(ag.a == 250) v = 90;
                if(ag.a == 260) v = 110;
                if(ag.a == 275) v = 130;
                if(ag.a == 280) v = 160;
                if(ag.a == 285) v = 185;
                if(ag.a == 290) v = 210;
                if(ag.a == 295) v = 235;
                if(ag.a == 300) break;
                pausa(v);
                d.repaint();
                ag.a++;
            }
            first=0;
            if(ag.a == 300 && ag.intentos == 0) {
                JOptionPane.showMessageDialog(null,"Te quedan 2 intentos mas");
                ag.intentos++;
                first = 0;
                ag.a = 5;
                v = 30;
                ParaElRandom = false;
            } else if (ag.a == 300 && ag.intentos == 1) {
                JOptionPane.showMessageDialog(null,"Te queda 1 intento mas");
                ag.intentos++;
                first = 0;
                ag.a = 5;
                v = 30;
                ParaElRandom = false;
            } else if (ag.a == 300 && ag.intentos == 2) {
                JOptionPane.showMessageDialog(null,"Ya no te quedan intentos extra");
                ag.intentos++;
                first = 0;
                ag.a = 5;
                v = 30;
                ParaElRandom = false;
            } else if (ag.a == 300 && ag.intentos == 3) {
                ag.cerrar();
                first = 0;
                ag.a = 5;
                v = 30;
            }
        }
    }
    //Este metodo se utiliza para definir el tiempo que quieres que la pelota rebote
    void pausa(int ms){
        Long ini=System.currentTimeMillis();
        while(System.currentTimeMillis()<(ini+ms)){
        }
    }

}