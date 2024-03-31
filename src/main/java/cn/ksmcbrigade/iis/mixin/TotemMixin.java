package cn.ksmcbrigade.iis.mixin;

import net.minecraft.advancements.criterion.UsedTotemTrigger;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static cn.ksmcbrigade.iis.InfinityItems.HAS;

@Mixin(UsedTotemTrigger.class)
public abstract class TotemMixin {

    @Inject(method = "trigger",at = @At("TAIL"))
    public void SetAir(ServerPlayerEntity p_193187_1_, ItemStack p_193187_2_, CallbackInfo ci){
        if(HAS(p_193187_2_.getEnchantmentTags())){
            ItemStack itemStack = Items.TOTEM_OF_UNDYING.getDefaultInstance();
            itemStack.enchant(Enchantments.INFINITY_ARROWS,1);
            if(!p_193187_1_.getMainHandItem().isEmpty()){
                p_193187_1_.setItemInHand(Hand.OFF_HAND,itemStack);
            }
            else{
                p_193187_1_.setItemInHand(Hand.MAIN_HAND,itemStack);
            }
        }
    }
}
