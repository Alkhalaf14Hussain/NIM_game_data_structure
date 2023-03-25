
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Boolean PC =false;
        Boolean me =true;
        GameTree game;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of pieces: ");
        int numOfPieces = scanner.nextInt();
        while (numOfPieces<=0){
            System.out.println("Invalid choice. please try again.");
            System.out.print("Enter number of pieces: ");
            numOfPieces=scanner.nextInt();
        }
        System.out.println();
        System.out.println("Who start first? ");
        System.out.println("1.Me");
        System.out.println("2.Computer");
        int playFirst =scanner.nextInt();

        while(playFirst>2 || playFirst<1){
            System.out.println("Invalid choice. please try again...");
            System.out.println("Who start first? ");
            System.out.println("1.Me");
            System.out.println("2.Computer");
            playFirst=scanner.nextInt();

        }
        if (playFirst==1) {
            game = new GameTree(numOfPieces, me);
            game.generateGame();
        }
        else if(playFirst==2) {
            game = new GameTree(numOfPieces, PC);
            game.generateGame();
        }




    }
}
