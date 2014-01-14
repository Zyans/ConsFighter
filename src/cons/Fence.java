package cons;

class Fence
{
	static final Fence fence1 = new Fence(1);
	static final Fence fence2 = new Fence(2);
	
	final Field tb, l, r, tl, bl, tr, br;
	
	private static final String FENCES_HOME_DIRECTORY = "fences/fence";
	
	Fence(int id)
	{
		tb = new Field(FENCES_HOME_DIRECTORY + id + "/tb.png", false) .setCanGoThrough(false);
		l  = new Field(FENCES_HOME_DIRECTORY + id + "/l.png", false)  .setCanGoThrough(false);
		r  = new Field(FENCES_HOME_DIRECTORY + id + "/r.png", false)  .setCanGoThrough(false);
		tl = new Field(FENCES_HOME_DIRECTORY + id + "/tl.png", false) .setCanGoThrough(false);
		bl = new Field(FENCES_HOME_DIRECTORY + id + "/bl.png", false) .setCanGoThrough(false);
		tr = new Field(FENCES_HOME_DIRECTORY + id + "/tr.png", false) .setCanGoThrough(false);
		br = new Field(FENCES_HOME_DIRECTORY + id + "/br.png", false) .setCanGoThrough(false);
	}
}