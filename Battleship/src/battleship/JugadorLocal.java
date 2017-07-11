/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import interfaz.matrix;
import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Teo Gavriel
 */
public class JugadorLocal extends IBattleShipOpponent{
    public String Name = "Local";
    Size gameSize;
    Random rand = new Random();
    public matrix Matrix1;
    public matrix Matrix2;
    
    
    public void setMatrix(matrix m1, matrix m2){
        this.Matrix1 = m1;
        this.Matrix2 = m2;
    }
    @Override
    public void restartMatrix(){
        for (int i=0;i<10;i++)
            for (int h=0; h<10; h++)
                this.Matrix1.MatrizUsuario[i][h].setBackground(Color.DARK_GRAY);
    }
    @Override
    public void NewGame(Size size){
        this.gameSize = size;
    }

    @Override
    public void PlaceShips(List<Ship> ships){
        
        for (Ship ship : ships) {
            {
                
                        
                int x = demeNumero("Digite la Columna en la cual desea colocar el barco de largo "+ship.Length(), 1,10);
                int y = demeNumero("Digite la Fila en la cual desea colocar el barco de largo "+ship.Length(), 1,10);
                
                boolean orient=true;
                if (demeNumero("Digite 1 si desea el barco Horizontal y 2 si lo desea Vertical" ,1,2)==2)orient=false;
                System.out.println("Colocando barco en X="+x +" Y="+y+" Orientacion: " + (orient?"Horizontal":"Vertical"));
                ship.Place(new Point(x-1, y-1), 
                        orient?ShipOrientation.HORIZONTAL:ShipOrientation.VERTICAL);
                for (Point p : ship.GetAllLocations()){
                    try{
                        this.Matrix1.MatrizUsuario[p.X][p.Y].setBackground(Color.green);
                    }catch (Exception e){
                        System.out.println("Barco mal colocado, reiniciando");
                    }
                }
            }
        }
    }

    @Override
    public void OpponentShot(Point shot){
        
        this.Matrix1.MatrizUsuario[shot.X][shot.Y].setBackground(Color.red);
    }
    
    @Override
    public void ShotHit(Point shot, boolean sunk){
        this.Matrix2.MatrizUsuario[shot.X][shot.Y].setBackground(sunk?Color.BLUE:Color.ORANGE);
        
    }
    
    @Override
    public void ShotMiss(Point shot){
        System.out.println("Disparo fallloooooo");
        this.Matrix2.MatrizUsuario[shot.X][shot.Y].setBackground(Color.RED);
    
    }
    
    
    @Override
    public Point GetShot(){
        int x = demeNumero("Digite la Columna en la cual desea disparar" , 1,10);
        int y = demeNumero("Digite la Fila en la cual desea disparar" , 1,10);
        
        System.out.println("Disparando a X="+x +" Y="+y);
        return new Point(x-1,y-1); 
    }


    
    
 
}
