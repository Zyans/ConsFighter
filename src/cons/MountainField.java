package cons;

public class MountainField extends Field {

	private Mountain mountain;
	
	MountainField(Mountain mountain, Texture texture, int x, int y, boolean addToEditor) {
        super(texture, x, y, 16, addToEditor);
        setMountain(mountain);
        setCanGoThrough(false);
    }

	Mountain getMountain() {
		return mountain;
	}

	void setMountain(Mountain mountain) {
		this.mountain = mountain;
	}
}