package cons;

public class Water extends Field {
	Water(Texture texture, int x, int y, int height, boolean addToEditor) {
        super(texture, x, y, height, addToEditor);
        setCanGoThrough(false);
    }
}