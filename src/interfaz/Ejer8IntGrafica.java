package interfaz;

import java.awt.Color;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.*;

public class Ejer8IntGrafica {
	JFrame v;
	JLabel lNombre, lPeso, lAltura, lIndice;
	JTextField cNombre, cPeso, cAltura, cIndice;
	JButton bCalcular;
	JPanel contenedor;
	JMenuBar barraMenu;
	JMenuItem salir;
	JMenuItem guardar;
	JMenu menuSalir;

	public Ejer8IntGrafica() {
		crearVentana();
		crearComponentes();
		crearContenedor();
		asociarComponentesContenedor();
		asociarContenedorVentana();
		crearMenu();
		controlarEventos();
		mostrarVentana();
	}

	public void crearVentana() {
		v = new JFrame();
		v.setTitle("Ejercicio 8");
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setBounds(100, 100, 250, 300);
	}

	public void crearComponentes() {
		lNombre = new JLabel("Nombre");
		lPeso = new JLabel("Peso(kg)");
		lAltura = new JLabel("Altura");
		lIndice = new JLabel("Indice de Masa Corporal");

		lNombre.setBounds(25, 25, 75, 25);
		lPeso.setBounds(25, 60, 75, 25);
		lAltura.setBounds(25, 95, 75, 25);
		lIndice.setBounds(25, 180, 175, 25);

		cNombre = new JTextField();
		cAltura = new JTextField();
		cPeso = new JTextField();
		cIndice = new JTextField();

		cNombre.setBounds(100, 25, 100, 25);
		cNombre.setText("Nombre");
		cPeso.setBounds(100, 60, 100, 25);
		cPeso.setText("0");
		cAltura.setBounds(100, 95, 100, 25);
		cAltura.setText("0");
		cIndice.setBounds(25, 205, 175, 25);
		cIndice.setText("0");

		bCalcular = new JButton("Calcular IMC");
		bCalcular.setBounds(25, 130, 175, 40);
	}

	public void crearContenedor() {
		contenedor = new JPanel();
		contenedor.setLayout(null);
	}

	public void asociarComponentesContenedor() {
		contenedor.add(lNombre);
		contenedor.add(lPeso);
		contenedor.add(lAltura);
		contenedor.add(lIndice);
		contenedor.add(cNombre);
		contenedor.add(cPeso);
		contenedor.add(cAltura);
		contenedor.add(cIndice);
		contenedor.add(bCalcular);
	}

	public void asociarContenedorVentana() {
		v.getContentPane().add(contenedor);
	}

	public void controlarEventos() {
		accionCalcular aCalcular = new accionCalcular();
		bCalcular.addActionListener(aCalcular);

		accionSalir aSalir = new accionSalir();
		salir.addActionListener(aSalir);

		accionGuardar aGuardar = new accionGuardar();
		guardar.addActionListener(aGuardar);
		
		limpiarCajas lCaja = new limpiarCajas();
		cNombre.addFocusListener(lCaja);
		cPeso.addFocusListener(lCaja);
		cAltura.addFocusListener(lCaja);
	}

	private void crearMenu() {
		barraMenu = new JMenuBar();
		menuSalir = new JMenu("Archivo");
		salir = new JMenuItem("Salir");
		guardar = new JMenuItem("Guardar");

		barraMenu.add(menuSalir);
		menuSalir.add(salir);
		menuSalir.add(guardar);

		v.setJMenuBar(barraMenu);

	}

	public void mostrarVentana() {
		v.setVisible(true);
	}

	public class accionCalcular implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				double imc = 0;
				imc = Double.parseDouble(cPeso.getText()) / ((Math.pow(Double.parseDouble(cAltura.getText()), 2)));
				cIndice.setText(imc + "");
				if (imc < 25) {
					cIndice.setForeground(Color.green);
				} else {
					cIndice.setForeground(Color.red);
				}
			} catch (Exception exc) {
				cIndice.setForeground(Color.yellow);
				cIndice.setText("Valores incorrectos");
			}

		}
	}

	public class accionSalir implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int valor = JOptionPane.showConfirmDialog(contenedor, "Seguro que quieres salir?");

			if (valor == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(contenedor, "ok");
				System.exit(0);
			} else {
				JOptionPane.showMessageDialog(contenedor, "Buena elección");
			}
		}
	}

	public class accionGuardar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			File pesos = new File("pesos.txt");
			try {
				FileWriter archivo = new FileWriter(pesos, true);
				BufferedWriter contenido = new BufferedWriter(archivo);
				String cadena = "Nombre: " + cNombre.getText() + " | Peso: " + cPeso.getText() + " | Altura: "
						+ cAltura.getText() + " | IMC: " + cIndice.getText();
				contenido.write(cadena);
				contenido.newLine();
				contenido.close();
			} catch (Exception er) {
				JOptionPane.showMessageDialog(contenedor, "Error en la escritura del archivo");
			}
		}
	}

	public class limpiarCajas implements FocusListener {
		public void focusGained(FocusEvent arg0) {
			JTextField caja = (JTextField) arg0.getComponent();
			caja.setText("");
			caja.setBackground(Color.yellow);
		}

		public void focusLost(FocusEvent arg0) {
			JTextField caja = (JTextField) arg0.getComponent();
			caja.setBackground(Color.white);
		}
	}

	public static void main(String[] args) {
		Ejer8IntGrafica i = new Ejer8IntGrafica();
	}
}
