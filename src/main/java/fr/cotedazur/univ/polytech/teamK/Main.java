package fr.cotedazur.univ.polytech.teamK;

import fr.cotedazur.univ.polytech.teamK.board.Cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.Cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.Cards.TypeOfCards;
import fr.cotedazur.univ.polytech.teamK.board.Cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.Cities;
import fr.cotedazur.univ.polytech.teamK.board.map.Connections;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
           on veut que dans le main il y ait un truc du genre
           Game currentGame = new Game(Player joueur1, Player joueur2);
           currentGame.startGame();
         */
        Deck<DestinationCard> deckOfDestinationCards = new Deck(TypeOfCards.DESTINATION);
        Deck<WagonCard> deckOfWagonCards = new Deck(TypeOfCards.WAGON);

        Connections connection1 = new Connections(Cities.DUSSELDORF, Cities.DORTMUND, 3, List.of(Colors.ORANGE, Colors.YELLOW));


        Player player1 = new Player("John Doe");
        Player player2 = new Player("Jane Doe");

        System.out.println("Les joueurs n'ont pour le moment aucune carte.");
        System.out.println(player1 + "\n" + player2);

        player1.addCardDestination(deckOfDestinationCards.draw());
        player1.addCardDestination(deckOfDestinationCards.draw());
        player1.addCardWagon(deckOfWagonCards.draw());
        player2.addCardWagon(deckOfWagonCards.draw());
        player2.addCardDestination(deckOfDestinationCards.draw());

        Player player3 = new Player("Tom doe");
        player3.addCardWagon(new WagonCard(Colors.ORANGE));
        player3.addCardWagon(new WagonCard(Colors.ORANGE));
        player3.addCardWagon(new WagonCard(Colors.YELLOW));
        player3.addCardWagon(new WagonCard(Colors.ORANGE));
        player3.addCardWagon(new WagonCard(Colors.ORANGE));

        System.out.println("\n\nLes joueurs ont tirés quelques cartes.");
        System.out.println(player1 + "\n" + player2);

        System.out.println("\n\nLe joueur 3 n'a pas acheté une connection.");
        System.out.println(player3);

        System.out.println("\n\nLe joueur 3 a acheté une connection.");
        player3.buyRail(connection1, Colors.ORANGE);
        System.out.println(connection1);


        System.out.println("\n\nLe joueur 3 a pris des meeples.");
        player3.takeMeeples(Cities.DUSSELDORF);
        player3.takeMeeples(Cities.DORTMUND);
        System.out.println(player3);

        System.out.println("\n\nLe joueur 2 a pris une connection (la meme que joueur 3).");
        player2.addCardWagon(new WagonCard(Colors.YELLOW));
        player2.addCardWagon(new WagonCard(Colors.YELLOW));
        player2.addCardWagon(new WagonCard(Colors.YELLOW));
        player2.buyRail(connection1, Colors.YELLOW);
        System.out.println(connection1);

        System.out.println("\n\nLe joueur 2 essaie de prendre des meeples.");
        player2.takeMeeples(Cities.DUSSELDORF);
        player2.takeMeeples(Cities.DORTMUND);
        System.out.println(player2);

    }
}
