package cons;

class Mountain
{
	static final Mountain mountain1 = new Mountain(9, 4, false);
	static final Mountain mountain2 = new Mountain(12, 4, false);
	static final Mountain mountain3 = new Mountain(9, 4, true);
	static final Mountain mountain4 = new Mountain(12, 4, true);
	
	final MountainField t, b, l, r, tl, bl, tr, br, m, cbl, ctl, cbr, ctr;
	final MountainField gras_tr, gras_tl;
	
	Mountain(int startX, int startY, boolean meadowmountain)
	{
		b = new MountainField(this, Field.TEXTURE, startX+1, startY+2, false);
		l = new MountainField(this, Field.TEXTURE, startX, startY+1, false);
		r = new MountainField(this, Field.TEXTURE, startX+2, startY+1, false);
		bl = new MountainField(this, Field.TEXTURE, startX, startY+2, false);
		br = new MountainField(this, Field.TEXTURE, startX+2, startY+2, false);
		tl = new MountainField(this, Field.TEXTURE, startX, startY, false);
		tr = new MountainField(this, Field.TEXTURE, startX+2, startY, false);
		cbl = new MountainField(this, Field.TEXTURE, startX, startY+4, false);
		cbr = new MountainField(this, Field.TEXTURE, startX+1, startY+4, false);
		
		if(meadowmountain) {
			gras_tr = new MountainField(this, Field.TEXTURE, startX+2, startY+3, false);
			gras_tl = new MountainField(this, Field.TEXTURE, startX, startY+3, false);
			t = new MountainField(this, Field.TEXTURE, startX+1, startY+3, false);
			m = (MountainField) new MountainField(this, Field.TEXTURE, 0, 0, true).setCanGoThrough(true);
		} else {
			gras_tr = null;
			gras_tl = null;
			t = new MountainField(this, Field.TEXTURE, startX+1, startY, false);
			m = new MountainField(this, Field.TEXTURE, startX+1, startY+1, true);
		}
		ctl = m;
		ctr = m;
	}
}