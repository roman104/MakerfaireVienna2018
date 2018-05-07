
/* *
 * 
 * Potrbuje vediet
 * - polohu hacika
 * - polohu ryby
 * 
 * */

public class Rybar extends Tvor{
	
	//Motor je vypnuty
	boolean motor = false;
	
	public static final Vec2 VELKOST_OBRAZKA = new Vec2(440, 220);	
	
	public static final Vec2 BOD_UDICE = new Vec2(422, 87);
	
	private final double MAX_HLBKA_VLASCA 	= 600;
	private final double RYCHLOST_VLASCA 	= 5;
	private final double MAX_RYCHLOST_CLANA = 2;
	
	public double hlbkaVlasca;
	public double smerNavijania;
	
	enum Animacie{
		Normal, ZmurkniLodivod, ZmurkniRybar;
	};
	
	Rybar(Vec2 _pozicia, Vec2 _hraniceMin, Vec2 _hraniceMax){
			
		//Uloz hranice
		this.hraniceMax = _hraniceMax;
		this.hraniceMin = _hraniceMin;
		
		//Lavy horny roh
		this.hraniceMin.x -= (VELKOST_OBRAZKA.x - 20);		
		this.hraniceMin.y = Zakonik.HLADINA - VELKOST_OBRAZKA.y + 50;	
		//Pravy dolny roh
		this.hraniceMax.x -= VELKOST_OBRAZKA.x - 20;		
		this.hraniceMax.y = this.hraniceMin.y; 	 
		
		//ZaËiatoËn· pozÌcia
		this.pozicia = _pozicia;
		pozicia.y = this.hraniceMax.y;
		
		//Meno na identifikaciu triedy
		this.setName("Rybar");
		
		/*
		 *  M· 3 freamy (enum Animacie):
		 *  0 -> Normal
		 *  1 -> ZmurkniLodivod
		 *  2 -> ZmurkniRybar
		 */
		pocetFreamov = 3;
		
		/*
		 *  MusÌ byù < 0 
		 */
		hlbkaVlasca = -3;
		
		/*
		 *  0 -> ryb·r je pripraven˝
		 *  1 -> ryb·r nahadzuje
		 * -1 -> nav˝ja 
		 */
		smerNavijania = 0;
		
	}
	
	public void zahin(){
		Zakonik.pustiZvuk(Zakonik.Zvuky.Volnobeh, Zakonik.ZvukyStav.Stop);
		Zakonik.pustiZvuk(Zakonik.Zvuky.Motor, Zakonik.ZvukyStav.Stop);
	}
	
	protected void animuj(Animacie a){
		switch (a){
		case Normal:
			fream = 0;
			break;
		case ZmurkniLodivod:
			fream = 2;
			break;
		case ZmurkniRybar:
			fream = 1;
			break;	
		}
	}
	
	protected void nahadzuj(){
		if(hlbkaVlasca > MAX_HLBKA_VLASCA){ 	//ak je hacik na dne
			smerNavijania = -1;	
		}else{
			if(hlbkaVlasca < 0){			//ak je hacik navyt˝			
				smerNavijania = 0;
				Zakonik.pustiZvuk(Zakonik.Zvuky.Udica, Zakonik.ZvukyStav.Stop);
			}
		}
		//ak nav˝nam
		if(smerNavijania != 0){
			hlbkaVlasca += RYCHLOST_VLASCA * smerNavijania;
		}
	}
	
