package com.huffman;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //200 200 200 200 8 6 4 2

//        20 20 20 20 8 6 4 2
        HuffmanAlgo Huff = new HuffmanAlgo(getProbability());
        Huff.printAns();
//        Huff.startProcess();
//        System.out.print("Probabilities: ");
//        for(HuffmanCode i: Huff.getHufCode()){
//            System.out.print(i.getProb()+" ");
//        }
//        System.out.println();
//        System.out.print("Encode:  ");
//        for(HuffmanCode i : Huff.getHufCode()){
//            System.out.print(i.getEncode()+" ");
//        }

    }
    public static ArrayList getProbability()
    {
        ArrayList<Integer> res;
        try (Scanner in = new Scanner(System.in)) {
            res = new ArrayList<>();
            System.out.print("Enter probabilities: ");
            while (!in.hasNextLine());
            {
                //input.addAll(Array.asList(   in.nextLine().split("\\s+")));
                String InputStr = in.nextLine();
                int line = InputStr.split("\\s+").length;
                String[] str = new String[line];


                str = InputStr.split("\\s+");
                //System.out.println(line);
                for (String s: str)
                {
                    res.add(Integer.parseInt(s));
                    //System.out.println(s);

                }
                //input.addAll(in.nextLine().split("\\s+"));

            }
        }
        return res;
    }
}
