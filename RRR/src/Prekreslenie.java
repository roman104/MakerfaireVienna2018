import java.applet.Applet;


public class Prekreslenie extends Thread{
	Applet a;
	boolean live;
	public Prekreslenie(Applet a){
		this.a = a;
		live = true;
	}
	
	public void run(){
		while(live){
			a.repaint();
			try {
				sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
