package graficos;

import java.awt.Canvas;
import java.awt.Dimension;
import java.util.concurrent.SynchronousQueue;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private boolean isRunning = true;
	public static JFrame frame;
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private final int SCALE = 3;
	
	public Game()
	{
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
	}
	
	public void initFrame() {
		frame = new JFrame("Game 01");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		
	}
	
	public static void main(String args[]) {
		
		Game game = new Game();
		game.start();
		
	}
	
	// Fun��o respons�vel pelo update (atualiza��o do game)
	public void tick() {
		
	}
	
	// M�todo respons�vel pelos gr�ficos
	public void render() {
		
	}
	
	@Override
	public void run() {

		// Pega o �ltimo momento em nanotime 
		// porque a precis�o aumenta.
		long lastTime = System.nanoTime();
		
		// Uma constante que carrega o n�mero de frames
		double amountOfTicks = 60.0;
		
		// Esse abaixo � o momento certo que deve
		// ser feito o update do game
		// O valor de 100 milh�es abaixo � o mesmo
		// que 1 segundo em nano segundos.
		double ns = 1000000000 / amountOfTicks;
		
		double delta = 0;
		
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunning) {
			
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			
			lastTime = now;
			
			if(delta >= 1) {
				// Sempre se deve atualizar antes de renderizar
				tick();
				render();
				frames++;
				delta--;
			}
			
			// Para confer�ncia dos FPS
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
			
		}
		
	}

}
