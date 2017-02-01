/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author ShameOnU
 */
public class DictBinTree implements Dict
{
    DictBinTreeNode root;
    int nodes;
    int height;
    ArrayList<Integer> helper;
    String[] theHuff;
    
    public DictBinTree()
    {
        root = null;
        nodes = 0;
    }
    
    @Override
    public void insert(int k) 
    {
        DictBinTreeNode insert = root;
        if(insert != null)
        while(insert != null)
        {
            if(k < insert.key)
            {
                if(insert.left == null)
                {
                    insert.left = new DictBinTreeNode(k,insert);
                    insert = null;
                }
                else insert = insert.left;
            }
            else
            {
                if(insert.right == null)
                {
                    insert.right = new DictBinTreeNode(k,insert);
                    insert = null;
                }
                else insert = insert.right;
            }
        }
        else 
        {
            root = new DictBinTreeNode(k,null);
        }
        
        nodes++;
    }
    
    public void insert(int k, int character) 
    {
        DictBinTreeNode insert = root;
        if(insert != null)
        while(insert != null)
        {
            if(k < insert.key)
            {
                if(insert.left == null)
                {
                    insert.left = new DictBinTreeNode(k,character,insert);
                    insert = null;
                }
                else insert = insert.left;
            }
            else
            {
                if(insert.right == null)
                {
                    insert.right = new DictBinTreeNode(k,character,insert);
                    insert = null;
                }
                else insert = insert.right;
            }
        }
        else 
        {
            root = new DictBinTreeNode(k,character,null);
        }
        
        nodes++;
    }
    
    public void insert(DictBinTreeNode node) 
    {
        DictBinTreeNode insert = root;
        int k = node.key;
        if(insert != null)
        while(insert != null)
        {
            if(k < insert.key)
            {
                if(insert.left == null)
                {
                    node.setParent(insert);
                    insert.setLeft(node);
                    insert = null;
                }
                else insert = insert.left;
            }
            else
            {
                if(insert.right == null)
                {
                    node.setParent(insert);
                    insert.setRight(node);
                    insert = null;
                }
                else insert = insert.right;
            }
        }
        else 
        {
            root = node;
        }
        
        nodes++;
    }

    @Override
    
    public int[] orderedTraversal() 
    {
        helper = new ArrayList(nodes);
        treeWalk(root);
        int[] result = new int[nodes];
        int i = 0;
        while(helper.size() > 0)
        {
            result[i] = (int)helper.get(0);
            helper.remove(0);
            i++;
        }
        return result;       
    }
    
    private void treeWalk(DictBinTreeNode start)
    {
        if(start != null)
        {
            treeWalk(start.left);
            helper.add(start.key);
            treeWalk(start.right);
        }       
    }
    
    public String[] huffWalk()
    {
        theHuff = new String[256];
        String[] result = huffHelper(root,"");
        return result;
    }
    
    private String[] huffHelper(DictBinTreeNode start,String init)
    {
        String str = init;
        if(start != null)
        {
            huffHelper(start.left,str + "1");
            if(start.character != -1)
            theHuff[start.character] = str;
            huffHelper(start.right,str + "0");
        }
        return theHuff;
    }

    @Override
    public boolean search(int k) 
    {
        if(root != null)
        {
            DictBinTreeNode search = root;
            while(search.key != k)
            {
                if (search.key <= k)
                {
                    if(search.right != null)
                        search = search.right;
                    else return false;
                }
                else
                {
                    if(search.left != null)
                        search = search.left;
                    else return false;
                }
                    
            }
            return true;
        }
        return false;
    }
    
    public void setRoot(DictBinTreeNode root)
    {
        this.root = root;
    }
    
    public void addNode()
    {
        nodes++;
    }
    
    public int rootKey()
    {
        return root.key;
    }
    
    public DictBinTreeNode root()
    {
        return root;
    }
    
    public int nodes()
    {
        return nodes;
    }
    
    public void setNodes(int newNodes)
    {
        nodes = newNodes;
    }
}
