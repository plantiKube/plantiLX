package heronarts.lx.app.pattern;

// In this file you can define your own custom patterns

import heronarts.lx.LX;
import heronarts.lx.LXCategory;
import heronarts.lx.color.LXColor;
import heronarts.lx.model.LXPoint;
import heronarts.lx.parameter.BooleanParameter;
import heronarts.lx.parameter.CompoundParameter;
import heronarts.lx.pattern.LXPattern;

import static java.lang.Math.abs;
import static java.lang.Math.max;

// Here is a fairly basic example pattern that renders a plane that can be moved
// across one of the axes.
@LXCategory("Studio")
public class XYZ_plane_n extends LXPattern {

    public final CompoundParameter x = new CompoundParameter("X", 0, 1)
            .setDescription("x position");

    public final CompoundParameter y = new CompoundParameter("Y", 0, 1)
            .setDescription("y position");

    public final CompoundParameter z = new CompoundParameter("Z", 0, 1)
            .setDescription("z position");

    public final CompoundParameter wth = new CompoundParameter("Width", .4, 0, 1)
            .setDescription("Thickness of the plane");

    public final BooleanParameter x_en = new BooleanParameter("X_en", false)
            .setDescription("X plane enabled");

    public final BooleanParameter y_en = new BooleanParameter("Y_en", false)
            .setDescription("X plane enabled");

    public final BooleanParameter z_en = new BooleanParameter("Z_en", false)
            .setDescription("X plane enabled");

    public XYZ_plane_n(LX lx) {
        super(lx);

        // enable params per axis
        addParameter("x_en", this.x_en);
        addParameter("y_en", this.y_en);
        addParameter("z_en", this.z_en);

        // axis values (normalized)
        addParameter("x", this.x);
        addParameter("y", this.y);
        addParameter("z", this.z);

        // width (currently all axis)
        addParameter("width", this.wth);
    }

    @Override
    public void run(double deltaMs) {
        boolean x_en = this.x_en.getValueb();
        boolean y_en = this.y_en.getValueb();
        boolean z_en = this.z_en.getValueb();


        float x = this.x.getValuef();
        float y = this.y.getValuef();
        float z = this.z.getValuef();

        float falloff = 100 / this.wth.getValuef();

        for (LXPoint p : model.points) {
            float px = p.xn;
            float py = p.yn;
            float pz = p.zn;

            int x_color = x_en ? LXColor.gray(
                    (int) max(0, 100 - falloff*abs(px - x))
            ) : 0;

            int y_color = y_en ? LXColor.gray(
                    (int) max(0, 100 - falloff*abs(py - y))
            ) : 0;

            int z_color = z_en ? LXColor.gray(
                    (int) max(0, 100 - falloff*abs(pz - z))
            ) : 0;

            colors[p.index] = LXColor.lightest(LXColor.lightest(x_color, y_color), z_color);
        }
    }
}