package edu.cc.ftc.Utilities;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import edu.cc.ftc.Hardware.Hardware1;

import static edu.cc.ftc.Utilities.STATE.CLOSED;
import static edu.cc.ftc.Utilities.STATE.DOWN;
import static edu.cc.ftc.Utilities.STATE.MID;
import static edu.cc.ftc.Utilities.STATE.OPEN;
import static edu.cc.ftc.Utilities.STATE.RAISED;

public class AutoPaths {
    private Hardware1 robot;
    private LinearOpMode linearOpMode;
    private ElapsedTime runtime;

    private AutonomousUtilities au;
    private GyroUtilities gu;
    private AutoEncoder ae;
    private WebcamUtilities wu;



    public AutoPaths(Hardware1 robot, LinearOpMode linearOpMode, ElapsedTime runtime) {
        this.robot = robot;
        this.linearOpMode = linearOpMode;
        this.runtime = runtime;

        au = new AutonomousUtilities(robot, this.linearOpMode, runtime);
        gu = new GyroUtilities(robot, this.linearOpMode, runtime);
        ae = new AutoEncoder(robot,this.linearOpMode,runtime);
        wu = new WebcamUtilities(robot, this.linearOpMode, runtime);
    }

    //Shoots 3 discs
    public void shoot3 () {
        au.pause(.15);
        au.shoot();
        au.pause(.15);
        au.shoot();
        au.pause(.15);
        au.shoot();
    }


    //Commands for grabbing and dropping wobble goals
    //Grabs a wobble goal and moves it to the high position
    public void grabWobble(){
        au.wobblepos(MID);
        au.pause(.4);
        au.wobblegrab(CLOSED);
        au.pause(.4);
        au.wobblepos(RAISED);
    }

    //Grabs a wobble goal
    public void dropWobble(){
        au.wobblegrab(OPEN);
        au.wobblepos(MID);
        au.strafeTime(.5, 90, .3);
        au.wobblepos(DOWN);
        au.pause(.3);
    }


    //Paths for when 0 Discs are present
    //Moves from starting position to drop the first wobble goal at it's drop point
    public void zero1(double driveSpeed, double turnSpeed){
        ae.encoderDrive(driveSpeed, 65);
        au.pause(.3);
        gu.gyroTurn(turnSpeed, -90, 2);
        au.pause(.3);
        ae.encoderDrive(driveSpeed, 35);
        dropWobble();
    }

    //Moves from first wobble goal drop point to parking on the center line
    public void zero1Park(double driveSpeed, double turnSpeed){
        au.strafeTime(driveSpeed, 90, .5);
        gu.gyroTurn(turnSpeed, 270, 2);
        au.pause(.3);
        ae.encoderDrive(-driveSpeed, -35);
        au.pause(.3);
        au.strafeTime(driveSpeed,-90,1.25);
    }

    //Moves from first wobble goal drop point to collect second wobble goal and drop it in the drop point
    public void zero2(double driveSpeed, double turnSpeed){
        au.strafeTime(driveSpeed, 90, .5);
        gu.gyroTurn(turnSpeed, 0, 2);
        au.strafeTime(driveSpeed, 90, .4);
        au.strafeTime(driveSpeed, 170, 2.25);
        grabWobble();
        gu.gyroTurn(turnSpeed, 0, .25);
        au.pause(.1);
        ae.encoderDrive(driveSpeed, 65);
        au.pause(.1);
        gu.gyroTurn(turnSpeed, 270, 1);
        dropWobble();
    }

    //Moves from second wobble goal drop point to parking on the center line
    public void zero2Park(double driveSpeed, double turnSpeed){
        au.strafeTime(driveSpeed, 90, .5);
        gu.gyroTurn(turnSpeed, 270, .5);
        au.pause(.1);
        ae.encoderDrive(-driveSpeed, -20);
        au.pause(.1);
        au.strafeTime(driveSpeed,-90,1);
    }


    //Paths for when 1 Disc is present
    //Moves from starting position to drop the first wobble goal at it's drop point
    public void single1(double driveSpeed, double turnSpeed){
        au.strafeTime(driveSpeed, -90, 1);
        gu.gyroTurn(turnSpeed, 0, .5);
        au.pause(.3);
        ae.encoderDrive(driveSpeed, 95);
        au.pause(.3);
        gu.gyroTurn(turnSpeed, -90, 2);
        au.pause(.3);
        ae.encoderDrive(driveSpeed, 8);
        dropWobble();
    }

    //Moves from first wobble goal drop point to parking on the center line
    public void single1Park(double driveSpeed, double turnSpeed){
        au.strafeTime(driveSpeed, 90, 1.5);
    }

    //Moves from first wobble goal drop point to collect second wobble goal and drop it in the drop point
    public void single2(double driveSpeed, double turnSpeed){
        au.strafeTime(driveSpeed, 90, .5);
        gu.gyroTurn(turnSpeed, 0, 1);
        au.strafeTime(driveSpeed, 90, 1.5);
        gu.gyroTurn(turnSpeed, 0, .5);
        au.strafeTime(driveSpeed, 150, 2.5);
        gu.gyroTurn(turnSpeed, 0, .25);
        grabWobble();
        ae.encoderDrive(driveSpeed, 104);
        au.strafeTime(driveSpeed, -90, .65);
        dropWobble();
    }

    //Moves from second wobble goal drop point to parking on the center line
    public void single2Park(double driveSpeed, double turnSpeed){
        au.strafeTime(driveSpeed, 150, 1);
    }


