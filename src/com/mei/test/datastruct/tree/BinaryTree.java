package com.mei.test.datastruct.tree;

import java.util.Stack;

public class BinaryTree {

	Node root;

	public BinaryTree(Node root) {
		this.root = root;
	}

	public static class Node<T> {
		T data;
		Node<T> leftChild;
		Node<T> rightChild;

		public Node(T data, Node<T> leftChild, Node<T> rightChild) {
			this.data = data;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
	}

	/**
	 * 前序遍历 递归实现
	 * 
	 * @param root
	 */
	public void preOrderTraverse(Node root) {
		if (root == null) {
			return;   
		}
		System.out.println("pre order:" + root.data);
		preOrderTraverse(root.leftChild);
		preOrderTraverse(root.rightChild);
	}

	/**
	 * 前序遍历 栈实现
	 * 
	 * @param root
	 */
	public void preOrderTraverse2(Node root) {
		if (root == null) {
			return;
		}
		Stack<Node<String>> stack = new Stack<BinaryTree.Node<String>>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node<String> cur = stack.pop();
			if (cur != null) {
				System.out.println("pre order:" + cur.data);
				stack.push(cur.rightChild);
				stack.push(cur.leftChild);
			}
		}
	}

	/**
	 * 中序遍历 递归实现
	 * 
	 * @param root
	 */
	public void midOrderTraverse(Node root) {
		if (root == null) {
			return;
		}
		midOrderTraverse(root.leftChild);
		System.out.println("mid order:" + root.data);
		midOrderTraverse(root.rightChild);
	}

	/**
	 * 中序遍历 栈实现
	 * 
	 * @param root
	 */
	public void midOrderTraverse2(Node root) {
		if (root == null) {
			return;
		}
		Stack<Node<String>> stack = new Stack<BinaryTree.Node<String>>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node<String> node = stack.pop();
			
			while (node!=null&&node.leftChild!=null) {
				stack.push(node.rightChild);
				stack.push(node);
				stack.push(node.leftChild);
				node=node.leftChild;
			}
			
		}
		System.out.println("mid order:" + root.data);
	}

	public void endOrderTraverse(Node root) {
		if (root == null) {
			return;
		}
		endOrderTraverse(root.leftChild);
		endOrderTraverse(root.rightChild);
		System.out.println("end order:" + root.data);
	}

	/**
	 * A
	 * 
	 * B C
	 * 
	 * D E F G
	 */
	public void createTree() {
		Node<String> nodeB = new Node<String>("B", null, null);
		Node<String> nodeC = new Node<String>("C", null, null);
		Node<String> nodeD = new Node<String>("D", null, null);
		Node<String> nodeE = new Node<String>("E", null, null);
		Node<String> nodeF = new Node<String>("F", null, null);
		Node<String> nodeG = new Node<String>("G", null, null);
		root.leftChild = nodeB;
		root.rightChild = nodeC;
		nodeB.leftChild = nodeD;
		nodeB.rightChild = nodeE;
		nodeC.leftChild = nodeF;
		nodeC.rightChild = nodeG;

	}

	public static void main(String[] args) {
		Node<String> root = new Node<String>("A", null, null);
		BinaryTree tree = new BinaryTree(root);
		tree.createTree();
		tree.preOrderTraverse(root);
		System.out.println("----------------------");
		tree.preOrderTraverse2(root);
	}

}
