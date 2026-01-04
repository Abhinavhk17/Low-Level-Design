package factory;

import enums.SplitType;
import interfaces.SplitStrategy;
import strategy.EqualSplitStrategy;

public class SplitFactory {

    public static SplitStrategy getStrategy(SplitType type) {
        return switch (type) {
            case EQUAL -> new EqualSplitStrategy();
            // Additional cases for other split types can be added here
            default -> throw new IllegalArgumentException("Invalid split type: " + type);
        };
    }
}
