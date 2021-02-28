/**
 * @author Adam Śmieja
 * @version 1.0
 */
package other;

import core.player.PlayerAttributes;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class MyWriter {

    private TreeSet<Double> topScoreSet = new TreeSet<>();
    private ArrayList<String> arrayListPlayerAttributes = new ArrayList<>();

    /**
     * Metoda uloží hráče do souboru a načte nové hodnoty
     */
    public void saveAndLoadPlayer() {
        savePlayer();
        loadPlayer();
    }

    /**
     * metoda uloží hráče do souboru
     */
    private void savePlayer() {
        BufferedWriter writer;
        try {
            writer =
              new BufferedWriter(new FileWriter(Constants.PLAYER_ATTRIBUTES));
            writer.write(
              ((PlayerAttributes.getSpeedX()) + ";") + ((PlayerAttributes
                .getAccountBalance()) + ";") + ((PlayerAttributes
                .getWingManValue()) + ";") + ((PlayerAttributes
                .getNumberOfWingMan()) + ";"));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * metoda načte hráče ze souboru
     */
    public void loadPlayer() {
        arrayListPlayerAttributes.clear();
        Scanner sc = null;
        try {
            sc = new Scanner(new File(Constants.PLAYER_ATTRIBUTES));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            arrayListPlayerAttributes.add(sc.next());
        }
        sc.close();
        PlayerAttributes
          .setSpeedX(Double.parseDouble(arrayListPlayerAttributes.get(0)));
        PlayerAttributes.setAccountBalance(
          Integer.parseInt(arrayListPlayerAttributes.get(1)));
        PlayerAttributes
          .setWingManValue(Integer.parseInt(arrayListPlayerAttributes.get(2)));
        PlayerAttributes.setNumberOfWingMan(
          Integer.parseInt(arrayListPlayerAttributes.get(3)));
    }

    /**
     * načte ze souboru celkové skóre
     * @return celkové skóre
     */
    public Double getLifeTimeScore() {
        double lifeTimeScore = 0;
        Scanner sc = null;
        try {
            sc = new Scanner(new File(Constants.HIGHSCORE_CSV));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            lifeTimeScore += Double.parseDouble(sc.next());
        }
        sc.close();
        return lifeTimeScore;
    }

    /**
     * Načte soubor a vrátí nejvyšší skóre
     * @return nejvyšší skóre
     */
    public Double getHighScore() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(Constants.HIGHSCORE_CSV));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            topScoreSet.add(Double.parseDouble(sc.next()));
        }
        sc.close();
        return topScoreSet.last();
    }

    /**
     * zapíše do souboru skóre
     * @param score nahrané skóre
     */
    public void writeHighScore(String score) {
        score = score + ";";
        BufferedWriter writer;
        try {
            writer =
              new BufferedWriter(new FileWriter(Constants.HIGHSCORE_CSV, true));
            writer.append(score);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * vrátí počet sebraných víl
     * @return počet sebraných víl
     */
    public int getWingManCollected() {
        Scanner sc = null;
        int amount = 0;
        try {
            sc = new Scanner(new File(Constants.WING_MAN_TEXT_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            amount = Integer.parseInt(sc.next());
        }
        sc.close();
        return amount;
    }

    /**
     * zapíše počet sebraných víl
     * @param amount počet sebraných víl
     */
    public void writeWingMan(int amount) {
        amount = getWingManCollected() + amount;
        BufferedWriter writer;
        try {
            writer =
              new BufferedWriter(new FileWriter(Constants.WING_MAN_TEXT_FILE));
            writer.write(String.valueOf(amount));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



