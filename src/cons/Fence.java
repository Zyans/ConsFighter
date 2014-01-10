package cons;

class Fence
{
	static final Fence fence1 = new Fence(1);
	static final Fence fence2 = new Fence(2);
	
	final Field tb, l, r, tl, bl, tr, br;
	
	private static final String FENCES_HOME_DIRECTORY = "fences/fence";
	
	Fence(int id)
	{
		tb = new Field(FENCES_HOME_DIRECTORY + id + "/tb.png") .setCanGoThrough(false);
		l  = new Field(FENCES_HOME_DIRECTORY + id + "/l.png")  .setCanGoThrough(false);
		r  = new Field(FENCES_HOME_DIRECTORY + id + "/r.png")  .setCanGoThrough(false);
		tl = new Field(FENCES_HOME_DIRECTORY + id + "/tl.png") .setCanGoThrough(false);
		bl = new Field(FENCES_HOME_DIRECTORY + id + "/bl.png") .setCanGoThrough(false);
		tr = new Field(FENCES_HOME_DIRECTORY + id + "/tr.png") .setCanGoThrough(false);
		br = new Field(FENCES_HOME_DIRECTORY + id + "/br.png") .setCanGoThrough(false);
	}
}