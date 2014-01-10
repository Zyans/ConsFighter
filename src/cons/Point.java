package cons;

class Point
{
	private double offense;
	private double defense;
	private PointType type;
	private double battleHealth;
	private double healthLimit;
	
	Point(double offense, double defense, PointType type, double health)
	{
		setOffense(offense);
		setDefense(defense);
		setPointType(type);
		setBattleHealth(health);
		setHealthLimit(health);
	}

	double getOffense() {
		return offense;
	}

	void setOffense(double offense) {
		this.offense = offense;
	}

	double getDefense() {
		return defense;
	}

	void setDefense(double defense) {
		this.defense = defense;
	}

	PointType getPointType() {
		return type;
	}

	void setPointType(PointType type) {
		this.type = type;
	}

	public double getBattleHealth() {
		return battleHealth;
	}

	public void setBattleHealth(double getBattleHealth) {
		this.battleHealth = getBattleHealth;
	}

	public double getHealthLimit() {
		return healthLimit;
	}

	public void setHealthLimit(double startHealth) {
		this.healthLimit = startHealth;
	}
}