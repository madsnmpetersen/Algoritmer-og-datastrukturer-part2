/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author ShameOnU
 */
public class DictBinTreeNode 
{
    int key;
    int character;
    DictBinTreeNode left;
    DictBinTreeNode right;
    DictBinTreeNode parent;
    
    public DictBinTreeNode(int key, int character, DictBinTreeNode parent)
    {
        this.key = key;
        this.parent = parent;
        left = null;
        right = null;
        this.character = character;
    }
    
    public DictBinTreeNode(int key,int character)
    {
        this.key = key;
        this.parent = null;
        left = null;
        right = null;
        this.character = character;
    }
    
    public DictBinTreeNode(int key,DictBinTreeNode parent)
    {
        this.key = key;
        this.parent = parent;
        left = null;
        right = null;
        character = -1;
    }
    
    public DictBinTreeNode(int key)
    {
        this.key = key;
        this.parent = null;
        left = null;
        right = null;
        character = -1;
    }
    
    public void setLeft(DictBinTreeNode left)
    {
        this.left = left;
    }
    
    public void setRight(DictBinTreeNode right)
    {
        this.right = right;
    }
    
    public void setParent(DictBinTreeNode parent)
    {
        this.parent = parent;
    }
    
    public int returnKey()
    {
        return key;
    }
    
    public boolean hasChildren()
    {
        return left != null || right != null;                   
    }
    
    public DictBinTreeNode left()
    {
        return left;
    }
    
    public DictBinTreeNode right()
    {
        return right;
    }
    
    public int returnChar()
    {
        return character;
    }
    
}
