package ee.a1nu.decathlon.model;

public enum Discipline {
    HUNDRED_METERS_RUN (0, "100m", 25.4347, 18, 1.81) {
        @Override
        public int calculateEventScore(double result) {
            return calculateTrackEventScore(result);
        }
    },
    LONG_JUMP(1, "Long jump", 0.14354, 220, 1.4) {
        @Override
        public int calculateEventScore(double result) {
            return calculateJumpScore(result);
        }
    },
    SHOT_PUT(2, "Shot put", 51.39, 1.5, 1.05) {
        @Override
        public int calculateEventScore(double result) {
            return calculateThrowScore(result);
        }
    },
    HIGH_JUMP(3, "High jump", 0.8465, 75, 1.42) {
        @Override
        public int calculateEventScore(double result) {
            return calculateJumpScore(result);
        }
    },
    FOUR_HUNDRED_METERS_RUN(4, "400m", 1.53775, 82, 1.81) {
        @Override
        public int calculateEventScore(double result) {
            return calculateTrackEventScore(result);
        }
    },
    HURDLES_RUN(5, "110m hurdles", 5.74352, 28.5, 1.92) {
        @Override
        public int calculateEventScore(double result) {
            return calculateTrackEventScore(result);
        }
    },
    DISCUS_THROW(6, "Discus throw", 12.91, 4, 1.1) {
        @Override
        public int calculateEventScore(double result) {
            return calculateThrowScore(result);
        }
    },
    POLE_VAULT(7, "Pole vault", 0.2797, 100, 1.35) {
        @Override
        public int calculateEventScore(double result) {
            return calculateJumpScore(result);
        }
    },
    JAVELIN_THROW(8, "Javelin throw", 10.14, 7, 1.08) {
        @Override
        public int calculateEventScore(double result) {
            return calculateThrowScore(result);
        }
    },
    FIFTEEN_HUNDREDS_RUN(9, "1500m", 0.03768, 480, 1.85) {
        @Override
        public int calculateEventScore(double result) {
            return calculateTrackEventScore(result);
        }
    };
    
    public int getIndex() {
        return index;
    }
    
    private static final int CM_IN_METER = 100;
    
    private final int index;
    private final String name;
    private final double a;
    private final double b;
    private final double c;
    
    Discipline(int index, String name, double a, double b, double c) {
        this.index = index;
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public abstract int calculateEventScore(double result);
    
    int calculateTrackEventScore(double result) {
        Double temp = a * Math.pow(b - result, c);
        return temp.intValue();
    }
    
    int calculateJumpScore(double result) {
        return calculateThrowScore(result * CM_IN_METER);
    }
    
    int calculateThrowScore(double result) {
        double temp = a * Math.pow(result - b, c);
        return (int) temp;
    }
    
    public String getName() {
        return name;
    }
}
