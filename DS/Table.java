public class Table
{
    class info
    {
        int data;
        boolean flag;
        info()
        {
            data=0;
            flag=false;
        }
    }
    int tableSize=101;
    info [] a=new info[tableSize];
    {
        for(int i=0;i<tableSize;i++)
        {
            a[i]=new info();
        }
    }

    void insert(int data)
    {
        int i=0;
        while(i<tableSize)
        {
            int h=((data)%tableSize+i)%tableSize;
            if(a[h].flag==false)
            {
                a[h].data=data;
                a[h].flag=true;
                break;
            }
            else
            {
                i++;
            }
        }
    }

    void search(int k)
    {
        int i=0;
        while(i<tableSize)
        {
            int h=((k)%tableSize+i)%tableSize;
            if(a[h].data==k && a[h].flag==true)
            {
                System.out.println("Index:"+i);
                break;
            }
            else
            {
                i++;
            }
        }
    }
}
