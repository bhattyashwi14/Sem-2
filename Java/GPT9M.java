class Car extends Thread
{
    ParkingLot p;
    String carName;
    Car(ParkingLot p,String carName)
    {
        super(carName);
        this.p=p;
    }
    public void run()
    {
        p.park(getName());
        try
        {Thread.sleep(1000);} catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        p.leave(getName());
    }
}
class ParkingLot
{
    int availableSlots=3;
    synchronized void park(String carName)
    {
        while(availableSlots==0)
        {
            try
            {
            wait();}
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        availableSlots--;
        System.out.println(carName+" parked");
        System.out.println("Available slots:"+availableSlots);
        notifyAll();
    }
    synchronized void leave(String carName)
    {
        availableSlots++;
        System.out.println(carName+" left");
        System.out.println("Available slots:"+availableSlots);
        notifyAll();
    }
}
public class GPT9M
{
    public static void main(String[] args)
    {
        ParkingLot p=new ParkingLot();
      for(int i=1;i<=6;i++)
      {
         Car c=new Car(p,"Car-"+i);
         c.start();
      }
    }
}
