/*
 * 
 */
package steamcraft.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class TileDropHammer.
 */
public class TileDropHammer extends TileEntity
{

	/** The is master. */
	private boolean hasMaster, isMaster;

	/** The master z. */
	private int masterX, masterY, masterZ;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.tileentity.TileEntity#updateEntity()
	 */
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (!worldObj.isRemote)
		{
			if (hasMaster())
			{
				if (isMaster())
				{
					if (checkMultiBlockForm())
					{
						System.out.print("potato");
					}
					else
						resetStructure();
				}
				else
				{
					if (checkForMaster())
						reset();
				}
			}
			else
			{
				// Constantly check if structure is formed until it is.
				if (checkMultiBlockForm())
					setupStructure();
			}
		}
	}

	/**
	 * Check that structure is properly formed.
	 * 
	 * @return true, if successful
	 */
	public boolean checkMultiBlockForm()
	{
		int i = 0;
		// Scan a 3x3x3 area, starting with the bottom left corner
		for (int x = xCoord - 1; x < xCoord + 2; x++)
			for (int y = yCoord; y < yCoord + 3; y++)
				for (int z = zCoord - 1; z < zCoord + 2; z++)
				{
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					// Make sure tile isn't null, is an instance of the same
					// Tile, and isn't already a part of a multiblock
					if (tile != null && (tile instanceof TileDropHammer) && !((TileDropHammer) tile).hasMaster())
						i++;
				}
		// check if there are 26 blocks present ((3*3*3) - 1) and check that
		// center block is empty
		return i > 25 && worldObj.isAirBlock(xCoord, yCoord + 1, zCoord);
	}

	/**
	 * Setup all the blocks in the structure.
	 */
	public void setupStructure()
	{
		for (int x = xCoord - 1; x < xCoord + 2; x++)
			for (int y = yCoord; y < yCoord + 3; y++)
				for (int z = zCoord - 1; z < zCoord + 2; z++)
				{
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					// Check if block is bottom center block
					boolean master = (x == xCoord && y == yCoord && z == zCoord);
					if (tile != null && (tile instanceof TileDropHammer))
					{
						((TileDropHammer) tile).setMasterCoords(xCoord, yCoord, zCoord);
						((TileDropHammer) tile).setHasMaster(true);
						((TileDropHammer) tile).setIsMaster(master);
					}
				}
	}

	/**
	 * Reset method to be run when the master is gone or tells them to.
	 */
	public void reset()
	{
		masterX = 0;
		masterY = 0;
		masterZ = 0;
		hasMaster = false;
		isMaster = false;
	}

	/**
	 * Check that the master exists.
	 * 
	 * @return true, if successful
	 */
	public boolean checkForMaster()
	{
		TileEntity tile = worldObj.getTileEntity(masterX, masterY, masterZ);
		return (tile != null && (tile instanceof TileDropHammer));
	}

	/**
	 * Reset all the parts of the structure.
	 */
	public void resetStructure()
	{
		for (int x = xCoord - 1; x < xCoord + 2; x++)
			for (int y = yCoord; y < yCoord + 3; y++)
				for (int z = zCoord - 1; z < zCoord + 2; z++)
				{
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					if (tile != null && (tile instanceof TileDropHammer))
						((TileDropHammer) tile).reset();
				}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.tileentity.TileEntity#writeToNBT(net.minecraft.nbt.
	 * NBTTagCompound)
	 */
	@Override
	public void writeToNBT(NBTTagCompound data)
	{
		super.writeToNBT(data);
		data.setInteger("masterX", masterX);
		data.setInteger("masterY", masterY);
		data.setInteger("masterZ", masterZ);
		data.setBoolean("hasMaster", hasMaster);
		data.setBoolean("isMaster", isMaster);
		if (hasMaster() && isMaster())
		{
			// Any other values should ONLY BE SAVED TO THE MASTER
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.tileentity.TileEntity#readFromNBT(net.minecraft.nbt.
	 * NBTTagCompound)
	 */
	@Override
	public void readFromNBT(NBTTagCompound data)
	{
		super.readFromNBT(data);
		masterX = data.getInteger("masterX");
		masterY = data.getInteger("masterY");
		masterZ = data.getInteger("masterZ");
		hasMaster = data.getBoolean("hasMaster");
		isMaster = data.getBoolean("isMaster");
		if (hasMaster() && isMaster())
		{
			// Any other values should ONLY BE READ BY THE MASTER
		}
	}

	/**
	 * Checks for master.
	 * 
	 * @return true, if successful
	 */
	public boolean hasMaster()
	{
		return hasMaster;
	}

	/**
	 * Checks if is master.
	 * 
	 * @return true, if is master
	 */
	public boolean isMaster()
	{
		return isMaster;
	}

	/**
	 * Gets the master x.
	 * 
	 * @return the master x
	 */
	public int getMasterX()
	{
		return masterX;
	}

	/**
	 * Gets the master y.
	 * 
	 * @return the master y
	 */
	public int getMasterY()
	{
		return masterY;
	}

	/**
	 * Gets the master z.
	 * 
	 * @return the master z
	 */
	public int getMasterZ()
	{
		return masterZ;
	}

	/**
	 * Sets the checks for master.
	 * 
	 * @param bool
	 *            the new checks for master
	 */
	public void setHasMaster(boolean bool)
	{
		hasMaster = bool;
	}

	/**
	 * Sets the checks if is master.
	 * 
	 * @param bool
	 *            the new checks if is master
	 */
	public void setIsMaster(boolean bool)
	{
		isMaster = bool;
	}

	/**
	 * Sets the master coords.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 */
	public void setMasterCoords(int x, int y, int z)
	{
		masterX = x;
		masterY = y;
		masterZ = z;
	}
}