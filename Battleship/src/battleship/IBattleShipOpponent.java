/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.List;

/**
 *
 * @author ulacit
 */
public class IBattleShipOpponent {
        public String Name = "";

        public void NewMatch(String opponent){}

        public void NewGame(Size size){}

        public void PlaceShips(List<Ship> ships){}

        public Point GetShot(){
            return new Point(0,0);
        }

        public void OpponentShot(Point shot){}

        public void ShotHit(Point shot, boolean sunk){}

        public void ShotMiss(Point shot){}

        public void GameWon(){}

        public void GameLost(){}

        public void MatchOver(){}
        
        public void restartMatrix(){}
        
        public int demeNumero(String mensaje, int menor, int mayor) {
         while (true){
            try{
                String s = javax.swing.JOptionPane.showInputDialog(mensaje);
                int n=Integer.parseInt(s);
                if (n>=menor && n<=mayor)
                    return n;
            }catch (Exception e){
                mensaje = "El número ingresado no es correcto.\n";
            }
        }
        
    }
}
