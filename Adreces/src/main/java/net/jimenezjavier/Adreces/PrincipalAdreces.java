package net.jimenezjavier.Adreces;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * Programa que emmagatzemarà adreces en un arxiu txt.
 */
public final class PrincipalAdreces {

    /**
     * Classe que genera una finestra on afegirem
     * diferents objectes per poder complimentar
     * el nostre programa.
     */
    private JFrame frame;

    /**
     * Objecte que ens permet afegir text escrit
     * directament per teclat.
     */
    private JTextField txtAdreca;

    /**
     * Variable que ens crearà el fitxer que volem escriure
     * e iniciarà l'escriptura del mateix.
     */
    private FileWriter fichero;

    /**
     * Variable ArrayList on guardarem els objectes Adreca
     * que anem creant cada com que guardem una nova url.
     */
    private ArrayList<Adreca> totalDades;

    /**
     * Variable que ens serveix per escriure el fitxer on
     * emmagatzemarem les adreces url.
     */
    private PrintWriter pw;

    /**
     * Variable Integer que serveix per iniciar el posicionament
     * de les nostres url a l'hora d'emmagatzemarles.
     */
    private int id = 1;

    /**
     * Variable Label que ens mostra el total d'adreces url
     * que tenim emmagatzemades.
     */
    private JLabel lblTotalAdreces = new JLabel("0");

    /**
     * Variable Label que ens mostra la posició actual de l'última
     * adreça afegida i també mostra la posició de les adreces quan
     * cerquem entre les que tenim emmagatzemades.
     */
    private JLabel lblPosActual = new JLabel(Integer.toString(id));

    /**
     * Variable boolean que serveix per limitar la cerca
     * de adreces url i no mostrar una posició inexistent.
     */
    private boolean semafor = true;

    /**
     * Encarregat d'iniciar l'aplicació.
     *
     * @param args arguments per línia de comanda.
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrincipalAdreces window = new PrincipalAdreces();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Crea l'aplucació.
     */
    private PrincipalAdreces() {
        initialize();
    }

