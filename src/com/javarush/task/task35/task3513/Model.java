package com.javarush.task.task35.task3513;

import java.util.*;
import java.util.stream.IntStream;

public class Model {

    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;  //не забудь убрать static!!!!!
    protected int score = 0;
    protected int maxTile = 2;
    private Stack<Tile[][]> previousStates = new Stack<Tile[][]>();
    private Stack<Integer> previousScores = new Stack<Integer>();
    private boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        /*IntStream.range(0, FIELD_WIDTH).forEach(
                i-> {
                    IntStream.range(0, FIELD_WIDTH).forEach(
                            j-> gameTiles[j][i] = new Tile()
                    );
                }
        );*/
        addTile();
        addTile();
    }

    public void autoMove(){
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
        queue.offer(getMoveEfficiency(this::left));
        queue.offer(getMoveEfficiency(this::up));
        queue.offer(getMoveEfficiency(this::down));
        queue.offer(getMoveEfficiency(this::right));

        queue.poll().getMove().move();

    }

    public boolean hasBoardChanged(){
        return getSumValue(gameTiles)!=getSumValue(previousStates.peek());
    }

    private int getSumValue(Tile[][] tiles){
        int sum = 0;
        for (int i = 0; i <tiles.length ; i++) {
            for (int j = 0; j <tiles[i].length ; j++) {
                sum+= tiles[i][j].value;
            }
        }
        return sum;
    }

    public MoveEfficiency getMoveEfficiency(Move move){
        int numberOfEmptyTiles = -1;
        int scoreX = 0;
        move.move();
        if(hasBoardChanged()){
            numberOfEmptyTiles = getEmptyTiles().size();
            scoreX = score;
        }
        rollback();
        return new MoveEfficiency(numberOfEmptyTiles, scoreX, move);
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() > 0) {
            emptyTiles.get((int) (Math.random() * emptyTiles.size())).value = (Math.random() < 0.9) ? 2 : 4;
        }

    }

    private List< Tile> getEmptyTiles() {
        List<Tile> tiles = new ArrayList<>();
        IntStream.range(0, FIELD_WIDTH).forEach((i) -> {
            Arrays.stream(gameTiles[i])
                    .filter(Tile::isEmpty)
                    .forEach(tiles::add);
        });
        return tiles;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean x = false;
        for (int j = tiles.length - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (tiles[i].isEmpty() && !tiles[i + 1].isEmpty()) {
                    tiles[i].value = tiles[i + 1].value;
                    tiles[i + 1].value = 0;
                    x = true;
                }
            }
        }
        return x;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean x = false;
        for (int i = 1; i < tiles.length; i++) {
            if (tiles[i - 1].value == tiles[i].value && !tiles[i - 1].isEmpty() && !tiles[i].isEmpty()) {
                tiles[i - 1].value *= 2;
                tiles[i].value = 0;
                if (tiles[i - 1].value > maxTile) {
                    maxTile = tiles[i - 1].value;
                }
                score += tiles[i - 1].value;
                x = true;
            }
        }

        return x;
    }

    public void left() {
        if(isSaveNeeded){
            saveState(gameTiles);
            
        }
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (mergeTiles(gameTiles[i]) | compressTiles(gameTiles[i])) {
                isChanged = true;
            }
        }
        if (isChanged) {
            addTile();
        }
        isSaveNeeded = true;
    }

    public void up() {
        saveState(gameTiles);
        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }

    public void down() {
        saveState(gameTiles);
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    public void right() {
        saveState(gameTiles);
        rotate();
        rotate();
        left();
        rotate();
        rotate();

    }
    
    public void randomMove(){
        int n = (int)(Math.random()*4);
        switch(n){
            case 0 :
                left();
                break;
            case 1 :
                up();
                break;
            case 2 :
                right();
                break;
            case 3 :
                down();
                break;
        }
    }

    private void rotate() {
        Tile[][] tempTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        //int y = FIELD_WIDTH - 1;
        for (int i = 0; i < tempTiles.length; i++) {
            int y = FIELD_WIDTH - 1;
            for (int j = 0; j < tempTiles[i].length; j++) {
                tempTiles[i][j] = gameTiles[y][i];
                //System.out.println(gameTiles[y][i]);
                y--;
            }
            //y--;
        }
        gameTiles = tempTiles;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        boolean h = proof();
        //System.out.println(h);
        rotate();
        boolean v = proof();
        //System.out.println(v);
        rotate();
        rotate();
        rotate();
        return h || v || !getEmptyTiles().isEmpty();
    }

    private boolean proof() {
        boolean x = false;
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length - 1; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j + 1].value) {
                    x = true;
                    break;
                }
            }
        }
        return x;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] tilesToSave = new Tile[tiles.length][tiles[0].length];
        isSaveNeeded = false;
        
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tilesToSave[i][j] = new Tile(tiles[i][j].value);
            }

        }
        previousStates.push(tilesToSave);
        int x = score;
        previousScores.push(x);
        
    }

    public void rollback() {
        if (!previousStates.isEmpty() && !previousScores.isEmpty()) {
            gameTiles =  previousStates.pop();
            score = previousScores.pop();
            //isSaveNeeded = true;
        }
    }

    /*public static void print(Tile[][] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                System.out.print(tiles[i][j].value + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        gameTiles = new Tile[][]{{new Tile(4), new Tile(4), new Tile(2), new Tile(4)},
        {new Tile(8), new Tile(2), new Tile(4), new Tile(2)},
        {new Tile(2), new Tile(4), new Tile(2), new Tile(4)},
        {new Tile(4), new Tile(2), new Tile(4), new Tile(2)}};
        print(gameTiles);

        Model model = new Model();
        //model.compressTiles(tiles);
        //model.mergeTiles(tiles);
        //model.left();
        System.out.println("----------------------------");
        model.canMove();
        model.up();
        //System.out.println(model.canMove());
        print(gameTiles);
        /*System.out.println("----------------------------");
        model.left();
        print(gameTiles);
        System.out.println("----------------------------");
        model.right();
        print(gameTiles);
        /*System.out.println("----------------------------");
        model.rotate();
        print(gameTiles);
        System.out.println("----------------------------");
        model.rotate();
        print(gameTiles);
        System.out.println("----------------------------");
        model.rotate();
        print(gameTiles);*/
    //model.compressTiles(tiles);
    //print(tiles);
    //model.mergeTiles(tiles);
    //print(tiles);
    //model.mergeTiles(tiles);
    //print(tiles);
    //}
}

