import java.applet.AudioClip;


public class Zvuk {
	AudioClip zvuk;
	boolean prehrava;
	
	public Zvuk(AudioClip _zvuk){
		zvuk = _zvuk;
		prehrava = false;
	}
	
	public void prehraj(){
		if(prehrava == false){
			prehrava = true;
			zvuk.play();
			prehrava = false;
		}
	}
	
	public void doOkola(){
		if(prehrava == false){
			prehrava = true;
			zvuk.loop();
			
		}
	}
	
	public void stop(){
		if(prehrava){
			prehrava = false;
			zvuk.stop();
		}
	}
}
