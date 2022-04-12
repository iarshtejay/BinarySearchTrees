package org.intelligentjava.algos.trees;

import org.intelligentjava.algos.trees.utils.MathUtils;

/**
 * Zip tree implementation.
 * 
 * @author Arshdeep Singh
 * @created April 4, 2021
 * 
 */
public class ZipTree extends AbstractSelfBalancingBinarySearchTree {

    // Replace with geometric distribution
    private Random random = new Random(System.currentTimeMillis());

    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#insert(int)
     * 
     *
     */
    @Override
    public Node insert(int element) {
    }

    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#delete(int)
     */
    @Override
    public Node delete(int element) {
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
