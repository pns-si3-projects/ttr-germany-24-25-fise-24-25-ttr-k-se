package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.Cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.Cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.Random;

public class Bot extends Player {
    private Integer ID;
    public Bot(Integer ID)
    {
        super("temp name)");
        this.ID = ID;
        if (ID == 0)
        {
            super.setName("Dumb");
        }
    }


    public boolean playTurn(MapSimple currentmap, Deck<DestinationCard> destinationDeck, Deck<WagonCard> wagonDeck)
    {
        if (this.ID == 0)
        {
            Colors[] availableColors = Colors.values();
            for (int connectionIndex = 0; connectionIndex < currentmap.getConnectionsInMap().size(); connectionIndex++)
            {
                //tests every color to buy
                Connection currentConnectionTried = currentmap.getConnectionsInMap().get(connectionIndex);
                for (int colorIndex = 0; colorIndex < Colors.values().length; colorIndex++)
                {
                    Colors colorToTest = availableColors[colorIndex];
                    if(this.buyRail(currentConnectionTried, colorToTest))
                    {

                        //super.takeMeeples(currentConnectionTried.getStartCity(), PICKORDERFORCOLORS);
                        //super.takeMeeples(currentConnectionTried.getEndCity(), PICKORDERFORCOLORS);
                        return true;
                        //we want to be breaking out of the method,
                        // maybe a return would make more sense? to see what we did on that turn
                    }
                }
            }
            //if you get here, no connections can be bought: in which case draw a card
            Random rand = new Random();
            int rand_int = rand.nextInt(100);
            if (rand_int < 20)
            {
                //draw from destination
                DestinationCard destCardDrawn = destinationDeck.draw();
                if (destCardDrawn != null)
                {
                    super.addCardDestination(destCardDrawn);
                    return true;
                }

            }
            else
            {
                //draw from wagons
                WagonCard wagonCardDrawn1 = wagonDeck.draw();
                WagonCard wagonCardDrawn2 = wagonDeck.draw();
                if (wagonCardDrawn1 != null && wagonCardDrawn2 != null)
                {
                    super.addCardWagon(wagonCardDrawn1);
                    super.addCardWagon(wagonCardDrawn2);
                    return true;
                }
            }

            //would need to have a throw about if nothing happens, idk; something to ensure turns actually happen
            return false;
        }
        //need a return statement outside of the if
        return false;
    }

}
