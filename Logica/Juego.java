package Logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Juego {
	private Celda [] [] tablero;
	private int cantFilas;
	private static int medidaPanel=3;
	private long tiempoInicio;
	/**
	 * Inicia el juego 
	 * Establece el panel con las celdas correspondientes segun el archivo de incio de juego
	 * y verifica que sea valido. Luego elimina celdas al azar para que se pueda comenzar a jugar.
	 * En el caso de que el archivo de incio no sea valido se mostrara un panel emergente con una notificacion.
	 */
	public Juego() {
		tiempoInicio = System.currentTimeMillis();
		String linea;
		int numero;
		BufferedReader br;
		Scanner sc;
		Random rand = new Random();
		cantFilas=9;
		tablero = new Celda[cantFilas][cantFilas];
		br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/Archivos/InicioSudoku.txt")));
			try {
				for(int i=0; i<cantFilas && (linea=br.readLine())!=null;i++) {
					sc= new Scanner(linea);
					for(int j=0;j<cantFilas;j++) {
						numero=sc.nextInt();
						int randInt = rand.nextInt(1);	//ceo todo el tablero relleno
						tablero [i][j] = new Celda();
						if(randInt==0) {
							tablero[i][j].setValue(numero);
						}
					}
				}		
				if(controlarTablero()) {
					for(int i=0;i<cantFilas;i++) {
						for(int j=0;j<cantFilas;j++) {
							int randInt = rand.nextInt(2);	
							if(randInt==0) {
								tablero [i][j] = new Celda();
							}
						}
					}	
				}else {
					for(int i=0;i<cantFilas;i++) {
						for(int j=0;j<cantFilas;j++) {
							tablero [i][j] = new Celda();
						}
					}	
					JOptionPane.showMessageDialog(null,"Error","El archivo solucion no es valido",JOptionPane.WARNING_MESSAGE);
				}
				br.close();
			}catch(IOException e) {
				JOptionPane.showMessageDialog(null,"Error","El archivo solucion no es valido",JOptionPane.WARNING_MESSAGE);
			}
	}
	/**
	 * Verifica si el tablero completo tiene una respuesta valida para el juego.
	 * @return verdadero si el tablero es una respuesta valida, falso caso contrario.
	 */
	private boolean controlarTablero() {
		boolean valido=true;
		int [] valoresColumna;
		int [] valoresFila;
		for(int i=0;i<cantFilas && valido;i++) {//recorro las filas y compruebo que en cada una no se repitan numeros
			valoresColumna= new int[cantFilas];
			valoresFila= new int[cantFilas];
			for(int j=0;j<cantFilas && valido;j++) {
				//compruebo columna
				if(!valorExistente(tablero[i][j].getValue(),valoresColumna)) {
					valoresColumna[j]=tablero[i][j].getValue();
				}else {
					valido=false;
				}
				//compruebo fila
				if(!valorExistente(tablero[j][i].getValue(),valoresFila)) {
					valoresFila[j]=tablero[j][i].getValue();
				}else {
					valido=false;
				}
			}
		}
		//compruebo los paneles
		for(int i=0;i<medidaPanel && valido;i++) {
			for(int j=0;j<medidaPanel && valido;j++) {
				valido=recorrerPanel(medidaPanel*i,medidaPanel*j);
			}
		}
		return valido;
	}
	/**
	 * Controla si un determinado valor v se encuentra en un arreglo de enteros valores.
	 * @param v entero a buscar.
	 * @param valores arreglo de enteros donde se buscara el valor.
	 * @return verdadero si el valor existe en el arreglo, falso caso contrario.
	 */
	private boolean valorExistente(int v,int [] valores) {
		boolean existe=false;
		for(int i=0;i<valores.length && !existe;i++) {
			if(v==valores[i]) {
				existe=true;
			}
		}
		return existe;
	}
	/**
	 * Verifica que en un determinado panel del tablero no se repitan los mismos valores.
	 * @param inicioF indice de la fila en la cual inicia el panel.
	 * @param inicioC indice de la cola en la cual inicia el panel.
	 * @return verdadero si en el panel no se repiten valores, falso caso contrario.
	 */
	private boolean recorrerPanel(int inicioF,int inicioC) {
		boolean valido=true;
		int [] valores= new int [medidaPanel*medidaPanel];
		int v=0;
		for(int k=inicioF;k<inicioF+medidaPanel && valido;k++) {	
			for(int l=inicioC;l<inicioC+medidaPanel && valido;l++) {	
				if(!valorExistente(tablero[k][l].getValue(),valores)) {
					valores[v]=tablero[k][l].getValue();
					v++;
				}else {
					valido=false;
				}
			}
		}
		return valido;
	}
	/**
	 * Retorna el tiempo que transcurrio desde el inicio del juego.
	 * @return tiempo trascurrido desde el incio del juego.
	 */
	public long getTiempoTranscurrido() {
		long tiempoActual = System.currentTimeMillis();
		long transcurrido = tiempoActual - tiempoInicio;
		return transcurrido;
	}
	/**
	 * Establece el valor de una determinada celda.
	 * @param i indice correspondiente a la fila de la celda.
	 * @param j indice correspondiente a la columna de la celda.
	 * @param numero valor a establecer en la celda.
	 */
	public void setCelda(int i,int j, int numero) {
		if(i<cantFilas && j<cantFilas) {
			tablero[i][j].setValue(numero);	
		}
	}
	/**
	 * Retorna la celda correspondiente.
	 * @param i indice correspondiente a la fila de la celda.
	 * @param j indice correspondiente a la columna de la celda.
	 * @return celda correspondiente.
	 */
	public Celda getCelda(int i, int j) {
		return this.tablero[i][j];
	}
	/**
	 * Retorna la cantidad de filas del tablero.
	 * @return cantidad de filas.
	 */
	public int getCantFilas() {
		return this.cantFilas;
	}
	/**
	 * Verifica que el valor establecido en determinada celda sea valido segun el reglamento del juego.
	 * @param i indice correspondiente a la fila de la celda.
	 * @param j indice correspondiente a la columna de la celda.
	 */
	public void validarRespuesta(int i,int j) {
		int inicioF= (i/medidaPanel)*medidaPanel;
		int inicioC= (j/medidaPanel)*medidaPanel;
		int numeroNuevo=tablero[i][j].getValue();
		tablero[i][j].setEstado(true);
		for(int k=0;k<cantFilas;k++) {
			if(k!=j) {//verificar que en cada fila no se repitan numeros
				if(tablero[i][k].getValue()!=null) {
						if(tablero[i][k].getValue()==numeroNuevo) {
							System.out.println("entref1");
							tablero[i][j].setEstado(false);
							tablero[i][k].setEstado(false);
						}else if(!tablero[i][k].getEstado()) {	//si la celda no fue puesta como falsa en la verificacion actual y es falsa
							validarRespuestaFalsa(i,k);
						}
				}
			}
			if(k!=i) {
				if(tablero[k][j].getValue()!=null) {	//verifica que en cada columna no se repitan numeros
					if(tablero[k][j].getValue()==numeroNuevo) {
						System.out.println("entref2"+k+j);
						tablero[i][j].setEstado(false);
						tablero[k][j].setEstado(false);
					}else if(!tablero[k][j].getEstado()) {	//si la celda no fue puesta como falsa en la verificacion actual y es falsa
						validarRespuestaFalsa(k,j);
					}
				}
			}
		}
		for(int k=inicioF;k<inicioF+medidaPanel;k++) {	
			for(int l=inicioC;l<inicioC+medidaPanel;l++) {	
				if(k!=i && l!=j) {	//verifico que en el panel no se repitan valores
					if(tablero[k][l].getValue()!=null && tablero[k][l].getValue()==numeroNuevo) {
						tablero[i][j].setEstado(false);
						tablero[k][l].setEstado(false);
					}else if(tablero[k][l].getEstado()==false) {//si la celda no fue puesta como falsa en la verificacion actual y es falsa
						validarRespuestaFalsa(k,l);
					}
				}
			}
		}
		finJuego();
	}
	/**
	 * Verifica si el juego finalizo de manera correcta.
	 * @return verdadero si el panel esta completo de manera correcta, falso caso contrario.
	 */
	public boolean finJuego() {
		boolean fin=true;
		for(int i=0;i<cantFilas && fin;i++) {
			for(int j=0;j<cantFilas && fin;j++) {
				if(tablero[i][j].getValue()==null || !tablero[i][j].getEstado()) {
					fin=false;
				}
			}
		}
		return fin;
	}
	/**
	 * Retorna la medida que tendra el panel de alto y ancho.
	 * @return medida del panel.
	 */
	public int getMedidaPanel() {
		return medidaPanel;
	}
	/**
	 *  Verifica una celda con estado falso para ver si debe ser alterado.
	 * @param i indice correspondiente a la fila de la celda.
	 * @param j indice correspondiente a la columna de la celda.
	 */
	private void validarRespuestaFalsa(int i,int j) {
		boolean encontreRepetida=false;
		int inicioF= (i/medidaPanel)*medidaPanel;
		int inicioC= (j/medidaPanel)*medidaPanel;
		for(int k=0;k<cantFilas && !encontreRepetida;k++) {
			//verifico que no se repita el valor en la misma fila
			if(k!=j && tablero[i][k].getValue()!=null && tablero[i][k].getValue()==tablero[i][j].getValue()) {
				encontreRepetida=true;
			}
			//verifico que no se repita el valor en la misma columna
			if(k!=i && tablero[k][j].getValue()!=null && tablero[k][j].getValue()==tablero[i][j].getValue()) {
				encontreRepetida=true;
			}
		}
		//verifico que no se repita el valor en el mismo panel
		for(int k=inicioF;k<inicioF+medidaPanel;k++) {	
			for(int l=inicioC;l<inicioC+medidaPanel;l++) {	
				if(k!=i && l!=j && tablero[k][l].getValue()!=null && tablero[k][l].getValue()==tablero[i][j].getValue()) {
					encontreRepetida=true;
				}
			}
		}
		tablero[i][j].setEstado(!encontreRepetida);
	}
}