	protected void zijem() {
		//Ëi bola stlaËen· kl·vesnica
		Klavesnica();
		
		//Uprav pozÌciu ak je r˝chlost nenulova
		if(rychlost != 0){		
			rychlost = rychlost - ((rychlost) / 100);
		}	
		
		//Kontroluj hranice
		if(pozicia.x > hraniceMax.x ){			//Ak prejdes za pravu stranu
			Zakonik.pustiZvuk(Zakonik.Zvuky.Naraz, Zakonik.ZvukyStav.Prehraj);
			pozicia.x = hraniceMax.x - 10;
			//spi(10);
		}else{
			if(pozicia.x < hraniceMin.x){		//Ak prejdes za lavu stranu 
				Zakonik.pustiZvuk(Zakonik.Zvuky.Naraz, Zakonik.ZvukyStav.Prehraj);
				pozicia.x = hraniceMin.x  + 10;	
				//spi(10);
			}else{
				pozicia.x = pozicia.x + rychlost;
			}
		}
		
		//Animuj
		if((tik&160) == 160){
			animuj(Animacie.Normal);
		}else{		
			if((tik&110) == 110){
				animuj(Animacie.ZmurkniLodivod);
			}else{
				if((tik&140) == 140){
					animuj(Animacie.ZmurkniRybar);
				}
			}
		}
		
		//Udica
		if(smerNavijania != 0){ //iba ak nahadzujem
			nahadzuj();
		}
		
		spi(20);
	}

	public Vec2 dajPoziciuVlasca(){
		return new Vec2(pozicia.x + BOD_UDICE.x, pozicia.y + BOD_UDICE.y + hlbkaVlasca);
	}
	
	private void Klavesnica(){
		if(Main.key['w']){
			//Zapn˙ù motor
			if(motor == false){
				motor = true;
				Zakonik.pustiZvuk(Zakonik.Zvuky.Start, Zakonik.ZvukyStav.Prehraj);
				//cakaj kym natoci
				spi(800);
				Zakonik.pustiZvuk(Zakonik.Zvuky.Volnobeh, Zakonik.ZvukyStav.DoOkola);
			}
		}
		if(Main.key['s']){
			//Zhasn˙ù motor
			if(motor){
				motor = false;
				Zakonik.pustiZvuk(Zakonik.Zvuky.Volnobeh, Zakonik.ZvukyStav.Stop);
				Zakonik.pustiZvuk(Zakonik.Zvuky.Motor, Zakonik.ZvukyStav.Stop);
				Zakonik.pustiZvuk(Zakonik.Zvuky.Vypnutie, Zakonik.ZvukyStav.Prehraj);
			}
		}
		if(Main.key['d']){
			smer.x = 1;
			if(motor){
				double pomRychlost =  rychlost + 0.1;
				if(pomRychlost < MAX_RYCHLOST_CLANA){
					rychlost = pomRychlost;
				}
				Zakonik.pustiZvuk(Zakonik.Zvuky.Volnobeh, Zakonik.ZvukyStav.Stop);
				Zakonik.pustiZvuk(Zakonik.Zvuky.Motor, Zakonik.ZvukyStav.DoOkola);
			}
		}
		else{
			if((motor == true)&&(smer.x == 1)){
				Zakonik.pustiZvuk(Zakonik.Zvuky.Motor, Zakonik.ZvukyStav.Stop);
				Zakonik.pustiZvuk(Zakonik.Zvuky.Volnobeh, Zakonik.ZvukyStav.DoOkola);
			}
		}
		
		if(Main.key['a']){
			smer.x = -1;
			if(motor){
				double pomRychlost = rychlost - 0.1;
				if(pomRychlost > (MAX_RYCHLOST_CLANA * -1)){
					rychlost = pomRychlost;
				}
				Zakonik.pustiZvuk(Zakonik.Zvuky.Volnobeh, Zakonik.ZvukyStav.Stop);
				Zakonik.pustiZvuk(Zakonik.Zvuky.Motor, Zakonik.ZvukyStav.DoOkola);
			}
		}
		else{
			if((motor == true)&&(smer.x == -1)){
				Zakonik.pustiZvuk(Zakonik.Zvuky.Motor, Zakonik.ZvukyStav.Stop);
				Zakonik.pustiZvuk(Zakonik.Zvuky.Volnobeh, Zakonik.ZvukyStav.DoOkola);
			}
		}
		
		if(Main.key[32]){
			if(smerNavijania == 0){
				smerNavijania = 1;
				hlbkaVlasca = 1;
				Zakonik.pustiZvuk(Zakonik.Zvuky.Udica, Zakonik.ZvukyStav.DoOkola);
			}
		}
	}
}
