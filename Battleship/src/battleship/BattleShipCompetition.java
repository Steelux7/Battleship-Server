package battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Teo
 */
public class BattleShipCompetition {
    

        private IBattleShipOpponent op1;
        private IBattleShipOpponent op2;
        private Size boardSize;
        private int[] shipSizes;
        private int wins;

        public BattleShipCompetition(IBattleShipOpponent op1, IBattleShipOpponent op2, int wins, Size boardSize, int[] shipSizes)
        {
            if (op1 == null)
            {
                System.out.println("Jugador 1 no puede ser nulo");
            }

            if (op2 == null)
            {
                System.out.println("Jugador 2 no puede ser nulo");
            }


            if (boardSize.Width <= 2 || boardSize.Height <= 2)
            {
                System.out.println("Tama�o del tablero invalido");
            }

            if (shipSizes == null || shipSizes.length < 1)
            {
                System.out.println("Error en la cantidad inicial de barcos");
            }

            /*comprobacion no necesaria
            if (shipSizes.Sum() >= (boardSize.Width * boardSize.Height))
            {
                throw new ArgumentOutOfRangeException("shipSizes");
            }
            */
            this.wins = wins;
            this.op1 = op1;
            this.op2 = op2;
            this.boardSize = boardSize;
            this.shipSizes = shipSizes;
        }

        //public Dictionary<IBattleShipOpponent, int> RunCompetition()
        
