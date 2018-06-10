package structure;

import java.util.ArrayList;
import java.util.Comparator;

public class Tree<T> {
	private Node<T> root;
	private Comparator<T> comparator;
	
	public void addChildTo(Node<T> node, Node<T> father) {
		if (father != null) {
			father.addChild(node);
		} else {
			root = node;
		}
	}

	public Node<T> search(String info) {
		if (info != null) {
			return search(root, info);
		}
		return null;
	}

	private Node<T> search(Node<T> actual, String info) {
		if (actual.toString().equalsIgnoreCase(info)) {
			return actual;
		} else if (!actual.getChildren().isEmpty()) {
			for (Node<T> node : actual.getChildren()) {
				Node<T> seacrh = search(node, info);
				if (seacrh != null) {
					return seacrh;
				}
			}
		}
		return null;
	}
	
	public Tree(Comparator<T> comparator) {
		this.comparator = comparator;
		this.root = null;
	}
	
	public boolean rootIsnull() {
		return (root == null);
	}
	


	public void print() {
		print(root);
	}

	private void print(Node<T> root) {
		if (root != null) {
			System.out.println(root);
			for (Node<T> node : root.getChildren()) {
				print(node);
			}
		}
	}
	
	/**
	 * Hacer el pasar todos los datos a un array
	 * @param root
	 * @param depth
	 */
	
	private ArrayList<Node<T>> getTreeData(ArrayList<Node<T>> list, Node<T> root){
		list.add(root);
		if (root.getChildren().size() > 0) {
			for (int i = 0; i < root.getChildren().size() ; i++) {
				getTreeData(list, root.getChildren().get(i));
			}
		}
		return list;
	}
	
	public ArrayList<Node<T>> getTreeData(ArrayList<Node<T>> list){
		return getTreeData(list, root);
	}

	//Imprimir con identacion
	private void printWithTab(Node<T> root, int depth) {
		if (root != null) {
			for (int i = 0; i < depth; i++) {
				System.out.print("\t");
			}
			System.out.println(root.toString());
			if (root.getChildren().size() > 0) {
				for (int i = 0; i < root.getChildren().size() ; i++) {
					printWithTab(root.getChildren().get(i), depth + 1);
				}
			}

		}
	}

	public void printWithTab() {
		printWithTab(root, 0);
	}

	//Buscar Padre
	public Node<T> searchFather(T info){
		return (root != null) ? root.searchFather(info) : null;
	}

	//Buscar Nodo
	public Node<T> searchNode(T info){
		return (root != null) ? root.searhNode(info) : null;
	}
	
	//Buscar Nodo por nombre
		public Node<T> searchNodeByName(T info){
			return (root != null) ? root.serchNodeByName(comparator, info) : null;
		}

	//Obtener path
	public ArrayList<Node<T>> getPath(Node<T> node){
		return (root != null) ? root.getPath(node) : null;
	}

	public boolean isLeaf(Node<T> root) {
		return (root.getChildren().size() == 0);
	}

	public Node<T> getRoot(){
		return root;
	}

	public Node<T> deleteNode(Node<T> node) {
		return root.deleteChild(node, node.getFather());
	}
}
