package cons;


public class Teleporter extends Field
{
	private Map destinationMap; 						// Map, zu der teleportiert wird
	private int destinationxPos; 						// X-Position, an die teleportiert wird
	private int destinationyPos; 						// Y-Position, an die teleportiert wird
	private int id; 									// wichtig zur Identifikation für spezifische Aktionen
	private boolean isActive; 							// Teleporter aktiv?
	
	private static Main main;

	static final Teleporter t0 = new Teleporter("houses/house3/b2.png",  MapUtils.getMaps()[1], 7, 15, 0, false);
	static final Teleporter t1 = new Teleporter(Field.TEXTURE, 0, 3, 16, MapUtils.getMaps()[0], 11, 10, 1, true);
	static final Teleporter t2 = new Teleporter(Field.TEXTURE, 1, 3, 16, MapUtils.getMaps()[0], 11, 10, 2, true);
	static final Teleporter t3 = new Teleporter(Field.TEXTURE, 3, 3, 16, MapUtils.getMaps()[2], 15, 0, 3, true);
	static final Teleporter t4 = new Teleporter(Field.TEXTURE, 3, 2, 16, MapUtils.getMaps()[1], 15, 0, 4, true);
	
	Teleporter(String imgPath, Map destinationMap, int destinationXPos, int destinationYPos, int id, boolean isActive)
	{
		super(imgPath);
		this.destinationMap = destinationMap;
		this.destinationxPos = destinationXPos;
		this.destinationyPos = destinationYPos;
		this.id = id;
		this.isActive = isActive;
		setCanGoThrough(isActive);
	}
	
	Teleporter(Texture texture, int x, int y, int height, Map destinationMap, int destinationXPos, int destinationYPos, int id, boolean isActive)
	{
		super(texture, x, y, height);
		this.destinationMap = destinationMap;
		this.destinationxPos = destinationXPos;
		this.destinationyPos = destinationYPos;
		this.id = id;
		this.isActive = isActive;
		setCanGoThrough(isActive);
	}
	
	@Override
	public void onWalkOn()
	{
		if(!isActive)
			return;
		
		doIdSpecificActionsBeforeTeleport();
		teleport();
		
		main.getPlayer().place.drawImage(main);
		main.drawMap();
		
		doIdSpecificActionsAfterTeleport();	
	}


	private void doIdSpecificActionsBeforeTeleport() {
		if(id == 1 || id == 2)
			SoundPlayer.soundDoorClose.play();
		if(id == 3 || id == 4)
		{
			SoundPlayer.soundStairs.play();
			main.getPlayer().walkOnY(-1);
		}
		if(id == 7)
		{
			for(int y = 0; y < 16; y+=2)
			{
				for(int x = 24; x <= 31; x++)
				{
					MapUtils.getMaps()[3].getPersons()[x][y].walkOnX(-1);
					MapUtils.getMaps()[3].getPersons()[x-1][y].setDirection(1);
				}
			}
				
			for(int y = 1; y < 16; y+=2)
			{
				for(int x = 7; x >= 0; x--)
				{
					MapUtils.getMaps()[3].getPersons()[x][y].walkOnX(1);
					MapUtils.getMaps()[3].getPersons()[x+1][y].setDirection(3);
				}
			}
		}
	}
	
	private void teleport() {
		main.getPlayer().setOnMap(destinationMap, destinationxPos, destinationyPos);
	}
	
	private void doIdSpecificActionsAfterTeleport() {
		if(id == 0)
			main.getPlayer().walkOnY(-1);
		if(id == 1 || id == 2)
		{
			main.getPlayer().walkOnY(1);
			t0.setImage("houses/house3/b2.png");
			main.getPlayer().place.drawImage1(main);
			main.drawMap();
			t0.setCanGoThrough(false);
		}
		if(id == 4)
		{
			t3.isActive = false;
			main.getPlayer().walkOnY(1);
			main.getPlayer().walkOnY(1);
			t3.isActive = true;
		}
		if(id == 3)
		{
			t4.isActive = false;
			main.getPlayer().walkOnY(1);
			main.getPlayer().walkOnY(1);
			t4.isActive = true;
		}
	}

	@Override
	public void onSpacePressed()
	{
		if(id == 0)
		{
			SoundPlayer.soundDoorOpen.play();
			setImage("door_open.png");
			main.getPlayer().place.drawImage1(main);
			main.drawMap();
			isActive = true;
			setCanGoThrough(isActive);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			main.getPlayer().walkOnY(-1);
		}
	}
	
	public static void load(Main m)
	{
		main = m;
	}
}