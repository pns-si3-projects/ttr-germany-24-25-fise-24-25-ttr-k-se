package fr.cotedazur.univ.polytech.teamK.game;

public class GracefulShutdown {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Runtime.getRuntime().halt(0);
            System.out.println("Shutdown hook triggered. Preforming cleanup...");
            preformCleanup();
        }));
        try{
            System.out.println("Game is running...");
            throw new RuntimeException("An error accured during gameplay");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void preformCleanup(){
        System.out.println("Releasing resources ...");
        //Close database connection
        //Save game state
    }
}
