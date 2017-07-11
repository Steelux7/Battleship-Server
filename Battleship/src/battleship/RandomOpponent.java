/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.List;
import java.util.Random;

/**
 *
 * @author ulacit
 */
public class RandomOpponent extends IBattleShipOpponent{
    public String Name = "Random";
    Size gameSize;
    Random rand = new Random();
         
    @Override
    public void NewGame(Size size){
        this.gameSize = size;
    }

    @Override
    public void PlaceShips(List<Ship> ships){
        
        for (Ship ship : ships) {
            {
                int x = rand.nextInt(this.gameSize.Width);
                int y = rand.nextInt(this.gameSize.Height);
                
                boolean orient =  rand.nextBoolean();
                System.out.println("Colocando barco en X="+x+1 +" Y="+y+1+" Orientacion: " + (orient?"Horizontal":"Vertical"));
                ship.Place(new Point(x, y), 
                        orient?ShipOrientation.HORIZONTAL:ShipOrientation.VERTICAL);
            }
        }
    }

    @Override
    public Point GetShot(){
        int x = rand.nextInt(this.gameSize.Width);
        int y = rand.nextInt(this.gameSize.Height);
        System.out.println("Disparando a X="+x+1 +" Y="+y+1);
        return new Point(x,y); 
    }


    
    
 
}
