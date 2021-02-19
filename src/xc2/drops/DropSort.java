/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xc2.drops;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author pyro2
 */
public class DropSort extends ArrayFill {

    public int[] ID = new int[501];
    public ArrayList<String[]> Ref;
    public ArrayList<String[]> ItemId = new ArrayList<String[]>();
    public ArrayList<int[]> DropProb = new ArrayList<int[]>();
    public ArrayList list = new ArrayList();

    public DropSort() {
        ID = ID();
        Ref = ref();
        ItemId.add(I1());
        ItemId.add(I2());
        ItemId.add(I3());
        ItemId.add(I4());
        ItemId.add(I5());
        ItemId.add(I6());
        ItemId.add(I7());
        ItemId.add(I8());
        DropProb.add(D1());
        DropProb.add(D2());
        DropProb.add(D3());
        DropProb.add(D4());
        DropProb.add(D5());
        DropProb.add(D6());
        DropProb.add(D7());
        DropProb.add(D8());
    }
    public void Items(String name) {
        for(int arr =0;0< ItemId.size();arr++){
            
        }
        
        
    }

    public void FullRow(int num) {
        list.clear();
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = Ref.get(num-1);
        ArrayList<ArrayList<Integer>> Llist = new ArrayList<ArrayList<Integer>>();

        for (int x = 0; x < row.length; x++) {
            String name = row[x];
            ArrayList<Integer> lname = Occur(name);
            Llist.add(lname);
        }
        ArrayList<String> frow = new ArrayList<String>();
        ArrayList<Integer> com = new ArrayList<Integer>(); //Num sorting variable

        for (int x = 0; x < Llist.size(); x++) {
            frow.add(row[x]);
            com.add(x);
        }
        for (int x = 0; x < Llist.size(); x++) {//sorting
            ArrayList<Integer> temp = Llist.get(x);//sort varible 1
            for (int z = 0; z < Llist.size(); z++) {

                if (x == z) {

                } else if (temp.equals(Llist.get(z)) == true) {
                    Collections.replaceAll(com, x, z);
                }
            }
        }

        ArrayList<ArrayList<Integer>> tlist = Llist;

        for (int x = 0; x < Llist.size(); x++) {
            StringBuilder sb1 = new StringBuilder();
            sb1.append(row[x]);
            for (int z = 0; z < Llist.size(); z++) {
                if (x == z) {
                } else if (com.get(x) == com.get(z)) {
                    sb1.append(", " + row[z]);
                }
            }
            String name1 = sb1.toString();
            Collections.replaceAll(frow, row[x], name1);
        }

        for (int x = 0; x < com.size(); x++) { //check repeats
            int ck1 = com.get(x);
            for (int z = com.size() - 1; z > 0; z--) {
                int ck2 = com.get(z);
                if (x == z) {
                } else if (ck1 == ck2) {
                    frow.remove(z);
                    com.remove(z);
                }

            }
        }

        for (int x = 0; x < frow.size(); x++) {
            String str = frow.get(x);
            int num1 = com.get(x);
        }
        for (int x = 0; x < com.size(); x++) {
            String b = row[com.get(x)];
            Searchlist(b, frow.get(x));
        }

    }

    public void basiclist(int num) {
        list.clear();
        String[] a = Ref.get(num);
        String name = "";
        for (int x = 0; x < a.length; x++) {
            name += a[x] + ", ";
        }
        list.add(name);
        ArrayList<String> item = ItemGet(num);
        list.add(item);
        ArrayList<String> type = TypeGet(item);
        list.add(type);
        ArrayList<Integer> rare = RarityGet(type, item);
        list.add(rare);
        ArrayList rate = RateGet(type, num);
        list.add(rate);
        Print(item);
    }

