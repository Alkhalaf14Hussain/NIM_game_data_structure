public class GameNode {
    int numberOfPieces;
    boolean player;
    int level;
    public GameNode left,right;
    int minMaxValue;

    public GameNode(int numberOfPieces){
        this.numberOfPieces=numberOfPieces;
    }
    public GameNode(int numberOfPieces,int level){
        this.numberOfPieces=numberOfPieces;
        this.level=level;
    }

    public GameNode(){
        level=0;
        minMaxValue=0;
        numberOfPieces=0;
    }

    public boolean isTerminate (int numberOfPieces) {
        if ((numberOfPieces == 0 )) { // End of the game
            return true;
    }
        return false;
    }


}
