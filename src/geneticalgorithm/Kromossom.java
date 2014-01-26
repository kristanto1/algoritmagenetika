/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Dzulfan Fadli, Kristanto Andreas, Arif Romadhan
 * @NIM : 10113918, 10110688, 10112063
 */

public class Kromossom {

    //Generasi pertama
    //2awalbit menentukan jenis. 1bit menentukan posisi
    //1= horizontal, 0 = vertikal
    private int panjangbahan;
    private int lebarbahan;
    private int[][] jenis = new int[4][3];
    private String[] bahan = new String[3];
    private String[] individuRandom = new String[8];
    private String[] garis = new String[2];
    private int lg,pg;
    private int diterima;
    private int tempPanjang;

    public Kromossom() {
        jenis[1][1] = 5;
        jenis[1][2] = 5;

        jenis[2][1] = 4;
        jenis[2][2] = 6;

        jenis[3][1] = 2;
        jenis[3][2] = 6;

        panjangbahan = 11;
        lebarbahan = 15;

        bahan[0] = "11";
        bahan[1] = "10";
        bahan[2] = "01";

        garis[0] = "0";
        garis[1] = "1";

    }

    public static String getRandom(String[] individu2) {
        int rnd = new Random().nextInt(individu2.length);
        return individu2[rnd];
    }

    //instansiasi nilai panjang dan lebar bahan
    public String GenerateIndividu() {

        int jum1 = 0;
        int jum2 = 0;
        int jum3 = 0;
        int i = 0;
        String ind;

        while (i < 8) {
            ind = getRandom(bahan);

            if ((ind.equals(bahan[0])) && (jum1 < 3)) {
                jum1++;
                individuRandom[i] = ind;
                i++;
            } else if ((ind.equals(bahan[1])) && (jum2 < 2)) {
                jum2++;
                individuRandom[i] = ind;
                i++;
            } else if ((ind.equals(bahan[2])) && (jum3 < 3)) {
                jum3++;
                individuRandom[i] = ind;
                i++;
            }
        }
        ind = "";
        for (int j = 0; j < individuRandom.length; j++) {

            if (j == individuRandom.length - 1) {
                ind = ind + individuRandom[j] + getRandom(garis);
            } else {
                ind = ind + individuRandom[j] + getRandom(garis) + "-";
            }
        }
        return ind;
    }

    public double hitungfitness(String s) {

        System.out.println("=================================");
        System.out.println("kromosom =" + s);
        //Membagi kromosom menjadi 8 bagian
        String[] kromosom_split = s.split("-");

        int lebar = 0, panjang = 0;
        diterima = 0;
        int[] posisiPL = new int[2];

        for (int i = 0; i < 8; i++) {
            posisiPL = getPosisi(kromosom_split[i]);
            lebar = posisiPL[0];
            panjang = posisiPL[1];
            diterima = luasPermintaan(lebar, panjang, i);
            double fit = diterima / (((panjangbahan * lebarbahan)) + 0.0000001);
            System.out.println("fitness=" + fit);
        }
        return diterima / (((panjangbahan * lebarbahan)) + 0.0000001);
    }
    public int luasPermintaan(int newL, int newP, int i) {
        int[] l = new int[11];
        int[] p = new int[11];
        boolean status=true;
        if (i == 0) {
            diterima = 0;
            lg = 0;
            pg = 0;
            tempPanjang = 0;
        }
        if ((newL + lg) > lebarbahan) {
            if (pg+newP<=panjangbahan) {
                tempPanjang = pg;
                pg += newP;
                lg=newL;
            }else{
                status=false;
            }
        } else {
           if((newP+tempPanjang)>pg){
                pg =newP+tempPanjang;
           }
           if (pg<newP) {
                pg = newP;
           }
           lg += newL;
        }
        if (status) {
            diterima += (newP * newL);
        }
        System.out.print("l=" + newL);
        System.out.print(" p=" + newP);
        System.out.print(" lg=" + lg);
        System.out.print(" pg=" + pg);
        System.out.println(" diterima=" + diterima);
        return diterima;

    }
    public int[] getPosisi(String posisi) {
        int PanjangLebar[] = new int[2];
        if (posisi.substring(0, 2).equals("01")) {
            if (posisi.substring(2, 3).equals("1")) {
                PanjangLebar[0] = jenis[1][2];
                PanjangLebar[1] = jenis[1][1];
            } else {
                PanjangLebar[0] = jenis[1][1];
                PanjangLebar[1] = jenis[1][2];
            }
        } else if (posisi.substring(0, 2).equals("10")) {
            if (posisi.substring(2, 3).equals("1")) {
                PanjangLebar[0] = jenis[2][2];
                PanjangLebar[1] = jenis[2][1];
            } else {
                PanjangLebar[0] = jenis[2][1];
                PanjangLebar[1] = jenis[2][2];
            }
        } else if (posisi.substring(0, 2).equals("11")) {
            if (posisi.substring(2, 3).equals("1")) {
                PanjangLebar[0] = jenis[3][2];
                PanjangLebar[1] = jenis[3][1];
            } else {
                PanjangLebar[0] = jenis[3][1];
                PanjangLebar[1] = jenis[3][2];
            }
        }
        return PanjangLebar;
    }
}
