package Logica;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Grafica {
	private ImageIcon grafico;
	private String[] imagenes;
	private JLabel label;
	/**
	 * Establece el grafico como una imagen vacia e incializa el arreglo de imagenes con las referenciaas
	 * a los archivos corrspondientes a las imagenes de numeros.
	 */
	public Grafica() {
		this.grafico = new ImageIcon();
		this.imagenes = new String[]{"/Archivos/cero.png","/Archivos/uno.png", "/Archivos/dos.png","/Archivos/tres.png","/Archivos/cuatro.png",
				"/Archivos/cinco.png","/Archivos/seis.png","/Archivos/siete.png","/Archivos/ocho.png","/Archivos/nueve.png"};
	}
	/**
	 * Actualiza la imagen de la grafica con una determinada imagen.
	 * @param indice valor que se desea poner en la imagen.
	 */
	public void actualizar(int indice) {
		if (indice < this.imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	/**
	 * Retorna la imagen correspondiente a la grafica.
	 * @return	imagen correspondiente a la grafica.
	 */
	public ImageIcon getGrafico() {
		return this.grafico;
	}
	/**
	 * Establece el label de la grafica.
	 * @param l label a establecer.
	 */
	public void setLabel(JLabel l) {
		label=l;
	}
	/**
	 * Retorna el label correspondiente a la grafica.
	 * @return label.
	 */
	public JLabel getLabel() {
		return label;
	}
	/**
	 * Establece el grafico.
	 * @param grafico a establecer.
	 */
	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	/**
	 * Retorna el arreglo de Strig con las referencias a las imagenes de la grafica.
	 * @return arreglo de String.
	 */
	public String[] getImagenes() {
		return this.imagenes;
	}
	/**
	 * Establece el arreglo de String con las referencias a las imagenes de la grafica.
	 * @param imagenes arreglo de String.
	 */
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
}