    /**
     * Inicialitza el contingut del frame.
     */
    private void initialize() {
        File fitxer = new File("src/main/resources/adreces.txt");
        totalDades = new ArrayList<Adreca>();
        if (fitxer.exists()) {
            try {
                Scanner iterate = new Scanner(fitxer);
                while (iterate.hasNextLine()) {
                    String currLine = iterate.nextLine();
                    URL currUrl = new URL(currLine);
                    totalDades.add(new Adreca(id, currUrl));
                    id++;
                    lblPosActual.setText(Integer.toString(id));
                    lblTotalAdreces
                            .setText(Integer.toString(totalDades.size()));
                }
                fichero = new FileWriter(fitxer, true);
                iterate.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            try {
                fichero = new FileWriter("src/main/resources/adreces.txt",
                        true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JLabel lblSecret = new JLabel("");
        lblSecret.setBounds(31, 12, 364, 18);
        JLabel lblAdreca = new JLabel("Adreça");
        lblAdreca.setBounds(31, 38, 49, 15);
        txtAdreca = new JTextField();
        txtAdreca.setBounds(122, 36, 273, 19);
        txtAdreca.setColumns(10);
        JLabel lblPosicio = new JLabel("Posició");
        lblPosicio.setBounds(31, 83, 55, 15);
        lblPosActual.setBounds(92, 83, 30, 15);
        JButton btnAfegir = new JButton("Nova Adreça");
        btnAfegir.setBounds(149, 73, 122, 25);
        afegirAdreca(lblSecret, btnAfegir);
        JButton btnAnterior = new JButton("<");
        btnAnterior.setBounds(295, 73, 44, 25);
        movimentAnterior(lblSecret, btnAnterior);
        JButton btnSeguent = new JButton(">");
        btnSeguent.setBounds(351, 73, 44, 25);
        movimentSeguent(lblSecret, btnSeguent);
        JLabel lblTotal = new JLabel("Total");
        lblTotal.setBounds(31, 139, 36, 15);
        lblTotalAdreces.setBounds(79, 139, 43, 15);
        JButton btnTancar = new JButton("Sortir");
        btnTancar.setBounds(321, 129, 74, 25);
        tancarAplicacio(btnTancar);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(lblAdreca);
        frame.getContentPane().add(txtAdreca);
        frame.getContentPane().add(lblPosicio);
        frame.getContentPane().add(lblPosActual);
        frame.getContentPane().add(lblTotal);
        frame.getContentPane().add(lblTotalAdreces);
        frame.getContentPane().add(btnAfegir);
        frame.getContentPane().add(btnAnterior);
        frame.getContentPane().add(btnSeguent);
        frame.getContentPane().add(btnTancar);
        frame.getContentPane().add(lblSecret);
        frame.setSize(435, 201);
        frame.setResizable(false);
    }

    /**
     * Mètode encarregat de guardar les adreces url escrites
     * correctament, a les quals els hi dona un valor que marca
     * en quina posició es troba emmagatzemada.
     *
     * @param lblSecret Variable de tipus Label que ens mostra
     * el text si fem una acció incorrecta.
     *
     * @param btnAfegir Variable de tipus botó que emmagatzema
     * les variables escrites correctament.
     */
    private void afegirAdreca(final JLabel lblSecret, final JButton btnAfegir) {
        btnAfegir.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                lblSecret.setText("");
                FileWriter fitxerTemp = null;
                try {
                    fitxerTemp = new FileWriter(
                            "src/main/resources/adreces.txt", true);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                pw = new PrintWriter(fitxerTemp);
                try {
                    URL adreces = new URL(txtAdreca.getText());
                    totalDades.add(new Adreca(id, adreces));
                    pw.println(txtAdreca.getText());
                    System.out.println(txtAdreca.getText());
                    id++;
                    lblTotalAdreces.setText(Integer
                            .toString(totalDades.size()));
                    lblPosActual.setText(Integer.toString(id));
                    txtAdreca.setText("");
                } catch (MalformedURLException e2) {
                    lblSecret.setText("Error: El text"
                            + " introduït no es una URL vàlida");
                }
                try {
                    fitxerTemp.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    /**
     * Mètode encarregat de mostrar les nostres URL emmagatzemades
     * fins a arribar a l'última que hem guardat.
     *
     * @param lblSecret Variable de tipus Label que ens mostra
     * el text si fem una acció incorrecta.
     *
     * @param btnSeguent Variable de tipus botó que mostra
     * les nostres URL emmagatzemades fins a arribar a
     * l'última que hem guardat.
     */
    private void movimentSeguent(final JLabel lblSecret,
            final JButton btnSeguent) {
        btnSeguent.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                semafor = true;
                lblSecret.setText("");
                if (semafor) {
                    if (Integer.parseInt(lblPosActual.getText()) >= totalDades
                            .size()) {
                        lblSecret
                                .setText("No es pot avançar més en la llista.");
                        semafor = false;
                    } else {
                        int position = Integer
                                .parseInt(lblPosActual.getText()) - 1;
                        String url = totalDades
                                .get(position).getAdreca().toString();
                        int numeracio = totalDades.get(position).getId();
                        lblPosActual.setText(Integer.toString(numeracio + 1));
                        txtAdreca.setText(url);
                    }
                }
            }
        });
    }

    /**
     * Mètode encarregat de mostrar les nostres URL emmagatzemades
     * abans de la última que hem afegit.
     *
     * @param lblSecret Variable de tipus Label que ens mostra
     * el text si fem una acció incorrecta.
     *
     * @param btnAnterior Variable de tipus botó que recorre
     * les nostres URL anteriors a la última afegida.
     */
    private void movimentAnterior(final JLabel lblSecret,
            final JButton btnAnterior) {
        btnAnterior.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                semafor = true;
                lblSecret.setText("");
                if (semafor) {
                    if (Integer.parseInt(lblPosActual.getText()) == 1) {
                        semafor = false;
                        lblSecret
                                .setText("No es pot recular més en la llista.");
                    } else {
                        int retroceso = Integer
                                .parseInt(lblPosActual.getText()) - 2;
                        String url = totalDades
                                .get(retroceso).getAdreca().toString();
                        int numeracio = totalDades.get(retroceso).getId();
                        lblPosActual.setText(Integer.toString(numeracio));
                        txtAdreca.setText(url);
                    }
                }
            }
        });
    }

    /**
     * Mètode per tancar la finestra de la nostra aplicació.
     *
     * @param btnTancar Variable de tipus botó que tanca la finestra.
     */
    private void tancarAplicacio(final JButton btnTancar) {
        btnTancar.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (null != fichero) {
                    try {
                        fichero.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                System.exit(0);
            }
        });
    }
}
