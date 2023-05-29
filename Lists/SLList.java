senpublic class SLList<Arbitrary>{
	private IntNode sentinel;
	private IntNode last;
	private int size;

	private class IntNode{
		public Arbitrary item;
		public IntNode next;

		public IntNode(Arbitrary i, IntNode n){
			item = i;
			next = n;
		}
	}
	

	public SLList(Arbitrary x){
		sentinel = new IntNode(66, null);  // 这里报错了，sentinel = new IntNode(null, null)
		sentinel.next = new IntNode(x ,null);
		size = 1;
	}

	public SLList(){
		sentinel = new IntNode(66, null); // sentinel = new IntNode(null, null)
		size = 0;
	}

	public void addFisrt(Arbitrary x){
		sentinel.next = new IntNode(x, sentinel.next);
		size += 1;
	}

	public void addLast(Arbitrary x){
		IntNode p = sentinel;
		while(p.next != null){
			p = p.next;
		}
		p.next = new IntNode(x, null);
		size += 1;
	}

	public Arbitrary getFirst(){
		return sentinel.next.item;
	}

/*	private static int size(IntNode p){
		if (p.next == null){
			return 1;
		}
		return 1 + size(p.next);
	}

	public int size(){
		return size(first);
	}
*/
	public int size(){
		return size;
	}

	public static void main(String args[]){
		SLList<Integer> L = new SLList<>();
		L.addFisrt(10);
		L.addFisrt(5);
		L.addLast(20);
		//L.addLast(20);
		System.out.println(L);
		System.out.println(L.size());
	


	}





	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        IntNode p = sentinel.next;
        while (p != null) {
            sb.append(p.item);
            if (p.next != null) {
                sb.append(", ");
            }
            p = p.next;
        }
        sb.append("]");
        return sb.toString();
    }
}