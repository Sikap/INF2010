package tp3;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class AvlTree<ValueType extends Comparable<? super ValueType> > {

    private BinaryNode<ValueType> root;

    public AvlTree() { }

    /**
     * Adds value to the tree and keeps it as a balanced AVL Tree
     * @param value value to add to the tree
     */
    public void insert(ValueType value) {
        if (root == null) {
            root = new BinaryNode<ValueType>(value, null);
        } else {
            insert(value, root);
        }
    }

    /**
     * Removes value from the tree and keeps it as a balanced AVL Tree
     * @param value value to add to the tree
     */
    public void remove(ValueType value){
        remove(value, root);
    }

    /**
     * Verifies if the tree contains value
     * @param value value to verify
     * @return if value already exists in the tree
     */
    public boolean contains(ValueType value) {
        return contains(value, root);
    }

    /**
     * Returns the max level contained in our root tree
     * @return Max level contained in our root tree
     */
    public int getHeight() {

        return getLevelCount(root) - 1;
    }

    /**
     * Returns the minimal value contained in our root tree
     * @return Minimal value contained in our root tree
     */
    public ValueType findMin() {
        BinaryNode<ValueType> minimalNode = findMin(root);
        if (minimalNode == null) return null;
        return minimalNode.value;
    }

    /**
     * Returns all values contained in the root tree in ascending order
     * @return Values contained in the root tree in ascending order
     */
    public List<ValueType> infixOrder() {
        List<ValueType> items = new LinkedList<ValueType>();
        infixOrder(root, items);
        return items;
    }

    /**
     * Returns all values contained in the root tree in level order from top to bottom
     * @return Values contained in the root tree in level order from top to bottom
     */
    public List<ValueType> levelOrder(){
        List<ValueType> items = new LinkedList<ValueType>();

        ArrayDeque<BinaryNode<ValueType>> nodesToCheck = new ArrayDeque<BinaryNode<ValueType>>();

        if (root != null){
            nodesToCheck.push(root);
            levelOrder(nodesToCheck, items);
        }

        return items;
    }

    /** TODO O( log n )
     * Adds value to the tree and keeps it as a balanced AVL Tree
     * Should call balance only if insertion succeeds
     * AVL Trees do not contain duplicates
     *
     * @param value value to add to the tree
     * @param currentNode Node currently being accessed in this recursive method
     * @return if parent node should balance
     */
    private boolean insert (ValueType value, BinaryNode<ValueType> currentNode){

        int compareResults = currentNode.value.compareTo(value);
        if(compareResults<0){
            if(currentNode.right!=null) {
                insert(value,currentNode.right);
            }
            else {
                currentNode.right=new BinaryNode<ValueType>(value,currentNode);
                balance(currentNode.right);
                return true;
            }
        }
        else if(compareResults>0)
        {
            if(currentNode.left!=null)
            {
                insert(value, currentNode.left);
            }
            else{
                currentNode.left= new BinaryNode<ValueType>(value,currentNode);
                balance(currentNode.left);
                return true;
            }
        }

        return false;
    }

    /** TODO O ( log n )
     * Removes value from the tree and keeps it as a balanced AVL Tree
     * Should call balance only if removal succeeds
     * @param value value to remove from the tree
     * @param currentNode Node currently being accessed in this recursive method
     * @return if parent node should balance
     */
    private boolean remove(ValueType value, BinaryNode<ValueType> currentNode) {
        if(currentNode==null){return false;}
        int compareResults = currentNode.value.compareTo(value);
        if(compareResults>0){
            remove(value,currentNode.left);
        }else if(compareResults<0){
           remove(value, currentNode.right);
        }else if(currentNode.left!=null&&currentNode.right!=null){// If node that we want removed has 2 Kids
            ValueType minOfRightKid =findMin(currentNode.right).value;
            currentNode.value=findMin(currentNode.right).value;
            remove(minOfRightKid,currentNode.right);
        }else if(currentNode.right!=null){// If node that we want removed has only a right Kid
            currentNode.parent.left=currentNode.right;
            balance(currentNode);
            return true;
        }else  if(currentNode.left!=null){// If node that we want removed has only a left Kid
            currentNode.parent.left=currentNode.left;
            balance(currentNode);
            return true;
        }else{// If node that we want removed has no Kids
                if(currentNode.equals(root)){
                    this.root=null;
                    return true;
                }
                else{
                    if(currentNode.parent.left!=null){
                        if(currentNode.parent.left.equals(currentNode))
                            currentNode.parent.left=null;
                    }else
                        currentNode.parent.right=null;
                }
            balance(currentNode);
                return true;
        }
        return false;
    }

    public enum Direction{rightDirection,leftDirection};
    /** TODO O( n )
     * Balances the subTree
     * @param subTree SubTree currently being accessed to verify if it respects the AVL balancing rule
     */
    private void balance(BinaryNode<ValueType> subTree) {
        int levelRight,levelLeft;
        while (subTree!=null){
            levelRight=getLevelCount(subTree.right);
            levelLeft=getLevelCount(subTree.left);
            if(levelLeft>levelRight+1) {
                chooseRotation(subTree,Direction.leftDirection);
            }
            if(levelRight-levelLeft>1) {
                chooseRotation(subTree,Direction.rightDirection);
            }
            subTree=subTree.parent;
        }
    }

    private void chooseRotation(BinaryNode<ValueType> subTree, Direction directionRotation) {
        switch (directionRotation){
            case leftDirection:
                if(getLevelCount(subTree.left.left)>=getLevelCount(subTree.left.right))
                    rotateLeft(subTree);
                else
                    doubleRotateOnLeftChild(subTree);
                break;
            case rightDirection:
                if(getLevelCount(subTree.right.right)>=getLevelCount(subTree.right.left))
                    rotateRight(subTree);
                else
                    doubleRotateOnRightChild(subTree);
                break;
        }
    }

    /** TODO O( 1 )
     * Single rotation to the left child, AVR Algorithm
     * @param node1 Node to become child of its left child
     */
    private void rotateLeft(BinaryNode<ValueType> node1){
        BinaryNode<ValueType> oldParent=node1.parent;
        BinaryNode<ValueType> newHeightNode =node1.left;
        BinaryNode<ValueType> newRight=node1.left.right;
        newHeightNode.parent=oldParent;
        if(this.root.equals(node1)){
            this.root= newHeightNode;
        }
        else
        if(oldParent.left.value==node1.value)
            oldParent.left=newHeightNode;
        else
            oldParent.right=newHeightNode;
        node1.parent= newHeightNode;
        node1.left=newRight;
        if(newRight!=null)
            newRight.parent=node1;
        newHeightNode.right=node1;

    }

    /** TODO O( 1 )
     * Single rotation to the right, AVR Algorithm
     * @param node1 Node to become child of its right child
     */
    private void rotateRight(BinaryNode<ValueType> node1){
        BinaryNode<ValueType> oldParent=node1.parent;
        BinaryNode<ValueType> newHeightNode=node1.right;
        BinaryNode<ValueType> newleft=node1.right.left;
        newHeightNode.parent=oldParent;
        if(this.root.equals(node1))
            this.root=newHeightNode;
        else
        if(oldParent.left.value==node1.value)
            oldParent.left=newHeightNode;
        else
            oldParent.right=newHeightNode;
        node1.parent=newHeightNode;
        node1.right=newleft;
        if(newleft!=null)
            newleft.parent=node1;
        newHeightNode.left=node1;


    }

    /** TODO O( 1 )
     * Double rotation on left child, AVR Algorithm
     * @param node1 Node to become child of the right child of its left child
     */
    private void doubleRotateOnLeftChild(BinaryNode<ValueType> node1){
        rotateRight(node1.left);
        rotateLeft(node1);
    }

    /** TODO O( 1 )
     * Double rotation on right child, AVR Algorithm
     * @param node1 Node to become child of the left child of its right child
     */
    private void doubleRotateOnRightChild(BinaryNode<ValueType> node1){
        rotateLeft(node1.right);
        rotateRight(node1);
    }

    /** TODO O( log n )
     * Verifies if the root tree contains value
     * @param value value to verify
     * @param currentNode Node currently being accessed in this recursive method
     * @return if value already exists in the root tree
     */
    private boolean contains(ValueType value, BinaryNode<ValueType> currentNode){
        if(currentNode==null||currentNode.value==null) return false;
        if( currentNode.value==value){
            return  true;
        }
        if(currentNode.value.compareTo(value)==1){
            return contains(value,currentNode.left);
        }
        else{
            return contains(value,currentNode.right);
        }
    }

    /** TODO O( n )
     * Returns the number of level contained in subTree including subTree node level
     * @return Number of level contained in subTree including subTree node level
     */
    private int getLevelCount(BinaryNode<ValueType> subTree){
        int left,right;
        if(subTree==null||subTree.value==null){return 0;}
        left=getLevelCount(subTree.left);
        right=getLevelCount(subTree.right);
        return  (left>right)?left+1:right+1;

    }

    /** TODO O( log n )
     * Returns the node which has the minimal value contained in our root tree
     * @return Node which has the minimal value contained in our root tree
     */
    private BinaryNode<ValueType> findMin(BinaryNode<ValueType> currentNode) {
        if(currentNode==null){
            return null;
        }
        if(currentNode.left==null)
            return currentNode;
        return findMin(currentNode.left);
    }

    /** TODO O( n )
     * Builds items which should contain all values within the root tree in ascending order
     * @param currentNode Node currently being accessed in this recursive method
     * @param items List being modified to contain all values in the root tree in ascending order
     */
    private void infixOrder(BinaryNode<ValueType> currentNode, List<ValueType> items){
        if(currentNode==null)return;
        if(currentNode.left!=null)
            infixOrder(currentNode.left,items);
        items.add(currentNode.value);
        if(currentNode.right!=null)
            infixOrder(currentNode.right,items);
    }

    /** TODO O( n )
     * Builds items which should contain all values within the root tree in level order from top to bottom
     * @param nodesToCheck Queue for non-recursive algorithm
     * @param items List being modified to contain all values in the root tree in level order from top to bottom
     */
    private void levelOrder(ArrayDeque<BinaryNode<ValueType>> nodesToCheck, List<ValueType> items) {
        BinaryNode<ValueType> binaryNode=nodesToCheck.pop();
        if(binaryNode.left!=null)
            nodesToCheck.add(binaryNode.left);
        if(binaryNode.right!=null)
            nodesToCheck.add(binaryNode.right);
        items.add(binaryNode.value);
        if(nodesToCheck.size()==0)return;
        levelOrder(nodesToCheck,items);
    }

    static class BinaryNode<ValueType> {
        ValueType value;

        BinaryNode<ValueType> parent; // Pointer to the node containing this node

        BinaryNode<ValueType> left = null; // Pointer to the node on the left which should contain a value below this.value
        BinaryNode<ValueType> right = null; // Pointer to the node on the right which should contain a value above this.value

        BinaryNode(ValueType value, BinaryNode<ValueType> parent)
        {
            this.value = value;
            this.parent = parent;
        }
    }
}