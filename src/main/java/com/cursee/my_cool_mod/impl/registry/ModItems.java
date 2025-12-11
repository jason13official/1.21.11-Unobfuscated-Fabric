package com.cursee.my_cool_mod.impl.registry;

import com.cursee.my_cool_mod.MyCoolMod;
import com.cursee.my_cool_mod.impl.item.ModStickItem;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;

public class ModItems {

//  public static Item MOD_STICK;

  public static final Item MOD_STICK = Items.registerItem(modItemId("mod_stick"), ModStickItem::new, new Properties());

  public static void registerItems() {

//    Identifier modStickId = Identifier.fromNamespaceAndPath(MyCoolMod.MOD_ID, "mod_stick");
//    ResourceKey<Item> modStickKey = ResourceKey.create(Registries.ITEM, modStickId);
//    MOD_STICK = new ModStickItem(new Properties().setId(modStickKey));

  }

  private static ResourceKey<Item> modItemId(final String name) {
    return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MyCoolMod.MOD_ID, name));
  }
}
