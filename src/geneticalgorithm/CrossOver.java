/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

/**
 *
 * @author Dzulfan Fadli, Kristanto Andreas, Arif Romadhan
 * @NIM : 10113918, 10110688, 10112063
 */

public class CrossOver {

    public String[] splitInduk(String kromosom) {
        String[] induk = kromosom.split("-");
        return induk;
    }

    public String[][] crossOver(String kromosom1, String kromosom2) {
        String[] indukB = splitInduk(kromosom2);
        String[] indukA = splitInduk(kromosom1);
        System.out.println("------------------------------------");
        System.out.println("Induk A=" + kromosom1 + " Induk B=" + kromosom2);
        int Min = 1, Max = 7;
        int randompotong = Min + (int) (Math.random() * ((Max - Min) + 1));
        for (int i = 0; i < randompotong; i++) {
            String Temp = indukA[i].substring(2, 3);
            indukA[i] = indukA[i].substring(0, 2) + indukB[i].substring(2, 3);
            indukB[i] = indukB[i].substring(0, 2) + Temp;
        }
        String[][] induk = new String[2][1];
        induk[0] = indukA;
        induk[1] = indukB;
        return induk;
    }
}
