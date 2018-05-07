
public abstract class Tvor extends Thread{
		Vec2 pozicia;
		Vec2 bodChytenia;
		
		Vec2 hraniceMin;
		Vec2 hraniceMax;
		
		Vec2 smer;
		double rychlost;
		
		Tvor next;
		Tvor prev;
		
		boolean bZijem;
		boolean chyteny;
		
		public int fream;
		public int pocetFreamov;
		
		int tik;
		
		Tvor(){
			
			fream = 0;
			
			tik = 0;
			
			next = null;
			prev = null;
			bZijem = true;
			smer = new Vec2(1,0);
			rychlost = 0;
		}
		
		public static void init(Vec2 velkostOkna){
			velkostOkna = new Vec2(velkostOkna.x, velkostOkna.y);
		}
		
		public void run(){
			while(bZijem){
				zijem();
				tik++;
			}
		}
		protected abstract void zijem();
		
		protected void spi(long naCas){
			try {
				sleep(naCas);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		public abstract void zahin();
		
}
