package fr.cotedazur.univ.polytech.teamK;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.Cities;
import fr.cotedazur.univ.polytech.teamK.board.map.Connections;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Alice", 10);

        // Définir une route entre deux villes avec 3 rails rouges
        List<Colors> rails = List.of(Colors.RED, Colors.RED, Colors.RED);
        Connections connection = new Connections(Cities.NETHERLANDS, Cities.DUSSELDORF, 3, rails);

        // Démontrer les affirmations
        System.out.println("Initial counters: " + player.getCounters()); // Compteurs initiaux
        System.out.println("Is the connection claimed? " + connection.isClaimed()); // Route non réclamée

        if (player.claimRoute(connection)) {
            System.out.println("Route claimed successfully!");
            System.out.println("Remaining counters: " + player.getCounters()); // Compteurs restants après réclamation
            System.out.println("Is the connection claimed? " + connection.isClaimed()); // Route réclamée
            System.out.println("Claimed connections: " + player.getClaimedConnections().size()); // Nombre de routes réclamées
        } else {
            System.out.println("Failed to claim the route.");
        }

        // Tester une autre réclamation (même route ou autre scénario)
        try {
            player.claimRoute(connection); // Essayer de réclamer une route déjà réclamée
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
