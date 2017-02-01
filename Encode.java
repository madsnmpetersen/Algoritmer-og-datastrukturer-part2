/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ShameOnU
 */
public class Encode
{
    public String[] theHuff;
    
    public static void main(String[] args)
    {
        try 
        {
            if(args.length < 2)
                System.out.println("Too few arguments, needs an inputfile and an outputfile as arguments 1 and 2 respectively");
            else
                new Encode().compress(args[0],args[1]);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Encode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void compress(String inFile, String outFile) throws IOException
    {
        PQ myQ;
        FileInputStream in = new FileInputStream(inFile);
        FileOutputStream fileOut = new FileOutputStream(outFile);
        BitOutputStream out = new BitOutputStream(fileOut);
        int inBytes = 0;
        int input;
        int[] alphabet = new int[256];
        
        while((input=in.read()) != -1)
        {
            alphabet[input]++;
            inBytes++;
        }
        
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
        
        theHuff = ((DictBinTree)myQ.extractMin().data).huffWalk();
        
        /*
        for(int i = 0; i<256 ;i++)
        {
            System.out.println(i+" : "+alphabet[i]+" : "+theHuff[i]);
        }
        */
        
        in = new FileInputStream(inFile);
        
        for(int i = 0; i<256 ;i++)
        {
            out.writeInt(alphabet[i]);
        }
        //int compBits = 0;
        out.writeInt(inBytes);
        while((input=in.read()) != -1)
        {
            int count = 0;
            String temp = theHuff[input];
            while(count < temp.length())
                    {
                        if(temp.charAt(count) == '1')
                        {
                            out.writeBit(1);
                            //compBits ++;
                        }
                        else
                        {
                            out.writeBit(0);
                            //compBits ++;
                        }
                        count++;
                    }
        }
        out.close();
        in.close();        
    }
}
