import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public final class Main extends Applet
{
	public static boolean ladenie = false;
	
	private static final long serialVersionUID = 1L;
	
	static boolean key[];
	
	public static Image scena;
	Graphics kresli;
	
	public final int sirka = 1024;
	public final int vyska = 600;
	
	public int absSirka;
	public int absVyska;
	
	private Image tvor[];
	private Image kulisa[];
	
	private Zakonik zakonik;
	
	private Prekreslenie prekreslenie;
	
	public static Random nahodne = new Random();
	private Font pismo;
	 
	
	public void init(){
		
		pismo = new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, 16);
		
		
		key = new boolean[255];
		
		setSize(sirka, vyska);	
		setBackground(Color.WHITE);
		
		//absSirka = getWidth();
		//absVyska = getHeight();
		
		scena = createImage(sirka, vyska);
		kresli = scena.getGraphics();
		
		tvor = new Image[4];
		
		tvor[0] = getImage(getCodeBase(),"data/rak.png");
		tvor[1] = getImage(getCodeBase(),"data/rybar.png");
		tvor[2] = getImage(getCodeBase(),"data/stuka.png");
		tvor[3] = getImage(getCodeBase(),"data/ryba.png");
		
		kulisa = new Image[3];
		kulisa[0] = getImage(getCodeBase(),"data/hladina.png");
		kulisa[1] = getImage(getCodeBase(),"data/kopce.png");
		kulisa[2] = getImage(getCodeBase(),"data/dno.png");
		
		zakonik = new Zakonik(this);
		zakonik.setPriority(Thread.NORM_PRIORITY);
		zakonik.start();
		
		prekreslenie = new Prekreslenie(this);
		prekreslenie.setPriority(Thread.NORM_PRIORITY);
		prekreslenie.start();
		
		setFocusable(true);
	}
	/*
	public void resize(int width, int height){
		absSirka = width;
		absVyska = height;
	}*/
	/*
	@Override
	public void resize(int width, int height) {
		absSirka = width;
		absVyska = height;
		// TODO Auto-generated method stub
		super.resize(width, height);
	}*/
	/*
	@Override
	public void setSize(int arg0, int arg1) {
		absSirka = arg0;
		absVyska = arg1;
		// TODO Auto-generated method stub
		super.setSize(arg0, arg1);
	}*/
	
	public void vykresliTvora(Tvor _tvor){
		kresli.drawRect(1, 1, sirka-2, vyska-2);	
		
		//TVORY
		if(_tvor.getName().equals("Rak")){
			int size_y = (int)((Rak)_tvor).VELKOST_OBRAZKA.y;
			size_y -= 5;
			
			int sx = 0;
			int sy = (85 * _tvor.fream)-1;
			int ssx = sx + tvor[0].getWidth(null);
			int ssy = (sy + size_y)-1;
			
			int dx = (int)_tvor.pozicia.x;
			int dy = (int)_tvor.pozicia.y;
			int ddx = dx + tvor[0].getWidth(null);
			int ddy = dy + size_y;
			
			kresli.drawImage(tvor[0], dx, dy, ddx, ddy, sx, sy, ssx, ssy,null);
			
			if(ladenie){
				kresli.setColor(Color.YELLOW);
				kresli.drawRect((int)_tvor.hraniceMin.x, (int)_tvor.hraniceMin.y, (int)(_tvor.hraniceMax.x-_tvor.hraniceMin.x), (int)(_tvor.hraniceMax.y-_tvor.hraniceMin.y));
				kresli.setColor(Color.BLACK);
				//kresli.drawOval((int)(_tvor.pozicia.x - 2), (int)(_tvor.pozicia.y - 2), 4, 4);
			}
		}
		if(_tvor.getName().equals("Rybar")){
			int sx = 0;
			int sy = (241 * _tvor.fream)-1;
			int ssx = sx + tvor[1].getWidth(null);
			int ssy = (sy + 241)-1;
			
			int dx = (int)_tvor.pozicia.x;
			int dy = (int)_tvor.pozicia.y;
			int ddx = dx + tvor[1].getWidth(null);
			int ddy = dy + 241;
			
			kresli.drawImage(tvor[1], dx, dy, ddx, ddy, sx, sy, ssx, ssy,null);
			//vlasec
			kresli.drawLine((int)(_tvor.pozicia.x + Rybar.BOD_UDICE.x), (int)(_tvor.pozicia.y + Rybar.BOD_UDICE.y), (int)(_tvor.pozicia.x + Rybar.BOD_UDICE.x), (int)(_tvor.pozicia.y + Rybar.BOD_UDICE.y + ((Rybar)(_tvor)).hlbkaVlasca));
			
			if(ladenie){		
				kresli.setColor(Color.RED);
				kresli.drawRect((int)_tvor.hraniceMin.x, (int)_tvor.hraniceMin.y, (int)(_tvor.hraniceMax.x-_tvor.hraniceMin.x), (int)(_tvor.hraniceMax.y-_tvor.hraniceMin.y));
				kresli.setColor(Color.BLACK);
			}
		}
		if(_tvor.getName().equals("Stuka")){
			int size_y = (int)((Stuka)_tvor).VELKOST_OBRAZKA.y;
			size_y -= 5;
			
			int sx = 0;
			int sy = (size_y * _tvor.fream)-1;
			int ssx = sx + tvor[2].getWidth(null);
			int ssy = (sy + size_y)-1;
			
			int dx = (int)_tvor.pozicia.x;
			int dy = (int)_tvor.pozicia.y;
			int ddx = dx + tvor[2].getWidth(null);
			int ddy = dy + size_y;
			
			kresli.drawImage(tvor[2], dx, dy, ddx, ddy, sx, sy, ssx, ssy,null);			
			
			if(ladenie){
				kresli.setColor(Color.BLUE);
				kresli.drawRect((int)_tvor.hraniceMin.x, (int)_tvor.hraniceMin.y, (int)(_tvor.hraniceMax.x-_tvor.hraniceMin.x), (int)(_tvor.hraniceMax.y-_tvor.hraniceMin.y));
				kresli.setColor(Color.BLACK);
			}
		}
		if(_tvor.getName().equals("Ryba")){
			int size_y = (int)((Ryba)_tvor).VELKOST_OBRAZKA.y;
			size_y -= 5;
			
			int sx = 0;
			int sy = (size_y * _tvor.fream)-1;
			int ssx = sx + tvor[3].getWidth(null);
			int ssy = (sy + size_y)-1;
			
			int dx = (int)_tvor.pozicia.x;
			int dy = (int)_tvor.pozicia.y;
			int ddx = dx + tvor[3].getWidth(null);
			int ddy = dy + size_y;
			
			kresli.drawImage(tvor[3], dx, dy, ddx, ddy, sx, sy, ssx, ssy,null);
			
			if(ladenie){	
				kresli.setColor(Color.GREEN);
				kresli.drawRect((int)_tvor.hraniceMin.x, (int)_tvor.hraniceMin.y, (int)(_tvor.hraniceMax.x -_tvor.hraniceMin.x), (int)(_tvor.hraniceMax.y -_tvor.hraniceMin.y));
				kresli.setColor(Color.BLACK);
			}
		}	
		
		if(ladenie){
			kresli.setColor(Color.GRAY);
			kresli.drawLine(400, 0, 400, (int)Zakonik.HLADINA);
		}
		
	}
	
	public void vykresliText(Vec2 pozicia, String text){
		kresli.setFont(pismo);
		kresli.drawString(text ,(int)pozicia.x, (int)pozicia.y);
	}
	
	public boolean keyDown(Event evt, int key){
		if (key <255)
		  Main.key[key] = true;
		return true;
	}
	
	public boolean keyUp(Event evt, int key){
		if (key <255)
			Main.key[key] = false;
		return true;
	}
	
	
	public void update(Graphics g)
    {
         paint(g);
    } 
	
	public void vycisti(){
		kresli.clearRect(0,0, sirka, vyska);
	}
	
	public void paint (Graphics g){	
		/*
		AlphaComposite ac = 
		      AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f);
		kresli.s */
		vycisti();
		//KULISA
		vykresliText(new Vec2((sirka / 2)-50,vyska- 10) , "pre pomoc stlaè h");
		//Kopce
		kresli.drawImage(kulisa[1], 0 ,0 ,null);
		//Dno
		kresli.drawImage(kulisa[2], 0 , vyska - kulisa[2].getHeight(null),null);

		zakonik.repaint();
		//KULISA
		//Hladina
		kresli.drawImage(kulisa[0], 0 ,(int)Zakonik.HLADINA ,null);
		
		//HELP
		if(key['h']){
			vykresliText(new Vec2((sirka / 2)-50,(vyska/1.5) - 10) , "w     - naštartuj");
			vykresliText(new Vec2((sirka / 2)-50,(vyska/1.5) - 25) , "s     - zhasni motor");
			vykresliText(new Vec2((sirka / 2)-50,(vyska/1.5) - 40) , "d     - doprava");
			vykresliText(new Vec2((sirka / 2)-50,(vyska/1.5) - 55) , "a     - do¾ava");
			vykresliText(new Vec2((sirka / 2)-50,(vyska/1.5) - 70) , "space - nahodenie");
		}
		//Vykresli scenu na obrazovku
        g.drawImage(scena,0, 0,getWidth(), getHeight(),this); 
	}
}
