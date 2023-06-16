package heronarts.lx.app.pattern;// In this file you can define your own custom patterns

import heronarts.lx.LX;
import heronarts.lx.LXCategory;
import heronarts.lx.color.LXColor;
import heronarts.lx.model.LXModel;
import heronarts.lx.model.LXPoint;
import heronarts.lx.parameter.BooleanParameter;
import heronarts.lx.parameter.DiscreteParameter;
import heronarts.lx.pattern.LXPattern;

import static java.lang.Math.abs;
import static java.lang.Math.max;

// Here is a fairly basic example pattern that renders a plane that can be moved
// across one of the axes.
@LXCategory("Test")
public class ModelTestWut extends LXPattern {

    public final DiscreteParameter x = new DiscreteParameter("select component", 0, this.lx.getModel().children[0].children.length)
            .setDescription("select segment");

    BooleanParameter[] boolParams;

    public ModelTestWut(LX lx) {
        super(lx);
        for (int i = 0; i < 30; i++) {
            boolParams[i] = new BooleanParameter(String.valueOf(i), false)
                    .setDescription(String.valueOf(i));
            addParameter(String.valueOf(i), boolParams[i]);
        }

        addParameter("x", this.x);
    }

    public void run(double deltaMs) {
        LXModel model = this.lx.getModel();

        int x = this.x.getValuei();

        LXModel[] modelSegments = model.children[0].children;
//        LXModel modelSegment = model.children[0].children[x];
        for (int i = 0; i < modelSegments.length; i++ ){
            LXModel modelSegment = modelSegments[i];
            if (i == x) {
                float bright = 0;
                float bright_inc = (float) (100.0/((float)modelSegment.points.length));
                for (LXPoint p : modelSegment.points){
                    colors[p.index] = LXColor.gray(bright);
                    bright += bright_inc;
                }
            }
            else {
                for (LXPoint p : modelSegment.points){
                    colors[p.index] = LXColor.rgb(
                            (int) 0x00,
                            (int) 0x00,
                            (int) 0x00
                    );
                }
            }
        }
    }
}
