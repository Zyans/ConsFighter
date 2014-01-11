package cons;

public class Water extends Field {

	Water(Texture texture, int x, int y, boolean addToEditor) {
        super(texture, x, y, 16, addToEditor);
        setCanGoThrough(false);
    }
}