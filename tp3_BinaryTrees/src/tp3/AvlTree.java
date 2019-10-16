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
        boolean resulta= true;
        if(value==currentNode.value){
            return false;
        }
        if(currentNode.value.compareTo(value)==1){
            if(currentNode.left==null){
                currentNode.left= new BinaryNode<ValueType>(value,currentNode);
                resulta= true;
            }
            else {
                resulta= insert(value,currentNode.left);
            }
        }
        else {
            if(currentNode.right==null){
                currentNode.right= new BinaryNode<ValueType>(value,currentNode);
                resulta= true;
            }
            else {
                resulta= insert(value,currentNode.right);

            }
        }

        if(resulta){//si la ballence en avl est fait une foit apres l'ajour l'arbre est automatiquement avl
            balance(currentNode);
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
        boolean bReussi=false;
        if(value==currentNode.value){
            if(currentNode.left!=null||currentNode.right!=null)//verifie si se n'ai pas une feuille
                currentNode= occuperDesEnfant(currentNode);//a tester pour voir si sa prend tous les cat
            else{
                if(nodeParantEstRoot(currentNode,currentNode)){
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
            }
            bReussi= true;
        }
        else if(currentNode.value.compareTo(value)==1){
            bReussi= remove(value,currentNode.left);
            if(bReussi&&currentNode.left.value==null)
                currentNode.left=null;
        }
        else {
            bReussi= remove(value, currentNode.right);
            if(bReussi&&currentNode.right.value==null)
                currentNode.right=null;
        }
        if(bReussi){
            balance(currentNode);
        }
        return false;
    }
    private BinaryNode<ValueType> occuperDesEnfant(BinaryNode<ValueType> currentNode){
        BinaryNode<ValueType> enfantNodeDroit =currentNode.right;
        BinaryNode<ValueType> enfantNodeGauche =currentNode.left;
        BinaryNode<ValueType> nouvelNodeEnHaut=null;
        if(enfantNodeDroit !=null) {
            while (enfantNodeDroit.left != null) {
                enfantNodeDroit = enfantNodeDroit.left;
            }
            if(enfantNodeDroit.parent!=null)
                if(enfantNodeDroit.parent.left.value==enfantNodeDroit.value){
                    enfantNodeDroit.parent.left=null;
                }
        }
        if(enfantNodeDroit==null){
            nouvelNodeEnHaut= occupeDuParent(enfantNodeGauche,currentNode);
        }
        else {
            nouvelNodeEnHaut=occupeDuParent(enfantNodeDroit,currentNode);
            enfantNodeDroit.left=enfantNodeGauche;
            enfantNodeDroit.right=currentNode.right;
        }
        return nouvelNodeEnHaut;
    }
    private BinaryNode<ValueType> occupeDuParent(BinaryNode<ValueType> nouvelNode,BinaryNode<ValueType> vieuxNode){
        if( nodeParantEstRoot( nouvelNode, vieuxNode)){
            return nouvelNode;
        }else if(vieuxNode.parent.left.value==vieuxNode.value){
            vieuxNode.parent.left=nouvelNode;
        }
        else if(vieuxNode.parent.right.value==vieuxNode.value){
            vieuxNode.parent.right=nouvelNode;
        }
        nouvelNode.parent=vieuxNode.parent;
        return nouvelNode;
    }
    private boolean nodeParantEstRoot(BinaryNode<ValueType> nouvelNode,BinaryNode<ValueType> vieuxNode){
        if(vieuxNode.parent==null){
            this.root=nouvelNode;
            nouvelNode.parent=null;
            return true;
        }
        else
            return false;
    }
    /** TODO O( n )
     * Balances the subTree
     * @param subTree SubTree currently being accessed to verify if it respects the AVL balancing rule
     */
    private void balance(BinaryNode<ValueType> subTree) {

        int iGauche,iDroit,typeRotation=0;
        iGauche=getLevelCount(subTree.left);
        iDroit=getLevelCount(subTree.right);
        while (subTree!=null){
            break;
        }
        /*switch (typeRotate){
            case 0:
                rotateLeft(subTree);
                break;
            case 1:
                rotateRight(subTree);
                break;
            case 2:
                doubleRotateOnLeftChild(subTree);
                break;
            case 3:
                doubleRotateOnRightChild(subTree);
                break;
        }*/
    }

    /** TODO O( 1 )
     * Single rotation to the left child, AVR Algorithm
     * @param node1 Node to become child of its left child
     */
    private void rotateLeft(BinaryNode<ValueType> node1){
        BinaryNode<ValueType> Vieu_parent=node1.parent;
        BinaryNode<ValueType> nouveauxEnHaut =node1.left;
        BinaryNode<ValueType> newRight=node1.left.right;
        nouveauxEnHaut.parent=Vieu_parent;
        if(this.root.equals(node1))
            this.root= nouveauxEnHaut;
        node1.parent= nouveauxEnHaut;
        node1.left=null;
        node1.right=newRight;
        nouveauxEnHaut.right=node1;
    }

    /** TODO O( 1 )
     * Single rotation to the right, AVR Algorithm
     * @param node1 Node to become child of its right child
     */
    private void rotateRight(BinaryNode<ValueType> node1){
        BinaryNode<ValueType> OLD_parent=node1.parent;
        BinaryNode<ValueType> newTop=node1.right;
        BinaryNode<ValueType> newleft=node1.right.left;
        newTop.parent=OLD_parent;
        if(this.root.equals(node1))
            this.root=newTop;
        node1.parent=newTop;
        node1.left=newleft;
        node1.right=null;
        newTop.left=node1;

    }

    /** TODO O( 1 )
     * Double rotation on left child, AVR Algorithm
     * @param node1 Node to become child of the right child of its left child
     */
    private void doubleRotateOnLeftChild(BinaryNode<ValueType> node1){
    }

    /** TODO O( 1 )
     * Double rotation on right child, AVR Algorithm
     * @param node1 Node to become child of the left child of its right child
     */
    private void doubleRotateOnRightChild(BinaryNode<ValueType> node1){
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