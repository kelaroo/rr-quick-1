package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Autonomous1")

public class AutoRoutine extends LinearOpMode {

    private HardwareConfig hw;

    @Override
    public void runOpMode() throws InterruptedException {
        hw = new HardwareConfig(hardwareMap, DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Status", "Hardware initialzied, waiting for start");
        resetAllEncoders();
        telemetry.addData("Encoders", "reset");
        telemetry.update();

        waitForStart();

        goDrive(1, 700);
        goRotate(1, 1500);
        goDrive(1, -1100);

        goDrive(1, -5000);
    }

    private void resetDriveEncoders() {
        for(DcMotor motor: hw.driveMotors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    private void resetAllEncoders() {
        for(DcMotor motor: hw.allMotors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    private void stopDrivePower() {
        for(DcMotor motor: hw.driveMotors)
            motor.setPower(0);
    }

    private void goDrive(double power, int ticks) {
        for(DcMotor motor: hw.driveMotors) {
            motor.setTargetPosition(ticks);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(power);
        }

        boolean isRunning;
        do{
            isRunning = false;
            for(DcMotor motor: hw.driveMotors) {
                isRunning |= motor.isBusy();
                break;
            }
        }while(opModeIsActive() && isRunning);

        stopDrivePower();
        sleep(500);
        resetDriveEncoders();
    }

    private void goStrafe(double power, int ticks) {
        hw.right_front.setTargetPosition(-ticks);
        hw.right_back.setTargetPosition(ticks);
        hw.left_front.setTargetPosition(ticks);
        hw.left_back.setTargetPosition(-ticks);

        for(DcMotor motor: hw.driveMotors) {
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(power);
        }

        boolean isRunning;
        do{
            isRunning = true;
            for(DcMotor motor: hw.driveMotors) {
                if(!motor.isBusy()) {
                    isRunning = false;
                    break;
                }
            }
        }while(opModeIsActive() && isRunning);

        stopDrivePower();
        sleep(100);
        resetDriveEncoders();
    }

    private void goRotate(double power, int ticks) {
        hw.left_front.setTargetPosition(ticks);
        hw.left_back.setTargetPosition(ticks);
        hw.right_front.setTargetPosition(-ticks);
        hw.right_back.setTargetPosition(-ticks);

        for(DcMotor motor: hw.driveMotors) {
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(power);
        }

        boolean isRunning;
        do{
            isRunning = false;
            for(DcMotor motor: hw.driveMotors) {
                if(motor.isBusy()) {
                    isRunning = true;
                    break;
                }
            }
        }while(opModeIsActive() && isRunning);

        stopDrivePower();
        sleep(500);
        resetDriveEncoders();
    }
}
