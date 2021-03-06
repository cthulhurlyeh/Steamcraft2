
package steamcraft.common.packets;

import boilerplate.client.ClientHelper;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author decebaldecebal
 *
 */
public class UpdateClientsideInventoryPacket implements IMessage
{
	private int x, y, z;
	private int[] ids = new int[] {};

	public UpdateClientsideInventoryPacket()
	{
	} // REQUIRED

	public UpdateClientsideInventoryPacket(int x, int y, int z, int[] ids)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.ids = ids;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		for (int i = 0; i < this.ids.length; i++)
		{
			this.ids[i] = buf.readInt();
		}
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		for (int id : this.ids)
		{
			buf.writeInt(id);
		}
	}

	public static class UpdateClientsideInventoryPacketHandler implements IMessageHandler<UpdateClientsideInventoryPacket, IMessage>
	{
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(UpdateClientsideInventoryPacket message, MessageContext ctx)
		{
			World world = ClientHelper.world();

			if (world.getTileEntity(message.x, message.y, message.z) instanceof BaseTileWithInventory)
			{
				BaseTileWithInventory tile = (BaseTileWithInventory) world.getTileEntity(message.x, message.y, message.z);

				for (int i = 0; i < message.ids.length; i++)
				{
					tile.inventory[i] = new ItemStack(Item.getItemById(message.ids[i]));
				}
			}

			return null;
		}
	}
}