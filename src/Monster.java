import java.util.Random;


public class Monster extends Actor {

	//test

	
	int bulletLocationX;
	int bulletLocationY;
	int bulletSpeed = 5;
	int direction = -1;
	int shouldMove = 0;
	int movedLeftAmmount = 4;
	int movedRightAmmount = 0;
	int moveAmmount = 10;
	int moveDownAmmount = 20;
	Random rand = new Random();
	int  randomNumber;
	int num = 0;
	boolean canDrop = true;
	boolean dropped = false;
	
	Player p = GameEngine.p;
	Sandbag[] sb = GameEngine.sb;
	

	public Monster(int x, int y) {
		this.LocationX = x;
		this.LocationY = y;
		
		bulletLocationX = -500;
		bulletLocationY = -500;
		
	}
	
	public void update(){
		checkCollision();
		
		dance();
		
		if(canDrop && num > 60){
			drop();
			num = 0;
		}
		num++;
		if(bulletLocationY > Stage.HEIGHT){
			canDrop = true;
		}
		
		if(dropped){
			bulletLocationY = bulletLocationY + bulletSpeed;
		}
		
	}
	
	public void dance(){
			
		shouldMove++;
		if(shouldMove > 45){
			
			shouldMove = 0;
			
			if(direction == -1){
				LocationX = LocationX - moveAmmount;
				movedLeftAmmount++;
			}
			if(direction == 1){
				LocationX = LocationX + moveAmmount;
				movedRightAmmount++;
			}
		}
		
		if(movedLeftAmmount >=9){
			movedLeftAmmount = 0;
			LocationY = LocationY + moveDownAmmount;
			direction = 1;
		}
		if(movedRightAmmount >=9){
			movedRightAmmount = 0;
			LocationY = LocationY + moveDownAmmount;
			direction = -1;
		}
	}
	
	/**
	 * This method is responsible for the "shooting"
	 * uses random number to decide when to "shoot"
	 */
	public void drop(){
		randomNumber = rand.nextInt(100) + 1;
		if(randomNumber > 97){
			canDrop = false;
			dropped = true;
			bulletLocationX = LocationX + 25;
			bulletLocationY = LocationY + 15;
		}
	}
	
	public void checkCollision(){
				
		
		if(b.LocationY > this.LocationY + 15 && b.LocationY < this.LocationY + 45 &&
				b.LocationX > this.LocationX + 10 && b.LocationX < this.LocationX + 45)
		{
			Life = 0;
			LocationX = 5000;
			b.LocationX = 4000;
			b.shot = false;
		}
		
		if(bulletLocationX > p.LocationX && bulletLocationX < p.LocationX + 60 &&
			bulletLocationY > p.LocationY && bulletLocationY < p.LocationY + 60	)
		{
			bulletLocationX = 5000;
			p.Life--;
		}
		
		if(true){
			
		}
		
	}
}