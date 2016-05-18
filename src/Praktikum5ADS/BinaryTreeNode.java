package Praktikum5ADS;


class BinaryTreeNode implements IBinaryTreeNode<Integer, Integer> {
	private Integer key;
	private Integer value;
	private Integer ebene;
	private BinaryTreeNode leftchild;
	private BinaryTreeNode rightchild;
	private BinaryTreeNode parent;
	
	public Integer getKey(){
		return this.key;
	}
	
	public void setKey(Integer key){
		this.key = key;
	}
	
	public Integer getValue(){
		return this.value;
	}
	
	public void setValue(Integer value){
		this.value=value;
	}
	
	public BinaryTreeNode getRightChild(){
		return rightchild;
	}
	
	public BinaryTreeNode getLeftChild(){
		return leftchild;
	}

	@Override
	public void setLeftChild(@SuppressWarnings("rawtypes") IBinaryTreeNode node) {
		this.leftchild = (BinaryTreeNode) node;
		
	}

	@Override
	public void setRightChild(@SuppressWarnings("rawtypes")IBinaryTreeNode node) {
		this.rightchild = (BinaryTreeNode) node;
		
	}

	public BinaryTreeNode getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode parent) {
		this.parent = parent;
	}

	public Integer getEbene() {
		return ebene;
	}

	public void setEbene(Integer ebene) {
		this.ebene = ebene;
	}
	
}