    //Paths for when 4 Discs are present
    //Moves from starting position to drop the first wobble goal at it's drop point
    public void quad1(double driveSpeed, double turnSpeed){
        au.strafeTime(driveSpeed, -90, 1);
        gu.gyroTurn(turnSpeed, 0, .5);
        au.pause(.3);
        ae.encoderDrive(driveSpeed, 126);
        au.pause(.3);
        gu.gyroTurn(turnSpeed, -90, 2);
        au.pause(.3);
        ae.encoderDrive(driveSpeed, 43);
        dropWobble();
    }

    //Moves from first wobble goal drop point to parking on the center line
    public void quad1Park(double driveSpeed, double turnSpeed){
        au.strafeTime(driveSpeed, 90, 1);
        au.pause(.3);
        gu.gyroTurn(turnSpeed, 0, .5);
        au.pause(.3);
        ae.encoderDrive(-driveSpeed, -33);
    }

    //Moves from first wobble goal drop point to collect second wobble goal and drop it in the drop point
    public void quad2(double driveSpeed, double turnSpeed){
        au.strafeTime(driveSpeed, 90, .5);
        gu.gyroTurn(turnSpeed, -5, 1);
        ae.encoderDrive(-driveSpeed, -45);
        gu.gyroTurn(turnSpeed, 0, .5);
        au.pause(.1);
        gu.gyroTurn(turnSpeed, 0, .5);
        au.strafeTime(driveSpeed, 160, 1.6);
        gu.gyroTurn(turnSpeed, 0, .25);
        grabWobble();
        gu.gyroTurn(turnSpeed, 0, .25);
        ae.encoderDrive(driveSpeed, 110);
        au.pause(.1);
        gu.gyroTurn(turnSpeed, 270, 1);
        au.pause(.1);
        dropWobble();
    }

    //Moves from second wobble goal drop point to parking on the center line
    public void quad2Park(double driveSpeed, double turnSpeed){
        gu.gyroTurn(turnSpeed, 0, 1.5);
        ae.encoderDrive(-driveSpeed, -27);
    }

    public void powerShot(double driveSpeed, double turnSpeed, double launchSpeed){
        gu.gyroTurn(turnSpeed, 18, .5);
        ae.encoderDrive(driveSpeed, 60);
        au.launcherStart(launchSpeed);
        gu.gyroTurn(turnSpeed, 4, .5);
        au.pause(.1);
        gu.gyroTurn(turnSpeed, 4, .5);
        au.pause(.5);
        au.shoot();
        gu.gyroTurn(turnSpeed, 0, .5);
        au.pause(.1);
        gu.gyroTurn(turnSpeed, 0, .25);
        au.shoot();
        gu.gyroTurn(turnSpeed, -4, .5);
        au.pause(.1);
        gu.gyroTurn(turnSpeed, -4, .5);
        au.shoot();
        au.pause(.25);
        au.launcherStop();
    }

    public void zeroPower(double driveSpeed, double turnSpeed){
        gu.gyroTurn(turnSpeed, 0, .5);
        ae.encoderDrive(driveSpeed, 3);
        gu.gyroTurn(turnSpeed, -90, 1);
        ae.encoderDrive(driveSpeed, 50);
        dropWobble();
    }

    public void singlePower(double driveSpeed, double turnSpeed){
        gu.gyroTurn(turnSpeed, 0, .5);
        ae.encoderDrive(driveSpeed, 23);
        gu.gyroTurn(turnSpeed, -90, 1);
        ae.encoderDrive(driveSpeed, 15);
        dropWobble();
    }
    public void quadPower(double driveSpeed, double turnSpeed){
        //gu.gyroTurn(turnSpeed, -20, .5);
        //ae.encoderDrive(driveSpeed, 56);
        //gu.gyroTurn(turnSpeed, -90, 1);
        //ae.encoderDrive(driveSpeed, 20);
        gu.gyroTurn(turnSpeed, -40, 1);
        ae.encoderDrive(driveSpeed, 70);
        gu.gyroTurn(turnSpeed, -90, 1);
        dropWobble();
    }

    public void singlePickup(double driveSpeed, double turnSpeed, double launchSpeed){
        gu.gyroTurn(turnSpeed, -12, 1);
        au.loaderStart();
        ae.encoderDrive(-driveSpeed, -60);
        gu.gyroTurn(turnSpeed, 0, 1);
        ae.encoderDrive(driveSpeed, 18);
        au.pause(.25);
        au.launcherStart(launchSpeed);
        gu.gyroTurn(turnSpeed, 0, .5);
        au.pause(.30);
        au.loaderStop();
        au.shoot();
        ae.encoderDrive(driveSpeed, 8);
        au.launcherStop();
    }

    public void quadPickup(double driveSpeed, double turnSpeed, double launchSpeed){
        gu.gyroTurn(turnSpeed, -23, 1);
        au.barDown();
        au.loaderStart();
        ae.encoderDrive(-1, -55);
        ae.encoderDrive(-driveSpeed * .15, -18);
        au.barUp();
        gu.gyroTurn(turnSpeed,-15, 1);
        ae.encoderDrive(driveSpeed, 17);
        au.launcherStart(launchSpeed);
        gu.gyroTurn(turnSpeed, 0, .5);
        au.pause(.05);
        gu.gyroTurn(turnSpeed, 0, .5);
        au.pause(.3);
        shoot3();
        au.loaderStop();
        ae.encoderDrive(driveSpeed, 8);
        au.launcherStop();

    }
}

