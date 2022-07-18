
public class Cache{
	Block cache[];
	static String memory[];
	int hitRate,missRate,access;
	int s;
	public Cache(int size,String[] mem) {
		s=size;
		cache=new Block[size];
		for(int i = 0 ; i< size;i++) {
			cache[i] = new Block(i);
		}
		memory=mem;
	}
	
	public boolean isValid(int index) {
		return cache[index % s].valid;
	}
	
	public void insert(int index,String data) {
		//cache[index % s]= new Block(index);
		cache[index % s].valid = true;
		cache[index % s].tag = index / s;
		cache[index % s].data = data;
		memory[index] = cache[index % s].data;
	}
	
	
	public String get(int index) {
		access++;
		if(isValid(index)) {
			int tag = index / s;
			if(cache[index % s].tag == tag) {
				hitRate++;
				System.out.print("it's a hit data is  ");
				System.out.println(cache[index % s ].data);
				return cache[index % s ].data;
				
			}else {
				//miss
				missRate++;
				System.out.println("it's a miss");
				insert(index,memory[index]);
				System.out.println(cache[index % s ].data+" was inserted into the cache");
				return cache[index % s ].data;

			}
		}else {
			// miss
			missRate++;
			insert(index,memory[index]);
			System.out.println("it's a miss");
			System.out.println(cache[index % s ].data +" was inserted into the cache");
			return cache[index % s ].data;
		}
	}
	
	public void getMissRate() {
		System.out.println("miss rate is " + missRate + " out of " + access);
	}
	
	public void getHitRate() {
		System.out.println("hit rate is "+ hitRate + " out of " + access);
	}
	
}
