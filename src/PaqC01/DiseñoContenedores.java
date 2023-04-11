package PaqC01;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiseñoContenedores extends JFrame {
    private JLabel NumId;
    private JTextField NumIdtext;
    private JLabel Peso;
    private JTextField Pesotext;
    private JLabel Desc;
    private JTextArea Desctext;
    private JLabel Emp_rem;
    private JLabel Emp_rec;
    private JTextField Emp_rectext;
    private JLabel Pais_proc;
    private JComboBox Pais_procbox;
    private JLabel Prior;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JCheckBox Insp_Aduanas;
    private JPanel mainPanel;
    private JTextField Emp_remtext;
    private JLabel Ops;
    private JButton Ap_button;
    private JButton Desap_button;
    private JTextField numCol_text;
    private JButton ContData_button;
    private JTextField ID_text;
    private JButton Cuantos_button;
    private JComboBox cuantosPais_box;
    private JTextField Cant_text;
    private JPanel auxPanel;
    private JLabel Estado;
    private JTextArea Estad_text;
    private JLabel Logo;
    private JLabel Mensajes;
    private JLabel hubATrabajar;
    private JRadioButton a1Hub;
    private JRadioButton a2Hub;
    private JRadioButton a3Hub;


    public static String textoID = "";
    public static String textoPeso = "";
    public static String textoDesc = "";
    public static String textoRem = "";
    public static String textoRec = "";
    public static String botonPais = "";
    public static boolean botonPrior1 = false;
    public static boolean botonPrior2 = false;
    public static boolean botonPrior3 = false;
    public static boolean botonAduanas = false;
    private int hubMostrar = 0;


    public DiseñoContenedores() {
        setContentPane(mainPanel);
        setTitle("Welcome");
        setSize(1500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        Hub h1 = new Hub();
        Hub h2 = new Hub();
        Hub h3 = new Hub();
        Puerto p1 = new Puerto();
        p1.setPuerto(new Hub[]{h1, h2, h3});

        Estad_text.setText(p1.toStringHUB(hubMostrar));

        NumIdtext.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Introduzca el número de identificación.");
                try {
                    int dato = Integer.parseInt(NumIdtext.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El dato no es un entero, vuelve a teclearlo.");
                }
                textoID = NumIdtext.getText();
            }
        });
        Pesotext.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Introduzca el peso en toneladas.");
                try {
                    int dato = Integer.parseInt(Pesotext.getText());
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"El dato no es un entero, vuelve a teclearlo.");
                }
                textoPeso = Pesotext.getText();
            }
        });
        Desctext.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Complete la descripción del contenido.");
                //En este apartado no imponemos restricción
                textoDesc = Desctext.getText();
            }
        });
        Emp_remtext.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Escriba el nombre de la empresa remitente.");
                try {
                    Integer.toString(Integer.parseInt(Emp_remtext.getText()));
                    JOptionPane.showMessageDialog(null,"El dato no es correcto (debe ser String), vuelve a teclearlo.");
                } catch(NumberFormatException ex) {
                }
                textoRem = Emp_remtext.getText();
            }
        });
        Emp_rectext.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Escriba el nombre de la empresa receptora.");
                try {
                    Integer.toString(Integer.parseInt(Emp_rectext.getText()));
                    JOptionPane.showMessageDialog(null,"El dato no es correcto (debe ser String), vuelve a teclearlo.");
                } catch(NumberFormatException ex) {
                }
                textoRec = Emp_rectext.getText();
            }
        });
        Ap_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Contenedor apilado.");
                int prioridad = 0;
                if(a1RadioButton.isSelected()) {
                    prioridad = 1;
                } else if(a2RadioButton.isSelected()) {
                    prioridad = 2;
                } else if(a3RadioButton.isSelected()) {
                    prioridad = 3;
                }
                p1.apilaContenedor(new Contenedor(Integer.parseInt(NumIdtext.getText()), Integer.parseInt(Pesotext.getText()), (String) Pais_procbox.getSelectedItem(), Insp_Aduanas.isSelected(), prioridad, Desctext.getText(), Emp_remtext.getText(), Emp_rectext.getText()));
                Estad_text.setText(p1.toStringHUB(hubMostrar));
            }
        });
        Desap_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Contenedor desapilado.");
                p1.desapilaContenedor(hubMostrar, Integer.parseInt(numCol_text.getText()));
                Estad_text.setText(p1.toStringHUB(hubMostrar));
            }
        });
        numCol_text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Introduzca el número de columna de la que hay que desapilar");
                try {
                    int dato = Integer.parseInt(numCol_text.getText());
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"El dato no es un entero, vuelve a teclearlo.");
                }
            }
        });
        ContData_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Mostrando datos del contenedor.");
                DiseñoContenedoresP2 newframe = new DiseñoContenedoresP2();
                newframe.setVisible(true);
            }
        });
        ID_text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Introduzca el ID del contenedor");
                try {
                    int dato = Integer.parseInt(ID_text.getText());
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"El dato no es válido para la ID.");
                }
            }
        });
        Cuantos_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Cantidad registrada.");
                Cant_text.setText(String.valueOf(p1.contenedoresPorPais2((String) cuantosPais_box.getSelectedItem())));
            }
        });
        cuantosPais_box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Contenedores del país.");
            }
        });
        Cant_text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Número de contenedores procedentes del país indicado.");
            }
        });
        Pais_procbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("País de procedencia registrado.");
                botonPais = (String)Pais_procbox.getSelectedItem();
            }
        });
        a1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Prioridad anotada (Marque solo una).");
                botonPrior1 = a1RadioButton.isSelected();
            }
        });
        a2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Prioridad anotada (Marque solo una).");
                botonPrior2 = a2RadioButton.isSelected();
            }
        });
        a3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Prioridad anotada (Marque solo una).");
                botonPrior3 = a3RadioButton.isSelected();
            }
        });
        Insp_Aduanas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Inspección confirmada.");
                botonAduanas = Insp_Aduanas.isSelected();
            }
        });
        Estad_text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Mensajes.setText("Plano del hub.");
            }
        });
        a1Hub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Hub seleccionado (Marque solo uno).");
                if(a1Hub.isSelected()) hubMostrar = 1;
            }
        });
        a2Hub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Hub seleccionado (Marque solo uno).");
                if(a2Hub.isSelected()) hubMostrar = 2;
            }
        });
        a3Hub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensajes.setText("Hub seleccionado (Marque solo uno).");
                if(a3Hub.isSelected()) hubMostrar = 3;
            }
        });
    }

    public DiseñoContenedores(JLabel logo) throws HeadlessException {
        Logo = logo;
        Logo.setSize(150, 128);
    }
}
