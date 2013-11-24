package cons;

class House
{
	private Field[] b, t;
	private int depth;
	
	// === Haus 5 ===
	static final House house1 = new House(Field.TEXTURE, 1, 9, 0, 4, 48, 1);
	static final House house2 = new House(Field.TEXTURE, 1, 13, 0, 5, 48, 1);
	static final House house3 = new House(Field.TEXTURE, 1, 18, 0, 5, 64, 1);
	static final House house4 = new House(4, 4, 1);
	static final House house5 = new House(5, 9, 3);
	static final House house6 = new House(6, 7, 3);
	static final House house7 = new House(7, 10, 4);
	static final House house8 = new House(8, 5, 1);
	
	private static final String HOUSES_HOME_DIRECTORY = "houses/house";
	
	House(int id, int width, int depth)
	{
		this.setDepth(depth);
		
		setB(new Field[width]);
		setT(new Field[width]);
		
		for(int i = 0; i < width; i++)
		{
			getB()[i] = new Field(HOUSES_HOME_DIRECTORY + id + "/b" + (i+1) + ".png").setCanGoThrough(false);
			getT()[i] = new Field(HOUSES_HOME_DIRECTORY + id + "/t" + (i+1) + ".png");
		}
	}
	
	House(Texture texture, int id, int x, int y, int width, int heightOfTop, int depth)
	{
		this.setDepth(depth);
		
		setB(new Field[width]);
		setT(new Field[width]);
		
		for(int i = 0; i < width; i++)
		{
			getT()[i] = new Field(texture, x + i, y, heightOfTop, false);
			getB()[i] = new Field(texture, x + i, y+heightOfTop/16, 16, false).setCanGoThrough(false);
		}
	}
	
	public Field[] getB() {
		return b;
	}

	public void setB(Field[] b) {
		this.b = b;
	}

	public Field[] getT() {
		return t;
	}

	public void setT(Field[] t) {
		this.t = t;
	}
	
	int getDepth() {
		return depth;
	}

	void setDepth(int depth) {
		this.depth = depth;
	}
	
	static void load()
	{
		house1.getB()[1].setMessage("Die Tür ist verschlossen ...");
		house2.getB()[1].setMessage("Die Tür ist verschlossen ...");
		house3.getB()[1].setMessage("Die Tür ist verschlossen ...");
		house4.getB()[1].setMessage("Die Tür ist verschlossen ...");
		house5.getB()[4].setMessage("Die Tür ist verschlossen ...");
		house8.getB()[3].setMessage("Die Tür ist verschlossen ...");
	}

}