    public void Searchlist(String drops, String name) {
        list.clear();
        ArrayList<Integer> num = new ArrayList<Integer>();
        num = Occur(drops);

        ArrayList Tlist = new ArrayList(); //populate MasterList
        for (int x = 0; x < num.size(); x++) {
            ArrayList Llist = new ArrayList();
            Llist.clear();
            int n = num.get(x);
            Llist = numlist(n);
            Tlist.add(Llist);
        }

        int size = 0;
        for (int x = 0; x < Tlist.size(); x++) {
            ArrayList Llist = (ArrayList) Tlist.get(x); //get size
            ArrayList<String> get = (ArrayList<String>) Llist.get(0);
            size += get.size();
        }

        ArrayList<String> item = new ArrayList<String>();  //variables
        ArrayList<String> type = new ArrayList<String>();
        int typeN = 0;
        ArrayList<Integer> rare = new ArrayList<Integer>();
        int rareN = 0;
        ArrayList rate = new ArrayList();
        int rateN = 0;

        for (int x = 0; x < Tlist.size(); x++) {//populate varibles

            ArrayList Llist = (ArrayList) Tlist.get(x);

            ArrayList<String> item1 = (ArrayList<String>) Llist.get(0);//populate Items
            for (int a = 0; a < size; a++) {
                if (a >= item1.size()) {
                    a = 50;
                } else {
                    item.add(item1.get(a));
                }
            }
            ArrayList<String> type1 = (ArrayList<String>) Llist.get(1); //populate Types
            for (int a = 0; a < size; a++) {
                if (a >= type1.size()) {
                    a = 50;
                    typeN = 0;
                } else {
                    type.add(type1.get(a));
                    typeN++;
                }
            }

            ArrayList<Integer> rare1 = (ArrayList<Integer>) Llist.get(2); //populate rarities
            for (int a = 0; a < size; a++) {
                if (a >= rare1.size()) {

                } else {
                    rare.add(rare1.get(a));
                    rareN++;
                }
            }

            ArrayList rate1 = (ArrayList) Llist.get(3); //Populate rates
            for (int a = 0; a < size; a++) {
                if (a >= rate1.size()) {

                } else {
                    rate.add(rate1.get(a));
                    rateN++;
                }
            }
        }

        list.add(name);
        list.add(item);
        list.add(type);
        list.add(rare);
        list.add(rate);
        Print(item);
    }

    public ArrayList<Integer> Occur(String name) {
        ArrayList<Integer> num = new ArrayList<Integer>();
        for (int x = 0; x < Ref.size(); x++) { //reference occurences 
            String[] b = Ref.get(x);
            for (int a = 0; a < b.length; a++) {
                if (b[a].equals(name)) {
                    num.add(ID[x]);
                }
            }
        }

        for (int x = 0; x < num.size(); x++) { //check repeats
            int ck1 = num.get(x);
            for (int z = num.size() - 1; z > 0; z--) {
                int ck2 = num.get(z);
                if (x == z) {

                } else if (ck1 == ck2) {
                    num.remove(z);
                }
            }
        }
        return num;
    }

    public ArrayList numlist(int num) {
        ArrayList Llist = new ArrayList();
        Llist.clear();
        String[] a = Ref.get(num);
        ArrayList<String> item = ItemGet(num);
        Llist.add(item);
        ArrayList<String> type = TypeGet(item);
        Llist.add(type);
        ArrayList<Integer> rare = RarityGet(type, item);
        Llist.add(rare);
        ArrayList rate = RateGet(type, num);
        Llist.add(rate);
        return Llist;
    }

    public ArrayList<String> ItemGet(int num) {
        ArrayList<String> get = new ArrayList<String>();
        for (int x = 0; x < ItemId.size(); x++) {
            String[] item = (String[]) ItemId.get(x);
            if (item[num] == " " || item[num] == null) {
                x = ItemId.size();
            } else {
                get.add(item[num]);
            }
        }
        return get;
    }

    public ArrayList<String> TypeGet(ArrayList<String> item) {
        ArrayList<String> get = new ArrayList<String>(item.size());
        for (int x = 0; x < item.size(); x++) {
            String ob = item.get(x);
            if (ob.endsWith(" I") == true || ob.endsWith(" II") == true || ob.endsWith(" III") == true || ob.endsWith(" IV") == true || ob.endsWith(" V") == true ) {
                get.add("Aux Core");
            } else if (ob.contains("Chip") == true) {
                get.add("Core Chip");
            } else if (ob.contains("Core") == true) {
                get.add("Core Crystal");
            } else if (ob.endsWith("meat") == true || ob.endsWith(" Extract") == true || ob.endsWith(" Wing") == true || ob.endsWith(" Stinger") == true || ob.endsWith(" Tirkin Spear") == true || ob.endsWith(" Arrowhead") == true || ob.endsWith(" Pearl") == true || ob.endsWith(" Mane") == true || ob.endsWith(" Tail") == true || ob.endsWith(" Carapace") == true ) {
                get.add("Collectible (TTGC) |Enemy Part");
            }else if (ob.endsWith(" Braid") == true || ob.endsWith(" Greatsword") == true || ob.endsWith(" Sword") == true) {
                get.add("Weapons (XC2)|Weapon");
            } else {
                get.add("Accessory");
            }
        }
        return get;
    }

