package cons;

class AttackType
{
	private PointType attackPointType; // angreifendes Körperteil
	private PointType hitPointType; // getroffenes Körperteil
	private double damageFactor; // spezifischer Schadensfaktor für Gegner
	private double energyFactor; // spezifischer Energieverbrauch bei Angriff
	private String name;
 
    AttackType (String name, PointType attackPoint, PointType hitPoint, double damage, double energy)
    {
	   setAttackPointType(attackPoint);
	   setHitPointType(hitPoint);
	   setDamage(damage);
	   setEnergy(energy);
	   setName(name);
    }

    PointType getAttackPointType() {
	   return attackPointType;
    }

    void setAttackPointType(PointType attackPointType) {
	   this.attackPointType = attackPointType;
    }

    PointType getHitPointType() {
	   return hitPointType;
    }

    void setHitPointType(PointType hitPointType) {
	   this.hitPointType = hitPointType;
    }

    double getDamage() {
	   return damageFactor;
    }

	void setDamage(double damage) {
		this.damageFactor = damage;
	}
	
	double getEnergy() {
		return energyFactor;
	}
	
	void setEnergy(double energy) {
		this.energyFactor = energy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	static final AttackType kinnhaken = new AttackType("Kinnhaken", PointType.rightArm, PointType.head, 8, 5);
	static final AttackType giftbiss = new AttackType("Giftbiss", PointType.head, PointType.torso, 7, 7);
	static final AttackType stachel = new AttackType("Stachel", PointType.tail, PointType.torso, 10, 8);
	static final AttackType biss = new AttackType("Biss", PointType.head, PointType.head,6, 4);
	static final AttackType linkerHaken = new AttackType("Linker Haken", PointType.leftArm, PointType.head,6, 4);
}