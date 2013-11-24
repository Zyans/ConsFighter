package cons;

public class Loop extends Thread
{
	Main main;
	
	private final int TARGET_FPS = 60;
	private final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	private static long lastFpsTime = 0;
	private static int fps = 0;
	private static int renderFps = 0;
	
	public Loop(Main main)
	{
		this.main = main;
	}
	
	@Override
	public void run()
	{
		long lastLoopTime = System.nanoTime();
		while(!main.isRunning()) {}
		main.repaint();
		while(main.isRunning()) {
				long now = System.nanoTime();
				
				long updateLength = now - lastLoopTime;
				lastLoopTime = now;
				
				double delta = updateLength / OPTIMAL_TIME;
				lastFpsTime += updateLength;
				fps++;
				
				if (lastFpsTime >= 1000000000) {
					renderFps = fps;
					fps = 0;
					lastFpsTime = 0;
				}
				
				main.getPlayer().walk();
				
				try {
					Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
				} catch(Exception e) {
				}
		}
	}
}