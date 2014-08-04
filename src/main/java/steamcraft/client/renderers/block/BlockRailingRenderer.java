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
package steamcraft.client.renderers.block;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import steamcraft.client.lib.RenderIDs;
import steamcraft.common.blocks.BlockCastIronRailing;
import steamcraft.common.tiles.TileBattery;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockCrystalRenderer.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class BlockRailingRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(final Block block, final int metadata, final int modelID, final RenderBlocks renderer)
	{

	}

	@Override
	public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block, final int modelID,
			final RenderBlocks renderer)
	{
		BlockCastIronRailing railing = (BlockCastIronRailing) block;
		  int l = world.getHeight();
	        Tessellator tessellator = Tessellator.instance;
	        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
	        int i1 = block.colorMultiplier(world, x, y, z);
	        float f = (float)(i1 >> 16 & 255) / 255.0F;
	        float f1 = (float)(i1 >> 8 & 255) / 255.0F;
	        float f2 = (float)(i1 & 255) / 255.0F;

	        if (EntityRenderer.anaglyphEnable)
	        {
	            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
	            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
	            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
	            f = f3;
	            f1 = f4;
	            f2 = f5;
	        }

	        tessellator.setColorOpaque_F(f, f1, f2);
	        IIcon iicon = railing.blockIcon;
	        IIcon iicon1 = railing.icon;

	        double d21 = (double)iicon.getMinU();
	        double d0 = (double)iicon.getInterpolatedU(8.0D);
	        double d1 = (double)iicon.getMaxU();
	        double d2 = (double)iicon.getMinV();
	        double d3 = (double)iicon.getMaxV();
	        double d4 = (double)iicon1.getInterpolatedU(7.0D);
	        double d5 = (double)iicon1.getInterpolatedU(9.0D);
	        double d6 = (double)iicon1.getMinV();
	        double d7 = (double)iicon1.getInterpolatedV(8.0D);
	        double d8 = (double)iicon1.getMaxV();
	        double d9 = (double)x;
	        double d10 = (double)x + 0.5D;
	        double d11 = (double)(x + 1);
	        double d12 = (double)z;
	        double d13 = (double)z + 0.5D;
	        double d14 = (double)(z + 1);
	        double d15 = (double)x + 0.5D - 0.0625D;
	        double d16 = (double)x + 0.5D + 0.0625D;
	        double d17 = (double)z + 0.5D - 0.0625D;
	        double d18 = (double)z + 0.5D + 0.0625D;
	        boolean flag  = railing.canPaneConnectTo(world, x, y, z - 1, NORTH);
	        boolean flag1 = railing.canPaneConnectTo(world, x, y, z + 1, SOUTH);
	        boolean flag2 = railing.canPaneConnectTo(world, x - 1, y, z, WEST );
	        boolean flag3 = railing.canPaneConnectTo(world, x + 1, y, z, EAST );
	        boolean flag4 = railing.shouldSideBeRendered(world, x, y + 1, z, 1);
	        boolean flag5 = railing.shouldSideBeRendered(world, x, y - 1, z, 0);
	        double d19 = 0.01D;
	        double d20 = 0.005D;

	        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1))
	        {
	            if (flag2 && !flag3)
	            {
	                tessellator.addVertexWithUV(d9, (double)(y + 1), d13, d21, d2);
	                tessellator.addVertexWithUV(d9, (double)(y + 0), d13, d21, d3);
	                tessellator.addVertexWithUV(d10, (double)(y + 0), d13, d0, d3);
	                tessellator.addVertexWithUV(d10, (double)(y + 1), d13, d0, d2);
	                tessellator.addVertexWithUV(d10, (double)(y + 1), d13, d21, d2);
	                tessellator.addVertexWithUV(d10, (double)(y + 0), d13, d21, d3);
	                tessellator.addVertexWithUV(d9, (double)(y + 0), d13, d0, d3);
	                tessellator.addVertexWithUV(d9, (double)(y + 1), d13, d0, d2);

	                if (!flag1 && !flag)
	                {
	                    tessellator.addVertexWithUV(d10, (double)(y + 1), d18, d4, d6);
	                    tessellator.addVertexWithUV(d10, (double)(y + 0), d18, d4, d8);
	                    tessellator.addVertexWithUV(d10, (double)(y + 0), d17, d5, d8);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1), d17, d5, d6);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1), d17, d4, d6);
	                    tessellator.addVertexWithUV(d10, (double)(y + 0), d17, d4, d8);
	                    tessellator.addVertexWithUV(d10, (double)(y + 0), d18, d5, d8);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1), d18, d5, d6);
	                }

	                if (flag4 || y < l - 1 && world.isAirBlock(x - 1, y + 1, z))
	                {
	                    tessellator.addVertexWithUV(d9, (double)(y + 1) + 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d18, d5, d8);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d17, d4, d8);
	                    tessellator.addVertexWithUV(d9, (double)(y + 1) + 0.01D, d17, d4, d7);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d9, (double)(y + 1) + 0.01D, d18, d5, d8);
	                    tessellator.addVertexWithUV(d9, (double)(y + 1) + 0.01D, d17, d4, d8);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d17, d4, d7);
	                }

	                if (flag5 || y > 1 && world.isAirBlock(x - 1, y - 1, z))
	                {
	                    tessellator.addVertexWithUV(d9, (double)y - 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d18, d5, d8);
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d17, d4, d8);
	                    tessellator.addVertexWithUV(d9, (double)y - 0.01D, d17, d4, d7);
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d9, (double)y - 0.01D, d18, d5, d8);
	                    tessellator.addVertexWithUV(d9, (double)y - 0.01D, d17, d4, d8);
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d17, d4, d7);
	                }
	            }
	            else if (!flag2 && flag3)
	            {
	                tessellator.addVertexWithUV(d10, (double)(y + 1), d13, d0, d2);
	                tessellator.addVertexWithUV(d10, (double)(y + 0), d13, d0, d3);
	                tessellator.addVertexWithUV(d11, (double)(y + 0), d13, d1, d3);
	                tessellator.addVertexWithUV(d11, (double)(y + 1), d13, d1, d2);
	                tessellator.addVertexWithUV(d11, (double)(y + 1), d13, d0, d2);
	                tessellator.addVertexWithUV(d11, (double)(y + 0), d13, d0, d3);
	                tessellator.addVertexWithUV(d10, (double)(y + 0), d13, d1, d3);
	                tessellator.addVertexWithUV(d10, (double)(y + 1), d13, d1, d2);

	                if (!flag1 && !flag)
	                {
	                    tessellator.addVertexWithUV(d10, (double)(y + 1), d17, d4, d6);
	                    tessellator.addVertexWithUV(d10, (double)(y + 0), d17, d4, d8);
	                    tessellator.addVertexWithUV(d10, (double)(y + 0), d18, d5, d8);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1), d18, d5, d6);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1), d18, d4, d6);
	                    tessellator.addVertexWithUV(d10, (double)(y + 0), d18, d4, d8);
	                    tessellator.addVertexWithUV(d10, (double)(y + 0), d17, d5, d8);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1), d17, d5, d6);
	                }

	                if (flag4 || y < l - 1 && world.isAirBlock(x + 1, y + 1, z))
	                {
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d18, d5, d6);
	                    tessellator.addVertexWithUV(d11, (double)(y + 1) + 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d11, (double)(y + 1) + 0.01D, d17, d4, d7);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d17, d4, d6);
	                    tessellator.addVertexWithUV(d11, (double)(y + 1) + 0.01D, d18, d5, d6);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d17, d4, d7);
	                    tessellator.addVertexWithUV(d11, (double)(y + 1) + 0.01D, d17, d4, d6);
	                }

	                if (flag5 || y > 1 && world.isAirBlock(x + 1, y - 1, z))
	                {
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d18, d5, d6);
	                    tessellator.addVertexWithUV(d11, (double)y - 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d11, (double)y - 0.01D, d17, d4, d7);
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d17, d4, d6);
	                    tessellator.addVertexWithUV(d11, (double)y - 0.01D, d18, d5, d6);
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d17, d4, d7);
	                    tessellator.addVertexWithUV(d11, (double)y - 0.01D, d17, d4, d6);
	                }
	            }
	        }
	        else
	        {
	            tessellator.addVertexWithUV(d9, (double)(y + 1), d13, d21, d2);
	            tessellator.addVertexWithUV(d9, (double)(y + 0), d13, d21, d3);
	            tessellator.addVertexWithUV(d11, (double)(y + 0), d13, d1, d3);
	            tessellator.addVertexWithUV(d11, (double)(y + 1), d13, d1, d2);
	            tessellator.addVertexWithUV(d11, (double)(y + 1), d13, d21, d2);
	            tessellator.addVertexWithUV(d11, (double)(y + 0), d13, d21, d3);
	            tessellator.addVertexWithUV(d9, (double)(y + 0), d13, d1, d3);
	            tessellator.addVertexWithUV(d9, (double)(y + 1), d13, d1, d2);

	            if (flag4)
	            {
	                tessellator.addVertexWithUV(d9, (double)(y + 1) + 0.01D, d18, d5, d8);
	                tessellator.addVertexWithUV(d11, (double)(y + 1) + 0.01D, d18, d5, d6);
	                tessellator.addVertexWithUV(d11, (double)(y + 1) + 0.01D, d17, d4, d6);
	                tessellator.addVertexWithUV(d9, (double)(y + 1) + 0.01D, d17, d4, d8);
	                tessellator.addVertexWithUV(d11, (double)(y + 1) + 0.01D, d18, d5, d8);
	                tessellator.addVertexWithUV(d9, (double)(y + 1) + 0.01D, d18, d5, d6);
	                tessellator.addVertexWithUV(d9, (double)(y + 1) + 0.01D, d17, d4, d6);
	                tessellator.addVertexWithUV(d11, (double)(y + 1) + 0.01D, d17, d4, d8);
	            }
	            else
	            {
	                if (y < l - 1 && world.isAirBlock(x - 1, y + 1, z))
	                {
	                    tessellator.addVertexWithUV(d9, (double)(y + 1) + 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d18, d5, d8);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d17, d4, d8);
	                    tessellator.addVertexWithUV(d9, (double)(y + 1) + 0.01D, d17, d4, d7);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d9, (double)(y + 1) + 0.01D, d18, d5, d8);
	                    tessellator.addVertexWithUV(d9, (double)(y + 1) + 0.01D, d17, d4, d8);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d17, d4, d7);
	                }

	                if (y < l - 1 && world.isAirBlock(x + 1, y + 1, z))
	                {
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d18, d5, d6);
	                    tessellator.addVertexWithUV(d11, (double)(y + 1) + 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d11, (double)(y + 1) + 0.01D, d17, d4, d7);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d17, d4, d6);
	                    tessellator.addVertexWithUV(d11, (double)(y + 1) + 0.01D, d18, d5, d6);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d10, (double)(y + 1) + 0.01D, d17, d4, d7);
	                    tessellator.addVertexWithUV(d11, (double)(y + 1) + 0.01D, d17, d4, d6);
	                }
	            }

	            if (flag5)
	            {
	                tessellator.addVertexWithUV(d9, (double)y - 0.01D, d18, d5, d8);
	                tessellator.addVertexWithUV(d11, (double)y - 0.01D, d18, d5, d6);
	                tessellator.addVertexWithUV(d11, (double)y - 0.01D, d17, d4, d6);
	                tessellator.addVertexWithUV(d9, (double)y - 0.01D, d17, d4, d8);
	                tessellator.addVertexWithUV(d11, (double)y - 0.01D, d18, d5, d8);
	                tessellator.addVertexWithUV(d9, (double)y - 0.01D, d18, d5, d6);
	                tessellator.addVertexWithUV(d9, (double)y - 0.01D, d17, d4, d6);
	                tessellator.addVertexWithUV(d11, (double)y - 0.01D, d17, d4, d8);
	            }
	            else
	            {
	                if (y > 1 && world.isAirBlock(x - 1, y - 1, z))
	                {
	                    tessellator.addVertexWithUV(d9, (double)y - 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d18, d5, d8);
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d17, d4, d8);
	                    tessellator.addVertexWithUV(d9, (double)y - 0.01D, d17, d4, d7);
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d9, (double)y - 0.01D, d18, d5, d8);
	                    tessellator.addVertexWithUV(d9, (double)y - 0.01D, d17, d4, d8);
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d17, d4, d7);
	                }

	                if (y > 1 && world.isAirBlock(x + 1, y - 1, z))
	                {
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d18, d5, d6);
	                    tessellator.addVertexWithUV(d11, (double)y - 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d11, (double)y - 0.01D, d17, d4, d7);
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d17, d4, d6);
	                    tessellator.addVertexWithUV(d11, (double)y - 0.01D, d18, d5, d6);
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d18, d5, d7);
	                    tessellator.addVertexWithUV(d10, (double)y - 0.01D, d17, d4, d7);
	                    tessellator.addVertexWithUV(d11, (double)y - 0.01D, d17, d4, d6);
	                }
	            }
	        }

	        if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1))
	        {
	            if (flag && !flag1)
	            {
	                tessellator.addVertexWithUV(d10, (double)(y + 1), d12, d21, d2);
	                tessellator.addVertexWithUV(d10, (double)(y + 0), d12, d21, d3);
	                tessellator.addVertexWithUV(d10, (double)(y + 0), d13, d0, d3);
	                tessellator.addVertexWithUV(d10, (double)(y + 1), d13, d0, d2);
	                tessellator.addVertexWithUV(d10, (double)(y + 1), d13, d21, d2);
	                tessellator.addVertexWithUV(d10, (double)(y + 0), d13, d21, d3);
	                tessellator.addVertexWithUV(d10, (double)(y + 0), d12, d0, d3);
	                tessellator.addVertexWithUV(d10, (double)(y + 1), d12, d0, d2);

	                if (!flag3 && !flag2)
	                {
	                    tessellator.addVertexWithUV(d15, (double)(y + 1), d13, d4, d6);
	                    tessellator.addVertexWithUV(d15, (double)(y + 0), d13, d4, d8);
	                    tessellator.addVertexWithUV(d16, (double)(y + 0), d13, d5, d8);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1), d13, d5, d6);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1), d13, d4, d6);
	                    tessellator.addVertexWithUV(d16, (double)(y + 0), d13, d4, d8);
	                    tessellator.addVertexWithUV(d15, (double)(y + 0), d13, d5, d8);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1), d13, d5, d6);
	                }

	                if (flag4 || y < l - 1 && world.isAirBlock(x, y + 1, z - 1))
	                {
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d12, d5, d6);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d13, d5, d7);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d13, d4, d7);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d12, d4, d6);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d13, d5, d6);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d12, d5, d7);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d12, d4, d7);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d13, d4, d6);
	                }

	                if (flag5 || y > 1 && world.isAirBlock(x, y - 1, z - 1))
	                {
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d12, d5, d6);
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d13, d5, d7);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d13, d4, d7);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d12, d4, d6);
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d13, d5, d6);
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d12, d5, d7);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d12, d4, d7);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d13, d4, d6);
	                }
	            }
	            else if (!flag && flag1)
	            {
	                tessellator.addVertexWithUV(d10, (double)(y + 1), d13, d0, d2);
	                tessellator.addVertexWithUV(d10, (double)(y + 0), d13, d0, d3);
	                tessellator.addVertexWithUV(d10, (double)(y + 0), d14, d1, d3);
	                tessellator.addVertexWithUV(d10, (double)(y + 1), d14, d1, d2);
	                tessellator.addVertexWithUV(d10, (double)(y + 1), d14, d0, d2);
	                tessellator.addVertexWithUV(d10, (double)(y + 0), d14, d0, d3);
	                tessellator.addVertexWithUV(d10, (double)(y + 0), d13, d1, d3);
	                tessellator.addVertexWithUV(d10, (double)(y + 1), d13, d1, d2);

	                if (!flag3 && !flag2)
	                {
	                    tessellator.addVertexWithUV(d16, (double)(y + 1), d13, d4, d6);
	                    tessellator.addVertexWithUV(d16, (double)(y + 0), d13, d4, d8);
	                    tessellator.addVertexWithUV(d15, (double)(y + 0), d13, d5, d8);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1), d13, d5, d6);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1), d13, d4, d6);
	                    tessellator.addVertexWithUV(d15, (double)(y + 0), d13, d4, d8);
	                    tessellator.addVertexWithUV(d16, (double)(y + 0), d13, d5, d8);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1), d13, d5, d6);
	                }

	                if (flag4 || y < l - 1 && world.isAirBlock(x, y + 1, z + 1))
	                {
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d13, d4, d7);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d14, d4, d8);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d14, d5, d8);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d13, d5, d7);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d14, d4, d7);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d13, d4, d8);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d13, d5, d8);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d14, d5, d7);
	                }

	                if (flag5 || y > 1 && world.isAirBlock(x, y - 1, z + 1))
	                {
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d13, d4, d7);
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d14, d4, d8);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d14, d5, d8);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d13, d5, d7);
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d14, d4, d7);
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d13, d4, d8);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d13, d5, d8);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d14, d5, d7);
	                }
	            }
	        }
	        else
	        {
	            tessellator.addVertexWithUV(d10, (double)(y + 1), d14, d21, d2);
	            tessellator.addVertexWithUV(d10, (double)(y + 0), d14, d21, d3);
	            tessellator.addVertexWithUV(d10, (double)(y + 0), d12, d1, d3);
	            tessellator.addVertexWithUV(d10, (double)(y + 1), d12, d1, d2);
	            tessellator.addVertexWithUV(d10, (double)(y + 1), d12, d21, d2);
	            tessellator.addVertexWithUV(d10, (double)(y + 0), d12, d21, d3);
	            tessellator.addVertexWithUV(d10, (double)(y + 0), d14, d1, d3);
	            tessellator.addVertexWithUV(d10, (double)(y + 1), d14, d1, d2);

	            if (flag4)
	            {
	                tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d14, d5, d8);
	                tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d12, d5, d6);
	                tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d12, d4, d6);
	                tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d14, d4, d8);
	                tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d12, d5, d8);
	                tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d14, d5, d6);
	                tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d14, d4, d6);
	                tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d12, d4, d8);
	            }
	            else
	            {
	                if (y < l - 1 && world.isAirBlock(x, y + 1, z - 1))
	                {
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d12, d5, d6);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d13, d5, d7);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d13, d4, d7);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d12, d4, d6);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d13, d5, d6);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d12, d5, d7);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d12, d4, d7);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d13, d4, d6);
	                }

	                if (y < l - 1 && world.isAirBlock(x, y + 1, z + 1))
	                {
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d13, d4, d7);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d14, d4, d8);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d14, d5, d8);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d13, d5, d7);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d14, d4, d7);
	                    tessellator.addVertexWithUV(d15, (double)(y + 1) + 0.005D, d13, d4, d8);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d13, d5, d8);
	                    tessellator.addVertexWithUV(d16, (double)(y + 1) + 0.005D, d14, d5, d7);
	                }
	            }

	            if (flag5)
	            {
	                tessellator.addVertexWithUV(d16, (double)y - 0.005D, d14, d5, d8);
	                tessellator.addVertexWithUV(d16, (double)y - 0.005D, d12, d5, d6);
	                tessellator.addVertexWithUV(d15, (double)y - 0.005D, d12, d4, d6);
	                tessellator.addVertexWithUV(d15, (double)y - 0.005D, d14, d4, d8);
	                tessellator.addVertexWithUV(d16, (double)y - 0.005D, d12, d5, d8);
	                tessellator.addVertexWithUV(d16, (double)y - 0.005D, d14, d5, d6);
	                tessellator.addVertexWithUV(d15, (double)y - 0.005D, d14, d4, d6);
	                tessellator.addVertexWithUV(d15, (double)y - 0.005D, d12, d4, d8);
	            }
	            else
	            {
	                if (y > 1 && world.isAirBlock(x, y - 1, z - 1))
	                {
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d12, d5, d6);
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d13, d5, d7);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d13, d4, d7);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d12, d4, d6);
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d13, d5, d6);
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d12, d5, d7);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d12, d4, d7);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d13, d4, d6);
	                }

	                if (y > 1 && world.isAirBlock(x, y - 1, z + 1))
	                {
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d13, d4, d7);
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d14, d4, d8);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d14, d5, d8);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d13, d5, d7);
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d14, d4, d7);
	                    tessellator.addVertexWithUV(d15, (double)y - 0.005D, d13, d4, d8);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d13, d5, d8);
	                    tessellator.addVertexWithUV(d16, (double)y - 0.005D, d14, d5, d7);
	                }
	            }
	        }

	        return true;
	}

	@Override
	public boolean shouldRender3DInInventory(final int i)
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return RenderIDs.blockCastIronRailingRI;
	}
}
