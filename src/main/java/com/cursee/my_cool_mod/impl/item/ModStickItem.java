package com.cursee.my_cool_mod.impl.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class ModStickItem extends Item {

  public ModStickItem(Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {

    BlockPlaceContext placeContext = new BlockPlaceContext(context);

    BlockPos placePos = placeContext.getClickedPos();

    Level level = context.getLevel();

    if (level instanceof ServerLevel serverLevel && serverLevel.getBlockState(placePos).canBeReplaced()) {
      serverLevel.setBlockAndUpdate(placePos, Blocks.LAVA.defaultBlockState());
      return InteractionResult.SUCCESS;
    }

    return InteractionResult.FAIL;
  }
}
