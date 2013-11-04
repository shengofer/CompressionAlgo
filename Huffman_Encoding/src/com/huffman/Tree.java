package com.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public void buildTree()
    {
        while(nodeArrayList.size()>2)
        {
            Node last = nodeArrayList.get(nodeArrayList.size()-1);
            nodeArrayList.remove(nodeArrayList.size()-1);
            Node penultimate = nodeArrayList.get(nodeArrayList.size() - 1);
            nodeArrayList.remove(nodeArrayList.size()-1);

            Node newProb = new Node(last.getProbability()+penultimate.getProbability());
            last.setParent(newProb);
            penultimate.setParent(newProb);
            newProb.setRightchild(last);
            newProb.setLeftchild(penultimate);

            //set newNode in nodeArrayList
            int nodeSize = nodeArrayList.size();
            for(int i=nodeSize-1;i>=0;i--)
            {
                if(newProb.getProbability()<=nodeArrayList.get(i).getProbability())
                {
                    nodeArrayList.add(i, newProb);
                    break;
                }
            }
            if(nodeSize==nodeArrayList.size())   {
                if(newProb.getProbability()<nodeArrayList.get(nodeArrayList.size()-1).getProbability()) {
                    nodeArrayList.add(nodeArrayList.size(), newProb);
                }
                if (newProb.getProbability()>nodeArrayList.get(0).getProbability()){
                    nodeArrayList.add(0,newProb);
                }
            }




        }
         //work with last 2 node
        Node last = nodeArrayList.get(nodeArrayList.size()-1);
        nodeArrayList.remove(nodeArrayList.size()-1);
        Node penultimate = nodeArrayList.get(nodeArrayList.size() - 1);
        nodeArrayList.remove(nodeArrayList.size()-1);
        root = new Node(last.getProbability()+penultimate.getProbability());
        last.setParent(root);
        penultimate.setParent(root);
        root.setRightchild(last);
        root.setLeftchild(penultimate) ;
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
