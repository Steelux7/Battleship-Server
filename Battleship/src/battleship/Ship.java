/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teo-PC
 */
public class Ship {
    
    private boolean isPlaced = false;
    private Point location;
    private ShipOrientation orientation;
    private final int length;

    public Ship(int length)
    {
        if (length <= 1)
        {
            System.out.println("Error largo barco");
        }

        this.length = length;
    }

    public boolean IsPlaced()
    {
        return this.isPlaced;
    }

    public Point Location()
    {
        if (!this.isPlaced)
            System.out.println("No esta colocada");
        return this.location;
            
    }

    public ShipOrientation Orientation()
    {
        return this.orientation;         
    }

    public int Length()
    {
        return this.length;
    }

    public void Place(Point location, ShipOrientation orientation)
    {
        this.location = location;
        this.orientation = orientation;
        this.isPlaced = true;
    }

    //Retorna verdero si el barco no se sale del tablero
    public boolean IsValid(Size boardSize)
    {
        if (!this.isPlaced)
        {
            return false;
        }

        if (this.location.X < 0 || this.location.Y < 0)
        {
            return false;
        }

        if (this.orientation == ShipOrientation.HORIZONTAL)
        {
            if (this.location.Y >= boardSize.Height || this.location.X + this.length > boardSize.Width)
            {
                return false;
            }
        }
        else
        {
            if (this.location.X >= boardSize.Width || this.location.Y + this.length > boardSize.Height)
            {
                return false;
            }
        }

        return true;
    }

    //Retorna verdadero si este punto esta dentro del barco.
    public boolean IsAt(Point location)
    {
        if (this.orientation == ShipOrientation.HORIZONTAL)
        {
            return (this.location.Y == location.Y) && (this.location.X <= location.X) && (this.location.X + this.length > location.X);
        }
        else
        {
            return (this.location.X == location.X) && (this.location.Y <= location.Y) && (this.location.Y + this.length > location.Y);
        }
    }

    //Retorna la lista con todos los puntos del barco
    public List<Point> GetAllLocations()
    {
        List<Point> locations = new ArrayList();
        if (this.orientation == ShipOrientation.HORIZONTAL)
        {
            for (int i = 0; i < this.length; i++)
            {
                locations.add(new Point(this.location.X + i, this.location.Y));
            }
        }
        else
        {
            for (int i = 0; i < this.length; i++)
            {
                locations.add(new Point(this.location.X, this.location.Y + i));
            }
        }
        return locations;
    }

    //Retorna verdadero si este barco choca con el barco que pasa de parametro
    public boolean ConflictsWith(Ship otherShip)
    {
        List<Point> locations = otherShip.GetAllLocations();
        for (Point location1 : locations) {
            if (this.IsAt(location1)) {
                return true;
            }
        }

        return false;
    }

    //Retorna verdadero si la lista de disparos es suficiente para unidir este barco
    //Revisar
    public boolean IsSunk(List<Point> shots)
    {
        List<Point> locations = this.GetAllLocations();
        for (Point location1 : locations)
        {
            if (!shots.contains(location1)){
                return false;
            }
        }
        return true;
    }
    
    
}
