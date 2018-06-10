package com.mei.test.datastruct.tree;

/**
 * ¶þ²æÅÅÐòÊ÷
 */
public class SearchBinaryTree {

	public class TreeNode {
		int data;
		TreeNode leftChild;
		TreeNode rightChild;
		TreeNode parent;

		public TreeNode(int data) {
			this.data = data;
		}
	}

	private TreeNode root;

	public TreeNode put(int data) {
		if (root == null) {
			root = new TreeNode(data);
			return root;
		}

		TreeNode node = root;
		TreeNode parent = null;
		while (node != null) {
			parent = node;
			if (data < node.data) {
				node = node.leftChild;
			} else if (data > node.data) {
				node = node.rightChild;
			} else {
				return node;
			}
		}
		TreeNode child = new TreeNode(data);
		if (parent.data > data) {
			parent.leftChild = child;
		} else {
			parent.rightChild = child;
		}
		child.parent = parent;
		return child;
	}
	
	public void midOrderTraverse(TreeNode node) {
		if (node==null) {
			return;
		}
		midOrderTraverse(node.leftChild);
		System.out.println("mid order:"+node.data);
		midOrderTraverse(node.rightChild);
	}
	
	public void preOrderTraverse(TreeNode node) {
		if (node==null) {
			return;
		}
		System.out.println("pre order:"+node.data);
		preOrderTraverse(node.leftChild);
		preOrderTraverse(node.rightChild);
	}
	
	public TreeNode search(int data) {
		TreeNode node=root;
		while (node!=null) {
			if (node.data==data) {
				return node;
			}else if (node.data>data) {
				node=node.leftChild;
			}else {
				node=node.rightChild;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		SearchBinaryTree tree=new SearchBinaryTree();
		int[] arrays=new int[]{10,5,3,8,7,15,14,20};
		for (int i = 0; i < arrays.length; i++) {
			tree.put(arrays[i]);
		}
		tree.midOrderTraverse(tree.root);
//		tree.preOrderTraverse(tree.root);
		TreeNode node=tree.search(21);
		System.out.println(node==null?null:node.data);
	}

}
