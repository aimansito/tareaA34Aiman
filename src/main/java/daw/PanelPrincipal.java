/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author aiman
 */
public class PanelPrincipal extends JPanel implements ActionListener{

    // Atributos de la clase (privados)
    private PanelBotones botonera;
    private JTextArea areaTexto;
    private int tipoOperacion;
    private double valor;

    // Constructor
    public PanelPrincipal() {
        initComponents();
        tipoOperacion = -1; // No hay operaciones en la calculadora
    }

    // Se inicializan los componentes gráficos y se colocan en el panel
    private void initComponents() {
        // Creamos el panel de botones
        botonera = new PanelBotones();
        // Creamos el área de texto
        areaTexto = new JTextArea(10, 50);
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.white);

        //Establecemos layout del panel principal
        this.setLayout(new BorderLayout());
        // Colocamos la botonera y el área texto
        this.add(areaTexto, BorderLayout.NORTH);
        this.add(botonera, BorderLayout.SOUTH);
        
        for (JButton jb : botonera.getgrupoBotones()) {
            jb.addActionListener(this);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        double valorActual = 0;
        Object o = e.getSource();
        if (o instanceof JButton jButton) {
            String textoBoton = jButton.getText();
            switch (textoBoton) {
                case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
                    areaTexto.setText(areaTexto.getText() + textoBoton );
                }
                case "+" -> {
                    valor = Double.parseDouble(areaTexto.getText());
                    tipoOperacion = 0;
                    areaTexto.setText(Double.parseDouble(areaTexto.getText())+"+"+valor);
                }
                case "-" -> {
                    valor = Double.parseDouble(areaTexto.getText());
                    tipoOperacion = 1;
                    areaTexto.setText("");
                }
                case "*" -> {
                    valor = Double.parseDouble(areaTexto.getText());
                    tipoOperacion = 2;
                    areaTexto.setText(valor+"");
                }
                case "/" -> {
                    valor = Double.parseDouble(areaTexto.getText());
                    tipoOperacion = 3;
                    areaTexto.setText(valor+"");
                }
                case "C" -> {
                    tipoOperacion = -1;
                    areaTexto.setText(valor+"");
                }
                case "=" -> {
                    try {
                        valorActual = Double.parseDouble(areaTexto.getText());
                        switch (tipoOperacion) {
                            case 0 -> {
                                // Suma
                                double resultadoSuma = valor + valorActual;
                                areaTexto.setText(String.valueOf(valor+"+"+valorActual+"="+resultadoSuma));
                            }
                            case 1 -> {
                                // Resta
                                double resultadoResta = valor - valorActual;
                                areaTexto.setText(String.valueOf(valor+"+"+valorActual+"="+resultadoResta));
                            }
                            case 2 -> {
                                // Multiplicación
                                double resultadoMultiplicacion = valor * valorActual;
                                areaTexto.setText(String.valueOf(valor+"+"+valorActual+"="+resultadoMultiplicacion));
                            }
                            case 3 -> {
                                // División
                                if (valorActual != 0) {
                                    double resultadoDivision = valor / valorActual;
                                    areaTexto.setText(String.valueOf(valor+"+"+valorActual+"="+resultadoDivision));
                                } else {
                                    areaTexto.setText("Error: división por cero");
                                }
                            }
                        }
                    } catch (NumberFormatException ex) {
                        areaTexto.setText("Error: entrada inválida");
                    }
                    tipoOperacion = -1;
                }
            }
        }
    }
}
