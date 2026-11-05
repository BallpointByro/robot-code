package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class JeremySenior2026 extends LinearOpMode {
    private DcMotor FrontLeft;
    private DcMotor FrontRight;
    private DcMotor BackLeft;
    private DcMotor BackRight;

    private CRServo IntakeMotor; // Change name and if motor (CRServo) is not right.
    // Add Outtake here when built

    double IntakePower;
    double DriveMultiplier;

    bool Intake; // if true, turned on

    @Override
    public void runOpMode() {
        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        BackRight = hardwareMap.get(DcMotor.class, "BackRight");

        IntakeMotor = hardwareMap.get(CRServo.class, "IntakeMotor");

        // Multipliers and values
        IntakePower = 0.75;
        Intake = false;

        DriveMultiplier = 1;

        waitForStart();
        if (opModeIsActive()) {
            IntakeMotor.power(IntakePowerSpeed);

            while (opModeIsActive()) {
                drive();
            }
        }
    }
}

private void drive() {
    double x;
    double y;
    double rx; // For strafing or some shit idk

    x = gamepad1.left_stick_x;
    y = -gamepad1.left_stick_y;
    rx = gamepad1.right_stick_x;

    FrontLeft.power(((y+x)+rx)*DriveMultiplier);
    BackLeft.power(((y-x)+rx)*DriveMultiplier);
    FrontRight.power(((y-x)-rx)*DriveMultiplier);
    BackRight.power(((y+x)-rx)*DriveMultiplier);
}

private void intakeToggle() {
    if (gamepad2.left_trigger) {
        if (Intake == true) {
            IntakeMotor.power(IntakePower);

            Intake = false;
        } else {
            Intake = true;
        }
    }
}
