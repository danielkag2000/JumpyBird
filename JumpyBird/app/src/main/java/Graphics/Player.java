package Graphics;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private List<Integer> playerScoreGame;  // list of scores
    private int lastScore;  // the last score

    /*
     * 0 yellow
     * 1 red
     * 2 blue
     * 3 black
     */
    public int[] colorsArr = {0, 0, 0, 0};



    public Player() {
        this.playerScoreGame = new LinkedList<Integer>();
        this.lastScore = -1;
    }

    /**
     * add the score to the stats
     *
     * @param score the score to add
     */
    public void addScore(int score) {
        this.playerScoreGame.add(score);
        this.colorsArr[Game.br.COLOR_BIRD]++;
        //System.out.println("players scores: " + this.playerScoreGame);
    }

    /**
     * get the element of the i game
     *
     * @param index the i game
     * @return the score of the i game
     */
    public int getIscore(int index) {
        return this.playerScoreGame.get(index);
    }

    /**
     * reset the stats of the player
     */
    public void resetPlayersStats() {

        this.playerScoreGame.removeAll(this.playerScoreGame);  // remove the scores

        // remove the use of birds
        for (int i = 0; i < this.colorsArr.length; i++) {
            this.colorsArr[i] = 0;
        }

        this.lastScore = -1;  // set an no new score
    }

    /**
     * get the last score
     *
     * @return the last score
     */
    public int getLastScore() {
        if (!this.playerScoreGame.isEmpty())
            return this.lastScore;
        return -1;
    }

    /**
     * get the number of  time that the player has played
     *
     * @return the number of  time that the player has played
     */
    public int getNumOfPoints() {
        return this.playerScoreGame.size();
    }

    /**
     * set the last score
     *
     * @param score the last score
     */
    public void setLastScore(int score) {
        this.lastScore = score;
    }

    /**
     * get the max score that the player have made.
     *
     * @return the max score
     */
    public int getMaxScore() {
        int max = 0;

        for (int i = 0; i < this.playerScoreGame.size(); i++) {
            if (this.playerScoreGame.get(i) > max) {
                max = this.playerScoreGame.get(i);
            }
        }
        return max;
    }

    /**
     * get the most used bird
     *
     * @return the most used bird
     */
    public int mostUseBird() {
        int max = 0;
        int index = 0;
        for (int i = 0; i < this.colorsArr.length; i++) {
            if (this.colorsArr[i] > max) {
                max = this.colorsArr[i];
                index = i;
            }
        }
        return index;
    }

    /**
     * put score to player
     *
     * @param s in a shape of S1,S2,S3,...,Sn
     */
    public void putScore(String s){
        if(s == "")
            return;

        String[] arr = s.split(",");

        for (int i = 0; i < arr.length; i++){
            this.playerScoreGame.add(Integer.parseInt(arr[i]));
        }
        this.lastScore = -2;
    }

    /**
     * get the score as a String
     *
     * @return String in a shape of S1,...,Sn
     */
    public String getScore(){
        String s = "";

        for (int i = 0; i < this.playerScoreGame.size(); i++){
            if (i == this.playerScoreGame.size() - 1){
                s += this.playerScoreGame.get(i);
            } else {
                s += this.playerScoreGame.get(i) + ",";
            }
        }

        return s;
    }

    /**
     * put score to player
     *
     * @param s in a shape of C1,C2,C3,...,Cn
     */
    public void putBirds(String s){
        if(s == "")
            return;

        String[] arr = s.split(",");

        for (int i = 0; i < this.colorsArr.length; i++){
            this.colorsArr[i] = Integer.parseInt(arr[i]);
        }

    }

    /**
     * get the score as a String
     *
     * @return String in a shape of C1,...,Cn
     */
    public String getBirds(){
        String s = "";

        for (int i = 0; i < this.colorsArr.length; i++){
            if (i == this.colorsArr.length - 1){
                s += this.colorsArr[i];
            } else {
                s += this.colorsArr[i] + ",";
            }
        }

        return s;
    }
}
