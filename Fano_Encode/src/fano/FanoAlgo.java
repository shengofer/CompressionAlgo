package fano;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 10/6/13
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class FanoAlgo {
    private Tree tree;
    private ArrayList<Integer> prob;
    private ArrayList<FanoCode> fanCode = new ArrayList<>();


    public FanoAlgo(ArrayList<Integer> prob)
    {
        this.prob = new ArrayList<>(sortArrayListbyDescending(prob));
        tree = new Tree(this.prob);
        tree.buildTree();
    }

    public ArrayList<FanoCode> getHufCode() {
        return fanCode;
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
           FanoCode HC = new FanoCode(Nod.getProbability(),Nod.getSymbol(),s.toString());
            this.fanCode.add(HC);

            return;
        }

    }

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
            res+=this.prob.get(i)*this.fanCode.get(i).getEncode().length();
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
        Collections.sort(fanCode);
        System.out.print("Probabilities: ");
        for(FanoCode i: getHufCode()){
            System.out.print(i.getProb()+" ");
        }
        System.out.println();
        System.out.print("Encode:  ");
        for(FanoCode i : getHufCode()){
            System.out.print(i.getEncode()+" ");
        }

        System.out.println();
        System.out.println("Middle length: "+ getMiddleLength());
        System.out.println("Entropy: " + getEntropy());
    }
}
