package Praktikum5ADS;


import java.util.ArrayList;
import java.util.Stack;

class BinaryTree {
	
	private BinaryTreeNode root;
	//private BinaryTreeNode parent;
	
	public void insert(BinaryTreeNode node){
		if(root==null){
			root=node;
			return;
		}
		if(root.getKey()==node.getKey()){
			return;
		}
		root=insertNode(root,node);
	}
	
	private BinaryTreeNode insertNode(BinaryTreeNode currentParent, BinaryTreeNode newNode) {
	    if (currentParent == null) {
	        return newNode;
	    } else if (newNode.getKey() > currentParent.getKey()) {
	    	
	    	//newNode > current, current.rightchild = insertNode(current.rightchild,newNode)
	    	currentParent.setRightChild(insertNode(currentParent.getRightChild(), newNode));
	    } else if (newNode.getKey() < currentParent.getKey()) {
	    	
	    	//newNode < current, current.leftchild = insertNode(current.leftchild,newNode)
	    	currentParent.setLeftChild(insertNode(currentParent.getLeftChild(), newNode));
	    }
	    return currentParent;
	}

	public boolean delete(BinaryTreeNode node){
		if(root==null) return false;
		try{
			root = delete(root,node);
			return true;
		}
		catch(IllegalArgumentException e){
			return false;
		}
	}
	
	private BinaryTreeNode delete(BinaryTreeNode p, BinaryTreeNode toDelete)	   {
		  if (p == null)  throw new IllegalArgumentException("Node not Found.");
	      else if (toDelete.getKey() < p.getKey())
	    	  p.setLeftChild(delete (p.getLeftChild(), toDelete));
	      else if (toDelete.getKey() > p.getKey())
	    	  p.setRightChild(delete(p.getRightChild(), toDelete));
	      else
	      {
	    	  if (p.getLeftChild() == null) return p.getRightChild();
	          else
	          if (p.getRightChild() == null) return p.getLeftChild();
	          else
	          {
	        	  // get data from the rightmost node in the left subtree
	        	  p = p.getLeftChild();
	        	  // delete the rightmost node in the left subtree
	        	  p.setLeftChild( delete(p.getLeftChild(), p));
	          }
	      }
	      return p;
	}
	
	public BinaryTreeNode search(BinaryTreeNode node){
		if(root==null){
			return null;
		}
		BinaryTreeNode focusNode=root;
		int cT = focusNode.getKey().compareTo(node.getKey());
		while(cT!=0){
			//node.getKey()<focusNode.getKey()
			if(cT>0){
					focusNode=focusNode.getLeftChild();
				}else{
					focusNode=focusNode.getRightChild();
				}
			if(focusNode == null) return null;
			cT = focusNode.getKey().compareTo(node.getKey());
		}
		return focusNode;
	}
	
	public boolean modify(BinaryTreeNode node1, BinaryTreeNode node2){
		if(delete(node1))
		{
			node2.setValue(node1.getValue());
			insert(node2);
			return true;
		}
		return false;		
	}	
	
	public BinaryTreeNode getRoot(){
		return this.root;
	}
	
	protected BinaryTreeNode setRoot(BinaryTreeNode root){
		return this.root = root;
	}
	
	public ArrayList<BinaryTreeNode> inorder(){
		if(root == null) return null;
		ArrayList<BinaryTreeNode> abt = new ArrayList<BinaryTreeNode>();
		Stack<BinaryTreeNode> s = new Stack<BinaryTreeNode>();  
		BinaryTreeNode focusNode=root;
		while(!s.empty() || focusNode!=null){  
			if(focusNode!=null){  
				s.push(focusNode);  
				focusNode=focusNode.getLeftChild();
			}else{  
				BinaryTreeNode n=s.pop();
				abt.add(n);
				focusNode=n.getRightChild();  
			}  
		}  
		return abt;
	}
	
}

