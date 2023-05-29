public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r){
		first = f;
		rest = r;
	}

	public int size(){
		// base case
		if (rest == null){
			return 1;
		}else{
			return 1 + this.rest.size();
		}
	}

	public int IterativeSize(){
		IntList p = this;
		int len = 0;
		while(p != null){
			len += 1;
			p = p.rest;
		
		}
		return len;
	}

	public int get(int num){
		// base case
		if (num == 0){
			return this.first;
		}else{
			return this.get(num - 1);
		}
		
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(first);

        IntList current = rest;
        while (current != null) {
            sb.append(" -> ");
            sb.append(current.first);
            current = current.rest;
        }

        return sb.toString();
    }


	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);
	
		int size = L.size();
		int size2 = L.IterativeSize();
	}
}
