package cons;

class PointType
{
	private double damageFactor;
	
	static PointType leftArm = new PointType();
	static PointType rightArm = new PointType();
	static PointType leftHand = new PointType();
	static PointType rightHand = new PointType();
	static PointType leftLeg = new PointType();
	static PointType rightLeg = new PointType();
	static PointType leftFoot = new PointType();
	static PointType rightFoot = new PointType();
	static PointType head = new PointType();
	static PointType torso = new PointType();
	static PointType tail = new PointType();
	
	public PointType()
	{
		setDamageFactor(damageFactor);
	}
	
	void setDamageFactor(double damageFactor)
	{
		this.damageFactor = damageFactor;
	}
	
	double getDamageFactor()
	{
		return damageFactor;
	}
}