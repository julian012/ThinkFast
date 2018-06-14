package structure;

public class SimpleList<T> {

	public NodeSimpleList<T> head;

	public SimpleList() {
		head = null;
	}

	public NodeSimpleList<T> getHead(){
		return head;
	}

	public boolean addNode(NodeSimpleList<T> node){
		if(head != null){
			NodeSimpleList<T> actualNode = getHead();
			while(actualNode.getNext() != null){
				actualNode = actualNode.getNext();
			}
			actualNode.setNext(node);
		}else{
			head = node;
		}
		return true;
	}

	public boolean deleteNode(T info){
		if(head != null){
			if(head.getInfo().equals(info)){
				head = head.getNext();
			}else{
				NodeSimpleList<T> actualNode = getHead();
				while(actualNode.getNext() != null){
					if(actualNode.getNext().getInfo().equals(info)){
						actualNode.setNext(actualNode.getNext().getNext());
						return true;
					}else{
						actualNode = actualNode.getNext();
					}
				}
			}
		}
		return false;
	}

//	public String printList(){
//		String list = "[";
//		if (head != null) {
//			Node<T> actualNode = getHead();
//			while(actualNode != null){
//				if (actualNode.getNext() != null) {
//					list += actualNode.getInfo() + ", ";
//				}else{
//					list += actualNode.getInfo() + " ";
//				}
//				actualNode = actualNode.getNext();
//			}
//		}
//		return list + "]";
//	}
}
