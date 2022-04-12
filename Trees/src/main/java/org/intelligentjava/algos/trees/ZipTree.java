package org.intelligentjava.algos.trees;

import org.intelligentjava.algos.trees.utils.MathUtils;

/**
 * Zip tree implementation.
 * 
 * @author Arshdeep Singh
 * @created April 4, 2021
 * 
 */
public class ZipTree extends AbstractBinarySearchTree {

    // Replace with geometric distribution
    private Random random = new Random(System.currentTimeMillis());

    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#insert(int)
     * 
     *
     */
    @Override
    public Node insert(int element) {
        int rank = random.nextInt(10000);
        int key = element;
        ZipNode curr = root;
        ZipNode x = createNode(key, null, null, null, rank);
        ZipNode prev;

        while(curr!=null && ((rank<curr.rank)||(rank == curr.rank && key>curr.value))){
            prev = curr;
            curr = key<curr.value?curr.left:curr.right;
        }

        if(curr==root){
            root=x;
        }else if(key<prev.value){
            prev.left=x;
        }else{
            prev.right=x;
        }

        ZipNode fix;
        while(curr!=null){
            fix=prev;
            if(curr.value<key){
                do{
                    prev=curr;
                    curr=curr.right;
                }
                while(curr!=null && curr.value<=key);
            }else{
                do{
                    prev=curr;
                    curr=curr.left;
                }
                while(curr!=null && curr.value>=key);
            }

            if((fix.value>key)||(fix == x && prev.value > key) {
                fix.left=curr;
            }else{
                fix.right=curr;
            }
        }
        return x;
    }

    @Override
    public ZipNode search(int element) {
        ZipNode node = root;
        while (node != null && node.value != null && node.value != element) {
            if (element < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }


    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#delete(int)
     */
    @Override
    public Node delete(int element) {
        ZipNode x = this.search(element);
        if(x==null){
            return x;
        }

        int key = x.value;
        ZipNode curr = root;
        ZipNode prev;

        while(key!=curr.value){
            prev=curr;
            curr=(key<curr.value)?curr.left:curr.right;
        }
        ZipNode left = curr.left;
        ZipNode right = curr.right;

        if(left==null){
            curr = right;
        }else if(right==null){
            curr=left;
        }else if(left.rank>=right.rank){
            curr=left;
        }else{
            curr=right;
        }

        if(x==root){
            root=curr;
        }else if(key<prev.value){
            prev.left=curr;
        }else{
            prev.right=curr;
        }

        while(left!=null && right!=null){
            if(left.rank>=right.rank){
                do{
                    prev=left;
                    left=left.right;
                }
                while(left!=null && left.rank>=right.rank);
                prev.right=right;
            }else{
                do{
                    prev=right;
                    right=right.left;
                }
                while(right!=null && left.rank<right.rank);
                prev.left=left;
            }
        }
        return null;
    }
    
    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#createNode(int, org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node, org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node, org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node)
     */
    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new ZipNode(value, parent, left, right, random.nextInt(10000));
    }

    /**
     * Node of Zip tree has rank as an additional property
     * 
     * @author Arshdeep Singh
     * @created April 11, 2022
     * 
     */
    protected static class ZipNode extends Node {
        public int rank;

        public ZipNode(int value, Node parent, Node left, Node right, int rank) {
            super(value, parent, left, right);

            this.rank = rank;
        }
    }

}
