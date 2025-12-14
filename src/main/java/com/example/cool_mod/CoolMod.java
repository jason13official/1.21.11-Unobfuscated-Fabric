package com.example.cool_mod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.KineticWeapon;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.HitResult.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoolMod implements ModInitializer {

  public static final String MOD_ID = "cool_mod";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  private static ResourceKey<Item> modItemId(final String name) {

    return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, name));
  }

  public static InteractionResult use(Item item, Level level, Player player, InteractionHand hand) {
//    LOGGER.info("Used the mod stick item!");
//    return InteractionResult.SUCCESS;

    ItemStack stack = player.getItemInHand(hand);
    Consumable consumable = stack.get(DataComponents.CONSUMABLE);
    if (consumable != null) {
      return consumable.startConsuming(player, stack, hand);
    } else {
      Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
      if (equippable != null && equippable.swappable()) {
        return equippable.swapWithEquipmentSlot(stack, player);
      } else if (stack.has(DataComponents.BLOCKS_ATTACKS)) {
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
      } else {
        KineticWeapon kineticWeapon = stack.get(DataComponents.KINETIC_WEAPON);
        if (kineticWeapon != null) {
          player.startUsingItem(hand);
          kineticWeapon.makeSound(player);
          return InteractionResult.CONSUME;
        } else {
          return InteractionResult.PASS;
        }
      }
    }
  }

  public static int useDuration(Item item, ItemStack itemStack, LivingEntity user) {
    return 10;

//    Consumable consumable = itemStack.get(DataComponents.CONSUMABLE);
//    if (consumable != null) {
//      return consumable.consumeTicks();
//    } else {
//      return !itemStack.has(DataComponents.BLOCKS_ATTACKS) && !itemStack.has(DataComponents.KINETIC_WEAPON) ? 0 : 72000;
//    }
  }

  public static ItemUseAnimation useAnimation(Item item, ItemStack itemStack) {
    // return ItemUseAnimation.BLOCK;

//    Consumable consumable = itemStack.get(DataComponents.CONSUMABLE);
//    if (consumable != null) {
//      return consumable.animation();
//    } else if (itemStack.has(DataComponents.BLOCKS_ATTACKS)) {
//      return ItemUseAnimation.BLOCK;
//    } else {
//      return itemStack.has(DataComponents.KINETIC_WEAPON) ? ItemUseAnimation.SPEAR : ItemUseAnimation.NONE;
//    }

    return ItemUseAnimation.SPYGLASS;
  }

  public static InteractionResult useOn(Item item, UseOnContext context) {
//    BlockPlaceContext placeContext = new BlockPlaceContext(context);
//
//    BlockPos placePos = placeContext.getClickedPos();
//
//    Level level = context.getLevel();
//
//    if (level instanceof ServerLevel serverLevel && serverLevel.getBlockState(placePos).canBeReplaced()) {
//      serverLevel.setBlockAndUpdate(placePos, Blocks.LAVA.defaultBlockState());
//      return InteractionResult.SUCCESS;
//    }
//
//    return InteractionResult.FAIL;

//    return InteractionResult.PASS;

    Player player = context.getPlayer();
    if (player != null) {
      player.startUsingItem(context.getHand());
    }

    return InteractionResult.CONSUME;
  }

//  public static HitResult calculateHitResult(final Player player) {
//    return ProjectileUtil.getHitResultOnViewVector(player, EntitySelector.CAN_BE_PICKED, player.blockInteractionRange());
//  }











  @Override
  public void onInitialize() {
    Items.registerItem(modItemId("mod_stick"), ModStickItem::new, new Properties());
  }




  public static class ModStickItem extends Item {

    public ModStickItem(Properties properties) {
      super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
      return CoolMod.use(this, level, player, hand);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

      return CoolMod.useOn(this, context);
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity user) {
      return CoolMod.useDuration(this, itemStack, user);
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
      return CoolMod.useAnimation(this, itemStack);
    }
  }
}