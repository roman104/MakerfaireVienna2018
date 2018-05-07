
/* *
 *  
 *  MusÌ vedieù:
 *  - suradnice jazera max a min
 *  - chod˝ len po min y -> po dne
 * 
 * */
public class Rak extends Tvor{

	double preistRovnakymSmerom;
	
	public static final Vec2 VELKOST_OBRAZKA = new Vec2(111, 90);	 
	
	enum Animacie{
		Normal, Spi, Zmurkni, VykrocPravou, VykrocLavov;
	};
	
	public Rak(Vec2 _hraniceMin, Vec2 _hraniceMax){
		
		//Uloz hranice
		this.hraniceMax = _hraniceMax;
		this.hraniceMin = _hraniceMin;
		
		//Lavy horny roh
		//this.hraniceMin.x = velkostObrazka.x;		
		this.hraniceMin.y = this.hraniceMax.y - VELKOST_OBRAZKA.y;	//Po dne
		//Pravy dolny roh
		this.hraniceMax.x -= VELKOST_OBRAZKA.x;		
		this.hraniceMax.y = this.hraniceMin.y;						//Po dne
		
		bodChytenia = new Vec2(53,53);
		
		this.setName("Rak");
		
		pocetFreamov = 4;
		preistRovnakymSmerom = 0;
		
		pozicia = new Vec2(0, this.hraniceMax.y);
		
		pozicia.x = this.hraniceMin.x + ( Main.nahodne.nextInt((int) (this.hraniceMax.x - VELKOST_OBRAZKA.x - this.hraniceMin.x)));
	}
	

	public void zahin(){
		Zakonik.pustiZvuk(Zakonik.Zvuky.Rak, Zakonik.ZvukyStav.Stop);
		bZijem = false;
	}
	
	protected void animuj(Animacie a){
		switch (a){
		case Normal:
			fream = 0;
			spi(100);
			break;
		case Spi:
			fream = 3;
			break;
		case VykrocLavov:
			fream = 1;
			spi(100);
			break;	
		case VykrocPravou:
			fream = 2;
			spi(100);
			break;	
		}
	}

	protected void zijem() {
		// Chodiù po dne a spaù
		
		if(preistRovnakymSmerom <= 0){ //Ak som uz presiel co som mal
			//N·hodn˝ uhod od 0 - 2PI
			double uhol = Main.nahodne.nextDouble()*Math.PI*2;
			
			//vypocitaj smer
			smer.x = (smer.x*Math.cos(uhol)) - (smer.y*Math.sin(uhol));
			
			//aku vzdialenost prejdem
			preistRovnakymSmerom = Main.nahodne.nextDouble() * 500;
			
			//Priprav spanok
			animuj(Animacie.Spi);
			Zakonik.pustiZvuk(Zakonik.Zvuky.Rak, Zakonik.ZvukyStav.Stop);
			spi(3000);
			//Ked sa zobudim kracaj
			Zakonik.pustiZvuk(Zakonik.Zvuky.Rak, Zakonik.ZvukyStav.DoOkola);
		}
		else{
			//Animuj pr·Ëanie
			if(fream == 0){
				animuj(Animacie.VykrocLavov);
			}else{
				if(fream == 1){
					animuj(Animacie.VykrocPravou);
				}else{
					animuj(Animacie.Normal);
				}
			}
			//MeÚ r˝chlosù
			rychlost = Main.nahodne.nextDouble()*20;
			preistRovnakymSmerom -= rychlost;
		}
		
		//Uprav pozÌciu
		double sz = Math.sqrt((smer.x*smer.x) + (smer.y*smer.y));
		Vec2 tmp = new Vec2((pozicia.x + rychlost*(smer.x/sz)), (pozicia.y + rychlost*(smer.y/sz)));
	
		//Kontrola hranÌc
		if( (tmp.x < hraniceMax.x)&&
			/*(tmp.y < maxDole)&& */
			(tmp.x > hraniceMin.x)
			/*(tmp.y > maxHore)*/){	
			pozicia.x = tmp.x;
			pozicia.y = tmp.y;
		}else{
			//Ak nemozem tak rovnakym smerom uz nepojdem
			preistRovnakymSmerom = 0;
		}
		
		spi(30);	
		
	}

}
