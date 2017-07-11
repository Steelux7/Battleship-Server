/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import javax.swing.JButton;

/**
 *
 * @author Mariano
 */
public class Casilla extends JButton{

    //private Casilla Casilla;
    private int[] Posicion;
    private boolean Disponible;
    private boolean Atacada;
    private ElementoTablero ElementoTablero;
    //El tipo de elemento es el tipo (edificio) que hay en cada casilla
    private int TipoElemento; // 0 null , 1 Mundo, 2 Conector, 3 Mercado, 4 Mina, 5 Armeria, 6 Templo, 7 agujeroNegro  
    
    public Casilla() {
        this.Disponible = true;
        this.Atacada = false;
        this.Posicion = new int[2];
    }
  
    public Casilla(int fila, int columna) {
        this.Disponible = true;
        this.Atacada = false;
        this.Posicion = new int[2];
        this.Posicion[0] = fila;
        this.Posicion[1] = columna;
        //this.ElementoTablero = new ElementoTablero(fila, columna, null);
    }

    public boolean AgregarElemento(ElementoTablero elemento) {
        if (this.Disponible) {
            this.ElementoTablero = elemento;
            this.Disponible = false;
        }
        return !Disponible;
    }

    public int[] getPosicion() {
        return Posicion;
    }

    public int getFila() {
        return Posicion[0];
    }

    public int getColumna() {
        return Posicion[1];
    }

    public boolean EsAtacado() {
        if (!this.Disponible) { 
            this.ElementoTablero.RecibirDaño();
            this.Atacada = true;
            return Atacada;
            
        } else {
            this.Atacada = false;
            return Atacada;
        }
    }

    public boolean isDisponible() {
        return Disponible;
    }

    public void setDisponible(boolean Disponible) {
        this.Disponible = Disponible;
    }

    public boolean isAtacada() {
        return Atacada;
    }

    
/*

    public Logica.ElementoTablero getElementoTablero() {
        return ElementoTablero;
    }

    public void setElementoTablero(Logica.ElementoTablero ElementoTablero) {
        this.ElementoTablero = ElementoTablero;
    }
    
  */  
    
}
