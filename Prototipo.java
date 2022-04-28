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
		TO_TOP(0),
		TO_RIGHT(90),
		TO_BOTTON(180),
		TO_LEFT(270);
		
		public int value;
		
		DirectionEnum(int value) {
			this.value = value;
		}
	}
	
	private double topLine;
	private double bottonLine;
	private double leftLine;
	private double rightLine;
	private DirectionEnum botDirection;
	
	public void changeDirection(boolean ignoreActualDirection) {
		double toTop = this.topLine - getY();
		double toBotton = getY() - this.bottonLine;
		double toLeft = getX() - this.leftLine;
		double toRight = this.rightLine - getX();
		
		if(!(ignoreActualDirection && botDirection.equals(DirectionEnum.TO_TOP)) && (toTop < toBotton && toTop < toLeft && toTop < toRight)) {
			this.botDirection = DirectionEnum.TO_TOP;
			turnRight(normalRelativeAngleDegrees(this.botDirection.value - getHeading()));
		} else if(!(ignoreActualDirection && botDirection.equals(DirectionEnum.TO_BOTTON)) && (toBotton < toTop && toBotton < toLeft && toBotton < toRight)) {
			this.botDirection = DirectionEnum.TO_BOTTON;
			turnRight(normalRelativeAngleDegrees(this.botDirection.value - getHeading()));
		} else if(!(ignoreActualDirection && botDirection.equals(DirectionEnum.TO_LEFT)) && (toLeft < toTop && toLeft < toBotton && toLeft < toRight)) {
			this.botDirection = DirectionEnum.TO_LEFT;
			turnRight(normalRelativeAngleDegrees(this.botDirection.value - getHeading()));
		} else if(!(ignoreActualDirection && botDirection.equals(DirectionEnum.TO_RIGHT)) && (toRight < toTop && toRight < toBotton && toRight < toLeft)) {
			this.botDirection = DirectionEnum.TO_RIGHT;
			turnRight(normalRelativeAngleDegrees(this.botDirection.value - getHeading()));
		}
		
		out.println("TO_TOP: " + toTop);
		out.println("TO_BOTTON: " + toBotton);
		out.println("TO_LEFT: " + toLeft);
		out.println("TO_RIGHT: " + toRight);
	}
	
	/**
	 * run: Prototipo's default behavior
	 */
	public void run() {
		this.topLine = getBattleFieldHeight() - (getHeight() + 2);
		this.bottonLine = getHeight() + 2;
		this.rightLine = getBattleFieldWidth() - (getWidth() + 2);
		this.leftLine = getWidth() + 2;
		
		out.println("TOP: " + this.topLine);
		out.println("BOTTON: " + this.bottonLine);
		out.println("LEFT: " + this.leftLine);
		out.println("RIGHT: " + this.rightLine);
	
		this.changeDirection(false);		

		while(true) {
			// Replace the next 4 lines with any behavior you would like
			//ahead(100);
			//turnGunRight(360);
			//back(100);
			//turnGunRight(360);
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
