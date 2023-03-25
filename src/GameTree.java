
import java.util.Random;
import java.util.Scanner;

public class GameTree {
    GameNode root;

    public GameTree(int numberOfPieces, boolean playerTurn){
        root= new GameNode();
        root.minMaxValue=0;
        root.level=0;
        root.numberOfPieces=numberOfPieces;
        root.player=playerTurn;
    }
    public GameTree(int numberOfPieces, boolean playerTurn,int level){
        root= new GameNode();
        root.minMaxValue=0;
        root.level=level;
        root.numberOfPieces=numberOfPieces;
        root.player=playerTurn;

    }

    public void createTree(){
        createTree(root,root.numberOfPieces,root.level);
    }

    public void createTree(GameNode root, int n,int level){
        level=level+1;
        if (n==1) {
            root.left = new GameNode(root.numberOfPieces - 1,level);
            createTree(root.left, n - 1,level);
        }

        if (n>1) {
            root.left = new GameNode(root.numberOfPieces - 1,level);
            root.right = new GameNode(root.numberOfPieces - 2,level);
            createTree(root.left, n - 1,level);
            createTree(root.right, n - 2,level);
        }
    }

    public void generateGame(){
        generateGame(root);
    }
    public void generateGame(GameNode root) {
        System.out.println("Choose difficulty:");
        System.out.println("1.Easy");
        System.out.println("2.Somewhat difficult");
        System.out.println("3.Difficult");
        createTree();
        Scanner scanner = new Scanner(System.in); // choosing the difficulty of the game
        int choice =scanner.nextInt();
        while (choice>3 || choice<1){
            System.out.println("invalid choice. please try again.");
            System.out.println("Choose difficulty:");
            System.out.println("1.Easy");
            System.out.println("2.Somewhat difficult");
            System.out.println("3.Difficult");
            choice =scanner.nextInt();
        }
        if (choice==1)
            easy(root);
        else if (choice==2)
            somewhatDifficult(root);
        else if (choice==3)
            difficult(root);

    }

    public void difficult(GameNode root){
        Scanner scanner = new Scanner(System.in);
        setMinMaxUsingDFS(); // assigning the min max values
        print(root.numberOfPieces);
        int hint=1;
        int move;
        while (root.numberOfPieces > 0) {

            if (root.numberOfPieces == 1 && root.player == true) { // human win
                System.out.println("Congratulation, You win!");
                System.out.println("===================");
                break;
            }

            if (root.numberOfPieces == 1 && root.player == false) { // pc win
                System.out.println("Computer wins");
                System.out.println("===================");
                break;
            }

            if (root.player == true) { // human move

                if(hint==1){
                    System.out.print("You have One Hint. press 3 to use and 4 to skip. ");
                    int choice=scanner.nextInt();
                    if (choice==3) {
                        System.out.print("\npick 1 or 2: ");
                        hint(root.numberOfPieces);
                        hint=hint-1;
                    }
                    else if(choice==4){
                        System.out.println("pick 1 or 2: ");
                    }
                }else
                    System.out.println("pick 1 or 2: ");
                    move=scanner.nextInt();
                    while (move>2 || move<1)
                    {
                        System.out.println("invalid choice. please try again.");
                        System.out.println("pick 1 or 2: ");
                        move=scanner.nextInt();
                    }
                if (move == 1) {
                    root=root.left;
                }
                else if (move==2)
                {
                    root=root.right;
                }
                System.out.print("Height of tree is: ");
                System.out.println(root.level);
                print(root.numberOfPieces);
                root.player=false;
                if (root.numberOfPieces == 0) { // human win
                    System.out.println("Congratulation, You win!");
                    System.out.println("===================");
                    break;
                }
            }
            else { // pc move
                if (root.level%2 == 0) {
                    if (root.left.minMaxValue > root.right.minMaxValue) {
                        System.out.println("Computer picks 1");
                        root = root.left;
                    } else {
                        root = root.right;
                        System.out.println("Computer picks 2");
                    }
                }
                else
                {
                    if (root.left.minMaxValue < root.right.minMaxValue) {
                        root = root.left;
                        System.out.println("Computer picks 1");
                    } else {
                        root = root.right;
                        System.out.println("Computer picks 2");
                    }
                }
                System.out.print("Height of tree is: ");
                System.out.println(root.level);
                print(root.numberOfPieces);
                root.player = true;
                if (root.numberOfPieces == 0) { // pc win
                    System.out.println("Computer wins!");
                    System.out.println("===================");
                    break;
                }
            }
        }
    }
    public void easy(GameNode root){
        Scanner scanner = new Scanner(System.in);
        Random random =new Random();
        print(root.numberOfPieces);
        int hint=1;
        int move;
        while (root.numberOfPieces>0){

            if (root.numberOfPieces==1 && root.player==true) // human win
            {
                System.out.println("Congratulation, You win!");
                System.out.println("===================");
                break;
            }

            if (root.numberOfPieces==1 && root.player==false)// pc win
            {
                System.out.println("Computer wins");
                System.out.println("===================");
                break;
            }

            if (root.player==true) { // human move
                if(hint==1){
                    System.out.print("You have One Hint. press 3 to use and 4 to skip. ");
                    int choice=scanner.nextInt();
                    if (choice==3) {
                        System.out.print("\npick 1 or 2: ");
                        hintForNonPerfect(root.numberOfPieces);
                        hint=hint-1;
                    }
                    else if(choice==4){
                        System.out.println("pick 1 or 2: ");
                    }
                }else
                    System.out.println("pick 1 or 2: ");

                move=scanner.nextInt();
                while (move>2 || move<1)
                {
                    System.out.println("invalid choice. please try again.");
                    System.out.println("pick 1 or 2: ");
                    move=scanner.nextInt();
                }
                if (move == 1) {
                    root=root.left;
                }
                else if (move==2)
                {
                    root=root.right;
                }
                print(root.numberOfPieces);
                System.out.print("Height of tree is: ");
                System.out.println(root.level);
                root.player = false;
                if (root.numberOfPieces == 0) { // human win
                    System.out.println("Congratulation, You win!");
                    System.out.println("===================");
                    break;
                }
            }
            else { // pc move
                int number= 1+random.nextInt(2);
                if (number==1) {
                    System.out.println("Computer picks 1");
                    root=root.left;
                }

                else {
                    System.out.println("Computer picks 2");
                    root = root.right;
                }
                print(root.numberOfPieces);
                System.out.print("Height of tree is: ");
                System.out.println(root.level);
                root.player = true;

                if (root.numberOfPieces==0) { // pc win
                    System.out.println("Computer wins");
                    System.out.println("===================");
                    break;
                }
            }
        }
    }

