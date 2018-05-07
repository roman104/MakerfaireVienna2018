
public class Vec2 {
	public double x;
	public double y;
	
	Vec2(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double vzdialenost( Vec2 v){
		Vec2 t = new Vec2(0,0);

		
		return Math.abs(this.rozdiel(v).velkost());
	}
	
	public double velkost(){
		return Math.sqrt(x*x + y*y);
	}
	
	public Vec2 sucet(Vec2 v){
		return new Vec2(x + v.x, y + v.y);
	}
	public Vec2 rozdiel(Vec2 v){
		Vec2 t = new Vec2(0,0);
		
		if(x > v.x){
			t.x = x - v.x;
		}else{
			t.x = v.x - x;
		}
		
		if(y > v.y){
			t.y = y - v.y;
		}else{
			t.y = v.y - y;
		}
		return  t;
	}
	public String toString(){
		return new String("x: "+ x + "y: "+y);
	}
}
