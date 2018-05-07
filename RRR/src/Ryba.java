import java.util.Random;


/* *
 * 
 *  MusÌ vedieù:
 *  - suradnice jazera max a min
 *  - pl·va smerom od pohybujucej sa lode -> ci ma loÔ motor zapnuty a jej smer
 * */
public class Ryba extends Tvor{
	private static final double maxRychlost = 3;
	
	public static final Vec2 VELKOST_OBRAZKA = new Vec2(126, 64);	 
	
	double preistRovnakymSmerom;

	enum Animacie{
		Normal, Plavaj, Zmurkni;
	};
	
	public Ryba(Vec2 _hraniceMin, Vec2 _hraniceMax){	
		
		//Uloz hranice		
		this.hraniceMin = _hraniceMin;
		this.hraniceMax = _hraniceMax;

		//Lavy horny roh
		this.hraniceMin.x -= VELKOST_OBRAZKA.x;		
		this.hraniceMin.y = Zakonik.HLADINA;	
		//Pravy dolny roh
		this.hraniceMax.x += VELKOST_OBRAZKA.x;		
		this.hraniceMax.y -= Ryba.VELKOST_OBRAZKA.y; 	 //nie az na dno
		
		//Kde chyta hacik - relativne
		bodChytenia = new Vec2(20,30);
		
		//N·zov triedy
		this.setName("Ryba");
		
		pocetFreamov = 3;
		preistRovnakymSmerom = 0;
		
		//Zaciatocna pozicia
		pozicia = new Vec2(0, 0);
		pozicia.x = hraniceMin.x + Main.nahodne.nextInt((int) (hraniceMax.x - hraniceMin.x));
		pozicia.y = hraniceMin.y + Main.nahodne.nextInt((int) (hraniceMax.y - hraniceMin.y));
	}
	
	public void zahin(){
		bZijem = false;
	}

	protected void animuj(Animacie a){
		switch (a){
		case Normal:
			fream = 0;
			spi(100);
			break;
		case Zmurkni:
			fream = 2;
			break;
		case Plavaj:
			fream = 1;
			spi(100);
			break;	
		}
	}

	
	protected void zijem() {
		//pohn˙ù sa a ak ide motor tak od neho; ak nie zmenit smer a rychlos max o P percent
		
		
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
		
		//Kontrola hranÌc
		if( (tmp.x < hraniceMax.x)&&
			(tmp.y < hraniceMax.y)&& 
			(tmp.x > hraniceMin.x)&&
			(tmp.y > hraniceMin.y)){
			//Ak mozem tak idem
			pozicia.x = tmp.x;
			pozicia.y = tmp.y;
		}else{
			//Ak nemozem tak rovnakym smerom uz nepojdem
			preistRovnakymSmerom = 0;
		}
		
		//Animuj
		if((tik&64) == 64){
			animuj(Animacie.Normal);
		}else{		
			if((tik&128) == 128){
				animuj(Animacie.Zmurkni);
			}
		}
		
		spi(30);
	}
}