    public void print(int n){ // printing the remaining
        for (int i = 1; i <= n; i++) {
            System.out.print("🏐 ");
            if(i%4==0) {
                System.out.println();
            }
        }
        System.out.println("\n###################################");
        System.out.println("the number of remaining is: "+ n);
    }


    public void somewhatDifficult(GameNode root){
        Scanner scanner = new Scanner(System.in);

        print(root.numberOfPieces);
        int hint=1;
        int move;
        while (root.numberOfPieces>0){

            if (root.numberOfPieces==1 && root.player==true)
            {
                System.out.println("Congratulation, You win!"); // human win
                System.out.println("===================");
                break;
            }

            if (root.numberOfPieces==1 && root.player==false) // pc win
            {
                System.out.println("Computer wins");
                System.out.println("===================");
                break;
            }

            if (root.player==true) // human move
            {
                if(hint==1){
                    System.out.print("You have One Hint. press 3 to use and 4 to skip. ");
                    int choice=scanner.nextInt();
                    if (choice==3) {
                        System.out.print("\npick 1 or 2: ");
                        hintForNonPerfect(root.numberOfPieces);
                        hint=hint-1;
                    }
                    else if(choice==4){
                        System.out.println("pick 1 or 2: ");
                    }
                }else
                    System.out.println("pick 1 or 2: ");
                move=scanner.nextInt();
                while (move>2 || move<1)
                {
                    System.out.println("invalid choice. please try again.");
                    System.out.println("pick 1 or 2: ");
                    move=scanner.nextInt();
                }
                if (move == 1) {
                    root=root.left;
                }
                else if (move==2)
                {
                    root=root.right;
                }
                System.out.print("Height of tree is: ");
                System.out.println(root.level);
                print(root.numberOfPieces);
                root.player=false;
                if (root.numberOfPieces==0) { // human move
                    System.out.println("You win");
                    System.out.println("===================");
                    break;
                }
            }
            else { // pc move
                int number;
                if (root.numberOfPieces==2)
                    number=2;
                else
                    number= (root.numberOfPieces%2==0)? 1:2;
                if (number==1)
                {
                    System.out.print("Height of tree is: ");
                    System.out.println(root.level);
                    System.out.println("Computer picks 1");
                    root=root.left;
                }
                else {
                    System.out.println("Computer picks 2");
                    root=root.right;
                }
                print(root.numberOfPieces);
                root.player=true;

                if (root.numberOfPieces==0) { // pc win
                    System.out.println("Computer wins");
                    System.out.println("===================");
                    break;
                }
            }
        }
    }

    public void hint(int n) {
        if (root.level % 2 == 0) {
            if (root.left.minMaxValue > root.right.minMaxValue || root.left.minMaxValue == root.right.minMaxValue)
                System.out.println("#Hint: Pick 1 ");
            else
                System.out.println("#Hint: Pick 2");
        }
        else {
            if (root.left.minMaxValue < root.right.minMaxValue)
                System.out.println("#Hint: Pick 2 ");
            else
                System.out.println("#Hint: Pick 1");

        }
    }

    public void hintForNonPerfect(int n){
        if (n%2!=0 ||n==2)
            System.out.println("#Hint: Pick 2");
        else
            System.out.println("#Hint: Pick 1");
    }

    public void setMinMaxUsingDFS(){
        postorder();

    }
    public void postorder() {
        postorder(root);
    }
    protected void postorder(GameNode root) {
        
        if (root != null) {
            postorder(root.left);
            postorder(root.right);

            if (root.isTerminate(root.numberOfPieces)) {
                if (root.level % 2 == 0)
                    root.minMaxValue = -1;
                else {
                    root.minMaxValue = 1;
                }
            } else {

                if (root.level % 2 == 0) {
                    if (root.right == null)
                        root.minMaxValue = root.left.minMaxValue;
                    else
                        root.minMaxValue = Math.max(root.left.minMaxValue, root.right.minMaxValue);
                } else {
                    if (root.right == null)
                        root.minMaxValue = root.left.minMaxValue;
                    else
                        root.minMaxValue = Math.min(root.left.minMaxValue, root.right.minMaxValue);
                }
            }
        }
    }
}
