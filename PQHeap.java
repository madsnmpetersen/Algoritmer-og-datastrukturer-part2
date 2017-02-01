/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author ShameOnU
 */
public class PQHeap implements PQ 
{
    int HeapSize;
    int MaxSize;
    private final Element[] Elements;
    public PQHeap(int maxElements)
    {
        Elements = new Element[maxElements+1]; /*Intialize the Elements array to the size of maxElements+1 because pos 0 isn't used */
        HeapSize = 0; /*The current size of the heap*/
        MaxSize = maxElements; /*The largest size the heap can have */
    }

    @Override
    public Element extractMin() 
    {
        Element least = Elements[1]; 
        Elements[1] = Elements[HeapSize];
        HeapSize--;
        minHeapIFY(1);
        return least;
        
    }

    @Override
    public void insert(Element e) 
    {
        HeapSize++;
        Elements[HeapSize] = e;
        int pointer = HeapSize;
        Element temp;
        while(pointer > 1 && (Elements[pointer].key < Elements[parentIndex(pointer)].key))
        {
            temp = Elements[pointer];
            Elements[pointer] = Elements[parentIndex(pointer)];
            Elements[parentIndex(pointer)] = temp;
            pointer = parentIndex(pointer);
        }
    }
    
    public Element returnElement (int pos)
    {
        return Elements[pos];
    }
    
    public int parentIndex(int i)
    {
        return i/2;
    }
    
    public int leftIndex (int i)
    {
        return i*2;
    }
    
    public int rightIndex (int i)
    {
        return (i*2)+1;
    }
    
    private void minHeapIFY(int i)
    {
        int l = leftIndex(i);
        int r = rightIndex(i);
        int smallest = i;
        Element temp;
        if(l <= HeapSize && Elements[l].key < Elements[smallest].key)
        {
            smallest = l;
        }
        
        if(r <= HeapSize && Elements[r].key < Elements[smallest].key)
        {
            smallest = r;
        }
        
        if(smallest != i)
        {
            temp = Elements[i];
            Elements[i] = Elements[smallest];
            Elements[smallest] = temp;
            minHeapIFY(smallest);
        }        
    }   
}