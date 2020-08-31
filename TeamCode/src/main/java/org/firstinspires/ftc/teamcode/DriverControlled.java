package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Muie Cyliis")

public class DriverControlled extends OpMode {

    private HardwareConfig hw;
    private double coeff = 1;

    @Override
    public void init() {
        hw = new HardwareConfig(hardwareMap, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.addData("Status", "Hardware initialized");
    }

    @Override
    public void loop() {
        double drive = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        double LB = drive - strafe + rotate;
        double LF = drive + strafe + rotate;
        double RF = drive - strafe - rotate;
        double RB = drive + strafe - rotate;

        if(gamepad1.x) {
            coeff = 1;
        }
        else if(gamepad1.b){
            coeff = 0.5;
        }

        hw.left_back.setPower(LB*coeff);
        hw.left_front.setPower(LF*coeff);
        hw.right_front.setPower(RF*coeff);
        hw.right_back.setPower(RB*coeff);

        if(gamepad2.left_trigger > 0.1) {
            hw.suck_right.setPower(1);
            hw.suck_left.setPower(-1);
        }
        else if(gamepad2.right_trigger > 0.1) {
            hw.suck_right.setPower(-0.5);
            hw.suck_left.setPower(0.5);
        }
        else {
            hw.suck_right.setPower(0);
            hw.suck_left.setPower(0);
        }

        if(gamepad2.dpad_up)
            hw.glisiere.setPower(1);
        else if(gamepad2.dpad_down)
            hw.glisiere.setPower(-0.7);
        else
            hw.glisiere.setPower(0);

        if(gamepad2.a)
            hw.intors_placa.setPower(1);
        else if(gamepad2.y)
            hw.intors_placa.setPower(-1);
        else
            hw.intors_placa.setPower(0);

        if(gamepad2.x)
            hw.intoarcere.setPosition(0.9);
        else if(gamepad2.b)
            hw.intoarcere.setPosition(0.15);

        if(gamepad2.dpad_left) {
            hw.prins.setPosition(0.5);
        }
        else if(gamepad2.dpad_right) {
            hw.prins.setPosition(0.7);
        }
    }
}
