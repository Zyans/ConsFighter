package cons;

public class ConsType
{
	private Element element; // Element des Cons
	private AttackType specialAttackType;
	
	ConsType(Element element, AttackType specialAttackType)
	{
		setElement(element);
		setSpecialAttackType(specialAttackType);
	}

	Element getElement()
	{
		return element;
	}
	
	void setElement(Element element)
	{
		this.element = element;
	}
	
	void setSpecialAttackType(AttackType specialAttackType)
	{
		this.specialAttackType = specialAttackType;
	}
	
	AttackType getSpecialAttackType()
	{
		return specialAttackType;
	}
	
	static ConsType spinne = new ConsType(Element.earth, AttackType.giftbiss);
	static ConsType tiger = new ConsType(Element.earth, AttackType.biss);
	static ConsType biene = new ConsType(Element.fire, AttackType.stachel);
	static ConsType piranha = new ConsType(Element.water, AttackType.biss);
}