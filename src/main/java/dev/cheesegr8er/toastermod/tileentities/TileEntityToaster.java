package dev.cheesegr8er.toastermod.tileentities;

import dev.cheesegr8er.toastermod.block.BlockToaster;
import dev.cheesegr8er.toastermod.init.ModSounds;
import dev.cheesegr8er.toastermod.init.ModTileEntities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;

public class TileEntityToaster extends TileEntity implements ITickableTileEntity{

	private int ticksTillToasted;
	public static final int TOAST_TIME = 100;

	public TileEntityToaster(){
		super(ModTileEntities.toaster_tile_entity);
		ticksTillToasted = 0;
	}

	/**
	 * Read the time until toast is done.
	 */
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		if(compound.contains("ticksTillToasted")) {
			this.ticksTillToasted = compound.getInt("ticksTillToasted");
		}
	}

	/**
	 * Write the time until toast is done.
	 */
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.putInt("ticksTillToasted", ticksTillToasted);
		return compound;
	}

	/**
	 * Start the timer for toasting.
	 */
	public void startTimer() {
		ticksTillToasted = TOAST_TIME;
	}

	/**
	 * Decrement the timer, until it reaches 0.
	 */
	@Override
	public void tick() {
		if(ticksTillToasted == 1) {
			toastBread();
		}
		if(ticksTillToasted > 0) {
			ticksTillToasted--;
			this.markDirty();
		}
	}

	/**
	 * Change the blockstate of the toaster to indicate that toast is ready.
	 */
	private void toastBread() {
		this.getWorld().setBlockState(this.getPos(), this.getBlockState().with(BlockToaster.STATUS, 4));
		System.out.println("Bread has been toasted!");
		this.getWorld().playSound(null, pos.getX(), pos.getY(), pos.getZ(), ModSounds.toaster_up, SoundCategory.BLOCKS, 1.0f, 1.0f);
	}
}