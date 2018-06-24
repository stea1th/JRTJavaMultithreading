package com.javarush.task.task35.task3513;


public class MoveEfficiency implements Comparable<MoveEfficiency>{

    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        //return this.numberOfEmptyTiles - o.numberOfEmptyTiles == 0? this.score - o.score : this.numberOfEmptyTiles - o.numberOfEmptyTiles;
        int x = Integer.compare(this.numberOfEmptyTiles, o.numberOfEmptyTiles);
        if(x==0){
            x = Integer.compare(this.score, o.score);
        }
        /*if(this.numberOfEmptyTiles - o.numberOfEmptyTiles==0){
            return this.score -o.score;
        }else
            return this.numberOfEmptyTiles-o.numberOfEmptyTiles;*/
        return x;
    }
}
