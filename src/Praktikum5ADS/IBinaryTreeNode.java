package Praktikum5ADS;


public interface IBinaryTreeNode<TKey extends Comparable<TKey>, TValue>{
	public TKey getKey();
	public void setKey(TKey key);
	public TValue getValue();
	public void setValue(TValue value);
	public IBinaryTreeNode<?, ?> getLeftChild();
	public void setLeftChild(IBinaryTreeNode<?, ?> node);
	public IBinaryTreeNode<?, ?> getRightChild();
	public void setRightChild(IBinaryTreeNode<?, ?> node);
}