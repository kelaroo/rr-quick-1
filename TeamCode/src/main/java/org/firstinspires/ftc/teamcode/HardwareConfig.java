package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Arrays;
import java.util.List;

public class HardwareConfig {

    public DcMotor left_back;
    public DcMotor left_front;
    public DcMotor right_front;
    public DcMotor right_back;

    public DcMotor suck_right;
    public DcMotor suck_left;
    public DcMotor glisiere;
    public DcMotor intors_placa;

    public Servo intoarcere;
    public Servo prins;

    public List<DcMotor> driveMotors;
    public List<DcMotor> allMotors;

    public HardwareConfig(HardwareMap hw, DcMotor.RunMode runMode) {
        left_back = hw.get(DcMotor.class, "left_back");
        left_front = hw.get(DcMotor.class, "left_front");
        right_front = hw.get(DcMotor.class, "right_front");
        right_back = hw.get(DcMotor.class, "right_back");

        suck_right = hw.get(DcMotor.class, "suck_right");
        suck_left = hw.get(DcMotor.class, "suck_left");
        glisiere = hw.get(DcMotor.class, "glisiere");
        intors_placa = hw.get(DcMotor.class, "intors_placa");

        intoarcere = hw.get(Servo.class, "intoarcere");
        prins = hw.get(Servo.class, "prins");

        left_back.setDirection(DcMotor.Direction.FORWARD);
        left_front.setDirection(DcMotor.Direction.FORWARD);
        right_front.setDirection(DcMotor.Direction.REVERSE);
        right_back.setDirection(DcMotor.Direction.REVERSE);

        driveMotors = Arrays.asList(left_back, left_front, right_front, right_back);
        for(DcMotor motor: driveMotors) {
            motor.setMode(runMode);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        for(DcMotor motor: Arrays.asList(suck_right, suck_left, glisiere, intors_placa)) {
            motor.setMode(runMode);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        intoarcere.setPosition(0.21);
        prins.setPosition(0.4);

        allMotors = Arrays.asList(left_front, left_back, right_back, right_front, suck_left, suck_right, glisiere, intors_placa);
    }
}
