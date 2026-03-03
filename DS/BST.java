public class BST
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
            return;
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
                    if(temp.data>data)
                    {
                        temp=temp.left;
                    }
                    else
                    {
                        temp=temp.right;
                    }
                }
            }
        }
    }

    void preOrder(node root)
    {
        if(root==null)
        {
            return;
        }
        else
        {
            System.out.println(root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    void postOrder(node root)
    {
        if(root==null)
        {
            return;
        }
        else
        {
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.data);
        }
    }

    void inOrder(node root)
    {
        if(root==null)
        {
            return;
        }
        else
        {
            inOrder(root.left);
            System.out.println(root.data);
            inOrder(root.right);
        }
    }

    void internalNode(node root)
    {
        if(root==null)
        {
            return;
        }
        else
        {
            if(root.left!=null || root.right!=null)
            {
                System.out.println(root.data);
            }
            internalNode(root.left);
            internalNode(root.right);
        }
    }

    void leafNode(node root)
    {
        if(root==null)
        {
            return;
        }
        else
        {
            if(root.left==null && root.right==null)
            {
                System.out.println(root.data);
            }
            leafNode(root.left);
            leafNode(root.right);
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

    boolean find(int key)
    {
        node temp=root; boolean flag=false;
        while(true)
        {
            if(temp==null)
            {
                break;
            }
            else if(temp.data==key)
            {
                flag=true;
                break;
            }
            else if(key>temp.data)
            {
                temp=temp.right;
            }
            else
            {
                temp=temp.left;
            }
        }
        if(flag)
        {
            return true;
        }
        else
        {
            return false;
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
        if(root.data>data)
        {
            root.left=delRec(root.left,data);
        }
        else if(root.data<data)
        {
            root.right=delRec(root.right,data);
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

    void printOneDegreeNodes(node root)
    {
        if(root==null)
        {
            return;
        }
        if((root.left==null && root.right!=null) || (root.left!=null && root.right==null))
        {
            System.out.println(root.data);
        }
        printOneDegreeNodes(root.left);
        printOneDegreeNodes(root.right);
    }

    void findPredecessor(int key)
    {
        node pred=null;
        node cur=root;
        if(find(key))
        {
            while(cur!=null)
            {
                if(cur.data<key)
                {
                    pred=cur;
                    cur=cur.right;
                }
                else
                {
                    cur=cur.left;
                }
            }
            if(pred==null)
            {
                System.out.println("No predecessor");
            }
            else
            {
                System.out.println("Predecessor:"+pred);
            }
        }
        else
        {
            System.out.println("No such value found");
        }
    }
}
