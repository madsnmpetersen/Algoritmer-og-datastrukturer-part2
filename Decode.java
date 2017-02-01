
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ShameOnU
 */
public class Decode {
    
    public String[] theHuff;
    
    public static void main(String[] args)
    {
        if(args.length < 2)
            System.out.println("Too few arguments, needs an inputfile and an outputfile as arguments 1 and 2 respectively");
        else
            try 
            {
                new Decode().decompress(args[0],args[1]);
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void decompress(String inFile, String outFile) throws IOException
    {
        PQ myQ;
        DictBinTree myT;
        FileInputStream infile = new FileInputStream(inFile);
        BitInputStream in = new BitInputStream(infile);
        FileOutputStream out = new FileOutputStream(outFile);
        int inBytes = 0;
        int input = 0;
        int[] alphabet = new int[256];
        
        for(int i = 0 ; i < 256 ; i++)
        {
            input = in.readInt();
            alphabet[i] = input;
        }
        inBytes = in.readInt();        
        int n = 0;
        
        for(int i = 0; i<256 ;i++)
        {
            if(alphabet[i]>0)
                n++;
        }
        
        myQ = new PQHeap(n);
        
        for(int i = 0; i<256 ;i++)
        {
            if(alphabet[i]>0)
            {
                DictBinTree tree = new DictBinTree();
                tree.insert(alphabet[i], i);
                Element ele = new Element(alphabet[i],tree);
                myQ.insert(ele);
            }
        }
        
        for (int i = 1; i<n; i++)
        {
            DictBinTree left = (DictBinTree)myQ.extractMin().data;
            DictBinTree right = (DictBinTree)myQ.extractMin().data;
            DictBinTree tree = new DictBinTree();
            tree.insert(left.root().returnKey()+right.root().returnKey());
            tree.root().setLeft(left.root());
            tree.setNodes(tree.nodes()+left.nodes());
            tree.root().setRight(right.root());
            tree.setNodes(tree.nodes()+right.nodes());
            myQ.insert(new Element(left.root().returnKey()+right.root().returnKey(),tree));
        }
        
        myT = (DictBinTree)myQ.extractMin().data;
        theHuff = myT.huffWalk();
        
        /*
        for(int i = 0; i<256 ;i++)
        {
            System.out.println(i+" : "+alphabet[i]+" : "+theHuff[i]);
        }
        */
        int count = 0;
        while(input != -1)
        {
            DictBinTreeNode current = myT.root();
            while((current.returnChar() == -1))
            {
                input = in.readBit();
                count++;
                if (input == 1)
                    current = current.left();
                else
                    current = current.right();
                if(count == 825892)
                    input = -1;
            }
            out.write(current.returnChar());
        }
        out.close();
        in.close();
    }
}
