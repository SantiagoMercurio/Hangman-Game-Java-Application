import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class JuegoDelAhorcado extends JFrame implements ActionListener {

    private JLabel palabraOcultaSL, IntentosL, LetraL, Photo;
    private ImageIcon Iconos;
    private JTextField LetraTF;
    private JButton adivinarButton, reiniciarButton;
    private int intentos = 6;
    private String palabraOcultaS = "RIOT";
    private char[] palabraGuiones = new char[palabraOcultaS.length()];

    public JuegoDelAhorcado() {
        super("Hangman Game");

        
        try{
            Iconos = new ImageIcon(ImageIO.read(new File("Ahorcado.png")).getScaledInstance(200, 200, 0));
        }catch(Exception  e){
            System.out.println("Error loading Ahorcado");
        }
        
        palabraOcultaSL = new JLabel();
        palabraOcultaSL.setBounds(50, 50, 300, 30);
        add(palabraOcultaSL);

        LetraL = new JLabel("Letter:");
        LetraL.setBounds(50, 100, 50, 30);
        add(LetraL);

        LetraTF = new JTextField(1);
        LetraTF.setBounds(100, 100, 30, 30);
        add(LetraTF);

        Photo = new JLabel();
        Photo.setBounds(300,50,200,200);
        Photo.setIcon(Iconos);
        add(Photo);

        adivinarButton = new JButton("Guess");
        adivinarButton.addActionListener(this);
        adivinarButton.setBounds(150, 100, 100, 30);
        add(adivinarButton);

        reiniciarButton = new JButton("Reiniciar");
        reiniciarButton.addActionListener(this);
        reiniciarButton.setBounds(100, 150, 100, 30);


        IntentosL = new JLabel("Attempts left: " + intentos);
        IntentosL.setBounds(50, 200, 150, 30);
        add(IntentosL);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setVisible(true);
        setVisible(true);

        for (int i = 0; i < palabraGuiones.length; i++) {
            palabraGuiones[i] = '_';
        }
        actualizarpalabraOcultaS();
    }

    private void actualizarpalabraOcultaS() {
        String palabra = "";
        for (char c : palabraGuiones) {
            palabra += c + " ";
        }
        palabraOcultaSL.setText(palabra);
    }

    private void reiniciarJuego() {
        intentos = 6;
        IntentosL.setText("Intentos restantes: " + intentos);
        palabraOcultaS = "RIOT";
        palabraGuiones = new char[palabraOcultaS.length()];
        for (int i = 0; i < palabraGuiones.length; i++) {
            palabraGuiones[i] = '_';
        }
        actualizarpalabraOcultaS();
    }

    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == adivinarButton) {
            String letra = LetraTF.getText().toUpperCase();
            if (letra.length() == 1) {
                boolean acierto = false;
                for (int i = 0; i < palabraOcultaS.length(); i++) {
                    if (palabraOcultaS.charAt(i) == letra.charAt(0)) {
                        palabraGuiones[i] = letra.charAt(0);
                        acierto = true;
                    }
                }
                if (!acierto) {
                    intentos--;
                    IntentosL.setText("Intentos restantes: " + intentos);
                    String loadphoto = "Ahorcado"+String.valueOf(6 - intentos)+".png";
                    try{
                        Iconos = new ImageIcon(ImageIO.read(new File(loadphoto)).getScaledInstance(200, 200, 0));
                    }catch(IOException  exp){
                        System.out.println("Error loading Hangman game");
                        System.out.println(exp.getMessage());
                    }
                    Photo.setIcon(Iconos);
                }
                if (palabraOcultaS.equals(String.valueOf(palabraGuiones))) {
                    JOptionPane.showMessageDialog(this, "You Won!");
                    reiniciarJuego();
                }
                LetraTF.setText("");
                actualizarpalabraOcultaS();
                if (intentos == 0) {
                    JOptionPane.showMessageDialog(this, "You lost! The word was: " + palabraOcultaS);
                    reiniciarJuego();
                } else if (palabraOcultaS.equals(String.valueOf(palabraGuiones)))
                    ;
            }
        }
    }
}