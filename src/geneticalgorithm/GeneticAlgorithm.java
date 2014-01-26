/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.util.Random;

/**
 *
 * @author Dzulfan Fadli, Kristanto Andreas, Arif Romadhan
 * @NIM : 10113918, 10110688, 10112063
 */

public class GeneticAlgorithm {

    static String[] kromosom = new String[6];
    static double[] fitness = new double[6];
    static double[] newFitness = new double[6];

    public static void bubbleSort(double[] list) {
        boolean sorted = false;

        for (int top = list.length - 1; top > 0 && !sorted; top--) {
            sorted = true;

            for (int i = 0; i < top; i++) {
                if (list[i] < list[i + 1]) {
                    sorted = false;
                    double temp = list[i];
                    String tempo = kromosom[i];
                    list[i] = list[i + 1];
                    kromosom[i] = kromosom[i + 1];
                    list[i + 1] = temp;
                    kromosom[i + 1] = tempo;
                }
            }
        }
    }

    public String main() {
        StringBuilder sb = new StringBuilder();
        // TODO code application logic here
        System.out.println("Sistem GA Pemotongan Bahan");
        Kromossom krm = new Kromossom();
        //generate 4 individu random awal
        for (int i = 0; i < 4; i++) {
            kromosom[i] = krm.GenerateIndividu();
        }
        //menhitung fitness
        for (int kort = 0; kort < 4; kort++) {
            fitness[kort] = krm.hitungfitness(kromosom[kort]);
        }
        System.out.println("");
        //sorting generasi pertama
        bubbleSort(fitness);
        System.out.println("Populasi Generasi ke:1 ***************************************************");
        sb.append("===================== Populasi Generasi ke:1 =====================\n");
        for (int kort = 0; kort < 4; kort++) {
            System.out.println("kromosom " + (kort + 1) + "=" + kromosom[kort] + " fitness =" + fitness[kort]);
            sb.append("kromosom " + (kort + 1) + "=" + kromosom[kort] + " fitness =" + fitness[kort] + "\n");
        }
        int round = 2;
        while (round < 2000 && fitness[0] < 0.96) {
            Mutasi mutasi = new Mutasi();
            //selection, fitness dari kromosom 1 dan 2 diambil untuk dilakukan mating, 
            //karena kromosom 1 dan 2 memiliki fitness tertinggi
            int Minp = 1, Maxp = 3;
            int randompasangan = mutasi.randomMutasi(Minp, Maxp);
            CrossOver CO = new CrossOver();
            String[][] induk = CO.crossOver(kromosom[0], kromosom[randompasangan]);
            if (round == 2) {


                kromosom[4] = induk[0][0];
                kromosom[5] = induk[0][1];
                for (int i = 1; i < 8; i++) {
                    kromosom[4] = kromosom[4] + "-" + induk[0][i];
                    kromosom[5] = kromosom[5] + "-" + induk[1][i];
                }
                sb.append("\nInduk A=" + kromosom[4] + " Induk B=" + kromosom[5]+"\n");
            }
            //Mutasi
            mutasi.mutation(induk[0], induk[1]);
            int Mina = 1, Maxa = 3;
            int randommutasi = mutasi.randomMutasi(Mina, Maxa);
            if (randommutasi == 2) {
                induk = mutasi.mutation(induk[0], induk[1]);
            }
            //changer mutasi
            int Minaj = 1, Maxaj = 5;
            int randommutasii = mutasi.randomMutasi(Minaj, Maxaj);
            if (randommutasii == 3) {
                induk = mutasi.changerMutasi(induk[0], induk[1]);
            }
            kromosom[4] = induk[0][0];
            kromosom[5] = induk[0][1];
            for (int i = 1; i < 8; i++) {
                kromosom[4] = kromosom[4] + "-" + induk[0][i];
                kromosom[5] = kromosom[5] + "-" + induk[1][i];
            }

            fitness[4] = krm.hitungfitness(kromosom[4]);
            fitness[5] = krm.hitungfitness(kromosom[5]);
            bubbleSort(fitness);
            System.out.println("populasi generasi ke :" + round + " ***************************************************");
            sb.append("===================== Populasi Generasi ke:" + round + " =====================\n");
            for (int kort = 0; kort < 6; kort++) {
                System.out.println("kromosom " + (kort + 1) + "=" + kromosom[kort] + " fitness =" + fitness[kort]);
                sb.append("kromosom " + (kort + 1) + "=" + kromosom[kort] + " fitness =" + fitness[kort] + "\n");
            }
            if (fitness[0] > 0.96) {
                System.out.println("KROMOSOM TERBAIK(OPTIMUM) DITEMUKAN !!");
                System.out.println("Kromosom =" + kromosom[0]);
                System.out.println("Fitness =" + fitness[0]);
                System.out.println("******************************************************************************");
                sb.append("\n\n===================== KROMOSOM TERBAIK(OPTIMUM) DITEMUKAN !! =====================\n");
                sb.append("Kromosom =" + kromosom[0] + "\n");
                sb.append("Fitness =" + fitness[0] + "\n");
            } else {
                System.out.println("Kromosom Terbaik = " + kromosom[0] + " dengan fitness=" + fitness[0]);
                System.out.println("******************************************************************************");
                sb.append("\nKromosom Terbaik = " + kromosom[0] + " dengan fitness=" + fitness[0] + "\n");
            }
            round++;
        }
        return sb.toString();

    }
}
