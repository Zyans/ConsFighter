package cons;

class Trail
{
	static final Trail path1 = new Trail(1);
	static final Trail path2 = new Trail(2);
	static final Trail path3 = new Trail(3);
	
	final Field t, b, l, r, tl, bl, tr, br, ntl, nbl, ntr, nbr;
	
	private static final String TRAILS_HOME_DIRECTORY = "trails/trail";//"pathes/path";
	
	Trail(int id)
	{
		t = new Field(TRAILS_HOME_DIRECTORY + id + "/t.png");
		b = new Field(TRAILS_HOME_DIRECTORY + id + "/b.png");
		l = new Field(TRAILS_HOME_DIRECTORY + id + "/l.png");
		r = new Field(TRAILS_HOME_DIRECTORY + id + "/r.png");
		tl = new Field(TRAILS_HOME_DIRECTORY + id + "/tl.png");
		bl = new Field(TRAILS_HOME_DIRECTORY + id + "/bl.png");
		tr = new Field(TRAILS_HOME_DIRECTORY + id + "/tr.png");
		br = new Field(TRAILS_HOME_DIRECTORY+ id + "/br.png");
		ntl = new Field(TRAILS_HOME_DIRECTORY + id + "/ntl.png");
		nbl = new Field(TRAILS_HOME_DIRECTORY + id + "/nbl.png");
		ntr = new Field(TRAILS_HOME_DIRECTORY + id + "/ntr.png");
		nbr = new Field(TRAILS_HOME_DIRECTORY + id + "/nbr.png");
	}
}