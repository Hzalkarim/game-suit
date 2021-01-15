package kuliah.esqsot.mobileprogram.gamesuit;

import java.util.Random;

public class GameSuitController {

    private Random r = new Random();
    private String[][] winRules;

    public GameSuitController(){
        winRules = new String[][]{
                {"Gunting", "Kertas"},
                {"Batu", "Gunting"},
                {"Kertas", "Batu"}
        };
    }

    public String duel(String player, String lawan){
        if (player.equals(lawan)) {
            return "Seri";
        } else {
            for (int i = 0; i < winRules.length; i++) {
                if (winRules[i][0].equals(player) && winRules[i][1].equals(lawan))
                    return  "Menang";
            }
            return "Kalah";
        }
    }

    public String generateLawan(){
        int i = r.nextInt(3);
        String hasil = "haha";
        switch (i){
            case 0:
                hasil = "Gunting";
                break;
            case 1:
                hasil = "Batu";
                break;
            case 2:
                hasil = "Kertas";
                break;
        }
        return hasil;
    }

    public String generateInstruction(){
        int i = r.nextInt(3);
        String hasil = "haha";
        switch (i){
            case 0:
                hasil = "Menang";
                break;
            case 1:
                hasil = "Kalah";
                break;
            case 2:
                hasil = "Seri";
                break;
        }
        return hasil;
    }

    public Boolean testDuel(String player, String lawan, String instruction){
        return duel(player, lawan).equals(instruction);
    }
}
