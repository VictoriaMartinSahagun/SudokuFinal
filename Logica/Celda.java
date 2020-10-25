package Logica;

public class Celda {
	private Integer valor;
	private Grafica entidadGrafica;
	private boolean estadoUbicacion;
	
	public Celda() {
		this.valor = null;
		this.entidadGrafica = new Grafica();
		this.estadoUbicacion = true;
	}
	/**
	 * Retorna si una celda esta ubicada correctamente.
	 * @return verdadero su esta bien ubicada, falso caso contrario.
	 */
	public boolean getEstado() {
		return this.estadoUbicacion;
	}
	/**
	 * Establece si una celda esta ubicada correctamente.
	 * @param estado en el cual se encuentra la celda.
	 */
	public void setEstado(boolean estado) {
		this.estadoUbicacion=estado;
	}
	/**
	 * Retorna el entero correspondiente a la cantidad de valores que puede tomar la celda.
	 * @return cantidad de valores posibles.
	 */
	public int getCantElementos() {
		return this.entidadGrafica.getImagenes().length;
	}
	/**
	 * Retorna el valor de la celda.
	 * @return valor de la celda.
	 */
	public Integer getValue() {
		return this.valor;
	}
	/**
	 * Establece el valor de la celda.
	 * @param valor que tomara la celda.
	 */
	public void setValue(Integer valor) {
		if (valor!=null && valor < this.getCantElementos()) {
			this.valor = valor;
			this.entidadGrafica.actualizar(this.valor);
		}else {
			this.valor = null;
		}
	}
	/**
	 * Retorna la entidad grafica correspondiente a la celda.
	 * @return entidad grafica.
	 */
	public Grafica getEntidadGrafica() {
		return entidadGrafica;
	}

}
