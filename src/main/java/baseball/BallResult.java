package baseball;

public enum BallResult {

    STRIKE {
        @Override
        public boolean isStrike() {
            return true;
        }
    },

    BALL {
        @Override
        public boolean isBall() {
            return true;
        }
    },

    NOTHING;

    public boolean isStrike() {
        return false;
    }

    public boolean isBall() {
        return false;
    }

}