        public void RunCompetition()
        {
            Random rand = new Random();

            Map<Integer, IBattleShipOpponent> opponents = new HashMap<>();
            Map<Integer, Integer> scores = new HashMap<>();
            Map<Integer, List<Ship>> ships = new HashMap<>();
            Map<Integer, List<Point>> shots = new HashMap<>();
           
            Integer first = 0;
            Integer second = 1;

            opponents.put(first, this.op1);
            opponents.put(second, this.op2);
            scores.put(second, 0);
            scores.put(first, 0);
            List<Point> shotsFirst = new ArrayList<>();
            List<Point> shotsSecond = new ArrayList<>();
            List<Ship> shipsFirst = new ArrayList<>();
            List<Ship> shipsSecond = new ArrayList<>();            
            shots.put(first, shotsFirst);
            shots.put(second, shotsSecond);
            ships.put(first, shipsFirst);
            ships.put(second, shipsSecond);
            
            /*if (rand.nextBoolean())
            {
            */    Integer swap = first;
                first = second;
                second = swap;
            

			
            //se le avisa a los jugadores que va a comenzar un nueva partida
            opponents.get(first).NewMatch(opponents.get(second).Name );
            opponents.get(second).NewMatch(opponents.get(first).Name);

            boolean success;

            while (true)
            {
		//revisa si se lleg� al limite de juegos para terminar la partida
                //if ((!this.playOut && scores.Where(p => p.Value >= this.wins).Any()) || (this.playOut && scores.Sum(s => s.Value) >= (this.wins * 2 - 1)))
                if (scores.get(first) >= this.wins || scores.get(second) >= this.wins)
                {
                    break;
                }

                /*{
                    Integer swap = first;
                    first = second;
                    second = swap;
                }*/

                shots.get(first).clear();
                shots.get(second).clear();

          
                opponents.get(first).NewGame(this.boardSize);
                opponents.get(second).NewGame(this.boardSize);
       
                
                success = false;
                do
                {
                    System.out.println("Colocando barcos jugador 1");
                    for (int i=0; i<this.shipSizes.length; i++){
                        System.out.println("Colocando barco de largo "+this.shipSizes[i]);
                        ships.get(first).add(new Ship(this.shipSizes[i]));
                    }
                                
                    opponents.get(first).PlaceShips(ships.get(first)); //coloca los barcos

                    System.out.println("Revisando que esten dentro del tablero:");
                    boolean allPlacedValidly = true;
                    for (int i = 0; i < ships.get(first).size(); i++)
                    {
                        if (!ships.get(first).get(i).IsPlaced() || !ships.get(first).get(i).IsValid(this.boardSize)) //revisa que estene colocados y en posicion v�lida
                        {
                            opponents.get(first).restartMatrix();
                            JOptionPane.showMessageDialog(null,"Hay un barco mal colocado o chocando con otro,\npor favor vuelve a poner los barcos");
                            System.out.println("El barco " +i+"  barco no esta colocado correctamente.");
                            allPlacedValidly = false;
                            break;
                        }
                    }
                    
                    if (!allPlacedValidly)
                    {
                        ships.get(first).clear();
                        continue;
                    }

                    System.out.println("Revisando que no choquen con otro barco: ");
                    boolean noneConflict = true;
                    for (int i = 0; i < ships.get(first).size(); i++)
                    {
                        for (int j = i + 1; j < ships.get(first).size(); j++)
                        {
                            if (ships.get(first).get(i).ConflictsWith(ships.get(first).get(j))) //revisa que no hayan conflictos entre los barcos.
                            {
                                opponents.get(first).restartMatrix();
                                JOptionPane.showMessageDialog(null,"Hay un barco mal colocado o chocando con otro,\npor favor vuelve a poner los barcos");
                                System.out.println("Conflicto entre el barco "+i+" y el barco "+j);
                                noneConflict = false;
                                break;
                            }
                        }

                        if (!noneConflict)
                        {
                            break;
                        }
                    }

                    if (!noneConflict)
                    {
                        ships.get(first).clear();
                        continue;
                    }
                    else
                    {
                        success = true;
                    }
                } while (!success);

                
                success = false;
                do
                {
                    System.out.println("Colocando barcos jugador 2");
                    for (int i=0; i<this.shipSizes.length; i++){
                        ships.get(second).add(new Ship(this.shipSizes[i]));
                    }
                  
                    opponents.get(second).PlaceShips(ships.get(second));
                 
                    boolean allPlacedValidly = true;
                    for (int i = 0; i < ships.get(second).size(); i++)
                    {
                        if (!ships.get(second).get(i).IsPlaced() || !ships.get(second).get(i).IsValid(this.boardSize)) //revisa que estene colocados y en posicion v�lida
                        {
                            
                            System.out.println("El barco " +i+"  barco no esta colocado correctamente.");
                            allPlacedValidly = false;
                            break;
                        }
                    }
                    
                    if (!allPlacedValidly)
                    {
                        ships.get(second).clear();
                        continue;
                    }

                    boolean noneConflict = true;
                    for (int i = 0; i < ships.get(second).size(); i++)
                    {
                        for (int j = i + 1; j < ships.get(second).size(); j++)
                        {
                            if (ships.get(second).get(i).ConflictsWith(ships.get(second).get(j))) //revisa que no hayan conflictos entre los barcos.
                            {
                                
                                System.out.println("Conflicto entre el barco "+i+" y el barco "+j);
                                
                                noneConflict = false;
                                break;
                            }
                        }

                        if (!noneConflict)
                        {
                            break;
                        }
                    }

                    if (!noneConflict)
                    {
                        ships.get(second).clear();
                        continue;
                    }
                    else
                    {
                        success = true;
                    }
                } while (!success);

                System.out.println("Se han colocado correctamente los barcos de ambos jugadores");
                int current = first;
                while (true)
                {
                
                    Point shot = opponents.get(current).GetShot();//obtiene el disparo de un oponente
                    boolean yasehizo = false;
                    System.out.println("Disparando jugador: "+current);
                    //if (shots[current].Where(s => s.X == shot.X && s.Y == shot.Y).Any()) //revisa que el disparto no se haya realizado ya.
                    for (int i=0; i<shots.get(current).size(); i++){
                        if (shots.get(current).get(i).equals(shot))
                        {
                            yasehizo =true;
                            JOptionPane.showMessageDialog(null,"Este disparo ya se habia realizado");
                            
                            System.out.println("Este disparo ya se habia hecho");
                            continue;
                            
                        }
                    }

                    if (yasehizo) continue;
                    shots.get(current).add(shot);

                 
                    opponents.get(1 - current).OpponentShot(shot);//le avisa al oponente donde se realiza el disparo
                    
                    //revisa si le disparo a un barco
                    Ship ship = null;
                    for (int i=0; i<ships.get(1-current).size();i++){
                        if (ships.get(1-current).get(i).IsAt(shot))
                            ship = ships.get(1-current).get(i);
                    }
                    
                    if (ship != null)//si le disparo revisa si esta hundido
                    {
                        boolean sunk = ship.IsSunk(shots.get(current));
                        opponents.get(current).ShotHit(shot, sunk);//le avisa que el disparo fue exitoso, y si undio o no un barco
                   }
                    else
                    {
                        System.out.println("Disparo fallo");
                        opponents.get(current).ShotMiss(shot);//le avisa que el disparo fallo
                    }

                    Ship barcoSinUndir = null;
                               
                    for (int i=0; i<ships.get(1-current).size(); i++){
                        if (!ships.get(1-current).get(i).IsSunk(shots.get(current))){
                            barcoSinUndir = ships.get(1-current).get(i);
                            
                        }
                            
                    }
                   // if (!unsunk.Any()) { RecordWin(current, 1 - current, scores, opponents); break; } //si quedan barcos sin undir entonces termina el juego
                    if (barcoSinUndir==null){
                        System.out.println("Gano el jugador "+current);
                        if (current == first)
                            JOptionPane.showMessageDialog(null,"Has Ganado");
                        else
                            JOptionPane.showMessageDialog(null,"Has Perdido");
                        break;
                        
                    }
                    current = 1 - current;
                }
                break;
            }

            opponents.get(first).MatchOver();
            opponents.get(second).MatchOver();

            //return scores.Keys.ToDictionary(s => opponents[s], s => scores[s]);
        }
/*
        private void RecordWin(int winner, int loser, Dictionary<int, int> scores, Dictionary<int, IBattleshipOpponent> opponents)
        {
            scores[winner]++;
            opponents[winner].GameWon();
            opponents[loser].GameLost();
        }
        */
    
}
