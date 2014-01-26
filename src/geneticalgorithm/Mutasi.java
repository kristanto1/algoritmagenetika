package geneticalgorithm;

/*
 * To change this template, choose Tools | 
 * Templates and open the template in the editor.
 */

/**
 *
 * @author Dzulfan Fadli, Kristanto Andreas, Arif Romadhan
 * @NIM : 10113918, 10110688, 10112063
 */


public class Mutasi {
    
    public int randomMutasi(int Mina, int Maxa){
        int randommutasi = Mina + (int) (Math.random() * 
                ((Maxa - Mina) + 1));
        return randommutasi;
    }

    public String[][] mutation(String[] indukA, String[] indukB) {
        
            int Minax = 1, Maxax = 7;
            int randomm = randomMutasi(Minax, Maxax);
            if (indukA[randomm].substring(2, 3).equals("1")) {
                indukA[randomm] = indukA[randomm].substring(0, 2) + "0";
            } else {
                indukA[randomm] = indukA[randomm].substring(0, 2) + "1";
            }
            if (indukB[randomm].substring(2, 3).equals("1")) {
                indukB[randomm] = indukB[randomm].substring(0, 2) + "0";
            } else {
                indukB[randomm] = indukB[randomm].substring(0, 2) + "1";
            }
            String[][] induk = new String[2][8];
            induk[0] = indukA;
            induk[1] = indukB;
            return induk;

    }

    public String[][] changerMutasi(String[] indukA, String[] indukB) {
            int Mina = 0, Maxa = 7;
            int randdommutasi = randomMutasi(Mina, Maxa);
            int randomA = randomMutasi(Mina, Maxa);
            int randomB = randomMutasi(Mina, Maxa);
            String Temp = indukA[randomA].substring(0, 2);
            indukA[randomA] = indukA[randomB].substring(0, 2) + 
                    indukA[randomA].substring(2, 3);
            indukA[randomB] = Temp + indukA[randomB].substring(2, 3);
            String Tempa = indukB[randomA].substring(0, 2);
            indukB[randomA] = indukB[randomB].substring(0, 2) + 
                    indukB[randomA].substring(2, 3);
            indukB[randomB] = Tempa + indukB[randomB].substring(2, 3);
            
            String[][] induk = new String[2][8];
            induk[0] = indukA;
            induk[1] = indukB;
            return induk;
    }
}
