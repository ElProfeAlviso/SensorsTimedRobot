//Codigo Base de aprendizaje para control de robots del equipo Titanium Rams 5959


package frc.robot; //Este es el paquete principal donde se encuentra el codigo fuente.

import edu.wpi.first.wpilibj.TimedRobot; //Esta es la clase de un robot controlado por tiempo de ejecucion, es decir ejecuta las funciones periodicas en un lapso de aprox 20ms
import edu.wpi.first.wpilibj.Timer;//Esta es la clase de un temporizador
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;//Esta es la clase de un controlador modelo victorSP por PWM
import edu.wpi.first.wpilibj.PS4Controller;//Esta es la clase para usar un control de play4

/**
 * The VM (Virtual Machine) is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  //Declaracion de Variables y objetos de controladores de motor.

  private final VictorSP leftMotor1 = new VictorSP(0);
  private final VictorSP leftMotor2 = new VictorSP(1);
  private final VictorSP rightMotor1 = new VictorSP(2);
  private final VictorSP rightMotor2 = new VictorSP(3);

  


  //Declaracion de Joystick
  private PS4Controller joystick1 = new PS4Controller(0);




  //Declaracion de variables
  private final Timer timer = new Timer(); //Nuevo timer para controlar el autonomo
  



  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  //Hay 2 tipos de funciones INIT y PERIODIC, las init solo se ejecutan una vez al inicializar ese periodo del match o haberlo habilitado en la driver station.
  //Las funciones periodicas se ejecutara una y otra vez en un lapso de aprox 20 ms. segun el modo que esta habilitado en la driver station.

  //--------------------------------------------------------------------------
  //Estas funciones son propias del robot, se ejecutan al encenderlo y se usan para inicalizar variables o secuencias de arranque.
  @Override
  public void robotInit() {
    //invierte el giro de los motores derechos debido a la posicion invertidas de los motores.
    rightMotor1.setInverted(true);
    rightMotor2.setInverted(true);


  }

  @Override
  public void robotPeriodic() {
  }
  

  //---------------------------------------------------------------------------
  //Estas fucniones se ejecutan al entrar en modo autonomo

  @Override
  public void autonomousInit() {
   
    timer.restart(); //Guarda el tiempo que tiene el timer del robot al momento de iniciar autonomo

  }

  @Override
  public void autonomousPeriodic() {

              //Obtiene el tiempo del robot desde que encendio.

    if (timer.get() < 3) {  //Realiza la resta del tiempo desde que encendio con el tiempo de iniciar autonomo.
      
      leftMotor1.set(0.6);
      leftMotor2.set(0.6);
      rightMotor1.set(0.6);
      rightMotor2.set(0.6);

    } else {
      
      leftMotor1.set(0);
      leftMotor2.set(0);
      rightMotor1.set(0);
      rightMotor2.set(0);
    }

  }
  

  //--------------------------------------------------------------------------
  //Estas funciones se ejecutan al entrar en modo teleoperado

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    //Control de robot tipo Arcade

    double speed = -joystick1.getLeftY() * 0.6; //Se le pone -1 para invertir el valor del joystick ya que adelante es negativo.
    double turn = joystick1.getRightX() * 0.6;

    
    
  
    double left = speed + turn; //Ajusta la potencia segun el giro
    double right = speed - turn;//Ajusta la potencia segune el giro
    
    //Se envia la velocidad a los controladores de cada motor.
    leftMotor1.set(left);
    leftMotor2.set(left);
    rightMotor1.set(right);
    rightMotor2.set(right);
    

  }
  

  //---------------------------------------------------------------------------
  //Estas funciones se ejecutan al deshabilitar el robot.

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
  }
  
  //----------------------------------------------------------------------------
  //Estas funciones son para hacer pruebas, ejecutando desde el driver station.

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {
  }
  
  //-----------------------------------------------------------------------------
  // Estas funciones son para hacer rutinas de simulacion

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
