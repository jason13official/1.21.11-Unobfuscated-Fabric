package com.cursee.my_cool_mod.mixin;

import com.cursee.my_cool_mod.impl.registry.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuiltInRegistries.class)
public class BuiltInRegistriesMixin {

  @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/core/registries/BuiltInRegistries;createContents()V"), method = "bootStrap")
  private static void my_cool_mod$bootStrap(CallbackInfo ci) {
    ModItems.registerItems();
  }

}
