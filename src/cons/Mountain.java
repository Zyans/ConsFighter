package cons;

class Mountain
{
	static final Mountain mountain1 = new Mountain(1);
	static final Mountain mountain2 = new Mountain(2);
	
	final Field t, b, l, r, tl, bl, tr, br, m, gras_t, gras_tr, gras_tl;
	
	private static final String MOUNTAIN_HOME_DIRECTORY = "mountains/mountain";
	
	Mountain(int id)
	{
		t = new Field(MOUNTAIN_HOME_DIRECTORY + id + "/t.png").setCanGoThrough(false);
		b = new Field(MOUNTAIN_HOME_DIRECTORY + id + "/b.png").setCanGoThrough(false);
		l = new Field(MOUNTAIN_HOME_DIRECTORY + id + "/l.png").setCanGoThrough(false);
		r = new Field(MOUNTAIN_HOME_DIRECTORY + id + "/r.png").setCanGoThrough(false);
		tl = new Field(MOUNTAIN_HOME_DIRECTORY + id + "/tl.png").setCanGoThrough(false);
		bl = new Field(MOUNTAIN_HOME_DIRECTORY + id + "/bl.png").setCanGoThrough(false);
		tr = new Field(MOUNTAIN_HOME_DIRECTORY + id + "/tr.png").setCanGoThrough(false);
		br = new Field(MOUNTAIN_HOME_DIRECTORY+ id + "/br.png").setCanGoThrough(false);
		m = new Field(MOUNTAIN_HOME_DIRECTORY + id + "/m.png");
		gras_t = new Field(MOUNTAIN_HOME_DIRECTORY + id + "/gras_t.png").setCanGoThrough(false);
		gras_tl = new Field(MOUNTAIN_HOME_DIRECTORY + id + "/gras_tl.png").setCanGoThrough(false);
		gras_tr = new Field(MOUNTAIN_HOME_DIRECTORY + id + "/gras_tr.png").setCanGoThrough(false);
	}
}