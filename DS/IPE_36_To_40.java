public class IPE_36_To_40
{
   class node
   {
       int data;
       node left;
       node right;
       node(int data)
       {
           this.data=data;
           left=null;
           right=null;
       }
   }
   node root=null;

   void insert(int data)
   {
       node n=new node(data);
       if(root==null)
       {
           root=n;
       }
       else
       {
           node temp=root;
           while(true)
           {
               if(temp.left==null && temp.data>data)
               {
                   temp.left=n;
                   return;
               }
               else if(temp.right==null && temp.data<data)
               {
                   temp.right=n;
                   return;
               }
               else
               {
                   if(data>temp.data)
                   {
                       temp=temp.right;
                   }
                   else
                   {
                       temp=temp.left;
                   }
               }
           }
       }
   }
   int findMin(node root)
   {
       node temp=root;
       while(temp.left!=null)
       {
           temp=temp.left;
       }
       return temp.data;
   }

    int findMax(node root)
    {
        node temp=root;
        while(temp.right!=null)
        {
            temp=temp.right;
        }
        return temp.data;
    }

    boolean search(int data)
    {
        boolean flag=false;
        node temp=root;
        while(true)
        {
            if(temp==null)
            {
                break;
            }
            else if(temp.data==data)
            {
                flag=true;
                break;
            }
            else
            {
                if(data>temp.data)
                {
                    temp=temp.right;
                }
                else
                {
                    temp=temp.left;
                }
            }
        }
        if(flag==false)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    void del(int data)
    {
        delRec(root,data);
    }

    node delRec(node root,int data)
    {
        if(root==null)
        {
            return null;
        }
        else if(root.data<data)
        {
            root.right=delRec(root.right,data);
        }
        else if(root.data>data)
        {
            root.left=delRec(root.left,data);
        }
        else
        {
            if(root.right==null)
            {
                return root.left;
            }
            else if(root.left==null)
            {
                return root.right;
            }
            root.data=findMin(root.right);
            root.right=delRec(root.right,root.data);
        }
        return root;
    }

    int height(node root)
    {
        if(root==null)
        {
            return 0;
        }
        else
        {
            int leftHeight=height(root.left);
            int rightHeight=height(root.right);
            return Math.max(leftHeight,rightHeight)+1;
        }
    }
}
