package structure;

public class NodeSimpleList<T> {
	
	private T info;
	private NodeSimpleList<T> next;
	
	public NodeSimpleList(T info){
		setInfo(info);
		next = null;
	}
	
	public T getInfo(){
		return info;
	}
	
	public NodeSimpleList<T> getNext(){
		return next;
	}
	
	public void setInfo(T info){
		this.info = info;
	}
	
	public void setNext(NodeSimpleList<T> next){
		this.next = next;
	}
}
