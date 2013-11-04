package fano;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 10/6/13
 * Time: 6:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tree {

    private Node root;
    private ArrayList<Node> nodeArrayList=new ArrayList<>();
    private ArrayList<Integer> listProb;

    public Tree(ArrayList<Integer> listProb) {
        this.listProb = new ArrayList<>(listProb);
        for(Integer i: listProb)
            nodeArrayList.add(new Node(i,i));

        /* because that we used only probability,we add them to as symbol */
//        for(Integer i: listProb)
//            nodeArrayList.get(i).setSymbol(i);
    }
    public Node getRoot() {
        return root;
    }

    public ArrayList<Integer> getListProb() {
        return listProb;
    }

    public void build(Node root,ArrayList<Node> nodelist)
    {
        if(nodelist.size()==1){
            return;
        }


        int sum=0;
        for(int i = 0;i<nodelist.size();i++)
        {
            sum+=nodelist.get(i).getProbability();
        }
        int centerindex =0;
        int tempvar =0;
        while(tempvar<sum/2.){
            tempvar+=this.listProb.get(centerindex);
            centerindex++;
        }

        ArrayList<Node> leftnodelist = new ArrayList<>(nodelist.subList(0,centerindex));
        ArrayList<Node> rightnodelist = new ArrayList<>(nodelist.subList(centerindex,nodelist.size()));

        int sumleftnode=0;
        int sumrightnode =0;
        for(int i=0;i<leftnodelist.size();i++)
            sumleftnode+=leftnodelist.get(i).getProbability();
        for(int j=0;j<rightnodelist.size();j++)
            sumrightnode+=rightnodelist.get(j).getProbability();

        root.setLeftchild(new Node(sumleftnode));
        root.setRightchild(new Node(sumrightnode));
        build(root.getLeftchild(),leftnodelist);
        build(root.getRightchild(),rightnodelist);

    }
    public void buildTree(){
        int sum=0;
        for(Integer i: listProb)
            sum+=i;
        this.root = new Node(sum);
        build(this.root,this.nodeArrayList);

    }


    public static class Node {
        private int probability;
        // save encode symbol
        private int symbol;
        private Node parent;
        private Node leftchild;
        private Node rightchild;

        public Node(int prob)
        {
            this.probability = prob;
            this.symbol=0;
            this.parent=null;
            this.leftchild=null;
            this.rightchild=null;
        }
        public Node(int prob, int sym)
        {
            this.probability = prob;
            this.symbol=sym;
            this.parent=null;
            this.leftchild=null;
            this.rightchild=null;
        }

        public Node getRightchild() {
            return rightchild;
        }

        public void setRightchild(Node rightchild) {
            this.rightchild = rightchild;
        }

        public int getProbability() {
            return probability;
        }

        public void setProbability(int probability) {
            this.probability = probability;
        }

        public int getSymbol() {
            return symbol;
        }

        public void setSymbol(int symbol) {
            this.symbol = symbol;
        }

        public Node getLeftchild() {
            return leftchild;
        }

        public void setLeftchild(Node leftchild) {
            this.leftchild = leftchild;
        }


        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }



    }
}