    public ArrayList<Integer> RarityGet(ArrayList<String> type, ArrayList<String> item) { //get rarities
        ArrayList<Integer> get = new ArrayList<Integer>(type.size());
        for (int x = 0; x < type.size(); x++) {
            String ob = item.get(x);

            if (type.get(x).equals("Aux Core")) {//aux core
                if (ob.endsWith(" VI") == true || ob.endsWith(" V") == true) {
                    get.add(3);
                } else if (ob.endsWith(" III") == true || ob.endsWith(" IV") == true) {
                    get.add(2);
                } else if (ob.endsWith(" I") == true || ob.endsWith(" II") == true) {
                    get.add(1);
                } else {
                    get.add(0);
                    System.out.println("Aux error");
                }

            } else if (type.get(x).equals("Core Crystal")) {
                if (ob.contains("Common") == true) {
                    get.add(1);
                } else if (ob.contains("Rare") == true) {
                    get.add(2);
                } else if (ob.contains("Legendary") == true) {
                    get.add(3);
                } else {
                    get.add(0);
                    System.out.println("Crystal error");
                }

            }else if (type.get(x).equals("Accessory")) {
                int occur = intOccur(item.get(x),item);
                if(occur == 1){
                    get.add(1);
                }else{
                    get.add(0);
                }
            }else if (type.get(x).equals("Collectible (TTGC) |Enemy Part")) {
                   get.add(1);
            }else if (type.get(x).equals("Weapons (XC2)|Weapon")) {
                   get.add(0);
            }else {
                get.add(0);
            }
        }
        get.equals(Arare(type, item, get));
        return get;
    }

    public ArrayList<Integer> Arare(ArrayList<String> type, ArrayList<String> item, ArrayList<Integer> rare) {
        ArrayList<Integer> like = new ArrayList<Integer>();
        for (int x = 0; x < rare.size(); x++) {
            if (rare.get(x).equals(0)) {
                like.add(x);
            } else {
                like.add(rare.size() + 1);
            }
        }

        for (int x = 0; x < rare.size(); x++) {
            String it1 = item.get(x);
            if (rare.get(x) == 0) {
                if (type.get(x).equals("Accessory")) {
                    for (int z = 0; z < rare.size(); z++) {
                        String it2 = item.get(z);
                        if (it1.equals(it2)) {
                            like.set(x, z);
                        }
                    }

                }
            }
        }
        for (int x = 0; x < like.size(); x++) {
            int num = 2;
            int ck1 = like.get(x);
            int max = like.size() + 1;
            if (ck1 == max) {
            } else {
                if (type.get(x).equals("Accessory")) {
                    for (int z = 0; z < like.size(); z++) {
                        int ck2 = like.get(z);
                         if (ck1 == ck2) {
                            rare.set(z, num);
                            num++;
                        }
                    }
                }
            }
        }
        return rare;
    }

    public int intOccur(String name, ArrayList<String> item) {
        int num = 0;
        for (int x = 0; x < item.size(); x++) { //reference occurences 
            String b = item.get(x);
            if (b.equals(name)) {
                num++;
            }
        }
        return num;
    }

    public ArrayList RateGet(ArrayList<String> type, int num) { //get rates for index num
        ArrayList get = new ArrayList(type.size());
        for (int x = 0; x < type.size(); x++) {
            int[] item = (int[]) DropProb.get(x);
            double b = item[num];
            double n = b / 10;
            if (item[num] == 0) {
                x = ItemId.size();
            } else {
                get.add(n);
            }
        }

        return get;
    }

    public void Print(ArrayList<String> item) { //print results
        ArrayList<String> item1 = (ArrayList<String>) list.get(1);
        ArrayList<String> type1 = (ArrayList<String>) list.get(2);
        ArrayList<Integer> rarity1 = (ArrayList<Integer>) list.get(3);
        ArrayList rate1 = (ArrayList) list.get(4);
        System.out.println(list.get(0));
        System.out.println("==Drops==");
        System.out.println("{{XC2 Enemy drops");
        for (int x = 0; x < item.size(); x++) {
            System.out.println("|item" + (x + 1) + "   = [[" + item1.get(x) + "]]");
            System.out.println("|type" + (x + 1) + "   = [[" + type1.get(x) + "]]");
            System.out.println("|rarity" + (x + 1) + "   = " + rarity1.get(x));
            System.out.println("|rate" + (x + 1) + "   = " + rate1.get(x) + "%");
            System.out.println();
        }
        System.out.println("}}");
        System.out.println();
    }
}
