package cons;

class Fighter
{
	private double energyLimit;
	private double energy;
	private Point[] construction;
	private Attack[] attacks;
	private Main main;
	
	Fighter(Point[] construction, float startEnergy, Attack[] attacks, Main main)
	{
		this.setConstruction(construction);
		this.setEnergyLimit(startEnergy);
		this.setBattleEnergy(this.energyLimit);
		this.setMain(main);
		this.setAttacks(attacks);
	}
	
	double getBattleHealth()
	{
		double health = 0;
		for(final Point p : getConstruction())
			health += p.getBattleHealth();
		return health;
	}

	double getHealthLimit()
	{
		double health = 0;
		for(final Point p : getConstruction())
			health += p.getHealthLimit();
		return health;
	}
	
	String attackFighter(Attack attack, Fighter fighter)
	{
		String information = ""; // Return
		
		if(attack == null)
			information = "Keine Attacke ausgewählt.";
		else if(getBattleEnergy() >= attack.getAttackType().getEnergy())
		{
			int i;
			for(i = 0; i < fighter.getConstruction().length && fighter.getConstruction()[i].getPointType() != attack.getAttackType().getHitPointType(); i++){}
			if(i < fighter.getConstruction().length)
			{
				fighter.getConstruction()[i].setBattleHealth(fighter.getConstruction()[i].getBattleHealth() - attack.getAttackType().getDamage());
				information = ("Die Attacke hat gesessen.");
				if(fighter.getBattleHealth() <= 0 || getBattleHealth() <= 0)
				{
					information = "Kampf beendet";
					main.setInBattle(false);
					main.setWalkingEnabled(true);
				}
			}
			else
				information = "Nichts getroffen.";
			
			// Energie abziehen
			setBattleEnergy(getBattleEnergy() - attack.getAttackType().getEnergy());
		}
		else
			information = "Nicht genug Energie für die Attacke.";
		
		return information;
	}
	
	double getEnergyLimit() {
		return energyLimit;
	}

	void setEnergyLimit(double startEnergy) {
		this.energyLimit = startEnergy;
	}

	Point[] getConstruction() {
		return construction;
	}

	void setConstruction(Point[] construction) {
		this.construction = construction;
	}

	double getBattleEnergy() {
		return energy;
	}

	void setBattleEnergy(double battleEnergy) {
		this.energy = battleEnergy;
	}

	Attack[] getAttacks() {
		return attacks;
	}

	void setAttacks(Attack[] attacks) {
		this.attacks = attacks;
	}

	Main getMain() {
		return main;
	}

	void setMain(Main main) {
		this.main = main;
	}
}