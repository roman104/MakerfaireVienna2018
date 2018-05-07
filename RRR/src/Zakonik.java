import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;


public class Zakonik extends Thread{
	
	Tvor first, last;
	boolean live;
	
	//Skore
	int skore = 0;
	String textSkore = "Skóre: ";
	Vec2 poziciaSkore = new Vec2(900, 200);
	
	//Obrazovka
	Vec2 obrazovka;
	
	
	private static Zvuk zvuk[];
	
	protected static enum Zvuky{
		Motor, Start, Volnobeh, Rieka, Vypnutie, Udica, Rak, Naraz;
	}
	protected static enum ZvukyStav{
		DoOkola, Stop, Prehraj;
	}
	
	Main a;
	
	final protected double HACIK_VOLA = 10;
	final public static double HLADINA = 210;
	
	
	Zakonik(Main a){
		//obrazovka
		obrazovka = new Vec2(a.sirka, a.vyska);
		
		//Zijem
		live = true;
		
		//PRVY a POSLEDNY TVOR
		first = null;
		last = null;			
	
		this.a = a;
		
		//INICIALIZUJ ZVUKY
		zvuk = new Zvuk[8];
		
		try {
			zvuk[0] = new Zvuk(Applet.newAudioClip(new URL(a.getCodeBase() + "sound/rieka.wav")));
			zvuk[1] = new Zvuk(Applet.newAudioClip(new URL(a.getCodeBase() + "sound/start.wav")));
			zvuk[2] = new Zvuk(Applet.newAudioClip(new URL(a.getCodeBase() + "sound/motor_volnobeh.wav")));
			zvuk[3] = new Zvuk(Applet.newAudioClip(new URL(a.getCodeBase() + "sound/motor_chod.wav")));
			zvuk[4] = new Zvuk(Applet.newAudioClip(new URL(a.getCodeBase() + "sound/motor_vypnutie.wav")));
			zvuk[5] = new Zvuk(Applet.newAudioClip(new URL(a.getCodeBase() + "sound/udica.wav")));
			zvuk[6] = new Zvuk(Applet.newAudioClip(new URL(a.getCodeBase() + "sound/rak.wav")));
			zvuk[7] = new Zvuk(Applet.newAudioClip(new URL(a.getCodeBase() + "sound/naraz.wav")));
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//INICIALIZUJ HRACOV
		//rybnikMax = new Vec2(this.a.sirka, this.a.vyska);
		//rybnikMin = new Vec2(0, 300);	
		
		pridajTvora(new Rybar(new Vec2(400,0), new Vec2(0, 0), new Vec2(obrazovka.x, obrazovka.y)));
		pridajTvora(new Ryba(new Vec2(0, 0), new Vec2(obrazovka.x, obrazovka.y)));
		pridajTvora(new Ryba(new Vec2(0, 0), new Vec2(obrazovka.x, obrazovka.y)));
		pridajTvora(new Ryba(new Vec2(0, 0), new Vec2(obrazovka.x, obrazovka.y)));
		pridajTvora(new Ryba(new Vec2(0, 0), new Vec2(obrazovka.x, obrazovka.y)));
		pridajTvora(new Stuka(new Vec2(0, 0), new Vec2(obrazovka.x, obrazovka.y)));
		pridajTvora(new Rak(new Vec2(0, 0), new Vec2(obrazovka.x, obrazovka.y)));
		//pridajTvora(new Rak(new Vec2(0, 0), new Vec2(obrazovka.x, obrazovka.y)));
		ozivTvory();			
		
		//PUSTI RIEKU
		pustiZvuk(Zvuky.Rieka, ZvukyStav.DoOkola);
		
	}
	
	private void skontrolujKolizu(Tvor t){
		
		/*if(t.getName().equals("Rak")){

		}*/
		if(t.getName().equals("Rybar")&&(((Rybar)t).smerNavijania != 0)){
			Tvor tmp;
			tmp = first;
			
			while(tmp != null){
				if((tmp.getName().equals("Ryba"))||(tmp.getName().equals("Stuka"))||(tmp.getName().equals("Rak"))){
					//a.vykresliText(tmp.pozicia, String.format("Vzd: %.2f" , (float)tmp.pozicia.sucet(tmp.bodChytenia).vzdialenost(((Rybar)t).dajPoziciuVlasca())));
					if(tmp.pozicia.sucet(tmp.bodChytenia).vzdialenost(((Rybar)t).dajPoziciuVlasca()) < HACIK_VOLA){
						//skore
						if(tmp.getName().equals("Rak")){
							skore += 200;
						}else{
							if(tmp.getName().equals("Stuka")){
								skore += 500;
							}else{
								if(tmp.getName().equals("Ryba")){
									skore += 100;
								}
							}
						}
						tmp.zahin();
						odoberTvora(tmp);
					}
				}
				
				tmp = tmp.next;
			}
		}
		if(t.getName().equals("Stuka")){

		}
		/*if(t.getName().equals("Ryba")){

		}*/
	}

	
	protected static void pustiZvuk(Zvuky _zvuk, ZvukyStav stav){
		int idz = 0;
		
		switch(_zvuk){
		case Motor:
			idz = 3;
			break;	
		case Start:
			idz = 1;
			break;
		case Volnobeh:
			idz = 2;
			break;
		case Rieka:
			idz = 0;
			break;
		case Vypnutie:
			idz = 4;
			break;
		case Udica:
			idz = 5;
			break;
		case Rak:
			idz = 6;
			break;
		case Naraz:
			idz = 7;
			break;
		}
		
		switch(stav){
		case DoOkola:
			zvuk[idz].doOkola();
			break;	
		case Stop:
			zvuk[idz].stop();
			break;
		case Prehraj:
			zvuk[idz].prehraj();		
			break;
		}
		
	}
	
	
	private void pridajTvora(Tvor tvor){
		if((first == null)&&(last == null)){
			first = tvor;
			last = tvor;
		}
		
		if((first != null)&&(last != null)){
			last.next = tvor;
			tvor.prev = last;
			last = tvor;
		}
	}
	
	private void odoberTvora(Tvor tvor){
		if(tvor.prev != null){
			//niesom prvy
			tvor.prev.next = tvor.next;
			if(tvor.next != null){
				//niesom posledy
				tvor.next.prev = tvor.prev;
			}
			else{
				//som posledny
				last = tvor.prev;
			}
		}else{
			//som prvy
			if(tvor.next != null){
				//niekto tam este je
				tvor.next.prev = null;
				first = tvor.next.prev;
			}
			else{
				//som sam
				first = null;
				last = null;
			}
		}
		tvor.zahin();
		tvor = null;
	}
	
	private void ozivTvory(){
		Tvor tmp;
		tmp = first;
		
		while(tmp != null){
			tmp.setPriority(Thread.NORM_PRIORITY);
			tmp.start();
			
			tmp = tmp.next;
		}
	}
	
	public void repaint(){
		Tvor tmp;
		tmp = first;			
			
		while(tmp != null){
			a.vykresliTvora(tmp);
			//a.vykresliText(tmp.pozicia, String.format("Poz: %.2f %.2f rych: %f", tmp.pozicia.x,tmp.pozicia.y, tmp.rychlost));
			//a.vykresliText(tmp.pozicia, String.format("tik: %d",tmp.tik)); 
			tmp = tmp.next;
		}
		
		a.vykresliText(poziciaSkore, textSkore + skore);
	}
	
	public void run(){		
		while (live) {
			
			//kontrola kolizii
			Tvor tmp;
			tmp = first;
			
			if(first == last){
				//Ostal uz len rybar
				a.vykresliText(new Vec2((a.sirka / 2)-50,(a.vyska/1.5) - 70) , "VYCHYTAL SI VŠETKY RYBY GRATULUJEM");
			}
			
			while(tmp != null){

				skontrolujKolizu(tmp);
				tmp = tmp.next;
			}	
	
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
