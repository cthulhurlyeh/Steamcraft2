/**
 * This class was created by BrassGoggledCoders modding team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 */
package steamcraft.client.renderers.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelBrassMonocle.
 * 
 * @author Surseance (Johnny Eatmon) & Falkok15
 */
@SideOnly(Side.CLIENT)
public class ModelBrassMonocle extends ModelBiped
{

	/** The Shape1. */
	ModelRenderer Shape1, Shape2, Shape3, Shape4, Shape5;

	/**
	 * Instantiates a new model brass monocle.
	 * 
	 * @param f
	 *            the f
	 */
	public ModelBrassMonocle(final float f)
	{
		super(f, 0, 64, 32);
		// textureWidth = 64;
		// textureHeight = 32;
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 1, 2, 1);
		Shape1.setRotationPoint(-4F, -4.5F, -5F);
		// Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 4);
		Shape2.addBox(0F, 0F, 0F, 2, 1, 1);
		Shape2.setRotationPoint(-3F, -5.5F, -5F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(0F, 0F, 0F, 1, 2, 1);
		Shape3.setRotationPoint(-1F, -4.5F, -5F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 4);
		Shape4.addBox(0F, 0F, 0F, 2, 1, 1);
		Shape4.setRotationPoint(-3F, -2.5F, -5F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 4, 0);
		Shape5.addBox(0F, 0F, 0F, 1, 1, 0);
		Shape5.setRotationPoint(-4F, -1.5F, -4.5F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 4, 1);
		Shape5.addBox(0F, 0F, 0F, 1, 3, 0);
		Shape5.setRotationPoint(-5F, -0.5F, -4.5F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);

		bipedHead.addChild(Shape3);
		bipedHead.addChild(Shape1);
		bipedHead.addChild(Shape2);
		bipedHead.addChild(Shape4);
		bipedHead.addChild(Shape5);

	}

	@Override
	public void render(final Entity entity, final float f1, final float f2, final float f3, final float f4, final float f5, final float f6)
	{
		super.render(entity, f1, f2, f3, f4, f5, f6);
		setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
	}

	// Techne method
	private void setRotation(final ModelRenderer model, final float x, final float y, final float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(final float f1, final float f2, final float f3, final float f4, final float f5, final float f6, final Entity entity)
	{
		super.setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
	}
}
