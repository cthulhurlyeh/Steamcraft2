
package steamcraft.common.items.armor;

import org.lwjgl.opengl.GL11;

import boilerplate.client.ClientHelper;
import boilerplate.common.baseclasses.items.BaseArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitItems;
import steamcraft.common.lib.ModInfo;

/**
 * @author Surseance
 *
 */
public class ItemBrassGoggles extends BaseArmor
{
	public static ResourceLocation overlay = new ResourceLocation(ModInfo.PREFIX + "textures/misc/goggles.png");

	public ItemBrassGoggles(ArmorMaterial mat, int renderIndex, int type)
	{
		super(mat, type, "");
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		return ModInfo.PREFIX + "textures/models/armor/goggles.png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen,
			int mouseX, int mouseY)
	{
		if ((ClientHelper.player() == null) || (ClientHelper.screen() != null))
			return;

		ItemStack helmet = ClientHelper.player().inventory.armorItemInSlot(3);

		if ((ClientHelper.settings().thirdPersonView == 0) && (helmet != null) && (helmet.getItem() == InitItems.itemBrassGoggles))
		{
			ClientHelper.textureManager().bindTexture(overlay);
			Tessellator tessellator = Tessellator.instance;
			ScaledResolution scaledResolution = ClientHelper.resolution();
			int width = scaledResolution.getScaledWidth();
			int height = scaledResolution.getScaledHeight();

			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);

			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glClearDepth(1.0D);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(0.0D, height, 90.0D, 0.0D, 1.0D);
			tessellator.addVertexWithUV(width, height, 90.0D, 1.0D, 1.0D);
			tessellator.addVertexWithUV(width, 0.0D, 90.0D, 1.0D, 0.0D);
			tessellator.addVertexWithUV(0.0D, 0.0D, 90.0D, 0.0D, 0.0D);
			tessellator.draw();
			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glDisable(GL11.GL_BLEND);
		}
	}
}
