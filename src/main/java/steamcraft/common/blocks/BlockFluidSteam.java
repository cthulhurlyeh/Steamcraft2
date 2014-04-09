package steamcraft.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidSteam extends BlockFluidClassic 
{
	@SideOnly(Side.CLIENT)
	protected Icon stillIcon;
	@SideOnly(Side.CLIENT)
	protected Icon flowingIcon;

	public BlockFluidSteam(Fluid fluid, Material material) 
	{
		super(1000, fluid, material);
		setUnlocalizedName("steamFluidBlock");
		setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public Icon getIcon(int side, int meta) 
	{
		return (side == 0 || side == 1) ? stillIcon : flowingIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) 
	{
		stillIcon = register.registerIcon(LibInfo.PREFIX + "steamStill");
		flowingIcon = register.registerIcon(LibInfo.PREFIX + "flowingIcon");
	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) 
	{
		if (world.getBlockMaterial(x,  y,  z).isLiquid()) 
			return false;
		return 
			super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) 
	{
		if (world.getBlockMaterial(x,  y,  z).isLiquid())
			return false;
		return 
			super.displaceIfPossible(world, x, y, z);
	}
}