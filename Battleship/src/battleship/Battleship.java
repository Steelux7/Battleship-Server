/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

/**
 *
 * @author Teo-PC
 */
public class Battleship {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RandomOpponent op1 = new RandomOpponent();
        RandomOpponent op2 = new RandomOpponent();
        
      int[] shipSizes = {2, 3, 3, 4, 5};
      
      BattleShipCompetition bc = new BattleShipCompetition(
        op1,  //Oponente 1
        op2, // Oponente 2
        1,    //                 // Wins per matc
        new Size(10, 10),       // Board Size
        shipSizes           // Ship Sizes
        );
      
      bc.RunCompetition();
      
 
    }
        
}
