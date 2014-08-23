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
package steamcraft.client.renderers.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import steamcraft.client.renderers.models.ModelBattery;
import steamcraft.common.lib.LibInfo;

/**
 * @author warlordjones
 * 
 */
public class TileBatteryRenderer extends TileEntitySpecialRenderer
{
	private ModelBattery model;

	public TileBatteryRenderer()
	{
		this.model = new ModelBattery();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double dx, double dy, double dz, float scale)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) dx + 0.5F, (float) dy + 1.5F, (float) dz + 0.5F);
		ResourceLocation crystal = new ResourceLocation(LibInfo.PREFIX.replace(":", ""), "textures/models/batterybank.png");
		Minecraft.getMinecraft().renderEngine.bindTexture(crystal);
		this.renderBattery(te, te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);
		GL11.glPopMatrix();
	}

	public void renderBattery(TileEntity te, World world, int x, int y, int z)
	{
		GL11.glPushMatrix();
		GL11.glScalef(1.3F, 1.5F, 1F);
		GL11.glTranslatef(-0.1F, 0.05F, 0.25F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, te);
		GL11.glPopMatrix();
	}
}
