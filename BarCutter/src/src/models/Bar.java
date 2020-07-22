package src.models;

import java.util.ArrayList;
import java.util.List;

public class Bar {
    private List<Float> barSizeCuts;
    private final Float maximumBarSize, waste;

    public Bar(Float maxSize, Float waste) {
        if (maxSize <= 0 || waste <= 0) {
            throw new IllegalArgumentException("Bar init values cannot be equal to zero.");
        }
        this.maximumBarSize = maxSize;
        this.waste = waste;
        this.barSizeCuts = new ArrayList<>();
    }
    public Bar(Bar bar) {
        this.barSizeCuts = bar.getCutListCopy();
        this.maximumBarSize = bar.maximumBarSize;
        this.waste = bar.getWaste();
    }
    public Bar() {
        this.barSizeCuts = new ArrayList<>();
        this.maximumBarSize = Bars.maximumBarSize;
        this.waste = Bars.waste;
    }
    public boolean cut(Float size) {
        if (unusedLength() < size) { return false; }
        barSizeCuts.add(size);
        return true;
    }
    public boolean canCut(Float size) {
        return unusedLength() >= size;
    }
    public boolean isInAcceptanceArea(Float percent) {
        if (percent <= 0 || percent >= 1) { return false; }
        return usedLength() > Bars.maximumBarSize*(1-percent);
    }
    public Float unusedLength() {
        return maximumBarSize - usedLength();
    }
    public Float usedLength() {
        float length = barSizeCuts.size()*waste + barSizeCuts.stream().reduce(0f, Float::sum);
        return length > maximumBarSize ? maximumBarSize : length;
    }
    public Boolean isEmpty() {
        return barSizeCuts.isEmpty();
    }

    public List<Float> getCutListCopy() {
        return new ArrayList<>(barSizeCuts);
    }
    public Float getMaximumBarSize() {
        return maximumBarSize;
    }
    public Float getWaste() {
        return waste;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Bar { ");
        for (Float f : barSizeCuts) {
            stringBuilder.append(f).append("; ");
        }
        return stringBuilder.append("}").toString();
    }
}

