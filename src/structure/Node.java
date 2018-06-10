package structure;

import java.util.ArrayList;
import java.util.Comparator;

public class Node <T> {
	
	private T info;
	private Node<T> father;
	private ArrayList<Node<T>> children;
	
	public Node(T info, Node<T> father) {
		this.father = father;
		this.info = info;
		children = new ArrayList<Node<T>>();
	}
	
	public T getInfo() {
		return info;
	}
	
	/**
	 * Patron fachada
	 * 
	 */
	public void addChild(Node<T> newChild) {
		children.add(newChild);
	}
	
	/**
	 * Programacion funcional
	 */
	public void printChildren() {
		//children.forEach(System.out::print());
		for(Node<T> node : children) {
			System.out.println(node);
		}
	}
	
	public ArrayList<Node<T>> getChildren() {
		return children;
	}
	
	//Buscar Nodo
	public Node<T> searhNode(T infoSearch){
		if (infoSearch.equals(info)) {
			return this;
		}else if(children.size() == 0) {
			return null;
		}else {
			for (int i = 0; i < children.size(); i++) {
				Node<T> child = children.get(i).searhNode(infoSearch);
				if (child != null) {
					return child;
				}
			}
			return null;
		}
	}
	
	//Buscar Padre
	public Node<T> searchFather(T infoSearch){
		if (infoSearch.equals(info)) {
			return this;
		}else if(children.size() == 0) {
			return null;
		}else {
			for (int i = 0; i < children.size(); i++) {
				Node<T> child = children.get(i).searhNode(infoSearch);
				if (child != null) {
					return child.getFather();
				}
			}
			return null;
		}
	}
	
	//Buscar padre por nombre
	public Node<T> serchNodeByName(Comparator<T> compare, T name){
		if (compare.compare(name, info) > 0 ) {
			return this;
		}else if(children.size() == 0) {
			return null;
		}else {
			for (int i = 0; i < children.size(); i++) {
				Node<T> child = children.get(i).serchNodeByName(compare, name);
				if (child != null) {
					return child;
				}
			}
			return null;
		}
	}
	
	//Path nodeo
	public ArrayList<Node<T>> getPath(Node<T> node){
		ArrayList<Node<T>> listPath = new ArrayList<Node<T>>();
		return getPath(listPath, node);
	}
	
	private ArrayList<Node<T>> getPath(ArrayList<Node<T>> nodeList, Node<T> node) {
		if (node.getFather() != null) {
			nodeList.add(node);
			return getPath(nodeList, node.getFather());
		}else {
			return nodeList;
		}
	}
	
	public Node<T> getFather(){
		return father;
	}
	//Obtener hermano anterior
	public Node<T> getBeforeNodeChild(Node<T> node) {
		Node<T> father = node.getFather();
		if (!father.getChildren().get(0).equals(node)) {
			for (int i = 0; i < father.getChildren().size(); i++) {
				if (father.getChildren().get(i).equals(node)) {
					return father.getChildren().get(i - 1);
				}
			}
		}
		return null;
	}
	
	//Obetner el hermano siguiente
	public Node<T> getLastNodeChild(Node<T> node) {
		Node<T> father = node.getFather();
		int size = father.getChildren().size();
		if (!father.getChildren().get(size - 1).equals(node) ) {
			for (int i = 0; i < father.getChildren().size(); i++) {
				if (father.getChildren().get(i).equals(node)) {
					return father.getChildren().get(i + 1);
				}
			}
		}
		return null;
	}
	
	//Lista de Hermanos de un nodo
	public ArrayList<Node<T>> getSiblings(Node<T> node){
		return node.getFather().getChildren();
	}
	
	
	//Borrar Hijo
	public Node<T> deleteChild(Node<T> node, Node<T> father){
		for (int i = 0; i < father.getChildren().size(); i++) {
			if (father.getChildren().get(i).getInfo().equals(node.getInfo())) {
				return father.getChildren().remove(i);
			}
		}
		return null;
	}
	
	
	@Override
	public String toString() {
		return info.toString();
	}
}
