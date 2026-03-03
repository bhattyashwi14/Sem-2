import java.util.Scanner;
interface Device
{
    void turnOn();
    void turnOff();
    boolean getStatus();
}
abstract class HomeAutomation
{
    abstract void addDevice(Device d);
    abstract void removeDevice(Device d);
    abstract void checkDeviceStatus(Device d);
    abstract void turnOffAllDevices();

}
class Light implements Device
{
    boolean b;
    public void turnOn()
    {
        System.out.println("Activates light");
        b=true;
    }
    public void turnOff()
    {
        System.out.println("Deactivates light");
        b=false;
    }
    public boolean getStatus()
    {
        return b;
    }
}
class SecurityCamera implements Device
{
    boolean b;
    public void turnOn()
    {
        System.out.println("Activates the security camera");
        b=true;
    }
    public void turnOff()
    {
        System.out.println("Deactivates the security camera");
        b=false;
    }
    public boolean getStatus()
    {
        return b;
    }
}
public class PB112 extends HomeAutomation {
    private Light light = null;
    private SecurityCamera sc = null;

    void addDevice(Device d) {
        if (d instanceof Light) {
            light = (Light) d;
            System.out.println("Light added");
        }
        if (d instanceof SecurityCamera) {
            sc = (SecurityCamera) d;
            System.out.println("Security camera added");
        }
    }

    void removeDevice(Device d) {
        if (d instanceof Light && light != null) {
            light = null;
            System.out.println("Light removed");
        } else if (d instanceof SecurityCamera && sc != null) {
            sc = null;
            System.out.println("Security camera removed");
        } else {
            throw new DeviceNotFoundException("No such device found");
        }
    }

    void checkDeviceStatus(Device d) {
        if (d instanceof Light) {
            if (light == null) {
                throw new DeviceNotFoundException("No such device found");
            } else {
                System.out.println("Light is" + (light.getStatus() ? " ON" : " OFF"));
            }
        }
        if (d instanceof SecurityCamera) {
            if (sc == null) {
                throw new DeviceNotFoundException("No such device found");
            } else {
                System.out.println("Security Camera is" + (sc.getStatus() ? " ON" : " OFF"));
            }
        }
    }

    void turnOffAllDevices() {
        if (light != null && light.getStatus()) {
            light.turnOff();
        }
        if (sc != null && sc.getStatus()) {
            sc.turnOff();
        }
    }

    public static void main(String[] args) throws InvalidChoiceException {
        Scanner sc = new Scanner(System.in);
        PB112 ob = new PB112();
        int x;
        do {
            System.out.println("Enter 1 to add light");
            System.out.println("Enter 2 to add security camera");
            System.out.println("Enter 3 to turn Light On/Off");
            System.out.println("Enter 4 to Activate/Deactivate security camera");
            System.out.println("Enter 5 to check status of light");
            System.out.println("Enter 6 to check status of security camera");
            System.out.println("Enter 7 to turn off all devices");
            System.out.println("Enter 8 to exit");
            System.out.print("Enter your choice:");
            x = sc.nextInt();
            ob.Choice(x);
        } while (x != 8);
        try
        {
            return;
        }
        finally
        {
            System.out.println("Thank you for using smart hub");
        }
    }
    void Choice(int x) {
        switch (x) {
            case 1:
                addDevice(new Light());
                break;
            case 2:
                addDevice(new SecurityCamera());
                break;
            case 3:
                if (light != null) {
                    if (light.getStatus()) {
                        light.turnOff();
                    } else {
                        light.turnOn();
                    }
                } else {
                    System.out.println("Add the device first");
                }
                break;
            case 4:
                if (sc != null) {
                    if (sc.getStatus()) {
                        sc.turnOff();
                    } else {
                        sc.turnOn();
                    }
                } else {
                    System.out.println("Add the device first");
                }
                break;
            case 5:
                try {
                    checkDeviceStatus(new Light());
                } catch (DeviceNotFoundException e) {
                    System.out.println(e);
                }
                break;
            case 6:
                try {
                    checkDeviceStatus(new SecurityCamera());
                } catch (DeviceNotFoundException e) {
                    System.out.println(e);
                }
                break;
            case 7:
                turnOffAllDevices();
                break;
            case 8:
                System.out.println("Exiting!");
                break;
            default:
                try {
                    throw new InvalidChoiceException("Enter a choice from 1 to 8 only");
                }
                catch(InvalidChoiceException e)
                {
                    System.out.println(e);
                }
        }
    }
}
class DeviceNotFoundException extends RuntimeException
{
    DeviceNotFoundException(String s)
    {
        super(s);
    }
}
class InvalidChoiceException extends Exception
{
    InvalidChoiceException(String s)
    {
        super(s);
    }
}
