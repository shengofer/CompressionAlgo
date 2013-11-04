package com.huffman;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 10/6/13
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class HuffmanAlgo {
    private Tree tree;
    private ArrayList<Integer> prob;
    private ArrayList<HuffmanCode> hufCode = new ArrayList<>();


    public HuffmanAlgo(ArrayList<Integer> prob)
    {
        this.prob = new ArrayList<>(sortArrayListbyDescending(prob));
        tree = new Tree(this.prob);
        tree.buildTree();
    }

    public ArrayList<HuffmanCode> getHufCode() {
        return hufCode;
    }



    public void getSymbolCode(Tree.Node Nod,StringBuilder s)
    {

         if(Nod.getLeftchild()!=null)
         {
             s.append("0");
             getSymbolCode(Nod.getLeftchild(),s);
             s.deleteCharAt(s.length()-1);
         }

        if(Nod.getRightchild()!=null)
        {
            s.append("1");
            getSymbolCode(Nod.getRightchild(),s);
            s.deleteCharAt(s.length()-1);
        }

        if(Nod.getRightchild()==null && Nod.getLeftchild()==null)
        {
            HuffmanCode HC = new HuffmanCode(Nod.getProbability(),Nod.getSymbol(),s.toString());
            this.hufCode.add(HC);

            return;
        }

    }

//    public void getSymbolCode(Tree.Node Nod,StringBuilder s)
//    {
//        //Tree.Node Noderoot = tree.getRoot();
//        if (Nod.getSymbol()!=0)
//        {
//
//            //this.hufCode.add(new HuffmanCode(Nod.getProbability(),Nod.getSymbol(),s));
//            HuffmanCode HC = new HuffmanCode(Nod.getProbability(),Nod.getSymbol(),s.toString());
//            this.hufCode.add(HC);
//          //  s.deleteCharAt(s.length()-1);
//          //  return;
//        }
//        if(Nod.getRightchild()==null && Nod.getLeftchild()==null)
//        {
//         //   s.deleteCharAt(s.length()-1);
//            s.delete(0,s.length()-1);
//            return;
//        }
//
//        if(Nod.getRightchild()!=null)
//        {
//            s.append("1");
//            getSymbolCode(Nod.getRightchild(),s);
//        }
//
//        if(Nod.getLeftchild()!=null)
//        {
//            s.append("0");
//            getSymbolCode(Nod.getLeftchild(),s);
//        }



//        while(Noderoot.getSymbol()!=symbol)
//        {
//            if(Noderoot.getLeftchild()>)
//        }


 //   }

//    public void getSymbolCode(Tree.Node Nod,StringBuilder s)
//    {
//        //Tree.Node Noderoot = tree.getRoot();
//        if (Nod.getSymbol()!=0)
//        {
//
//            //this.hufCode.add(new HuffmanCode(Nod.getProbability(),Nod.getSymbol(),s));
//            HuffmanCode HC = new HuffmanCode(Nod.getProbability(),Nod.getSymbol(),s);
//            this.hufCode.add(HC);
//            //s.deleteCharAt(s.length()-1);
//        }
//        if(Nod.getRightchild()==null && Nod.getLeftchild()==null)
//        {
//            s.deleteCharAt(s.length()-1);
//            return;
//        }
//
//        if(Nod.getRightchild()!=null)
//        {
//            s.append("1");
//            getSymbolCode(Nod.getRightchild(),s);
//        }
//
//        if(Nod.getLeftchild()!=null)
//        {
//            s.append("0");
//            getSymbolCode(Nod.getLeftchild(),s);
//        }
//
//
//
////        while(Noderoot.getSymbol()!=symbol)
////        {
////            if(Noderoot.getLeftchild()>)
////        }
//
//
//    }
    public void startProcess()
    {
        getSymbolCode(this.tree.getRoot(),new StringBuilder(""));
    }

    private ArrayList<Integer> sortArrayListbyDescending(ArrayList<Integer> list)
    {
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }

    public double getMiddleLength(){
        double res = 0;
        double sum = 0;
        for(Integer i: this.prob)
            sum+=i;
        for(int i=0;i<this.prob.size();i++)
        {
            res+=this.prob.get(i)*this.hufCode.get(i).getEncode().length();
        }
        return res/sum;
    }

    public double getEntropy()
    {
        double res=0;
        double sum=0;
        for(Integer i: this.prob)
            sum+=i;
        for(int i =0;i<this.prob.size();i++)
        {
            res+=-1*(this.prob.get(i)/sum)*(Math.log(this.prob.get(i)/sum)/Math.log(2));
        }
        return res;
    }

    public void printAns(){
        startProcess();
        Collections.sort(hufCode);
        System.out.print("Probabilities: ");
        for(HuffmanCode i: getHufCode()){
            System.out.print(i.getProb()+" ");
        }
        System.out.println();
        System.out.print("Encode:  ");
        for(HuffmanCode i : getHufCode()){
            System.out.print(i.getEncode()+" ");
        }

        System.out.println();
        System.out.println("Middle length: "+ getMiddleLength());
        System.out.println("Entropy: " + getEntropy());
    }
}
