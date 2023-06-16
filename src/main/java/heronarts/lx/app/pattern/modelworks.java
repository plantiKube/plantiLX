package heronarts.lx.app.pattern;

import heronarts.lx.LX;
import heronarts.lx.LXCategory;
import heronarts.lx.color.LXColor;
import heronarts.lx.model.LXPoint;
import heronarts.lx.parameter.BooleanParameter;
import heronarts.lx.parameter.DiscreteParameter;
import heronarts.lx.pattern.LXPattern;

import static java.lang.Math.abs;
import static java.lang.Math.max;

@LXCategory("Test")
public class modelworks extends LXPattern {
    public final BooleanParameter z_en = new BooleanParameter("Z_en", false)
            .setDescription("X plane enabled");

    public final DiscreteParameter x = new DiscreteParameter("select component", 0, this.lx.getModel().children[0].children.length)
            .setDescription("select segment");
    public modelworks(LX lx) {
        super(lx);

        addParameter("z_en", this.z_en);
    }

    public void run(double deltaMs) {
        boolean z_en = this.z_en.getValueb();
        for (LXPoint p : model.points) {

            if (z_en) {
                colors[p.index] = LXColor.hsb(0, 100, 100);
            } else {
                colors[p.index] = LXColor.hsb(120, 100, 100);
            }
        }
    }
}
