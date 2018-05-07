
/* *
 * 
 * MusÌ vedieù:
 * - s˙radnice jazera max a min
 * - s˙radnice ryby ak je v radiuse r
 * 
 * */
public class Stuka extends Tvor{

	double preistRovnakymSmerom;
	
	public static final Vec2 VELKOST_OBRAZKA = new Vec2(288, 86);	
	
	enum Animacie{
		Normal, Plavaj, Plavaj2, Zmurkni;
	};
	
	
	public Stuka(Vec2 _hraniceMin, Vec2 _hraniceMax){	
		
		//Uloz hranice
		this.hraniceMax = _hraniceMax;
		this.hraniceMin = _hraniceMin;
		
		//Lavy horny roh
		//this.hraniceMin.x -= velkostObrazka.x;		
		this.hraniceMin.y = Zakonik.HLADINA;	
		//Pravy dolny roh
		//this.hraniceMax.x -= VELKOST_OBRAZKA.x;		
		this.hraniceMax.y -= Ryba.VELKOST_OBRAZKA.y; //nie az na dno
		
		bodChytenia = new Vec2(32,40);
		
		this.setName("Stuka");
		
		pozicia = new Vec2(0, 0);

		
		preistRovnakymSmerom = 0;
		pocetFreamov = 4;
		
		pozicia.x = hraniceMin.x + ( Main.nahodne.nextInt((int) (hraniceMax.x - VELKOST_OBRAZKA.x - hraniceMin.x)));
		pozicia.y = hraniceMin.y + ( Main.nahodne.nextInt((int) (hraniceMax.y - VELKOST_OBRAZKA.y - hraniceMin.y)));
	}

	public void zahin(){
		bZijem = false;
		//Zakonik.pustiZvuk(Zakonik.Zvuky.Rak, Zakonik.ZvukyStav.Stop);
	}
	
	protected void animuj(Animacie a){
		switch (a){
		case Normal:
			fream = 0;
			spi(100);
			break;
		case Plavaj:
			fream = 2;
			spi(100);
			break;
		case Plavaj2:
			fream = 1;
			spi(100);
			break;	
		case Zmurkni:
			fream = 3;
			//spi(200);
		break;	
	}
	}
	
	protected void zijem() {
		// Ak mam v r·diuse R rybu tak zmenim k nej smer; ak nie meniù smer len o P percent
		
		if(preistRovnakymSmerom <= 0){
			//N·hodn˝ uhod od 0 - 2PI
			double uhol = Main.nahodne.nextDouble()*Math.PI*2;
		
			smer.x = (smer.x*Math.cos(uhol)) - (smer.y*Math.sin(uhol));
			smer.y = (smer.y*Math.sin(uhol)) + (smer.x*Math.cos(uhol));
			
			preistRovnakymSmerom = Main.nahodne.nextDouble() * 500;
		}
		else{
			rychlost = Main.nahodne.nextDouble()*3;
			preistRovnakymSmerom -= rychlost;
		}
		//Uprav pozÌciu
		double sz = Math.sqrt((smer.x*smer.x) + (smer.y*smer.y));
		Vec2 tmp = new Vec2((pozicia.x + rychlost*(smer.x/sz)), (pozicia.y + rychlost*(smer.y/sz)));
		
		if( (tmp.x < hraniceMax.x)&&
			(tmp.y < hraniceMax.y)&& 
			(tmp.x > hraniceMin.x)&&
			(tmp.y > hraniceMin.y)){	
			pozicia.x = tmp.x;
			pozicia.y = tmp.y;
		}else{
			preistRovnakymSmerom = 0;
		}
		
		if((tik&180) == 180){
			animuj(Animacie.Normal);
		}else{		
			if((tik&128) == 128){
				animuj(Animacie.Zmurkni);
			}
		}
		
		spi(30);		
	}

}
