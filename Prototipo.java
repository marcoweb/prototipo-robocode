package marcocarvalho;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Prototipo - a robot by (Marco Carvalho)
 */
public class Prototipo extends AdvancedRobot
{

	private enum DirectionEnum {
		TO_TOP("TO_TOP", 0),
		TO_RIGHT("TO_RIGHT", 90),
		TO_BOTTON("TO_BOTTON", 180),
		TO_LEFT("TO_LEFT", 270);

		public final String direction;		
		public final int angle;
		public double distance;
		
		DirectionEnum(String direction, int angle) {
			this.direction = direction;
			this.angle = angle;
		}
	}
	
	private double topLine;
	private double bottonLine;
	private double leftLine;
	private double rightLine;
	private DirectionEnum botDirection;
	
	public void changeDirection() {
		
		DirectionEnum[] directions = {DirectionEnum.TO_TOP, DirectionEnum.TO_BOTTON, DirectionEnum.TO_LEFT, DirectionEnum.TO_RIGHT};
		directions[0].distance = this.topLine - getY();
		directions[1].distance = getY() - this.bottonLine;
		directions[2].distance = getX() - this.leftLine;
		directions[3].distance = this.rightLine - getX();
		
		for(int index = 0;index < directions.length - 1;index++) {
			if(directions[index].distance > directions[index + 1].distance) {
				DirectionEnum temp = directions[index];
				directions[index] = directions[index + 1];
				directions[index + 1] = temp;
				index = -1;
			}
		}
		
		for(int index = 0;index < directions.length;index++) {
			out.println(directions[index].direction + " : " + directions[index].distance);
		}
		
		for(int index = 0;index < directions.length;index++) {
			if(directions[index].distance > 0.0d) {
				this.botDirection = directions[index];
				break;
			}
		}
		
		turnRight(normalRelativeAngleDegrees(this.botDirection.angle - getHeading()));
		
	}
	
	public void move() {
		if(this.botDirection.distance > 0.0d) {
			double distance = (this.botDirection.distance > 100) ? 100 : this.botDirection.distance;
			ahead(distance);
			this.botDirection.distance -= distance;
		} else {
			this.changeDirection();
		}
	}
	
	public void turnGun() {
		if((getX() == this.rightLine && this.botDirection.direction == "TO_TOP") || (getX() == this.leftLine && this.botDirection.direction == "TO_BOTTON") || (getY() == this.topLine && this.botDirection.direction == "TO_LEFT") || (getY() == this.bottonLine && this.botDirection.direction == "TO_RIGHT")){
			turnGunRight(-180);
			turnGunRight(180);
		} else {
			turnGunRight(180);
			turnGunRight(-180);
		}
	}
	
	/**
	 * run: Prototipo's default behavior
	 */
	public void run() {
		this.topLine = getBattleFieldHeight() - (getHeight() + 2);
		this.bottonLine = getHeight() + 2;
		this.rightLine = getBattleFieldWidth() - (getWidth() + 2);
		this.leftLine = getWidth() + 2;
	
		this.changeDirection();

		while(true) {
			move();
			turnGun();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(1);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		//back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		//back(20);
	}	
}
