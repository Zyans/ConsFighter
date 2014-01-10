package cons;

class Attack
{
	private double learned;
	private AttackType attackType;
 
    Attack(AttackType attackType, double learned)
    {
	   setLearned(learned);
	   setAttackType(attackType);
    }
    
	double getLearned() {
		return learned;
	}
	
	void setLearned(double learned) {
		this.learned = learned;
	}

	public AttackType getAttackType() {
		return attackType;
	}

	public void setAttackType(AttackType attackType) {
		this.attackType = attackType;
	}
}