package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import Logica.Celda;
import Logica.Grafica;
import Logica.Juego;

public class Gui extends JFrame {
	private Juego juego;
	private JPanel contentPane;
	private int seleccion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					ImageIcon iconoFrame = new ImageIcon(this.getClass().getResource("/Archivos/iconoFrame.png"));
					frame.setIconImage(iconoFrame.getImage());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		juego = new Juego();
		
		//CREO EL FRAME INICIAL
		setTitle("SUDOKU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 453);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//CREO EL PANEL TIMER
		JPanel PanelTimer = new JPanel();
		PanelTimer.setPreferredSize(new Dimension(100,50));
		PanelTimer.setLayout(new GridLayout(0, 5, 0, 0));
		contentPane.add(PanelTimer, BorderLayout.NORTH);
		
		Grafica imagenm1= new Grafica();
    	Grafica imagenm2= new Grafica();
    	Grafica imagens1= new Grafica();
    	Grafica imagens2= new Grafica();
		//Creo el label correspondiente al minuto 1
		JLabel m1 = new JLabel();
		imagenm1.actualizar(0);
		m1.setIcon(imagenm1.getGrafico());
		m1.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					reDimensionar(m1, imagenm1.getGrafico());
					m1.setIcon(imagenm1.getGrafico());
				}
		});
		PanelTimer.add(m1);
		//Creo el label correspondiente al minuto 2
		JLabel m2 = new JLabel();
		imagenm2.actualizar(0);
		m2.setIcon(imagenm2.getGrafico());
		m2.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					reDimensionar(m2, imagenm2.getGrafico());
					m2.setIcon(imagenm2.getGrafico());
				}
		});
		PanelTimer.add(m2);
		//Creo el label correspondiente a ':'
		ImageIcon medio = new ImageIcon((this.getClass().getResource("/Archivos/dosPuntosTimer.png")));
		JLabel med = new JLabel();
		med.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reDimensionar(med, medio);
				med.setIcon(medio);
			}
		});
		PanelTimer.add(med);
		//Creo el label correspondiente al segundo 1
		JLabel s1 = new JLabel();
		imagens1.actualizar(0);
		s1.setIcon(imagens1.getGrafico());
		s1.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					reDimensionar(s1, imagens1.getGrafico());
					s1.setIcon(imagens1.getGrafico());
				}
		});
		PanelTimer.add(s1);
		//Creo el label correspondiente al segundo 2
		JLabel s2 = new JLabel();
		imagens2.actualizar(0);
		s2.setIcon(imagens2.getGrafico());
		s2.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					reDimensionar(s2, imagens2.getGrafico());
					s2.setIcon(imagens2.getGrafico());
				}
		});
		PanelTimer.add(s2);
		//Creo el Timer para que el timer funcione adecuadamente
		int aumentoTimer=1000;
		Timer timer = new Timer(aumentoTimer, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                       	
                long tiempo = juego.getTiempoTranscurrido();
                int totalS = (int) (tiempo/1000)%60;
                int si1 = totalS/10;
                int si2 = totalS%10;
                int totalM = (int) (tiempo/1000)/60;
                int mi1 = totalM/10;
                int mi2 = totalM%10;
                
                imagens1.actualizar(si1);
                s1.setIcon(imagens1.getGrafico());
                reDimensionar(s1,imagens1.getGrafico());
                
                imagens2.actualizar(si2);
                s2.setIcon(imagens2.getGrafico());
                reDimensionar(s2,imagens2.getGrafico());
                
                imagenm1.actualizar(mi1);
                m1.setIcon(imagenm1.getGrafico());
                reDimensionar(m1,imagenm1.getGrafico());
                
                imagenm2.actualizar(mi2);
                m2.setIcon(imagenm2.getGrafico());
                reDimensionar(m2,imagenm2.getGrafico());
                
                PanelTimer.repaint();
            }
        });
		
		//CREO EL PANEL INICIAL 
		JPanel PanelTablero = new JPanel();
		PanelTablero.setBorder(new LineBorder(SystemColor.activeCaption, 5));
		contentPane.add(PanelTablero, BorderLayout.CENTER);
		PanelTablero.setLayout(new GridLayout(juego.getCantFilas(), juego.getCantFilas(), 0, 0));
		
		//CREO EL PANEL DE INCIO Y VARIABLES
		JPanel PanelInicioExtras = new JPanel();
		contentPane.add(PanelInicioExtras, BorderLayout.SOUTH);
		PanelInicioExtras.setLayout(new BorderLayout(0, 0));
		
		//Creo un subpanel para las variables
		JPanel PanelVariables = new JPanel();
		PanelInicioExtras.add(PanelVariables,BorderLayout.NORTH);
		PanelVariables.setLayout(new GridLayout(1,0,0,0));
		
		//Creo los botones que contendran las variables a introducir en las celdas
		for(int i=1;i<juego.getCantFilas()+1;i++) {
			JButton boton = new JButton(""+i);
			boton.setName("elemento "+i);
			PanelVariables.add(boton);
			boton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccion=Integer.parseInt(boton.getText());
				}
			});	
		}
		
		//Creo el boton iniciar 
		JButton Iniciar = new JButton("Iniciar");
		Iniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iniciar.setText("Iniciado");
				iniciarJuego(PanelTablero,timer);
				timer.start();
				Iniciar.setEnabled(false);
			}
		});
		
		PanelInicioExtras.add(Iniciar, BorderLayout.SOUTH);	
	}
	/**
	 * Inicia el juego.
	 * @param PanelTablero panel en el cual se ubica el tablero principal.
	 * @param timer.
	 */
	private void iniciarJuego(JPanel PanelTablero, Timer timer) {
		int filas=juego.getCantFilas();
		int cantPaneles=juego.getCantFilas()/juego.getMedidaPanel();
		int k;
		
		//Inicio el panel incial del juego con sus valores.
		for (int i = 0; i <filas; i++) {
			for(int j =0; j<filas; j++) {
				
				//Creo los labels correspondientes a las celdas.
				k=1;
				Celda c = juego.getCelda(i,j);
				ImageIcon grafico = c.getEntidadGrafica().getGrafico();
				JLabel label = new JLabel();
				label.setName(i+""+j);
				c.getEntidadGrafica().setLabel(label);
				label.setOpaque(true);
				label.setBorder(new MatteBorder(1,1,1,1,SystemColor.activeCaption));
				label.setBackground(Color.white);
				PanelTablero.add(label);

				//Agrego los bordes correspondientes a la division de subpaneles.
				while(k<cantPaneles) {
					if(j==juego.getMedidaPanel()*k-1) {
						label.setBorder(new MatteBorder(1,1,1,3,SystemColor.activeCaption));
					}
					if(i==juego.getMedidaPanel()*k-1) {
						label.setBorder(new MatteBorder(1,1,3,1,SystemColor.activeCaption));
					}
					if(i==juego.getMedidaPanel()*k-1 && j==juego.getMedidaPanel()*k-1) {
						label.setBorder(new MatteBorder(1,1,3,3,SystemColor.activeCaption));
					}
					if(i==juego.getMedidaPanel()*k-1 && j==juego.getMedidaPanel()-1) {
						label.setBorder(new MatteBorder(1,1,3,3,SystemColor.activeCaption));
					}
					if(j==juego.getMedidaPanel()*k-1 && i==juego.getMedidaPanel()-1) {
						label.setBorder(new MatteBorder(1,1,3,3,SystemColor.activeCaption));
					}
					k++;
				}

				//Añado al los labels los oyentes correspondientes.
				label.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						reDimensionar(label, grafico);
						label.setIcon(grafico);
					}
				});
				if(c.getValue()==null) {
					label.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int nombre= Integer.parseInt(label.getName());
							int columna =(nombre%10);
							int fila = nombre/10;
							juego.setCelda(fila,columna,seleccion);
							ImageIcon grafico = juego.getCelda(fila,columna).getEntidadGrafica().getGrafico();
							label.setIcon(grafico);
							reDimensionar(label, grafico);
							juego.validarRespuesta(fila,columna);//le indico al juego que valide la celda agregada.
							//recorro la matriz y si existen celdas mal ubicadas las señalo.
							for(int i=0;i<juego.getCantFilas();i++) {
								for(int j=0;j<juego.getCantFilas();j++) {
									if (juego.getCelda(i,j).getEstado()) {
										juego.getCelda(i,j).getEntidadGrafica().getLabel().setBackground(Color.white);
									}else {
										juego.getCelda(i,j).getEntidadGrafica().getLabel().setBackground(Color.gray);
									}
								}
							}
							PanelTablero.repaint();
							if(juego.finJuego()) {//verifico si es el final del juego
								timer.stop();
								Icon gano = new ImageIcon((this.getClass().getResource("/Archivos/ganador.jpg")));
								JOptionPane.showMessageDialog(null, "Gano el sudoku", "Fin del juego", JOptionPane.INFORMATION_MESSAGE, gano);
							}
						}
					});
				}
			}
		}	
	}
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
}
