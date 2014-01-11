package cons;

class Cons extends Fighter
{
	private ConsType type;
	
	Cons(Point[] construction, float startEnergy, ConsType type, Attack[] attacks, Main main)
	{
		super(construction, startEnergy, attacks, main);
		setType(type);
	}

	void setType(ConsType type) {
		this.type = type;
	}

	ConsType getType() {
		return this.type;
	}
}