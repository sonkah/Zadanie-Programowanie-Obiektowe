package app;

public class Record implements Comparable<Record>{
	
	private int points;
	private String name;
	
	public Record(int p, String n){
		this.points = p;
		this.name = n;
	}
	
	private String spaces(){
		String s = "  ";
		for(int i = 0; i < (21 - name.length()); i++){
			s = (s + "  ");
		}
		s = s + "\t";
		return s;
	}
	
	public String toString(){
		return (name + this.spaces() + points);
	}
	public int getPoints(){
		return this.points;
	}
	public String getName(){
		return this.name;
	}

	@Override
	public int compareTo(Record o) {
		Integer comp = new Integer(this.points);
		Integer comp2 = new Integer(o.points);
		int l = comp.compareTo(comp2);
		return l;
	}
	
	
}
