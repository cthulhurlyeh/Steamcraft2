/*
 * @author warlordjones
 *
 * Using this source for addon development or examples/education is cool with me.
 * Taking this source code and claiming it is yours isn't cool!

 */
package steamcraft.client.renderers.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

//Model code generated by MCModeller. All animation, AI,
//and special features are yours to program. Also keep in
//mind that some of these class names may have changed since
//wrote this. Make sure to check for compatibility with the
//latest version of the Minecraft Coder Pack before attempting
//to use this code.

public class ModelTeslaCoil extends ModelBase {
    public ModelRenderer center;
    public ModelRenderer fin1;
    public ModelRenderer fin2;
    public ModelRenderer fin3;
    public ModelRenderer top;

    public ModelTeslaCoil() {
	center = new ModelRenderer(this, 0, 0);
	center.addBox(-1.5F, 7.5F, -1.5F, 3, 15, 3, 0);

	top = new ModelRenderer(this, 0, 0);
	top.addBox(-2.5F, 4.5F, -2.5F, 5, 5, 5, 0);

	fin1 = new ModelRenderer(this, 0, 0);
	fin1.addBox(-3.5F, 11.0F, -3.5F, 7, 2, 7, 0);

	fin2 = new ModelRenderer(this, 0, 0);
	fin2.addBox(-4.5F, 15.0F, -4.5F, 9, 2, 9, 0);

	fin3 = new ModelRenderer(this, 0, 0);
	fin3.addBox(-5.5F, 19.0F, -5.5F, 11, 2, 11, 0);

    }

    public void render(final float f, final float f1, final float f2,
	    final float f3, final float f4, final float f5) {
	this.setRotationAngles(f, f1, f2, f3, f4, f5);
	center.render(f5);
	top.render(f5);
	fin1.render(f5);
	fin2.render(f5);
	fin3.render(f5);
    }

    public void renderModel(final float f, final float f1, final float f2,
	    final float f3, final float f4, final float f5) {
	this.setRotationAngles(f, f1, f2, f3, f4, f5);
	center.render(f5);
	top.render(f5);
	fin1.render(f5);
	fin2.render(f5);
	fin3.render(f5);
    }

    // Method you're going to want to override:
    public void setRotationAngles(final float f, final float f1,
	    final float f2, final float f3, final float f4, final float f5) {
    }

}
