package gui.game;

public class Robot {
    private volatile double m_PositionX = 100;
    private volatile double m_PositionY = 100;
    private volatile double m_Direction = 0;

    private static final double maxVelocity = 0.1;
    private static final double maxAngularVelocity = 0.003;

    public Robot() {

    }

    public double getM_PositionX() {
        return m_PositionX;
    }

    public double getM_PositionY() {
        return m_PositionY;
    }

    public double getM_Direction() {
        return m_Direction;
    }

    public void setM_Direction(double d) {
        m_Direction = d;
    }

    public void setM_PositionX(double d) {
        m_PositionX = d;
    }

    public void setM_PositionY(double d) {
        m_PositionY = d;
    }

    public void onModelUpdateEvent(int m_targetPositionX, int m_targetPositionY, int width, int height) {
        double distance = distance(m_targetPositionX, m_targetPositionY,
                m_PositionX, m_PositionY);
        if (distance < 0.5) {
            return;
        }
        double velocity = maxVelocity;
        double angleToTarget = angleTo(m_PositionX, m_PositionY, m_targetPositionX, m_targetPositionY);
        double angularVelocity = 0;

        if (angleToTarget * m_Direction >= 0) {
            if (angleToTarget > m_Direction) {
                angularVelocity = maxAngularVelocity;
            }
            if (angleToTarget < m_Direction) {
                angularVelocity = -maxAngularVelocity;
            }
        }
        if (angleToTarget * m_Direction < 0) {
            if (Math.abs(angleToTarget) + Math.abs(m_Direction) < Math.PI) {
                angularVelocity = Math.signum(angleToTarget) * maxAngularVelocity;
            }
            if (Math.abs(angleToTarget) + Math.abs(m_Direction) > Math.PI) {
                angularVelocity = -1 * Math.signum(angleToTarget) * maxAngularVelocity;
            }
        }

        moveRobot(velocity, angularVelocity, 10, width, height);
    }


    private void moveRobot(double velocity, double angularVelocity, double duration, int width, int height) {
        velocity = applyLimits(velocity, 0, maxVelocity);
        angularVelocity = applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);
        double newX = m_PositionX + velocity / angularVelocity *
                (Math.sin(m_Direction + angularVelocity * duration) -
                        Math.sin(m_Direction));
        if (!Double.isFinite(newX)) {
            newX = m_PositionX + velocity * duration * Math.cos(m_Direction);
        }
        double newY = m_PositionY - velocity / angularVelocity *
                (Math.cos(m_Direction + angularVelocity * duration) -
                        Math.cos(m_Direction));
        if (!Double.isFinite(newY)) {
            newY = m_PositionY + velocity * duration * Math.sin(m_Direction);
        }
        double newDirection = 0;
        if (newX < 0 || newY < 0
                || newX > width || newY > height) {

        } else {
            m_PositionX = newX;
            m_PositionY = newY;
        }
        newDirection = asNormalizedRadians(m_Direction + angularVelocity * duration);
        m_Direction = newDirection;
    }


    private static double asNormalizedRadians(double angle) {
        while (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }
        while (angle >= Math.PI) {
            angle -= 2 * Math.PI;
        }

        return angle;
    }

    private static double distance(double x1, double y1, double x2, double y2) {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    private static double angleTo(double fromX, double fromY, double toX, double toY) {
        double diffX = toX - fromX;
        double diffY = toY - fromY;

        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    private static double applyLimits(double value, double min, double max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

}
