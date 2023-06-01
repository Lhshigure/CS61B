public class AList<T> implements List61B<T>{
	private T[] items;
	private int size;
	public AList(){
		items = (T[])new Object[100];
		size = 0;
	}

	private void resize(int capacity){
		T[] a = (T[])new Object[capacity];
		System.arraycopy(items, 0, a, 0, size);
		items = a;
			
	}
	public void addFirst(T x){

	}
	public void addLast(T x){
		if(size == items.length){
			resize(size * 10);
		}
		items[size] = x;
		size ++;
	}

	public T getFirst(){
		return items[0];
	}
	public T getLast(){
		return items[size - 1];
	}

	public T get(int i){
		return items[i - 1];
	}

	public void insert(T item, int position){
		T[] newItems = (T[]) new Object[items.length + 1];

		System.arraycopy(items, 0, newItems, 0, position);
		newItems[position] = item;

		System.arraycopy(items, position, newItems, position + 1, items.length - position);
		items = newItems;

	}
	
	public int size(){
		return size;
	}
	public T removeLast(){
		T x = getLast();
		items[size] = null;
		size --;
		return x;
	}
}