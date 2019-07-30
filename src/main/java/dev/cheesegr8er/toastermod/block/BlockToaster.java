package dev.cheesegr8er.toastermod.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dev.cheesegr8er.toastermod.init.ModBlocks;
import dev.cheesegr8er.toastermod.init.ModItems;
import dev.cheesegr8er.toastermod.init.ModSounds;
import dev.cheesegr8er.toastermod.tileentities.TileEntityToaster;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeBlock;
import javax.annotation.Nullable;

/**
 * Toaster block.
 * 
 * @author Terence
 */
public class BlockToaster extends FallingBlock implements IForgeBlock{

	public static final Direction[] HORIZONTAL_DIRECTIONS = new Direction[] {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

	public static final DirectionProperty FACING = DirectionProperty.create("facing", HORIZONTAL_DIRECTIONS);
	public static final IntegerProperty STATUS = IntegerProperty.create("status", 0, 4);
	public static final VoxelShape TOASTER_BB = BlockToaster.makeCuboidShape(1, 0, 1, 15, 9, 15);

	public BlockToaster() {
		super(Properties.create(Material.IRON).hardnessAndResistance(5, 30));

		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(STATUS, 0));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, STATUS);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return TOASTER_BB;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader worldIn) {
		return new TileEntityToaster();
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> drops = new ArrayList<ItemStack>();

		if(state.get(STATUS)==1) {
			drops.add(new ItemStack(ModItems.bread_slice, 1));
		} else if (state.get(STATUS)==2){
			drops.add(new ItemStack(ModItems.bread_slice, 1));
		} else if (state.get(STATUS)==3) {
			drops.add(new ItemStack(ModItems.toast_slice, 1));
		}
		drops.add(new ItemStack(ModBlocks.toaster, 1));

		return drops;
	}

	/**
	 * The left click button.
	 */
	@Override
	public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		if(!worldIn.isRemote()) {
			int toasterStatus = worldIn.getBlockState(pos).get(STATUS);

			if(toasterStatus == 1) {
				// Spit the single slice back out
				worldIn.setBlockState(pos, state.with(STATUS, 0));
				worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.bread_slice)));
			} else if(toasterStatus == 2) {
				// Spit two slices back out
				worldIn.setBlockState(pos, state.with(STATUS, 0));
				worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.bread_slice,2)));
			} else if (toasterStatus == 4) {
				// Retrieve the toast
				worldIn.setBlockState(pos, state.with(STATUS, 0));
				worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.toast_slice,2)));
			}
		}
	}

	/**
	 * The right click button.
	 */
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		TileEntityToaster tileEntity = (TileEntityToaster) worldIn.getTileEntity(pos);
		int toasterStatus = worldIn.getBlockState(pos).get(STATUS);

		if(!worldIn.isRemote()) {
			boolean playerHasBreadSlice = player.getHeldItemMainhand().getItem().getRegistryName().toString().equals("toastermod:bread_slice");	

			if(toasterStatus == 0 && playerHasBreadSlice) {
				// Place bread in the toaster
				if(!player.isCreative()) {
					player.getHeldItemMainhand().setCount(player.getHeldItemMainhand().getCount()-1);
				}
				worldIn.setBlockState(pos, state.with(STATUS, 1));
			} else if(toasterStatus == 1 && playerHasBreadSlice) {
				// Place more bread in the toaster
				if(!player.isCreative()) {
					player.getHeldItemMainhand().setCount(player.getHeldItemMainhand().getCount()-1);
				}
				worldIn.setBlockState(pos, state.with(STATUS, 2));
			} else if(toasterStatus == 2) {
				// Set the toaster
				worldIn.setBlockState(pos, state.with(STATUS, 3));
				tileEntity.startTimer();
				worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), ModSounds.toaster_down, SoundCategory.BLOCKS, 1.0f, 1.0f);
			} else if(toasterStatus == 4) {
				// Retreive the toast
				worldIn.addEntity(new ItemEntity(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), new ItemStack(ModItems.toast_slice,2)));
				worldIn.setBlockState(pos, state.with(STATUS, 0));
			}
			return true;
		} else {
			return true;
		}
	}
	
	/**
	 * Produce a toasting effect if the toaster is down.
	 */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if(worldIn.getBlockState(pos).get(STATUS)==3) {
			double d0 = (double)pos.getX()+rand.nextFloat()*0.5;
			double d1 = (double)pos.getY()+rand.nextFloat()*0.5;
			double d2 = (double)pos.getZ()+rand.nextFloat()*0.5;

			worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected void onStartFalling(FallingBlockEntity fallingEntity) {
		fallingEntity.setHurtEntities(true);
	}

	@Override
	public void onEndFalling(World worldIn, BlockPos pos, BlockState fallingState, BlockState hitState) {
		worldIn.playEvent(1031, pos, 0);
	}
}
