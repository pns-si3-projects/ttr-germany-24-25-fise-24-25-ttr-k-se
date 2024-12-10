package fr.cotedazur.univ.polytech.teamK.board.player;

import fr.cotedazur.univ.polytech.teamK.board.map.Connections;

import java.util.ArrayList;


public class Player {
    private int id;
    private static int COUNT = 0;
    private String name;
    private int score;
    private int counters; //Ceci représente le nombre de rails que le joueur a.
    private ArrayList<Connections> claimedConnections; // List of routes claimed by the player

    public Player(String name, int initialCounters) {
        this.id = COUNT++;
        this.name = name;
        this.score = 0;
        this.counters = initialCounters;
        this.claimedConnections = new ArrayList<>();
    }

    // Getteur and Setteur
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getScore() {return score;}
    public int getCounters() {return counters;} //WEEK1
    public ArrayList<Connections> getClaimedConnections() {return claimedConnections;}

    /**
     * Modifie le score du joueur en lui ajoutant une valeur
     *
     * @param value la valeur peut être négative
     */
    public void addScore(int value) { this.score += value; }

    /**
     * Essayes de récupérer une connexion
     *
     * @param connection la connection que le joueur veut
     * @return true if the route is successfully claimed, false otherwise
     */
    public boolean claimRoute(Connections connection) {
        if (connection.isClaimed()) {
            throw new IllegalArgumentException("This route has already been claimed");
        }

        if (connection.getLength() > this.counters) {
            throw new IllegalArgumentException("Not enough counters to claim this route");
        }

        // Deduct counters and claim the route
        this.counters -= connection.getLength();
        this.claimedConnections.add(connection);
        connection.setClaimed();
        return true;
    }
}

/*      CE CODE SERA VALABLE EN SEMAINE 2
        A ADAPTER AVEC LES NOUVELLES CLASSES CARDS

        public class Player {
            private int id ;
            private static int COUNT = 0;
            private String name ;
            private int score;
            //private ArrayList<WagonCards> cartesWagon; POUR WEEK2
            //private ArrayList<CarteDestination> cartesDestination; POUR WEEK2


            public Player(String name) {
                this.id = COUNT++;
                this.name = name;
                this.score = 0;
                this.cartesWagon = new ArrayList<>();
                this.cartesDestination = new ArrayList<>();
            }

            public Player(int id, String name, ArrayList<WagonCards> cartesWagon, ArrayList<CarteDestination> cartesDestination) {
                this(name);
                this.cartesWagon = cartesWagon;
                this.cartesDestination = cartesDestination;
            }

            // Getteur and Setteur
            public int getId() {return id;}
            public void setId(int id) {this.id = id;}
            public String getName() {return name;}
            public void setName(String name) {this.name = name;}
            public int getScore() {return score;}
            public ArrayList<CarteDestination> getCartesDestination() {return cartesDestination;}
            public ArrayList<WagonCards> getCartesWagon() {return cartesWagon;}

            /**
             * Modify the socre of the player by adding a value
             * @param value the value to add to the score
             */
/*          public void addScore(int value) {
                this.score += value;
            }

            /**
             * Add a WagonCard to the player's hand
             * @param carte the card to add
             */
/*          public boolean addCardWagon(WagonCards carte) {
                this.cartesWagon.add(carte);
                return true;
            }


/*          public boolean removeCardWagon(Colors color, int number) {
                if (getNumberColor(color) <number) {
                    throw new IllegalArgumentException("The player doesn't have enough card");
                    return false;
                }
                for (WagonCards carte : this.cartesWagon) {
                    if (carte.getColor() == color && number > 0) {
                        this.cartesWagon.remove(carte);
                        number--;
                    }
                }
                return true;
            }

            /**
             * Get the number of WagonCard of a specific color
             * @param color the color to count
             * @return the number of WagonCard of the color
             */
/*          public int getNumberColor(Colors color) {
                int count = 0;
                for (WagonCards carte : this.cartesWagon) {
                    if (carte.getColor() == color) {
                        count++;
                    }
                }
                return count;
            }

            /**
             * Add a DestinationCard to the player's hand
             * @param carte the card to add
             * @return true if the card was added, false otherwise
             */
/*          public boolean addCardDestination(CarteDestination carte) {
                if (cartesDestination.contains(carte)) {
                    throw new IllegalArgumentException("The player already has this card");
                    return false;
                }
                this.cartesDestination.add(carte);
                return true;
            }

            /**
             * Add DestinationCard points and remove it from the player's hand
             * @param carte the card to check
             * @return true if the card was removed, false otherwise
             */
/*          public boolean validDestinationCard(CarteDestination carte) {
                if (cartesDestination.contains(carte)) {
                    this.score += carte.getPoints();
                    this.cartesDestination.remove(carte);
                    return true;
                }
                throw new IllegalArgumentException("The player doesn't have this card");
                return false;
            }
        }
*/
