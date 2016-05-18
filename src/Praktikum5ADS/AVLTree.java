package Praktikum5ADS;

public class AVLTree extends BinaryTree {
	
	public AVLTree() {
		// TODO Auto-generated constructor stub
	}

	public void recursiveBalance(BinaryTreeNode cur) {

		setBalance(cur);
		int balance = cur.getEbene();

		// check balance
		if (balance == -2) {

			if (ebene(cur.getLeftChild().getLeftChild()) >= ebene(cur.getLeftChild().getRightChild())) {
				cur = rotateRight(cur);
			} else {
				cur = doubleRotateLeftRight(cur);
			}
		} else if (balance == 2) {
			if (ebene(cur.getRightChild().getRightChild()) >= ebene(cur.getRightChild().getLeftChild())) {
				cur = rotateLeft(cur);
			} else {
				cur = doubleRotateRightLeft(cur);
			}
		}
		
		// looking for root
		if (cur.getParent() != null) {
			recursiveBalance(cur.getParent());
		} else {
			this.setRoot(cur);			
		}
	}

	private void setBalance(BinaryTreeNode cur) {
		cur.setEbene(ebene(cur.getRightChild()) - ebene(cur.getLeftChild()));
	}

	public BinaryTreeNode rotateLeft(BinaryTreeNode n) {

		BinaryTreeNode v = n.getRightChild();
		v.setParent(n.getParent());

		n.setRightChild(v.getLeftChild());

		if (n.getRightChild() != null) {
			n.getRightChild().setParent(n);
		}

		v.setLeftChild(n);
		n.setParent(v);

		if (v.getParent() != null) {
			if (v.getParent().getRightChild() == n) {
				v.getParent().setRightChild(v);
			} else if (v.getParent().getLeftChild() == n) {
				v.getParent().setLeftChild(v);
			}
		}

		setBalance(n);
		setBalance(v);

		return v;
	}

	public BinaryTreeNode rotateRight(BinaryTreeNode n) {

		BinaryTreeNode v = n.getLeftChild();
		v.setParent(n.getParent());

		n.setLeftChild(v.getRightChild());

		if (n.getLeftChild() != null) {
			n.getLeftChild().setParent(n);
		}

		v.setRightChild(n);
		n.setParent(v);

		if (v.getParent() != null) {
			if (v.getParent().getRightChild() == n) {
				v.getParent().setRightChild(v);
			} else if (v.getParent().getLeftChild() == n) {
				v.getParent().setLeftChild(v);
			}
		}

		setBalance(n);
		setBalance(v);

		return v;
	}

	public BinaryTreeNode doubleRotateLeftRight(BinaryTreeNode u) {
		u.setLeftChild(rotateLeft(u.getLeftChild()));
		return rotateRight(u);
	}

	public BinaryTreeNode doubleRotateRightLeft(BinaryTreeNode u) {
		u.setRightChild(rotateRight(u.getRightChild()));
		return rotateLeft(u);
	}

	private int ebene(BinaryTreeNode cur) {
		if (cur == null) {
			return -1;
		}
		if (cur.getLeftChild() == null && cur.getRightChild() == null) {
			return 0;
		} else if (cur.getLeftChild() == null) {
			return 1 + ebene(cur.getRightChild());
		} else if (cur.getRightChild() == null) {
			return 1 + ebene(cur.getLeftChild());
		} else {
			return 1 + Math.max(ebene(cur.getLeftChild()), ebene(cur.getRightChild()));
		}
	}

}
